package com.weaforce.cms.dao.impl.ads;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ads.ITagDao;
import com.weaforce.cms.entity.ads.Tag;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("adsTagDao")
public class TagDao extends GenericDao<Tag, Long> implements ITagDao {
	private static final String QUERY_TAG_BY_CATEGORY = "From Tag a Where a.tagCategory.categoryId=? Order by a.tagOrder ";

	/**
	 * 根据广告栏目,取得标签 list
	 * 
	 * @param categoryId
	 *            广告栏目
	 * @return
	 */
	public List<Tag> getTagListByCategory(Long categoryId) {
		return listQuery(QUERY_TAG_BY_CATEGORY, categoryId);
	}
}
