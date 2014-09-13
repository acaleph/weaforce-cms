package com.weaforce.cms.dao.impl.ads;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ads.IAdsServiceDao;
import com.weaforce.cms.entity.ads.AdsService;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("adsAdsServiceDao")
public class AdsServiceDao extends GenericDao<AdsService, Long> implements
		IAdsServiceDao {
	private static final String QUERY_ADSSERVICE_BY_ADS_SERVICE = " From AdsService a Where a.serviceAds.adsId=? And a.serviceService.serviceId=? ";
	private static final String QUERY_ADSSERVICE_BY_ADS = " From AdsService a Where a.serviceAds.adsId=? Order by a.serviceService.serviceOrder";

	/**
	 * 取得联合广告栏目/服务
	 * 
	 * @param adsId
	 *            广告
	 * @param categoryId
	 *            广告目录类别
	 * @return
	 */
	public AdsService getAdsService(Long adsId, Long serviceId) {
		return loadEntity(QUERY_ADSSERVICE_BY_ADS_SERVICE, adsId, serviceId);
	}

	/**
	 * 根据广告，取得广告栏目/服务 list
	 * 
	 * @param adsId
	 *            广告
	 * @return
	 */
	public List<AdsService> getAdsServiceListByAds(Long adsId) {
		return listQuery(QUERY_ADSSERVICE_BY_ADS, adsId);
	}
}
