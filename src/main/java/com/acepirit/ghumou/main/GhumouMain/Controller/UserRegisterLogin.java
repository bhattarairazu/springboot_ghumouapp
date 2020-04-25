package com.acepirit.ghumou.main.GhumouMain.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.acepirit.ghumou.main.GhumouMain.Entity.AuthenticateRequest;
import com.acepirit.ghumou.main.GhumouMain.Entity.PasswordChange;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;
import com.acepirit.ghumou.main.GhumouMain.Entity.UserEdit;
import com.acepirit.ghumou.main.GhumouMain.Service.FileUploadService;
import com.acepirit.ghumou.main.GhumouMain.Service.GlobalResponseService;
import com.acepirit.ghumou.main.GhumouMain.Service.UserService;

@RestController
@RequestMapping("api/v2/account/")
public class UserRegisterLogin {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;

	//for uploading file
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private GlobalResponseService gresponse;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestPart("profileImage") MultipartFile profileImage,@RequestPart("user") User user){
		String email = user.getEmail();
		String username = user.getUserName();
		
		if(userService.isEmailExist(email)) {
			throw new RuntimeException("User with email "+email+" already Exist.Please insert new email");
		}
		if(userService.isUserNameExist(username)) {
			throw new RuntimeException("User with username "+username+" already Exist.Please insert new username");
				
		}
		String profileImagePath = fileUploadService.storeFile(profileImage);
		user.getUser_profile().setProfileImage(profileImagePath);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.save(user);
		
		return gresponse.responseClient(user);
		
		
	}
	//for login
	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody AuthenticateRequest userlogin){
		boolean isLoginSuccess = userService.checkLogin(userlogin.getUsername(), userlogin.getPassword());
		if(isLoginSuccess) {
			return gresponse.loginSuccessResponse(userService.findByUserName(userlogin.getUsername()).getId());
		}else {
			return gresponse.loginErrorResponse();
		}
		
	}
	
	//for lisitng all users
	@GetMapping("/usersList")
	public ResponseEntity<?> usersList(){
		List<User> allUsers = userService.findAll();
		return gresponse.listResponse(allUsers);
	}
	//finding user by username
	@RequestMapping(value="/users",params="username")
	public ResponseEntity<?> userByUsername(@RequestParam("username") String username){
		User user = userService.findByUserName(username);
		return gresponse.responseClient(user);
	}
	
	//finding user by username
		@RequestMapping(value="/users",params="id")
		public ResponseEntity<?> userById(@RequestParam("id") int id){
			User user = userService.findById(id);
			return gresponse.responseClient(user);
		}
		//finding user by email
		@RequestMapping(value="/users",params="email")
		public ResponseEntity<?> userByEmail(@RequestParam("email") String email){
			User user = userService.findByEmail(email);
			return gresponse.responseClient(user);
		}
		
	//changing password
		@PostMapping("/users/passwordchange")
		public ResponseEntity<?> changePassword(@RequestBody PasswordChange pwdChange){
			boolean ispasswordChanged = userService.isPasswordChanged(pwdChange);
			if(ispasswordChanged) {
				return gresponse.globalResponse("Success",HttpStatus.OK.value());
			}else {

				return gresponse.globalResponse("Your current password doesnot match",HttpStatus.BAD_REQUEST.value());
			}
		}
		
		//changing profilepicture
		@PostMapping("/users/changeprofilepicture")
		public ResponseEntity<?> changePicture(@RequestParam("profileImage") MultipartFile profileImage,@RequestParam("userid") int userid){
			User user = userService.findById(userid);
			if(user!=null) {
				String profileImagePath = fileUploadService.storeFile(profileImage);
				user.getUser_profile().setProfileImage(profileImagePath);
				userService.save(user);
				return gresponse.profilePicSuccess(profileImagePath);
			}else {
				throw new RuntimeException("User with id "+userid+" Not found");
			}
		}
		
		//editing profile information
		@PutMapping("/users/editProfile")
		public ResponseEntity<?> changeProfile(@RequestBody User edituser){
			//User userwithid = userService.findById(edituser.getId());
			int id = edituser.getId();
			User user = userService.findById(id);
			if(user !=null) {
				//retreiving password and image from saved user
				String profileImage = user.getUser_profile().getProfileImage();
				String password = user.getPassword();
				//editing user profile and saving
				edituser.setPassword(password);
				edituser.getUser_profile().setProfileImage(profileImage);
				userService.save(edituser);
				return gresponse.responseClient(edituser);
				}else {
					return gresponse.globalResponse("User with Id "+id+" Not Found",HttpStatus.BAD_REQUEST.value());
				}
			
				}
		//searching user based on username,firstame,lastname
		@GetMapping(value="/users",params="search")
		public ResponseEntity<?> getAllUserByKeyword(@RequestParam("search") String search){
			List<User> allSearchedUser = userService.findByKeyword(search);
			return gresponse.listResponse(allSearchedUser);
			
		}
		
		

}
