package com.acepirit.ghumou.main.GhumouMain.Controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import com.acepirit.ghumou.main.GhumouMain.Entity.*;
import com.acepirit.ghumou.main.GhumouMain.Repository.RoleRepository;
import com.acepirit.ghumou.main.GhumouMain.Utils.Jwtutil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.acepirit.ghumou.main.GhumouMain.Repository.ConfirmationTokenReposiroty;
import com.acepirit.ghumou.main.GhumouMain.Service.EmailService;
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
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ConfirmationTokenReposiroty confirmationTokenRepository;

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private Jwtutil jwtFilter;


	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_USER') or hasRole('ROLE_SELLAR')")
	public ResponseEntity<?> registerUser(@RequestPart("profileImage") MultipartFile profileImage,@RequestPart("user") User user){
		final String baseUrl = 
				ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		//System.out.println("Bawse url"+baseUrl);
		String email = user.getEmail();
		String username = user.getUserName();
		
		if(userService.isEmailExist(email)) {
			throw new RuntimeException("User with email "+email+" already Exist.Please insert new email");
		}
		if(userService.isUserNameExist(username)) {
			throw new RuntimeException("User with username "+username+" already Exist.Please insert new username");
				
		}
		//store the image to the disk and save the profile image path to the users table
		String profileImagePath=null;
		if(profileImage!=null) {
			try {
				profileImagePath = fileUploadService.storeFile(profileImage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//check if the user contains roles or not
		Set<Role> roleslist = user.getRoles();
		Set<Role> newCollection = new HashSet<>();
		//geting streams of roles from user

		for (Role role : roleslist) {
			//checking null roles
			if(role==null){
				throw new RuntimeException("You need to enter role of the user.Please try again");
			}else{
				//checking wether the role exis in the db or not
				Role rolea =roleRepository.findRoleByName(role.getName());
				if(rolea==null){
					throw new RuntimeException("Please enter valid user role i.e ROLE_SELLAR or ROLE_USER");
				}

				newCollection.add(rolea);
				//settign roles to user
				user.setRoles(newCollection);
			}

		}



		System.out.println("Get profile image path"+profileImagePath);

		user.getUser_profile().setProfileImage(profileImagePath);
		user.setPassword(passwordEncoder.encode(user.getPassword()));


		userService.save(user);
		
		//saving user and sending confirmation token
		ConfirmationToken confirmationToken = new ConfirmationToken(user);
		confirmationTokenRepository.save(confirmationToken);
		//sendign mail
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setSubject("Complete Registration");
		mailMessage.setFrom("acepirit@gmail.com");
		mailMessage.setText(" To Confirm Your Account, Please click here."
		+ "http://api.ghumou.com/ghumou/api/v2/account/confirm-account?token="+confirmationToken.getConfirmationToken());
		
		
		//email sent
		emailService.sendEmail(mailMessage);
		
		
		
		
		return gresponse.responseClient(user);
		
		
	}
	//for verifying account
	@GetMapping(value="/confirm-account",params="token")
	public ResponseEntity<?> confirmAccount(@RequestParam("token") String token){
		ConfirmationToken tokens = confirmationTokenRepository.findByConfirmationToken(token);
		if(tokens!=null) {
			User user = userService.findByEmail(tokens.getUser().getEmail());
			user.setEnabled(true);
			userService.save(user);
			return gresponse.globalResponse("Success", HttpStatus.OK.value());
		}else {
			return gresponse.globalResponse("Failed", HttpStatus.BAD_REQUEST.value());
			
		}
	}
	//for use patch request//which means only updating the requeired fields

	@PatchMapping("/updates/{id}")
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_USER') or hasRole('ROLE_SELLAR')")
	public ResponseEntity<?> updateRequiredFields(@RequestBody Map<String,Object> updates,@PathVariable int id){
		System.out.println("data"+updates);
		User user= userService.findById(id);
		System.out.print(user.getUser_profile());

		updates.forEach((k,v)->{
			if(k.matches("zipCode") || k.matches("dob")){
				Field fie = ReflectionUtils.findField(Profile.class,k);
				fie.setAccessible(true);
				ReflectionUtils.setField(fie,user.getUser_profile(),v);
				System.out.println("field inside profile values"+v);
			}else{
				Field field = ReflectionUtils.findField(User.class,k);
				field.setAccessible(true);
				ReflectionUtils.setField(field,user,v);
				System.out.println("field inside user values"+v);
			}

		});
		user.setUser_profile(user.getUser_profile());
		userService.save(user);
		return gresponse.globalResponse("Successfully Updated User Profile",HttpStatus.OK.value());
	}

	
	//for login
	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody AuthenticateRequest userlogin) throws Exception {
//		boolean isEnabled = userService.findByUserName(userlogin.getUsername()).isEnabled();
//		if(!isEnabled){
//			throw new RuntimeException("Please verify your email before login");
//		}
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userlogin.getUsername(),userlogin.getPassword()));
//
//		}catch(BadCredentialsException ex) {
//			throw new Exception("Incorrect username and password");
//		}
		boolean isLogin = userService.loginUser(userlogin,"API");
		if(isLogin){

			final UserDetails userDetails = userService.loadUserByUsername(userlogin.getUsername());
			String jwt = jwtFilter.generateToken(userDetails);
			return gresponse.loginSuccessResponse(userService.findByUserName(userlogin.getUsername()).getId(),jwt);
		}else{
			return gresponse.globalResponse("Failed",HttpStatus.BAD_REQUEST.value());

		}



//
//
//
//		boolean isLoginSuccess = userService.checkLogin(userlogin.getUsername(), userlogin.getPassword());
//		if(isLoginSuccess) {
//			return gresponse.loginSuccessResponse(userService.findByUserName(userlogin.getUsername()).getId());
//		}else {
//			return gresponse.loginErrorResponse();
//		}
//
	}
	
	//for lisitng all users
	@GetMapping("/usersList")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> usersList(){
		List<User> allUsers = userService.findAll();
		return gresponse.listResponse(allUsers);
	}
	//for lisitng all users
