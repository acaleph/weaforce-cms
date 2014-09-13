package com.weaforce.cms.dao.forum;

import com.weaforce.cms.entity.forum.PostHtml;
import com.weaforce.core.dao.IGenericDao;

public interface IPostHtmlDao extends IGenericDao<PostHtml, Long> {
	/**
	 * 根据贴子,取得HTML内容,用于保存topic及post
	 * 
	 * @param postId
	 * @return
	 */
	public PostHtml getHtmlByPost(Long postId);
}
