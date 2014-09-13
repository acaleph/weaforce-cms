package com.weaforce.system.dao.impl.area;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.entity.area.Province;
import com.weaforce.system.dao.area.IProvinceDao;

@Repository("provinceDao")
public class ProvinceDao extends GenericDao<Province, Long> implements
		IProvinceDao {
	public static final String QUERY_PROVINCE_BY_COUNTRY = " From Province a Where a.provinceCountry.countryId = ? ";
	
	public List<Province> getProvinceList(Long countryId){
		return listQuery(QUERY_PROVINCE_BY_COUNTRY,countryId);
	}
}
