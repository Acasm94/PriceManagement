package com.sep.pricemanagement.services;

import java.io.IOException;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sep.pricemanagement.config.DatabaseUri;
import com.sep.pricemanagement.model.Cenovnik;
import com.sep.pricemanagement.model.KalkulatorCene;
import com.sep.pricemanagement.model.StavkaCenovnika;
import com.sep.pricemanagement.model.VrednostAtributaOsiguranja;

@Service
public class JBossDroolsService {

	private final KieContainer kieContainer;
	
	@Value("${spring.data.company}")
	private String osiguravajucaKucaId;
	
	double ukupnaCena = 0;
	List<StavkaCenovnika> stavkeCenovnikaArr = null;
	
	@Autowired
	private DatabaseUri databaseUri;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	public JBossDroolsService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}
	
	public void calculatePrice(List<VrednostAtributaOsiguranja> vrednostiAtributaOsiguranja) 
	{
		KieSession kieSession = kieContainer.newKieSession();
		
		ukupnaCena = 0;
		stavkeCenovnikaArr = null;
		
		ObjectMapper mapper = new ObjectMapper();
			
		Cenovnik cenovnik = restTemplate.getForObject(databaseUri.getDatabaseUri()+"/cenovnici/aktuelan/zaOsiguravajucuKucu/" + osiguravajucaKucaId, Cenovnik.class);
		JsonNode  stavkeCenovnika = (JsonNode) restTemplate.getForObject(databaseUri.getDatabaseUri() + "/stavkeCenovnika/zaCenovnik/" + cenovnik.getId(), JsonNode.class);
		
		try {
			stavkeCenovnikaArr = mapper.readValue(
				    mapper.treeAsTokens(stavkeCenovnika), 
				    new TypeReference<List<StavkaCenovnika>>(){}
				);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		vrednostiAtributaOsiguranja.forEach(vrednostAO -> {
			if(stavkeCenovnikaArr.stream().anyMatch(pv -> pv.getPredefinisanaVrednost().getKonkretnaVrednost().equals(vrednostAO.getVrednost()))) 
			{
				StavkaCenovnika stc = stavkeCenovnikaArr.stream().filter(pv -> pv.getPredefinisanaVrednost().getKonkretnaVrednost().equals(vrednostAO.getVrednost())).findFirst().orElse(null);				
				ukupnaCena += stc != null ? stc.getSuma() : 0;
			}			
		});
		
		if(vrednostiAtributaOsiguranja.stream().anyMatch(vr -> vr.getVrednost().equals("Broj osoba")))
		{
			VrednostAtributaOsiguranja vrAtr = vrednostiAtributaOsiguranja.stream().filter(vr -> vr.getTipAtributa().getVrednostiAtributa().equals("Broj osoba")).findFirst().orElse(null);
			ukupnaCena *= vrAtr != null ? Integer.parseInt(vrAtr.getVrednost()) : 1;
			
			//ukupnaCena *= vrednostiAtributaOsiguranja != null ? 3 : 1;
		}
		
		KalkulatorCene kalkulatorCene = new KalkulatorCene(vrednostiAtributaOsiguranja, ukupnaCena);
				
		kieSession.insert(kalkulatorCene);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(kalkulatorCene.getCena());	
	}
}
