package com.spring.boot.micro.services.example.service;

import java.util.List;

import com.spring.boot.micro.services.example.exception.SBException;

public interface SBService {
	
	public List<?> getData(String serviceName,Object sbDTO) throws SBException;

}
