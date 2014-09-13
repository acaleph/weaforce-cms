package com.weaforce.system.dao.trafic;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.trafic.BusLine;

public interface IBusLineDao extends IGenericDao<BusLine, Long> {
	/**
	 * Get bus line list
	 * 
	 * @return
	 */
	public List<BusLine> getLineList();
}
