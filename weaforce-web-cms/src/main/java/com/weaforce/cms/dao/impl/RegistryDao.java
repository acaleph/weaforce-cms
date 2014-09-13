package com.weaforce.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.IRegistryDao;
import com.weaforce.cms.entity.Registry;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("registryDao")
public class RegistryDao extends GenericDao<Registry, Long> implements
		IRegistryDao {
	private static final String QUERY_REGISTRY_BY_LOGIN_PASSWORD = " From Registry a Where a.registryLogin=? And a.registryPassword=? ";

	/**
	 * 取得登录用户
	 * 
	 * @param userLogin
	 *            用户
	 * @param userPassword
	 *            密码
	 * @return
	 */
	public Registry getRegistryByLoginPassword(String userLogin,
			String userPassword) {
		return loadEntity(QUERY_REGISTRY_BY_LOGIN_PASSWORD, userLogin,
				userPassword);
	}
}
