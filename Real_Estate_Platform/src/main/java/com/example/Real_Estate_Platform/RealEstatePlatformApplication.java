package com.example.Real_Estate_Platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class RealEstatePlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealEstatePlatformApplication.class, args);
	}
	@Bean(name = "messageSource")
	public ResourceBundleMessageSource resourceBundleMessageSource(){
		ResourceBundleMessageSource resourceBundleMessage=new ResourceBundleMessageSource();
		resourceBundleMessage.setBasename("message");
		return resourceBundleMessage;
	}

}
