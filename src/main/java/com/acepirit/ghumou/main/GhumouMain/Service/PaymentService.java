package com.acepirit.ghumou.main.GhumouMain.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.Orderpackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.Payment;
import com.acepirit.ghumou.main.GhumouMain.Entity.PaymentRequest;

import java.util.List;

public interface PaymentService {
	
	public void save(String idx,PaymentRequest paymentRequest);
	
	public List<Payment> findByOrder(Orderpackage order);

	Payment findById(int id);

	List<Payment> findByUserid(int userid);

	List<Payment> findBySellar(String sellarname);

}
