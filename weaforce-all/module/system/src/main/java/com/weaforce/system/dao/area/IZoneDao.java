package com.weaforce.system.dao.area;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.entity.area.Zone;

public interface IZoneDao extends IGenericDao<Zone, Long> {
	/**
	 * 根据城市,取得区 list
	 * 
	 * @param cityId
	 * @return
	 */
	public List<Zone> getZoneListByCity(Long cityId);
}
