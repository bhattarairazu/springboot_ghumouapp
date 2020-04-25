package com.acepirit.ghumou.main.GhumouMain.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acepirit.ghumou.main.GhumouMain.Entity.Orderpackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;

public interface OrderRepository extends JpaRepository<Orderpackage, Integer> {
	
	public  List<Orderpackage> findAllByUser(User user);
	
}
