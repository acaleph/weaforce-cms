package com.weaforce.cms.dao;

import java.util.List;

import com.weaforce.cms.entity.CategoryUser;
import com.weaforce.core.dao.IGenericDao;

public interface ICategoryUserDao extends IGenericDao<CategoryUser, Long> {
	/**
	 * 根据用户主键取得用户栏目
	 * 
	 * @param userId
	 *            用戶主键
	 * @return
	 */
	public List<CategoryUser> getCategoryUserListByUser(Long userId);

	/**
	 * 取得用户栏目
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @param userLogin
	 *            用户登录ID
	 * @return
	 */
	public CategoryUser getCategoryUserByCategoryUser(Long categoryId,
			String userLogin);

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
			Long channelId);
}
