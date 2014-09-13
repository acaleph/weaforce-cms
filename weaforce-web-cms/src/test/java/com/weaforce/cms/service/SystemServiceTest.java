package com.weaforce.cms.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.entity.admin.User;
import com.weaforce.entity.app.Menu;
import com.weaforce.system.service.ISystemService;

public class SystemServiceTest extends AbstractTransactionalSpringTest {
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	protected static Log log = LogFactory.getLog(SystemServiceTest.class);

	// @Test
	public void testGetUserLoginListByLogin() throws InterruptedException {
		List<String> userList = systemService
				.getUserLoginListByLogin("ariel@tekcon.com.tw");
		assertTrue(userList.size() == 0);
	}

	//@Test
	public void getUser() {
		User user = systemService.getUserByLogin("ariel@tekcon.com.tw");
		assertTrue(user != null);
	}

	@Test
	public void getMenuList() {
		List<Menu> menuList = systemService.getMenuList();
		assertTrue(menuList.size() > 0);
	}

	// @Test
	// @Rollback(true)
	public void saveUser() {
		User user = systemService.getUserByLogin("cdef@cdb");
		user.setUserLogin("MAERSK@MAERSK.COM");
		if (systemService.getUserByLogin("MAERSK@MAERSK.COM") == null)
			systemService.saveUser(user);
	}

	//@Test
	public void testGetUserLoginJSON() {
		assertTrue(systemService.getUserLoginJSON("100").length() > 0);
	}

}
