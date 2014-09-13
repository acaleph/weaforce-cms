package com.weaforce.cms.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.weaforce.cms.entity.forum.Forum;
import com.weaforce.cms.entity.forum.ForumCategory;
import com.weaforce.cms.entity.forum.ForumChannel;
import com.weaforce.cms.entity.forum.Post;
import com.weaforce.cms.entity.forum.PostContent;
import com.weaforce.cms.entity.forum.PostHtml;
import com.weaforce.core.util.PageInfo;

/**
 * 论坛服务
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public interface IForumService {
	/**
	 * 预处理频道
	 * 
	 * @param o
	 *            频道
	 * @param channelId
	 *            频道主键
	 * @param cityId
	 *            所属城市
	 * @return
	 */
	public ForumChannel prepareChannel(ForumChannel o, Long channelId,
			Long cityId);

	/**
	 * 取得频道
	 * 
	 * @param channelId
	 * @return
	 */
	public ForumChannel getChannel(Long channelId);

	/**
	 * 保存频道
	 * 
	 * @param o
	 * @param cityId
	 * @param templateId
	 * @return
	 */
	public ForumChannel saveChannel(ForumChannel o, Long cityId, Long templateId);

	/**
	 * 删除频道
	 * 
	 * @param channelId
	 */
	public void deleteChannel(Long channelId);

	/**
	 * parse 频道
	 * 
	 * @param account
	 * @param channelId
	 * @param menu
	 *            菜单
	 * @throws Exception
	 */
	public void parseChannel(String account, Long channelId, String menu)
			throws Exception;

	/**
	 * 根据城市,取得频道 list
	 * 
	 * @param cityId
	 * @return
	 */
	public List<ForumChannel> getChannelListByCity(Long cityId);

	/**
	 * 取得频道 page
	 * 
	 * @param pageInfo
	 * @param cityId
	 * @param channelName
	 * @return
	 */
	public PageInfo<ForumChannel> getChannelPage(
			PageInfo<ForumChannel> pageInfo, Long cityId, String channelName);

	/**
	 * 预处理栏目
	 * 
	 * @param o
	 * @param categoryId
	 * @param channelId
	 * @return
	 */
	public ForumCategory prepareCategory(ForumCategory o, Long categoryId,
			Long channelId);

	/**
	 * 取得栏目
	 * 
	 * @param categoryId
	 * @return
	 */
	public ForumCategory getCategory(Long categoryId);

	/**
	 * 保存栏目
	 * 
	 * @param o
	 * @param channelId
	 * @param templateId
	 * @return
	 */
	public ForumCategory saveCategory(ForumCategory o, Long channelId,
			Long templateId);

	/**
	 * 删除栏目
	 * 
	 * @param categoryId
	 */
	public void deleteCategory(Long categoryId);

	/**
	 * 根据频道，取得栏目 JSON
	 * 
	 * @param channelId
	 *            频道
	 * @return
	 */
	public String getCategoryDDL(Long channelId);

	/**
	 * 根据频道,取得栏目 list
	 * 
	 * @param channelId
	 * @return
	 */
	public List<ForumCategory> getCategoryListByChannel(Long channelId);

	/**
	 * 取得栏目 page
	 * 
	 * @param pageInfo
	 * @param channelId
	 * @param categoryName
	 * @return
	 */
	public PageInfo<ForumCategory> getCategoryPage(
			PageInfo<ForumCategory> pageInfo, Long channelId,
			String categoryName);

	/**
	 * 论坛预处理
	 * 
	 * @param o
	 * @param forumId
	 * @param categoryId
	 * @return
	 */
	public Forum prepareForum(Forum o, Long forumId, Long categoryId);

	/**
	 * 取得论坛
	 * 
	 * @param forumId
	 * @return
	 */
	public Forum getForum(Long forumId);

	/**
	 * 保存论坛
	 * 
	 * @param o
	 * @param templateId
	 *            模板
	 * @param categoryId
	 *            栏目
	 * @return
	 */
	public Forum saveForum(Forum o, Long categoryId, Long templateId);

	/**
	 * 删除论坛
	 * 
	 * @param forumId
	 */
	public void deleteForum(Long forumId);

	/**
	 * parse 论坛
	 * 
	 * @param account
	 *            帐套
	 * @param forumId
	 *            论坛主键
	 * @param menu
	 *            菜单
	 * @throws Exception
	 */

	public void parseForum(String account, Long forumId, String menu)
			throws Exception;

	/**
	 * 根据论坛,取得论坛 list
	 * 
	 * @return
	 */
	public List<Forum> getForumList();

	/**
	 * 取得论坛 Page
	 * 
	 * @param pageInfo
	 * @param categoryId
	 * @param forumName
	 * @return
	 */
	public PageInfo<Forum> getForumPage(PageInfo<Forum> pageInfo,
			Long categoryId, String forumName);

	/**
	 * 主题预处理
	 * 
	 * @param o
	 *            主题
	 * @param topicId
	 *            主题主键
	 * @param forumId
	 *            论坛主键
	 * @return
	 */
	public Post prepareTopic(Post o, Long topicId, Long forumId);

	/**
	 * 预处理贴子
	 * 
	 * @param o
	 *            贴子
	 * @param postId
	 *            贴子主键
	 * @param topicId
	 *            主题主键
	 * @return
	 */
	public Post preparePost(Post o, Long postId, Long topicId);

	/**
	 * 取得贴子
	 * 
	 * @param postId
	 * @return
	 */
	public Post getPost(Long postId);

	/**
	 * 保存主题贴子
	 * 
	 * @param topic
	 *            主题贴子
	 * @param forumId
	 *            论坛主键
	 * @param postContent
	 *            内容
	 * @param remoteIp
	 *            客端IP
	 * @return
	 */
	public Post saveTopic(Post topic, Long forumId, String postContent,
			String remoteIp);

	/**
	 * 保存贴子
	 * 
	 * @param o
	 * @param parentId
	 *            父贴子
	 * @param contentContent
	 *            贴子内容
	 * @param remoteIp
	 *            用户IP
	 * @return
	 */
	public Post savePost(Post o, Long parentId, String contentContent,
			String remoteIp);

	/**
	 * 删除主题
	 * 
	 * @param topicId
	 */
	public void deleteTopic(Long topicId);

	/**
	 * parse 主题,并通过AJAX展示贴子,文件名称:post.html,目录与频道index.html相同,只有一个post.html
	 * 
	 * @param account
	 *            帐套
	 * @param topicId
	 *            主题主键
	 * @param menu
	 *            菜单
	 * @throws Exception
	 */
	public void parseTopic(String account, Long topicId, String menu)
			throws Exception;

	/**
	 * 删除贴子
	 * 
	 * @param postId
	 */
	public void deletePost(Long postId);

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
	 * 以HTML格式,取得贴子page
	 * 
	 * @param topicId
	 *            主题
	 * @param postTitle
	 *            标题
	 * @param pageNumber
	 *            当前页数
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getPostPageHTML(Long topicId, String postTitle, Integer pageNumber);
	/**
	 * 以JSON格式，取得贴子分页信息
	 * @param topicId 论坛
	 * @param psotTitle 标题
	 * @param pageNumber 当前页数
	 * @return
	 */
	public String getPostPageJSON(Long topicId, String psotTitle,
			Integer pageNumber);

	/**
	 * 取得主题贴子 page
	 * 
	 * @param pageInfo
	 * @param forumId
	 *            论坛主键
	 * @param queryTitle
	 *            主题贴子标题
	 * @return
	 */
	public PageInfo<Post> getTopicPage(PageInfo<Post> pageInfo, Long forumId,
			String postTitle);

	/**
	 * 取得贴子 page
	 * 
	 * @param pageInfo
	 * @param topicId
	 * @param postTitle
	 * @return
	 */
	public PageInfo<Post> getPostPage(PageInfo<Post> pageInfo, Long topicId,
			String postTitle);

	/**
	 * 根据贴子,取得贴子HTML
	 * 
	 * @param postId
	 * @return
	 */
	public PostHtml getHtml(Long htmlId);

	/**
	 * 保存贴子HTML
	 * 
	 * @param o
	 * @param postId
	 * @return
	 */
	public PostHtml saveHtml(PostHtml o, Long postId);

	/**
	 * 根据贴子,取得贴子内容
	 * 
	 * @param contentId
	 * @return
	 */
	public PostContent getContent(Long contentId);

	/**
	 * 保存贴子内容
	 * 
	 * @param o
	 * @param postId
	 * @return
	 */
	public PostContent saveContent(PostContent o, Long postId);

	

}
