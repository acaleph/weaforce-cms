package com.weaforce.system.dao.base;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.base.Unit;

public interface IUnitDao extends IGenericDao<Unit, Long> {
	public List<Unit> getUnitList();
}
