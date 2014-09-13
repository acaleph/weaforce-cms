package com.weaforce.cms.dao.impl.social;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.social.ICarPhotoDao;
import com.weaforce.cms.entity.social.CarPhoto;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("carPhotoDao")
public class CarPhotoDao extends GenericDao<CarPhoto, Long> implements
		ICarPhotoDao {
	private static final String QUERY_PHOTO_BY_CAR = " From CarPhoto a Where a.photoCar.carId=?";

	/**
	 * Get photo list by car
	 * 
	 * @param carId
	 *            Car primary key
	 * @return
	 */
	public List<CarPhoto> getPhotoListByCar(Long carId) {
		return listQuery(QUERY_PHOTO_BY_CAR, carId);
	}

}
