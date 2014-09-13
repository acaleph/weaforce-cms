package com.weaforce.cms.dao.ads;

import java.util.List;

import com.weaforce.cms.entity.ads.Tag;
import com.weaforce.core.dao.IGenericDao;

public interface ITagDao extends IGenericDao<Tag, Long> {
	/**
	 * 根据广告目录,取得标签 list
	 * 
	 * @param categoryId
	 *            广告栏目
	 * @return
	 */
	public List<Tag> getTagListByCategory(Long categoryId);
}
