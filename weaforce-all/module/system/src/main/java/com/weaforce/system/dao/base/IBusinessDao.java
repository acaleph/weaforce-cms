package com.weaforce.system.dao.base;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.entity.app.Business;

public interface IBusinessDao extends IGenericDao<Business, Long> {
	/**
	 * 取得模块list
	 * 
	 * @return
	 */
	public List<Business> getBusinessList();

	/**
	 * 根据资源标识,取得模块list
	 * 
	 * @param isResource
	 *            标识
	 * @return
	 */
	public List<Business> getBusinessListByResource(String isResource);
}
