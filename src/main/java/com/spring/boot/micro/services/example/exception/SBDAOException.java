package com.spring.boot.micro.services.example.exception;

public class SBDAOException extends SBException{

	private static final long serialVersionUID = 1L;
	
	public SBDAOException(String errorCode, String message, Exception cause) {
		super(errorCode, message, cause);
		
	}	
}
