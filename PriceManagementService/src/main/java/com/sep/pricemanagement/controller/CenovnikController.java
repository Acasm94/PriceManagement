package com.sep.pricemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sep.pricemanagement.config.DatabaseUri;
import com.sep.pricemanagement.model.Cenovnik;

@CrossOrigin
@RestController
@RequestMapping("/api/cenovnici")
public class CenovnikController {

	@Value("${spring.data.company}")
	private String osiguravajucaKucaId;
	
	@Autowired
	private DatabaseUri databaseUri;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/zaOsiguravajucuKucu")
	@ResponseBody
	public List<Cenovnik> getCenovniciZaOsiguravajucuKucu() {
		return restTemplate.getForObject(databaseUri.getDatabaseUri()+"/cenovnici/zaOsiguravajucuKucu/" + osiguravajucaKucaId, List.class);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/zaOsiguravajucuKucuPoDatumu")
	@ResponseBody
	public List<Cenovnik> getCenovniciZaOsiguravajucuKucuAndDate() {
		return restTemplate.getForObject(databaseUri.getDatabaseUri()+"/cenovnici/zaOsiguravajucuKucuPoDatumu/" + osiguravajucaKucaId, List.class);
	}
	
	@GetMapping
	@ResponseBody
	public Cenovnik getAktuelanCenovnikZaOsiguravajucuKucu() {
		return restTemplate.getForObject(databaseUri.getDatabaseUri()+"/cenovnici/aktuelan/zaOsiguravajucuKucu/" + osiguravajucaKucaId, Cenovnik.class);
	}
	
	@GetMapping("/aktuelan/{cenovnikId}")
	@ResponseBody
	public Cenovnik setAktuelanCenovnikZaOsiguravajucuKucu(@PathVariable("cenovnikId")Long cenovnikId) {
		return restTemplate.getForObject(databaseUri.getDatabaseUri()+"/cenovnici/aktuelan/" + osiguravajucaKucaId, Cenovnik.class);
	}
	
	@PostMapping
	@ResponseBody
	public Cenovnik createCenovnik(@RequestBody Cenovnik cenovnik) {
		return restTemplate.postForObject(databaseUri.getDatabaseUri() + "/cenovnici/" + osiguravajucaKucaId, cenovnik, Cenovnik.class);
	}
	
	@PutMapping
	@ResponseBody
	public Cenovnik updateCenovnik(@RequestBody Cenovnik cenovnik) {
		return restTemplate.postForObject(databaseUri.getDatabaseUri() + "/cenovnici/" + osiguravajucaKucaId, cenovnik, Cenovnik.class);
	}
	
}
