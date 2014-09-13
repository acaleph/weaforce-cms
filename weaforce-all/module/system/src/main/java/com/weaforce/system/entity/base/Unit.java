package com.weaforce.system.entity.base;

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
 * <h3>单位字典域</h3>
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "base_unit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class Unit  implements Serializable {
	private static final long serialVersionUID = -1218256141084252160L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "75", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "UNIT_ID", length = 20)
	private Long unitId;
	@Column(name = "UNIT_CODE", length = 10)
	private String unitCode;
	@Column(name = "UNIT_NAME", length = 45)
	private String unitName;
	@Column(name = "UNIT_DESCRIPTION", length = 90)
	private String unitDescription;
	@Column(name = "UNIT_IS_ACTIVE", length = 1)
	private String unitIsActive;

	public Unit() {
		unitIsActive = "1";
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitDescription() {
		return unitDescription;
	}

	public void setUnitDescription(String unitDescription) {
		this.unitDescription = unitDescription;
	}

	public String getUnitIsActive() {
		return unitIsActive;
	}

	public void setUnitIsActive(String unitIsActive) {
		this.unitIsActive = unitIsActive;
	}

}
