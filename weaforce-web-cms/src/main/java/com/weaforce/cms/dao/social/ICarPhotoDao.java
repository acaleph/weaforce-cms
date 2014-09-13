package com.weaforce.cms.dao.social;

import java.util.List;

import com.weaforce.cms.entity.social.CarPhoto;
import com.weaforce.core.dao.IGenericDao;

public interface ICarPhotoDao extends IGenericDao<CarPhoto, Long> {
	/**
	 * Get photo list by car
	 * 
	 * @param carId
	 *            Car primary key
	 * @return
	 */
	public List<CarPhoto> getPhotoListByCar(Long carId);
}
