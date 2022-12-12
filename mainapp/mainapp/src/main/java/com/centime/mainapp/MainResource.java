package com.centime.mainapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.centime.custom.exception.ServiceException;
import com.centime.custom.exception.UserValidationException;
import com.centime.model.User;
import com.centime.service.InvocationService;
import com.centime.util.Validator;

@RestController
public class MainResource {

	private static final Logger LOG = LoggerFactory.getLogger(MainResource.class);

	@Autowired
	InvocationService invocationService;
	
	@GetMapping("check")
	public String getHealthcheck() {
		LOG.info("Health check ");
		return "up";
	}

	@PostMapping("wishuser")
	public ResponseEntity<String> wishUser(@RequestBody User user) {
		LOG.info("wishuser service called....");
		
		Validator.validateUser(user);
		
		String resultUserName = invocationService.invoke(user);
		
		return ResponseEntity.ok(resultUserName);
		
	}

	
	
	@ExceptionHandler(value = UserValidationException.class)
	   public ResponseEntity<Object> exception(UserValidationException exception) {
	      return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	   }
	
	@ExceptionHandler(value = ServiceException.class)
	   public ResponseEntity<Object> exception(ServiceException exception) {
	      return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	   }

}
