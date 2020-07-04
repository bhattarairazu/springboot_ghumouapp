package com.acepirit.ghumou.main.GhumouMain.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acepirit.ghumou.main.GhumouMain.Service.GlobalVariableService;
import com.acepirit.ghumou.main.GhumouMain.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserService userDetailsService;
	
	@Autowired
	private Jwtutil jwtUtils;

	@Autowired
	private GlobalVariableService globalVariableService;


	private final ObjectMapper objectMapper;

	public JwtRequestFilter(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {


		if(request.getRequestURI().equals("/admin_v1/**")){
			return;
		}


		final String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;
	
		if(authorizationHeader!=null && authorizationHeader.contains("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = jwtUtils.extractUsername(jwt);

			//setting username to global variable
			globalVariableService.setUsername(username);
			//checking userlogin

		}
		//checking whether user is logout or not
		if(username!=null){
			if(!userDetailsService.findByUserName(username).isLogin()){
				Map<String, Object> errorDetails = new HashMap<>();
				errorDetails.put("message", "Your Session is Expired.Please Login to continue");

				response.setStatus(HttpStatus.FORBIDDEN.value());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);

				objectMapper.writeValue(response.getWriter(), errorDetails);


				return;
				//throw new RuntimeException("Your session is expired.Please login to access this resource");

			}
		}

		
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails =userDetailsService.loadUserByUsername(username);
				System.out.println("Spring logoing user");
				
				if(jwtUtils.validateToken(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken usernametoken = 
							new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
					usernametoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernametoken);
				}
				
			}else{
				//System.out.println("Spring logout ");
			}
			
		filterChain.doFilter(request, response);
		
	}

}
