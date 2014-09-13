package com.weaforce.system.dao.logistics;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.logistics.Customer;

public interface ICustomerDao extends IGenericDao<Customer, Long> {
	/**
	 * 取得客户 list
	 * 
	 * @param account
	 *            帐套
	 * @param flag
	 *            是否有none选项
	 * @return
	 */
	public List<Customer> getCustomerList(String account, boolean flag);

	/**
	 * 根据帐套取得客户
	 * 
	 * @param account
	 *            当前帐套
	 * @param accountId
	 *            目标帐套主键
	 * @return
	 */
	public Customer getCustomerByAccount(String account, Long accountId);
}
