package com.acepirit.ghumou.main.GhumouMain.Controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.acepirit.ghumou.main.GhumouMain.Entity.*;
import com.acepirit.ghumou.main.GhumouMain.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private GlobalResponseService globaResponse;

	@Autowired
	private GlobalVariableService globalVariableService;

	@Autowired
	private PaymentService paymentService;
	
	//posting orders
	@PostMapping("/orders")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> postOrder(@RequestBody OrderRequest orders){
		
		orderService.save(orders);

		orders.setId(orderService.lastInsertId());
		return globaResponse.responseClient(orders);
					
	}
	//patch request for OrderPackage data
	@PatchMapping("/updateorder/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLAR')")
	public ResponseEntity<?> patchOrder(@RequestBody Map<String,Object> update,@PathVariable int id){

		//getting order by id
		Orderpackage order = orderService.findById(id);
//		Set<com.acepirit.ghumou.main.GhumouMain.Entity.Role> role = userService.findByUserName(globalVariableService.getUsername()).getRoles();
//		String rolename = null;
//		for(Role rol:role){
//			rolename = rol.getName();
//
//		}
//		System.out.println("role"+rolename);
//		if(rolename.matches("ROLE_USER")){
//			if(!order.getUser().getUserName().equals(globalVariableService.getUsername())){
//				throw new RuntimeException("You don't have permission to access this resource.Please Access your specific resource");
//			}
//		}else{
//			String orgname = userService.findByUserName(globalVariableService.getUsername()).getUser_profile().getOrganizationName();
//			if(orgname.compareTo(order.getPackages().getPackageSellar())!=0){
//				System.out.println("Equal");
//				throw new RuntimeException("You don't have permission to access this resource.Please Access your specific resource");
//			}
//		}

		update.forEach((k,v)->{
			Field field = ReflectionUtils.findField(Orderpackage.class,k);
			field.setAccessible(true);
			ReflectionUtils.setField(field,order,v);
		});
		orderService.saveDirect(order);
		return globaResponse.globalResponse("Successfully Updated Order",HttpStatus.OK.value());
	}


//	
	//edititng orders
	@PutMapping("/orders")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLAR')")
	public ResponseEntity<?> putOrder(@RequestBody OrderRequest orders){
		Orderpackage order = orderService.findById(orders.getId());
//		Set<Role> role = userService.findByUserName(globalVariableService.getUsername()).getRoles();
//		String rol = role.stream().map(x->x.getName()).toString();
//
//		if(rol.matches("ROLE_USER")){
//			if(!order.getUser().getUserName().equals(globalVariableService.getUsername())){
//				throw new RuntimeException("You don't have permission to access this resource.Please Access your specific resource");
//			}
//		}else{
//			String orgname = userService.findByUserName(globalVariableService.getUsername()).getUser_profile().getOrganizationName();
//			if(orgname.compareTo(order.getPackages().getPackageSellar())!=0){
//				System.out.println("Equal");
//				throw new RuntimeException("You don't have permission to access this resource.Please Access your specific resource");
//			}
//		}
//
		orderService.update(orders);
		
		return globaResponse.responseClient(orders);
					
	}
	
	//getting orders based on user id
	@GetMapping(value="/orders",params="user_id")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLAR')")
	public ResponseEntity<?> getOrders(@RequestParam("user_id") int user_id){
		User user = userService.findById(user_id);
		if(!user.getUserName().equals(globalVariableService.getUsername())){
			throw new RuntimeException("You don't have permission to access this resource.Please Access your specific resource");
		}
		List<Orderpackage> allOrdres = orderService.findAllByUser(user);
		
		return globaResponse.listOrderResponse(allOrdres);
		
	}
	
	@DeleteMapping(value="/orders",params="order_id")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLAR')")
	public ResponseEntity<?> deleteOrder(@RequestParam("order_id") int order_id){

		Orderpackage order = orderService.findById(order_id);

		if(!order.getUser().getUserName().equals(globalVariableService.getUsername())){
			throw new RuntimeException("You don't have permission to access this resource.Please Access your specific resource");
		}

		orderService.deleteById(order_id);
		return globaResponse.globalResponse("Success",HttpStatus.OK.value());
	}

	//getting order with respect to seller name
	@GetMapping(value = "/orders",params="sellar")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLAR')")
	public ResponseEntity<?> getOrderBySellar(@RequestParam("sellar") String sellar){

		User user = userService.findByOrganizationName(sellar);
		if(!user.getUserName().equals(globalVariableService.getUsername())){
			throw new RuntimeException("You don't have permission to access this resource.Please Access your specific resource");
		}

		List<Orderpackage> orders = orderService.findAllByPackagessPackageSellar(sellar);
		return globaResponse.listOrderResponse(orders);
	}

	//getting order by id
	@GetMapping("/orders/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLAR') or hasRole('ROLE_USER')")
	public ResponseEntity<?> getOrderById(@PathVariable int id){

		Orderpackage orderpackage = orderService.findById(id);
//		Set<Role> role = userService.findByUserName(globalVariableService.getUsername()).getRoles();
//		String rolename = null;
//		for(Role rol:role){
//			rolename = rol.getName();
//
//		}
//		System.out.println("role"+rolename);
//		if(rolename.matches("ROLE_USER")){
//			if(!orderpackage.getUser().getUserName().equals(globalVariableService.getUsername())){
//				throw new RuntimeException("You don't have permission to access this resource.Please Access your specific resource");
//			}
//		}else{
//			String orgname = userService.findByUserName(globalVariableService.getUsername()).getUser_profile().getOrganizationName();
//			System.out.println("compare "+orgname.compareTo(orderpackage.getPackages().getPackageSellar()));
////			if(orgname.compareTo(orderpackage.getPackages().getPackageSellar())!=0){
////				System.out.println("Equal");
////				throw new RuntimeException("You don't have permission to access this resource.Please Access your specific resource");
////			}
//			if(!orgname.equals(orderpackage.getPackages().getPackageSellar())){
//				System.out.println("not Equal");
//				throw new RuntimeException("You don't have permission to access this resource.Please Access your specific resource");
//			}
//		}
		List<Payment> paymentsList = paymentService.findByOrder(orderpackage);


		return globaResponse.orderWithPaymet(orderpackage,paymentsList);
	}


}
