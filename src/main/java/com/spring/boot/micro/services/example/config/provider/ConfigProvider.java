package com.spring.boot.micro.services.example.config.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.boot.micro.services.example.commons.Constants.ConfigType;
import com.spring.boot.micro.services.example.config.ErrorConfig;
import com.spring.boot.micro.services.example.config.MessageConfig;
import com.spring.boot.micro.services.example.config.SQLConfig;
import com.spring.boot.micro.services.example.config.ValidationConfig;

@Component
public class ConfigProvider {
	
	@Autowired
	private SQLConfig sqlConfig;
	
	@Autowired
	private ErrorConfig errorConfig;
	
	@Autowired
	private ValidationConfig validationConfig;
	
	@Autowired
	private MessageConfig messageConfig;
	
	
	@SuppressWarnings("unchecked")
	public <T> T getProvider(ConfigType configType) {
		switch(configType){
			case ValidationConfig: return (T) validationConfig;
			case MessageConfig: return (T)messageConfig;
			case SQLConfig: return (T)sqlConfig;
			case ErrorConfig: return (T)errorConfig;
			default : return null;
		}
		
	}


}
