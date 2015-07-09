package com.spring.boot.micro.services.example.config.provider;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ServiceConfig")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceConfig {
	
	@XmlElement(name="name")
	private String name;
	
	@XmlElement(name="query-id")
	private String queryId;
	
	@XmlElement(name="model")
	private String model;
	
	private Class<?> modelClass;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getQueryId() {
		return queryId;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setQueryId(String query) {
		this.queryId = query;
	}
	
	public Class<?> getModelClass() {
		return modelClass;
	}
	public void setModelClass(Class<?> modelClass) {
		this.modelClass = modelClass;
	}
	
}
