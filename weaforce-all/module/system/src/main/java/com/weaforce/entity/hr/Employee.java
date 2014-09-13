package com.weaforce.entity.hr;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.core.util.DateUtil;

/**
 * 雇员管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "hr_employee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Employee extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 8418978469846353280L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "97", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "EMPLOYEE_ID", length = 20)
	private Long employeeId;
	// 姓名
	@Column(name = "EMPLOYEE_NAME", length = 45)
	private String employeeName;
	// 出生日期
	@Column(name = "EMPLOYEE_BIRTHDAY", length = 20)
	private Long employeeBirthday;
	// 身份证
	@Column(name = "EMPLOYEE_IDENTITY", length = 20)
	private String employeeIdentity;
	// 地址
	@Column(name = "EMPLOYEE_ADDRESS", length = 180)
	private String employeeAddress;
	// 手机
	@Column(name = "EMPLOYEE_MOBILE", length = 15)
	private String employeeMobile;
	// Email
	@Column(name = "EMPLOYEE_EMAIL", length = 45)
	private String employeeEmail;
	// MSN
	@Column(name = "EMPLOYEE_MSN", length = 45)
	private String employeeMsn;
	// QQ
	@Column(name = "EMPLOYEE_QQ", length = 15)
	private String employeeQq;
	// 活动
	@Column(name = "EMPLOYEE_IS_ACTIVE", length = 1)
	private String employeeIsActive;
	// 说明
	@Column(name = "EMPLOYEE_DESCRIPTION", length = 255)
	private String employeeDescription;

	@Transient
	private String employeeBirthdayDate;

	public Employee() {
		employeeIsActive = "1";
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Long getEmployeeBirthday() {
		return employeeBirthday;
	}

	public void setEmployeeBirthday(Long employeeBirthday) {
		this.employeeBirthday = employeeBirthday;
	}

	public String getEmployeeIdentity() {
		return employeeIdentity;
	}

	public void setEmployeeIdentity(String employeeIdentity) {
		this.employeeIdentity = employeeIdentity;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getEmployeeMobile() {
		return employeeMobile;
	}

	public void setEmployeeMobile(String employeeMobile) {
		this.employeeMobile = employeeMobile;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeMsn() {
		return employeeMsn;
	}

	public void setEmployeeMsn(String employeeMsn) {
		this.employeeMsn = employeeMsn;
	}

	public String getEmployeeQq() {
		return employeeQq;
	}

	public void setEmployeeQq(String employeeQq) {
		this.employeeQq = employeeQq;
	}

	public String getEmployeeIsActive() {
		return employeeIsActive;
	}

	public void setEmployeeIsActive(String employeeIsActive) {
		this.employeeIsActive = employeeIsActive;
	}

	public String getEmployeeDescription() {
		return employeeDescription;
	}

	public void setEmployeeDescription(String employeeDescription) {
		this.employeeDescription = employeeDescription;
	}

	/**
	 * 以W3C格式显示日期,如2008-08-08
	 * 
	 * @return
	 */
	public String getEmployeeBirthdayDate() {
		if (employeeBirthday != null)
			employeeBirthdayDate = DateUtil.defaultFormat(new Date(
					employeeBirthday));
		return employeeBirthdayDate;
	}

	/**
	 * 以UTC格式保存日期
	 * 
	 * @param employeeBirthdayDate
	 */

	public void setEmployeeBirthdayDate(String employeeBirthdayDate) {
		if (employeeBirthdayDate != null)
			employeeBirthday = DateUtil.getUTCDate(employeeBirthdayDate);
		this.employeeBirthdayDate = employeeBirthdayDate;
	}

}
