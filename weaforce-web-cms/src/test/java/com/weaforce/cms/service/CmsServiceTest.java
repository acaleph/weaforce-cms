package com.weaforce.cms.service;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;

import com.weaforce.cms.entity.Category;
import com.weaforce.core.util.BootStrap;
import com.weaforce.entity.admin.Role;
import com.weaforce.entity.admin.User;
import com.weaforce.entity.app.Menu;

public class CmsServiceTest extends AbstractTransactionalSpringTest {

	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	protected static Log log = LogFactory.getLog(CmsServiceTest.class);

	// @Test
	public void getArticle() {
		assertTrue(cmsService.getArticle(Long.valueOf("1")).getArticleId()
				.equals(Long.valueOf("1")));
		List<Category> categoryList = cmsService.getCategoryList(Long
				.valueOf("7"));
		assertTrue(categoryList.size() > 0);
		for (Category o : categoryList)
			log.debug(o.getCategoryName());

	}

	@Test
	@Rollback(false)
	public void getChannelCategoryTree() {
		User user = cmsService.getUserByLogin("YANJIACHENG@WEAFORCE.COM");
		String tree = cmsService.getChannelCategoryTree(user);
		Role role = user.getDefaultUserRole();
		role.setRoleCategory(tree);
		System.out.println("category: "
				+ cmsService.getChannelCategoryTree(user));
	}

	// @Test
	public void getSplitButtonMenuBar() {
		User user = cmsService.getUserByLogin("YANJIACHENG@WEAFORCE.COM");
		cmsService.getRoleMenu(user);
	}

	// @Test
	// @Rollback(true)
	public void getNavLists() {
		Menu m = cmsService.getMenu(Long.valueOf("122"));
		List<Menu> menuList = m.getMenuChild();
		String items[] = cmsService.getItems(menuList);
		String navLists = BootStrap.getNavListWithHead(null, m.getMenuNameCn(),
				items);
		Role r = cmsService.getRole(Long.valueOf("1"));
		r.setRoleMenuContent(navLists);
		cmsService.saveRole(r);
	}

	// @Test
	public void getLeftMenu() {
		System.out.println(cmsService.getLeftMenu(cmsService
				.getUserByLogin("YANJIACHENG@WEAFORCE.COM")));
	}

	// @Test
	public void getMenuTree() {
		User user = cmsService.getUserByLogin("YANJIACHENG@WEAFORCE.COM");
		System.out.println(cmsService.getMenuTree(4L, user));
	}

}
