package com.weaforce.cms.dao.ads;

import java.util.List;

import com.weaforce.cms.entity.ads.BizChannel;
import com.weaforce.core.dao.IGenericDao;

public interface IChannelDao extends IGenericDao<BizChannel, Long> {
	/**
	 * 取得商务频道 list
	 * 
	 * @return
	 */
	public List<BizChannel> getChannelList();

	/**
	 * 根据城市,取得商务频道 list
	 * 
	 * @param cityId
	 *            城市主键
	 * @return
	 */
	public List<BizChannel> getChannelListByCity(Long cityId);
}
