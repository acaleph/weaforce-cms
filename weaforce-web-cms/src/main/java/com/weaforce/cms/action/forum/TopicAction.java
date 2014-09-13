package com.weaforce.cms.action.forum;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.forum.Forum;
import com.weaforce.cms.entity.forum.Post;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.cms.service.IForumService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;

/**
 * 主题管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/forum")
public class TopicAction extends AbstractCrudAction<Post> {
	private static final long serialVersionUID = -2303750890111943031L;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;
	@Autowired
	@Qualifier("forumService")
	private IForumService forumService;

	private Post topic;
	private Long topicId;
	private Long forumId;
	private String contentContent;

	private Integer pageNumber;
	private String queryTitle;

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	
	protected void prepareModel() throws Exception {
		topic = forumService.prepareTopic(topic, topicId, forumId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		topic = forumService.saveTopic(topic, forumId, contentContent,
				StrutsUtil.getRequest().getRemoteAddr());
		return list();
	}

	
	public String delete() throws Exception {
		forumService.deleteTopic(topicId);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = forumService.getTopicPage(pageInfo, forumId, queryTitle);
		return SUCCESS;
	}

	/**
	 * parse 主题,并通过AJAX展示贴子,文件名称:topic.html,目录与频道index.html相同
	 * 
	 * @return
	 * @throws Exception
	 */
	public String parse() throws Exception {
		String menu = adsService.getChannelCategoryDDM(Long.valueOf("2"));
		forumService.parseTopic(getAdmin().getAccount(), topicId, menu);
		return list();
	}

	/**
	 * 当前页数
	 * 
	 * @param pageNumber
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * 以JSON格式,取得主题 page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String page() throws Exception {
		return StrutsUtil.renderJSON(forumService.getTopicJSON(forumId, queryTitle,
				pageNumber));
	}


	public Post getModel() {
		return topic;
	}

	/**
	 * 取得论坛 list
	 * 
	 * @return
	 */
	public List<Forum> getForumList() {
		return forumService.getForumList();
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	/**
	 * 内容
	 * 
	 * @param contentContent
	 */

	public void setContentContent(String contentContent) {
		this.contentContent = contentContent;
	}

	public String getQueryTitle() {
		return queryTitle;
	}

	public void setQueryTitle(String queryTitle) {
		this.queryTitle = queryTitle;
	}

}
