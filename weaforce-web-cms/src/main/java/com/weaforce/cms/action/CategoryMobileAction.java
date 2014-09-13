package com.weaforce.cms.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.CategoryMobile;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

public class CategoryMobileAction extends AbstractCrudAction<CategoryMobile> {
	private static final long serialVersionUID = 7361390224063964635L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	private CategoryMobile o;
	private Long categoryId;
	private Long channelId;
	private Long templateId;
	private Long articleTemplateId;

	private String queryName;

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	protected void prepareModel() throws Exception {
		o = cmsService.prepareCategoryMobile(o, categoryId, channelId);
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		pageInfo = cmsService.getCategoryMobilePage(pageInfo, getAdmin()
				.getAccount(), channelId, queryName);
		return SUCCESS;
	}

	@Override
	public String save() throws Exception {
		o = cmsService.saveCategoryMobile(o, channelId, templateId,
				articleTemplateId);
		return list();
	}

	@Override
	public String delete() throws Exception {
		return list();
	}

	@Override
	public CategoryMobile getModel() {
		return o;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
