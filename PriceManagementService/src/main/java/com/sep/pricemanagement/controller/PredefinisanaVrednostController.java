package com.sep.pricemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sep.pricemanagement.config.DatabaseUri;
import com.sep.pricemanagement.model.PredefinisanaVrednost;

@RestController
@RequestMapping("/api/predefinisaneVrednosti")
public class PredefinisanaVrednostController {

	@Value("${spring.data.company}")
	private String osiguravajucaKucaId;
	
	@Autowired
	private DatabaseUri databaseUri;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	@GetMapping
	@ResponseBody
	public List<PredefinisanaVrednost> getPredefinisaneVrednosti() {
		return restTemplate.getForObject(databaseUri.getDatabaseUri() + "/predefinisaneVrednosti/zaOsiguravajucuKucu/" + osiguravajucaKucaId, List.class);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/zaTipAtributa/{tipAtributaId}")
	@ResponseBody
	public ResponseEntity<List<PredefinisanaVrednost>> getPredefinisaneVrednostiZaTipAtributa(@PathVariable("tipAtributaId")Long tipAtributaId) {
		List<PredefinisanaVrednost> predefinisaneVrednosti = restTemplate.getForObject(databaseUri.getDatabaseUri()+"/predefinisaneVrednosti/zaTipAtributa/"+tipAtributaId, List.class);
		return new ResponseEntity<List<PredefinisanaVrednost>>(predefinisaneVrednosti, HttpStatus.OK);
	}
	
}
