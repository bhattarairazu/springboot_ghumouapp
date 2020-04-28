
package com.acepirit.ghumou.main.GhumouMain;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.io.ClassPathResource;

import com.acepirit.ghumou.main.GhumouMain.Service.FileUploadService;

@SpringBootApplication

public class GhumouMainApplication extends SpringBootServletInitializer{
	
	

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GhumouMainApplication.class);
	}

	public static void main(String[] args) {
		//uploading file to this directory
		new File("/home/acepirit/ghumoufiles").mkdir();
		SpringApplication.run(GhumouMainApplication.class, args);
	}

}
