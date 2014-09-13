package com.weaforce.cms.dao.social;

import com.weaforce.cms.entity.social.Friend;
import com.weaforce.core.dao.IGenericDao;

public interface IFriendDao extends IGenericDao<Friend, Long> {
	/**
	 * Get friend by login
	 * 
	 * @param loginMe
	 *            My login
	 * @param loginFriend
	 *            Friend login
	 * @return
	 */
	public Friend getFriendByLogin(String loginMe, String loginFriend);
}
