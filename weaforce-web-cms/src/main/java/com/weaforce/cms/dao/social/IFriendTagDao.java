package com.weaforce.cms.dao.social;

import com.weaforce.cms.entity.social.FriendTag;
import com.weaforce.core.dao.IGenericDao;

public interface IFriendTagDao extends IGenericDao<FriendTag, Long> {
	/**
	 * Check if the tag exists
	 * 
	 * @param tagOwner
	 *            Tag Owner login
	 * @param tagName
	 *            Tag name
	 * @return
	 */
	public boolean checkTagByOwnerName(String tagOwner, String tagName);
}
