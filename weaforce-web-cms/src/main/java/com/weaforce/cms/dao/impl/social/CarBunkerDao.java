package com.weaforce.cms.dao.impl.social;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.social.ICarBunkerDao;
import com.weaforce.cms.entity.social.CarBunker;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("carBunkerDao")
public class CarBunkerDao extends GenericDao<CarBunker, Long> implements ICarBunkerDao {

}
