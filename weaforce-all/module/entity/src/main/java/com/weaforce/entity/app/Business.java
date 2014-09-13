package com.weaforce.entity.app;

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

@Entity
@Table(name = "admin_business")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Business implements Serializable {

	private static final long serialVersionUID = -6967803950669219132L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "27", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "BUSINESS_ID", length = 20)
	private Long businessId;
	@Column(name = "BUSINESS_NAME_EN", length = 45)
	private String businessNameEn;
	@Column(name = "BUSINESS_NAME_CN", length = 90)
	private String businessNameCn;
	@Column(name = "BUSINESS_PREFIX", length = 10)
	private String businessNamePrefix;
	@Column(name = "BUSINESS_DESCRIPTION", length = 255)
	private String businessDescription;
	@Column(name = "BUSINESS_IS_RESOURCE", length = 1)
	private String businessIsResource;

	public Business() {
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getBusinessNameEn() {
		return businessNameEn;
	}

	public void setBusinessNameEn(String businessNameEn) {
		this.businessNameEn = businessNameEn;
	}

	public String getBusinessNameCn() {
		return businessNameCn;
	}

	public void setBusinessNameCn(String businessNameCn) {
		this.businessNameCn = businessNameCn;
	}

	public String getBusinessNamePrefix() {
		return businessNamePrefix;
	}

	public void setBusinessNamePrefix(String businessNamePrefix) {
		this.businessNamePrefix = businessNamePrefix;
	}

	public String getBusinessDescription() {
		return businessDescription;
	}

	public void setBusinessDescription(String businessDescription) {
		this.businessDescription = businessDescription;
	}

	public String getBusinessIsResource() {
		return businessIsResource;
	}

	public void setBusinessIsResource(String businessIsResource) {
		this.businessIsResource = businessIsResource;
	}

}
