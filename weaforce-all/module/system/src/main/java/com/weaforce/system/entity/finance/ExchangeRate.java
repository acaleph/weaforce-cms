package com.weaforce.system.entity.finance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.core.util.DateUtil;

@Entity
@Table(name = "finance_exchange_rate")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class ExchangeRate extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -7662650247108856932L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "54", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "RATE_ID", length = 20)
	private Long rateId;
	// 汇率
	@Column(name = "RATE_VALUE", precision = 10, scale = 4)
	private BigDecimal rateValue;
	// 描述
	@Column(name = "RATE_DESCRIPTION", length = 255)
	private String rateDescription;
	// 是否有效
	@Column(name = "RATE_IS_ACTIVE", length = 20)
	private String rateIsActive;
	// 有效日期
	@Column(name = "RATE_DATE", length = 20)
	private Long rateDate;
	// 原始货币
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "RATE_ORIGIN_CURRENCY_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_ORIGIN_CURRENCY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Currency rateOriginCurrency;
	// 目标货币
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "RATE_TARGET_CURRENCY_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_TARGET_CURRENCY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Currency rateTargetCurrency;

	@Transient
	private String rateName;
	@Transient
	private String rateDateDate;

	public ExchangeRate() {
		rateIsActive = "1";
	}

	public Long getRateId() {
		return rateId;
	}

	public void setRateId(Long rateId) {
		this.rateId = rateId;
	}

	public BigDecimal getRateValue() {
		return rateValue;
	}

	public void setRateValue(BigDecimal rateValue) {
		this.rateValue = rateValue;
	}

	public String getRateDescription() {
		return rateDescription;
	}

	public void setRateDescription(String rateDescription) {
		this.rateDescription = rateDescription;
	}

	public String getRateIsActive() {
		return rateIsActive;
	}

	public void setRateIsActive(String rateIsActive) {
		this.rateIsActive = rateIsActive;
	}

	public Long getRateDate() {
		return rateDate;
	}

	public void setRateDate(Long rateDate) {
		this.rateDate = rateDate;
	}

	public Currency getRateOriginCurrency() {
		return rateOriginCurrency;
	}

	public void setRateOriginCurrency(Currency rateOriginCurrency) {
		this.rateOriginCurrency = rateOriginCurrency;
	}

	public Currency getRateTargetCurrency() {
		return rateTargetCurrency;
	}

	public void setRateTargetCurrency(Currency rateTargetCurrency) {
		this.rateTargetCurrency = rateTargetCurrency;
	}

	public String getRateName() {
		return rateName;
	}

	public void setRateName(String rateName) {
		this.rateName = rateName;
	}

	/**
	 * 以W3C格式显示有效日期
	 * 
	 * @return
	 */
	public String getRateDateDate() {
		if (rateDate != null)
			rateDateDate = DateUtil.defaultFormat(new Date(rateDate));
		return rateDateDate;
	}

	/**
	 * 以UTC格式保存有效日期
	 * 
	 * @param rateDateDate
	 */
	public void setRateDateDate(String rateDateDate) {
		if (StringUtils.isNotEmpty(rateDateDate))
			rateDate = DateUtil.getUTCDate(rateDateDate);
		this.rateDateDate = rateDateDate;
	}

}
