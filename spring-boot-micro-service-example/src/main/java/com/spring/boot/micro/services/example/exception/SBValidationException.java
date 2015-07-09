package com.spring.boot.micro.services.example.exception;

import java.util.List;

public class SBValidationException extends SBException{

	private static final long serialVersionUID = 1L;
	private List<String> validationErrorList;

	public SBValidationException(String errorCode,List<String> validationErrorList){
        super(errorCode,"Invalid Input", null);
        this.validationErrorList=validationErrorList;		
	}
	
	public List<String> getValidationErrorList() {
		return validationErrorList;
	}
	
}
