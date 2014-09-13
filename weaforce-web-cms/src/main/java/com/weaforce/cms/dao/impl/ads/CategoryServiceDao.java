package com.weaforce.cms.dao.impl.ads;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ads.ICategoryServiceDao;
import com.weaforce.cms.entity.ads.BizCategoryService;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("adsBizCategoryServiceDao")
public class CategoryServiceDao extends GenericDao<BizCategoryService, Long>
		implements ICategoryServiceDao {
	private static final String QUERY_SERVICE_BY_CATEGORY = " From BizCategoryService a Where a.serviceCategory.categoryId=? ";

	/**
	 * 取得广告目录 list
	 * 
	 * @param catalogId
	 *            广告目录
	 * @return
	 */

	public List<BizCategoryService> getServiceListByCategory(Long categoryId) {
		return listQuery(QUERY_SERVICE_BY_CATEGORY, categoryId);
	}

}
