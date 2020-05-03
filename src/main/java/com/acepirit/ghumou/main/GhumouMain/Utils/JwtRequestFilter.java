package com.acepirit.ghumou.main.GhumouMain.Utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acepirit.ghumou.main.GhumouMain.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.acepirit.ghumou.main.GhumouMain.Service.AuthenticateService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserService userDetailsService;
	
	@Autowired
	private Jwtutil jwtUtils;
	
	
	
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
		}
		
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails =userDetailsService.loadUserByUsername(username);
				
				if(jwtUtils.validateToken(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken usernametoken = 
							new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
					usernametoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernametoken);
				}
				
			}
			
		filterChain.doFilter(request, response);
		
	}

}
