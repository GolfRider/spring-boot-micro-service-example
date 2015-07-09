package com.spring.boot.micro.services.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.spring.boot.micro.services.example.config.provider.ServiceConfig;
import com.spring.boot.micro.services.example.config.provider.ServiceConfigDefinition;

@Configuration
public class JAXBConfig {
	
	@Bean
	public Jaxb2Marshaller getJaxb2Marshaller(){
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(ServiceConfig.class);
		jaxb2Marshaller.setClassesToBeBound(ServiceConfigDefinition.class);
		return jaxb2Marshaller;
	}

}
