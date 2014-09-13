package com.weaforce.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.IMetaDao;
import com.weaforce.cms.entity.Meta;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("metaDao")
public class MetaDao extends GenericDao<Meta, Long> implements IMetaDao {
	private static final String QUERY_META = " From Meta a Where a.account=? ";
	private static final String QUERY_META_BY_KEY_SYSTEM = " From Meta a Where a.account=? And a.metaKey=? And a.metaIsSystem=? ";
	/**
	 * 取得当前帐套下的所有META标签
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	public List<Meta> getMetaList(String account) {
		return listQuery(QUERY_META, account);
	}

	/**
	 * 根据提供的Key,取得指定Meta值
	 * 
	 * @param account
	 *            帐套
	 * @param key
	 *            关键字
	 * @param isSystem
	 *            是否系统
	 * @return
	 */
	public String getMetaValueByKey(String account, String key, String isSystem) {
		Meta o = loadEntity(QUERY_META_BY_KEY_SYSTEM, account, key, isSystem);
		return o.getMetaValue();
	}
}
