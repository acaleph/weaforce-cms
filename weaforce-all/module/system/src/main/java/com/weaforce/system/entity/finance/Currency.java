package com.weaforce.system.entity.finance;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;


/**
 * 货币管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "finance_currency")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Currency  implements Serializable {
	private static final long serialVersionUID = 6021287095205915728L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "8", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "CURRENCY_ID", length = 20)
	private Long currencyId;
	@Column(name = "CURRENCY_CODE", length = 10)
	private String currencyCode;
	@Column(name = "CURRENCY_NAME", length = 45)
	private String currencyName;
	@Column(name = "CURRENCY_DESCRIPTION", length = 255)
	private String currencyDescription;
	@Column(name = "CURRENCY_IS_ACTIVE", length = 1)
	private String currencyIsActive;

	public Currency() {
		currencyIsActive = "1";
	}

	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencyDescription() {
		return currencyDescription;
	}

	public void setCurrencyDescription(String currencyDescription) {
		this.currencyDescription = currencyDescription;
	}

	public String getCurrencyIsActive() {
		return currencyIsActive;
	}

	public void setCurrencyIsActive(String currencyIsActive) {
		this.currencyIsActive = currencyIsActive;
	}

}
