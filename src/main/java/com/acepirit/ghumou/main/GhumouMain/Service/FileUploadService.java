package com.acepirit.ghumou.main.GhumouMain.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	//directory path for storing files
	public static String uploadDirectoryfilesystem = System.getProperty("user.dir")+"/ghumoufiles";

	public String storeFile(MultipartFile file) {
		//getting filepath
		String uploadDirectorynew = "/ghumoufiles";
		//String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		Path fileNamePath = Paths.get(uploadDirectoryfilesystem,file.getOriginalFilename());
		Path datastore = Paths.get(uploadDirectorynew,file.getOriginalFilename());
	
		//wiritn file to the respective path
		try {
			Files.write(fileNamePath,file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return datastore.toString();
	}
}
