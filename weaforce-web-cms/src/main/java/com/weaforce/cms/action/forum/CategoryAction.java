package com.weaforce.cms.action.forum;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Template;
import com.weaforce.cms.entity.forum.ForumChannel;
import com.weaforce.cms.entity.forum.ForumCategory;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.cms.service.IForumService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;

/**
 * 论坛栏目管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/forum")
public class CategoryAction extends AbstractCrudAction<ForumCategory> {
	private static final long serialVersionUID = 3757333728460819470L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;
	@Autowired
	@Qualifier("forumService")
	private IForumService forumService;

	private ForumCategory category;
	private Long categoryId;
	private Long channelId;
	private Long templateId;

	private String queryName;

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	protected void prepareModel() throws Exception {
		category = forumService
				.prepareCategory(category, categoryId, channelId);
	}

	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 所属频道
	 * 
	 * @param channelId
	 */
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
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
		category = forumService.saveCategory(category, channelId, templateId);
		return list();
	}

	public String delete() throws Exception {
		forumService.deleteCategory(categoryId);
		return list();
	}

	public String list() throws Exception {
		pageInfo = forumService.getCategoryPage(pageInfo, channelId, queryName);
		return SUCCESS;
	}

	public ForumCategory getModel() {
		return category;
	}

	/**
	 * 根据频道，取得栏目 JSON
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getCategoryDDL() throws Exception {
		return StrutsUtil.renderJSON(forumService.getCategoryDDL(channelId));
	}

	/**
	 * 取得频道 list
	 * 
	 * @return
	 */
	public List<ForumChannel> getChannelList() {
		return forumService.getChannelListByCity(Long.valueOf("2"));
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

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
