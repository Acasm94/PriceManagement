package com.sep.pricemanagement.notification;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class NotificationMailConfig {
	
	@Bean
	public JavaMailSender getGoogleJavaMailSender() {
	    JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
	    mailSenderImpl.setHost("smtp.gmail.com");
	    mailSenderImpl.setPort(587);
	     
	    mailSenderImpl.setUsername("insurancesep@gmail.com");
	    mailSenderImpl.setPassword("geN1S9!8la");
	     
	    Properties props = mailSenderImpl.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	     
	    JavaMailSender mailSender = mailSenderImpl;
	    
	    return mailSender;
	}
	
	@Bean
	public JavaMailSender getLocalJavaMailSender() {
	    JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
	    
	    mailSenderImpl.setHost("localhost");
	    mailSenderImpl.setPort(25);
	    
	    mailSenderImpl.setUsername("insurancesep@uniqa.com");
	    mailSenderImpl.setPassword("geN1S9!8la");
	     
	    Properties props = mailSenderImpl.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.debug", "true");
	    
	    props.put("mail.imap.host", "localhost");
	    props.put("mail.imap.port", 143);
	     
	    JavaMailSender mailSender = mailSenderImpl;
	    
	    return mailSender;
	}
}
