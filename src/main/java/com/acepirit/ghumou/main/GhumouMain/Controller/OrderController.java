package com.acepirit.ghumou.main.GhumouMain.Controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

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
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> postOrder(@RequestBody OrderRequest orders){
		
		orderService.save(orders);
		
		return globaResponse.globalResponse("Success", HttpStatus.CREATED.value());
					
	}
	//patch request for OrderPackage data
	@PatchMapping("/updateorder/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLAR')")
	public ResponseEntity<?> patchOrder(@RequestBody Map<String,Object> update,@PathVariable int id){

		//getting order by id
		Orderpackage orderpackage = orderService.findById(id);
		update.forEach((k,v)->{
			Field field = ReflectionUtils.findField(Orderpackage.class,k);
			field.setAccessible(true);
			ReflectionUtils.setField(field,orderpackage,v);
		});
		orderService.saveDirect(orderpackage);
		return globaResponse.globalResponse("Successfully Updated Order",HttpStatus.OK.value());
	}


//	
	//edititng orders
	@PutMapping("/orders")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLAR')")
	public ResponseEntity<?> putOrder(@RequestBody OrderRequest orders){
		
		orderService.update(orders);;
		
		return globaResponse.responseClient(orders);
					
	}
	
	
	@GetMapping(value="/orders",params="user_id")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLAR')")
	public ResponseEntity<?> getOrders(@RequestParam("user_id") int user_id){
		User user = userService.findById(user_id);
		
		List<Orderpackage> allOrdres = orderService.findAllByUser(user);
		
		return globaResponse.listOrderResponse(allOrdres);
		
	}
	
	@DeleteMapping(value="/orders",params="order_id")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLAR')")
	public ResponseEntity<?> deleteOrder(@RequestParam("order_id") int order_id){
		orderService.deleteById(order_id);
		return globaResponse.globalResponse("Success",HttpStatus.OK.value());
	}

	//getting order with respect to seller name
	@GetMapping(value = "/orders",params="sellar")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLAR')")
	public ResponseEntity<?> getOrderBySellar(@RequestParam("sellar") String sellar){
		List<Orderpackage> orders = orderService.findAllByPackagessPackageSellar(sellar);
		return globaResponse.listOrderResponse(orders);
	}


}
