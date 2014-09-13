package com.weaforce.cms.dao.rent;

import java.util.List;

import com.weaforce.cms.entity.rent.RentTag;
import com.weaforce.core.dao.IGenericDao;

public interface IRentTagDao extends IGenericDao<RentTag, Long> {
	/**
	 * 取得房间价格标签 list
	 * 
	 * @return
	 */
	public List<RentTag> getTagList();
}
