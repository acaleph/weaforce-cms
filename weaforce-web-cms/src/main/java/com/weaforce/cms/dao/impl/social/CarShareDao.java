package com.weaforce.cms.dao.impl.social;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.social.ICarShareDao;
import com.weaforce.cms.entity.social.CarShare;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("carShareDao")
public class CarShareDao extends GenericDao<CarShare, Long> implements
		ICarShareDao {

}
