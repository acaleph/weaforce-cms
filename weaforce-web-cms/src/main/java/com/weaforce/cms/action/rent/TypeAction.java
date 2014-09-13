package com.weaforce.cms.action.rent;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.rent.HouseType;
import com.weaforce.cms.service.IRentService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

/**
 * 房间类型管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/rent")
public class TypeAction extends AbstractCrudAction<HouseType> {

	private static final long serialVersionUID = 400743397037330642L;
	@Autowired
	@Qualifier("rentService")
	private IRentService rentService;

	private HouseType type;
	private Long typeId;

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	
	protected void prepareModel() throws Exception {
		type = rentService.prepareType(type, typeId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		type = rentService.saveType(type);
		return list();
	}

	
	public String delete() throws Exception {
		rentService.deleteType(typeId);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = rentService.getTypePage(pageInfo);
		return SUCCESS;
	}


	public HouseType getModel() {
		return type;
	}

}
