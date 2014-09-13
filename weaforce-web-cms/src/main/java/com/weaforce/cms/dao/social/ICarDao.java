package com.weaforce.cms.dao.social;

import java.util.List;

import com.weaforce.cms.entity.social.Car;
import com.weaforce.core.dao.IGenericDao;

public interface ICarDao extends IGenericDao<Car, Long> {
	/**
	 * Get car list by series primary key
	 * 
	 * @param seriesId
	 *            Car series primary key
	 * @return
	 */
	public List<Car> getCarListBySeries(Long seriesId);

	/**
	 * Get car list by owner
	 * 
	 * @param userLogin
	 *            User login
	 * @return
	 */
	public List<Car> getCarListByOwner(String userLogin);

}
