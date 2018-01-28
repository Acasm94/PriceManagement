package com.sep.pricemanagement.services;


import java.io.File;


import org.springframework.mail.javamail.JavaMailSender;

import com.sep.pricemanagement.model.Klijent;
import com.sep.pricemanagement.model.notification.Uplata;

public interface NotificationService {
	
	boolean notifyParties(Uplata uplata);
	void sendEmail(JavaMailSender mailSenderInterface, File attachment, String html, String recipient);
	File createIzvestaj(Klijent klijent, Uplata uplata);
}
