package com.weaforce.cms.dao.rent;

import com.weaforce.cms.entity.rent.House;
import com.weaforce.core.dao.IGenericDao;

public interface IHouseDao extends IGenericDao<House, Long> {
	/**
	 * 取得JSON格式房间记录
	 * 
	 * @param buildingId
	 *            建筑主键
	 * @param typeId
	 *            房间类型主键
	 * @param tagId
	 *            出租房价标签主键
	 * @param pageNumber
	 *            当前页数
	 * @return
	 */
	public String getHouseJSON(Long buildingId, Long typeId, Long tagId,
			Integer pageNumber);
}
