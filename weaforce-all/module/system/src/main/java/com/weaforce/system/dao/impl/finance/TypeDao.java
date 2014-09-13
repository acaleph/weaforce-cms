package com.weaforce.system.dao.impl.finance;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.finance.ITypeDao;
import com.weaforce.system.entity.finance.CostType;

@Repository("costTypeDao")
public class TypeDao extends GenericDao<CostType, Long> implements ITypeDao {
	private static final String QUERY_TYPE_BY_ACTIVE = " From CostType a Where a.account=? And a.typeIsActive=?";

	public List<CostType> getTypePage(String account, String isActive) {
		return listQuery(QUERY_TYPE_BY_ACTIVE, account, isActive);
	}

}
