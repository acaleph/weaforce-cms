package com.weaforce.system.dao.impl.logistics;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.logistics.ICustomerDao;
import com.weaforce.system.entity.logistics.Customer;

@Repository("customerDao")
public class CustomerDao extends GenericDao<Customer, Long> implements
		ICustomerDao {
	private static final String QUERY_CUSTOMER = " From Customer a Where a.account = ? ";
	private static final String QUERY_CUSTOMER_BY_ACCOUNT = " From Customer a Where a.account = ? and a.customerAccount.accountId=? ";

	/**
	 * 取得客户 list
	 * 
	 * @param account
	 *            帐套
	 * @param flag
	 *            是否有none选项
	 * @return
	 */
	public List<Customer> getCustomerList(String account, boolean flag) {
		List<Customer> customerList = listQuery(QUERY_CUSTOMER, account);
		if (flag) {
			Customer o = new Customer();
			o.setCustomerCode("--- none ---");
			customerList.add(0, o);
		}
		return customerList;
		/**
		 * 根据帐套取得客户
		 * 
		 * @param account
		 *            当前帐套
		 * @param accountId
		 *            目标帐套主键
		 * @return
		 */
	}

	public Customer getCustomerByAccount(String account, Long accountId) {
		return loadEntity(QUERY_CUSTOMER_BY_ACCOUNT, account, accountId);
	}
}
