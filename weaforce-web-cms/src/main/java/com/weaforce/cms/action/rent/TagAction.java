package com.weaforce.cms.action.rent;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.rent.RentTag;
import com.weaforce.cms.service.IRentService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

/**
 * 出租标签管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/rent")
public class TagAction extends AbstractCrudAction<RentTag> {
	private static final long serialVersionUID = -6917012557730301561L;
	@Autowired
	@Qualifier("rentService")
	private IRentService rentService;
	
	private RentTag tag;
	private Long tagId;

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	
	protected void prepareModel() throws Exception {
		tag = rentService.prepareTag(tag, tagId);
	}

	
	public String input() throws Exception {
		
		return INPUT;
	}

	
	public String save() throws Exception {
		tag = rentService.saveTag(tag);
		return list();
	}

	
	public String delete() throws Exception {
		rentService.deleteTag(tagId);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = rentService.getTagPage(pageInfo);
		return SUCCESS;
	}


	public RentTag getModel() {
		return tag;
	}

}
