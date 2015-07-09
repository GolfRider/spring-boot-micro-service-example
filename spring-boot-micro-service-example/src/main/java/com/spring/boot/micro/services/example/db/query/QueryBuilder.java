package com.spring.boot.micro.services.example.db.query;

import static com.spring.boot.micro.services.example.commons.Constants.BLANK_SPACE;

import java.util.Map;
import java.util.Map.Entry;

public class QueryBuilder {
	
	public static String prepareSQL(String baseQuery, Map<String,String> sqlParamMap){
		StringBuilder sqlBuilder = new StringBuilder();
		if(sqlParamMap.size()>0){
			sqlBuilder.append(baseQuery);
		}
		
		for(Entry<String, String> entry:sqlParamMap.entrySet()){
			if(sqlBuilder.indexOf("=:")!=-1){
				sqlBuilder.append(BLANK_SPACE);
				sqlBuilder.append("AND");
				sqlBuilder.append(BLANK_SPACE);
				sqlBuilder.append(entry.getKey());
				sqlBuilder.append("=:");
				sqlBuilder.append(entry.getKey());
				
			}else{
				sqlBuilder.append(BLANK_SPACE);
				sqlBuilder.append("WHERE");
				sqlBuilder.append(BLANK_SPACE);
				sqlBuilder.append(entry.getKey());
				sqlBuilder.append("=:");
				sqlBuilder.append(entry.getKey());			
			}
		}
		return sqlBuilder.toString();
	}

}
