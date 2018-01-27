package com.sep.pricemanagement.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sep.pricemanagement.config.AuthorizationProviderUri;
import com.sep.pricemanagement.config.DatabaseUri;
import com.sep.pricemanagement.model.Klijent;
import com.sep.pricemanagement.model.OsiguravajucaKuca;
import com.sep.pricemanagement.model.notification.Uplata;
import com.sep.pricemanagement.notification.NotificationMailConfig;
import com.sep.pricemanagement.services.NotificationService;


@Service
@SuppressWarnings("unchecked")
public class NotificationServiceImpl implements NotificationService{
	
	
	@Value("${spring.data.company}")
	private String osiguravajucaKucaId;
	
	private final RestTemplate restTemplate;
	private final AuthorizationProviderUri authorizationProviderUri;
	private final NotificationMailConfig notificationMailConfig;
	private final DatabaseUri databaseUri;
	
	public NotificationServiceImpl(RestTemplate restTemplate, AuthorizationProviderUri authorizationProviderUri, 
			NotificationMailConfig notificationMailConfig, DatabaseUri databaseUri) {
		this.restTemplate = restTemplate;
		this.authorizationProviderUri = authorizationProviderUri;
		this.notificationMailConfig = notificationMailConfig;
		this.databaseUri = databaseUri;
	}
	
	
	@Override
	public boolean notifyParties(Uplata uplata) {
		
		String[] notificationGroups = authorizationProviderUri.getNotificationGroups().split(",");
		
		String grupe = "";
		for(int i = 0;i < notificationGroups.length;i++) {
			if(i == (notificationGroups.length - 1) ) {
				grupe = grupe.concat("roleId=").concat(notificationGroups[i]);
			} else {
				grupe = grupe.concat("roleId=").concat(notificationGroups[i] + "&");
			}
		}
		
		List<String> zaposleniEmail;
		try {
			MimeMessage messageZaposleni = notificationMailConfig.getLocalJavaMailSender().createMimeMessage();
			
			Klijent klijent = restTemplate.getForObject(databaseUri.getDatabaseUri() + "/klijenti/getKlijentForUplata?uplataId=" + uplata.getId(), Klijent.class);
			OsiguravajucaKuca ok = restTemplate.getForObject(databaseUri.getDatabaseUri() + "/osiguravajuceKuce/" + osiguravajucaKucaId, OsiguravajucaKuca.class);
			
			File fileMultipart = new File(System.getProperty("user.home") + "/Downloads/Izvestaj - " + uplata.getId() + " - " + klijent.getIme().concat(" ").concat(klijent.getPrezime()) + ".pdf");
			
			restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());    
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			ResponseEntity<byte[]> response = restTemplate.exchange(databaseUri.getDatabaseUri() + "/uplate/getIzvestaj/" + uplata.getId(), HttpMethod.GET, entity, byte[].class);

			if(response.getStatusCode().equals(HttpStatus.OK))
			{       
                FileOutputStream output = new FileOutputStream(fileMultipart);
                IOUtils.write(response.getBody(), output);
                output.close();
			}
			
			zaposleniEmail = restTemplate.getForObject(authorizationProviderUri.getAuthorizationProviderUri() + "/users/getContactInfoForNotification?" + grupe, List.class);
			
			
			zaposleniEmail.forEach(email -> {
		        MimeMessageHelper helper;
				try {
					helper = new MimeMessageHelper(messageZaposleni, true);
					helper.setTo(email);
					helper.setFrom(((JavaMailSenderImpl)notificationMailConfig.getLocalJavaMailSender()).getUsername());
			        helper.setSubject("Uspesna uplata osiguranja");
			        helper.addAttachment("Izvestaj" , fileMultipart);;
			        helper.setText("<html><body><h1>Izvestaj o izvrsenoj uplati za kupljeno osiguranje</h1><div>Ime kupca:" + klijent.getIme() +"</div>"
			        		+ "<div>Prezime kupca:" + klijent.getPrezime() + "</div>"
			        		+ "<div>Adresa stanovanja:" + klijent.getAdresa() +"</div>"
			        		+ "<div>Kontakt telefon:" + klijent.getBrojTelefona() + "</div>"
			        		+ "<div>Iznos uplate:" + uplata.getOsiguranje().getIznos() + "</div>"
			        		+ "<div backgroundImage=cid:Uniqa><h1>" + ok.getNaziv() + "</h1>"
			        		+ "<div>" + ok.getAdresa() + "</div>"
			        		+ "<div>" + ok.getTelefon() +"</div>"
			        		+ "<div>" + ok.getMesto()+ "</div>"
			        		+ "</div></body></html>", true);
			        
			        notificationMailConfig.getLocalJavaMailSender().send(messageZaposleni);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			});
			
			MimeMessage message = notificationMailConfig.getGoogleJavaMailSender().createMimeMessage();
			
			MimeMessageHelper helper;
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(((JavaMailSenderImpl)notificationMailConfig.getGoogleJavaMailSender()).getUsername());
			helper.setTo(klijent.getEmail());
	        helper.setSubject("Uspesna uplata osiguranja");
	        helper.setText("<html><body><h1>Izvestaj o izvrsenoj uplati za kupljeno osiguranje</h1><div>Ime kupca:" + klijent.getIme() +"</div>"
	        		+ "<div>Prezime kupca:" + klijent.getPrezime() + "</div>"
	        		+ "<div>Adresa stanovanja:" + klijent.getAdresa() +"</div>"
	        		+ "<div>Kontakt telefon:" + klijent.getBrojTelefona() + "</div>"
	        		+ "<div>Iznos uplate:" + uplata.getOsiguranje().getIznos() + "</div>"
	        		+ "<div backgroundImage=cid:Uniqa><h1>" + ok.getNaziv() + "</h1>"
	        		+ "<div>" + ok.getAdresa() + "</div>"
	        		+ "<div>" + ok.getTelefon() +"</div>"
	        		+ "<div>" + ok.getMesto()+ "</div>"
	        		+ "</div></body></html>", true);
            
	        helper.addAttachment("Izvestaj" , fileMultipart);;
	        notificationMailConfig.getGoogleJavaMailSender().send(message);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
}
