package com.weaforce.system.dao.impl.trafic;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.trafic.IBusLineDao;
import com.weaforce.system.entity.trafic.BusLine;

@Repository("busLineDao")
public class BusLineDao extends GenericDao<BusLine, Long> implements
		IBusLineDao {
	private static final String QUERY_BUSLINE = " From BusLine a  ";

	/**
	 * Get bus line list
	 * 
	 * @return
	 */
	public List<BusLine> getLineList() {
		return listQuery(QUERY_BUSLINE);
	}

}
