package com.spring.boot.micro.services.example.controller.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.boot.micro.services.example.exception.SBException;
import com.spring.boot.micro.services.example.exception.SBValidationException;

import static com.spring.boot.micro.services.example.commons.CommonUtils.isNotEmpty;

public class ApiFilterValidator {
	
	private final String val;
	private final List<String> validationMessageList= new ArrayList<String>();
	private final static String INTEGER_REGEX="^-?\\d+$";
	
	public ApiFilterValidator(String val){
		this.val=val;
	}
	
		
	public List<String> getValidationErrors(){
		return validationMessageList;
	}

	public ApiFilterValidator checkLength(int checkLength){
		if(isNotEmpty(val)){
			if(val.length() != checkLength){
				validationMessageList.add("Length Validation failed for : "+val);
			}		
		}
		return this;
	}
	
	public ApiFilterValidator checkInteger(){
		if(isNotEmpty(val)){
			Pattern pattern=Pattern.compile(INTEGER_REGEX);
			Matcher matcher=pattern.matcher(val);
			if(!matcher.find()){
				validationMessageList.add("Not an Integer for : "+val);
			}	
		}
		return this;
	}
	
	//TODO 1. Validation Message, Message Externalization
	public static void checkFinalValidation(ApiFilterValidator ...validators) throws SBException{
		List<String> validationErrorList= new ArrayList<String>();
		for(ApiFilterValidator validator: validators){
			validationErrorList.addAll(validator.getValidationErrors());
		}
		
		if(validationErrorList.size()>0){
			throw new SBValidationException("Invalid Input ", validationErrorList);
		}
	}
	 
}
