package com.weaforce.cms.dao.impl.social;

import java.util.List;

import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.social.ICarBrandDao;
import com.weaforce.cms.entity.social.CarBrand;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("carBrandDao")
public class CarBrandDao extends GenericDao<CarBrand, Long> implements
		ICarBrandDao {
	private static final String QUERY_BRAND = " From CarBrand a ";

	/**
	 * Get car brand list
	 * 
	 * @return
	 * @throws InterruptedException 
	 */
	public List<CarBrand> getBrandList() throws InterruptedException {
		FullTextSession fullTextSession = Search
				.getFullTextSession(getSession());
		fullTextSession = Search.getFullTextSession(getSession());
		// Search session
		fullTextSession = Search.getFullTextSession(getSession());
		fullTextSession.createIndexer().startAndWait();
		return listQuery(QUERY_BRAND);
	}
}
