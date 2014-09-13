package com.weaforce.cms.dao;

import java.util.List;

import com.weaforce.cms.entity.Meta;
import com.weaforce.core.dao.IGenericDao;

public interface IMetaDao extends IGenericDao<Meta, Long> {
	/**
	 * 取得当前帐套下的所有META标签
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	public List<Meta> getMetaList(String account);

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
	public String getMetaValueByKey(String account, String key, String isSystem);

}
