package com.weaforce.cms.dao.social;

import java.util.List;

import com.weaforce.cms.entity.social.CarBrand;
import com.weaforce.core.dao.IGenericDao;

public interface ICarBrandDao extends IGenericDao<CarBrand, Long> {
	/**
	 * Get car brand list
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public List<CarBrand> getBrandList() throws InterruptedException;
}
