package com.weaforce.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ICopyFromDao;
import com.weaforce.cms.entity.CopyFrom;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("copyFromDao")
public class CopyFromDao extends GenericDao<CopyFrom, Long> implements
		ICopyFromDao {
	private static final String QUERY_FROM = " From CopyFrom a  ";

	/**
	 * 取得来源list
	 * 
	 * @param flag
	 *            是否包括 --- all --- 选项
	 * @return
	 */
	public List<CopyFrom> getCopyFromList( boolean flag) {
		List<CopyFrom> fromList = listQuery(QUERY_FROM);
		if (flag) {
			CopyFrom o = new CopyFrom();
			o.setFromName(" --- all --- ");
			fromList.add(0, o);
		}
		return fromList;
	}

}
