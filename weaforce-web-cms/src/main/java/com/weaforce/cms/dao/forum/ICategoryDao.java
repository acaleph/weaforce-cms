package com.weaforce.cms.dao.forum;

import java.util.List;

import com.weaforce.cms.entity.forum.ForumCategory;
import com.weaforce.core.dao.IGenericDao;

public interface ICategoryDao extends IGenericDao<ForumCategory, Long> {
	/**
	 * 根据频道,取得栏目 list
	 * 
	 * @param channelId
	 * @return
	 */
	public List<ForumCategory> getCategoryByChannel(Long channelId);

}
