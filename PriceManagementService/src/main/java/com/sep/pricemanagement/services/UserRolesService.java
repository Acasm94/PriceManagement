package com.sep.pricemanagement.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRolesService {

	@Value("${spring.data.authorizationProvider}")
	private String authorizationProviderUri;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> getPrivilegesForRole(String roleName){
		return restTemplate.getForObject(authorizationProviderUri+"/privileges/forRole/"+roleName, ArrayList.class);
	}
}
