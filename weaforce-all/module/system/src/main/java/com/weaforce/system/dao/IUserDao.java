package com.weaforce.system.dao;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.core.exception.ServiceException;
import com.weaforce.entity.admin.User;

public interface IUserDao extends IGenericDao<User, Long> {
	/**
	 * 取得用户,通过登录ID和密码
	 * 
	 * @param login
	 *            登录ID
	 * @param password
	 *            密码
	 * @return
	 * @throws ServiceException
	 */
	public User getUserByLoginPassword(String login, String password);

	/**
	 * 取得User list
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	public List<User> getUserListByAccount(String account);

	/**
	 * 取得用户登录ID的JSON格式字串
	 * 
	 * @param account
	 *            当前帐套
	 * @return
	 */
	public String getUserLoginJSON(String account);

	/**
	 * 判断用户登录是否已经存在
	 * 
	 * @param userLogin
	 *            用户登录ID
	 * @return String类型的布尔值
	 */
	public String checkBeingUserLogin(String userLogin);

	/**
	 * Get user list by query login stirng
	 * 
	 * @param queryLogin User login
	 * @return
	 * @throws InterruptedException 
	 */

	public List<User> getUserListByLogin(String queryLogin) throws InterruptedException;
}
