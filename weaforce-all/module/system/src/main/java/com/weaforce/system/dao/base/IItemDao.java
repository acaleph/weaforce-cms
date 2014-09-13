package com.weaforce.system.dao.base;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.base.DictionaryItem;

public interface IItemDao extends IGenericDao<DictionaryItem, Long> {
	/**
	 * 取得项目 list
	 * 
	 * @param dictionaryId
	 *            字典主键
	 * @see 2 Invoice Type List
	 * @return
	 */
	public List<DictionaryItem> getItemListByDictionary(Long dictionaryId);

}
