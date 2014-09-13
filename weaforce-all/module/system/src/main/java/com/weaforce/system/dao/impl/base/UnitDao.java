package com.weaforce.system.dao.impl.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.base.IUnitDao;
import com.weaforce.system.entity.base.Unit;

@Repository("unitDao")
public class UnitDao extends GenericDao<Unit, Long> implements IUnitDao {
	private static final String QUERY_UNIT = " From Unit a ";

	public List<Unit> getUnitList() {
		return listQuery(QUERY_UNIT);
	}
}
