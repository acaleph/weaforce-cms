package com.weaforce.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.IMetaDataDao;
import com.weaforce.cms.entity.MetaData;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("metaDataDao")
public class MetaDataDao extends GenericDao<MetaData, Long> implements
		IMetaDataDao {
	private static final String QUERY_META_DATA_BY_NAME = " From MetaData a Where a.dataName=?";

	/**
	 * Get meta data by name
	 * 
	 * @param name
	 *            : Name
	 * @return
	 */
	public MetaData getMetaDataByName(String name) {
		return loadEntity(QUERY_META_DATA_BY_NAME, name);
	}
}
