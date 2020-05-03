package com.acepirit.ghumou.main.GhumouMain.Service;

import java.util.List;

import com.acepirit.ghumou.main.GhumouMain.Entity.OrderRequest;
import com.acepirit.ghumou.main.GhumouMain.Entity.Orderpackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;

public interface OrderService {
	
	public void save(OrderRequest order);
	
	public void deleteById(int id);
	
	public  List<Orderpackage> findAllByUser(User user);
	
	public void update(OrderRequest order);

	Orderpackage findById(int id);

	void saveDirect(Orderpackage orderpackage);
	
	List<Orderpackage> findAll();
	
	

}
