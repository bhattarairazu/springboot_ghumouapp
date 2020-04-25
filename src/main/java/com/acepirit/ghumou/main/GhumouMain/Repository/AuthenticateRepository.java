package com.acepirit.ghumou.main.GhumouMain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acepirit.ghumou.main.GhumouMain.Entity.AuthenticateRequest;

public interface AuthenticateRepository extends JpaRepository<AuthenticateRequest, Integer>{
	
	public AuthenticateRequest findByUserName(String username);

}
