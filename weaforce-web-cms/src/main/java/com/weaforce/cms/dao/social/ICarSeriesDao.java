package com.weaforce.cms.dao.social;

import java.util.List;

import com.weaforce.cms.entity.social.CarSeries;
import com.weaforce.core.dao.IGenericDao;

public interface ICarSeriesDao extends IGenericDao<CarSeries, Long> {
	/**
	 * Get car series list refer to brand primary key
	 * 
	 * @param brandId
	 *            Car brand primary key
	 * @return
	 */
	public List<CarSeries> getSeriesByBrand(Long brandId);

	/**
	 * Get series JSON by brand
	 * 
	 * @param brandId
	 *            Brand primary key
	 * @return
	 */
	public StringBuffer getSeriesJSON(Long brandId);
}
