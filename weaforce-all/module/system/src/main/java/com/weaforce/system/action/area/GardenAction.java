package com.weaforce.system.action.area;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.entity.area.Garden;
import com.weaforce.system.service.ISystemService;

@ParentPackage("default")
@Namespace("/system/area")
public class GardenAction extends AbstractCrudAction<Garden> {
	private static final long serialVersionUID = -8549113399205356822L;

	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	private Garden o;
	private Long gardenId;
	private Long cityId;
	private Long zoneId;

	
	public Garden getModel() {
		return o;
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String list() throws Exception {
		pageInfo = systemService.getGardenPage(pageInfo, cityId, zoneId);
		return SUCCESS;
	}

	
	public String save() throws Exception {
		o = systemService.saveGarden(o, zoneId);
		return input();
	}

	
	public String delete() throws Exception {
		systemService.deleteGarden(gardenId);
		return list();
	}


	public void setGardenId(Long gardenId) {
		this.gardenId = gardenId;
	}

	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	
	protected void prepareModel() throws Exception {
		o = systemService.prepareGarden(o, gardenId, zoneId);
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getZoneId() {
		return zoneId;
	}

}
