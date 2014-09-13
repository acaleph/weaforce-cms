package com.weaforce.cms.dao.impl.forum;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.forum.IPostHtmlDao;
import com.weaforce.cms.entity.forum.PostHtml;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("forumPostHtmlDao")
public class PostHtmlDao extends GenericDao<PostHtml, Long> implements
		IPostHtmlDao {
	/**
	 * 根据贴子,取得HTML内容,用于保存topic及post
	 * 
	 * @param postId
	 * @return
	 */
	public PostHtml getHtmlByPost(Long postId) {
		return loadEntity("htmlPost.postId", postId);
	}
}
