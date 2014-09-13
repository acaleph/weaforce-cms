package com.weaforce.system.entity.organ;

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

@Entity
@Table(name = "organ_account_contact")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class AccountContact {
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "177", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "CONTACT_ID", length = 20)
	private Long contactId;
	// 简称
	@Column(name = "CONTACT_SHORT_NAME", length = 20)
	private String contactShortName;
	// 全称
	@Column(name = "CONTACT_FULL_NAME", length = 180)
	private String contactFullName;
	// 工作电话
	@Column(name = "CONTACT_WORK_PHONE", length = 20)
	private String contactWorkPhone;
	// 家庭电话
	@Column(name = "CONTACT_HOME_PHONE", length = 20)
	private String contactHomePhone;
	// 手机
	@Column(name = "CONTACT_MOBILE", length = 20)
	private String contactWorkMobile;
	// 传真
	@Column(name = "CONTACT_FAX", length = 20)
	private String contactFax;
	// 职称
	@Column(name = "CONTACT_STAFF_TITLE", length = 45)
	private String contactStaffTitle;
	// 电子邮件
	@Column(name = "CONTACT_EMAIL", length = 45)
	private String contactEmail;
	// 帐套
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTACT_ACCOUNT_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_CONTACT_ACCOUNT_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Account contactAccount;

	public AccountContact() {

	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public String getContactShortName() {
		return contactShortName;
	}

	public void setContactShortName(String contactShortName) {
		this.contactShortName = contactShortName;
	}

	public String getContactFullName() {
		return contactFullName;
	}

	public void setContactFullName(String contactFullName) {
		this.contactFullName = contactFullName;
	}

	public String getContactWorkPhone() {
		return contactWorkPhone;
	}

	public void setContactWorkPhone(String contactWorkPhone) {
		this.contactWorkPhone = contactWorkPhone;
	}

	public String getContactHomePhone() {
		return contactHomePhone;
	}

	public void setContactHomePhone(String contactHomePhone) {
		this.contactHomePhone = contactHomePhone;
	}

	public String getContactWorkMobile() {
		return contactWorkMobile;
	}

	public void setContactWorkMobile(String contactWorkMobile) {
		this.contactWorkMobile = contactWorkMobile;
	}

	public String getContactFax() {
		return contactFax;
	}

	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}

	public String getContactStaffTitle() {
		return contactStaffTitle;
	}

	public void setContactStaffTitle(String contactStaffTitle) {
		this.contactStaffTitle = contactStaffTitle;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public Account getContactAccount() {
		return contactAccount;
	}

	public void setContactAccount(Account contactAccount) {
		this.contactAccount = contactAccount;
	}

}
