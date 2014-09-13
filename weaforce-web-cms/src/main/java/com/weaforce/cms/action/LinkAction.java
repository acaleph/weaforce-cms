package com.weaforce.cms.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Link;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

@ParentPackage("default")
@Namespace("/cms")
public class LinkAction extends AbstractCrudAction<Link> {
	private static final long serialVersionUID = -5508377991954742194L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	private Link link;
	private Long linkId;

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	
	protected void prepareModel() throws Exception {
		link = cmsService.prepareLink(link, linkId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		link = cmsService.saveLink(link);
		return list();
	}

	
	public String delete() throws Exception {
		cmsService.deleteLink(linkId);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = cmsService.getLinkPage(pageInfo, getAdmin().getAccount());
		return SUCCESS;
	}

	
	public Link getModel() {
		return link;
	}

}
