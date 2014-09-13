package com.weaforce.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weaforce.cms.dao.IMetaDao;
import com.weaforce.cms.dao.IRegistryDao;
import com.weaforce.cms.dao.ITemplateDao;
import com.weaforce.cms.dao.forum.ICategoryDao;
import com.weaforce.cms.dao.forum.IChannelDao;
import com.weaforce.cms.dao.forum.IForumDao;
import com.weaforce.cms.dao.forum.IPostContentDao;
import com.weaforce.cms.dao.forum.IPostDao;
import com.weaforce.cms.dao.forum.IPostHtmlDao;
import com.weaforce.cms.entity.Registry;
import com.weaforce.cms.entity.forum.Forum;
import com.weaforce.cms.entity.forum.ForumCategory;
import com.weaforce.cms.entity.forum.ForumChannel;
import com.weaforce.cms.entity.forum.Post;
import com.weaforce.cms.entity.forum.PostContent;
import com.weaforce.cms.entity.forum.PostHtml;
import com.weaforce.cms.service.IForumService;
import com.weaforce.cms.util.ForumParser;
import com.weaforce.cms.util.HtmlParser;
import com.weaforce.core.util.PageInfo;
import com.weaforce.core.util.StringUtil;
import com.weaforce.system.dao.area.ICityDao;

