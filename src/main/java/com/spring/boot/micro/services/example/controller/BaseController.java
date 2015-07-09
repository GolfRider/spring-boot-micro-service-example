package com.spring.boot.micro.services.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.boot.micro.services.example.exception.SBException;
import com.spring.boot.micro.services.example.exception.SBValidationException;
import com.spring.boot.micro.services.example.model.ApiError;
import com.spring.boot.micro.services.example.service.SBService;


public abstract class BaseController {
	
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected SBService sbService;

	
   //Global Exception Handler Class 
	@ExceptionHandler({SBException.class})
	public ApiError baseExceptionHandler(SBException ex,HttpServletRequest req,HttpServletResponse res){
		log.error("Base Controller:"+req.getRequestURI());
			ApiError apiErrorResponse= ApiError.getInstance();
			apiErrorResponse.setTimeStamp(System.currentTimeMillis());
			apiErrorResponse.setPath(req.getRequestURI());
			apiErrorResponse.setErrorCode(ex.getErrorCode());
			apiErrorResponse.setMessage(ex.getMessage());
			res.setStatus(HttpStatus.BAD_REQUEST.value());
		return apiErrorResponse;
	}
	
	@ExceptionHandler({SBValidationException.class})
	public ApiError baseSBValidationExceptionHandler(SBValidationException ex,HttpServletRequest req,HttpServletResponse res){
		log.error("Base Controller:"+req.getRequestURI());
			ApiError apiErrorResponse= ApiError.getInstance();
			apiErrorResponse.setTimeStamp(System.currentTimeMillis());
			apiErrorResponse.setPath(req.getRequestURI());
			apiErrorResponse.setErrorCode(ex.getErrorCode());
			apiErrorResponse.setMessage(ex.getMessage());
			if(ex.getValidationErrorList().size()>0){
				apiErrorResponse.setMessage(ex.getValidationErrorList().toString());
			}
			res.setStatus(HttpStatus.BAD_REQUEST.value());
		return apiErrorResponse;
	}

}
