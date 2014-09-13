package com.weaforce.system.service;

import java.util.List;

import com.weaforce.core.util.PageInfo;
import com.weaforce.system.entity.logistics.Customer;
import com.weaforce.system.entity.logistics.Producer;
import com.weaforce.system.entity.logistics.Provider;

public interface ILogisticsService {
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
	public Customer prepareCustomer(String account, Customer o,
			Long customerId, Long accountId);

	public Customer getCustomer(Long customerId);

	/**
	 * 保存客户
	 * 
	 * @param o
	 *            客户
	 * @param accountId
	 *            帐套
	 * @return
	 */
	public Customer saveCustomer(Customer o, Long accountId);

	public void deleteCustomer(Long customerId);

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

	public Customer getCustomerByAccount(String account, Long accountId);

	public PageInfo<Customer> getCustomerPage(PageInfo<Customer> pageInfo,
			String account, String customerCode, String customerName);

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
	public Provider prepareProvider(String account, Provider o,
			Long providerId, Long accountId);

	public Provider getProvider(Long providerId);

	public Provider saveProvider(Provider o, Long accountId);

	public void deleteProvider(Long providerId);

	/**
	 * 取得供应商 list
	 * 
	 * @param account
	 *            当前帐套
	 * @param flag
	 *            是否有none选项
	 * @return
	 */
	public List<Provider> getProviderList(String account, boolean flag);

	public PageInfo<Provider> getProviderPage(PageInfo<Provider> pageInfo,
			String account, String providerCode, String providerName);

	public Producer prepareProducer(String account, Producer o,
			Long producerId, Long accountId);

	public Producer getProduer(Long producerId);

	public Producer saveProducer(Producer o, Long accountId);

	public void deleteProducer(Long producerId);

	public PageInfo<Producer> getProducerPage(PageInfo<Producer> pageInfo,
			String account, String producerCode, String producerName);

}
