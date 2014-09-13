package com.weaforce.cms.dao.ads;

import java.util.List;

import com.weaforce.cms.entity.ads.BizCategory;
import com.weaforce.core.dao.IGenericDao;

public interface ICategoryDao extends IGenericDao<BizCategory, Long> {
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
			boolean flag);

}
