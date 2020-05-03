package com.acepirit.ghumou.main.GhumouMain.DashboardController;

import java.io.IOException;
import java.util.*;

import com.acepirit.ghumou.main.GhumouMain.Entity.*;
import com.acepirit.ghumou.main.GhumouMain.Response.GlobalResponse;
import com.acepirit.ghumou.main.GhumouMain.Service.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin_v1")
public class TestHtmlCOntroller {
	@Autowired
	PackageService packageService;

	@Autowired
	UserService userService;

	@Autowired
	GlobalResponseService globalResponse;
	@Autowired
	FileUploadService fileUploadService;
	@Autowired
	OrderService orderService;

	@GetMapping("/addpackage")
	public String addpackage(Model newModel) {
		FormPackageGet packag = new FormPackageGet();
		newModel.addAttribute("new_package","new_package");
		newModel.addAttribute("packages",packag);
		return "addpackages";
	}

//	@PostMapping("/packages")
//	public ResponseEntity<?> postPackage(@RequestBody Packagess packages ) {
//
//		System.out.println("packegs"+packages);
//		return ResponseEntity.ok("Success");
//	}

	@GetMapping("/dashboards")
	public String showDashboard() {
		return "dashboard";

	}
	@GetMapping("/payment")
	public String showPayments() {
		return "payments";

	}
	@GetMapping("/booking")
	public String showBookings(Model theModel) {
		List<Orderpackage> allOrder = orderService.findAll();
		theModel.addAttribute("orders",allOrder);
		return "bookings";

	}

	@GetMapping("/user")
	public String showUser(Model theModel) {
		List<User> usrs = userService.findAll();
		theModel.addAttribute("users",usrs);
		return "users";

	}
	@GetMapping("/adminuser")
	public String showAdminUsers() {
		return "adminusers";
	}
	@GetMapping("/travelpartner")
	public String showTravelPartners() {
		return "travelpartners";
	}


	@GetMapping("/analytic")
	public String showAnalytics() {
		return "analytics";
	}
	@GetMapping("/package")
	public String listPackage(Model theModel) {
		List<Packagess> allpackages = packageService.findAll();
		theModel.addAttribute("packs",allpackages);
		return "packages";

	}


	@GetMapping("/packagedetail/{id}")
	public String packageDetails(@PathVariable int id,Model newModel) {
		Packagess packages = packageService.findById(id);
		newModel.addAttribute("update",true);
		newModel.addAttribute("packages",packages);

		return "addpackages";
	}

	@GetMapping("/package/delete/{id}")
	public String deletePackage(@PathVariable int id,Model newModel){
		System.out.println("id"+id);
		packageService.deleteById(id);
		return "redirect:../../package";
	}




	@PostMapping("/packageupload")
	public String upload(@ModelAttribute("packages") FormPackageGet packages, Model theNewModel) {
		Packagess pack = null;
		if(packages.getId()!=0){
			pack = packageService.findById(packages.getId());

			System.out.println("id "+packages.getId());
		}else{
			pack = new Packagess();

			System.out.println("id new package id ");
		}

		pack.setCreatedAt(new Date(System.currentTimeMillis()));
		pack.setDuration(packages.getDuration());
		pack.setExclusions(packages.getExclusions());

		pack.setInclusions(packages.getInclusions());
		pack.setItenarys(packages.getItenarys());
		pack.setPackageDescription(packages.getPackageDescription());
		pack.setOffers(packages.getOffers());
		pack.setViews(0);
		pack.setRating(0);
		pack.setSalePrice(packages.getSalePrice());
		pack.setRegularPrice(packages.getRegularPrice());
		pack.setPackageTitle(packages.getPackageTitle());
		pack.setPackageSellar(packages.getPackageSellar());
		pack.setPackageType(packages.getPackageType());

		String thumnailPath=null;
		if(packages.getThumnail()!=null) {
			try {
				thumnailPath = fileUploadService.storeFile(packages.getThumnail());
				pack.setThumnail(thumnailPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			return "redirect:addpackages";
		}
//checking list of images for null value
		List<Images> imagesPath = new ArrayList<>();
		if(packages.getImages()!=null) {

			for(MultipartFile file:packages.getImages()) {
				Images image = new Images();
				try {
					image.setImage(fileUploadService.storeFile(file));
				} catch (IOException e) {

					e.printStackTrace();
				}
				//imagesPath.add(fileUploadService.storeFile(file));
				imagesPath.add(image);
			}
			pack.setImages(imagesPath);
		}else {
			return "redirect:addpackages";
		}
		packageService.save(pack);

		return "redirect:package";
	}
//	@PostMapping("/testupload")
//	public ResponseEntity<?> uploadpackage(@RequestBody HtmlPackageGet packageget){
//		System.out.println("packages "+packageget.getPackagess());
//		return globalResponse.globalResponse("Succes",HttpStatus.OK.value());
//	}

	@GetMapping("/logins")
	public String login(Model theModel) {
		AuthenticateRequest authenticate = new AuthenticateRequest();
		theModel.addAttribute("login",authenticate);
		return "login";
	}

	@PostMapping("/authenticate")
	public String loginUser(@ModelAttribute("login") AuthenticateRequest login, Model theModel, RedirectAttributes redirectAttributes) throws Exception {
		boolean isLogin = userService.loginUser(login,"DASH");
		System.out.print(isLogin);
		if(isLogin){
			return "redirect:dashboards";
		}else{

			theModel.addAttribute("login",new AuthenticateRequest());
			redirectAttributes.addFlashAttribute("loginError","Please Enter a valid cerendtials");
			return "redirect:logins";
		}
	}

}
