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

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="is_enabled")
	private boolean isEnabled = false;
	
	@Column(name="password")
	private String password;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="profile_id",referencedColumnName = "id")
	private Profile user_profile;
	
	public User() {}
	
	
	public User(String firstName, String lastName, String userName, String email, String password,
			Profile user_profile) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.user_profile = user_profile;
	}






	public User(String firstName, String lastName, String userName, String email, boolean isEnabled, String password,
			Profile user_profile) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.isEnabled = isEnabled;
		this.password = password;
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

	public Profile getUser_profile() {
		return user_profile;
	}


	public void setUser_profile(Profile user_profile) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return isEnabled;
	}


	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
	
	

}
