package com.centime.custom.exception;

public class UserValidationException extends RuntimeException{

	   private static final long serialVersionUID = 1L;
	   public UserValidationException(String exceptionDetails){
		   super(exceptionDetails);
	   }

}
