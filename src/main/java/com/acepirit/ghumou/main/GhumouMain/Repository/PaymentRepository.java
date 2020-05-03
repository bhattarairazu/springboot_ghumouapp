package com.acepirit.ghumou.main.GhumouMain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acepirit.ghumou.main.GhumouMain.Entity.Orderpackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
	public Payment findByOrder(Orderpackage order);
}
