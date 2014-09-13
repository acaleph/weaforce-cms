package com.weaforce.system.entity.organ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "organ_account")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Account implements Serializable {
	private static final long serialVersionUID = -5768868852332983737L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "35", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "ACCOUNT_ID", length = 20)
	private Long accountId;
	// 简称
	@Column(name = "ACCOUNT_SHORT_NAME", length = 20)
	private String accountShortName;
	// 中文名称
	@Column(name = "ACCOUNT_NAME_CN", length = 180)
	private String accountNameCn;
	// 英文名称
	@Column(name = "ACCOUNT_NAME_EN", length = 180)
	private String accountNameEn;
	// 标识
	@Column(name = "ACCOUNT_IDENTITY", length = 20)
	private String accountIdentity;
	// 代码
	@Column(name = "ACCOUNT_CODE", length = 20)
	private String accountCode;
	// 联系人
	@Column(name = "ACCOUNT_DEFAULT_CONTACT", length = 45)
	private String accountDefaultContact;
	// 联系人
	@Column(name = "ACCOUNT_CONTACT_CELLPHONE", length = 20)
	private String accountContactCellphone;
	// 电话
	@Column(name = "ACCOUNT_PHONE", length = 45)
	private String accountPhone;
	// 传真
	@Column(name = "ACCOUNT_FAX", length = 45)
	private String accountFax;
	// 邮编
	@Column(name = "ACCOUNT_POSTCODE", length = 45)
	private String accountPostcode;
	// 联系地址
	@Column(name = "ACCOUNT_EMAIL", length = 45)
	private String accountEmail;
	// 网站
	@Column(name = "ACCOUNT_WEB", length = 45)
	private String accountWeb;
	// 网站
	@Column(name = "ACCOUNT_ADDRESS", length = 90)
	private String accountAddress;
	// 活动
	@Column(name = "ACCOUNT_IS_ACTIVE", length = 1)
	private String accountIsActive;
	// 是否有评分内容:统计需要
	@Column(name = "ACCOUNT_IS_SCORE", length = 1)
	private String accountIsScore;
	// 广告商家
	@Column(name = "ACCOUNT_ADS_ID", length = 20)
	private Long accountAdsId;
	// 创建时间
	@Column(name = "CREATE_TIME", length = 20)
	private Long createTime;
	// 当前帐套下的联系人
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "contactAccount")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<AccountContact> accountContact = new ArrayList<AccountContact>();

	public Account() {
		accountIsActive = "1";
		accountIsScore = "0";
		createTime = System.currentTimeMillis();
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountShortName() {
		return accountShortName;
	}

	public void setAccountShortName(String accountShortName) {
		this.accountShortName = accountShortName;
	}

	public String getAccountNameCn() {
		return accountNameCn;
	}

	public void setAccountNameCn(String accountNameCn) {
		this.accountNameCn = accountNameCn;
	}

	public String getAccountNameEn() {
		return accountNameEn;
	}

	public void setAccountNameEn(String accountNameEn) {
		this.accountNameEn = accountNameEn;
	}

	public String getAccountIdentity() {
		return accountIdentity;
	}

	public void setAccountIdentity(String accountIdentity) {
		this.accountIdentity = accountIdentity;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountDefaultContact() {
		return accountDefaultContact;
	}

	public void setAccountDefaultContact(String accountDefaultContact) {
		this.accountDefaultContact = accountDefaultContact;
	}

	public String getAccountContactCellphone() {
		return accountContactCellphone;
	}

	public void setAccountContactCellphone(String accountContactCellphone) {
		this.accountContactCellphone = accountContactCellphone;
	}

	public String getAccountPhone() {
		return accountPhone;
	}

	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}

	public String getAccountFax() {
		return accountFax;
	}

	public void setAccountFax(String accountFax) {
		this.accountFax = accountFax;
	}

	public String getAccountPostcode() {
		return accountPostcode;
	}

	public void setAccountPostcode(String accountPostcode) {
		this.accountPostcode = accountPostcode;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getAccountWeb() {
		return accountWeb;
	}

	public void setAccountWeb(String accountWeb) {
		this.accountWeb = accountWeb;
	}

	public String getAccountAddress() {
		return accountAddress;
	}

	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}

	public String getAccountIsActive() {
		return accountIsActive;
	}

	public void setAccountIsActive(String accountIsActive) {
		this.accountIsActive = accountIsActive;
	}

	public Long getAccountAdsId() {
		return accountAdsId;
	}

	public void setAccountAdsId(Long accountAdsId) {
		this.accountAdsId = accountAdsId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getAccountIsScore() {
		return accountIsScore;
	}

	public void setAccountIsScore(String accountIsScore) {
		this.accountIsScore = accountIsScore;
	}

	public List<AccountContact> getAccountContact() {
		return accountContact;
	}

	public void setAccountContact(List<AccountContact> accountContact) {
		this.accountContact = accountContact;
	}

}
