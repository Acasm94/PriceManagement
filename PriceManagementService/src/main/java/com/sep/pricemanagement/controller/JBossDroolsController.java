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
	
	/*@PostMapping("/izracunajCenu")
	@ResponseBody
	public Double createOsiguranje(@RequestBody List<VrednostAtributaOsiguranja> vrednostiAtributaOsiguranja) {
		
		jBossDroolsService.calculatePrice(vrednostiAtributaOsiguranja);
		
		List<VrednostAtributaOsiguranja> vrednostiAtributaOsiguranja = new ArrayList<VrednostAtributaOsiguranja>();
		VrednostAtributaOsiguranja vra1 = new VrednostAtributaOsiguranja();
		vra1.setVrednost("Preko 60 godina");
		VrednostAtributaOsiguranja vra2 = new VrednostAtributaOsiguranja();
		vra2.setVrednost("Australija");
		VrednostAtributaOsiguranja vra4 = new VrednostAtributaOsiguranja();
		vra4.setVrednost("30.000 EUR");
		VrednostAtributaOsiguranja vra3 = new VrednostAtributaOsiguranja();
		VrednostAtributaOsiguranja vao = new VrednostAtributaOsiguranja();
		vao.setVrednost("3");
		TipAtributa tp = new TipAtributa();
		List<VrednostAtributaOsiguranja> liVAO = new ArrayList<VrednostAtributaOsiguranja>();
		liVAO.add(vao);
		tp.setVrednostiAtributa(liVAO);
		vra3.setTipAtributa(tp);
		vra3.setVrednost("Broj osoba");
		vrednostiAtributaOsiguranja.add(vra1);
		vrednostiAtributaOsiguranja.add(vra2);
		vrednostiAtributaOsiguranja.add(vra3);
		vrednostiAtributaOsiguranja.add(vra4);
		
		return null;		
	}*/
	
	@RequestMapping(value = "/izracunajCenu", method = RequestMethod.GET, produces = "application/json")
	//@Permission(permissionName = "createOsiguranje")
	public Double createOsiguranje(@RequestBody List<VrednostAtributaOsiguranja> vrednostiAtributaOsiguranja) {
		return jBossDroolsService.calculatePrice(vrednostiAtributaOsiguranja);	
	}
	
	@GetMapping("/secured/kreirajPravilo")
	@ResponseBody
	@Permission(permissionName = "setAktuelanCenovnikZaOsiguravajucuKucu")
	public void setAktuelanCenovnikZaOsiguravajucuKucu(@RequestBody String pravilnik) {
		jBossDroolsService.kreirajNovoPravilo(pravilnik);	
	}
	
	@GetMapping("/secured/getFajlove")
	@ResponseBody
	@Permission(permissionName = "getFajlove")
	public List<String> getFajlove() {
		return jBossDroolsService.getListuFajlova();
	}

	@GetMapping("/secured/getSadrzajPravila/{nazivFajla}")
	@ResponseBody
	@Permission(permissionName = "getSadrzajPravila")
	public String getSadrzajPravila(@PathVariable("nazivFajla") String nazivFajla) {	
		return jBossDroolsService.getSadrzajPravila(nazivFajla);
	}
	
	@PostMapping("/secured/sacuvajPravilo/{imeFajla}")
	@ResponseBody
	@Permission(permissionName = "sacuvajPravilo")
	public void sacuvajPravilo(@PathVariable("imeFajla") String imeFajla, @RequestBody String sadrzajPravilnika) {
		jBossDroolsService.sacuvajPravilo(sadrzajPravilnika, imeFajla);
	}

}
