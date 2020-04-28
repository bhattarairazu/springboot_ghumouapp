package com.acepirit.ghumou.main.GhumouMain.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static String UPLOAD_ROOT = "/home/acepirit/ghumoufiles/";
	private final ResourceLoader resourceLoader;
	
	@Autowired
	public FileUploadService(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
		
	}
	
	public Path findImage(String filename) {
		Path fileNamePath = Paths.get(UPLOAD_ROOT,filename);
		return fileNamePath;
	}
	
	public String storeFile(MultipartFile file) throws IOException{
		//if(!file.isEmpty()) {
			Files.copy(file.getInputStream(),Paths.get(UPLOAD_ROOT,file.getOriginalFilename()));
			System.out.println("Insid eservice"+UPLOAD_ROOT+file.getOriginalFilename());
			return "/ghumoufiles/"+file.getOriginalFilename();
		//}
		//return null;
	}
//	public boolean deleteFile(String filename) {
//		try {
//			Files.deleteIfExists(Paths.get(UPLOAD_ROOT,filename));
//			return true;
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		
//	}
	
}
