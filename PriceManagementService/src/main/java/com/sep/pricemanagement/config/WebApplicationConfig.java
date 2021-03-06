package com.sep.pricemanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sep.pricemanagement.interceptor.PermissionInterceptor;

@Component
@ComponentScan(basePackages = "com")
public class WebApplicationConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private PermissionInterceptor pi;
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(pi)
				.addPathPatterns("/**");
	}

	@Bean
	public PermissionInterceptor interceptor() {
		return new PermissionInterceptor();
	}
	
	/*
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(createUserDetailsResolver());
	}
	
	@Bean
	public UserDetailsArgumentResolver createUserDetailsResolver(){
		return new UserDetailsArgumentResolver();
	}
	 */
/*
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("/webjars/");
	}
*/
}
