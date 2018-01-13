package com.sep.pricemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sep.pricemanagement.config.DatabaseUri;
import com.sep.pricemanagement.model.Cenovnik;

@CrossOrigin
@RestController
@RequestMapping("/api/cenovnik")
public class CenovnikController {

	@Value("${spring.data.company}")
	private String osiguravajucaKucaId;
	
	@Autowired
	private DatabaseUri databaseUri;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping
	@ResponseBody
	public Cenovnik getCenovniciZaOsiguravajucuKucu() {
		return restTemplate.getForObject(databaseUri.getDatabaseUri()+"/cenovnici/zaOsiguravajucuKucu/"+osiguravajucaKucaId, Cenovnik.class);
	}
	
	@GetMapping("/zaOsiguravajucuKucu/{id}")
	@ResponseBody
	public Cenovnik getCenovnikZaOsiguravajucuKucu(@PathVariable("id")Long osiguravajucaKucaId) {
		return restTemplate.getForObject(databaseUri.getDatabaseUri()+"/cenovnici/zaOsiguravajucuKucu/"+osiguravajucaKucaId, Cenovnik.class);
	}
	
}
