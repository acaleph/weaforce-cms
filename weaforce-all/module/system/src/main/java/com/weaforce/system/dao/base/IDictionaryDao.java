package com.weaforce.system.dao.base;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.base.Dictionary;

public interface IDictionaryDao extends IGenericDao<Dictionary, Long> {
	/**
	 * 取得字典 list
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	public List<Dictionary> getDictionaryList(String account);
}
