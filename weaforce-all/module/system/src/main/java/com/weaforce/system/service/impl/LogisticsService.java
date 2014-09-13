package com.weaforce.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.weaforce.core.util.PageInfo;
import com.weaforce.core.util.StringUtil;
import com.weaforce.system.dao.logistics.ICustomerDao;
import com.weaforce.system.dao.logistics.IProducerDao;
import com.weaforce.system.dao.logistics.IProviderDao;
import com.weaforce.system.dao.organ.IAccountDao;
import com.weaforce.system.entity.logistics.Customer;
import com.weaforce.system.entity.logistics.Producer;
import com.weaforce.system.entity.logistics.Provider;
import com.weaforce.system.entity.organ.Account;
import com.weaforce.system.service.ILogisticsService;

@Service("logisticsService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class LogisticsService implements ILogisticsService {
	@Autowired
	@Qualifier("accountDao")
	private IAccountDao accountDao;
	@Autowired
	@Qualifier("customerDao")
	private ICustomerDao customerDao;
	@Autowired
	@Qualifier("providerDao")
	private IProviderDao providerDao;
	@Autowired
	@Qualifier("producerDao")
	private IProducerDao producerDao;

	/**
	 * 预处理客户
	 * 
	 * @param account
	 *            当前帐套
	 * @param o
	 *            客户
	 * @param customerId
	 *            客户主键
	 * @param accountId
	 *            帐套主键
	 * @return
	 */
	@Transactional(readOnly = true)
	public Customer prepareCustomer(String account, Customer o,
			Long customerId, Long accountId) {
		if (customerId == null) {
			o = customerDao.getCustomerByAccount(account, accountId);
			if (o == null) {
				o = new Customer();
				o.setCustomerAccount(accountDao.loadEntity(accountId));
			}
		} else
			o = customerDao.loadEntity(customerId);
		return o;
	}

	@Transactional(readOnly = true)
	public Customer getCustomer(Long customerId) {
		return customerDao.loadEntity(customerId);
	}

	/**
	 * 保存客户
	 * 
	 * @param o
	 *            客户
	 * @param accountId
	 *            帐套
	 * @return
	 */
	public Customer saveCustomer(Customer o, Long accountId) {
		if (accountId != null) {
			Account a = accountDao.loadEntity(accountId);
			if (a != null) {
				o.setCustomerAccount(accountDao.loadEntity(accountId));
				if (StringUtil.isEmpty(o.getCustomerShortName()))
					if (StringUtil.isNotEmpty(a.getAccountShortName()))
						o.setCustomerShortName(a.getAccountShortName());
			}
		} else
			o.setCustomerAccount(null);
		return customerDao.save(o);
	}

	public void deleteCustomer(Long customerId) {
		customerDao.delete(customerId);
	}

	/**
	 * 取得客户 list
	 * 
	 * @param account
	 *            帐套
	 * @param flag
	 *            是否有none选项
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Customer> getCustomerList(String account, boolean flag) {
		return customerDao.getCustomerList(account, flag);
	}

	@Transactional(readOnly = true)
	public Customer getCustomerByAccount(String account, Long accountId) {
		return customerDao.getCustomerByAccount(account, accountId);
	}

	@Transactional(readOnly = true)
	public PageInfo<Customer> getCustomerPage(PageInfo<Customer> pageInfo,
			String account, String customerCode, String customerName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Customer a Where a.account= '" + account + "' ");
		if (StringUtil.isNotEmpty(customerCode))
			sb.append(" And a.customerCode like '%" + customerCode + "%'");
		if (StringUtil.isNotEmpty(customerName))
			sb.append(" And a.customerAccount.accountNameCn like '%"
					+ customerName + "%'");
		pageInfo = customerDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.customerCode ");
		return pageInfo;
	}

	/**
	 * 预处理供应商
	 * 
	 * @param account
	 *            当前帐套
	 * @param o
	 *            供应商
	 * @param providerId
	 *            供应商主键
	 * @param accountId
	 *            帐套主键
	 * @return
	 */
	@Transactional(readOnly = true)
	public Provider prepareProvider(String account, Provider o,
			Long providerId, Long accountId) {
		if (providerId == null) {
			o = providerDao.getProviderByAccount(account, accountId);
			if (o == null) {
				o = new Provider();
				o.setProviderAccount(accountDao.loadEntity(accountId));
			}
		} else
			o = providerDao.loadEntity(providerId);
		return o;
	}

	@Transactional(readOnly = true)
	public Provider getProvider(Long providerId) {
		return providerDao.loadEntity(providerId);
	}

	public Provider saveProvider(Provider o, Long accountId) {
		if (accountId == null)
			o.setProviderAccount(null);
		else {
			Account a = accountDao.loadEntity(accountId);
			if (a != null) {
				o.setProviderAccount(a);
				if (StringUtil.isEmpty(o.getProviderShortName()))
					if (StringUtil.isNotEmpty(a.getAccountShortName()))
						o.setProviderShortName(a.getAccountShortName());
			}
		}
		return providerDao.save(o);
	}

	public void deleteProvider(Long providerId) {
		providerDao.delete(providerId);
	}

	/**
	 * 取得供应商 list
	 * 
	 * @param account
	 *            当前帐套
	 * @param flag
	 *            是否有none选项
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Provider> getProviderList(String account, boolean flag) {
		return providerDao.getProviderList(account, flag);
	}

	@Transactional(readOnly = true)
	public PageInfo<Provider> getProviderPage(PageInfo<Provider> pageInfo,
			String account, String providerCode, String providerName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Provider a Where a.account= '" + account + "' ");
		if (StringUtil.isNotEmpty(providerCode))
			sb.append(" And a.providerCode like '%" + providerCode + "%'");
		if (StringUtil.isNotEmpty(providerName))
			sb.append(" And a.providerAccount.accountNameCn like '%"
					+ providerName + "%'");
		pageInfo = providerDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.providerCode ");
		return pageInfo;
	}

	@Transactional(readOnly = true)
	public Producer prepareProducer(String account, Producer o,
			Long producerId, Long accountId) {
		if (producerId == null) {
			o = producerDao.getProducerByAccount(account, accountId);
			if (o == null) {
				o = new Producer();
				o.setProducerAccount(accountDao.loadEntity(accountId));
			}
		} else
			o = producerDao.loadEntity(producerId);
		return o;
	}

	@Transactional(readOnly = true)
	public Producer getProduer(Long producerId) {
		return producerDao.loadEntity(producerId);
	}

	public Producer saveProducer(Producer o, Long accountId) {
		if (accountId == null)
			o.setProducerAccount(null);
		else {
			Account a = accountDao.loadEntity(accountId);
			if (a != null) {
				o.setProducerAccount(a);
				if (StringUtil.isEmpty(o.getProducerShortName()))
					if (StringUtil.isNotEmpty(a.getAccountShortName()))
						o.setProducerShortName(a.getAccountShortName());
			}
		}
		return producerDao.save(o);
	}

	public void deleteProducer(Long producerId) {
		producerDao.delete(producerId);
	}

	@Transactional(readOnly = true)
	public PageInfo<Producer> getProducerPage(PageInfo<Producer> pageInfo,
			String account, String producerCode, String producerName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Producer a Where a.account= '" + account + "' ");
		if (StringUtil.isNotEmpty(producerCode))
			sb.append(" And a.producerCode like '%" + producerCode + "%'");
		if (StringUtil.isNotEmpty(producerName))
			sb.append(" And a.producerAccount.accountNameCn like '%"
					+ producerName + "%'");
		pageInfo = producerDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.producerCode ");
		return pageInfo;
	}
}
