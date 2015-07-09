package com.spring.boot.micro.services.example.exception;

public class SBException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorCode;
	
	public SBException(String errorCode,String message,Throwable cause){
		super(message, cause);
		this.errorCode=errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
}
