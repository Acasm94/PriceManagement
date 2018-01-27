package com.sep.pricemanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationProviderUri {

	@Value("${spring.data.authorizationProvider}")
	private String authorizationProviderUri;

	@Value("${spring.data.notificationGroups}")
	private String notificationGroups;
	
	public String getAuthorizationProviderUri() {
		return authorizationProviderUri;
	}

	public void setAuthorizationProviderUri(String authorizationProviderUri) {
		this.authorizationProviderUri = authorizationProviderUri;
	}
	
	public String getNotificationGroups() {
		return notificationGroups;
	}

	public void setNotificationGroups(String notificationGroups) {
		this.notificationGroups = notificationGroups;
	}
	
}
