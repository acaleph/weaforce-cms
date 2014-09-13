package com.weaforce.entity.finance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.core.util.DateUtil;

/**
 * 帐期管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "finance_period")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Period extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -1679505651660832138L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "48", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "PERIOD_ID", length = 20)
	private Long periodId;
	// 开始
	@Column(name = "PERIOD_START", length = 20)
	private Long periodStart;
	// 结束
	@Column(name = "PERIOD_END", length = 20)
	private Long periodEnd;
	// 名称
	@Column(name = "PERIOD_NAME", length = 45)
	private String periodName;
	// 财年：不一定与当前年度相同
	@Column(name = "PERIOD_YEAR", length = 10)
	private Integer periodYear;
	// 排序
	@Column(name = "PERIOD_ORDER", length = 3)
	private byte periodOrder;
	// 活动
	@Column(name = "PERIOD_IS_ACTIVE", length = 1)
	private String periodIsActive;
	// 父账期
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "PERIOD_FID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_PERIOD_FID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Period periodParent;

	@Transient
	private String periodStartDate;
	@Transient
	private String periodEndDate;

	public Period() {
		periodIsActive = "1";
	}

	public Long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}

	public Long getPeriodStart() {
		return periodStart;
	}

	public void setPeriodStart(Long periodStart) {
		this.periodStart = periodStart;
	}

	public Long getPeriodEnd() {
		return periodEnd;
	}

	public void setPeriodEnd(Long periodEnd) {
		this.periodEnd = periodEnd;
	}

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public Integer getPeriodYear() {
		return periodYear;
	}

	public void setPeriodYear(Integer periodYear) {
		this.periodYear = periodYear;
	}

	public byte getPeriodOrder() {
		return periodOrder;
	}

	public void setPeriodOrder(byte periodOrder) {
		this.periodOrder = periodOrder;
	}

	public String getPeriodIsActive() {
		return periodIsActive;
	}

	public void setPeriodIsActive(String periodIsActive) {
		this.periodIsActive = periodIsActive;
	}

	public Period getPeriodParent() {
		return periodParent;
	}

	public void setPeriodParent(Period periodParent) {
		this.periodParent = periodParent;
	}

	/**
	 * 以W3C格式显示起始日期
	 * 
	 * @return
	 */
	public String getPeriodStartDate() {
		if (periodStart != null)
			periodStartDate = DateUtil.defaultFormat(new Date(periodStart));
		return periodStartDate;
	}

	/**
	 * 以UTC格式保存起始日期
	 * 
	 * @param periodStartDate
	 */
	public void setPeriodStartDate(String periodStartDate) {
		if (StringUtils.isNotEmpty(periodStartDate))
			periodStart = DateUtil.getUTCDate(periodStartDate);
		this.periodStartDate = periodStartDate;
	}

	/**
	 * 以W3C格式显示结束日期
	 * 
	 * @return
	 */
	public String getPeriodEndDate() {
		if (periodEnd != null)
			periodEndDate = DateUtil.defaultFormat(new Date(periodEnd));
		return periodEndDate;
	}

	/**
	 * 以UTC格式保存结束日期
	 * 
	 * @param periodEndDate
	 */
	public void setPeriodEndDate(String periodEndDate) {
		if (StringUtils.isNotEmpty(periodEndDate))
			periodEnd = DateUtil.getUTCDate(periodEndDate);
		this.periodEndDate = periodEndDate;
	}

}
