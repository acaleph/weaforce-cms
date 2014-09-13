package com.weaforce.cms.dao.forum;

import java.util.List;

import com.weaforce.cms.entity.forum.Forum;
import com.weaforce.core.dao.IGenericDao;

public interface IForumDao extends IGenericDao<Forum, Long> {
	/**
	 * 取得论坛 list
	 * 
	 * @return
	 */
	public List<Forum> getForumList();

}
