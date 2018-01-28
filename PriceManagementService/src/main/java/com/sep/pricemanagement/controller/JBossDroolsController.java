package com.sep.pricemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sep.pricemanagement.model.VrednostAtributaOsiguranja;
import com.sep.pricemanagement.model.user.Permission;
import com.sep.pricemanagement.services.JBossDroolsService;

@RestController
@RequestMapping("/apii/jboosdrools")
public class JBossDroolsController {
	
	private final JBossDroolsService jBossDroolsService;
	
	@Autowired
    public JBossDroolsController(JBossDroolsService jBossDroolsService) {
        this.jBossDroolsService = jBossDroolsService;
    }
	
	@RequestMapping(value = "/izracunajCenu", method = RequestMethod.POST, produces = "application/json")
	//@Permission(permissionName = "createOsiguranje")
	public Double createOsiguranje(@RequestBody List<VrednostAtributaOsiguranja> vrednostiAtributaOsiguranja) {
		return jBossDroolsService.calculatePrice(vrednostiAtributaOsiguranja);	
	}
	
	@GetMapping("/api/kreirajPravilo")
	@ResponseBody
	@Permission(permissionName = "setAktuelanCenovnikZaOsiguravajucuKucu")
	public void setAktuelanCenovnikZaOsiguravajucuKucu(@RequestBody String pravilnik) {
		jBossDroolsService.kreirajNovoPravilo(pravilnik);	
	}
	
	@GetMapping("/api/getFajlove")
	@ResponseBody
	@Permission(permissionName = "getFajlove")
	public List<String> getFajlove() {
		return jBossDroolsService.getListuFajlova();
	}

	@GetMapping("/api/getSadrzajPravila/{nazivFajla}")
	@ResponseBody
	@Permission(permissionName = "getSadrzajPravila")
	public String getSadrzajPravila(@PathVariable("nazivFajla") String nazivFajla) {	
		return jBossDroolsService.getSadrzajPravila(nazivFajla);
	}
	
	@PostMapping("/api/sacuvajPravilo/{imeFajla}")
	@ResponseBody
	@Permission(permissionName = "sacuvajPravilo")
	public void sacuvajPravilo(@PathVariable("imeFajla") String imeFajla, @RequestBody String sadrzajPravilnika) {
		jBossDroolsService.sacuvajPravilo(sadrzajPravilnika, imeFajla);
	}

}
