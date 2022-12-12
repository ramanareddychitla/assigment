package com.centime.custom.exception;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	   public ServiceException(String exceptionDetails){
		   super(exceptionDetails);
	   }
}
