package com.spring.boot.micro.services.example.db.mapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
/**
 * 
 * Some Code has been adapted from : http://oprsteny.com/?p=900
 * 
 */
public class ResultSetMapper<T> implements ResultSetExtractor<T>{
	
	final Class<T> modelClazz; 
	private List<T> resultList=null;
	
      private void setProperty(Object clazz, String fieldName, Object columnValue) {
        try {
          Field field = clazz.getClass().getDeclaredField(fieldName);
          field.setAccessible(true);
          field.set(clazz, columnValue.toString());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
     
      @SuppressWarnings({ "unchecked", "rawtypes" })
      public List<T> mapRersultSetToObject(ResultSet rs, Class clazz) {
        List<T> outputList = null;
        try {
          // make sure resultset is not null
          if (rs != null) {
     
              ResultSetMetaData rsmd = rs.getMetaData();
              Field[] fields = clazz.getDeclaredFields();
     
              while (rs.next()) {
                T bean = (T) clazz.newInstance();
                for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
                  String columnName = rsmd.getColumnName(_iterator + 1);
                  Object columnValue = rs.getObject(_iterator + 1);
                  for (Field field : fields) {
                    if (field.isAnnotationPresent(DBColumn.class)) {
                      DBColumn column = field.getAnnotation(DBColumn.class);
                      if (column.name().equalsIgnoreCase(columnName) 
                          && columnValue != null) {
                        this.setProperty(bean, field.getName(), columnValue);
                        break;
                      }
                    }
                  } // EndOf for(Field field : fields)
                } // EndOf for(_iterator...)
                if (outputList == null) {
                  outputList = new ArrayList<T>();
                }
                outputList.add(bean);
              } // EndOf while(rs.next())
            
          } else {
            // ResultSet is empty
            return null;
          }
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (SQLException e) {
          e.printStackTrace();
        } catch (InstantiationException e) {
          e.printStackTrace();
        }
     
        return outputList;
      }

      
    public ResultSetMapper(Class<T> clazz) {
    	this.modelClazz=clazz;		
	}  
    
	@Override
	public T extractData(ResultSet rs) throws SQLException,
			DataAccessException {
	    resultList= this.mapRersultSetToObject(rs, modelClazz);
		return null;
	}
	
	public List<T> getResult(){
		return resultList;
	}
	
	
    }