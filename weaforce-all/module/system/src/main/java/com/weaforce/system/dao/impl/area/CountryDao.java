package com.weaforce.system.dao.impl.area;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.entity.area.Country;
import com.weaforce.system.dao.area.ICountryDao;
@Repository("countryDao")
public class CountryDao extends GenericDao<Country,Long> implements ICountryDao {
	private static final String QUERY_COUNTRY = " From Country a ";
	public List<Country> getCountryList(){
		return listQuery(QUERY_COUNTRY);
	}
	
}
