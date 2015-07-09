package com.spring.boot.micro.services.example.db.dao;

import java.util.List;

import com.spring.boot.micro.services.example.exception.SBDAOException;

public interface SBDAO {
	
	public <TModel> List<TModel> getData(String queryId,Object sbDTO,Class<TModel> modelClazz)
			throws SBDAOException;

}
