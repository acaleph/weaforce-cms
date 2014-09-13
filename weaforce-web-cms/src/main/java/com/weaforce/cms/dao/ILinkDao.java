package com.weaforce.cms.dao;

import java.util.List;

import com.weaforce.cms.entity.Link;
import com.weaforce.core.dao.IGenericDao;

public interface ILinkDao extends IGenericDao<Link, Long> {
	/**
	 * 取得友情链接 list
	 * 
	 * @param start
	 *            起始记录
	 * @param rowNum
	 *            记录数
	 * @return
	 */
	public List<Link> getLinkList(Integer start, Integer rowNum);
}
