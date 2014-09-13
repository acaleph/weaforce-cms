package com.weaforce.cms.dao;

import com.weaforce.cms.entity.Registry;
import com.weaforce.core.dao.IGenericDao;

public interface IRegistryDao extends IGenericDao<Registry, Long> {
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
			String userPassword);

}
