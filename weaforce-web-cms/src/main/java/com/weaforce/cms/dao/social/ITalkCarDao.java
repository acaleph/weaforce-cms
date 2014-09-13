package com.weaforce.cms.dao.social;

import java.util.List;

import com.weaforce.cms.entity.social.TalkCar;
import com.weaforce.core.dao.IGenericDao;

public interface ITalkCarDao extends IGenericDao<TalkCar, Long> {
	/**
	 * Get talk list by parent
	 * 
	 * @param parentId
	 *            Parent talk primary key
	 * @return
	 */
	public List<TalkCar> getTalkListByParent(Long parentId);
}
