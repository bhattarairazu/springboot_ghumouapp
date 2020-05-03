package com.acepirit.ghumou.main.GhumouMain.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.Orderpackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.Payment;
import com.acepirit.ghumou.main.GhumouMain.Entity.PaymentRequest;

public interface PaymentService {
	
	public void save(PaymentRequest paymentRequest);
	
	public Payment findByOrder(Orderpackage order);

}
