package com.weaforce.cms.action.forum;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Template;
import com.weaforce.cms.entity.forum.Forum;
import com.weaforce.cms.entity.forum.ForumCategory;
import com.weaforce.cms.entity.forum.ForumChannel;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.cms.service.IForumService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

/**
 * 论坛管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/forum")
public class ForumAction extends AbstractCrudAction<Forum> {
	private static final long serialVersionUID = -5192220999576217329L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;
	@Autowired
	@Qualifier("forumService")
	private IForumService forumService;

	private Forum forum;
	private Long forumId;
	private Long channelId;
	private Long categoryId;
	private Long templateId;

	private List<ForumChannel> channelList;

	private String queryName;

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	/**
	 * 栏目
	 * 
	 * @param categoryId
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	protected void prepareModel() throws Exception {
		forum = forumService.prepareForum(forum, forumId, categoryId);
	}

	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 模板
	 * 
	 * @param templateId
	 */
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String save() throws Exception {
		forum = forumService.saveForum(forum, categoryId, templateId);
		return list();
	}

	public String delete() throws Exception {
		forumService.deleteForum(forumId);
		return list();
	}

	public String list() throws Exception {
		channelList = forumService.getChannelListByCity(Long.valueOf("2"));
		if (channelId == null)
			if (channelList.size() > 0)
				channelId = channelList.get(0).getChannelId();
		pageInfo = forumService.getForumPage(pageInfo, categoryId, queryName);
		return SUCCESS;
	}

	public String parse() throws Exception {
		String menu = adsService.getChannelCategoryDDM(Long.valueOf("2"));
		forumService.parseForum(getAdmin().getAccount(), forumId, menu);
		return list();
	}

	public Forum getModel() {
		return forum;
	}

	/**
	 * 取得频道List
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ForumChannel> getChannelList() throws Exception {
		return channelList;
	}

	/**
	 * getCategoryListByChannel
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ForumCategory> getCategoryList() throws Exception {
		return forumService.getCategoryListByChannel(channelId);
	}

	/**
	 * 取得模板list
	 * 
	 * @return
	 */
	public List<Template> getTemplateList() {
		return cmsService.getTemplateList(getAdmin().getAccount(), "1", false);
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
