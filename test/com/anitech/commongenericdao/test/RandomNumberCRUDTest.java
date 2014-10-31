package com.anitech.commongenericdao.test;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.anitech.commongenericdao.dao.CommonGenericDAO;
import com.anitech.commongenericdao.dao.JPACommonGenericDAO;
import com.anitech.commongenericdao.entity.RandomNumber;

/**
 * @author Tapas
 *
 */
@SuppressWarnings("rawtypes")
public class RandomNumberCRUDTest {

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
	public void testRandomNumberCRUDOperations() throws Exception {

		RandomNumber random = new RandomNumber();
		random.setAppRandomNumber("TAPAS1Apppp65365fhgfh46gkgkggkg4k6gkj46gj4g64g4j64jg6g46g413");
		random.setHsmRandomNumber("TAPASHSMu7565758vftfgfhgffgfftrtryt676856756mjlljllkjkjjlkj");
		random.setServerName("RMB-Server");
		random.setTimeStamp(new Date());

		// Create the RandomNumber record
		RandomNumber createdRandom = (RandomNumber) bankDAO.createEntity(random);
		Assert.assertNotNull(createdRandom);
		Assert.assertEquals(createdRandom.getServerName(), "RMB-Server");
		
		// Update the RandomNumber record
		random.setServerName("RMB-India-Server");
		RandomNumber updatedRandom = (RandomNumber) bankDAO.updateEnity(random);
		Assert.assertNotNull(updatedRandom);
		Assert.assertEquals(updatedRandom.getServerName(), "RMB-India-Server");
		
		// Read the RandomNumber record
		RandomNumber rand = (RandomNumber) bankDAO.findEntityById(RandomNumber.class, "TAPAS1Apppp65365fhgfh46gkgkggkg4k6gkj46gj4g64g4j64jg6g46g413");
		Assert.assertEquals(rand.getHsmRandomNumber(), "TAPASHSMu7565758vftfgfhgffgfftrtryt676856756mjlljllkjkjjlkj");
		
		// Delete the RandomNumber record 
		boolean seleteStatus = bankDAO.deleteEntity(random);
		Assert.assertTrue(seleteStatus);

	}

}
