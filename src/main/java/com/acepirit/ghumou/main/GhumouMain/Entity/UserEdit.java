package com.acepirit.ghumou.main.GhumouMain.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
public class UserEdit {

	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String email;
	
	private ProfileEdit user_profile;
	
	public UserEdit() {}
		
	public UserEdit(String firstName, String lastName, String userName, String email,
			ProfileEdit user_profile) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.user_profile = user_profile;
	}






	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ProfileEdit getUser_profile() {
		return user_profile;
	}


	public void setUser_profile(ProfileEdit user_profile) {
		this.user_profile = user_profile;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	
}
