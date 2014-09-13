package com.weaforce.cms.action.social.car;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.social.CarShare;
import com.weaforce.cms.service.ISocialService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.entity.area.City;

/**
 * Car share
 * 
 * @author Manfred
 * 
 */
@ParentPackage("default")
@Namespace("/cms/social/car")
public class ShareAction extends AbstractCrudAction<CarShare> {

	private static final long serialVersionUID = -3147568829226096748L;

	@Autowired
	@Qualifier("socialService")
	private ISocialService socialService;

	private CarShare share;
	private Long shareId;
	private Long carId;

	private Long typeId;
	private Long cityFromId;
	private Long cityToId;
	private Long zoneFromId;
	private Long zoneToId;

	public CarShare getModel() {
		return share;
	}

	public String input() throws Exception {
		return INPUT;
	}

	public String list() throws Exception {
		pageInfo = socialService.getCarSharePage(pageInfo, typeId, cityFromId,
				cityToId, zoneFromId, zoneToId);
		return SUCCESS;
	}

	public String save() throws Exception {
		share = socialService.saveCarShare(share, typeId, carId, cityFromId,
				cityToId, zoneFromId, zoneToId);
		return list();
	}

	public String delete() throws Exception {
		return list();
	}

	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}

	protected void prepareModel() throws Exception {
		share = socialService.prepareCarShare(share, carId, shareId);
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getCityFromId() {
		return cityFromId;
	}

	public void setCityFromId(Long cityFromId) {
		this.cityFromId = cityFromId;
	}

	public Long getCityToId() {
		return cityToId;
	}

	public void setCityToId(Long cityToId) {
		this.cityToId = cityToId;
	}

	public Long getZoneFromId() {
		return zoneFromId;
	}

	public void setZoneFromId(Long zoneFromId) {
		this.zoneFromId = zoneFromId;
	}

	public Long getZoneToId() {
		return zoneToId;
	}

	public void setZoneToId(Long zoneToId) {
		this.zoneToId = zoneToId;
	}

	public List<City> getCityList() {
		return socialService.getCityList();
	}

}
