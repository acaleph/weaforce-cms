package com.weaforce.system.dao.finance;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.finance.CostType;

public interface ITypeDao extends IGenericDao<CostType, Long> {

	public List<CostType> getTypePage(String account, String isActive);

}
