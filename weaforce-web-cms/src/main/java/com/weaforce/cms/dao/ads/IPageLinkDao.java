package com.weaforce.cms.dao.ads;

import java.util.List;

import com.weaforce.cms.entity.ads.AdsPageLink;
import com.weaforce.core.dao.IGenericDao;

public interface IPageLinkDao extends IGenericDao<AdsPageLink, Long> {
	/**
	 * 根据级别，取得链接 list
	 * 
	 * @param linkLevel
	 *            级别
	 * @return
	 */
	public List<AdsPageLink> getLinkListByLevel(Byte linkLevel);

	/**
	 * 根据父节点，取得链接 list
	 * 
	 * @param parentId
	 *            父节点
	 * @return
	 */
	public List<AdsPageLink> getLinkListByParent(Long parentId);
}
