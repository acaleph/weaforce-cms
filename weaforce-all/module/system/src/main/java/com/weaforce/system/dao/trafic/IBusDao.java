package com.weaforce.system.dao.trafic;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.trafic.Bus;

public interface IBusDao extends IGenericDao<Bus, Long> {
	/**
	 * Get busList by bus line
	 * 
	 * @param lineId
	 *            : Bus line primary key
	 * @return
	 */
	public List<Bus> getBusList(Long lineId);
}
