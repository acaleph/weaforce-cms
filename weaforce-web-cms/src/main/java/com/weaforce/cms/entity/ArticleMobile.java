package com.weaforce.cms.entity;

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

@Entity
@Table(name = "cms_article_mobile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Proxy(lazy = true)
public class ArticleMobile extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 1741333119900344758L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "188", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "MOBILE_ID", length = 20)
	private Long mobileId;
	// Title
	@Column(name = "MOBILE_TITLE", length = 180)
	private String mobileTitle;
	// Image URL
	@Column(name = "MOBILE_IMAGE_URL", length = 90)
	private String mobileImageUrl;
	// Image URL resized
	@Column(name = "MOBILE_IMAGE_URL_RESIZE", length = 90)
	private String mobileImageUrlResize;
	// Image physical full path
	@Column(name = "MOBILE_IMAGE_FILE", length = 90)
	private String mobileImageFile;
	// Image physical full path
	@Column(name = "MOBILE_IMAGE_FILE_RESIZE", length = 90)
	private String mobileImageFileResize;
	// Mobile article URL
	@Column(name = "MOBILE_URL", length = 90)
	private String mobileUrl;
	// Mobile article physical full path
	@Column(name = "MOBILE_LOCATION", length = 90)
	private String mobileLocation;
	// Mobile article content
	@Column(name = "MOBILE_CONTENT")
	private String mobileContents;
	// Category
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "MOBILE_CATEGORY_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_MOBILE_ARTICLE_CATEGORY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Category mobileCategory;

	public ArticleMobile() {

	}

	public Long getMobileId() {
		return mobileId;
	}

	public void setMobileId(Long mobileId) {
		this.mobileId = mobileId;
	}

	public String getMobileTitle() {
		return mobileTitle;
	}

	public void setMobileTitle(String mobileTitle) {
		this.mobileTitle = mobileTitle;
	}

	public String getMobileImageUrl() {
		return mobileImageUrl;
	}

	public void setMobileImageUrl(String mobileImageUrl) {
		this.mobileImageUrl = mobileImageUrl;
	}

	public String getMobileImageUrlResize() {
		return mobileImageUrlResize;
	}

	public void setMobileImageUrlResize(String mobileImageUrlResize) {
		this.mobileImageUrlResize = mobileImageUrlResize;
	}

	public String getMobileImageFile() {
		return mobileImageFile;
	}

	public void setMobileImageFile(String mobileImageFile) {
		this.mobileImageFile = mobileImageFile;
	}

	public String getMobileImageFileResize() {
		return mobileImageFileResize;
	}

	public void setMobileImageFileResize(String mobileImageFileResize) {
		this.mobileImageFileResize = mobileImageFileResize;
	}

	public String getMobileUrl() {
		return mobileUrl;
	}

	public void setMobileUrl(String mobileUrl) {
		this.mobileUrl = mobileUrl;
	}

	public String getMobileLocation() {
		return mobileLocation;
	}

	public void setMobileLocation(String mobileLocation) {
		this.mobileLocation = mobileLocation;
	}

	public String getMobileContents() {
		return mobileContents;
	}

	public void setMobileContents(String mobileContents) {
		this.mobileContents = mobileContents;
	}

	public Category getMobileCategory() {
		return mobileCategory;
	}

	public void setMobileCategory(Category mobileCategory) {
		this.mobileCategory = mobileCategory;
	}
}
