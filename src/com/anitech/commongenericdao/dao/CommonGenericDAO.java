package com.anitech.commongenericdao.dao;

import java.util.List;
import java.util.Map;

/**
 * @author Tapas
 *
 */
public interface CommonGenericDAO<T> {
	
	public T createEntity(T entity) throws Exception;
	
	public T updateEnity(T entity) throws Exception;
	
	public T findEntityById(Class<T> entityClass, Object id) throws Exception;
	
	public List<T> findEntityByQuery(Class<T> entityClass, String hqlQuery) throws Exception;
	
	public List<T> findEntityByQuery(Class<T> entityClass, String hqlQuery, Map<Integer, String> parameters) throws Exception;

	public List<T> findEntityByQuery(Class<T> entityClass, String hqlQuery, Map<Integer, String> parameters, int maxResultLimit) throws Exception;
	
	public boolean deleteEntity(T entity) throws Exception;
	
	public List<T> findAllEntity(Class<T> entityClass) throws Exception;
	
	public int getEntityCount(Class<T> entityClass) throws Exception;

}
