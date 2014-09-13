package com.weaforce.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ICategoryUserDao;
import com.weaforce.cms.entity.CategoryUser;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("categoryUserDao")
public class CategoryUserDao extends GenericDao<CategoryUser, Long> implements
		ICategoryUserDao {
	private static final String QUERY_CATEGORYUSER_BY_USER = " From CategoryUser a Where a.categoryUser.userId=? ";
	private static final String QUERY_CATEGORYUSER_BY_USER_CHANNEL = " From CategoryUser a Where a.categoryUser.userId=? and a.userCategory.categoryChannel.channelId=? ";
	private static final String GET_CATEGORYUSER_BY_CATEGORY_USER = " From CategoryUser a Where a.userCategory.categoryId = ? and a.categoryUser.userLogin=?";

	/**
	 * 根据用户主键取得用户栏目
	 * 
	 * @param userId
	 *            用戶主键
	 * @return
	 */
	public List<CategoryUser> getCategoryUserListByUser(Long userId) {
		return listQuery(QUERY_CATEGORYUSER_BY_USER, userId);
	}

	/**
	 * 取得用户栏目
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @param userId
	 *            用户登录ID
	 * @return
	 */
	public CategoryUser getCategoryUserByCategoryUser(Long categoryId,
			String userLogin) {
		return loadEntity(GET_CATEGORYUSER_BY_CATEGORY_USER, categoryId,
				userLogin);
	}

	/**
	 * Get category user list by user and channel
	 * 
	 * @param userId
	 *            User primary key
	 * @param channelId
	 *            Channel primary key
	 * @return
	 */
	public List<CategoryUser> getCategoryUserListByUserChannel(Long userId,
			Long channelId) {
		return listQuery(QUERY_CATEGORYUSER_BY_USER_CHANNEL, userId, channelId);
	}

}
