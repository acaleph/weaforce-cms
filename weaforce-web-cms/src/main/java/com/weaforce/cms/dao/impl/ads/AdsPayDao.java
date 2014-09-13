package com.weaforce.cms.dao.impl.ads;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ads.IAdsPayDao;
import com.weaforce.cms.entity.ads.AdsPay;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("adsPayDao")
public class AdsPayDao extends GenericDao<AdsPay, Long> implements IAdsPayDao {

}
