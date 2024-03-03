package com.srhomoeo.clinic.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.srhomoeo.clinic.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		
	    // Get the original filename and file extension
	    String originalFilename = file.getOriginalFilename();
	    String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
	    
	    // Generate a random filename
	    String randomId = UUID.randomUUID().toString();
	    String fileName = randomId + fileExtension;
	    
	    // Build the complete file path
	    String filePath = path + File.separator + fileName;
	    
	    // Create Folder if not exist
	    File f = new File(path);
	    if (!f.exists()) {
	        f.mkdir();
	    }
	    
	    // Copy the file to the specified path
	    Files.copy(file.getInputStream(), Paths.get(filePath));

	    return fileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String filePath = path + File.separator + fileName;
		InputStream input = new FileInputStream(filePath);
		return input;
	}

}
