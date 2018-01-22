package com.sep.pricemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sep.pricemanagement.config.DatabaseUri;
import com.sep.pricemanagement.model.TipOsiguranja;
import com.sep.pricemanagement.model.user.Permission;

@CrossOrigin
@RestController
@RequestMapping("/api/tipOsiguranja")
public class TipOsiguranjaController {

	@Autowired
	private DatabaseUri databaseUri;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/zaOsiguravajucuKucu/{id}")
	@ResponseBody
	//@Permission(permissionName = "readTipoveOsiguranjaZaOK")
	public List<TipOsiguranja> getTipoviOsiguranjaZaOsiguravajucuKucu(@PathVariable("id")Long osiguravajucaKucaId) {
		return restTemplate.getForObject(databaseUri.getDatabaseUri()+"/tipoviOsiguranja/zaOsiguravajucuKucu/"+osiguravajucaKucaId, List.class);
	}
	
}
