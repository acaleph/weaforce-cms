package com.weaforce.cms.entity.rent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

/**
 * 月租标签类，作为查询检索条件
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_rent_house_tag")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class RentTag implements Serializable {
	private static final long serialVersionUID = 896832166019712552L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "133", allocationSize = 1)
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

	public RentTag() {

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

}
