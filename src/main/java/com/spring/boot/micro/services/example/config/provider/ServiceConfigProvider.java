package com.spring.boot.micro.services.example.config.provider;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import com.spring.boot.micro.services.example.commons.Constants;
import com.spring.boot.micro.services.example.commons.Constants.ConfigType;
import com.spring.boot.micro.services.example.config.SQLConfig;

@Component
public class ServiceConfigProvider {

	private static final Map<String,ServiceConfig> serviceConfigMap= new HashMap<String, ServiceConfig>();
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;
	
	private static ConfigProvider configProvider;
	
	@Autowired
    public void setConfigProvider(ConfigProvider configProvider){
        ServiceConfigProvider.configProvider = configProvider;
    }
	
	@PostConstruct
	protected void initFromXML(){
		try {
				JAXBContext jaxbContext = JAXBContext.newInstance(ServiceConfigDefinition.class,ServiceConfig.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				ServiceConfigDefinition serviceDefiConfigDefinition = (ServiceConfigDefinition) jaxbUnmarshaller.unmarshal(getResource(Constants.SERVICE_CONFIG_FILE).getFile());
				for(ServiceConfig serviceConfig:serviceDefiConfigDefinition.getServiceConfigList()){
		    		serviceConfig.setModelClass(Class.forName(serviceConfig.getModel()));
		 	    	serviceConfigMap.put(serviceConfig.getName(), serviceConfig);
				}				
		   } 
		   catch (IOException | JAXBException | ClassNotFoundException e) {
				e.printStackTrace();
		   }
			
	}
	
	
	protected void initFromSpringOXM(){
	   try {
			FileInputStream fis = new FileInputStream(getResource("service-config.xml").getFile());
			ServiceConfigDefinition serviceDefiConfigDefinition=(ServiceConfigDefinition)jaxb2Marshaller.unmarshal(new StreamSource(fis));
			for(ServiceConfig serviceConfig:serviceDefiConfigDefinition.getServiceConfigList()){
					serviceConfig.setModelClass(Class.forName(serviceConfig.getModel()));
				
	        	serviceConfigMap.put(serviceConfig.getName(), serviceConfig);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	}
	
	protected void initFromJSON(){
		try {
			InputStream is=getResource("service-config.json").getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuilder fileText= new StringBuilder();
	          while ((line = br.readLine()) != null) {
	             fileText.append(line);
	       	  } 
	          JsonParser jsonParser= JsonParserFactory.getJsonParser();
	          List<Object> serviceConfigList=jsonParser.parseList(fileText.toString());
	          for(Object obj:serviceConfigList){
	        	  Map<String,String> map = (Map<String,String>) obj;
	        	  ServiceConfig serviceConfig= new ServiceConfig();
	        	  serviceConfig.setName(map.get("name"));
	        	  serviceConfig.setQueryId(map.get("query"));  
	        	  serviceConfig.setModelClass(Class.forName(map.get("model")));
	        	  serviceConfigMap.put(serviceConfig.getName(), serviceConfig);
	          }
	          
	          br.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Resource getResource(String path) {
		    return this.resourceLoader.getResource(path);
	}
	
	public static ServiceConfig getServiceConfig(String serviceName){
		return serviceConfigMap.get(serviceName);		
	}
	
	public static String getQuery(String queryId){
		SQLConfig sqlConfig=configProvider.getProvider(ConfigType.SQLConfig);
		BeanWrapper beanWrapper=PropertyAccessorFactory.forBeanPropertyAccess(sqlConfig);
		String sql=beanWrapper.getPropertyValue(queryId).toString();
		return sql;
	}

}
