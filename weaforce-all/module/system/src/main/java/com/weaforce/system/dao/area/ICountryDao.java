package com.weaforce.system.dao.area;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.entity.area.Country;

public interface ICountryDao extends IGenericDao<Country, Long> {
	public List<Country> getCountryList();
}
