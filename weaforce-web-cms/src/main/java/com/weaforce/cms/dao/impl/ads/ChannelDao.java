package com.weaforce.cms.dao.impl.ads;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ads.IChannelDao;
import com.weaforce.cms.entity.ads.BizChannel;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("adsBizChannelDao")
public class ChannelDao extends GenericDao<BizChannel, Long> implements
		IChannelDao {
	private static final String QUERY_CHANNEL = " From BizChannel a Order by a.channelOrder";
	private static final String QUERY_CHANNEL_BY_CITY = " From BizChannel a Where a.channelCity.cityId=? Order by a.channelOrder";

	/**
	 * 取得商务频道 list
	 * 
	 * @return
	 */
	public List<BizChannel> getChannelList() {
		return listQuery(QUERY_CHANNEL);
	}

	/**
	 * 根据城市,取得商务频道 list
	 * 
	 * @param cityId
	 *            城市主键
	 * @return
	 */
	public List<BizChannel> getChannelListByCity(Long cityId) {
		return listQuery(QUERY_CHANNEL_BY_CITY, cityId);
	}
}
