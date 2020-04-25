package com.acepirit.ghumou.main.GhumouMain.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.acepirit.ghumou.main.GhumouMain.Entity.AuthenticateRequest;

public interface AuthenticateService extends UserDetailsService {
	public AuthenticateRequest findByUserName(String username);
}
