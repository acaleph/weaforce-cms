package com.weaforce.cms.dao.impl.ads;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ads.IAdsDao;
import com.weaforce.cms.entity.ads.Ads;
import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.core.util.StringUtil;

@Repository("adsDao")
public class AdsDao extends GenericDao<Ads, Long> implements IAdsDao {
	private static final String QUERY_ADS = " From Ads a ";
	private static final String QUERY_ADS_BY_CATEGORY = " From Ads a Where a.adsCategory.categoryId=? And a.adsIsParse=? ";
	private static final String QUERY_ADS_BY_CATEGORY_TAG_PARSE = " From Ads a Where a.adsCategory.categoryId=? And a.adsTag.tagId=? And a.adsIsParse=? ";
	private static final String QUERY_ADS_BY_ZONE = " From Ads a Where a.adsZone.zoneId=? ";
	private static final String QUERY_ADS_BY_ZONE_PARSE = " From Ads a Where a.adsZone.zoneId=? And a.adsIsParse=? ";
	private static final String QUERY_ADS_BY_ZONE_CATEGORY = " From Ads a Where a.adsZone.zoneId=? And a.adsCategory.categoryId=? ";
	private static final String QUERY_ADS_BY_ZONE_CATEGORY_PARSE = " From Ads a Where a.adsZone.zoneId=? And a.adsCategory.categoryId=? And a.adsIsParse=? ";
	private static final String QUERY_ADS_BY_ZONE_CATEGORY_TAG_PARSE = " From Ads a Where a.adsZone.zoneId=? And a.adsCategory.categoryId=? And a.adsTag.tagId=? And a.adsIsParse=? ";
	// 每页的记录数
	private static final Integer pageSize = 14;

	/**
	 * 根据广告栏目,取得广告 list
	 * 
	 * @param categoryId
	 *            　栏目主键
	 * @param isParse
	 *            是否已经 parse
	 * @return
	 */
	public List<Ads> getAdsListByCategoryParse(Long categoryId, String isParse) {
		return listQuery(QUERY_ADS_BY_CATEGORY + " Order by a.adsHotLevel ",
				categoryId);
	}

	/**
	 * 根据区域主键,取得广告商家 list
	 * 
	 * @param zoneId
	 *            区域主键
	 * @return
	 */
	public List<Ads> getAdsListByZone(Long zoneId) {
		return listQuery(QUERY_ADS_BY_ZONE, zoneId);
	}

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
			String isParse, boolean flag) {
		List<Ads> adsList = listQuery(QUERY_ADS_BY_ZONE_CATEGORY, zoneId,
				categoryId);
		if (flag) {
			Ads o = new Ads();
			o.setAdsName(" ---All--- ");
			adsList.add(0, o);
		}
		return adsList;
	}

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
			Integer rowNum) {
		return listQueryPage(QUERY_ADS_BY_CATEGORY + " Order by a.adsHotLevel ",
				start, rowNum, categoryId);
	}

	/**
	 * 取得广告 list
	 * 
	 * @param start
	 *            起始
	 * @param rowNum
	 *            记录数目
	 * @return
	 */
	public List<Ads> getAdsList(Integer start, Integer rowNum) {
		return listQueryPage(QUERY_ADS, start, rowNum);
	}

	/**
	 * 根据广告栏目、标签、城区取得广告 page，默认已经parse的广告
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
			Long tagId, String adsName, Integer pageNumber) {
		if (categoryId != null && categoryId > 0)
			return listQueryPage(
					getQueryHql(QUERY_ADS_BY_CATEGORY, zoneId, tagId, adsName)
							+ " Order by a.adsHotLevel desc ", pageSize
							* (pageNumber - 1), pageSize, categoryId);
		else if (StringUtil.isNotEmpty(adsName))
			return listQuery(QUERY_ADS + " Where a.adsName like '%" + adsName
					+ "%'");
		else
			return new ArrayList<Ads>();

	}

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
			String adsName, Integer pageNumber) {
		StringBuffer sb = new StringBuffer();
		// 页数
		Integer pageCount = 0;
		// 所有记录数
		Integer totalCount = 0;
		if (categoryId != null && categoryId > 0)
			totalCount = executeStat(
					" Select Count(*) "
							+ getQueryHql(QUERY_ADS_BY_CATEGORY, zoneId, tagId,
									adsName), categoryId).intValue();
		else if (StringUtil.isNotEmpty(adsName))
			totalCount = executeStat(
					" Select Count(*) " + QUERY_ADS
							+ " Where a.adsIsParse='1' And a.adsName like '%"
							+ adsName + "%'").intValue();
		if (totalCount % pageSize == 0)
			pageCount = totalCount / pageSize;
		else
			pageCount = totalCount / pageSize + 1;
		sb.append("[{\"pageNumber\":\"" + pageNumber + "\",\"pageCount\":\""
				+ pageCount + "\",\"totalCount\":\"" + totalCount + "\"}]");
		return sb.toString();
	}

	/**
	 * 取得组合查询语句:栏目/标签/城区:该方法没有出现在接口中
	 * 
	 * @param queryStr
	 *            原始HQL
	 * @param tagId
	 *            标签主键
	 * @param zoneId
	 *            城区主键
	 * @return
	 */
	public String getQueryHql(String queryStr, Long zoneId, Long tagId,
			String adsName) {
		if (zoneId != null && zoneId > 0)
			queryStr = queryStr + " And a.adsZone.zoneId=" + zoneId;
		if (tagId != null && tagId > 0)
			queryStr = queryStr + " And a.adsTag.tagId=" + tagId;
		if (StringUtil.isNotEmpty(adsName))
			queryStr = queryStr + " And a.adsName like '%" + adsName + "%'";
		queryStr = queryStr + " Order by a.adsHotLevel desc ";
		return queryStr;
	}

	/**
	 * 根据条件取得广告 list
	 * 
	 * @param zoneId
	 *            城市区主键
	 * @param isParse
	 *            是否已经 parse
	 * @return
	 */
	public List<Ads> getAdsListByZoneParse(Long zoneId, String isParse) {
		return listQuery(QUERY_ADS_BY_ZONE_PARSE, zoneId, isParse);
	}

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
			Long categoryId, String isParse) {
		return listQuery(QUERY_ADS_BY_ZONE_CATEGORY_PARSE, zoneId, categoryId,
				isParse);
	}

	/**
	 * 根据条件取得广告 list
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @param tagId
	 *            广告标签主键
	 * @return
	 */

	public List<Ads> getAdsListByCategoryTagParse(Long categoryId, Long tagId,
			String isParse) {
		return listQuery(QUERY_ADS_BY_CATEGORY_TAG_PARSE, categoryId, tagId,
				isParse);
	}

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
			Long categoryId, Long tagId, String isParse) {
		return listQuery(QUERY_ADS_BY_ZONE_CATEGORY_TAG_PARSE, zoneId,
				categoryId, tagId, isParse);
	}
}
