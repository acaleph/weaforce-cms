package com.weaforce.cms.dao.impl.rent;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.rent.IHouseDao;
import com.weaforce.cms.entity.rent.House;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("houseDao")
public class HouseDao extends GenericDao<House, Long> implements IHouseDao {
	private static final String QUERY_HOUSE_BY_BUILDING = " From House a Where a.houseBuilding.buildingId=?";

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
			Integer pageNumber) {
		StringBuffer sb = new StringBuffer();
		/* 初始化页参数 */
		// 每页的记录数
		Integer pageSize = 14;
		// 页数
		Integer pageCount = 0;
		// 所有记录数
		Integer totalCount = executeStat(
				" Select Count(*) "
						+ getQueryHql(QUERY_HOUSE_BY_BUILDING, typeId, tagId),
				buildingId).intValue();
		if (totalCount % pageSize == 0)
			pageCount = totalCount / pageSize;
		else
			pageCount = totalCount / pageSize + 1;
		/* 初始化页参数 */
		List<House> houseList = listQueryPage(getQueryHql(QUERY_HOUSE_BY_BUILDING,
				typeId, tagId), pageSize * (pageNumber - 1), pageSize,
				buildingId);
		for (House house : houseList) {
			if (sb.length() == 0)
				sb.append("[");
			else
				sb.append(",");
			sb.append("{\"houseId\":\"" + house.getHouseId()
					+ "\",\"houseTitle\":\"" + house.getHouseTitle()
					+ "\",\"houseContact\":\"" + house.getHouseContact()
					+ "\",\"houseMobile\":\"" + house.getHouseContactMobile()
					+ "\",\"housePhone\":\"" + house.getHouseContactPhone()
					+ "\",\"createTime\":\"" + house.getDate() + "\"}");
		}
		StringBuffer sbPage = new StringBuffer("[");
		sbPage.append("{\"pageNumber\":\"" + pageNumber + "\",\"pageCount\":\""
				+ pageCount + "\",\"totalCount\":\"" + totalCount
				+ "\",\"page\":" + sb.toString() + "}]");
		return sbPage.toString();
	}

	public String getQueryHql(String queryStr, Long typeId, Long tagId) {
		if (typeId != null && typeId > 0)
			queryStr = queryStr + " And a.houseType.typeId =" + typeId;
		if (tagId != null && tagId > 0)
			queryStr = queryStr + " And a.houseTag.tagId=" + tagId;
		return queryStr;
	}
}
