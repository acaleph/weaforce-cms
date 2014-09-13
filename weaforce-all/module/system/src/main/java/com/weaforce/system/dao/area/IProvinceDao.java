package com.weaforce.system.dao.area;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.entity.area.Province;

public interface IProvinceDao extends IGenericDao<Province, Long> {
	public List<Province> getProvinceList(Long countryId);
}