//	@GetMapping("/usersList")
//	public List<User> usersList(){
//		List<User> allUsers = userService.findAll();
//		return allUsers;
//	}

	
	
	//finding user by username
	@RequestMapping(value="/users",params="username")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> userByUsername(@RequestParam("username") String username){
		User user = userService.findByUserName(username);
		return gresponse.responseClient(user);
	}
	
	//finding user by username
		@RequestMapping(value="/users",params="id")
		@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
		public ResponseEntity<?> userById(@RequestParam("id") int id){
			User user = userService.findById(id);
			return gresponse.responseClient(user);
		}
		//finding user by email
		@RequestMapping(value="/users",params="email")
		@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
		public ResponseEntity<?> userByEmail(@RequestParam("email") String email){
			User user = userService.findByEmail(email);
			return gresponse.responseClient(user);
		}
		
	//changing password
		@PostMapping("/users/passwordchange")
		@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR')")
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
		@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
		public ResponseEntity<?> changePicture(@RequestParam("profileImage") MultipartFile profileImage,@RequestParam("userid") int userid){
			User user = userService.findById(userid);
			if(user!=null) {
				String profileImagePath=null;
				if(profileImage!=null) {
					try {
						profileImagePath = fileUploadService.storeFile(profileImage);
					} catch (IOException e) {
					
						e.printStackTrace();
					}
				}else {
					throw new RuntimeException("Profile Image Shouldnot be Empty");
				}
				user.getUser_profile().setProfileImage(profileImagePath);
				userService.save(user);
				return gresponse.profilePicSuccess(profileImagePath);
			}else {
				throw new RuntimeException("User with id "+userid+" Not found");
			}
		}
		
		//editing profile information
		@PutMapping("/users/editProfile")
		@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
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
		@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
		public ResponseEntity<?> getAllUserByKeyword(@RequestParam("search") String search){
			List<User> allSearchedUser = userService.findByKeyword(search);
			return gresponse.listResponse(allSearchedUser);
			
		}
		
		//for forgot password
		@GetMapping(value="/resetpassword",params="email")
		@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
		public ResponseEntity<?> resetPassword(@RequestParam("email") String email){
			User user = userService.findByEmail(email);
			if(user!=null) {
				String password = user.getPassword();
				String randomPasswordGenerater = UUID.randomUUID().toString();
				user.setPassword(passwordEncoder.encode(randomPasswordGenerater));
				userService.save(user);
				
				//sending password to registered email
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(user.getEmail());
				mailMessage.setFrom("acepirit@gmail.com");
				mailMessage.setSubject("Password Reset");
				mailMessage.setText("Your password reset is successfull.Please login with new password "+randomPasswordGenerater);
				emailService.sendEmail(mailMessage);
				return gresponse.globalResponse("Success", HttpStatus.OK.value());
				
			}else {
				return gresponse.globalResponse("Failed",HttpStatus.BAD_REQUEST.value());
			}
		}
		

}
