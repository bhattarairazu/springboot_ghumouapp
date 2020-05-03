package com.acepirit.ghumou.main.GhumouMain.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.AuthenticateRequest;
import com.acepirit.ghumou.main.GhumouMain.Repository.AuthenticateRepository;

@Service
public class AuthenticateRequestImpl implements AuthenticateService {
	//for jwt authentication only
	@Autowired
	private AuthenticateRepository authenRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthenticateRequest user = authenRepository.findByUserName(username);
		return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
	}

	@Override
	public AuthenticateRequest findByUserName(String username) {

		return authenRepository.findByUserName(username);
	}

}
