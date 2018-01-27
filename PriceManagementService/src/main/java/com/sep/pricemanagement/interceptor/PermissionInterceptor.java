package com.sep.pricemanagement.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sep.pricemanagement.model.user.Permission;
import com.sep.pricemanagement.services.UserRolesService;

@Component
@EnableMBeanExport
@EnableConfigurationProperties
public class PermissionInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserRolesService userRolesService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = (Method) handlerMethod.getMethod();
		if (method.isAnnotationPresent(Permission.class)) {
			String permission = method.getAnnotation(Permission.class).permissionName();

			KeycloakAuthenticationToken kat = (KeycloakAuthenticationToken) request.getUserPrincipal();
			
			for(String s : kat.getAccount().getRoles()){
				ArrayList<String> lista = userRolesService.getPrivilegesForRole(s.replaceFirst("ROLE_", ""));
				if(lista.contains(permission)){
					return true;
				}
			}
			
			response.sendError(401, "Unauthorized request");
			return true;
		}
		return true;
	}

}