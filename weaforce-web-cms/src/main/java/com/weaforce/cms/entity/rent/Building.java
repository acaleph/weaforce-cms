package com.weaforce.cms.entity.rent;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.cms.entity.ads.Ads;
import com.weaforce.entity.area.Zone;

/**
 * 建筑管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_rent_building")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class Building implements Serializable {
	private static final long serialVersionUID = -6901088357624071146L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "131", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "BUILDING_ID", length = 20)
	private Long buildingId;
	// 名称
	@Column(name = "BUILDING_NAME", length = 45)
	private String buildingName;
	// 所在区（镇）
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "BUILDING_ZONE_ID", nullable = false)
	@org.hibernate.annotations.ForeignKey(name = "FK_BUILDING_ZONE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Zone buildingZone;
	// 广告商家
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinColumn(name = "BUILDING_ADS_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_BUILDING_ADS_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Ads buildingAds;
	// 房间
	@OneToMany(mappedBy = "houseBuilding", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.LAZY)
	@org.hibernate.annotations.OrderBy(clause = "LAST_UPDATE_TIME asc")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<House> buildingHouse = new HashSet<House>();

	public Building() {

	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Zone getBuildingZone() {
		return buildingZone;
	}

	public void setBuildingZone(Zone buildingZone) {
		this.buildingZone = buildingZone;
	}

	public Ads getBuildingAds() {
		return buildingAds;
	}

	public void setBuildingAds(Ads buildingAds) {
		this.buildingAds = buildingAds;
	}

	public Set<House> getBuildingHouse() {
		return buildingHouse;
	}

	public void setBuildingHouse(Set<House> buildingHouse) {
		this.buildingHouse = buildingHouse;
	}
}
