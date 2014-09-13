package com.weaforce.cms.dao.impl.ads;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ads.IPageLinkDao;
import com.weaforce.cms.entity.ads.AdsPageLink;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("adsPageLinkDao")
public class PageLinkDao extends GenericDao<AdsPageLink, Long> implements
		IPageLinkDao {
	private static final String QUERY_LINK_BY_LEVEL = " From AdsPageLink a Where a.linkLevel=? ";
	private static final String QUERY_LINK_BY_PARENT = " From AdsPageLink a Where a.linkParent.linkId=? ";

	/**
	 * 根据级别，取得链接 list
	 * 
	 * @param linkLevel
	 *            级别
	 * @return
	 */
	public List<AdsPageLink> getLinkListByLevel(Byte linkLevel) {
		return listQuery(QUERY_LINK_BY_LEVEL, linkLevel);
	}

	/**
	 * 根据父节点，取得链接 list
	 * 
	 * @param parentId
	 *            父节点
	 * @return
	 */
	public List<AdsPageLink> getLinkListByParent(Long parentId) {
		return listQuery(QUERY_LINK_BY_PARENT, parentId);
	}
}
