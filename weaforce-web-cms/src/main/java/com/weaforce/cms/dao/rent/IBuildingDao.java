package com.weaforce.cms.dao.rent;

import java.util.List;

import com.weaforce.cms.entity.rent.Building;
import com.weaforce.core.dao.IGenericDao;

public interface IBuildingDao extends IGenericDao<Building, Long> {
	/**
	 * 根据城区,取得建筑 list
	 * 
	 * @param zoneId
	 * @return
	 */
	public List<Building> getBuildingListByZone(Long zoneId);

	/**
	 * 根据广告商家,取得建筑 list
	 * 
	 * @param adsId
	 * @return
	 */
	public List<Building> getBuildingListByAds(Long adsId);
}
