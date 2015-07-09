package com.spring.boot.micro.services.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.spring.boot.micro.services.example.exception.SBException;


public interface SBController {
	
	public ResponseEntity<List<?>> getData(String regionCode,String code) throws SBException;


}
