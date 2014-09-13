package com.weaforce.cms.dao.social;

import java.util.List;

import com.weaforce.cms.entity.social.CarShareType;
import com.weaforce.core.dao.IGenericDao;

public interface ICarShareTypeDao extends IGenericDao<CarShareType, Long> {
	/**
	 * Get car share type list
	 * 
	 * @return
	 */
	public List<CarShareType> getTypeList();
}
