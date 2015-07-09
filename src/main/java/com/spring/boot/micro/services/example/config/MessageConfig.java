package com.spring.boot.micro.services.example.config;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix="message")
public class MessageConfig {
	
	@NotBlank
	private String query1;

	public String getQuery1() {
		return query1;
	}
	
	public void setQuery1(String query1) {
		this.query1 = query1;
	}

}
