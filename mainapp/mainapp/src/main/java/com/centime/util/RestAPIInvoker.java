package com.centime.util;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.centime.constants.Constants;
import com.centime.custom.exception.ServiceException;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@Service("restAPIInvoker")
public class RestAPIInvoker {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EurekaClient eurekaClient;

	public ResponseEntity<String> postForEntity(String appName, String path, Object o) {
		try {
			URI url = getURL(appName, path);
			
			ResponseEntity<String> response = restTemplate.postForEntity(url, o, String.class);
			
			Validator.validateResponse(response);
			
			return response;
		} catch (Exception e) {
			new ServiceException(Constants.INTERNAL_SERVER_ERROR);
		}
		return null;
	}


	public ResponseEntity<String> getForEntity(String appName, String path) {

		try {
			URI url = getURL(appName, path);

			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

			Validator.validateResponse(response);

			return response;
		} catch (RestClientException e) {
			new ServiceException(Constants.INTERNAL_SERVER_ERROR);
		}
		return null;

	}
	
	private URI getURL(String appName, String path) {
		InstanceInfo appServer = eurekaClient.getApplication(appName).getInstances().get(0);

		String hostName = appServer.getHostName();
		int port = appServer.getPort();

		URI url = URI.create(Constants.HTTP + hostName + Constants.COLON + port + path);
		return url;
	}

}
