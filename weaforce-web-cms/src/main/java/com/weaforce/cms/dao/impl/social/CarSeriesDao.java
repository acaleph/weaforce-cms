package com.weaforce.cms.dao.impl.social;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.social.ICarSeriesDao;
import com.weaforce.cms.entity.social.CarSeries;
import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.core.util.StringUtil;

@Repository("carSeriesDao")
public class CarSeriesDao extends GenericDao<CarSeries, Long> implements
		ICarSeriesDao {

	private static final String QUERY_SERIES_BY_BRAND = " From CarSeries a Where a.seriesBrand.brandId = ? ";

	/**
	 * Get car series list refer to brand primary key
	 * 
	 * @param brandId
	 *            Car brand primary key
	 * @return
	 */
	public List<CarSeries> getSeriesByBrand(Long brandId) {
		return listQuery(QUERY_SERIES_BY_BRAND, brandId);
	}

	/**
	 * Get series JSON by brand
	 * 
	 * @param brandId
	 *            Brand primary key
	 * @return
	 */
	public StringBuffer getSeriesJSON(Long brandId) {
		List<CarSeries> seriesList = listQuery(QUERY_SERIES_BY_BRAND, brandId);
		StringBuffer sb = new StringBuffer("[");
		for (CarSeries o : seriesList) {
			sb.append("{\"value\":\"" + o.getSeriesId() + "\",\"caption\":\""
					+ o.getSeriesName() + "\"},");
		}
		sb = new StringBuffer(StringUtil.cutLastChar(sb.toString(), ","));
		sb.append("]");
		return sb;
	}
}
