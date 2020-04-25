
package com.acepirit.ghumou.main.GhumouMain;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.acepirit.ghumou.main.GhumouMain.Service.FileUploadService;

@SpringBootApplication

public class GhumouMainApplication {

	public static void main(String[] args) {
		//uploading file to this directory
		new File(FileUploadService.uploadDirectoryfilesystem).mkdir();
		SpringApplication.run(GhumouMainApplication.class, args);
	}

}
