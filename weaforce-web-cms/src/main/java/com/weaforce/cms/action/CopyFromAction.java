package com.weaforce.cms.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.CopyFrom;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

@ParentPackage("default")
@Namespace("/cms")
public class CopyFromAction extends AbstractCrudAction<CopyFrom> {

	private static final long serialVersionUID = 8177479085651452256L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	private CopyFrom from;
	private Long fromId;

	private String queryName;

	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}

	
	protected void prepareModel() throws Exception {
		from = cmsService.prepareFrom(from, fromId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		from = cmsService.saveCopyFrom(from);
		return list();
	}

	
	public String delete() throws Exception {
		cmsService.deleteCopyFrom(fromId);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = cmsService.getCopyFromPage(pageInfo, queryName);
		return SUCCESS;
	}


	public CopyFrom getModel() {
		return from;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
