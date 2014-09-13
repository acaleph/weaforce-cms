package com.weaforce.cms.dao.forum;

import java.util.List;

import com.weaforce.cms.entity.forum.Post;
import com.weaforce.core.dao.IGenericDao;

public interface IPostDao extends IGenericDao<Post, Long> {
	/**
	 * 根据主题,取得贴子 list
	 * 
	 * @param topicId
	 * @return
	 */
	public List<Post> getPostListByTopic(Long topicId);

	/**
	 * 以JSON格式,取得主题
	 * 
	 * @param forumId
	 *            论坛主键
	 * @param topicTitle
	 *            标题
	 * @param pageNumber
	 *            当前页数
	 * @return
	 */
	public String getTopicJSON(Long forumId, String topicTitle,
			Integer pageNumber);

	/**
	 * 根据主题/标题,取得贴子 page list
	 * 
	 * @param topicId
	 *            主题主键
	 * @param postTitle
	 *            标题
	 * @param pageNumber
	 *            当前页数
	 * @return
	 */
	public List<Post> getPostPageByTopicTitle(Long topicId, String postTitle,
			Integer pageNumber);

	/**
	 * 以JSON格式，取得贴子分页信息
	 * 
	 * @param topicId
	 *            主题
	 * @param postTitle
	 *            标题
	 * @param pageNumber
	 *            当前页数
	 * @return
	 */
	public String getPostPageJSON(Long topicId, String postTitle, Integer pageNumber);
}
