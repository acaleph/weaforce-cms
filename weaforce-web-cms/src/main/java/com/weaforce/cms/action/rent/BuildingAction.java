package com.weaforce.cms.action.rent;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.ads.Ads;
import com.weaforce.cms.entity.rent.Building;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.cms.service.IRentService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.entity.area.City;
import com.weaforce.entity.area.Zone;

/**
 * 建筑管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/rent")
public class BuildingAction extends AbstractCrudAction<Building> {
	private static final long serialVersionUID = -6948061616936661438L;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;
	@Autowired
	@Qualifier("rentService")
	private IRentService rentService;

	private Building building;
	private Long buildingId;
	private Long cityId;
	private Long zoneId;
	private Long adsId;

	private List<City> cityList;
	private List<Zone> zoneList;

	private String queryName;

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	
	protected void prepareModel() throws Exception {
		building = rentService.prepareBuilding(building, buildingId);
	}

	
	public String input() throws Exception {
		initList();
		return INPUT;
	}

	/**
	 * 广告商家
	 * 
	 * @param adsId
	 */
	public void setAdsId(Long adsId) {
		this.adsId = adsId;
	}

	
	public String save() throws Exception {
		building = rentService.saveBuilding(building, zoneId, adsId);
		return list();
	}

	
	public String delete() throws Exception {
		return list();
	}

	
	public String list() throws Exception {
		initList();
		pageInfo = rentService.getBuildingPage(pageInfo, zoneId, queryName);
		return SUCCESS;
	}

	/**
	 * 初始化 list
	 */
	public void initList() {
		// 城市
		cityList = adsService.getCityListByProvince(Long.valueOf("1"));
		if (cityId == null)
			if (cityList.size() > 0)
				cityId = cityList.get(0).getCityId();
		// 城区/镇
		if (cityId != null)
			zoneList = adsService.getZoneListByCity(cityId);
		if (zoneId == null)
			if (zoneList.size() > 0)
				zoneId = zoneList.get(0).getZoneId();
	}

	/**
	 * 根据城区，取得建筑 Drop Down List
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getBuildingDDL() throws Exception {
		return StrutsUtil.renderJSON(rentService.getBuildingDDL(zoneId));
	}


	public Building getModel() {
		return building;
	}

	/**
	 * 根据区域/栏目，取得广告 JSON
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAdsDDL() throws Exception {
		return StrutsUtil.renderJSON(adsService
				.getAdsDDL(zoneId, Long.valueOf("49"), true));
	}

	/**
	 * 取得城市 list
	 * 
	 * @return
	 */
	public List<City> getCityList() {
		return cityList;
	}

	/**
	 * 取得区 list
	 * 
	 * @return
	 */
	public List<Zone> getZoneList() {
		return zoneList;
	}

	/**
	 * 广告商家 list
	 * 
	 * @return
	 */
	public List<Ads> getAdsList() {
		return adsService.getAdsListByZoneCategory(zoneId, Long.valueOf("49"),
				false);
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

	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
