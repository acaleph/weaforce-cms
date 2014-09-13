package com.weaforce.cms.dao.impl.ads;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ads.IDealDao;
import com.weaforce.cms.entity.ads.Deal;
import com.weaforce.core.dao.impl.GenericDao;
@Repository("adsDealDao")
public class DealDao extends GenericDao<Deal, Long> implements IDealDao {

}
