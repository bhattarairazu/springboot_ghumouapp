package com.acepirit.ghumou.main.GhumouMain.Controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.acepirit.ghumou.main.GhumouMain.Entity.Images;
import com.acepirit.ghumou.main.GhumouMain.Entity.Packagess;
import com.acepirit.ghumou.main.GhumouMain.Service.FileUploadService;
import com.acepirit.ghumou.main.GhumouMain.Service.GlobalResponseService;
import com.acepirit.ghumou.main.GhumouMain.Service.PackageService;
import com.google.common.net.HttpHeaders;

@RestController
@RequestMapping("/api/v2/")
public class PackageController {
	
	@Autowired
	private FileUploadService fileUploadService;
	

	@Autowired
	private PackageService packageService;
	
	@Autowired
	private GlobalResponseService globalResponse;
	
	//inserting package to the database
	@PostMapping("/packages")
	public ResponseEntity<?> postPackage(@RequestPart("thumnail") MultipartFile thumnail,
				@RequestPart("images") MultipartFile[] images,
				@RequestPart("packages") Packagess packages) {
		String thumnailPath=null;
		if(thumnail!=null) {
			try {
				thumnailPath = fileUploadService.storeFile(thumnail);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			throw new RuntimeException("Thumnail Image should not be empty");
		}
		//checking list of images for null value
		List<Images> imagesPath = new ArrayList<>();
		if(images!=null) {
			
			for(MultipartFile file:images) {
				Images image = new Images();
				try {
					image.setImage(fileUploadService.storeFile(file));
				} catch (IOException e) {
				
					e.printStackTrace();
				}
				//imagesPath.add(fileUploadService.storeFile(file));
				imagesPath.add(image);
			}
		}else {
			throw new RuntimeException("Package Should Contain Atleast One Image ");
		}
		//settingn respecting images to packages 
		packages.setCreatedAt(new Date(System.currentTimeMillis()));
		packages.setImages(imagesPath);
		packages.setThumnail(thumnailPath);
		packageService.save(packages);
		return globalResponse.responseClient(packages);	
	}
	//getting all packages list
	@GetMapping("/packages")
	public ResponseEntity<?> allPakcage(){
		List<Packagess> allpakcage = packageService.findAll();
		return globalResponse.listPackageResponse(allpakcage); 
	}
	
	
	//For Download files 
		@GetMapping("/ghumoufiles/{fileName:.*}")
		public ResponseEntity<PathResource> downloadFile(@PathVariable String fileName){
			
			String uploadDirectoryfilesystem ="/home/acepirit/ghumoufiles/";
			Path fileNamePath = Paths.get(uploadDirectoryfilesystem,fileName);
			System.out.println("filename "+fileName);
			return ResponseEntity.ok()
					//.contentType(MediaType.parseMediaType("image/jpeg"))
					.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+fileName+"\"")
					//.body(new PathResource(fileNamePath));
					.body(new PathResource(fileNamePath));
		}
		
	//getting package by id
		@GetMapping(value="/packages",params="id")
		public ResponseEntity<?> getSinglePackage(@RequestParam("id") int id){
			Packagess packages = packageService.findById(id);
			if(packages!=null) {
				return globalResponse.responseClient(packages);
			}else {
				return globalResponse.globalResponse("Package with id "+ id+" Not found",HttpStatus.BAD_REQUEST.value());
			}
		}

		//getting package by package type
		@GetMapping(value="/packages",params="packagetype")
		public ResponseEntity<?> listPackageByType(@RequestParam("packagetype") String packagetype){
			List<Packagess> allPackageList = packageService.findByPackageType(packagetype);
			return globalResponse.listPackageResponse(allPackageList);
		}
		//getting package by Sellar 
		@GetMapping(value="/packages",params="packagesellar")
		public ResponseEntity<?> listPackageBySellar(@RequestParam("packagesellar") String packagesellar){
			List<Packagess> allPackageList = packageService.findByPackageSellar(packagesellar);
			return globalResponse.listPackageResponse(allPackageList);
		}
		
		
		//search package by keyword
		@GetMapping(value="/packages",params="search")
		public ResponseEntity<?> listPackageByKeyword(@RequestParam("search") String search){
			List<Packagess> allPackageList = packageService.findPackagesByKeyword(search);
			return globalResponse.listPackageResponse(allPackageList);
		}
		//filtering according to rating
		@GetMapping(value="/packages",params="rating")
		public ResponseEntity<?> listPackageByrating(@RequestParam("rating") int rating){
			List<Packagess> allPackageList = packageService.findByRating(rating);
			return globalResponse.listPackageResponse(allPackageList);
		}
	
		@GetMapping(value="/packages",params= {"ordering","ordertype"})
		public ResponseEntity<?> listPackageByViews(@RequestParam("ordering") String ordering,@RequestParam("ordertype") String ordertype){
			List<Packagess> allPackages = null;
			if(ordering.matches("rating") && ordertype.matches("DESC")) {
				//ordering as best rated
				allPackages = packageService.findByOrderByRatingDesc();
				return globalResponse.listPackageResponse(allPackages);
				
			}else if(ordering.matches("rating") && ordertype.matches("ASC")) {
				//ordering as least rating
				allPackages = packageService.findByOrderByRatingAsc();
				return globalResponse.listPackageResponse(allPackages);
			}else if(ordering.matches("views") && ordertype.matches("DESC")) {
				//ordering most views
				allPackages = packageService.findByOrderByViewsDesc();
				return globalResponse.listPackageResponse(allPackages);
			}else if(ordering.matches("views") && ordertype.matches("ASC")){
				allPackages = packageService.findByOrderByViewsAsc();
				return globalResponse.listPackageResponse(allPackages);
			}else if(ordering.matches("recent") && ordertype.matches("ASC")) {
				//old packages
				allPackages = packageService.findByOrderByCreatedAtAsc();
				return globalResponse.listPackageResponse(allPackages);
				
			}else if(ordering.matches("recent") && ordertype.matches("DESC")) {
				//recently added
				allPackages = packageService.findByOrderByCreatedAtDesc();
				return globalResponse.listPackageResponse(allPackages);
			
			}else {
				return globalResponse.globalResponse("Your query didn't not matched the reqeust.Please enter valid query", HttpStatus.BAD_REQUEST.value());
			}
		}
		
}
