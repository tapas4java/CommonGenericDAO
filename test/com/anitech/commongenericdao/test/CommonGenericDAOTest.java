package com.anitech.commongenericdao.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.anitech.commongenericdao.dao.CommonGenericDAO;
import com.anitech.commongenericdao.dao.JPACommonGenericDAO;
import com.anitech.commongenericdao.entity.Release;

/**
 * @author Tapas
 *
 */
@SuppressWarnings("rawtypes")
public class CommonGenericDAOTest {

	CommonGenericDAO bankDAO;
	
	@Before
	public void beforeEach() {
		bankDAO = new JPACommonGenericDAO();
	}

	@After
	public void afterEach() {
		// perform any cleanup task
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testCRUDOperations() throws Exception {
		
		Release rel = new Release();
		rel.setVersionID("1.0.5");
		rel.setDescription("Digital Bank Initial Release");
		rel.setReleaseDate(new Date());
		rel.setOsName("Both");
		rel.setUpgradeType("Optional");
		rel.setAndroidUpgradeURL("http://www.google.com");
		rel.setiOSUpgradeURL("http://www.apple.com");
		
		//create
		Release createdRelease = (Release) bankDAO.createEntity(rel);
		Assert.assertNotNull(createdRelease);
		Assert.assertEquals(createdRelease.getDescription(), "Digital Bank Initial Release");
		
		//update
		rel.setDescription("Digital Bank Initial Release Test");
		Release updatedRelease = (Release) bankDAO.updateEnity(rel);
		Assert.assertNotNull(updatedRelease);
		Assert.assertEquals(updatedRelease.getDescription(), "Digital Bank Initial Release Test");
		
		//find all
		List<Release> allReleases = (List<Release>) bankDAO.findAllEntity(Release.class);
		Assert.assertEquals(allReleases.size(), 5);
		
		//find by query
		List<Release> resultList = (List) bankDAO.findEntityByQuery(Release.class, "from Release");
		Assert.assertEquals(resultList.size(), 5);
		
		//find by query with parameter
		Map params = new HashMap();
		params.put(1, "Both");
		List<Release> resultList1 = bankDAO.findEntityByQuery(Release.class, "from Release as release where release.osName = ?1", params);
		Assert.assertEquals(resultList1.size(), 5);
		
		//find by query with parameter and maxResult
		List<Release> resultList2 = bankDAO.findEntityByQuery(Release.class, "from Release as release where release.osName = ?1", params, 3);
		Assert.assertEquals(resultList2.size(), 3);
		
		// find by id
		Release release = (Release) bankDAO.findEntityById(Release.class, "1.0.5");
		Assert.assertEquals(release.getDescription(), "Digital Bank Initial Release Test");
		
		//delete
		boolean deleteStatus = bankDAO.deleteEntity(release);
		Assert.assertNotNull(deleteStatus);
		Assert.assertTrue(deleteStatus);
		
		//get total count
		int count = bankDAO.getEntityCount(Release.class);
		Assert.assertEquals(count, 4);
	}
	
}
