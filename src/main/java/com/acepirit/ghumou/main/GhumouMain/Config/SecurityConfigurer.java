package com.acepirit.ghumou.main.GhumouMain.Config;

import com.acepirit.ghumou.main.GhumouMain.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.acepirit.ghumou.main.GhumouMain.Service.AuthenticateService;
import com.acepirit.ghumou.main.GhumouMain.Utils.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService authenService;
	
	@Autowired
	private JwtRequestFilter jwtReqeustFilter;

	@Autowired
	private AuthEntryPoint unauthorizedHandler;
//
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.authorizeRequests().antMatchers("/api/v2/account/login","/api/v2/ghumoufiles/**","/swagger-ui.html").permitAll()
			 .antMatchers("/api/v2/account/confirm-account/**").permitAll()
			//.anyRequest().authenticated()
				.anyRequest().permitAll()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtReqeustFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors();

	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/admin/**","/admin_v1/**","/resources/**");
	}
	
	//for authenticating the jwt request
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return  super.authenticationManagerBean();
	}
	
	//for encoding username and password
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
