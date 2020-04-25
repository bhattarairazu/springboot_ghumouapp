package com.acepirit.ghumou.main.GhumouMain.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ProfileEdit {


	private int id;
	
	private String country;
	
	private int zipCode;
	
	private String phone;
	
	private Date dob;
	
	
	
	public ProfileEdit() {}

	public ProfileEdit(String country, int zipCode, String phone, Date dob) {
		this.country = country;
		this.zipCode = zipCode;
		this.phone = phone;
		this.dob = dob;
		
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

}
