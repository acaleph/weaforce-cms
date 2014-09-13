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
@Table(name = "cms_category_mobile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Proxy(lazy = true)
public class CategoryMobile extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -8177616310273263107L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "187", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "CATEGORY_ID", length = 20)
	private Long categoryId;
	// Parse file name
	@Column(name = "CATEGORY_PARSE_NAME", length = 20)
	private String categoryParseName;
	// Parse file extension name
	@Column(name = "CATEGORY_PARSE_NAME_EXT", length = 5)
	private String categoryParseNameExt;
	// Name
	@Column(name = "CATEGORY_NAME", length = 45)
	private String categoryName;
	// URL
	@Column(name = "CATEGORY_URL", length = 45)
	private String categoryUrl;
	// Order
	@Column(name = "CATEGORY_ORDER", length = 3)
	private Byte categoryOrder;
	// Cell template content
	@Column(name = "CATEGORY_CELL_TEMPLATE", length = 500)
	private String categoryCellTemplate;
	// Channel
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_CHANNEL_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_MOBILE_CATEGORY_CHANNEL_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private ChannelMobile categoryChannel;
	// Template of category
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_TEMPLATE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_MOBILE_CATEGORY_TEMPLATE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private TemplateMobile categoryTemplate;
	// Template or article
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ARTICLE_TEMPLATE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_MOBILE_CATEGORY_ARTICLE_TEMPLATE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private TemplateMobile categoryArticleTemplate;

	public CategoryMobile() {

	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryParseName() {
		return categoryParseName;
	}

	public void setCategoryParseName(String categoryParseName) {
		this.categoryParseName = categoryParseName;
	}

	public String getCategoryParseNameExt() {
		return categoryParseNameExt;
	}

	public void setCategoryParseNameExt(String categoryParseNameExt) {
		this.categoryParseNameExt = categoryParseNameExt;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryUrl() {
		return categoryUrl;
	}

	public void setCategoryUrl(String categoryUrl) {
		this.categoryUrl = categoryUrl;
	}

	public Byte getCategoryOrder() {
		return categoryOrder;
	}

	public void setCategoryOrder(Byte categoryOrder) {
		this.categoryOrder = categoryOrder;
	}

	public String getCategoryCellTemplate() {
		return categoryCellTemplate;
	}

	public void setCategoryCellTemplate(String categoryCellTemplate) {
		this.categoryCellTemplate = categoryCellTemplate;
	}

	public ChannelMobile getCategoryChannel() {
		return categoryChannel;
	}

	public void setCategoryChannel(ChannelMobile categoryChannel) {
		this.categoryChannel = categoryChannel;
	}

	public TemplateMobile getCategoryTemplate() {
		return categoryTemplate;
	}

	public void setCategoryTemplate(TemplateMobile categoryTemplate) {
		this.categoryTemplate = categoryTemplate;
	}

	public TemplateMobile getCategoryArticleTemplate() {
		return categoryArticleTemplate;
	}

	public void setCategoryArticleTemplate(
			TemplateMobile categoryArticleTemplate) {
		this.categoryArticleTemplate = categoryArticleTemplate;
	}

}
