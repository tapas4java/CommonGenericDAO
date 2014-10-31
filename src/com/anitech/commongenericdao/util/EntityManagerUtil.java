package com.anitech.commongenericdao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

/**
 * @author Tapas
 *
 */
public class EntityManagerUtil {
	
	private static EntityManagerFactory emf = null;
	
	private static Logger LOG = Logger.getLogger(EntityManagerUtil.class);
	
	static{
		// Read the persistence.xml once
		LOG.debug("Loading persistence.xml to EntityManagerFactory.");
		emf = Persistence.createEntityManagerFactory("digibank-mysql");
	}
	
	public static EntityManagerFactory getEntityManagerFactory(){
		if(emf == null){
			LOG.debug("EntityManagerFactory is null. Creating fresh EntityManagerFactory.");
			emf = Persistence.createEntityManagerFactory("digibank-mysql");
		}
		return emf;
	}
	
	public static EntityManager getEntityManager(){
		return getEntityManagerFactory().createEntityManager();
	}
	
}
