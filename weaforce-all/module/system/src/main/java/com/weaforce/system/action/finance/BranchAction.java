package com.weaforce.system.action.finance;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.entity.area.Country;
import com.weaforce.system.entity.finance.BankBranch;
import com.weaforce.system.service.IFinanceService;
import com.weaforce.system.service.ISystemService;

@ParentPackage("default")
@Namespace("/system/finance")
public class BranchAction extends AbstractCrudAction<BankBranch> {
	private static final long serialVersionUID = -5009565724158118585L;
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;
	@Autowired
	@Qualifier("financeService")
	private IFinanceService financeService;

	private BankBranch branch;
	private Long branchId;
	private Long bankId;
	private Long countryId;

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	
	protected void prepareModel() throws Exception {
		branch = financeService.prepareBranch(branch, branchId, bankId,
				countryId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		branch = financeService.saveBranch(branch, branchId, bankId, countryId);
		return list();
	}

	
	public String delete() throws Exception {
		financeService.deleteBranch(branchId);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = financeService.getBranchPage(pageInfo, bankId, countryId);
		return SUCCESS;
	}

	
	public BankBranch getModel() {
		return null;
	}

	public Long getBankId() {
		return bankId;
	}

	public Long getCountryId() {
		return countryId;
	}

	public List<Country> getCountryList() {
		return systemService.getCountryList();
	}
}
