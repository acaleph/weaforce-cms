package com.weaforce.system.dao.impl.area;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.area.IGardenDao;
import com.weaforce.system.entity.area.Garden;

@Repository("gardenDao")
public class GardenDao extends GenericDao<Garden, Long> implements IGardenDao {

}
