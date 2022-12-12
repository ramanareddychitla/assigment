package com.centime.util;


import org.apache.commons.lang.StringUtils;

import com.centime.model.User;

public class Validator {
	
	public static boolean validateUser(User user){
		if(StringUtils.isBlank(user.getName()) || StringUtils.isBlank(user.getSurname()) ) {
			return false;
		}
		return true;
	}

}
