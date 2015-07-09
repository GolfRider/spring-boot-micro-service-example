package com.spring.boot.micro.services.example.db.mapper;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class DBMapper {

	public static Map<String,String> getRequestParamDBMap(Object obj){
		
	 Map<String,String> requestParamDBMap= new LinkedHashMap<String,String>();
	 
	 for(Field field:obj.getClass().getDeclaredFields()){
			DBColumn dbColumn = field.getAnnotation(DBColumn.class);
			if(dbColumn!=null){
				try {   
					    field.setAccessible(true);
						String fieldValue = (String) field.get(obj);
						if(fieldValue!=null){
							requestParamDBMap.put(dbColumn.name(), fieldValue);
						}
					
				}catch(Exception ex){
					   ex.printStackTrace();
				}
			}
			
		}
		return requestParamDBMap;		
	}
	
}
