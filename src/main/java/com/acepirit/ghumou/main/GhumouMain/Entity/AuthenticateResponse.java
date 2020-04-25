package com.acepirit.ghumou.main.GhumouMain.Entity;

public class AuthenticateResponse {
	public final String jwt;

	
	
	public AuthenticateResponse(String jwt) {
		this.jwt = jwt;
	}



	public String getJwt() {
		return jwt;
	}
	
	
}
