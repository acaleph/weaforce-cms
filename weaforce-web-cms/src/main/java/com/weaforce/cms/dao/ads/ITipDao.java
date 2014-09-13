package com.weaforce.cms.dao.ads;

import java.util.List;

import com.weaforce.cms.entity.ads.CategoryTip;
import com.weaforce.core.dao.IGenericDao;

public interface ITipDao extends IGenericDao<CategoryTip, Long> {
	/**
	 * 根据栏目取得贴士 list
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @return
	 */
	public List<CategoryTip> getTipListByCategory(Long categoryId);

	/**
	 * 根据广告栏目，取得小贴士 list
	 * 
	 * @param categoryId
	 *            广告栏目
	 * @param start
	 *            起始
	 * @param rowNum
	 *            记录数目
	 * @return
	 */
	public List<CategoryTip> getTipListByCategory(Long categoryId,
			Integer start, Integer rowNum);

	/**
	 * 取得小贴士 list
	 * 
	 * @param start
	 *            起始
	 * @param rowNum
	 *            记录数目
	 * @return
	 */
	public List<CategoryTip> getTipList(Integer start, Integer rowNum);

	/**
	 * 取得JSON格式小贴士记录
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @param tipTitle
	 *            标题
	 * @param pageNumber
	 *            当前页数
	 * @return
	 */
	public String getTipJSON(Long categoryId, String tipTitle,
			Integer pageNumber);

	/**
	 * 根据栏目，取得小贴士数量
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @return
	 */
	public Integer getTipCountByCategory(Long categoryId);
}
