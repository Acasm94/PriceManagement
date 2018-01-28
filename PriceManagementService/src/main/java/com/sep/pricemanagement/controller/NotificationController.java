package com.sep.pricemanagement.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sep.pricemanagement.model.notification.Uplata;
import com.sep.pricemanagement.services.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	
	private final NotificationService notificatioNService;
	
	public NotificationController(NotificationService notificationService) {
		this.notificatioNService = notificationService;
	}
	
	
	@PostMapping("/notifyParties")
	public Boolean notifyParties(@RequestBody Uplata uplata) {
		notificatioNService.notifyParties(uplata);		
		return false;
	}
}
