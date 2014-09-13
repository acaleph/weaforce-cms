package com.weaforce.system.dao.impl.area;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.entity.area.City;
import com.weaforce.system.dao.area.ICityDao;

@Repository("cityDao")
public class CityDao extends GenericDao<City, Long> implements ICityDao {
	private static final String QUERY_CITY = " From City a ";
	private static final String QUERY_CITY_BY_PROVINCE = " From City a Where a.cityProvince.provinceId = ? ";

	public List<City> getCityList() {
		return listQuery(QUERY_CITY);
	}

	public List<City> getCityList(Long provinceId) {
		return listQuery(QUERY_CITY_BY_PROVINCE, provinceId);
	}
}
