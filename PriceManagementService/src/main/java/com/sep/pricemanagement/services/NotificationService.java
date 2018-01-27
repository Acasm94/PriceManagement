package com.sep.pricemanagement.services;

import com.sep.pricemanagement.model.notification.Uplata;

public interface NotificationService {
	
	boolean notifyParties(Uplata uplata);
	
}
