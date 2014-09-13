package com.weaforce.cms.dao.impl.ads;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ads.ICategoryDao;
import com.weaforce.cms.entity.ads.BizCategory;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("adsBizCategoryDao")
public class CategoryDao extends GenericDao<BizCategory, Long> implements
		ICategoryDao {
	private static final String QUERY_CATEGORY_BY_CHANNEL = " From BizCategory a Where a.categoryChannel.channelId =? Order by a.categoryOrder ";

	/**
	 * 根据频道,取得栏目 list
	 * 
	 * @param channelId
	 *            频道
	 * @param flag
	 *            是否包括 " --- all --- "选项
	 * @return
	 */
	public List<BizCategory> getCategoryListByChannel(Long channelId,
			boolean flag) {
		List<BizCategory> categoryList = listQuery(QUERY_CATEGORY_BY_CHANNEL,
				channelId);
		if (flag) {
			BizCategory o = new BizCategory();
			o.setCategoryName(" --- all --- ");
			categoryList.add(0, o);
		}
		return categoryList;
	}

}
