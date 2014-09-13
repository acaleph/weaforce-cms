package com.weaforce.entity.ccm;

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

import com.weaforce.entity.PrimaryEntity;
import com.weaforce.core.util.DateUtil;
import com.weaforce.entity.ccm.pm.Engineering;
import com.weaforce.entity.finance.CostType;
import com.weaforce.entity.finance.Currency;
import com.weaforce.entity.finance.ExchangeRate;

@Entity
@Table(name = "co_cost")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Cost extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -2438605849971695367L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "43", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "COST_ID", length = 20)
	private Long costId;
	// 标题
	@Column(name = "COST_TITLE", length = 180)
	private String costTitle;
	// 描述
	@Column(name = "COST_DESCRIPTION", length = 255)
	private String costDescription;
	// 发票
	@Column(name = "COST_INVOICE", length = 15)
	private String costInvoice;
	// 发生日期
	@Column(name = "COST_DATE", length = 20)
	private Long costDate;
	// 计划费用
	@Column(name = "COST_ESTIMATE", precision = 16, scale = 4)
	private BigDecimal costEstimate;
	// 实际费用
	@Column(name = "COST_REAL", precision = 16, scale = 4)
	private BigDecimal costReal;
	// 相关工程
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "COST_ENGINEERING_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_COST_ENGINEERING_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Engineering costEngineering;
	// 交易货币
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "COST_CURRENCY_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_COST_CURRENCY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Currency costCurrency;
	// 汇率
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "COST_EXCHANGE_RATE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_COST_EXCHANGE_RATE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private ExchangeRate costExchangeRate;
	// 费用类型
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "COST_TYPE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_COST_TYPE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private CostType costType;

	@Transient
	private String costDateDate;

	public Cost() {

	}

	public Long getCostId() {
		return costId;
	}

	public void setCostId(Long costId) {
		this.costId = costId;
	}

	public String getCostTitle() {
		return costTitle;
	}

	public void setCostTitle(String costTitle) {
		this.costTitle = costTitle;
	}

	public String getCostDescription() {
		return costDescription;
	}

	public void setCostDescription(String costDescription) {
		this.costDescription = costDescription;
	}

	public String getCostInvoice() {
		return costInvoice;
	}

	public void setCostInvoice(String costInvoice) {
		this.costInvoice = costInvoice;
	}

	public Long getCostDate() {
		return costDate;
	}

	public void setCostDate(Long costDate) {
		this.costDate = costDate;
	}

	public BigDecimal getCostEstimate() {
		return costEstimate;
	}

	public void setCostEstimate(BigDecimal costEstimate) {
		this.costEstimate = costEstimate;
	}

	public BigDecimal getCostReal() {
		return costReal;
	}

	public void setCostReal(BigDecimal costReal) {
		this.costReal = costReal;
	}

	public Engineering getCostEngineering() {
		return costEngineering;
	}

	public void setCostEngineering(Engineering costEngineering) {
		this.costEngineering = costEngineering;
	}

	public Currency getCostCurrency() {
		return costCurrency;
	}

	public void setCostCurrency(Currency costCurrency) {
		this.costCurrency = costCurrency;
	}

	public ExchangeRate getCostExchangeRate() {
		return costExchangeRate;
	}

	public void setCostExchangeRate(ExchangeRate costExchangeRate) {
		this.costExchangeRate = costExchangeRate;
	}

	public CostType getCostType() {
		return costType;
	}

	public void setCostType(CostType costType) {
		this.costType = costType;
	}

	/**
	 * 把UTC日期转换为W3C日期显示
	 * 
	 * @return
	 */
	public String getCostDateDate() {
		if (costDateDate == null)
			costDateDate = DateUtil.defaultFormat(new Date(costDate));
		return costDateDate;
	}

	/**
	 * 把W3C日期转换为UTC日期保存
	 * 
	 * @param costDateDate
	 */
	public void setCostDateDate(String costDateDate) {
		if (StringUtils.isNotEmpty(costDateDate))
			costDate = DateUtil.getUTCDate(costDateDate);
		this.costDateDate = costDateDate;
	}

}
