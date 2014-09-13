package com.weaforce.entity.area;

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
 * 国家字典域
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "area_country")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Country implements Serializable {
	private static final long serialVersionUID = -1909909775135599981L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "57", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "COUNTRY_ID", length = 20)
	private Long countryId;
	@Column(name = "COUNTRY_CODE", length = 10)
	private String countryCode;
	@Column(name = "COUNTRY_NAME_CN", length = 45)
	private String countryNameCn;
	@Column(name = "COUNTRY_NAME_EN", length = 45)
	private String countryNameEn;
	@Column(name = "COUNTRY_DOMAIN", length = 2)
	private String countryDomain;
	@Column(name = "COUNTRY_PHONE", length = 10)
	private String countryPhone;
	@Column(name = "COUNTRY_ZONE", length = 2)
	private String countryZone;

	public Country() {

	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryNameCn() {
		return countryNameCn;
	}

	public void setCountryNameCn(String countryNameCn) {
		this.countryNameCn = countryNameCn;
	}

	public String getCountryNameEn() {
		return countryNameEn;
	}

	public void setCountryNameEn(String countryNameEn) {
		this.countryNameEn = countryNameEn;
	}

	public String getCountryDomain() {
		return countryDomain;
	}

	public void setCountryDomain(String countryDomain) {
		this.countryDomain = countryDomain;
	}

	public String getCountryPhone() {
		return countryPhone;
	}

	public void setCountryPhone(String countryPhone) {
		this.countryPhone = countryPhone;
	}

	public String getCountryZone() {
		return countryZone;
	}

	public void setCountryZone(String countryZone) {
		this.countryZone = countryZone;
	}

}
