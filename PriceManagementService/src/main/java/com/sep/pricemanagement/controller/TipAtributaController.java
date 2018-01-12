package com.sep.pricemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sep.pricemanagement.config.DatabaseUri;
import com.sep.pricemanagement.model.TipAtributa;

@CrossOrigin
@RestController
@RequestMapping("/api/tipoviAtributa")
public class TipAtributaController {

	private final DatabaseUri databaseUri;
	private final RestTemplate restTemplate;
	
	@Autowired
	public TipAtributaController(RestTemplate restTemplate, DatabaseUri databaseUri) {
		this.restTemplate = restTemplate;
		this.databaseUri = databaseUri;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/zaTipOsiguranja/{tipOsiguranjaId}")
	@ResponseBody
	public ResponseEntity<List<TipAtributa>> getTipoviAtributaZaTipOsiguranja(@PathVariable("tipOsiguranjaId") Long tipOsiguranjaId){
		List<TipAtributa> tipAtributa = restTemplate.getForObject(databaseUri.getDatabaseUri()+"/tipoviAtributa/zaTipOsiguranja/"+tipOsiguranjaId, List.class);
		return new ResponseEntity<List<TipAtributa>>(tipAtributa,HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/uticuNaCenu/{tipOsiguranjaId}")
	@ResponseBody
	public ResponseEntity<List<TipAtributa>> getTipoviAtributaZaTipOsiguranjaAndUticeNaCenu(@PathVariable("tipOsiguranjaId")Long tipOsiguranjaId) {
		List<TipAtributa> tipAtributa = restTemplate.getForObject(databaseUri.getDatabaseUri()+"/tipoviAtributa/uticuNaCenu/"+tipOsiguranjaId, List.class);
		return new ResponseEntity<List<TipAtributa>>(tipAtributa,HttpStatus.OK);
	}
	
	/*@SuppressWarnings("unchecked")
	@GetMapping("/zaKontekst/{kontekstAtributaId}")
	@ResponseBody
	public ResponseEntity<List<TipAtributa>> getTipoviAtributaZaKontekstAtributa(@PathVariable("kontekstAtributaId") Long kontekstAtributaId){
		List<TipAtributa> tipAtributa = restTemplate.getForObject(databaseUri.getDatabaseUri()+"/tipoviAtributa/zaKontekst/"+kontekstAtributaId, List.class);
		return new ResponseEntity<List<TipAtributa>>(tipAtributa,HttpStatus.OK);
	}*/
}
