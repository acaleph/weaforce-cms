package com.weaforce.system.action.finance;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.entity.area.Country;
import com.weaforce.system.entity.finance.Bank;
import com.weaforce.system.service.IFinanceService;
import com.weaforce.system.service.ISystemService;

@ParentPackage("default")
@Namespace("/system/finance")
public class BankAction extends AbstractCrudAction<Bank> {

	private static final long serialVersionUID = 5450869140515474580L;
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;
	@Autowired
	@Qualifier("financeService")
	private IFinanceService financeService;

	private Bank bank;
	private Long bankId;
	private Long countryId;

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	
	protected void prepareModel() throws Exception {
		bank = financeService.prepareBank(bank, bankId, countryId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		bank = financeService.saveBank(bank, countryId);
		return list();
	}

	
	public String delete() throws Exception {
		financeService.deleteBank(bankId);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = financeService.getBankPage(pageInfo);
		return SUCCESS;
	}


	
	public Bank getModel() {
		return bank;
	}

	public Long getCountryId() {
		return countryId;
	}

	public List<Country> getCountryList() {
		return systemService.getCountryList();
	}
}
