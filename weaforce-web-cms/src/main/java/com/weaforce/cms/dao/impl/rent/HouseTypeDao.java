package com.weaforce.cms.dao.impl.rent;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.rent.IHouseTypeDao;
import com.weaforce.cms.entity.rent.HouseType;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("houseTypeDao")
public class HouseTypeDao extends GenericDao<HouseType, Long> implements
		IHouseTypeDao {
	private static final String QUERY_TYPE = " From HouseType a ";

	/**
	 * 取得房间类型 list
	 * 
	 * @return
	 */
	public List<HouseType> getTypeList() {
		return listQuery(QUERY_TYPE);
	}

}
