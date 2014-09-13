package com.weaforce.system.dao.impl.trafic;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.trafic.IBusDao;
import com.weaforce.system.entity.trafic.Bus;

@Repository("busDao")
public class BusDao extends GenericDao<Bus, Long> implements IBusDao {
	private static final String QUERY_BUS = " From Bus a where busLine.lineId=? ";

	/**
	 * Get busList by bus line
	 * 
	 * @param lineId
	 *            : Bus line primary key
	 * @return
	 */
	public List<Bus> getBusList(Long lineId) {
		return listQuery(QUERY_BUS, lineId);
	}
}
