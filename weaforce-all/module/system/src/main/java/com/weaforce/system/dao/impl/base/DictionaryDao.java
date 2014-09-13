package com.weaforce.system.dao.impl.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.base.IDictionaryDao;
import com.weaforce.system.entity.base.Dictionary;

@Repository("dictionaryDao")
public class DictionaryDao extends GenericDao<Dictionary, Long> implements
		IDictionaryDao {
	private static final String QUERY_DICTIONARY = " From Dictionary a Where a.account=? ";

	/**
	 * 取得字典 list
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	public List<Dictionary> getDictionaryList(String account) {
		return listQuery(QUERY_DICTIONARY, account);
	}
}
