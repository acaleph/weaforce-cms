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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.util.DateUtil;

/**
 * 团购交易管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_ads_deal")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class Deal implements Serializable {
	private static final long serialVersionUID = 752600245735236361L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "161", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "DEAL_ID", length = 20)
	private Long dealId;
	// 日期起始
	@Column(name = "DEAL_DATE_START", length = 20)
	private Long dealDateStart;
	// 日期终止
	@Column(name = "DEAL_DATE_END", length = 20)
	private Long dealDateEnd;
	// 原价
	@Column(name = "DEAL_PRICE", precision = 8, scale = 2)
	private BigDecimal dealPrice;
	// 打折
	@Column(name = "DEAL_DISCOUNT", precision = 2, scale = 1)
	private BigDecimal dealDiscount;
	// 说明
	@Column(name = "DEAL_CONTENT")
	private String dealContent;
	// 有效交易次数
	@Column(name = "DEAL_COUNT", length = 10)
	private Integer dealCount;
	// 创建时间
	@Column(name = "CREATE_TIME", length = 20)
	private Long createTime;
	// 产品
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEAL_PRODUCT_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_CMS_ADS_DEAL_PRODUCT_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Product dealProduct;

	@Transient
	private BigDecimal dealPriceDiscount;
	@Transient
	private String dealDateStartDate;
	@Transient
	private String dealDateEndDate;
	@Transient
	private String createTimeDate;

	public Deal() {
		dealPrice = BigDecimal.ZERO;
		// 默认9折
		dealDiscount = BigDecimal.valueOf(9);
		dealCount = 0;
		createTime = System.currentTimeMillis();
	}

	public Long getDealId() {
		return dealId;
	}

	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}

	public Long getDealDateStart() {
		return dealDateStart;
	}

	public void setDealDateStart(Long dealDateStart) {
		this.dealDateStart = dealDateStart;
	}

	public Long getDealDateEnd() {
		return dealDateEnd;
	}

	public void setDealDateEnd(Long dealDateEnd) {
		this.dealDateEnd = dealDateEnd;
	}

	public BigDecimal getDealPrice() {
		return dealPrice;
	}

	public void setDealPrice(BigDecimal dealPrice) {
		this.dealPrice = dealPrice;
	}

	public BigDecimal getDealDiscount() {
		return dealDiscount;
	}

	public void setDealDiscount(BigDecimal dealDiscount) {
		this.dealDiscount = dealDiscount;
	}

	public String getDealContent() {
		return dealContent;
	}

	public void setDealContent(String dealContent) {
		this.dealContent = dealContent;
	}

	public Integer getDealCount() {
		return dealCount;
	}

	public void setDealCount(Integer dealCount) {
		this.dealCount = dealCount;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Product getDealProduct() {
		return dealProduct;
	}

	public void setDealProduct(Product dealProduct) {
		this.dealProduct = dealProduct;
	}

	/**
	 * 计算打折后价格
	 * 
	 * @return
	 */
	public BigDecimal getDealPriceDiscount() {
		dealPriceDiscount = dealPrice.multiply(dealDiscount).divide(
				BigDecimal.valueOf(10));
		return dealPriceDiscount;
	}

	public void setDealPriceDiscount(BigDecimal dealPriceDiscount) {
		this.dealPriceDiscount = dealPriceDiscount;
	}

	/**
	 * 以W3C格式显示日期
	 * 
	 * @return
	 */
	public String getDealDateStartDate() {
		if (dealDateStart != null)
			dealDateStartDate = DateUtil.defaultFormat(new Date(dealDateStart));
		return dealDateStartDate;
	}

	/**
	 * 以UTC格式保存日期
	 * 
	 * @param discountDateFrom
	 */
	public void setDealDateStartDate(String dealDateStartDate) {
		if (dealDateStartDate != null)
			dealDateStart = DateUtil.getUTCDate(dealDateStartDate);
		this.dealDateStartDate = dealDateStartDate;
	}

	/**
	 * 以W3C格式显示日期
	 * 
	 * @return
	 */
	public String getDealDateEndDate() {
		if (dealDateEnd != null)
			dealDateEndDate = DateUtil.defaultFormat(new Date(dealDateEnd));
		return dealDateEndDate;
	}

	public void setDealDateEndDate(String dealDateEndDate) {
		if (dealDateEndDate != null)
			dealDateEnd = DateUtil.getUTCDate(dealDateEndDate);
		this.dealDateEndDate = dealDateEndDate;
	}

	/**
	 * 完整显示用户订货时间
	 * 
	 * @return
	 */
	public String getCreateTimeDate() {
		if (createTime != null)
			createTimeDate = DateUtil.completeFormat(new Date(createTime));
		return createTimeDate;
	}

	public void setCreateTimeDate(String createTimeDate) {
		this.createTimeDate = createTimeDate;
	}

}
