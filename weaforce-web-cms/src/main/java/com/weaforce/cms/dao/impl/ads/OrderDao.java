package com.weaforce.cms.dao.impl.ads;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ads.IOrderDao;
import com.weaforce.cms.entity.ads.Order;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("adsOrderDao")
public class OrderDao extends GenericDao<Order, Long> implements IOrderDao {

}
