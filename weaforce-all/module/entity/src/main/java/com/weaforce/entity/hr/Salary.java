package com.weaforce.entity.hr;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;

/**
 * 工资管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "hr_salary")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Salary extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -2631880377774453013L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "98", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "SALARY_ID", length = 20)
	private Long salaryId;
	// 工资名称
	@Column(name = "SALARY_NAME", length = 45)
	private String salaryName;
	// 数量:标准天数/时数/件数
	@Column(name = "SALARY_VALUE", length = 10)
	private String salaryValue;
	// 活动
	@Column(name = "SALARY_IS_ACTIVE", length = 1)
	private String salaryIsActive;
	// 描述
	@Column(name = "SALARY_DESCRIPTION", length = 255)
	private String salaryDescription;
	// 薪资内容
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "itemSalary", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<SalaryItem> salaryItem;

	public Salary() {
		salaryIsActive = "1";
	}

	public Long getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(Long salaryId) {
		this.salaryId = salaryId;
	}

	public String getSalaryName() {
		return salaryName;
	}

	public void setSalaryName(String salaryName) {
		this.salaryName = salaryName;
	}

	public String getSalaryValue() {
		return salaryValue;
	}

	public void setSalaryValue(String salaryValue) {
		this.salaryValue = salaryValue;
	}

	public String getSalaryIsActive() {
		return salaryIsActive;
	}

	public void setSalaryIsActive(String salaryIsActive) {
		this.salaryIsActive = salaryIsActive;
	}

	public String getSalaryDescription() {
		return salaryDescription;
	}

	public void setSalaryDescription(String salaryDescription) {
		this.salaryDescription = salaryDescription;
	}

	public List<SalaryItem> getSalaryItem() {
		return salaryItem;
	}

	public void setSalaryItem(List<SalaryItem> salaryItem) {
		this.salaryItem = salaryItem;
	}

}
