package com.acepirit.ghumou.main.GhumouMain.Entity;

public class PaymentRequest {
	private int id;
	
	private long totalAmount;
	
	private long vatAmount;
	
	private long discountAmount;
	
	private int order_id;
	
	private long invoiceNo;
	
	private String paymentType;
	
	private String invoice;
	
	public PaymentRequest() {}

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

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
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

	public long getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(long discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	
}
