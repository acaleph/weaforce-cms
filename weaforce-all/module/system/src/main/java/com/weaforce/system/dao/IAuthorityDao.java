package com.weaforce.system.dao;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.entity.admin.Authority;

public interface IAuthorityDao extends IGenericDao<Authority, Long> {
	/**
	 * 取得授权list，提供role进行授权分配
	 * 
	 * @param account
	 * @return
	 */
	public List<Authority> getAuthorityList();

	/**
	 * 根据系统,取得授权list
	 * 
	 * @param businessId
	 *            系统
	 * @return
	 */
	public List<Authority> getAuthorityListByBusiness(Long businessId);

}
