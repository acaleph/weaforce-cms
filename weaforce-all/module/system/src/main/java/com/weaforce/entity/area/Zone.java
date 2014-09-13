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
 * 区字典域
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "area_zone")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Zone implements Serializable {
	private static final long serialVersionUID = 7855594975073183989L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "113", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "ZONE_ID", length = 20)
	private Long zoneId;
	// 名称
	@Column(name = "ZONE_NAME", length = 45)
	private String zoneName;
	// 排序
	@Column(name="ZONE_ORDER",length=3)
	private Byte zoneOrder;
	// 所属城市
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ZONE_CITY_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_ZONE_CITY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private City zoneCity;

	public Zone() {

	}

	public Long getZoneId() {
		return zoneId;
	}

	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public Byte getZoneOrder() {
		return zoneOrder;
	}

	public void setZoneOrder(Byte zoneOrder) {
		this.zoneOrder = zoneOrder;
	}

	public City getZoneCity() {
		return zoneCity;
	}

	public void setZoneCity(City zoneCity) {
		this.zoneCity = zoneCity;
	}

}
