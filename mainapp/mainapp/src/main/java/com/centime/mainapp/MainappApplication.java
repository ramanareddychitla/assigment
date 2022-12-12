package com.centime.mainapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan({"com.centime.service","com.centime.util","com.centime.mainapp"})
public class MainappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainappApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	}

}
