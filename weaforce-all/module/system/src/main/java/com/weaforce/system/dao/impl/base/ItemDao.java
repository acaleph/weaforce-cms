package com.weaforce.system.dao.impl.base;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.base.IItemDao;
import com.weaforce.system.entity.base.DictionaryItem;

@Repository("itemDao")
public class ItemDao extends GenericDao<DictionaryItem, Long> implements IItemDao {
	private static final String QUERY_ITEM_BY_DICTIONARY = " From DictionaryItem a Where a.itemDictionaryId=? ";

	/**
	 * 取得项目 list
	 * 
	 * @param dictionaryId
	 *            字典主键
	 * @return
	 */
	public List<DictionaryItem> getItemListByDictionary(Long dictionaryId) {
		return listQuery(QUERY_ITEM_BY_DICTIONARY, dictionaryId);
	}
}
