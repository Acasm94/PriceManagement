package com.sep.pricemanagement.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
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
			Klijent klijent = restTemplate.getForObject(databaseUri.getDatabaseUri() + "/klijenti/getKlijentForUplata?uplataId=" + uplata.getId(), Klijent.class);
			OsiguravajucaKuca ok = restTemplate.getForObject(databaseUri.getDatabaseUri() + "/osiguravajuceKuce/" + osiguravajucaKucaId, OsiguravajucaKuca.class);
			String htmlInfo = getHtml(klijent, ok, uplata);
			File izvestaj = createIzvestaj(klijent, uplata);
			try {
				zaposleniEmail = restTemplate.getForObject(authorizationProviderUri.getAuthorizationProviderUri() + "/users/getContactInfoForNotification?" + grupe, List.class);
				zaposleniEmail.forEach(email -> {
					try {
						sendEmail(notificationMailConfig.getLocalJavaMailSender(), izvestaj, htmlInfo, email);
					} catch (Exception e) {
						e.printStackTrace();
					}
					});
				sendEmail(notificationMailConfig.getGoogleJavaMailSender(), izvestaj, htmlInfo, klijent.getEmail());
			} catch(Exception mailexception) {
				sendEmail(notificationMailConfig.getGoogleJavaMailSender(), izvestaj, htmlInfo, klijent.getEmail());
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}


	@Override
	public void sendEmail(JavaMailSender mailSenderInterface, File attachment, String html, String recipient) {
		
		JavaMailSenderImpl mailSender = (JavaMailSenderImpl) mailSenderInterface;
		MimeMessage message = notificationMailConfig.getGoogleJavaMailSender().createMimeMessage();
		MimeMessageHelper helper;
		try {
			File notificationBody = new ClassPathResource("/html/notification.html").getFile();
			
	        StringWriter writer = new StringWriter();
	        FileInputStream inputStream = new FileInputStream(notificationBody) ;
	        IOUtils.copy(inputStream, writer, "utf-8");
	        String notificationBodyHtml = writer.toString();
	        notificationBodyHtml = notificationBodyHtml.replaceAll("<br class=\"dynamicContent\"/>", html);
	        
	        System.out.println(notificationBodyHtml);
	        
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(mailSender.getUsername());
			helper.setTo(recipient);
	        helper.setSubject("Uspesna uplata osiguranja");
	        helper.setText(notificationBodyHtml, true);
	        helper.addAttachment("Izvestaj" , attachment);
	        mailSender.send(message);
		} catch (Exception e) {
	        try {
			helper = new MimeMessageHelper(message, true);
			helper.setTo(recipient);
			helper.setFrom(mailSender.getUsername());
	        helper.setSubject("Uspesna uplata osiguranja");
	        helper.addAttachment("Izvestaj" , attachment);
			helper.setText(html, true);
			mailSender.send(message);
			} catch (MessagingException me) {
				me.printStackTrace();
			}
		} 
	}
	
	private String getHtml(Klijent klijent, OsiguravajucaKuca ok, Uplata uplata) {
		String html = "<div><h1>Izvestaj o izvrsenoj uplati za kupljeno osiguranje</h1><div>Ime kupca:" + klijent.getIme() +"</div>"
		+ "<div>Prezime kupca: " + klijent.getPrezime() + "</div>"
		+ "<div>Adresa stanovanja: " + klijent.getAdresa() +"</div>"
		+ "<div>Kontakt telefon: " + klijent.getBrojTelefona() + "</div>"
		+ "<div>Iznos uplate: " + uplata.getOsiguranje().getIznos() + "</div>"
		+ "<div><h1>" + ok.getNaziv() + "</h1>"
		+ "<div>" + ok.getAdresa() + "</div>"
		+ "<div>" + ok.getTelefon() +"</div>"
		+ "<div>" + ok.getMesto()+ "</div>"
		+ "</div>";
		return html;
	}

	@Override
	public File createIzvestaj(Klijent klijent, Uplata uplata) {
		File fileMultipart = new File(System.getProperty("user.home") + "/Downloads/Izvestaj - " + uplata.getId() + " - " + klijent.getIme().concat(" ").concat(klijent.getPrezime()) + ".pdf");
		
		restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());    
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<byte[]> response = restTemplate.exchange(databaseUri.getDatabaseUri() + "/uplate/getIzvestaj/" + uplata.getId(), HttpMethod.GET, entity, byte[].class);

		if(response.getStatusCode().equals(HttpStatus.OK))
		{       
            FileOutputStream output;
			try {
				output = new FileOutputStream(fileMultipart);
				IOUtils.write(response.getBody(), output);
	            output.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileMultipart;
	}
}
