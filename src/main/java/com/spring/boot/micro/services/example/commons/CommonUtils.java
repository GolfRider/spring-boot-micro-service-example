package com.spring.boot.micro.services.example.commons;

import java.text.MessageFormat;

public class CommonUtils {
	
	public static boolean isNotEmpty(String val){
		if(val!=null && val.trim().length()>0){
		   return true;	
		}else{
		   return false;
		}
		
	}
	
	public static String getMessage(String parameterizedMessage,String[] params){
		MessageFormat messageFormat = new MessageFormat(parameterizedMessage);
		return messageFormat.format(params);				
	}

}
