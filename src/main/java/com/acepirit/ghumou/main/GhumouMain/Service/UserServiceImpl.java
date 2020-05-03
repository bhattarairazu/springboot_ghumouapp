package com.acepirit.ghumou.main.GhumouMain.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.acepirit.ghumou.main.GhumouMain.Entity.AuthenticateRequest;
import com.acepirit.ghumou.main.GhumouMain.Entity.Role;
import com.acepirit.ghumou.main.GhumouMain.Repository.ConfirmationTokenReposiroty;
import com.acepirit.ghumou.main.GhumouMain.Repository.RoleRepository;
import com.acepirit.ghumou.main.GhumouMain.Utils.Jwtutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.PasswordChange;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;
import com.acepirit.ghumou.main.GhumouMain.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	//private NullAwareBeanUtilsBean beaUtils;
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

	@Override
	public User findByUserName(String username) {
		User user = userRepository.findByUserName(username);
		if(user==null) {
			throw new UsernameNotFoundException("Username with username "+username+" not found");
		}
		return user;
	}

	@Override
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if(user==null) {
			throw new RuntimeException("Username with email "+email+" not found");
		}
		return user;
	}

	@Override
	public boolean checkLogin(String username, String password) {
		User user = userRepository.findByUserName(username);
		if(user!=null) {
			if(user.isEnabled()) {
				String password_db = user.getPassword();
				if(passwordEncoder.matches(password, password_db)) {
					return true;
				}else {
					return false;
				}
			}else {
				throw new RuntimeException("Please Verify Your email before Login");
			}
			
		}else {
			throw new UsernameNotFoundException("Incorerct username or password");
		}
		
	}

	@Override
	public boolean isUserNameExist(String username) {
		User user = userRepository.findByUserName(username);
		if(user == null) {
			return false;
		}else {
			return true;
		}
		
	}

	@Override
	public boolean isEmailExist(String email) {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteById(int id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(int id){
		Optional<User> result = userRepository.findById(id);
		User user = null;
		if(result.isPresent()) {
			user = result.get();
		}else {
			throw new RuntimeException("User with id "+id+" not found");
		}
		return user;
	}

	@Override
	public boolean isPasswordChanged(PasswordChange pwdChange) {
		int id = pwdChange.getUserId();
		Optional<User> result = userRepository.findById(id);
		User user = null;
		if(result.isPresent()) {
			user = result.get();
			String currentpwd_server = user.getPassword();
			System.out.println("curent password "+currentpwd_server);
			String clientPassword = pwdChange.getCurrentPassword();
			if(passwordEncoder.matches(clientPassword, currentpwd_server)) {
				user.setPassword(passwordEncoder.encode(pwdChange.getNewPassword()));
				userRepository.save(user);
				return true;
			}else {
				return false;
			}
		}else {
			throw new RuntimeException("User with id "+id+" not found");
		}
		
	}

	@Override
	public List<User> findByKeyword(String keyword) {
		List<User> allUser = userRepository.findUsersByKeyword(keyword);
		
		return allUser;
	}

	@Override
	public void patch(User user) {
		Optional<User> result = userRepository.findById(user.getId());
		User users = null;
		if(result.isPresent()){
			users = result.get();

		}
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);

		if (user == null) {
			throw new UsernameNotFoundException("User with username "+username+" Not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().
				map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return authorities;
	}


	//login to the respected user from both restapi and dashboard
	@Override
	public boolean loginUser(AuthenticateRequest userlogin,String method) throws Exception {
		boolean isEnabled = findByUserName(userlogin.getUsername()).isEnabled();
		if (!isEnabled) {
			if(method.matches("API")) {
				throw new RuntimeException("Please verify your email before login");
			}else{
				return false;
			}
		}
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userlogin.getUsername(), userlogin.getPassword()));

		} catch (BadCredentialsException ex) {
			if(method.matches("API")) {
				throw new Exception("Incorrect username and password");
			}else{
				return false;
			}
		}
		return true;
//		final UserDetails userDetails = loadUserByUsername(userlogin.getUsername());
//		String jwt = jwtFilter.generateToken(userDetails);
//		return gresponse.loginSuccessResponse(findByUserName(userlogin.getUsername()).getId(), jwt);

		}
	}