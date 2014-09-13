package com.weaforce.cms.dao.impl.rent;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.rent.IBuildingDao;
import com.weaforce.cms.entity.rent.Building;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("buildingDao")
public class BuildingDao extends GenericDao<Building, Long> implements
		IBuildingDao {
	private static final String QUERY_BUILDING_BY_ZONE = " From Building a Where a.buildingZone.zoneId =? ";
	private static final String QUERY_BUILDING_BY_ADS = " From Building a Where a.buildingAds.adsId =? ";

	/**
	 * 根据城区,取得建筑 list
	 * 
	 * @param zoneId
	 * @return
	 */
	public List<Building> getBuildingListByZone(Long zoneId) {
		return listQuery(QUERY_BUILDING_BY_ZONE, zoneId);
	}

	/**
	 * 根据广告商家,取得建筑 list
	 * 
	 * @param adsId
	 * @return
	 */
	public List<Building> getBuildingListByAds(Long adsId) {
		return listQuery(QUERY_BUILDING_BY_ADS, adsId);
	}
}
