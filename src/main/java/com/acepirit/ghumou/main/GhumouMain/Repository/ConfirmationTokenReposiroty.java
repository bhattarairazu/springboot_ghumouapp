package com.acepirit.ghumou.main.GhumouMain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acepirit.ghumou.main.GhumouMain.Entity.ConfirmationToken;

public interface ConfirmationTokenReposiroty  extends JpaRepository<ConfirmationToken, Long>{
	public ConfirmationToken findByConfirmationToken(String confirmationtoken);
	void deleteByConfirmationToken(String confirmationtoken);
}
