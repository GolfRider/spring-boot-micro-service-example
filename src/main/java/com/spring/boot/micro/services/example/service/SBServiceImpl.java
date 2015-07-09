package com.spring.boot.micro.services.example.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.boot.micro.services.example.commons.Constants.ConfigType;
import com.spring.boot.micro.services.example.config.ErrorConfig;
import com.spring.boot.micro.services.example.config.provider.ConfigProvider;
import com.spring.boot.micro.services.example.config.provider.ServiceConfig;
import com.spring.boot.micro.services.example.config.provider.ServiceConfigProvider;
import com.spring.boot.micro.services.example.db.dao.SBDAO;
import com.spring.boot.micro.services.example.exception.SBDAOException;
import com.spring.boot.micro.services.example.exception.SBException;

@Component
public class SBServiceImpl implements SBService {

	@Autowired
	private SBDAO sbDAO;
	
	@Autowired
	ConfigProvider configProvider;

	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public List<?> getData(String serviceName,Object sbDTO) throws SBException{
		try{
			
			ServiceConfig serviceConfig=ServiceConfigProvider.getServiceConfig(serviceName);
			return sbDAO.getData(serviceConfig.getQueryId(),sbDTO,serviceConfig.getModelClass());
			
		}catch(SBDAOException  ex){
			ErrorConfig errorConfig=configProvider.getProvider(ConfigType.ErrorConfig);
		    throw new SBException(errorConfig.getServiceSbCode(), errorConfig.getServiceSbMessage(), ex);
		}	
  }
}
