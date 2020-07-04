package com.acepirit.ghumou.main.GhumouMain.DashboardController;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.acepirit.ghumou.main.GhumouMain.Entity.*;
import com.acepirit.ghumou.main.GhumouMain.Repository.RoleRepository;
import com.acepirit.ghumou.main.GhumouMain.Response.GlobalResponse;
import com.acepirit.ghumou.main.GhumouMain.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
	RoleRepository roleRepository;

	@Autowired
	UserService userService;

	@Autowired
	GlobalResponseService globalResponse;
	@Autowired
	FileUploadService fileUploadService;
	@Autowired
	OrderService orderService;
	@Autowired
	private DynamicPageService dynamicPageService;
	@Autowired
	OurServicesService ourServices;

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
		List<User> usrs = userService.findAllByRole("ROLE_USER");
		theModel.addAttribute("users",usrs);
		return "users";

	}
	//userprofile
	@GetMapping("/user/profile/{id}")
	public String showUserProfile(@PathVariable int id, Model theModel){
		User singleuser = userService.findById(id);
		Set<Role> roles = singleuser.getRoles();

		for(Role role:roles){
			if(role.getName().matches("ROLE_USER")){
				List<Orderpackage> orderList = orderService.findAllByUser(singleuser);

				theModel.addAttribute("user",singleuser);

				theModel.addAttribute("u","u");
				theModel.addAttribute("orders",orderList);

			}else if(role.getName().matches("ROLE_SELLAR")){
				theModel.addAttribute("user",singleuser);
				theModel.addAttribute("sellar","sellar");
			}
		}

		return "profile";
	}



	@GetMapping("/adminuser")
	public String showAdminUsers(Model theModel) {
		List<User> userAdmin = userService.findAllByRole("ROLE_ADMIN");


		//merging two list
		//		List<User> mergeList = Stream.of(userAdmin, userMod)
//				.flatMap(x -> x.stream())
//				.collect(Collectors.toList());

		theModel.addAttribute("users",userAdmin);

		return "adminusers";

	}
	//change usertype
	@GetMapping("/changeuser/{id}/{type}")
	public String changeUser(@PathVariable int id,@PathVariable String type){
		System.out.println(type);
		User user = userService.findById(id);
		System.out.println("enabl"+user.isEnabled());
		if(type.matches("admin")){
			System.out.println("inside admin");
			user.setEnabled(true);
		}else{

			System.out.println("inside mod");
			user.setEnabled(false);
		}

		System.out.println("enabl"+user.isEnabled());
		userService.save(user);

		return "redirect:../../adminuser";
	}

	//adding admin user
	@PostMapping("/adminadd")
	public String addAdmin(@RequestParam("username") String username,@RequestParam("password") String password,
						   @RequestParam("role") String role,Model theModel){
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		if(role.matches("ROLE_ADMIN")){
			user.setEnabled(true);
		}
		Set<Role> roles = new HashSet<>();
		Role rol = roleRepository.findRoleByName("ROLE_ADMIN");
		roles.add(rol);
		System.out.println("user"+username+"password"+password+"roe"+role);
		user.setRoles(roles);
		userService.save(user);
		return "redirect:adminuser";
	}




	@GetMapping("/travelpartner")
	public String showTravelPartners(Model theModel) {
		List<User> userSellar = userService.findAllByRole("ROLE_SELLAR");


		//merging two list
		//		List<User> mergeList = Stream.of(userAdmin, userMod)
//				.flatMap(x -> x.stream())
//				.collect(Collectors.toList());

		theModel.addAttribute("sellars",userSellar);
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
		Exclusions exc = null;
		Inclusions incl = null;
		Itenarys itenar = null;

		if(packages.getId()!=0){
			pack = packageService.findById(packages.getId());

			System.out.println("id "+packages.getId());
		}else{
			pack = new Packagess();
			exc = new Exclusions();
			incl = new Inclusions();
			itenar = new Itenarys();

			System.out.println("id new package id ");
		}

		pack.setCreatedAt(new Date(System.currentTimeMillis()));
		pack.setDuration(packages.getDuration());
		//settting exclusion first
		//exc.setExclusion(packages.getExclusions().getExclusion());
		pack.setExclusions(packages.getExclusions());

		//incl.setInclusion(packages.getInclusions().getInclusion());
		pack.setInclusions(packages.getInclusions());

		//itenar.setItenary(packages.getItenarys().getItenary());
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
		pack.setIcons(packages.getIcons());
		System.out.println("icons values "+packages.getIcons());
		String thumnailPath=null;
		if(packages.getId()==0 && !packages.getThumnail().isEmpty()) {
			try {
				thumnailPath = fileUploadService.storeFile(packages.getThumnail());
				pack.setThumnail(thumnailPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(packages.getId()==0 && packages.getThumnail().isEmpty()){
			return "redirect:addpackages";
		}else if(packages.getId()!=0 && !packages.getThumnail().isEmpty()){
			try {
				thumnailPath = fileUploadService.storeFile(packages.getThumnail());
				pack.setThumnail(thumnailPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//checking list of images for null value
		List<Images> imagesPath = new ArrayList<>();
		if((packages.getImages().length>0 && packages.getId()==0) || (packages.getImages().length>0 && packages.getId()!=0)) {

			for(MultipartFile file:packages.getImages()) {

				if (!file.isEmpty()) {
					Images image = new Images();
					try {
						image.setImage(fileUploadService.storeFile(file));
					} catch (IOException e) {

						e.printStackTrace();
					}
					//imagesPath.add(fileUploadService.storeFile(file));
					imagesPath.add(image);
				}
			}
			if(imagesPath.size()>0) {
				pack.setImages(imagesPath);
			}

		}else if(packages.getImages().length==0 && packages.getId()==0){
			return "redirect:addpackages";
		}
		System.out.println("Packages "+pack);
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

	//services
	@GetMapping("/ourservices")
	public String ourService(Model theModel){
		//for adding new services
		FormGetOurServices ourservices = new FormGetOurServices();
		//for listing all services
		List<OurServices> allServices = ourServices.findAll();
		theModel.addAttribute("services",allServices);
		theModel.addAttribute("ourservices",ourservices);
		return "services";
	}


	@PostMapping("/addservices")
	public String addServices(@ModelAttribute("ourservices") FormGetOurServices ourservices,Model theModel){
		System.out.println(ourservices.getFile());
		String imagepath = null;
		if(!ourservices.getFile().isEmpty()){
			try {
				imagepath = fileUploadService.storeFile(ourservices.getFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		OurServices service = new OurServices();

		service.setImage(imagepath);
		service.setName(ourservices.getName());
		service.setDescription(ourservices.getDescription());
		service.setType(ourservices.getType());
		ourServices.save(service);
		System.out.println("iamge "+ourservices.getFile()+" des"+ourservices.getDescription());

		return "redirect:ourservices";
	}

	@GetMapping("/ourservices/{id}")
	public String deleteService(@PathVariable int id){
		ourServices.deleteById(id);
		return "redirect:../ourservices";
	}
	@GetMapping("/user/profile/{id}/{type}")
	public String changeSellar(@PathVariable int id,@PathVariable String type){
		User user = userService.findById(id);
		user.getUser_profile().setStatus(type);
		userService.save(user);
		return "redirect:../../../travelpartner";
	}
	/**
	*Dynamic pages controller section
	*/
	@GetMapping("/dynamicpages")
	public String listPages(Model theModel){
		List<DynamicPages> allPages = dynamicPageService.findAll();
		theModel.addAttribute("dynamicpages",allPages);
		return "dynamicpages";
	}

	@GetMapping("/addpages")
	public String addPages(Model theModel){
		DynamicPages newPage = new DynamicPages();
		theModel.addAttribute("dynamicpage",newPage);
		return "addpages";
	}

	@PostMapping("/uploadpages")
	public String uploadPages(@ModelAttribute("dynamicpage") DynamicPages dynamicpage,Model theModel){
		if(dynamicpage.getId()>0){
			System.out.println("this is edit button");
		}else{
			System.out.println("this is new button");

		}
		dynamicpage.setCreatedAt(new Date(System.currentTimeMillis()));
		dynamicPageService.save(dynamicpage);
		return "redirect:dynamicpages";
	}

	@GetMapping("/editpage/{id}")
	public String ediPage(@PathVariable int id,Model theModel){
		DynamicPages page = dynamicPageService.findById(id);
		theModel.addAttribute("dynamicpage",page);
		return "addpages";

	}

	@GetMapping("/deletepage/{id}")
	public String deletePage(@PathVariable int id){
		dynamicPageService.deleteById(id);
		return "redirect:../dynamicpages";
	}





}
