package com.weaforce.system.action.logistics;


import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.entity.logistics.Customer;
import com.weaforce.system.service.ILogisticsService;

@ParentPackage("default")
@Namespace("/system/logistics")
public class CustomerAction extends AbstractCrudAction<Customer> {
	private static final long serialVersionUID = 2443582407772742456L;
	@Autowired
	@Qualifier("logisticsService")
	private ILogisticsService logisticsService;

	private Customer customer;
	private Long customerId;
	private Long accountId;


	private String queryCode;
	private String queryName;

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	
	protected void prepareModel() throws Exception {
		customer = logisticsService.prepareCustomer(getAdmin().getAccount(),
				customer, customerId, accountId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		customer = logisticsService.saveCustomer(customer, accountId);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = logisticsService.getCustomerPage(pageInfo, getAdmin()
				.getAccount(), queryCode, queryName);
		return SUCCESS;
	}


	
	public String delete() throws Exception {
		logisticsService.deleteCustomer(customerId);
		return list();
	}

	public Customer getModel() {
		return customer;
	}

	/**
	 * 客户实体
	 * 
	 * @param accountId
	 */

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}


	public String getQueryCode() {
		return queryCode;
	}

	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
