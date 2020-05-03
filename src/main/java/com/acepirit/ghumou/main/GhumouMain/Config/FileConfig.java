package com.acepirit.ghumou.main.GhumouMain.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer {
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//	    registry.addResourceHandler("/resources/**")
//	    .addResourceLocations("/resources/","classpath:/other-resources/","classpath:/static/");
//
//	}
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").
        allowedMethods("GET", "POST", "PUT", "DELETE","PATCH").allowedOrigins("http://localhost:3000")
        .allowedHeaders("*");
//
//
//       // .addCorsMappings("");
   }
}