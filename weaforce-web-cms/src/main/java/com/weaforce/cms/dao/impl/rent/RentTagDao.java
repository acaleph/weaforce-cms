package com.weaforce.cms.dao.impl.rent;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.rent.IRentTagDao;
import com.weaforce.cms.entity.rent.RentTag;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("rentTagDao")
public class RentTagDao extends GenericDao<RentTag, Long> implements
		IRentTagDao {
	private static final String QUERY_TAG = " From RentTag a ";

	/**
	 * 取得房间价格标签 list
	 * 
	 * @return
	 */
	public List<RentTag> getTagList() {
		return listQuery(QUERY_TAG);
	}
}
