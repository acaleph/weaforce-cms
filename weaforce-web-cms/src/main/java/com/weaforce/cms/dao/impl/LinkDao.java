package com.weaforce.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ILinkDao;
import com.weaforce.cms.entity.Link;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("linkDao")
public class LinkDao extends GenericDao<Link, Long> implements ILinkDao {
	private static final String QUERY_LINK = " From Link a ";

	/**
	 * 取得友情链接 list
	 * 
	 * @param start
	 *            起始记录
	 * @param rowNum
	 *            记录数
	 * @return
	 */
	public List<Link> getLinkList(Integer start, Integer rowNum) {
		return listQueryPage(QUERY_LINK, start, rowNum);
	}
}
