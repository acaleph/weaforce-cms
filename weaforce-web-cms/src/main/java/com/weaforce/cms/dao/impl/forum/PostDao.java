package com.weaforce.cms.dao.impl.forum;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.forum.IPostDao;
import com.weaforce.cms.entity.forum.Post;
import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.core.util.StringUtil;

@Repository("forumPostDao")
public class PostDao extends GenericDao<Post, Long> implements IPostDao {
	private static final String QUERY_TOPIC_BY_FORUM = " From Post a Where a.postForum.forumId=? ";
	private static final String QUERY_POST_BY_TOPIC = " From Post a Where a.postHtml.htmlTopicId=?";
	// 每页的记录数
	private static final Integer pageSize = 14;

	/**
	 * 根据主题,取得贴子 list
	 * 
	 * @param topicId
	 * @return
	 */
	public List<Post> getPostListByTopic(Long topicId) {
		return listQuery(QUERY_POST_BY_TOPIC, topicId);
	}

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
			Integer pageNumber) {
		StringBuffer sb = new StringBuffer();
		if (StringUtil.isNotEmpty(topicTitle))
			topicTitle = " And a.topicTitle like '%" + topicTitle + "%' ";
		else
			topicTitle = "";
		/* 初始化页参数 */
		// 每页的记录数
		Integer pageSize = 14;
		// 页数
		Integer pageCount = 0;
		// 所有记录数
		Integer totalCount = executeStat(
				" Select Count(*) " + QUERY_TOPIC_BY_FORUM + topicTitle,
				forumId).intValue();
		if (totalCount % pageSize == 0)
			pageCount = totalCount / pageSize;
		else
			pageCount = totalCount / pageSize + 1;
		/* 初始化页参数 */
		List<Post> topicList = listQueryPage(QUERY_TOPIC_BY_FORUM + topicTitle,
				pageSize * (pageNumber - 1), pageSize, forumId);
		for (Post topic : topicList) {
			if (sb.length() == 0)
				sb.append("[");
			else
				sb.append(",");
			String nickname = "";
			if (topic.getPostRegistry() != null)
				nickname = topic.getPostRegistry().getRegistryNickname();
			sb.append("{\"topicId\":\"" + topic.getPostId()
					+ "\",\"topicTitle\":\"" + topic.getPostTitle()
					+ "\",\"topicTotalView\":\"" + topic.getPostTotalView()
					+ "\",\"topicTotalReply\":\"" + topic.getPostTotalReply()
					+ "\",\"lastUpdate\":\"" + nickname
					+ "\",\"lastUpdateDate\":\"" + topic.getLastUpdateDate()
					+ "\",\"createTime\":\"" + topic.getDate() + "\"}");
		}
		if (sb.length() > 0)
			sb.append("]");
		StringBuffer sbPage = new StringBuffer("[");
		sbPage.append("{\"pageNumber\":\"" + pageNumber + "\",\"pageCount\":\""
				+ pageCount + "\",\"totalCount\":\"" + totalCount
				+ "\",\"page\":" + sb.toString() + "}]");
		return sbPage.toString();

	}

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
			Integer pageNumber) {
		if (topicId != null && topicId > 0)
			return listQueryPage(getQueryHql(QUERY_POST_BY_TOPIC, postTitle),
					pageSize * (pageNumber - 1), pageSize, topicId);
		else
			return new ArrayList<Post>();
	}

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
	public String getPostPageJSON(Long topicId, String postTitle,
			Integer pageNumber) {
		StringBuffer sb = new StringBuffer();
		// 页数
		Integer pageCount = 0;
		// 所有记录数
		Integer totalCount = 0;
		if (topicId != null && topicId > 0)
			totalCount = executeStat(
					" Select Count(*) "
							+ getQueryHql(QUERY_POST_BY_TOPIC, postTitle),
					topicId).intValue();
		if (totalCount % pageSize == 0)
			pageCount = totalCount / pageSize;
		else
			pageCount = totalCount / pageSize + 1;
		sb.append("[{\"pageNumber\":\"" + pageNumber + "\",\"pageCount\":\""
				+ pageCount + "\",\"totalCount\":\"" + totalCount + "\"}]");
		return sb.toString();
	}

	/**
	 * 取得组合查询语句
	 * 
	 * @param queryStr
	 * @param postTitle
	 * @return
	 */
	public String getQueryHql(String queryStr, String postTitle) {
		if (StringUtil.isNotEmpty(postTitle))
			queryStr = queryStr + " And a.postTitle like '%" + postTitle + "%'";
		return queryStr;
	}
}
