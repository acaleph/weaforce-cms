package com.weaforce.cms.dao.impl.forum;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.forum.ICategoryDao;
import com.weaforce.cms.entity.forum.ForumCategory;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("forumCategoryDao")
public class CategoryDao extends GenericDao<ForumCategory, Long> implements
		ICategoryDao {
	private static final String QUERY_CATEGORY_BY_CHANNEL = " From ForumCategory a Where a.categoryChannel.channelId=? ";
	/**
	 * 根据频道,取得栏目 list
	 * 
	 * @param channelId
	 * @return
	 */
	public List<ForumCategory> getCategoryByChannel(Long channelId){
		return listQuery(QUERY_CATEGORY_BY_CHANNEL,channelId);
	}
}
