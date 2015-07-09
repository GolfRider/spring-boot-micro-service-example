package com.spring.boot.micro.services.example.db.dao;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.spring.boot.micro.services.example.commons.Constants;
import com.spring.boot.micro.services.example.config.ErrorConfig;
import com.spring.boot.micro.services.example.config.provider.ConfigProvider;
import com.spring.boot.micro.services.example.config.provider.ServiceConfigProvider;
import com.spring.boot.micro.services.example.db.mapper.DBMapper;
import com.spring.boot.micro.services.example.db.mapper.ResultSetMapper;
import com.spring.boot.micro.services.example.db.query.QueryBuilder;
import com.spring.boot.micro.services.example.exception.SBDAOException;

@Component
public class SBDAOImpl implements SBDAO {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ConfigProvider configProvider;
	
	@Autowired
	private JdbcTemplate jdbc;
	
	
	public <TModel> List<TModel> getData(String queryId,Object sbDTO,Class<TModel> modelClazz)
			throws SBDAOException {
		
		log.info("Before Query Execution in  GenericDAO");
		List<TModel> dataList=null;
		try {
			
			Map<String,String> requestParamDBMap=DBMapper.getRequestParamDBMap(sbDTO);
			String baseQuery=ServiceConfigProvider.getQuery(queryId);
			String sql=QueryBuilder.prepareSQL(baseQuery,requestParamDBMap);
			
			ResultSetMapper<TModel> resultSetMapper=new ResultSetMapper<TModel>(modelClazz);
			jdbc.query(sql, requestParamDBMap.values().toArray(), resultSetMapper);
			
			dataList=resultSetMapper.getResult();
			if(dataList!=null){
				log.info("After Query Execution, Rows Fetched:"+dataList.size());	
			}		
			
		} catch (Exception ex) {			
			ex.printStackTrace();
			log.error("Exception in GenericDAO:", ex);
			ErrorConfig errorConfig=getErrorConfig();
		    throwDAOException(ex, errorConfig.getDaoSbCode(), errorConfig.getDaoSbMessage());
		}
		
		return dataList;
	}
	

	
	private ErrorConfig getErrorConfig() {
		return configProvider.getProvider(Constants.ConfigType.ErrorConfig);
	}
	
	private void throwDAOException(Exception ex,String errorCode,String errorMessage) throws SBDAOException{
		throw new SBDAOException(errorCode,errorMessage,ex);
	}
	
	
}
