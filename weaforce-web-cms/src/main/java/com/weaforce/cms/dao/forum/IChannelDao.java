package com.weaforce.cms.dao.forum;

import java.util.List;

import com.weaforce.cms.entity.forum.ForumChannel;
import com.weaforce.core.dao.IGenericDao;

public interface IChannelDao extends IGenericDao<ForumChannel, Long> {
	/**
	 * 根据城市,取得频道 list
	 * @param cityId
	 * @return
	 */
	public List<ForumChannel> getChannelListByCity(Long cityId);

}
