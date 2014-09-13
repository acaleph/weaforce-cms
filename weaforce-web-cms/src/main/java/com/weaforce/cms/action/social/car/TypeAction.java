package com.weaforce.cms.action.social.car;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.social.CarShareType;
import com.weaforce.cms.service.ISocialService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

/**
 * Car share type
 * 
 * @author Manfred
 * 
 */
@ParentPackage("default")
@Namespace("/cms/social/car")
public class TypeAction extends AbstractCrudAction<CarShareType> {

	private static final long serialVersionUID = -5834806565476221280L;

	@Autowired
	@Qualifier("socialService")
	private ISocialService socialService;

	private CarShareType type;
	private Long typeId;
	private List<CarShareType> typeList;

	public CarShareType getModel() {
		return type;
	}

	public String input() throws Exception {
		return INPUT;
	}

	public String list() throws Exception {
		typeList = socialService.getTypeList();
		return SUCCESS;
	}

	public String save() throws Exception {
		type = socialService.saveCarShareType(type);
		return list();
	}

	public String delete() throws Exception {
		return list();
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	protected void prepareModel() throws Exception {
		type = socialService.prepareCarShareType(type, typeId);
	}

	public List<CarShareType> getTypeList() {
		return typeList;
	}

}
