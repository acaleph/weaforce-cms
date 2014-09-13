package com.weaforce.cms.dao.impl.social;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.social.ICarShareTypeDao;
import com.weaforce.cms.entity.social.CarShareType;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("carShareTypeDao")
public class CarShareTypeDao extends GenericDao<CarShareType, Long> implements
		ICarShareTypeDao {
	private static final String QUERY_TYPE = " From CarShareType a ";

	/**
	 * Get car share type list
	 * 
	 * @return
	 */
	public List<CarShareType> getTypeList() {
		return listQuery(QUERY_TYPE);
	}
}
