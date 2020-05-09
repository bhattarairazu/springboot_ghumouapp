package com.acepirit.ghumou.main.GhumouMain.Service;

import java.util.List;

import com.acepirit.ghumou.main.GhumouMain.Entity.*;
import com.acepirit.ghumou.main.GhumouMain.Response.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GlobalResponseService {
	
	public ResponseEntity<GlobalResponse> globalResponse(String message,int statuscode){
		GlobalResponse respone = new GlobalResponse(message, statuscode, System.currentTimeMillis());
		return ResponseEntity.ok(respone);
	}
	//login error response
	public ResponseEntity<GlobalResponse> loginErrorResponse(){
		GlobalResponse respone = new GlobalResponse("Incorrect Username or Password", HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		return ResponseEntity.ok(respone);
	}
	
	
	public ResponseEntity<GResponseTemplate> responseClient(Object obj){
		GResponseTemplate gr = new GResponseTemplate();
		gr.setMsg("Success");
		gr.setStatusCode(HttpStatus.CREATED.value());
		gr.setTimestamp(System.currentTimeMillis());
		gr.setResults(obj);
		return ResponseEntity.ok(gr);
	}
	//similar packages response
	public ResponseEntity<SimilarPackagesResponse> similarResponse(Packagess packageDetail,List<Packagess> similarList){
		SimilarPackagesResponse simiar = new SimilarPackagesResponse();
		simiar.setMsg("Success");
		simiar.setStatusCode(HttpStatus.OK.value());
		simiar.setTimestamp(System.currentTimeMillis());
		simiar.setResults(packageDetail);
		simiar.setSimilarPackages(similarList);
		return ResponseEntity.ok(simiar);

	}



	public ResponseEntity<LoginResponse> loginSuccessResponse(int id,String jwt,String role){
		LoginResponse responseLogin = new LoginResponse();
		responseLogin.setMsg("Success");
		responseLogin.setStatusCode(HttpStatus.OK.value());
		responseLogin.setTimeStamp(System.currentTimeMillis());
		responseLogin.setUserid(id);
		responseLogin.setRole(role);
		responseLogin.setJwt(jwt);
		
		return ResponseEntity.ok(responseLogin);
	}
	
	
	public ResponseEntity<ListResponseTemplate> listResponse(List<User> objList){
		ListResponseTemplate lr = new ListResponseTemplate();
		lr.setMsg("Success");
		lr.setStatusCode(HttpStatus.CREATED.value());
		lr.setTimestamp(System.currentTimeMillis());
		lr.setResults(objList);
		return ResponseEntity.ok(lr);
	}
	//review list response
	public ResponseEntity<ListReviewResponse> listReview(List<Review> objList){
		ListReviewResponse lr = new ListReviewResponse();
		lr.setMsg("Success");
		lr.setStatusCode(HttpStatus.CREATED.value());
		lr.setTimestamp(System.currentTimeMillis());
		lr.setResults(objList);
		return ResponseEntity.ok(lr);
	}
	
	//profilepic chnage response
	public ResponseEntity<ProfilePicChnageResponse> profilePicSuccess(String profliepicurl){
		ProfilePicChnageResponse responses = new ProfilePicChnageResponse();
		responses.setMsg("Success");
		responses.setStatusCode(HttpStatus.OK.value());
		responses.setTimeStamp(System.currentTimeMillis());
		responses.setProfileImageUrl(profliepicurl);
		return ResponseEntity.ok(responses);
		
	}
	//package list response
	public ResponseEntity<PackageListResponse> listPackageResponse(List<Packagess> objList){
		PackageListResponse lr = new PackageListResponse();
		lr.setMsg("Success");
		lr.setStatusCode(HttpStatus.CREATED.value());
		lr.setTimestamp(System.currentTimeMillis());
		lr.setResults(objList);
		return ResponseEntity.ok(lr);
	}
	//package list response
		public ResponseEntity<AddtoFavouriteListResponse> listFavouriteResponse(List<AddToFavourites> objList){
			AddtoFavouriteListResponse lr = new AddtoFavouriteListResponse();
			lr.setMsg("Success");
			lr.setStatusCode(HttpStatus.CREATED.value());
			lr.setTimestamp(System.currentTimeMillis());
			lr.setResults(objList);
			return ResponseEntity.ok(lr);
		}
		
		//Order list response
		public ResponseEntity<ListOrderResponse> listOrderResponse(List<Orderpackage> objList){
			ListOrderResponse lr = new ListOrderResponse();
			lr.setMsg("Success");
			lr.setStatusCode(HttpStatus.CREATED.value());
			lr.setTimestamp(System.currentTimeMillis());
			lr.setResults(objList);
			return ResponseEntity.ok(lr);
		}
}
