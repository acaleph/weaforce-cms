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
 * 城市字典域
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "area_city")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class City implements Serializable {
	private static final long serialVersionUID = -4468853907053665932L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "59", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "CITY_ID", length = 20)
	private Long cityId;
	// City code
	@Column(name = "CITY_CODE", length = 10)
	private String cityCode;
	// English name
	@Column(name = "CITY_NAME_EN", length = 45)
	private String cityNameEn;
	// Chinese name
	@Column(name = "CITY_NAME_CN", length = 45)
	private String cityNameCn;
	// Province
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY_PROVINCE_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_CITY_PROVINCE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Province cityProvince;

	public City() {

	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityNameEn() {
		return cityNameEn;
	}

	public void setCityNameEn(String cityNameEn) {
		this.cityNameEn = cityNameEn;
	}

	public String getCityNameCn() {
		return cityNameCn;
	}

	public void setCityNameCn(String cityNameCn) {
		this.cityNameCn = cityNameCn;
	}

	public Province getCityProvince() {
		return cityProvince;
	}

	public void setCityProvince(Province cityProvince) {
		this.cityProvince = cityProvince;
	}

}
