package com.weaforce.cms.dao.rent;

import java.util.List;

import com.weaforce.cms.entity.rent.HouseType;
import com.weaforce.core.dao.IGenericDao;

public interface IHouseTypeDao extends IGenericDao<HouseType, Long> {
	/**
	 * 取得房间类型 list
	 * 
	 * @return
	 */
	public List<HouseType> getTypeList();

}
