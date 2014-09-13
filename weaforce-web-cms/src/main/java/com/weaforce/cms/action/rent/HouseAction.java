package com.weaforce.cms.action.rent;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.ads.Ads;
import com.weaforce.cms.entity.rent.Building;
import com.weaforce.cms.entity.rent.House;
import com.weaforce.cms.entity.rent.HouseType;
import com.weaforce.cms.entity.rent.RentTag;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.cms.service.IRentService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.entity.area.City;
import com.weaforce.entity.area.Zone;

/**
 * 房间管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/rent")
public class HouseAction extends AbstractCrudAction<House> {
	private static final long serialVersionUID = -1796248041096808967L;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;
	@Autowired
	@Qualifier("rentService")
	private IRentService rentService;

	private House house;
	private Long houseId;
	private Long buildingId;
	private Long cityId;
	private Long zoneId;
	private Long adsId;

	private List<City> cityList;
	private List<Zone> zoneList;
	private List<Ads> adsList;

	private String queryTitle;

	private Long typeId;
	private Long tagId;
	private Integer pageNumber;

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	
	protected void prepareModel() throws Exception {
		house = rentService.prepareHouse(house, houseId, buildingId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		house = rentService.saveHouse(house, buildingId, typeId, tagId);
		return list();
	}

	
	public String delete() throws Exception {
		rentService.deleteHouse(houseId);
		return list();
	}

	/**
	 * 更新房间
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if (houseId != null)
			rentService.updateHouse(houseId);
		return list();
	}

	
	public String list() throws Exception {
		initList();
		if (buildingId != null)
			pageInfo = rentService.getHousePage(pageInfo, buildingId,
					queryTitle);
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
		// 广告商家
		if (zoneId != null)
			adsList = adsService.getAdsListByZoneCategory(zoneId, Long
					.valueOf("49"), false);
		if (adsId == null)
			if (adsList.size() > 0)
				adsId = adsList.get(0).getAdsId();

	}

	/**
	 * 建筑
	 * 
	 * @param buildingId
	 */
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	/**
	 * 房间类型
	 * 
	 * @param typeId
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	/**
	 * 出租价格式
	 * 
	 * @param tagId
	 */
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	/**
	 * 当前页数
	 * 
	 * @param pageNumber
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * 以JSON格式,取得房间记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String page() throws Exception {
		return StrutsUtil.renderJSON(rentService.getHouseJSON(buildingId, typeId, tagId,
				pageNumber));
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
		return adsList;
	}

	/**
	 * 由house.jsp页面,取得广告商家主键
	 * 
	 * @param adsId
	 */
	public void setAdsId(Long adsId) {
		this.adsId = adsId;
	}

	/**
	 * 根据广告商家,取得建筑 list
	 * 
	 * @return
	 */
	public List<Building> getBuildingList() {
		return rentService.getBuildingListByAds(adsId);
	}

	/**
	 * 取得房间价格标签 list
	 * 
	 * @return
	 */
	public List<RentTag> getTagList() throws Exception {
		return rentService.getTagList();
	}

	/**
	 * 取得房间价格标签 list
	 * 
	 * @return
	 */
	public List<HouseType> getTypeList() throws Exception {
		return rentService.getTypeList();
	}


	public Long getAdsId() {
		return adsId;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public House getModel() {
		return house;
	}

	public String getQueryTitle() {
		return queryTitle;
	}

	public void setQueryTitle(String queryTitle) {
		this.queryTitle = queryTitle;
	}

}
