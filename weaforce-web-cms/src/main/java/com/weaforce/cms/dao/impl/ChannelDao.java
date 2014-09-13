package com.weaforce.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.IChannelDao;
import com.weaforce.cms.entity.Channel;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("channelDao")
public class ChannelDao extends GenericDao<Channel, Long> implements
		IChannelDao {
	private static final String QUERY_CHANNEL_BY_ACTIVE = "From Channel a Where a.account=? And a.channelIsActive=? ";

	/**
	 * 取得活动的频道list
	 * 
	 * @param account
	 *            　帐套
	 * @param isActive
	 *            　活动
	 * @return
	 */
	public List<Channel> getChannelListByActive(String account, String isActive) {
		return listQuery(QUERY_CHANNEL_BY_ACTIVE, account, isActive);
	}
}
