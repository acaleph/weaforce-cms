package com.weaforce.cms.dao.ads;

import java.util.List;

import com.weaforce.cms.entity.ads.BizCategoryService;
import com.weaforce.core.dao.IGenericDao;

public interface ICategoryServiceDao extends
		IGenericDao<BizCategoryService, Long> {
	/**
	 * 取得广告栏目服务 list
	 * 
	 * @param categoryId
	 *            广告栏目
	 * @return
	 */
	public List<BizCategoryService> getServiceListByCategory(Long categoryId);
}
