package com.weaforce.system.dao.area;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.entity.area.City;

public interface ICityDao extends IGenericDao<City, Long> {
	public List<City> getCityList();
	public List<City> getCityList( Long provinceId);
}
