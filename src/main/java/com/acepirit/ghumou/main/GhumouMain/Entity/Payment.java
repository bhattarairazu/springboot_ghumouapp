package com.acepirit.ghumou.main.GhumouMain.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="total_amount")
	private long totalAmount;
	
	@Column(name="vat_amount")
	private long vatAmount;
	
	@Column(name="discount_amount")
	private long discountAmount;
	
	@OneToOne
	@JoinColumn(name="order_id",referencedColumnName = "id")
	private Orderpackage order;
	
	@Column(name="invoice_no")
	private long invoiceNo;
	
	@Column(name="payment_type")
	private String paymentType;
	
	@Column(name="invoice")
	private String invoice;
	
	public Payment() {};

	

	public Payment(long totalAmount, long vatAmount, long discountAmount, Orderpackage order, long invoiceNo,
			String paymentType, String invoice) {
		this.totalAmount = totalAmount;
		this.vatAmount = vatAmount;
		this.discountAmount = discountAmount;
		this.order = order;
		this.invoiceNo = invoiceNo;
		this.paymentType = paymentType;
		this.invoice = invoice;
	}



	public long getDiscountAmount() {
		return discountAmount;
	}



	public void setDiscountAmount(long discountAmount) {
		this.discountAmount = discountAmount;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public long getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(long vatAmount) {
		this.vatAmount = vatAmount;
	}

	public Orderpackage getOrder() {
		return order;
	}

	public void setOrder(Orderpackage order) {
		this.order = order;
	}

	public long getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(long invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	
	
	
	
	

}
