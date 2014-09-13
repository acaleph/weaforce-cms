package com.weaforce.system.entity.logistics;

import java.io.Serializable;

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

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.system.entity.organ.Account;

@Entity
@Table(name = "logistics_customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Customer extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -8012708925805573900L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "63", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "CUSTOMER_ID", length = 20)
	private Long customerId;
	// 代码
	@Column(name = "CUSTOMER_CODE", length = 10)
	private String customerCode;
	// 简称
	@Column(name = "CUSTOMER_SHORT_NAME", length = 20)
	private String customerShortName;
	// 距离
	@Column(name = "CUSTOMER_DISTANCE", length = 10)
	private Integer customerDistance;
	// 活动
	@Column(name = "CUSTOMER_IS_ACTIVE", length = 1)
	private String customerIsActive;
	// 月结天数
	@Column(name = "CUSTOMER_FINANCE_DAY", length = 10)
	private Integer customerFinanceDay;
	// 帐套
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ACCOUNT_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_CUSTOMER_ACCOUNT_ID")
	private Account customerAccount;

	public Customer() {
		customerIsActive = "1";
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerShortName() {
		return customerShortName;
	}

	public void setCustomerShortName(String customerShortName) {
		this.customerShortName = customerShortName;
	}

	public Integer getCustomerDistance() {
		return customerDistance;
	}

	public void setCustomerDistance(Integer customerDistance) {
		this.customerDistance = customerDistance;
	}

	public String getCustomerIsActive() {
		return customerIsActive;
	}

	public void setCustomerIsActive(String customerIsActive) {
		this.customerIsActive = customerIsActive;
	}

	public Integer getCustomerFinanceDay() {
		return customerFinanceDay;
	}

	public void setCustomerFinanceDay(Integer customerFinanceDay) {
		this.customerFinanceDay = customerFinanceDay;
	}

	public Account getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(Account customerAccount) {
		this.customerAccount = customerAccount;
	}

}
