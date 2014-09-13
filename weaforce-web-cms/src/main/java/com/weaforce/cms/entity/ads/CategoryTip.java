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

import com.weaforce.cms.entity.CopyFrom;
import com.weaforce.core.util.DateUtil;

/**
 * 广告小贴士管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_ads_category_tip")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class CategoryTip implements Serializable {
	private static final long serialVersionUID = 5499871519456344184L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "119", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "TIP_ID", length = 20)
	private Long tipId;
	// 标题{$T000T$}
	@Column(name = "TIP_TITLE")
	private String tipTitle;
	// 内容
	@Column(name = "TIP_CONTENT")
	private String tipContent;
	// 审核
	@Column(name = "TIP_IS_AUDIT", length = 1)
	private String tipIsAudit;
	// 创建时间
	@Column(name = "CREATE_TIME", length = 20)
	private Long createTime;
	// 是否Parse
	@Column(name = "TIP_IS_PARSE", length = 1)
	private String tipIsParse;
	// Parse后的WEB服务器URL{$U000U$}
	@Column(name = "TIP_URL", length = 90)
	private String tipUrl;
	// Parse后的物理地址
	@Column(name = "TIP_LOCATION", length = 90)
	private String tipLocation;
	// 广告目录
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "TIP_CATEGORY_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_TIP_CATEGORY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private BizCategory tipCategory;
	// 来源(WEB)
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "TIP_FROM_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_TIP_COPY_FROM_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private CopyFrom tipFrom;

	@Transient
	private String date;

	public CategoryTip() {
		tipIsAudit = "0";
		tipIsParse = "0";
		createTime = System.currentTimeMillis();
	}

	public Long getTipId() {
		return tipId;
	}

	public void setTipId(Long tipId) {
		this.tipId = tipId;
	}

	public String getTipTitle() {
		return tipTitle;
	}

	public void setTipTitle(String tipTitle) {
		this.tipTitle = tipTitle;
	}

	public String getTipContent() {
		return tipContent;
	}

	public void setTipContent(String tipContent) {
		this.tipContent = tipContent;
	}

	public String getTipIsAudit() {
		return tipIsAudit;
	}

	public void setTipIsAudit(String tipIsAudit) {
		this.tipIsAudit = tipIsAudit;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getTipIsParse() {
		return tipIsParse;
	}

	public void setTipIsParse(String tipIsParse) {
		this.tipIsParse = tipIsParse;
	}

	public String getTipUrl() {
		return tipUrl;
	}

	public void setTipUrl(String tipUrl) {
		this.tipUrl = tipUrl;
	}

	public String getTipLocation() {
		return tipLocation;
	}

	public void setTipLocation(String tipLocation) {
		this.tipLocation = tipLocation;
	}

	public BizCategory getTipCategory() {
		return tipCategory;
	}

	public void setTipCategory(BizCategory tipCategory) {
		this.tipCategory = tipCategory;
	}

	public CopyFrom getTipFrom() {
		return tipFrom;
	}

	public void setTipFrom(CopyFrom tipFrom) {
		this.tipFrom = tipFrom;
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
