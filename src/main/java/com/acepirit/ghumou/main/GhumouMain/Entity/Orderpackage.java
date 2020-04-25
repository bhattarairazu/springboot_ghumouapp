package com.acepirit.ghumou.main.GhumouMain.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

@Entity
@Table(name="order_package")
public class Orderpackage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="booking_amount")
	private int bookingAmount;
	
	@Column(name="remaining_amount")
	private int remainingAmount;
	
	@Column(name="total_paid_status")
	private boolean totalPaidStatus;
	
	@Column(name="order_date")
	private Date orderDate;
	
	@Column(name="departure_date")
	private Date departureDate;
	
	@Column(name="order_from")
	private String orderFrom;
	
	@Column(name="total_people")
	private int totalPeople;
	
	@Column(name="order_notes")
	private String orderNotes;
	
	@Column(name="order_status")
	private String orderStatus;
	
	@Column(name="order_message")
	private String orderMessage;
	
//	//@JsonIgnore
//	@Column(insertable = false,updatable = false)
//	private int userid;
////	
//	//@JsonIgnore
//	@Column(insertable = false,updatable = false)
//	private int packageid;
//	
	@OneToOne
	@JoinColumn(name="user_id",referencedColumnName = "id")
	private User user;
	//user for receiving data
	
	@OneToOne
	@JoinColumn(name="package_id",referencedColumnName = "package_id")
	private Packagess packages;
	//only for recevigind data
	
	public Orderpackage() {}
	public Orderpackage(int bookingAmount, int remainingAmount, boolean totalPaidStatus, Date orderDate, Date departureDate,
			String orderFrom, int totalPeople, String orderNotes, String orderStatus, String orderMessage, User user,
			Packagess packages) {
		this.bookingAmount = bookingAmount;
		this.remainingAmount = remainingAmount;
		this.totalPaidStatus = totalPaidStatus;
		this.orderDate = orderDate;
		this.departureDate = departureDate;
		this.orderFrom = orderFrom;
		this.totalPeople = totalPeople;
		this.orderNotes = orderNotes;
		this.orderStatus = orderStatus;
		this.orderMessage = orderMessage;
		this.user = user;
		this.packages = packages;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookingAmount() {
		return bookingAmount;
	}

	public void setBookingAmount(int bookingAmount) {
		this.bookingAmount = bookingAmount;
	}

	public int getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(int remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public boolean isTotalPaidStatus() {
		return totalPaidStatus;
	}

	public void setTotalPaidStatus(boolean totalPaidStatus) {
		this.totalPaidStatus = totalPaidStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public String getOrderFrom() {
		return orderFrom;
	}

	public void setOrderFrom(String orderFrom) {
		this.orderFrom = orderFrom;
	}

	public int getTotalPeople() {
		return totalPeople;
	}

	public void setTotalPeople(int totalPeople) {
		this.totalPeople = totalPeople;
	}

	public String getOrderNotes() {
		return orderNotes;
	}

	public void setOrderNotes(String orderNotes) {
		this.orderNotes = orderNotes;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderMessage() {
		return orderMessage;
	}

	public void setOrderMessage(String orderMessage) {
		this.orderMessage = orderMessage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Packagess getPackages() {
		return packages;
	}

	public void setPackages(Packagess packages) {
		this.packages = packages;
	}
//	public int getUserid() {
//		return userid;
//	}
//	public void setUserid(int userid) {
//		this.userid = userid;
//	}
//	public int getPackageid() {
//		return packageid;
//	}
//	public void setPackageid(int packageid) {
//		this.packageid = packageid;
//	}

	
	
	

}
