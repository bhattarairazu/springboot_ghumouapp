package com.acepirit.ghumou.main.GhumouMain.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acepirit.ghumou.main.GhumouMain.Entity.OrderRequest;
import com.acepirit.ghumou.main.GhumouMain.Entity.Orderpackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.Packagess;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;
import com.acepirit.ghumou.main.GhumouMain.Service.GlobalResponseService;
import com.acepirit.ghumou.main.GhumouMain.Service.OrderService;
import com.acepirit.ghumou.main.GhumouMain.Service.PackageService;
import com.acepirit.ghumou.main.GhumouMain.Service.UserService;

@RestController
@RequestMapping("/api/v2/")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private GlobalResponseService globaResponse;
	
	//posting orders
	@PostMapping("/orders")
	public ResponseEntity<?> postOrder(@RequestBody OrderRequest orders){
		
		orderService.save(orders);
		
		return globaResponse.globalResponse("Success", HttpStatus.CREATED.value());
					
	}
	

//	
	//edititng orders
	@PutMapping("/orders")
	public ResponseEntity<?> putOrder(@RequestBody OrderRequest orders){
		
		orderService.update(orders);;
		
		return globaResponse.responseClient(orders);
					
	}
	
	
	@GetMapping(value="/orders",params="user_id")
	public ResponseEntity<?> getOrders(@RequestParam("user_id") int user_id){
		User user = userService.findById(user_id);
		
		List<Orderpackage> allOrdres = orderService.findAllByUser(user);
		
		return globaResponse.listOrderResponse(allOrdres);
		
	}
	
	@DeleteMapping(value="/orders",params="order_id")
	public ResponseEntity<?> deleteOrder(@RequestParam("order_id") int order_id){
		orderService.deleteById(order_id);
		return globaResponse.globalResponse("Success",HttpStatus.OK.value());
	}

	
}
