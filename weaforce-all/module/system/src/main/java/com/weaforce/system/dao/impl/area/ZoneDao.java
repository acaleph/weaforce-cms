package com.weaforce.system.dao.impl.area;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.entity.area.Zone;
import com.weaforce.system.dao.area.IZoneDao;

@Repository("zoneDao")
public class ZoneDao extends GenericDao<Zone, Long> implements IZoneDao {
	private static final String QUERY_ZONE_BY_CITY = " From Zone a Where a.zoneCity.cityId=? ";

	/**
	 * 根据城市,取得区 list
	 * 
	 * @param cityId
	 * @return
	 */
	public List<Zone> getZoneListByCity(Long cityId) {
		return listQuery(QUERY_ZONE_BY_CITY, cityId);
	}
}
