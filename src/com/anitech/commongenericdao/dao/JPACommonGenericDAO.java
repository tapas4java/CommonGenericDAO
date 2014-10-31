package com.anitech.commongenericdao.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.anitech.commongenericdao.util.EntityManagerUtil;

/**
 * This class is responsible for all application level database interaction. 
 * It provides unified apis for all basic CRUD operations like Create, Read, Update, Delete. 
 * 
 * @author Tapas
 */
public class JPACommonGenericDAO<T> implements CommonGenericDAO<T> {
	
	private static final Logger LOG = Logger.getLogger(JPACommonGenericDAO.class);
	
	//get the EntityManager reference
	EntityManager em;
	
	@Override
	public T createEntity(T entity) throws Exception {
		em = EntityManagerUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		try {
			LOG.debug("Persisting entity : " + entity.getClass().getSimpleName());
			em.persist(entity);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			LOG.debug("Error occured during Persisting entity : " + entity.getClass().getSimpleName());
			throw new Exception();
		} finally{
			em.close();
		}
		return entity;
	}

	@Override
	public T updateEnity(T entity) throws Exception {
		em = EntityManagerUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		try {
			LOG.debug("Updating entity : " + entity.getClass().getSimpleName());
			em.merge(entity);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			LOG.debug("Error occoured during updating entity : " + entity.getClass().getSimpleName());
			throw new Exception();
		} finally{
			em.close();
		}
		return entity;
	}

	@Override
	public T findEntityById(Class<T> entityClass, Object id) throws Exception {
		em = EntityManagerUtil.getEntityManager();
		T obj;
		
		try {
			LOG.debug("Finding entity : " + entityClass.getSimpleName() + " with ID : " + id.toString());
			obj = (T) em.find(entityClass, id);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.debug("Error occoured during finding entity : " + entityClass.getSimpleName() + " with ID : " + id.toString());
			throw new Exception();
		} finally {
			em.close();
		}
		return obj;
	}

	@Override
	public List<T> findEntityByQuery(Class<T> entityClass, String hqlQuery) throws Exception {
		em = EntityManagerUtil.getEntityManager();
		List<T> objectList;
		
		try {
			LOG.debug("Finding entity for query : " + hqlQuery);
			objectList = em.createQuery(hqlQuery, entityClass).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.debug("Error occoured during finding entity for query : " + hqlQuery);
			throw new Exception();
		} finally {
			em.close();
		}
		return objectList;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findEntityByQuery(Class<T> entityClass, String hqlQuery, Map<Integer, String> parameters) throws Exception {
		em = EntityManagerUtil.getEntityManager();
		List<T> objectList;
		
		try {
			LOG.debug("Finding entity for query : " + hqlQuery);
			Set<Entry<Integer, String>> rawParameters = parameters.entrySet();
	        Query query = em.createQuery(hqlQuery, entityClass);
	        for (Entry<Integer, String> entry : rawParameters) {
	            query.setParameter(entry.getKey(), entry.getValue());
	        }
			objectList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.debug("Error occoured during finding entity for query : " + hqlQuery);
			throw new Exception();
		} finally {
			em.close();
		}
		return objectList;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findEntityByQuery(Class<T> entityClass, String hqlQuery, Map<Integer, String> parameters, int maxResultLimit) throws Exception {
		em = EntityManagerUtil.getEntityManager();
		List<T> objectList;
		
		try {
			LOG.debug("Finding entity for query : " + hqlQuery);
			Set<Entry<Integer, String>> rawParameters = parameters.entrySet();
	        Query query = em.createQuery(hqlQuery, entityClass);
	        for (Entry<Integer, String> entry : rawParameters) {
	            query.setParameter(entry.getKey(), entry.getValue());
	        }
	        if(maxResultLimit > 0){
	            query.setMaxResults(maxResultLimit);
	        }
			objectList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.debug("Error occoured during finding entity for query : " + hqlQuery);
			throw new Exception();
		} finally {
			em.close();
		}
		return objectList;
	}

	@Override
	public boolean deleteEntity(T entity) throws Exception {
		boolean isSuccess = false;
		em = EntityManagerUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		try {
			LOG.debug("Deleting entity : " + entity.getClass().getSimpleName());
			em.remove(em.contains(entity) ? entity : em.merge(entity));
			transaction.commit();
			isSuccess = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			LOG.debug("Error occoured during deleting entity : " + entity.getClass().getSimpleName());
			throw new Exception();
		} finally{
			em.close();
		}
		return isSuccess;
	}

	@Override
	public List<T> findAllEntity(Class<T> entityClass) throws Exception {
		em = EntityManagerUtil.getEntityManager();
		List<T> objectList;
		
		try {
			LOG.debug("Finding all entity list for : " + entityClass.getSimpleName());
			objectList = em.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.debug("Error occoured while finding all entity list for : " + entityClass.getSimpleName());
			throw new Exception();
		} finally {
			em.close();
		}
		return objectList;
	}

	@Override
	public int getEntityCount(Class<T> entityClass) throws Exception {
		int totalCount = 0;
		em = EntityManagerUtil.getEntityManager();
		
		try {
			LOG.debug("Getting total entity count for : " + entityClass.getSimpleName());
			totalCount = em.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList().size();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.debug("Error occoured while getting total entity count for : " + entityClass.getSimpleName());
			throw new Exception();
		} finally {
			em.close();
		}
		return totalCount;
	}
	
}
