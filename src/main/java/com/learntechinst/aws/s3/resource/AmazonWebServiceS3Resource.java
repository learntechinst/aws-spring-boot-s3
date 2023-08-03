package com.learntechinst.aws.s3.resource;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learntechinst.aws.s3.service.AmazonWebServiceS3Service;

@RestController
@RequestMapping(value= "/s3")
public class AmazonWebServiceS3Resource {

	@Autowired
	private AmazonWebServiceS3Service service;

	@PostMapping(value= "/upload")
	public ResponseEntity<String> uploadFile(@RequestPart(value= "file") final MultipartFile multipartFile) {
		service.uploadFile(multipartFile);
		final String response = "[" + multipartFile.getOriginalFilename() + "] uploaded successfully.";
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/download/{filename}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) {
		ByteArrayOutputStream downloadInputStream = service.downloadFile(filename);
        return ResponseEntity.ok()
                .contentType(contentType(filename))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(downloadInputStream.toByteArray());
    }
	
	@GetMapping("/list/files")
    public ResponseEntity<List<String>> getListOfFiles() {
        return new ResponseEntity<>(service.listFiles(), HttpStatus.OK);
    }
	
	 private MediaType contentType(String filename) {
	        String[] fileArrSplit = filename.split("\\.");
	        String fileExtension = fileArrSplit[fileArrSplit.length - 1];
	        switch (fileExtension) {
	            case "txt":
	                return MediaType.TEXT_PLAIN;
	            case "png":
	                return MediaType.IMAGE_PNG;
	            case "jpg":
	                return MediaType.IMAGE_JPEG;
	            default:
	                return MediaType.APPLICATION_OCTET_STREAM;
	        }
	    }
}
