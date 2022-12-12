package com.centime.util;


import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;

import com.centime.custom.exception.ServiceException;
import com.centime.custom.exception.UserValidationException;
import com.centime.model.User;

public class Validator {
	
	public static void validateUser(User user){
		if(StringUtils.isBlank(user.getName()) || StringUtils.isBlank(user.getSurname()) ) {
			throw new UserValidationException("User details are not correct");
		}
	}
	
	public static void validateResponse(ResponseEntity<String> response) {
		if(response == null || response.getStatusCode().value() != 200 ) {
			new ServiceException("Internal server error");
		}
	}

}
