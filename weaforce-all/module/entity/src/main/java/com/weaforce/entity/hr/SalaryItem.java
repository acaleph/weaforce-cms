package com.weaforce.entity.hr;

import java.io.Serializable;
import java.math.BigDecimal;

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

import com.weaforce.entity.PrimaryEntity;

/**
 * 工资项目类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "hr_salary_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class SalaryItem extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -2228041458932714957L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "99", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "ITEM_ID", length = 20)
	private Long itemId;
	// 工资项目名称
	@Column(name = "ITEM_NAME", length = 45)
	private String itemName;
	// 工资
	@Column(name = "ITEM_SALARY_VALUE", length = 6)
	private BigDecimal itemSalaryValue;
	// 活动
	@Column(name = "ITEM_IS_ACTIVE", length = 1)
	private String itemIsActive;
	// 描述
	@Column(name = "ITEM_DESCRIPTION", length = 45)
	private String itemDescription;
	// 薪资
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_SALARY_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ITEM_SALARY_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Salary itemSalary;

	public SalaryItem() {
		itemIsActive = "1";
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getItemSalaryValue() {
		return itemSalaryValue;
	}

	public void setItemSalaryValue(BigDecimal itemSalaryValue) {
		this.itemSalaryValue = itemSalaryValue;
	}

	public String getItemIsActive() {
		return itemIsActive;
	}

	public void setItemIsActive(String itemIsActive) {
		this.itemIsActive = itemIsActive;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Salary getItemSalary() {
		return itemSalary;
	}

	public void setItemSalary(Salary itemSalary) {
		this.itemSalary = itemSalary;
	}

}
