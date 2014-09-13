package com.weaforce.cms.dao.impl.social;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.social.IFriendTagDao;
import com.weaforce.cms.entity.social.FriendTag;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("friendTagDao")
public class FriendTagDao extends GenericDao<FriendTag, Long> implements
		IFriendTagDao {
	private static final String GET_TAG_BY_OWNER_NAME = " From FriendTag a Where a.tagOwner=? And a.tagName=?";

	/**
	 * Check if the tag exists
	 * 
	 * @param tagOwner
	 *            Tag Owner login
	 * @param tagName
	 *            Tag name
	 * @return
	 */
	public boolean checkTagByOwnerName(String tagOwner, String tagName) {
		if (loadEntity(GET_TAG_BY_OWNER_NAME, tagOwner, tagName) == null)
			return false;
		return true;
	}
}
