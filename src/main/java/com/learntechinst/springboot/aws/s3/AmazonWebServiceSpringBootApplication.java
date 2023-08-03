package com.learntechinst.springboot.aws.s3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AmazonWebServiceSpringBootApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(AmazonWebServiceSpringBootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AmazonWebServiceSpringBootApplication.class, args);
		LOGGER.info("Amazon Web Service Spring Boot S3 application started successfully.");
	}
}
