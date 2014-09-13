package com.weaforce.cms.dao.impl.social;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.social.ICarShareRequestDao;
import com.weaforce.cms.entity.social.CarShareRequest;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("carShareRequestDao")
public class CarShareRequestDao extends GenericDao<CarShareRequest, Long>
		implements ICarShareRequestDao {

}
