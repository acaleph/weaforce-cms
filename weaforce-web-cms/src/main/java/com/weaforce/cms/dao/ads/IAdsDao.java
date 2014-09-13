package com.weaforce.cms.dao.ads;

import java.util.List;

import com.weaforce.cms.entity.ads.Ads;
import com.weaforce.core.dao.IGenericDao;

public interface IAdsDao extends IGenericDao<Ads, Long> {

	/**
	 * 根据广告栏目,取得广告 list
	 * 
	 * @param categoryId
	 *            　栏目主键
	 * @param isParse
	 *            是否已经 parse
	 * @return
	 */
	public List<Ads> getAdsListByCategoryParse(Long categoryId, String isParse);

	/**
	 * 根据区域主键,取得广告商家 list
	 * 
	 * @param zoneId
	 *            区域主键
	 * @return
	 */
	public List<Ads> getAdsListByZone(Long zoneId);

	/**
	 * 根据区域主键/广告栏目,取得广告商家 list
	 * 
	 * @param zoneId
	 *            城区
	 * @param categoryId
	 *            栏目
	 * @param isParse
	 *            是否已经 parse
	 * @param flag
	 *            是否有 all 选取项
	 * @return
	 */
	public List<Ads> getAdsListByZoneCategory(Long zoneId, Long categoryId,
			String isParse, boolean falg);

	/**
	 * 根据广告栏目,取得广告 list
	 * 
	 * @param categoryId
	 *            广告栏目
	 * @param start
	 *            起始
	 * @param rowNum
	 *            记录数目
	 * @return
	 */
	public List<Ads> getAdsListByCategory(Long categoryId, Integer start,
			Integer rowNum);

	/**
	 * 取得广告 list
	 * 
	 * @param start
	 *            起始
	 * @param rowNum
	 *            记录数目
	 * @return
	 */
	public List<Ads> getAdsList(Integer start, Integer rowNum);

	/**
	 * 根据广告栏目、标签、城区取得广告 list，默认已经parse的广告
	 * 
	 * @param categoryId
	 *            广告栏目
	 * @param tagId
	 *            标签
	 * @param zoneId
	 *            城区
	 * @param pageNumber
	 *            页码
	 * @return
	 */
	public List<Ads> getAdsPageByCategoryTagZone(Long categoryId, Long zoneId,
			Long tagId, String adsName, Integer pageNumber);

	/**
	 * 以JSON格式，取得栏目广告分页信息
	 * 
	 * @param categoryId
	 *            广告栏目
	 * @param tagId
	 *            标签
	 * @param zoneId
	 *            城区
	 * @param pageNumber
	 *            页码
	 * @return
	 */
	public String getAdsPageJSON(Long categoryId, Long zoneId, Long tagId,
			String adsName, Integer pageNumber);

	/**
	 * 根据条件取得广告 list
	 * 
	 * @param zoneId
	 *            城市区主键
	 * @param isParse
	 *            是否已经 parse
	 * @return
	 */
	public List<Ads> getAdsListByZoneParse(Long zoneId, String isParse);

	/**
	 * 根据条件取得广告 list
	 * 
	 * @param zoneId
	 *            城市区主键
	 * @param categoryId
	 *            栏目主键
	 * @param isParse是否已经
	 *            parse
	 * @return
	 */
	public List<Ads> getAdsListByZoneCategoryParse(Long zoneId,
			Long categoryId, String isParse);

	/**
	 * 根据条件取得广告 list
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @param tagId
	 *            广告标签主键
	 * @param isParse
	 *            　是否已经 parse
	 * @return
	 */

	public List<Ads> getAdsListByCategoryTagParse(Long categoryId, Long tagId,
			String isParse);

	/**
	 * 根据条件取得广告 list
	 * 
	 * @param zoneId
	 *            　城市区主键
	 * @param categoryId
	 *            　栏目主键
	 * @param tagId
	 *            　广告标签主键
	 * @param isParse
	 *            　是否已经 parse
	 * @return
	 */
	public List<Ads> getAdsListByZoneCategoryTagParse(Long zoneId,
			Long categoryId, Long tagId, String isParse);

}
