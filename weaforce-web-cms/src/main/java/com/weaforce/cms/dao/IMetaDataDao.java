package com.weaforce.cms.dao;

import com.weaforce.cms.entity.MetaData;
import com.weaforce.core.dao.IGenericDao;

public interface IMetaDataDao extends IGenericDao<MetaData, Long> {
	/**
	 * Get meta data by name
	 * 
	 * @param name
	 *            : Name
	 * @return
	 */
	public MetaData getMetaDataByName(String name);
}
