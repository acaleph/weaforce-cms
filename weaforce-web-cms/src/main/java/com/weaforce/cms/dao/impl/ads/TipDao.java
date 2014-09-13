package com.weaforce.cms.dao.impl.ads;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ads.ITipDao;
import com.weaforce.cms.entity.ads.CategoryTip;
import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.core.util.StringUtil;

@Repository("adsTipDao")
public class TipDao extends GenericDao<CategoryTip, Long> implements ITipDao {
	private static final String QUERY_TIP = " From CategoryTip a ";
	private static final String QUERY_TIP_BY_CATEGORY = " From CategoryTip a Where a.tipCategory.categoryId=? ";
	/**
	 * 根据栏目取得贴士 list
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @return
	 */
	public List<CategoryTip> getTipListByCategory(Long categoryId){
		return listQuery(QUERY_TIP_BY_CATEGORY,categoryId);
	}
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
			Integer start, Integer rowNum) {
		return listQueryPage(QUERY_TIP_BY_CATEGORY, start, rowNum, categoryId);
	}

	/**
	 * 取得小贴士 list
	 * 
	 * @param start
	 *            起始
	 * @param rowNum
	 *            记录数目
	 * @return
	 */
	public List<CategoryTip> getTipList(Integer start, Integer rowNum) {
		return listQueryPage(QUERY_TIP, start, rowNum);
	}

	/**
	 * 取得JSON格式小贴士记录
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @param tipTitle 标题
	 * @param pageNumber
	 *            当前页数
	 * @return
	 */
	public String getTipJSON(Long categoryId,String tipTitle, Integer pageNumber) {
		StringBuffer sb = new StringBuffer();
		if (StringUtil.isNotEmpty(tipTitle))
			tipTitle = " And a.tipTitle like '%" + tipTitle + "%' ";
		else
			tipTitle="";
		/*------------------ 初始化页参数 ------------------*/
		//每页的记录数
		Integer pageSize = 14;
		//页数
		Integer pageCount = 0;
		//所有记录数
		Integer totalCount = executeStat(
				" Select Count(*) " + QUERY_TIP_BY_CATEGORY + tipTitle, categoryId)
				.intValue();
		// 页数
		if (totalCount % pageSize == 0)
			pageCount = totalCount / pageSize;
		else
			pageCount = totalCount / pageSize + 1;
		/*------------------ 初始化页参数 ------------------*/
		List<CategoryTip> tipList = listQueryPage(QUERY_TIP_BY_CATEGORY + tipTitle, pageSize
				* (pageNumber - 1), pageSize, categoryId);
		for (CategoryTip tip : tipList) {
			if (sb.length() == 0)
				sb.append("[");
			else
				sb.append(",");
			sb.append("{\"tipId\":\"" + tip.getTipId() + "\",\"tipTitle\":\""
					+ tip.getTipTitle() + "\",\"tipUrl\":\"" + tip.getTipUrl()
					+ "\",\"tipFrom\":\"" + tip.getTipFrom().getFromName()
					+ "\",\"createTime\":\"" + tip.getDate() + "\"}");
		}
		if (sb.length() > 0)
			sb.append("]");
		StringBuffer sbPage = new StringBuffer("[");
		sbPage.append("{\"pageNumber\":\"" + pageNumber + "\",\"pageCount\":\""
				+ pageCount + "\",\"totalCount\":\"" + totalCount
				+ "\",\"page\":" + sb.toString() + "}]");
		return sbPage.toString();
	}

	/**
	 * 根据栏目，取得小贴士数量
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @return
	 */
	public Integer getTipCountByCategory(Long categoryId) {
		return executeStat(" Select Count(*) " + QUERY_TIP_BY_CATEGORY,
				categoryId).intValue();
	}
}
