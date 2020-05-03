package com.acepirit.ghumou.main.GhumouMain.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.OrderRequest;
import com.acepirit.ghumou.main.GhumouMain.Entity.Orderpackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;
import com.acepirit.ghumou.main.GhumouMain.Repository.OrderRepository;
import com.acepirit.ghumou.main.GhumouMain.Repository.UserRepository;

import javax.swing.text.html.Option;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private PackageService packageService;

	@Override
	public void deleteById(int id) {
		orderRepository.deleteById(id);
		
	}

	@Override
	public List<Orderpackage> findAllByUser(User user) {
		List<Orderpackage> allOrder = orderRepository.findAllByUser(user);
		return allOrder;
	}

	@Override
	public void save(OrderRequest order) {
		Orderpackage orderpackage = new Orderpackage();
		orderpackage.setBookingAmount(order.getBookingAmount());
		orderpackage.setRemainingAmount(order.getRemainingAmount());
		orderpackage.setTotalPaidStatus(order.isTotalPaidStatus());
		orderpackage.setOrderDate(order.getOrderDate());
		orderpackage.setDepartureDate(order.getDepartureDate());
		orderpackage.setOrderFrom(order.getOrderFrom());
		orderpackage.setTotalPeople(order.getTotalPeople());
		orderpackage.setOrderNotes(order.getOrderNotes());
		orderpackage.setOrderMessage(order.getOrderMessage());
		orderpackage.setOrderStatus(order.getOrderStatus());
		orderpackage.setUser(userService.findById(order.getUserId()));
		orderpackage.setPackages(packageService.findById(order.getPackageId()));
		orderpackage.setName(order.getName());
		orderpackage.setAddress(order.getAddress());
		orderpackage.setEmail(order.getEmail());
		orderpackage.setPhoneNo(order.getPhoneNo());
		orderRepository.save(orderpackage);
		
	}
	//for updating the order
	public void update(OrderRequest order) {
		Optional<Orderpackage> result = orderRepository.findById(order.getId());
		Orderpackage orderpackage = null;
		if(result.isPresent()) {
			orderpackage = result.get();
			orderpackage.setBookingAmount(order.getBookingAmount());
			orderpackage.setRemainingAmount(order.getRemainingAmount());
			orderpackage.setTotalPaidStatus(order.isTotalPaidStatus());
			orderpackage.setOrderDate(order.getOrderDate());
			orderpackage.setDepartureDate(order.getDepartureDate());
			orderpackage.setOrderFrom(order.getOrderFrom());
			orderpackage.setTotalPeople(order.getTotalPeople());
			orderpackage.setOrderNotes(order.getOrderNotes());
			orderpackage.setOrderMessage(order.getOrderMessage());
			orderpackage.setOrderStatus(order.getOrderStatus());
			orderpackage.setUser(userService.findById(order.getUserId()));
			orderpackage.setPackages(packageService.findById(order.getPackageId()));
			orderpackage.setName(order.getName());
			orderpackage.setAddress(order.getAddress());
			orderpackage.setEmail(order.getEmail());
			orderpackage.setPhoneNo(order.getPhoneNo());
			orderRepository.save(orderpackage);
			
		}else {
			throw new RuntimeException("Order with id "+order.getId()+" Not found");
		}
	}

	@Override
	public Orderpackage findById(int id) {
		Optional<Orderpackage> result = orderRepository.findById(id);
		Orderpackage orderpackage = null;
		if(result.isPresent())
		{
			orderpackage = result.get();

			return orderpackage;

		}else{
			throw new RuntimeException("Order with id "+id+" Not found");
		}
	}

	@Override
	public void saveDirect(Orderpackage orderpackage) {
		orderRepository.save(orderpackage);

	}

	@Override
	public List<Orderpackage> findAll() {
		
		return orderRepository.findAll();
	}


}
