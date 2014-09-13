package com.weaforce.system.dao.impl.trafic;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.trafic.IMobileDao;
import com.weaforce.system.entity.trafic.Mobile;

@Repository("mobileDao")
public class MobileDao extends GenericDao<Mobile, Long> implements IMobileDao {
	private static final String QUERY_MOBILE = " From Mobile a ";
	private static final String GET_MOBILE_BY_USER_LOGIN = " From Mobile a Where a.userLogin = ? and a.mobileMobile=? ";
	//private static final String GET_MOBILE_BY_USER_LOGIN = " From Mobile a Where a.mobileMobile = ? ";

	/**
	 * Get mobile list
	 * 
	 * @return
	 */
	public List<Mobile> getMobileList() {
		List<Mobile> mobileList = listQuery(QUERY_MOBILE);
		initObjects(mobileList);
		return mobileList;
	}

	/**
	 * Get Mobile by User login
	 * 
	 * @param userLogin
	 *            User login
	 * @return
	 */
	public Mobile getMobileByUserLogin(String userLogin, String mobile) {
		return loadEntity(GET_MOBILE_BY_USER_LOGIN, userLogin,mobile);
		//return loadEntity("userLogin",userLogin);
	}
}
