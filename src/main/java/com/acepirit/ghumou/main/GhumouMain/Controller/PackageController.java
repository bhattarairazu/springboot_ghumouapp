package com.acepirit.ghumou.main.GhumouMain.Controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import java.lang.reflect.Field;

import com.acepirit.ghumou.main.GhumouMain.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	@PreAuthorize("hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
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
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> allPakcage(){
		List<Packagess> allpakcage = packageService.findAll();
		return globalResponse.listPackageResponse(allpakcage); 
	}
	//delete package by id
	@DeleteMapping(value="/packages/delete/{id}")
	@PreAuthorize("hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deletePackage(@PathVariable int id){
		packageService.deleteById(id);
		return globalResponse.globalResponse("success",HttpStatus.OK.value());
	}
	@PatchMapping("/packages/update/{id}")
	@PreAuthorize("hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updatePackage(@RequestPart("updatePackage") Map<String,String> updatePackage,@PathVariable int id,@RequestPart("thumnail") MultipartFile thumnail,@RequestPart("images") MultipartFile[] images){
		Packagess packages = packageService.findById(id);

		String thumnailPath=null;
		if(!thumnail.isEmpty()) {
			try {
				thumnailPath = fileUploadService.storeFile(thumnail);
			} catch (IOException e) {
				e.printStackTrace();
			}
			packages.setThumnail(thumnailPath);

		}
		//checking list of images for null value
		List<Images> imagesPath = new ArrayList<>();
		if(images.length>0) {
			System.out.println("inside images "+images.length);
			for(MultipartFile file:images) {
				if(!file.isEmpty()) {
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
			if(imagesPath.size()>0){
			packages.setImages(imagesPath);
			}

		}

		updatePackage.forEach((k,v)->{
			if(k.matches("inclusion")){
				Field fie = ReflectionUtils.findField(Inclusions.class,k);
				fie.setAccessible(true);
				ReflectionUtils.setField(fie,packages.getInclusions(),v);
				System.out.println("field inside profile values"+v);
			}
			else if(k.matches("exclusion")){
				System.out.println("ex "+k+"valu "+v);

				Field exclusion = ReflectionUtils.findField(Exclusions.class,k);
				exclusion.setAccessible(true);
				ReflectionUtils.setField(exclusion,packages.getExclusions(),v);
			}
			else if(k.matches("itenary")){
				System.out.println("it "+k+"value "+v);

				Field itenary = ReflectionUtils.findField(Itenarys.class,k);
				itenary.setAccessible(true);

				ReflectionUtils.setField(itenary,packages.getItenarys(),v);
			}else if(k.matches("salePrice") || k.matches("regularPrice") || k.matches("offers")){

				Field fiels = ReflectionUtils.findField(Packagess.class,k);
				fiels.setAccessible(true);
				ReflectionUtils.setField(fiels,packages,Integer.parseInt(v));
			}else{
				Field fiels = ReflectionUtils.findField(Packagess.class,k);
				fiels.setAccessible(true);
				ReflectionUtils.setField(fiels,packages,v);

			}

		});

		packages.setInclusions(packages.getInclusions());
		packages.setExclusions(packages.getExclusions());
		packages.setItenarys(packages.getItenarys());

		packageService.save(packages);
		return globalResponse.globalResponse("success",HttpStatus.OK.value());

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
		
	//getting package by id and getting similar packages
		@GetMapping(value="/packages",params="id")
		@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
		public ResponseEntity<?> getSinglePackage(@RequestParam("id") int id){
			Packagess packages = packageService.findById(id);
			String packageName = packages.getPackageTitle();
			List<Packagess> similarlist = packageService.findPackagesBySearchWithRecent(packageName);

			//increase package view by 1
			packages.setViews(packages.getViews()+1);
			packageService.save(packages);
			if(packages!=null) {
				return globalResponse.similarResponse(packages,similarlist);
			}else {
				return globalResponse.globalResponse("Package with id "+ id+" Not found",HttpStatus.BAD_REQUEST.value());
			}
		}

		//getting package by package type
		@GetMapping(value="/packages",params="packagetype")
		@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
		public ResponseEntity<?> listPackageByType(@RequestParam("packagetype") String packagetype){
			List<Packagess> allPackageList = packageService.findByPackageType(packagetype);
			return globalResponse.listPackageResponse(allPackageList);
		}
		//getting package by Sellar 
		@GetMapping(value="/packages",params="packagesellar")
		@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
		public ResponseEntity<?> listPackageBySellar(@RequestParam("packagesellar") String packagesellar){
			List<Packagess> allPackageList = packageService.findByPackageSellar(packagesellar);
			return globalResponse.listPackageResponse(allPackageList);
		}
		
		
		//search package by keyword
		@GetMapping(value="/packages",params="search")
		@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
		public ResponseEntity<?> listPackageByKeyword(@RequestParam("search") String search){
			List<Packagess> allPackageList = packageService.findPackagesByKeyword(search);
			return globalResponse.listPackageResponse(allPackageList);
		}
		//filtering according to rating
		@GetMapping(value="/packages",params="rating")
		@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
		public ResponseEntity<?> listPackageByrating(@RequestParam("rating") int rating){
			List<Packagess> allPackageList = packageService.findByRating(rating);
			return globalResponse.listPackageResponse(allPackageList);
		}

		//filtering according to search and order type
		@GetMapping(value="/packages",params= {"search","ordering"})
		public ResponseEntity<?> listPackageByrating(@RequestParam("search") String search,@RequestParam("ordering") String ordering){
			List<Packagess> allPackageList = packageService.findPackagessByKeywordWithOrder(search,ordering);
			System.out.println("Ordering packages "+allPackageList);
			return globalResponse.listPackageResponse(allPackageList);
		}



	@GetMapping(value="/packages",params= {"ordering","ordertype"})
		@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
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
		//similar packages


}
