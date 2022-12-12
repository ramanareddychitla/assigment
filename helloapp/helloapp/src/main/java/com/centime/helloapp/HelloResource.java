package com.centime.helloapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(HelloResource.class);
	
	@GetMapping("hello")
	public ResponseEntity<String> getHello() {
		LOG.info("Hello service called....");
		return ResponseEntity.ok("Hello!");
	}

}
