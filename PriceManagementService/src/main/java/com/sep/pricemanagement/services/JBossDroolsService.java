package com.sep.pricemanagement.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.cli.MavenCli;
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

	//private final KieContainer kieContainer;
	
	@Value("${spring.data.company}")
	private String osiguravajucaKucaId;
	
	@Value("${spring.data.PriceManagementJBDDirectoryPath}")
	private String priceManagementJBDDirectoryPath;
	
	double ukupnaCena = 0;
	List<StavkaCenovnika> stavkeCenovnikaArr = null;
	
	@Autowired
	private DatabaseUri databaseUri;
	
	@Autowired
	private RestTemplate restTemplate;
/*
	@Autowired
	public JBossDroolsService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}
*/	
	public double calculatePrice(List<VrednostAtributaOsiguranja> vrednostiAtributaOsiguranja) 
	{
		//KieSession kieSession = kieContainer.newKieSession();
		
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
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
		
		System.out.println(ukupnaCena);	
		
		KalkulatorCene kalkulatorCene = new KalkulatorCene(vrednostiAtributaOsiguranja, ukupnaCena);
				
		//kieSession.insert(kalkulatorCene);
		//kieSession.fireAllRules();
		//kieSession.dispose();
		
		System.out.println(kalkulatorCene.getCena());
		
		return kalkulatorCene.getCena();
	}
	
	public void kreirajNovoPravilo(String pravilnik)
	{
		PrintWriter writer;
		try {
			writer = new PrintWriter(priceManagementJBDDirectoryPath + "\\src\\main\\resources\\drools\\rules\\novoPravilo.drl", "UTF-8");
			writer.println(pravilnik);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		MavenCli cli = new MavenCli();
		cli.doMain(new String[]{"clean", "install"}, priceManagementJBDDirectoryPath, System.out, System.out);
	}
	
	public String getSadrzajPravila(String nazivFajla)
	{
		String sadrzajFajla = "";		
		BufferedReader br = null;

		try {

			br = new BufferedReader(new FileReader(priceManagementJBDDirectoryPath + "\\src\\main\\resources\\drools\\rules\\" + nazivFajla + ".drl"));

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				sadrzajFajla += sCurrentLine + "\n";
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}	
		return sadrzajFajla;
	}
	
	public List<String> getListuFajlova()
	{
		List<String> listaFajlova = new ArrayList<String>();
		
		File folder = new File(priceManagementJBDDirectoryPath + "\\src\\main\\resources\\drools\\rules\\");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		    if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getName());
		        listaFajlova.add(listOfFiles[i].getName());
		    }
		}
		 
		 return listaFajlova;
	}
	
	public void sacuvajPravilo(String sadrzajPravila, String nazivFajla) 
	{
		PrintWriter writer;
		try {
			writer = new PrintWriter(priceManagementJBDDirectoryPath + "\\src\\main\\resources\\drools\\rules\\" + nazivFajla + ".drl", "UTF-8");
			writer.println(sadrzajPravila);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		MavenCli cli = new MavenCli();
		cli.doMain(new String[]{"clean", "install"}, priceManagementJBDDirectoryPath, System.out, System.out);
	}
}
