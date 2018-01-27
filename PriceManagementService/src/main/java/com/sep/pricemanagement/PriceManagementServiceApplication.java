package com.sep.pricemanagement;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.sep.pricemanagement.model.notification.Osiguranje;
import com.sep.pricemanagement.model.notification.StatusUplate;
import com.sep.pricemanagement.model.notification.Uplata;
import com.sep.pricemanagement.services.NotificationService;

@SpringBootApplication
public class PriceManagementServiceApplication {
	/*
	@Autowired
	NotificationService notificationService;
	*/
	
	public static void main(String[] args) {
		SpringApplication.run(PriceManagementServiceApplication.class, args);
	}
	
	/*
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
	            System.out.println("Let's inspect the beans provided by Spring Boot:");
	            String[] beanNames = ctx.getBeanDefinitionNames();
	            Arrays.sort(beanNames);
	            for (String beanName : beanNames) {
	                System.out.println(beanName);
	            }
	            
	            Uplata uplata = new Uplata();
	            uplata.setId(1L);
	            Osiguranje osiguranje = new Osiguranje();
	            osiguranje.setId(1L);
	            uplata.setOsiguranje(osiguranje);
	            uplata.setTrgovacId("pera");
	            uplata.setStatus(StatusUplate.UPLACENO);
	            uplata.setIznos(400);
	            notificationService.notifyParties(uplata);
	        };
	        
    }
	*/
	
	/*
	@Bean
    public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("com.sep","PriceManagementJBD", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10000);
		return kContainer;
    }
    */
}
