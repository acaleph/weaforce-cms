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
@Table(name = "logistics_provider")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Provider extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -4667847102326764028L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "64", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "PROVIDER_ID", length = 20)
	private Long providerId;
	// 代码
	@Column(name = "PROVIDER_CODE", length = 10)
	private String providerCode;
	// 代码
	@Column(name = "PROVIDER_SHORT_NAME", length = 20)
	private String providerShortName;
	// 活动
	@Column(name = "PROVIDER_IS_ACTIVE", length = 1)
	private String providerIsActive;
	// 月结天数
	@Column(name = "PROVIDER_FINANCE_DAY", length = 10)
	private Integer providerFinanceDay;
	// 帐套
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROVIDER_ACCOUNT_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_PROVIDER_ACCOUNT_ID")
	private Account providerAccount;

	public Provider() {
		providerIsActive = "1";
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	

	public String getProviderShortName() {
		return providerShortName;
	}

	public void setProviderShortName(String providerShortName) {
		this.providerShortName = providerShortName;
	}

	public String getProviderIsActive() {
		return providerIsActive;
	}

	public void setProviderIsActive(String providerIsActive) {
		this.providerIsActive = providerIsActive;
	}

	public Integer getProviderFinanceDay() {
		return providerFinanceDay;
	}

	public void setProviderFinanceDay(Integer providerFinanceDay) {
		this.providerFinanceDay = providerFinanceDay;
	}

	public Account getProviderAccount() {
		return providerAccount;
	}

	public void setProviderAccount(Account providerAccount) {
		this.providerAccount = providerAccount;
	}

}
