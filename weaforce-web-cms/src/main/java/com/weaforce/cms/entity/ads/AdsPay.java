package com.weaforce.cms.entity.ads;

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

/**
 * 付款明细管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_ads_pay")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class AdsPay extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 2475679195110741726L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "157", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "PAY_ID", length = 20)
	private Long payId;
	// 标题
	@Column(name = "PAY_TITLE", length = 180)
	private String payTitle;
	// 内容
	@Column(name = "PAY_CONTENT", length = 255)
	private String payContent;
	// 日期
	@Column(name = "PAY_DATE", length = 20)
	private Long payDate;
	// 当前费用金额
	@Column(name = "PAY_PAY", precision = 10, scale = 4)
	private BigDecimal payPay;
	// 广告商家
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PAY_ADS_ID", nullable = false)
	@org.hibernate.annotations.ForeignKey(name = "FK_PAY_ADS_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Ads payAds;

	@Transient
	private String payDateDate;

	public AdsPay() {
		payPay = BigDecimal.ZERO;
	}

	public Long getPayId() {
		return payId;
	}

	public void setPayId(Long payId) {
		this.payId = payId;
	}

	public String getPayTitle() {
		return payTitle;
	}

	public void setPayTitle(String payTitle) {
		this.payTitle = payTitle;
	}

	public String getPayContent() {
		return payContent;
	}

	public void setPayContent(String payContent) {
		this.payContent = payContent;
	}

	public Long getPayDate() {
		return payDate;
	}

	public void setPayDate(Long payDate) {
		this.payDate = payDate;
	}

	public BigDecimal getPayPay() {
		return payPay;
	}

	public void setPayPay(BigDecimal payPay) {
		this.payPay = payPay;
	}

	public Ads getPayAds() {
		return payAds;
	}

	public void setPayAds(Ads payAds) {
		this.payAds = payAds;
	}

	

	/**
	 * 以W3C格式显示日期
	 * 
	 * @return
	 */
	public String getPayDateDate() {
		if (payDate != null)
			payDateDate = DateUtil.defaultFormat(new Date(payDate));
		return payDateDate;
	}

	/**
	 * 以UTC格式保存日期
	 * 
	 * @param payDateDate
	 */
	public void setPayDateDate(String payDateDate) {
		if (StringUtils.isNotEmpty(payDateDate))
			payDate = DateUtil.getUTCDate(payDateDate);
		this.payDateDate = payDateDate;
	}
}
