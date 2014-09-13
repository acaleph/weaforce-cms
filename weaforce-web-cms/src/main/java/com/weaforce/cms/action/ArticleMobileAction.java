package com.weaforce.cms.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.ArticleMobile;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

public class ArticleMobileAction extends AbstractCrudAction<ArticleMobile> {
	private static final long serialVersionUID = -331857394853529990L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;
	
	private ArticleMobile o;
	private Long mobileId;
	private Long categoryId;
	

	@Override
	public ArticleMobile getModel() {
		return o;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		return SUCCESS;
	}

	@Override
	public String save() throws Exception {
		return list();
	}

	@Override
	public String delete() throws Exception {
		return list();
	}

	public void setMobileId(Long mobileId) {
		this.mobileId = mobileId;
	}

	@Override
	protected void prepareModel() throws Exception {
		o = cmsService.prepareArticleMobile(o, mobileId, categoryId);
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
