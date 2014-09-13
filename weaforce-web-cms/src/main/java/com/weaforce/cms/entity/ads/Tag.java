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
 * 广告标签类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_ads_category_tag")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class Tag implements Serializable {
	private static final long serialVersionUID = -8353961074802183362L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "115", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "TAG_ID", length = 20)
	private Long tagId;
	// 名称
	@Column(name = "TAG_NAME", length = 45)
	private String tagName;
	// 值
	@Column(name = "TAG_TAG", length = 45)
	private String tagTag;
	// 排序
	@Column(name = "TAG_ORDER", length = 3)
	private String tagOrder;
	//描述
	@Column(name="TAG_DESCRIPTION",length=255)
	private String tagDescription;
	// 广告标签目录
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "TAG_CATEGORY_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_TAG_CATEGORY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private BizCategory tagCategory;

	public Tag() {

	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagTag() {
		return tagTag;
	}

	public void setTagTag(String tagTag) {
		this.tagTag = tagTag;
	}

	public String getTagOrder() {
		return tagOrder;
	}

	public void setTagOrder(String tagOrder) {
		this.tagOrder = tagOrder;
	}

	public BizCategory getTagCategory() {
		return tagCategory;
	}

	public void setTagCategory(BizCategory tagCategory) {
		this.tagCategory = tagCategory;
	}

	public String getTagDescription() {
		return tagDescription;
	}

	public void setTagDescription(String tagDescription) {
		this.tagDescription = tagDescription;
	}

}
