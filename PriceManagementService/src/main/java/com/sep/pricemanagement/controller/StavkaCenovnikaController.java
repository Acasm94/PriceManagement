package com.sep.pricemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.sep.pricemanagement.model.StavkaCenovnika;
import com.sep.pricemanagement.model.user.Permission;

@CrossOrigin
@RestController
@RequestMapping("/api/stavkaCenovnika")
public class StavkaCenovnikaController {

	@Autowired
	private DatabaseUri databaseUri;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/zaCenovnik/{cenovnikId}")
	@ResponseBody
	//@Permission(permissionName = "readStavkeZaCenovnik")
	public List<StavkaCenovnika> getStavkaCenovnikaZaCenovnik(@PathVariable("cenovnikId")Long cenovnikId){
		return restTemplate.getForObject(databaseUri.getDatabaseUri() + "/stavkeCenovnika/zaCenovnik/" + cenovnikId, List.class);
	}
	
	@GetMapping("/{cenovnikId}/{predefinisanaVrednostId}")
	@ResponseBody
	//@Permission(permissionName = "readStavkeZaCenovnikIPredVrednost")
	public StavkaCenovnika getStavkaCenovnikaZaCenovnikAndPredefinisanuVrednost(@PathVariable("cenovnikId")Long cenovnikId, @PathVariable("predefinisanaVrednostId")Long predefinisanaVrednostId) {
		return restTemplate.getForObject(databaseUri.getDatabaseUri()+"/stavkeCenovnika/" + cenovnikId + "/" + predefinisanaVrednostId, StavkaCenovnika.class);
	}
	
	@PostMapping("/{cenovnikId}")
	@ResponseBody
	//@Permission(permissionName = "addStavkuCenovnika")
	public ResponseEntity<StavkaCenovnika> novaStavkaCenovnika(@RequestBody StavkaCenovnika stavkaCenovnika, @PathVariable("cenovnikId") Long id){
		StavkaCenovnika response = restTemplate.postForObject(databaseUri.getDatabaseUri()+"/stavkeCenovnika/" + id, stavkaCenovnika, StavkaCenovnika.class);
		return new ResponseEntity<StavkaCenovnika>(response, HttpStatus.OK);
	}
	
	@PutMapping("/{cenovnikId}")
	@ResponseBody
	//@Permission(permissionName = "updateStavkuCenovnika")
	public Boolean izmeniStavkaCenovnika(@RequestBody StavkaCenovnika stavkaCenovnika, @PathVariable("cenovnikId")Long id) {
		restTemplate.put(databaseUri.getDatabaseUri() + "/stavkeCenovnika/" + id, stavkaCenovnika);
		return true;
	}
}
