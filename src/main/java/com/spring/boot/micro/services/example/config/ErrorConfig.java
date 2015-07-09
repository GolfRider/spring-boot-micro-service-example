package com.spring.boot.micro.services.example.config;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="error")
public class ErrorConfig {
	
	@NotBlank
	private String serviceSbCode;
	
	@NotBlank
	private String serviceSbMessage;
	
	@NotBlank
	private String daoSbCode;
	
	@NotBlank
	private String daoSbMessage;

	public String getServiceSbCode() {
		return serviceSbCode;
	}

	public void setServiceSbCode(String serviceSbCode) {
		this.serviceSbCode = serviceSbCode;
	}

	public String getServiceSbMessage() {
		return serviceSbMessage;
	}

	public void setServiceSbMessage(String serviceSbMessage) {
		this.serviceSbMessage = serviceSbMessage;
	}

	public String getDaoSbCode() {
		return daoSbCode;
	}

	public void setDaoSbCode(String daoSbCode) {
		this.daoSbCode = daoSbCode;
	}

	public String getDaoSbMessage() {
		return daoSbMessage;
	}

	public void setDaoSbMessage(String daoSbMessage) {
		this.daoSbMessage = daoSbMessage;
	}
	
	
}
