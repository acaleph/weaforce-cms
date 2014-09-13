package com.weaforce.system.entity.finance;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.entity.area.Country;


/**
 * 银行分支机构管理类:Bank->BankBranch
 * 
 * @author Manfred
 * 
 */
@Entity
@Table(name = "finance_bank_branch")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class BankBranch {
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "176", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "BRANCH_ID", length = 20)
	private Long branchId;
	// 简称
	@Column(name = "BRANCH_SHORT_NAME", length = 45)
	private String branchShortName;
	// 全称
	@Column(name = "BRANCH_FULL_NAME", length = 90)
	private String branchFullName;
	// 地址
	@Column(name = "BRANCH_ADDRESS", length = 180)
	private String branchAddress;
	// 电话
	@Column(name = "BRANCH_PHONE", length = 20)
	private String branchPhone;
	// 电话传真
	@Column(name = "BRANCH_TELEX", length = 20)
	private String branchTelex;
	// 帐号
	@Column(name = "BRANCH_ACCOUNT", length = 45)
	private String branchAccount;
	// 银行
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH_BANK_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_BRANCH_BANK_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Bank branchBank;
	// 国家
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH_COUNTRY_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_BRANCH_COUNTRY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Country branchCountry;

	public BankBranch() {

	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getBranchShortName() {
		return branchShortName;
	}

	public void setBranchShortName(String branchShortName) {
		this.branchShortName = branchShortName;
	}

	public String getBranchFullName() {
		return branchFullName;
	}

	public void setBranchFullName(String branchFullName) {
		this.branchFullName = branchFullName;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getBranchPhone() {
		return branchPhone;
	}

	public void setBranchPhone(String branchPhone) {
		this.branchPhone = branchPhone;
	}

	public String getBranchTelex() {
		return branchTelex;
	}

	public void setBranchTelex(String branchTelex) {
		this.branchTelex = branchTelex;
	}

	public String getBranchAccount() {
		return branchAccount;
	}

	public void setBranchAccount(String branchAccount) {
		this.branchAccount = branchAccount;
	}

	public Bank getBranchBank() {
		return branchBank;
	}

	public void setBranchBank(Bank branchBank) {
		this.branchBank = branchBank;
	}

	public Country getBranchCountry() {
		return branchCountry;
	}

	public void setBranchCountry(Country branchCountry) {
		this.branchCountry = branchCountry;
	}

}
