package com.acepirit.ghumou.main.GhumouMain.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.acepirit.ghumou.main.GhumouMain.Service.FavouriteService;
import com.acepirit.ghumou.main.GhumouMain.Service.GlobalResponseService;
import com.acepirit.ghumou.main.GhumouMain.Service.PackageService;
import com.acepirit.ghumou.main.GhumouMain.Service.UserService;

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
	
	@PostMapping("/favourite")
	public ResponseEntity<?> postFavouriet(@RequestBody GettingFavouriteData favourite){
		System.out.println("favoureit is "+favourite.getPackageId());
		
		Packagess packages = packageService.findById(favourite.getPackageId());
		User users = userService.findById(favourite.getUserId());
		
		AddToFavourites addtofavoure = new AddToFavourites(users,packages);
		favouriteService.save(addtofavoure);
		
		return globalresponse.responseClient(addtofavoure);
	}
	
	@GetMapping(value="/favourite",params="user_id")
	public ResponseEntity<?> allFavorutei(@RequestParam("user_id") int user_id){
		User user = userService.findById(user_id);
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
	public ResponseEntity<?> removeFromFavoureit(@RequestParam("favourite_id") int favourite_id){
		
		favouriteService.deleteById(favourite_id);
		return globalresponse.globalResponse("Success",HttpStatus.OK.value());
	}
	


}