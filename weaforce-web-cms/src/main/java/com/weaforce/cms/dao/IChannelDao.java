package com.weaforce.cms.dao;

import java.util.List;

import com.weaforce.cms.entity.Channel;
import com.weaforce.core.dao.IGenericDao;

public interface IChannelDao extends IGenericDao<Channel, Long> {
	/**
	 * 取得活动的频道list
	 * 
	 * @param account
	 *            　帐套
	 * @param isActive
	 *            　活动
	 * @return
	 */
	public List<Channel> getChannelListByActive(String account, String isActive);

}
