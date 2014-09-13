package com.weaforce.cms.dao.forum;

import com.weaforce.cms.entity.forum.PostContent;
import com.weaforce.core.dao.IGenericDao;

public interface IPostContentDao extends IGenericDao<PostContent, Long> {
	/**
	 * 根据贴子主键,取得主键内容
	 * 
	 * @param postId
	 *            贴子主键
	 * @return
	 */
	public PostContent getContentByPost(Long postId);
}
