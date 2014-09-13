package com.weaforce.cms.dao.impl.social;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.social.IFriendDao;
import com.weaforce.cms.entity.social.Friend;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("friendDao")
public class FriendDao extends GenericDao<Friend, Long> implements IFriendDao {
	private static final String GET_FRIEND_BY_LOGIN = " From Friend a Where a.friendLoginMe=? And friendLogin=? ";

	/**
	 * Get friend by login
	 * 
	 * @param loginMe
	 *            My login
	 * @param loginFriend
	 *            Friend login
	 * @return
	 */
	public Friend getFriendByLogin(String loginMe, String loginFriend) {
		return loadEntity(GET_FRIEND_BY_LOGIN, loginMe, loginFriend);
	}
}
