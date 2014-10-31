package com.anitech.commongenericdao.test;

import java.util.Date;

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
public class ReleaseCRUDTest {
	
	private CommonGenericDAO bankDAO;

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
	public void testReleaseCRUDOperations() throws Exception {
		
		Release rel = new Release();
		rel.setVersionID("2.0.0");
		rel.setDescription("Digital Bank Initial Release");
		rel.setReleaseDate(new Date());
		rel.setOsName("Both");
		rel.setUpgradeType("Optional");
		rel.setAndroidUpgradeURL("http://www.google.com");
		rel.setiOSUpgradeURL("http://www.apple.com");
		
		// Create the Release record
		Release createdRelease = (Release) bankDAO.createEntity(rel);
		Assert.assertNotNull(createdRelease);
		Assert.assertEquals(createdRelease.getDescription(), "Digital Bank Initial Release");
		
		// Update the Release record
		rel.setUpgradeType("Mandatory");
		Release updatedRelease = (Release) bankDAO.updateEnity(rel);
		Assert.assertNotNull(updatedRelease);
		Assert.assertEquals(createdRelease.getUpgradeType(), "Mandatory");
		
		// Read the Release record
		Release release = (Release) bankDAO.findEntityById(Release.class, "2.0.0");
		Assert.assertEquals(release.getDescription(), "Digital Bank Initial Release");
		
		// Delete the Release record 
		boolean deleteStatus = bankDAO.deleteEntity(release);
		Assert.assertTrue(deleteStatus);
		
	}
	
}
