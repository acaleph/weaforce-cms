package com.weaforce.system.entity.organ;

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

@Entity
@Table(name = "organ_department")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Department extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -8605733404507920506L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "7", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "DEPARTMENT_ID", length = 20)
	private Long departmentId;
	@Column(name = "DEPARTMENT_CODE", length = 10)
	private String departmentCode;
	@Column(name = "DEPARTMENT_NAME", length = 45)
	private String departmentName;
	@Column(name = "DEPARTMENT_DESCRIPTION", length = 255)
	private String departmentDescription;
	@Column(name = "DEPARTMENT_LOCATION", length = 180)
	private String departmentLocation;
	@Column(name = "DEPARTMENT_IS_ACTIVE", length = 1)
	private String departmentIsActive;

	public Department() {
		departmentIsActive = "1";
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentDescription() {
		return departmentDescription;
	}

	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}

	public String getDepartmentLocation() {
		return departmentLocation;
	}

	public void setDepartmentLocation(String departmentLocation) {
		this.departmentLocation = departmentLocation;
	}

	public String getDepartmentIsActive() {
		return departmentIsActive;
	}

	public void setDepartmentIsActive(String departmentIsActive) {
		this.departmentIsActive = departmentIsActive;
	}
	

}
