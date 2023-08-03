package com.learntechinst.aws.s3.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface AmazonWebServiceS3Service {

	void uploadFile(MultipartFile multipartFile);
	
	ByteArrayOutputStream downloadFile(String fileName);
	
	List<String> listFiles(); 
}
