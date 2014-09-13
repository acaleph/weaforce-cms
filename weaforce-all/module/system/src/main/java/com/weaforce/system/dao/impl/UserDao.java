package com.weaforce.system.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import com.weaforce.system.dao.IUserDao;
import com.weaforce.entity.admin.User;
import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.core.exception.ServiceException;
import com.weaforce.core.util.StringUtil;

@Repository("userDao")
public class UserDao extends GenericDao<User, Long> implements IUserDao {
	private static final String QUERY_USER_BY_LOGIN_PASSWORD = " From User a Where a.userLogin=? And a.userPassword=? ";
	private static final String QUERY_USER_BY_ACCOUNT = " From User a Where a.account=? ";

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
	public User getUserByLoginPassword(String login, String password) {
		return loadEntity(QUERY_USER_BY_LOGIN_PASSWORD, login, password);
	}

	/**
	 * 取得User list
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	public List<User> getUserListByAccount(String account) {
		return listQuery(QUERY_USER_BY_ACCOUNT, account);
	}

	/**
	 * 取得用户登录ID的JSON格式字串
	 * 
	 * @param account
	 *            当前帐套
	 * @return
	 */
	public String getUserLoginJSON(String account) {
		List<User> userList = listQuery(QUERY_USER_BY_ACCOUNT, account);
		StringBuffer sb = new StringBuffer("[");
		for (User o : userList)
			sb.append("\"" + o.getUserLogin() + "\",");
		sb = new StringBuffer(StringUtil.cutLastChar(sb.toString(), ","));
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Get user list by query login stirng
	 * 
	 * @param queryLogin
	 *            User login
	 * @return
	 * @throws InterruptedException
	 */
	public List<User> getUserListByLogin(String queryLogin)
			throws InterruptedException {
		// User user = loadEntity((long) 9);
		FullTextSession fullTextSession = Search
				.getFullTextSession(getSession());

		// Transaction transactionManager = fullTextSession.beginTransaction();
		// fullTextSession.beginTransaction().begin();
		// fullTextSession.save(user);
		List<User> result = new ArrayList<User>();
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(User.class).get();

		org.apache.lucene.search.Query query = qb.keyword()
				.onFields("userLogin").matching(queryLogin).createQuery();
		System.out.println(query.toString());
		// wrap Lucene query in a org.hibernate.Query
		org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(
				query, User.class);
		// execute search
		hibQuery.setFirstResult(20).setMaxResults(20);
		result = hibQuery.list();
		// transactionManager.commit();
		// getSession().close();
		// System.out.println("size: " + result.size());

		result = listQuery(" From User a Where a.userLogin like '%"
				+ queryLogin + "%'");
		return result;
	}

	/**
	 * 判断用户登录是否已经存在
	 * 
	 * @param userLogin
	 *            用户登录ID
	 * @return String类型的布尔值
	 */
	public String checkBeingUserLogin(String userLogin) {
		if (loadEntity("userLogin", userLogin) == null)
			return "true";
		return "false";
	}
}