/**
 * 论坛服务
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Service("forumService")
@Transactional(rollbackFor = Exception.class)
public class ForumService implements IForumService {
	@Autowired
	@Qualifier("cityDao")
	private ICityDao cityDao;
	@Autowired
	@Qualifier("templateDao")
	private ITemplateDao templateDao;
	@Autowired
	@Qualifier("metaDao")
	private IMetaDao metaDao;
	@Autowired
	@Qualifier("registryDao")
	private IRegistryDao registryDao;
	@Autowired
	@Qualifier("forumChannelDao")
	private IChannelDao channelDao;
	@Autowired
	@Qualifier("forumCategoryDao")
	private ICategoryDao categoryDao;
	@Autowired
	@Qualifier("forumDao")
	private IForumDao forumDao;
	@Autowired
	@Qualifier("forumPostDao")
	private IPostDao postDao;
	@Autowired
	@Qualifier("forumPostHtmlDao")
	private IPostHtmlDao htmlDao;
	@Autowired
	@Qualifier("forumPostContentDao")
	private IPostContentDao contentDao;

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
	@Transactional(readOnly = true)
	public ForumChannel prepareChannel(ForumChannel o, Long channelId,
			Long cityId) {
		if (channelId == null) {
			o = new ForumChannel();
			if (cityId != null)
				o.setChannelCity(cityDao.loadEntity(cityId));
		} else
			o = channelDao.loadEntity(channelId);
		return o;
	}

	/**
	 * 取得频道
	 * 
	 * @param channelId
	 * @return
	 */
	@Transactional(readOnly = true)
	public ForumChannel getChannel(Long channelId) {
		return channelDao.loadEntity(channelId);
	}

	/**
	 * 保存频道
	 * 
	 * @param o
	 * @param cityId
	 * @param templateId
	 * @return
	 */
	public ForumChannel saveChannel(ForumChannel o, Long cityId, Long templateId) {
		if (cityId != null)
			o.setChannelCity(cityDao.loadEntity(cityId));
		else
			o.setChannelCity(null);
		if (templateId != null)
			o.setChannelTemplate(templateDao.loadEntity(templateId));
		else
			o.setChannelTemplate(null);
		return channelDao.save(o);
	}

	/**
	 * 删除频道
	 * 
	 * @param channelId
	 */
	public void deleteChannel(Long channelId) {
		channelDao.delete(channelId);
	}

	/**
	 * parse 频道
	 * 
	 * @param account
	 * @param channelId
	 * @param menu
	 *            菜单
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public void parseChannel(String account, Long channelId, String menu)
			throws Exception {
		ForumChannel channel = channelDao.loadEntity(channelId);
		String template = channel.getChannelTemplate().getTemplateContent();
		String parsePath = channel.getChannelParsePath();
		// 广告菜单
		template = StringUtil.replaceAll(template, "{$MENU$}", menu);
		template = ForumParser.parseChannel(channel, template);
		// 替换模板中用户自定义meta信息
		template = HtmlParser.replaceMeta(template, metaDao.getMetaList(account));
		// 保存论坛文件
		HtmlParser.saveHtml(template, parsePath, "index.html");
	}

	/**
	 * 根据城市,取得频道 list
	 * 
	 * @param cityId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<ForumChannel> getChannelListByCity(Long cityId) {
		return channelDao.getChannelListByCity(cityId);
	}

	/**
	 * 取得频道 page
	 * 
	 * @param pageInfo
	 * @param cityId
	 * @param channelName
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<ForumChannel> getChannelPage(
			PageInfo<ForumChannel> pageInfo, Long cityId, String channelName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From ForumChannel a Where a.channelCity.cityId=" + cityId);
		if (StringUtil.isNotEmpty(channelName))
			sb.append(" And a.channelName like " + "'%" + channelName + "%'");
		pageInfo = channelDao.listQuery(pageInfo, "Select Count(*)"
				+ sb.toString(), sb.toString() + " Order by a.channelOrder ");
		return pageInfo;
	}

	/**
	 * 预处理栏目
	 * 
	 * @param o
	 * @param categoryId
	 * @param channelId
	 * @return
	 */
	@Transactional(readOnly = true)
	public ForumCategory prepareCategory(ForumCategory o, Long categoryId,
			Long channelId) {
		if (categoryId == null) {
			o = new ForumCategory();
			if (channelId != null)
				o.setCategoryChannel(channelDao.loadEntity(channelId));
		} else
			o = categoryDao.loadEntity(categoryId);
		return o;
	}

	/**
	 * 取得栏目
	 * 
	 * @param categoryId
	 * @return
	 */
	@Transactional(readOnly = true)
	public ForumCategory getCategory(Long categoryId) {
		return categoryDao.loadEntity(categoryId);
	}

	/**
	 * 保存栏目
	 * 
	 * @param o
	 * @param channelId
	 * @return
	 */
	public ForumCategory saveCategory(ForumCategory o, Long channelId,
			Long templateId) {
		if (channelId != null)
			o.setCategoryChannel(channelDao.loadEntity(channelId));
		else
			o.setCategoryChannel(null);
		if (templateId != null)
			o.setCategoryTemplate(templateDao.loadEntity(templateId));
		else
			o.setCategoryTemplate(null);
		return categoryDao.save(o);
	}

	/**
	 * 删除栏目
	 * 
	 * @param categoryId
	 */
	public void deleteCategory(Long categoryId) {
		categoryDao.delete(categoryId);
	}

	/**
	 * 根据频道，取得栏目 JSON
	 * 
	 * @param channelId
	 *            频道
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getCategoryDDL(Long channelId) {
		StringBuffer sb = new StringBuffer();
		List<ForumCategory> categoryList = categoryDao
				.getCategoryByChannel(channelId);
		for (ForumCategory o : categoryList)
			if (sb.length() == 0)
				sb.append("[{\"value\":\"" + o.getCategoryId()
						+ "\",\"caption\":\"" + o.getCategoryName() + "\"}");
			else
				sb.append(",{\"value\":\"" + o.getCategoryId()
						+ "\",\"caption\":\"" + o.getCategoryName() + "\"}");
		if (sb.length() > 0)
			sb.append("]");
		return sb.toString();
	}

	/**
	 * 根据频道,取得栏目 list
	 * 
	 * @param channelId
	 * @return
	 */
	public List<ForumCategory> getCategoryListByChannel(Long channelId) {
		return categoryDao.getCategoryByChannel(channelId);
	}

	/**
	 * 取得栏目 page
	 * 
	 * @param pageInfo
	 * @param channelId
	 * @param categoryName
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<ForumCategory> getCategoryPage(
			PageInfo<ForumCategory> pageInfo, Long channelId,
			String categoryName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From ForumCategory a Where a.categoryChannel.channelId="
				+ channelId);
		if (StringUtil.isNotEmpty(categoryName))
			sb
					.append(" Where a.channelName like " + "'%" + categoryName
							+ "%'");
		pageInfo = categoryDao.listQuery(pageInfo, "Select Count(*)"
				+ sb.toString(), sb.toString() + " Order by a.categoryOrder ");
		return pageInfo;
	}

	/**
	 * 论坛预处理
	 * 
	 * @param o
	 * @param forumId
	 * @param categoryId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Forum prepareForum(Forum o, Long forumId, Long categoryId) {
		if (forumId == null) {
			o = new Forum();
			if (categoryId != null)
				o.setForumCategory(categoryDao.loadEntity(categoryId));
		} else
			o = forumDao.loadEntity(forumId);
		return o;
	}

	/**
	 * 取得论坛
	 * 
	 * @param forumId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Forum getForum(Long forumId) {
		return forumDao.loadEntity(forumId);
	}

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
	public Forum saveForum(Forum o, Long categoryId, Long templateId) {
		if (categoryId != null)
			o.setForumCategory(categoryDao.loadEntity(categoryId));
		else
			o.setForumCategory(null);
		if (templateId != null)
			o.setForumTemplate(templateDao.loadEntity(templateId));
		else
			o.setForumTemplate(null);
		return forumDao.save(o);
	}

	/**
	 * 删除论坛
	 * 
	 * @param forumId
	 */
	public void deleteForum(Long forumId) {
		forumDao.delete(forumId);
	}

	/**
	 * parse 论坛,并通过AJAX展示主题
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
			throws Exception {
		Forum forum = forumDao.loadEntity(forumId);
		ForumCategory category = forum.getForumCategory();
		String template = category.getCategoryTemplate().getTemplateContent();
		String parsePath = category.getCategoryParsePath();
		// 保存URL
		forum.setForumUrl(category.getCategoryUrl() + "/"
				+ forum.getForumName() + ".html");
		// 广告菜单
		template = StringUtil.replaceAll(template, "{$MENU$}", menu);
		template = ForumParser.parseForum(forum, category, template);
		// 替换模板中用户自定义meta信息
		template = HtmlParser.replaceMeta(template, metaDao.getMetaList(account));
		// 保存论坛文件
		HtmlParser.saveHtml(template, parsePath, forum.getForumFileName()
				+ ".html");
		forumDao.save(forum);
	}

	/**
	 * 取得论坛 list
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Forum> getForumList() {
		return forumDao.getForumList();
	}

	/**
	 * 取得论坛 Page
	 * 
	 * @param pageInfo
	 * @param categoryId
	 * @param forumName
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<Forum> getForumPage(PageInfo<Forum> pageInfo,
			Long categoryId, String forumName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Forum a Where a.forumCategory.categoryId="
				+ categoryId);
		if (StringUtil.isNotEmpty(forumName))
			sb.append(" And a.forumName like " + "'%" + forumName + "%'");
		pageInfo = forumDao.listQuery(pageInfo, "Select Count(*)"
				+ sb.toString(), sb.toString() + " Order by a.forumName ");
		return pageInfo;
	}

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
	@Transactional(readOnly = true)
	public Post prepareTopic(Post o, Long topicId, Long forumId) {
		if (topicId == null) {
			o = new Post();
			if (forumId != null)
				o.setPostForum(forumDao.loadEntity(forumId));
		} else
			o = postDao.loadEntity(topicId);
		return o;
	}

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
	@Transactional(readOnly = true)
	public Post preparePost(Post o, Long postId, Long parentId) {
		if (postId == null) {
			o = new Post();
			if (parentId != null)
				o.setPostParent(postDao.loadEntity(parentId));
		} else
			o = postDao.loadEntity(postId);
		return o;
	}

	/**
	 * 取得贴子
	 * 
	 * @param postId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Post getPost(Long postId) {
		return postDao.loadEntity(postId);
	}

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
			String remoteIp) {
		if (forumId != null)
			topic.setPostForum(forumDao.loadEntity(forumId));
		else
			topic.setPostForum(null);
		topic.setLastUpdateTime(System.currentTimeMillis());
		PostContent content = new PostContent();
		PostHtml html = new PostHtml();

		if (topic.getPostId() != null) {
			content = contentDao.getContentByPost(topic.getPostId());
			html = htmlDao.getHtmlByPost(topic.getPostId());
		} else {
			// 新主题
			Registry registry = topic.getPostRegistry();
			if (registry != null) {
				registry.addTopicCount();
				registryDao.save(registry);
			}
		}
		topic = postDao.save(topic);
		// PostContent
		content = savePostContent(content, topic, remoteIp, postContent);
		// PostHtml
		html = savePostHtml(html, topic, ForumParser.parsePost(topic));
		// content = contentDao.save(content);
		// html.setHtmlTopicId(topic.getPostId());
		// html = htmlDao.save(html);
		return topic;
	}

	/**
	 * 保存贴子
	 * 
	 * @param o
	 * @param contentContent
	 *            贴子内容
	 * @param remoteIp
	 *            用户IP
	 * @return
	 */
	public Post savePost(Post o, Long parentId, String postContent,
			String remoteIp) {
		// *************贴子信息*******************
		if (o.getPostId() == null) {
			Registry registry = o.getPostRegistry();
			registry.addPostCount();
			registryDao.save(registry);
		}
		if (parentId != null) {
			Post parent = postDao.loadEntity(parentId);
			o.setPostParent(parent);
			if (null == o.getPostTitle())
				o.setPostTitle(parent.getPostTitle());
		} else
			o.setPostParent(null); // 不可能为空的
		o.setLastUpdateTime(System.currentTimeMillis());
		o = postDao.save(o);
		// *************贴子信息*******************
		PostContent content = new PostContent();
		PostHtml html = new PostHtml();

		if (o.getPostId() != null) {
			content = contentDao.getContentByPost(o.getPostId());
			html = htmlDao.getHtmlByPost(o.getPostId());
		}
		// PostContent
		content = savePostContent(content, o, remoteIp, postContent);
		// PostHtml
		html = savePostHtml(html, o, ForumParser.parsePost(o));
		return o;
	}

	/**
	 * 保存贴子内容:该方法没有出现在服务接口中
	 * 
	 * @param o
	 * @param postId
	 *            贴子主键
	 * @return
	 */
	public PostContent savePostContent(PostContent o, Post post,
			String remoteIp, String postContent) {
		o.setContentIp(remoteIp);
		o.setContentContent(postContent);
		o.setContentPost(post);
		post.setPostContent(o);
		return contentDao.save(o);
	}

	/**
	 * 保存贴子HTML:该方法没有出现在服务接口中
	 * 
	 * @param o
	 * @param postId
	 *            贴子主键
	 * @return
	 */
	public PostHtml savePostHtml(PostHtml o, Post post, String htmlContent) {
		o.setHtmlContent(htmlContent);
		o.setHtmlPost(post);
		post.setPostHtml(o);
		// 取得postTopicId(主题贴):只有主题贴子，论坛不为null
		if (post.getPostForum() != null)
			o.setHtmlTopicId(post.getPostId());
		return htmlDao.save(o);
	}

	/**
	 * 删除主题
	 * 
	 * @param topicId
	 */
	public void deleteTopic(Long topicId) {
		postDao.delete(topicId);
	}

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
	@Transactional(readOnly = true)
	public void parseTopic(String account, Long topicId, String menu)
			throws Exception {
		Post topic = postDao.loadEntity(topicId);
		String template = topic.getPostForum().getForumTemplate()
				.getTemplateContent();
		String parsePath = topic.getPostForum().getForumCategory()
				.getCategoryChannel().getChannelParsePath();
		// 广告菜单
		template = StringUtil.replaceAll(template, "{$MENU$}", menu);
		// 替换模板中用户自定义meta信息
		template = HtmlParser.replaceMeta(template, metaDao.getMetaList(account));
		// 保存论坛文件
		HtmlParser.saveHtml(template, parsePath, "post.html");
	}

	/**
	 * 删除贴子
	 * 
	 * @param postId
	 */
	public void deletePost(Long postId) {
		postDao.delete(postId);
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
	@Transactional(readOnly = true)
	public String getTopicJSON(Long forumId, String topicTitle,
			Integer pageNumber) {
		return postDao.getTopicJSON(forumId, topicTitle, pageNumber);
	}

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
	public String getPostPageHTML(Long topicId, String postTitle, Integer pageNumber) {
		List<Post> postList = postDao.getPostPageByTopicTitle(topicId, postTitle,
				pageNumber);
		StringBuffer contentPost = new StringBuffer();
		int i = 0;
		for (Post post : postList) {
			String content = post.getPostHtml().getHtmlContent();
			// 替换贴子所在当前页位置
			content = StringUtil.replaceAll(content, "{$NUMBER$}", String
					.valueOf(i++));
			// 替换发贴人状态
			content = StringUtil
					.replaceAll(content, "{$REGISTRYSTATUS$}", "离线");
			content = StringUtil.replaceAll(content, "{$REGISTRYSTATUSCLASS$}",
					"offline");
			contentPost.append(content);
		}
		return contentPost.toString();

	}
	/**
	 * 以JSON格式，取得贴子分页信息
	 * @param topicId 论坛
	 * @param psotTitle 标题
	 * @param pageNumber 当前页数
	 * @return
	 */
	public String getPostPageJSON(Long topicId, String postTitle,
			Integer pageNumber){
		return postDao.getPostPageJSON(topicId, postTitle, pageNumber);
	}

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
	@Transactional(readOnly = true)
	public PageInfo<Post> getTopicPage(PageInfo<Post> pageInfo, Long forumId,
			String postTitle) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Post a Where a.postForum.forumId=" + forumId);
		if (StringUtil.isNotEmpty(postTitle))
			sb.append(" And a.postTitle like " + "'%" + postTitle + "%'");
		pageInfo = postDao.listQuery(pageInfo, "Select Count(*)"
				+ sb.toString(), sb.toString() + " Order by a.postTitle ");
		return pageInfo;
	}

	/**
	 * 取得贴子 page
	 * 
	 * @param pageInfo
	 * @param topicId
	 * @param postTitle
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<Post> getPostPage(PageInfo<Post> pageInfo, Long topicId,
			String postTitle) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Post a Where a.postHtml.postTopicId=" + topicId);
		if (StringUtil.isNotEmpty(postTitle))
			sb.append(" And a.postTitle like " + "'%" + postTitle + "%'");
		pageInfo = postDao.listQuery(pageInfo, "Select Count(*)"
				+ sb.toString(), sb.toString() + " Order by a.postTitle ");
		return pageInfo;
	}

	/**
	 * 根据贴子,取得贴子HTML
	 * 
	 * @param htmlId
	 * @return
	 */
	public PostHtml getHtml(Long htmlId) {
		return htmlDao.loadEntity(htmlId);
	}

	/**
	 * 保存贴子HTML
	 * 
	 * @param o
	 * @param postId
	 * @return
	 */
	public PostHtml saveHtml(PostHtml o, Long postId) {
		if (postId != null)
			o.setHtmlPost(postDao.loadEntity(postId));
		else
			o.setHtmlPost(null);
		return htmlDao.save(o);
	}

	/**
	 * 根据贴子,取得贴子内容
	 * 
	 * @param contentId
	 * @return
	 */
	public PostContent getContent(Long contentId) {
		return contentDao.loadEntity(contentId);
	}

	/**
	 * 保存贴子内容
	 * 
	 * @param o
	 * @param postId
	 * @return
	 */
	public PostContent saveContent(PostContent o, Long postId) {
		if (postId != null)
			o.setContentPost(postDao.loadEntity(postId));
		else
			o.setContentPost(null);
		return contentDao.save(o);
	}
}
