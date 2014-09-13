package com.weaforce.cms.entity;

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

import com.weaforce.core.entity.PrimaryEntity;

/**
 * 文章Meta类型
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_meta")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Proxy(lazy = true)
public class Meta extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -4907116187938928789L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "88", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "META_ID", length = 20)
	private Long metaId;
	// 名称
	@Column(name = "META_NAME", length = 45)
	private String metaName;
	// 键
	@Column(name = "META_KEY", length = 20)
	private String metaKey;
	// 值
	@Column(name = "META_VALUE", length = 180)
	private String metaValue;
	// 活动
	@Column(name = "META_IS_ACTIVE", length = 1)
	private String metaIsActive;
	// 系统
	@Column(name = "META_IS_SYSTEM", length = 1)
	private String metaIsSystem;
	// 描述
	@Column(name = "META_DESCRIPTION", length = 255)
	private String metaDescription;

	public Meta() {
		metaIsActive = "1";
		metaIsSystem = "0";
	}

	public Long getMetaId() {
		return metaId;
	}

	public void setMetaId(Long metaId) {
		this.metaId = metaId;
	}

	public String getMetaName() {
		return metaName;
	}

	public void setMetaName(String metaName) {
		this.metaName = metaName;
	}

	public String getMetaKey() {
		return metaKey;
	}

	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey;
	}

	public String getMetaValue() {
		return metaValue;
	}

	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue;
	}

	public String getMetaIsActive() {
		return metaIsActive;
	}

	public void setMetaIsActive(String metaIsActive) {
		this.metaIsActive = metaIsActive;
	}

	public String getMetaIsSystem() {
		return metaIsSystem;
	}

	public void setMetaIsSystem(String metaIsSystem) {
		this.metaIsSystem = metaIsSystem;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

}
