package com.weaforce.cms.dao;

import java.util.List;

import com.weaforce.cms.entity.Category;
import com.weaforce.core.dao.IGenericDao;

public interface ICategoryDao extends IGenericDao<Category, Long> {


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
			String isActive);

	/**
	 * 取得栏目列表
	 * 
	 * @param channelId
	 *            频道
	 * @return
	 */
	public List<Category> getCategoryListByChannel(Long channelId);

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
			String isActive);
	
}
