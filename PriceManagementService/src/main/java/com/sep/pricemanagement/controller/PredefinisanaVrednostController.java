package com.sep.pricemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.sep.pricemanagement.config.DatabaseUri;

public class PredefinisanaVrednostController {

	@Autowired
	private DatabaseUri databaseUri;
	
	@Autowired
	private RestTemplate restTemplate;
	
}
