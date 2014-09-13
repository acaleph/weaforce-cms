package com.weaforce.cms.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.TemplateMobile;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

public class TemplateMobileAction extends AbstractCrudAction<TemplateMobile> {
	private static final long serialVersionUID = -8001536917054216228L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	private TemplateMobile o;
	private Long templateId;

	private String queryName;

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	@Override
	protected void prepareModel() throws Exception {
		o = cmsService.prepareTemplateMobile(o, templateId);
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		pageInfo = cmsService.getTemplateMobilePage(pageInfo, getAdmin()
				.getAccount(), queryName);
		return SUCCESS;
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	public TemplateMobile getModel() {
		return o;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
