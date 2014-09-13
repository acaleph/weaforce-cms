package com.weaforce.cms.entity.ads;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.util.DateUtil;

/**
 * 广告打折信息管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_ads_discount")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class AdsDiscount implements Serializable {
	private static final long serialVersionUID = -1292883099203641361L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "117", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "DISCOUNT_ID", length = 20)
	private Long discountId;
	// 标题{$T000T$}
	@Column(name = "DISCOUNT_TITLE")
	private String discountTitle;
	// 有效期
	@Column(name = "DISCOUNT_DATE_FROM", length = 20)
	private Long discountDateFrom;
	// 有效期
	@Column(name = "DISCOUNT_DATE_TO", length = 20)
	private Long discountDateTo;
	// 简介{$I000I$}
	@Column(name = "DISCOUNT_INTRO")
	private String discountIntro;
	// 内容
	@Column(name = "DISCOUNT_CONTENT")
	private String discountContent;
	// 审核
	@Column(name = "DISCOUNT_IS_AUDIT", length = 1)
	private String discountIsAudit;
	// 访问链接{$U000U$}
	@Column(name = "DISCOUNT_URL", length = 45)
	private String discountUrl;
	// 是否parse
	@Column(name = "DISCOUNT_IS_PARSE", length = 1)
	private String discountIsParse;
	// Parse后的物理地址
	@Column(name = "DISCOUNT_LOCATION", length = 90)
	private String discountLocation;
	// 创建时间
	@Column(name = "CREATE_TIME", length = 20)
	private Long createTime;
	// 广告
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "DISCOUNT_ADS_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_DISCOUNT_ADS_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Ads discountAds;

	@Transient
	private String discountDateFromDate;
	@Transient
	private String discountDateToDate;
	@Transient
	private String date;

	public AdsDiscount() {
		discountIsAudit = "0";
		discountIsParse = "0";
		createTime = System.currentTimeMillis();
	}

	public Long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	public String getDiscountTitle() {
		return discountTitle;
	}

	public void setDiscountTitle(String discountTitle) {
		this.discountTitle = discountTitle;
	}

	public Long getDiscountDateFrom() {
		return discountDateFrom;
	}

	public void setDiscountDateFrom(Long discountDateFrom) {
		this.discountDateFrom = discountDateFrom;
	}

	public Long getDiscountDateTo() {
		return discountDateTo;
	}

	public void setDiscountDateTo(Long discountDateTo) {
		this.discountDateTo = discountDateTo;
	}

	/**
	 * 以W3C格式显示日期
	 * 
	 * @return
	 */
	public String getDiscountDateFromDate() {
		if (discountDateFrom != null)
			discountDateFromDate = DateUtil.defaultFormat(new Date(
					discountDateFrom));
		return discountDateFromDate;
	}

	/**
	 * 以UTC格式保存日期
	 * 
	 * @param discountDateFrom
	 */
	public void setDiscountDateFromDate(String discountDateFromDate) {
		if (discountDateFromDate != null)
			discountDateFrom = DateUtil.getUTCDate(discountDateFromDate);
		this.discountDateFromDate = discountDateFromDate;
	}

	/**
	 * 以W3C格式显示日期
	 * 
	 * @return
	 */
	public String getDiscountDateToDate() {
		if (discountDateTo != null)
			discountDateToDate = DateUtil
					.defaultFormat(new Date(discountDateTo));
		return discountDateToDate;
	}

	/**
	 * 以UTC格式保存日期
	 * 
	 * @param discountDateFrom
	 */
	public void setDiscountDateToDate(String discountDateToDate) {
		if (discountDateToDate != null)
			discountDateTo = DateUtil.getUTCDate(discountDateToDate);
		this.discountDateToDate = discountDateToDate;
	}

	public String getDiscountIntro() {
		return discountIntro;
	}

	public void setDiscountIntro(String discountIntro) {
		this.discountIntro = discountIntro;
	}

	public String getDiscountContent() {
		return discountContent;
	}

	public void setDiscountContent(String discountContent) {
		this.discountContent = discountContent;
	}

	public String getDiscountIsAudit() {
		return discountIsAudit;
	}

	public void setDiscountIsAudit(String discountIsAudit) {
		this.discountIsAudit = discountIsAudit;
	}

	public String getDiscountUrl() {
		return discountUrl;
	}

	public void setDiscountUrl(String discountUrl) {
		this.discountUrl = discountUrl;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getDiscountIsParse() {
		return discountIsParse;
	}

	public void setDiscountIsParse(String discountIsParse) {
		this.discountIsParse = discountIsParse;
	}

	public String getDiscountLocation() {
		return discountLocation;
	}

	public void setDiscountLocation(String discountLocation) {
		this.discountLocation = discountLocation;
	}

	public Ads getDiscountAds() {
		return discountAds;
	}

	public void setDiscountAds(Ads discountAds) {
		this.discountAds = discountAds;
	}

	/**
	 * 返回W3C格式日期,如2008-08-08
	 * 
	 * @return
	 */
	public String getDate() {
		if (createTime == null || createTime == 0) {
			date = DateUtil.defaultFormat(new Date(System.currentTimeMillis()));
		} else {
			date = DateUtil.defaultFormat(new Date(createTime));
		}
		return date;
	}
}
