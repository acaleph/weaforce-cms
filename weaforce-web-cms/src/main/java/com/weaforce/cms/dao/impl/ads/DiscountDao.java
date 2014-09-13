package com.weaforce.cms.dao.impl.ads;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ads.IDiscountDao;
import com.weaforce.cms.entity.ads.AdsDiscount;
import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.core.util.StringUtil;

@Repository("adsDiscountDao")
public class DiscountDao extends GenericDao<AdsDiscount, Long> implements
		IDiscountDao {
	private static final String QUERY_DISCOUNT = " From AdsDiscount a ";
	private static final String QUERY_DISCOUNT_BY_CATEGORY = " From AdsDiscount a Where a.discountAds.adsCategory.categoryId=? ";

	/**
	 * 根据栏目取得打折 list
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @return
	 */
	public List<AdsDiscount> getDiscountListByCategory(Long categoryId) {
		return listQuery(QUERY_DISCOUNT_BY_CATEGORY, categoryId);
	}

	/**
	 * 根据广告栏目，取得打折 list
	 * 
	 * @param categoryId
	 *            广告栏目
	 * @param start
	 *            起始
	 * @param rowNum
	 *            记录数目
	 * @return
	 */
	public List<AdsDiscount> getDiscountListByCategory(Long categoryId,
			Integer start, Integer rowNum) {
		return listQueryPage(QUERY_DISCOUNT_BY_CATEGORY, start, rowNum, categoryId);
	}

	/**
	 * 取得打折 list
	 * 
	 * @param start
	 *            起始
	 * @param rowNum
	 *            记录数目
	 * @return
	 */
	public List<AdsDiscount> getDiscountList(Integer start, Integer rowNum) {
		return listQueryPage(QUERY_DISCOUNT + " Where a.discountIsParse='1'", start, rowNum);
	}

	/**
	 * 取得JSON格式打折分页记录
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @param discountTitle
	 *            标题
	 * @param pageNumber
	 *            当前页数
	 * @return
	 */
	public String getDiscountJSON(Long categoryId, String discountTitle,
			Integer pageNumber) {
		StringBuffer sb = new StringBuffer();
		if (StringUtil.isNotEmpty(discountTitle))
			discountTitle = " And a.discountTitle like '%" + discountTitle
					+ "%' ";
		else
			discountTitle = "";
		/* 初始化页参数 */
		// 每页的记录数
		Integer pageSize = 14;
		// 页数
		Integer pageCount = 0;
		// 所有记录数
		Integer totalCount = executeStat(
				" Select Count(*) " + QUERY_DISCOUNT_BY_CATEGORY
						+ discountTitle, categoryId).intValue();
		if (totalCount % pageSize == 0)
			pageCount = totalCount / pageSize;
		else
			pageCount = totalCount / pageSize + 1;
		/* 初始化页参数 */
		List<AdsDiscount> discountList = listQueryPage(QUERY_DISCOUNT_BY_CATEGORY
				+ discountTitle, pageSize * (pageNumber - 1), pageSize,
				categoryId);
		for (AdsDiscount discount : discountList) {
			if (sb.length() == 0)
				sb.append("[");
			else
				sb.append(",");
			sb.append("{\"discountId\":\"" + discount.getDiscountId()
					+ "\",\"discountTitle\":\"" + discount.getDiscountTitle()
					+ "\",\"discountAdsName\":\""
					+ discount.getDiscountAds().getAdsName()
					+ "\",\"discountAdsWeb\":\""
					+ discount.getDiscountAds().getAdsWeb()
					+ "\",\"discountUrl\":\"" + discount.getDiscountUrl()
					+ "\",\"discountDateFrom\":\""
					+ discount.getDiscountDateFromDate()
					+ "\",\"discountDateTo\":\""
					+ discount.getDiscountDateToDate() + "\",\"createTime\":\""
					+ discount.getDate() + "\"}");
		}
		if (sb.length() > 0)
			sb.append("]");
		else
			sb.append("[{}]");
		StringBuffer sbPage = new StringBuffer("[");
		sbPage.append("{\"pageNumber\":\"" + pageNumber + "\",\"pageCount\":\""
				+ pageCount + "\",\"totalCount\":\"" + totalCount
				+ "\",\"page\":" + sb.toString() + "}]");
		return sbPage.toString();
	}

	/**
	 * 根据栏目，取得打折数量
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @return
	 */
	public Integer getDiscountCountByCategory(Long categoryId) {
		return executeStat(" Select Count(*) " + QUERY_DISCOUNT_BY_CATEGORY,
				categoryId).intValue();
	}
}
