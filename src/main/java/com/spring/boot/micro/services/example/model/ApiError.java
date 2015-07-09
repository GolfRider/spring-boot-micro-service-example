package com.spring.boot.micro.services.example.model;

public class ApiError {

	private long timeStamp;
	private String path;
	private String errorCode;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	private ApiError(){}
	
	public static ApiError getInstance(){
		return new ApiError();
	}
	
	
}
