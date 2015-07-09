package com.spring.boot.micro.services.example.model;

import com.spring.boot.micro.services.example.db.mapper.DBColumn;


public class SBModel {

	@DBColumn(name ="PRODUCT_NAME")
	private String name;
	
	@DBColumn(name ="CODE")
	private String code;
	
	@DBColumn(name ="REGION_CODE")
	private String region;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
}
