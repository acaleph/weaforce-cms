package com.weaforce.cms.dao.impl.forum;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.forum.IPostContentDao;
import com.weaforce.cms.entity.forum.PostContent;
import com.weaforce.core.dao.impl.GenericDao;
@Repository("forumPostContentDao")
public class PostContentDao extends GenericDao<PostContent, Long> implements
		IPostContentDao {
	//private static final String QUERY_CONTENT_BY_POST = " From PostContent a Where a.contentPost.postId=?";
	/**
	 * 根据贴子主键,取得主键内容
	 * 
	 * @param postId
	 *            贴子主键
	 * @return
	 */
	public PostContent getContentByPost(Long postId){
		return loadEntity("contentPost.postId",postId);
	}
}
