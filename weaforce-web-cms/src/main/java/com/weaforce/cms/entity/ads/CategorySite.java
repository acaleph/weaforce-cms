package com.weaforce.cms.entity.ads;

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

/**
 * 网上邻居管理类:栏目相关网站管理类，在打折区做为友好链接用途
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_ads_category_site")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class CategorySite implements Serializable {
	private static final long serialVersionUID = 2665623586507243343L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "123", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "SITE_ID", length = 20)
	private Long siteId;
	// 名称
	@Column(name = "SITE_NAME", length = 45)
	private String siteName;
	// 名称
	@Column(name = "SITE_URL", length = 90)
	private String siteUrl;
	// 所属栏目
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "SITE_CATEGORY_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_SITE_CATEGORY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private BizCategory siteCategory;

	public CategorySite() {

	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public BizCategory getSiteCategory() {
		return siteCategory;
	}

	public void setSiteCategory(BizCategory siteCategory) {
		this.siteCategory = siteCategory;
	}

}
