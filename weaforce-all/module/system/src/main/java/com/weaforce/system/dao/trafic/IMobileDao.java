package com.weaforce.system.dao.trafic;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.trafic.Mobile;

public interface IMobileDao extends IGenericDao<Mobile, Long> {
	/**
	 * Get mobile list
	 * 
	 * @return
	 */
	public List<Mobile> getMobileList();

	/**
	 * Get Mobile by User login
	 * 
	 * @param userLogin
	 *            User login
	 * @return
	 */
	public Mobile getMobileByUserLogin(String userLogin, String mobile);
}
