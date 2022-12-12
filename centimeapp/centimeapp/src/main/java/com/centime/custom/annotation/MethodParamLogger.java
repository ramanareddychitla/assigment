package com.centime.custom.annotation;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Aspect
@Component
public class MethodParamLogger {
	
	private static final Logger LOG = LoggerFactory.getLogger(MethodParamLogger.class);

	
	 private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	    @Around("execution(* *(..)) && @annotation(com.centime.custom.annotation.LogMethodParam)")
	    public Object logParameters(ProceedingJoinPoint jointPoint) throws Throwable {
	        String methodName = jointPoint.getSignature().getName();
	        String[] argumentNames = ((MethodSignature) jointPoint.getSignature()).getParameterNames();
	        Object[] values = jointPoint.getArgs();
	        Map<String, Object> parameters = new HashMap<>();
	        if (argumentNames.length != 0) {
	            for (int i = 0; i < argumentNames.length; i++) {
	                parameters.put(argumentNames[i], values[i]);
	            }
	        }

	        
	        Object result = jointPoint.proceed(jointPoint.getArgs());

	        LOG.info("Name of the methos " + methodName );
	        if (!parameters.isEmpty()) 
	        	LOG.info(gson.toJson(parameters), true);
	        return result;
	    }

}
