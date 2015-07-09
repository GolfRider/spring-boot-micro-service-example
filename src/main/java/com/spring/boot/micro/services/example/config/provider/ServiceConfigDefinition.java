package com.spring.boot.micro.services.example.config.provider;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ServiceConfigDefinition")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceConfigDefinition {
	
	@XmlElement(name="ServiceConfig")
	private List<ServiceConfig> serviceConfigList;

	public List<ServiceConfig> getServiceConfigList() {
		return serviceConfigList;
	}

	public void setServiceConfigList(List<ServiceConfig> serviceConfigList) {
		this.serviceConfigList = serviceConfigList;
	}
	
	
}
