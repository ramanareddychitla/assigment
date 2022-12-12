package com.centime.centimeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.centime.centimeapp.*","com.centime.centimeapp","com.centime.custom.annotation"})
public class CentimeappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentimeappApplication.class, args);
	}

}
