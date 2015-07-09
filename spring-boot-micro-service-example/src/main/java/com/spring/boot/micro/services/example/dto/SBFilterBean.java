package com.spring.boot.micro.services.example.dto;

import com.spring.boot.micro.services.example.db.mapper.DBColumn;

public class SBFilterBean  {
    
	@DBColumn(name="CODE") 	
	private String code;
    
	@DBColumn(name="REGION_CODE")
    private String region;
	
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

