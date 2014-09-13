package com.weaforce.cms.dao;

import java.util.List;

import com.weaforce.cms.entity.CopyFrom;
import com.weaforce.core.dao.IGenericDao;

public interface ICopyFromDao extends IGenericDao<CopyFrom, Long> {
	/**
	 * 取得来源list
	 * 
	 * @param flag
	 *            是否包括 --- all --- 选项
	 * @return
	 */
	public List<CopyFrom> getCopyFromList( boolean flag);

}
