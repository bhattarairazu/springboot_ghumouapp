package com.acepirit.ghumou.main.GhumouMain.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.Orderpackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.Payment;
import com.acepirit.ghumou.main.GhumouMain.Entity.PaymentRequest;
import com.acepirit.ghumou.main.GhumouMain.Repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public void save(PaymentRequest paymentRequest) {
		Payment payment = new Payment();
		payment.setId(paymentRequest.getId());
		payment.setPaymentType(paymentRequest.getPaymentType());
		payment.setTotalAmount(paymentRequest.getTotalAmount());
		payment.setVatAmount(paymentRequest.getVatAmount());
		payment.setDiscountAmount(paymentRequest.getDiscountAmount());
		payment.setInvoiceNo(123);
		payment.setInvoice("");
		paymentRepository.save(payment);
		
	}

	@Override
	public Payment findByOrder(Orderpackage order) {
		Payment result = paymentRepository.findByOrder(order);
		if(result==null) {
			throw new RuntimeException("Payment Not Found");
		}
		return result;
	}

}
