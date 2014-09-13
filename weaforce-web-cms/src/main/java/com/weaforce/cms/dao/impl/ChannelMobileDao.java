package com.weaforce.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.IChannelMobileDao;
import com.weaforce.cms.entity.ChannelMobile;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("channelMobileDao")
public class ChannelMobileDao extends GenericDao<ChannelMobile, Long> implements
		IChannelMobileDao {

}
