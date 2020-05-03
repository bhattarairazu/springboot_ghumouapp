package com.acepirit.ghumou.main.GhumouMain.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.acepirit.ghumou.main.GhumouMain.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);
	
	public User findByUserName(String username);
	
	//like query for serach and filter data
	@Query("SELECT u FROM User u WHERE u.firstName LIKE %:keyword% OR u.lastName LIKE %:keyword% OR u.userName LIKE %:keyword%")
	public List<User> findUsersByKeyword(@Param("keyword") String keyword);



}
