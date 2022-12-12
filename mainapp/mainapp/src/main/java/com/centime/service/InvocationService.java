package com.centime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.centime.model.User;
import com.centime.util.RestAPIInvoker;

@Service("invocationService")
public class InvocationService {

	@Autowired
	RestAPIInvoker restAPIInvoker;
	
	 @Value("${combiner.service}")
	  private String combinerService;
	 
	 @Value("${helloapp.service}")
	  private String helloappService;

	public String invoke(User user) {

		return getHello() + " " + postUser(user);
	}

	private String postUser(User user) {

		ResponseEntity<String> response = restAPIInvoker.postForEntity(combinerService, "/concatuser", user);

		return response.getBody();
	}

	private String getHello() {
		
		ResponseEntity<String> response = restAPIInvoker.getForEntity(helloappService, "/hello");

		return response.getBody();
	}

}
