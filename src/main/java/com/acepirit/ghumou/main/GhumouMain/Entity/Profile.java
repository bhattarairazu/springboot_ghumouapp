package com.acepirit.ghumou.main.GhumouMain.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="profile")
public class Profile {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="country")
	private String country;
	
	@Column(name="zip_code")
	private int zipCode;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="dob")
	private Date dob;
	
	@Column(name="profile_image")
	private String profileImage;
	
	public Profile() {}

	public Profile(String country, int zipCode, String phone, Date dob, String profileImage) {
		this.country = country;
		this.zipCode = zipCode;
		this.phone = phone;
		this.dob = dob;
		this.profileImage = profileImage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
	
	
	

}
