package com.weaforce.cms.dao.ads;

import java.util.List;

import com.weaforce.cms.entity.ads.AdsService;
import com.weaforce.core.dao.IGenericDao;

public interface IAdsServiceDao extends IGenericDao<AdsService, Long> {
	/**
	 * 取得联合广告栏目/服务
	 * 
	 * @param adsId
	 *            广告
	 * @param categoryId
	 *            广告目录类别
	 * @return
	 */
	public AdsService getAdsService(Long adsId, Long serviceId);

	/**
	 * 根据广告，取得广告栏目/服务 list
	 * 
	 * @param adsId
	 *            广告
	 * @return
	 */
	public List<AdsService> getAdsServiceListByAds(Long adsId);

}
