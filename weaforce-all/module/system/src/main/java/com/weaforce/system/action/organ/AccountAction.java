package com.weaforce.system.action.organ;


import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.system.entity.organ.Account;
import com.weaforce.system.service.ISystemService;

@ParentPackage("default")
@Namespace("/system/organ")
public class AccountAction extends AbstractCrudAction<Account> {
	private static final long serialVersionUID = 7853925385684019767L;
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	private Account account;
	private Long accountId;
	private String accountIdentity;
	private String accountCode;

	private String queryAccountNameEn;
	private String queryAccountNameCn;
	private String queryAccountShortName;

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	protected void prepareModel() throws Exception {
		account = systemService.prepareAccount(account, accountId);
	}

	public String input() throws Exception {
		return INPUT;
	}

	public String save() throws Exception {
		account = systemService.saveAccount(account);
		return list();
	}

	public String list() throws Exception {
		pageInfo = systemService.getAccountPage(pageInfo, queryAccountNameCn,
				queryAccountNameEn, queryAccountShortName);
		return SUCCESS;
	}

	public String delete() throws Exception {
		systemService.deleteAccount(accountId);
		return list();
	}

	/**
	 * 身份
	 * 
	 * @param accountIdentity
	 */

	public void setAccountIdentity(String accountIdentity) {
		this.accountIdentity = accountIdentity;
	}

	/**
	 * 代码
	 * 
	 * @param accountCode
	 */
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	/**
	 * 验证帐套身份是否存在
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkAccountIdentity() throws Exception {
		return StrutsUtil.renderText(systemService.checkAccount(accountId,
				"accountIdentity", accountIdentity));
	}

	/**
	 * 验证帐套代码是否存在
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkAccountCode() throws Exception {
		return StrutsUtil.renderText(systemService.checkAccount(accountId,
				"accountCode", accountCode));
	}

	public String getQueryAccountNameEn() {
		return queryAccountNameEn;
	}

	public void setQueryAccountNameEn(String queryAccountNameEn) {
		this.queryAccountNameEn = queryAccountNameEn;
	}

	public String getQueryAccountNameCn() {
		return queryAccountNameCn;
	}

	public void setQueryAccountNameCn(String queryAccountNameCn) {
		this.queryAccountNameCn = queryAccountNameCn;
	}

	public String getQueryAccountShortName() {
		return queryAccountShortName;
	}

	public void setQueryAccountShortName(String queryAccountShortName) {
		this.queryAccountShortName = queryAccountShortName;
	}

	public Account getModel() {
		return this.account;
	}

}
