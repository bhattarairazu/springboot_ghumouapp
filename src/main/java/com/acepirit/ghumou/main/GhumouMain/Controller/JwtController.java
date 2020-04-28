package com.acepirit.ghumou.main.GhumouMain.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acepirit.ghumou.main.GhumouMain.Entity.AuthenticateRequest;
import com.acepirit.ghumou.main.GhumouMain.Entity.AuthenticateResponse;
import com.acepirit.ghumou.main.GhumouMain.Repository.AuthenticateRepository;
import com.acepirit.ghumou.main.GhumouMain.Service.AuthenticateService;
import com.acepirit.ghumou.main.GhumouMain.Service.GlobalResponseService;
import com.acepirit.ghumou.main.GhumouMain.Utils.Jwtutil;

@RestController
public class JwtController {
	@Autowired
	private AuthenticateService authenService;
	
	@Autowired
	private Jwtutil jwtFilter;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthenticateRepository authenRepository;
	
	@Autowired
	private GlobalResponseService globalResponse;
	
	//authentication the user with token
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateToken(@RequestBody AuthenticateRequest authenticateRequest) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(),authenticateRequest.getPassword()));
			
		}catch(BadCredentialsException ex) {
			throw new Exception("Incorrect username and password");
		}
		
		final UserDetails userDetails = authenService.loadUserByUsername(authenticateRequest.getUsername());
		String jwt = jwtFilter.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticateResponse(jwt));
	}
	
	//initila signup for users
	@PostMapping("/signup")
	public ResponseEntity<?> signupUser(@RequestBody AuthenticateRequest authenrequest) {
		AuthenticateRequest auth = authenService.findByUserName(authenrequest.getUsername());
		if(auth==null) {
			authenrequest.setPassword(passwordEncoder.encode(authenrequest.getPassword()));;
			authenRepository.save(authenrequest);
			
			return globalResponse.globalResponse("Success",HttpStatus.CREATED.value());
		}else {
			return globalResponse.globalResponse("Username should be unique.Please select another username",HttpStatus.BAD_REQUEST.value());
		}
	}
	
	
	
}
