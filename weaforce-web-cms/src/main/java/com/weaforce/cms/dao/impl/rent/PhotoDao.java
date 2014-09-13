package com.weaforce.cms.dao.impl.rent;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.rent.IPhotoDao;
import com.weaforce.cms.entity.rent.HousePhoto;
import com.weaforce.core.dao.impl.GenericDao;
@Repository("housePhotoDao")
public class PhotoDao extends GenericDao<HousePhoto, Long> implements
		IPhotoDao {

}
