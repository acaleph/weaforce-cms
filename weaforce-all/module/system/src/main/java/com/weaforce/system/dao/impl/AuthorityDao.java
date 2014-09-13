package com.weaforce.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.IAuthorityDao;
import com.weaforce.entity.admin.Authority;

@Repository("authorityDao")
public class AuthorityDao extends GenericDao<Authority, Long> implements
		IAuthorityDao {
	private static final String QUERY_AUTHORITY = " From Authority a Order by a.authorityCode ";
	private static final String QUERY_AUTHORITY_BY_BUSINESS = " From Authority a Where a.authorityBusiness.businessId=?  Order by a.authorityCode ";

	/**
	 * 取得授权list，提供role进行授权分配
	 * 
	 * @param account
	 * @return
	 */
	public List<Authority> getAuthorityList() {
		return listQuery(QUERY_AUTHORITY);
	}

	/**
	 * 根据系统,取得授权list
	 * 
	 * @param businessId
	 *            系统
	 * @return
	 */
	public List<Authority> getAuthorityListByBusiness(Long businessId) {
		return listQuery(QUERY_AUTHORITY_BY_BUSINESS, businessId);
	}

}
