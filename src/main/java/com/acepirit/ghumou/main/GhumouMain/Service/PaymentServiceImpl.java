package com.acepirit.ghumou.main.GhumouMain.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.Orderpackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.Payment;
import com.acepirit.ghumou.main.GhumouMain.Entity.PaymentRequest;
import com.acepirit.ghumou.main.GhumouMain.Repository.PaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderService orderService;

	@Override
	public void save(String idx,PaymentRequest paymentRequest) {
		Payment payment = new Payment();
		payment.setId(paymentRequest.getId());
		payment.setPaymentType(paymentRequest.getPaymentType());
		payment.setTotalAmount(paymentRequest.getTotalAmount());
		payment.setVatAmount(paymentRequest.getVatAmount());
		payment.setDiscountAmount(paymentRequest.getDiscountAmount());
		payment.setOrder(orderService.findById(paymentRequest.getOrder_id()));
		payment.setInvoiceNo(123);
		payment.setInvoice("");
		payment.setTranscationIdx(idx);
		payment.setPaymentAmount(paymentRequest.getPaymentAmount());
		paymentRepository.save(payment);
		
	}

	@Override
	public List<Payment> findByOrder(Orderpackage order) {
		List<Payment> result = paymentRepository.findByOrder(order);
		if(result==null) {
			throw new RuntimeException("Payment Not Found");
		}
		return result;
	}

	@Override
	public Payment findById(int id) {
		Optional<Payment> result = paymentRepository.findById(id);
		Payment payment = null;
		if(result.isPresent()){
			payment = result.get();
			return payment;
		}else{
			throw new RuntimeException("Payment with id "+ id+ "Not found");
		}


	}

	@Override
	public List<Payment> findByUserid(int userid) {
		List<Payment> allPayment = paymentRepository.findByUserid(userid);

		return allPayment;
	}

	@Override
	public List<Payment> findBySellar(String sellarname) {
		List<Payment> allSellar = paymentRepository.findBySellar(sellarname);
		return allSellar;
	}

}
