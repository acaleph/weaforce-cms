package com.weaforce.system.action.logistics;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.entity.logistics.Provider;
import com.weaforce.system.service.ILogisticsService;

@ParentPackage("default")
@Namespace("/system/logistics")
public class ProviderAction extends AbstractCrudAction<Provider> {
	private static final long serialVersionUID = -4199728720492732506L;
	@Autowired
	@Qualifier("logisticsService")
	private ILogisticsService logisticsService;

	private Provider provider;
	private Long providerId;
	private Long accountId;

	private String queryCode;
	private String queryName;

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	
	protected void prepareModel() throws Exception {
		provider = logisticsService.prepareProvider(getAdmin().getAccount(),
				provider, providerId, accountId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		provider = logisticsService.saveProvider(provider, accountId);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = logisticsService.getProviderPage(pageInfo, getAdmin()
				.getAccount(), queryCode, queryName);
		return SUCCESS;
	}


	
	public String delete() throws Exception {
		return save();
	}

	public Provider getModel() {
		return provider;
	}

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
