package com.weaforce.system.dao.impl.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.entity.app.Business;
import com.weaforce.system.dao.base.IBusinessDao;

@Repository("businessDao")
public class BusinessDao extends GenericDao<Business, Long> implements
		IBusinessDao {
	private static final String QUERY_BUSINESS = "From Business a Order by a.businessNameEn ";
	private static final String QUERY_BUSINESS_BY_RESOURCE = "From Business a Where a.businessIsResource=? Order by a.businessNameEn ";
	/**
	 * 取得模块list
	 * 
	 * @return
	 */
	public List<Business> getBusinessList() {
		return listQuery(QUERY_BUSINESS);
	}

	/**
	 * 根据资源标识,取得模块list
	 * 
	 * @param isResource
	 *            标识
	 * @return
	 */
	public List<Business> getBusinessListByResource(String isResource) {
		return listQuery(QUERY_BUSINESS_BY_RESOURCE, isResource);
	}
}
