package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SurekhaDataApplication {

	private static final Logger logger = LoggerFactory.getLogger(SurekhaDataApplication.class);
	public static void main(String[] args) {
		
		logger.info("Application Started");
		SpringApplication.run(SurekhaDataApplication.class, args);
	}

}
