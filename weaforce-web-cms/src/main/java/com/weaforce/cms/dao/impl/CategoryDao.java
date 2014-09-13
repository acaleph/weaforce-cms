package com.weaforce.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ICategoryDao;
import com.weaforce.cms.entity.Category;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("categoryDao")
public class CategoryDao extends GenericDao<Category, Long> implements
		ICategoryDao {
	private static final String QUERY_CATEGORY_BY_ACTIVE = " From Category a Where a.account=? And a.categoryIsActive=? ";
	private static final String QUERY_CATEGORY_BY_CHANNEL = " From Category a Where a.categoryChannel.channelId=? ";
	private static final String QUERY_CATEGORY_BY_CHANNEL_ACTIVE = " From Category a Where a.categoryChannel.channelId=? And a.categoryIsActive=? ";


	/**
	 * 取得栏目列表
	 * 
	 * @param account
	 *            帐套
	 * @param isActive
	 *            活动
	 * @return
	 */
	public List<Category> getCategoryListByActive(String account,
			String isActive) {
		return listQuery(QUERY_CATEGORY_BY_ACTIVE, account, isActive);
	}

	/**
	 * 取得栏目列表
	 * 
	 * @param channelId
	 *            频道
	 * @return
	 */
	public List<Category> getCategoryListByChannel(Long channelId) {
		return listQuery(QUERY_CATEGORY_BY_CHANNEL, channelId);
	}

	/**
	 * 取得栏目列表
	 * 
	 * @param channelId
	 *            频道
	 * @param isActive
	 *            活动
	 * @return
	 */
	public List<Category> getCategoryListByChannelActive(Long channelId,
			String isActive) {
		return listQuery(QUERY_CATEGORY_BY_CHANNEL_ACTIVE, channelId, isActive);
	}
}
