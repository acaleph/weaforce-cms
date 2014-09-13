package com.weaforce.cms.dao.impl.social;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.social.ICarDao;
import com.weaforce.cms.entity.social.Car;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("carDao")
public class CarDao extends GenericDao<Car, Long> implements ICarDao {

	private static final String QUERY_CAR_BY_SERIES = " From Car a Where a.carSeries.seriesId=? ";
	private static final String QUERY_CAR_BY_OWNER = " From Car a Where a.carOwner=? ";

	/**
	 * Get car list by series primary key
	 * 
	 * @param seriesId
	 *            Car series primary key
	 * @return
	 */
	public List<Car> getCarListBySeries(Long seriesId) {
		return listQuery(QUERY_CAR_BY_SERIES, seriesId);
	}

	/**
	 * Get car list by owner
	 * 
	 * @param userLogin
	 *            User login
	 * @return
	 */
	public List<Car> getCarListByOwner(String userLogin) {
		return listQuery(QUERY_CAR_BY_OWNER, userLogin);
	}

	
}
