package com.weaforce.entity.area;

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
 * 省份字典域
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "area_province")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Province implements Serializable {
	private static final long serialVersionUID = -25226203716252140L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "58", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "PROVINCE_ID", length = 20)
	private Long provinceId;
	@Column(name = "PROVINCE_CODE", length = 10)
	private String provinceCode;
	@Column(name = "PROVINCE_NAME_EN", length = 45)
	private String provinceNameEn;
	@Column(name = "PROVINCE_NAME_CN", length = 45)
	private String provinceNameCn;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROVINCE_COUNTRY_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_PROVINCE_COUNTRY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Country provinceCountry;

	public Province() {

	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceNameEn() {
		return provinceNameEn;
	}

	public void setProvinceNameEn(String provinceNameEn) {
		this.provinceNameEn = provinceNameEn;
	}

	public String getProvinceNameCn() {
		return provinceNameCn;
	}

	public void setProvinceNameCn(String provinceNameCn) {
		this.provinceNameCn = provinceNameCn;
	}

	public Country getProvinceCountry() {
		return provinceCountry;
	}

	public void setProvinceCountry(Country provinceCountry) {
		this.provinceCountry = provinceCountry;
	}

}
