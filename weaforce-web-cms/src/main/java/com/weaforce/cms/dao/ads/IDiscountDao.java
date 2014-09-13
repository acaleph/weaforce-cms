package com.weaforce.cms.dao.ads;

import java.util.List;

import com.weaforce.cms.entity.ads.AdsDiscount;
import com.weaforce.core.dao.IGenericDao;

public interface IDiscountDao extends IGenericDao<AdsDiscount, Long> {
	/**
	 * 根据栏目取得打折 list
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @return
	 */
	public List<AdsDiscount> getDiscountListByCategory(Long categoryId);

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
			Integer start, Integer rowNum);

	/**
	 * 取得打折 list
	 * 
	 * @param start
	 *            起始
	 * @param rowNum
	 *            记录数目
	 * @return
	 */
	public List<AdsDiscount> getDiscountList(Integer start, Integer rowNum);

	/**
	 * 取得JSON格式打折记录
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
			Integer pageNumber);

	/**
	 * 根据栏目，取得打折数量
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @return
	 */
	public Integer getDiscountCountByCategory(Long categoryId);
}
