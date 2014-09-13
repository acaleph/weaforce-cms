package com.weaforce.cms.dao.impl.ads;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ads.IMessageDao;
import com.weaforce.cms.entity.ads.Message;
import com.weaforce.core.dao.impl.GenericDao;
@Repository("adsMessageDao")
public class MessageDao extends GenericDao<Message, Long> implements
		IMessageDao {

}
