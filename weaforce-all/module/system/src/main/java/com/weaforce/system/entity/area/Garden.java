package com.weaforce.system.entity.area;

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

import com.weaforce.entity.area.Zone;

@Entity
@Table(name = "area_garden")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Garden implements Serializable {
	private static final long serialVersionUID = 4084003513892732082L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "185", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "GARDEN_ID", length = 20)
	private Long zoneId;
	// 名称
	@Column(name = "GARDEN_NAME", length = 45)
	private String zoneName;
	// 排序
	@Column(name = "GARDEN_ORDER", length = 3)
	private Byte zoneOrder;

	public Garden() {

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


}
