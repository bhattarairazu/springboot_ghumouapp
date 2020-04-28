package com.acepirit.ghumou.main.GhumouMain.Service;

import java.util.List;

import com.acepirit.ghumou.main.GhumouMain.Entity.PasswordChange;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;

public interface UserService {
	
	public User findByUserName(String username);
	
	public User findByEmail(String email);
	
	public boolean checkLogin(String username,String password);
	
	public boolean isUserNameExist(String username);
	
	public boolean isEmailExist(String email);
	
	public void save(User user);
	
	public void deleteById(int id);
	
	public List<User> findAll();
	
	public User findById(int id);
	
	public boolean isPasswordChanged(PasswordChange pwdChange);
	
	public List<User> findByKeyword(String keyword);
	
	

}
