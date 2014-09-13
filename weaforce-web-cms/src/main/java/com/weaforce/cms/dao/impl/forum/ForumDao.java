package com.weaforce.cms.dao.impl.forum;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.forum.IForumDao;
import com.weaforce.cms.entity.forum.Forum;
import com.weaforce.core.dao.impl.GenericDao;
@Repository("forumDao")
public class ForumDao extends GenericDao<Forum,Long> implements IForumDao {
	private static final String QUERY_FORUM = " From Forum a ";
	/**
	 * 取得论坛 list
	 * @return
	 */
	public List<Forum> getForumList(){
		return listQuery(QUERY_FORUM);
	}
}
