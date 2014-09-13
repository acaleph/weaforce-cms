package com.weaforce.cms.dao.impl.social;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.social.ITalkCarDao;
import com.weaforce.cms.entity.social.TalkCar;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("talkCarDao")
public class TalkCarDao extends GenericDao<TalkCar, Long> implements ITalkCarDao {
	private static final String QUERY_TALK_BY_PARENT = " From Talk a Where a.parentTalk.talkId=?";

	/**
	 * Get talk list by parent
	 * 
	 * @param parentId
	 *            Parent talk primary key
	 * @return
	 */
	public List<TalkCar> getTalkListByParent(Long parentId) {
		return listQuery(QUERY_TALK_BY_PARENT, parentId);
	}
}
