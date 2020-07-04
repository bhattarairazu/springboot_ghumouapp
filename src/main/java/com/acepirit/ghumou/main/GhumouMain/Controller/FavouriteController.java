package com.acepirit.ghumou.main.GhumouMain.Controller;

import java.util.List;

import com.acepirit.ghumou.main.GhumouMain.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acepirit.ghumou.main.GhumouMain.Entity.AddToFavourites;
import com.acepirit.ghumou.main.GhumouMain.Entity.GettingFavouriteData;
import com.acepirit.ghumou.main.GhumouMain.Entity.Packagess;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;

@RestController
@RequestMapping("api/v2/")
public class FavouriteController {
	
	@Autowired
	private FavouriteService favouriteService;
	
	@Autowired
	private PackageService packageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GlobalResponseService globalresponse;

	@Autowired
	private GlobalVariableService globalVariableService;
	
	@PostMapping("/favourite")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> postFavouriet(@RequestBody GettingFavouriteData favourite){
		System.out.println("favoureit is "+favourite.getPackageId());
		
		Packagess packages = packageService.findById(favourite.getPackageId());
		User users = userService.findById(favourite.getUserId());
		
		AddToFavourites addtofavoure = new AddToFavourites(users,packages);
		favouriteService.save(addtofavoure);
		
		return globalresponse.responseClient(addtofavoure);
	}
	
	@GetMapping(value="/favourite",params="user_id")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> allFavorutei(@RequestParam("user_id") int user_id){
		User user = userService.findById(user_id);
		if(!user.getUserName().equals(globalVariableService.getUsername())){
			throw new RuntimeException("You don't have permission to access this resource.Please access your specific resource");
		}

		System.out.println("user"+user);
		List<AddToFavourites> allFavoruiet = favouriteService.findAllByUserId(user);
		return globalresponse.listFavouriteResponse(allFavoruiet);
		
	}
	
//	@DeleteMapping("/favourite")
//	public ResponseEntity<?> removeFromFavoureit(@RequestBody GettingFavouriteData favourietdata){
//		int user_id = favourietdata.getUserId();
//		int package_id = favourietdata.getPackageId();
//		User user = userService.findById(user_id);
//		Packagess pack = packageService.findById(package_id);
//		AddToFavourites fav = favouriteService.findByUserIdAndPackageId(user, pack);
//		favouriteService.deleteById(fav.getId());
//		return globalresponse.globalResponse("Success",HttpStatus.OK.value());
//	}
	
	@DeleteMapping(value="/favourite",params="favourite_id")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> removeFromFavoureit(@RequestParam("favourite_id") int favourite_id){

//		User user = userService
//		if(!user.getUserName().equals(globalVariableService.getUsername())){
//			throw new RuntimeException("You don't have permission to access this resource.Please access your specific resource");
//		}

		favouriteService.deleteById(favourite_id);
		return globalresponse.globalResponse("Success",HttpStatus.OK.value());
	}
	


}
