package com.weaforce.cms.dao.impl.forum;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.forum.IChannelDao;
import com.weaforce.cms.entity.forum.ForumChannel;
import com.weaforce.core.dao.impl.GenericDao;
@Repository("forumChannelDao")
public class ChannelDao extends GenericDao<ForumChannel, Long> implements
		IChannelDao {
	private static final String QUERY_CHANNEL_BY_CITY = " From ForumChannel a Where channelCity.cityId = ? ";
	/**
	 * 根据城市,取得频道 list
	 * @param cityId
	 * @return
	 */
	public List<ForumChannel> getChannelListByCity(Long cityId){
		return listQuery(QUERY_CHANNEL_BY_CITY,cityId);
	}
}
