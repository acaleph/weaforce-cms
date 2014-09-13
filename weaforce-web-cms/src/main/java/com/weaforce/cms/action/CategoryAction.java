package com.weaforce.cms.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Category;
import com.weaforce.cms.entity.Channel;
import com.weaforce.cms.entity.Template;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;

/**
 * 栏目管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms")
public class CategoryAction extends AbstractCrudAction<Category> {
	private static final long serialVersionUID = -7951569357874160L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	private Category category;
	private Long categoryId;
	private Long channelId;

	private List<Channel> channelList;
	private List<Template> templateList;

	private String queryName;

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	protected void prepareModel() throws Exception {
	}

	public String input() throws Exception {
		templateList = cmsService.getTemplateList(getAdmin()
				.getAccount(), "1", true);
		Template template = category.getCategoryTemplate();
		if (template != null)
			if (templateList.indexOf(template) == -1)
				templateList.add(template);
		channelList = cmsService.getChannelList(getAdmin().getAccount(), "1");
		Channel channel = category.getCategoryChannel();
		if (channel != null)
			if (channelList.indexOf(channel) == -1)
				channelList.add(channel);
		return INPUT;
	}


	/**
	 * 保存栏目
	 */

	public String save() throws Exception {
		//category = cmsService.saveCategory(category, channelId, templateId,
		//		articleTemplateId);
		return list();
	}

	public String list() throws Exception {
		channelList = cmsService.getChannelList(getAdmin().getAccount(), "1");
		if (channelId == null)
			if (channelList.size() > 0)
				channelId = channelList.get(0).getChannelId();
		return SUCCESS;
	}

	public String lock() throws Exception {
		return list();
	}

	public String delete() throws Exception {
		cmsService.deleteCategory(categoryId);
		return list();
	}

	/**
	 * 发布文章栏目到HTML,只发布文章列表类,文章类已经在生成文章时自动发布
	 * 
	 * @return
	 * @throws Exception
	 */
	public String parse() throws Exception {
		cmsService.parseCategory(null, categoryId, getAdmin().getUserCity()
				.getCityNameCn());
		return list();
	}

	/**
	 * 取得栏目下拉列表JSON格式数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getCategoryDDL() throws Exception {
		return StrutsUtil.renderJSON(cmsService.getCategoryDDL(getAdmin()
				.getUserId(), channelId));
	}

	/**
	 * 根据频道取得栏目JSON格式数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getCategoryJSON() throws Exception {
		return StrutsUtil.renderJSON(cmsService.getCategoryJSON(channelId));
	}

	/**
	 * 取得频道list
	 * 
	 * @return
	 */
	public List<Channel> getChannelList() {
		return channelList;
	}

	/**
	 * 取得模板list
	 * 
	 * @return
	 */
	public List<Template> getTemplateList() {
		return templateList;
	}

	/**
	 * 取得当前帐套下的用户JSON格式数据
	 * 
	 * @return
	 */

	public String getUserLoginJSON() {
		return cmsService.getUserLoginJSON(getAdmin().getAccount());
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Category getModel() {
		return category;
	}

}