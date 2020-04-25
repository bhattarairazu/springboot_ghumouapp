package com.acepirit.ghumou.main.GhumouMain.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
			String password_db = user.getPassword();
			if(passwordEncoder.matches(password, password_db)) {
				return true;
			}else {
				return false;
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

	

	

}
