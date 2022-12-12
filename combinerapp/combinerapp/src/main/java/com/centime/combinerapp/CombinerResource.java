package com.centime.combinerapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.centime.model.User;
import com.centime.util.Validator;

@RestController
public class CombinerResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(CombinerResource.class);

	
	@PostMapping("concatuser")
	public ResponseEntity<String> concatUser(@RequestBody User user) {
		
		LOG.info("combiner service called....");
		if(!Validator.validateUser(user)) {
			return ResponseEntity.badRequest().body("User details are missing");
		}
		
		LOG.info("User Details :" + user.toString());
		return ResponseEntity.ok(user.getName()+ " " + user.getSurname());
		
	}

}
