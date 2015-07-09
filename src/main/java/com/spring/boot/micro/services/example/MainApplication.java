package com.spring.boot.micro.services.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@SpringBootApplication
@PropertySources({
	@PropertySource("classpath:sql-config.properties"),
	@PropertySource("classpath:error-config.properties"),
	@PropertySource("classpath:message-config.properties"),
	@PropertySource("classpath:validation-config.properties")
	
})
public class MainApplication {
	protected static final Logger log = LoggerFactory.getLogger(MainApplication.class);
	
	public static void main(String args[]){
		SpringApplication.run(MainApplication.class, args);
		log.info("Spring Micro Services Started");
	}
}
