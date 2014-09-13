package com.weaforce.entity.ccm.pm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.entity.admin.User;
import com.weaforce.entity.ccm.Cost;
import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.core.util.CollectionUtil;

/**
 * 工程管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "co_engineering")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Engineering extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 7973633160402322372L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "46", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "ENGINEERING_ID", length = 20)
	private Long engineeringId;
	// 排序
	@Column(name = "ENGINEERING_GROUP_ORDER", length = 3)
	private byte engineeringGroupOrder;
	// 标题
	@Column(name = "ENGINEERING_TITLE", length = 45)
	private String engineeringTitle;
	// 描述
	@Column(name = "ENGINEERING_DESCRIPTION", length = 255)
	private String engineeringDescription;
	// 预算费用工程
	@Column(name = "ENGINEERING_IS_BUDGET", length = 1)
	private String engineeringIsBudget;
	// 计划费用
	@Column(name = "ENGINEERING_BUDGET", length = 16)
	private BigDecimal engineeringBudget;
	// 完成
	@Column(name = "ENGINEERING_IS_OK", length = 1)
	private String engineeringIsOk;
	// 有效
	@Column(name = "ENGINEERING_IS_ACTIVE", length = 1)
	private String engineeringIsActive;
	// 独占
	@Column(name = "ENGINEERING_IS_SINGLETON", length = 1)
	private String engineeringIsSingleton;
	// 工程指南
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "manualEngineering", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "ENGINEERING_ID")
	private EngineeringManual engineeringManual;
	// 负责人
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ENGINEERING_MASTER_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_ENGINEERING_MASTER_STAFF_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private User engineeringMaster;
	// 参与人
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "co_engineering_user", joinColumns = { @JoinColumn(name = "ENGINEERING_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	@org.hibernate.annotations.ForeignKey(name = "FK_ENGINEERING_USER_ID", inverseName = "FK_STAFF_ENGINEERING_ID")
	@OrderBy("userId")
	private List<User> engineeringStaff = new ArrayList<User>();
	// 父工程
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ENGINEERING_FID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ENGINEERING_FID")
	private Engineering engineeringParent;
	// 子工程
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "engineeringParent", fetch = FetchType.LAZY)
	@org.hibernate.annotations.OrderBy(clause = "ENGINEERING_GROUP_ORDER asc")
	// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Engineering> engineeringChildren = new ArrayList<Engineering>();
	// 所属项目
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "projectEngineering", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Project> engineeringProject = new ArrayList<Project>();
	// 相关费用
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "costEngineering", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Cost> engineeringCost = new ArrayList<Cost>();

	@Transient
	private String enableSave;
	@Transient
	private String enableDelete;

	public Engineering() {
		engineeringIsBudget = "1";
		engineeringIsOk = "0";
		engineeringIsActive = "1";
		engineeringIsSingleton = "0";
	}

	public Long getEngineeringId() {
		return engineeringId;
	}

	public void setEngineeringId(Long engineeringId) {
		this.engineeringId = engineeringId;
	}

	public byte getEngineeringGroupOrder() {
		return engineeringGroupOrder;
	}

	public void setEngineeringGroupOrder(byte engineeringGroupOrder) {
		this.engineeringGroupOrder = engineeringGroupOrder;
	}

	public String getEngineeringTitle() {
		return engineeringTitle;
	}

	public void setEngineeringTitle(String engineeringTitle) {
		this.engineeringTitle = engineeringTitle;
	}

	public String getEngineeringDescription() {
		return engineeringDescription;
	}

	public void setEngineeringDescription(String engineeringDescription) {
		this.engineeringDescription = engineeringDescription;
	}

	public List<User> getEngineeringStaff() {
		return engineeringStaff;
	}

	public void setEngineeringStaff(List<User> engineeringStaff) {
		this.engineeringStaff = engineeringStaff;
	}

	public String getEngineeringStaffNames() throws Exception {
		return CollectionUtil.fetchPropertyToString(this.getAccount(),
				engineeringStaff, "staffName", ",");
	}

	public List<Long> getEngineeringStaffIds() throws Exception {
		return CollectionUtil.fetchPropertyToList(this.getAccount(),
				engineeringStaff, "staffId");
	}

	public String getEngineeringIsBudget() {
		return engineeringIsBudget;
	}

	public void setEngineeringIsBudget(String engineeringIsBudget) {
		this.engineeringIsBudget = engineeringIsBudget;
	}

	public BigDecimal getEngineeringBudget() {
		return engineeringBudget;
	}

	public void setEngineeringBudget(BigDecimal engineeringBudget) {
		this.engineeringBudget = engineeringBudget;
	}

	public String getEngineeringIsOk() {
		return engineeringIsOk;
	}

	public void setEngineeringIsOk(String engineeringIsOk) {
		this.engineeringIsOk = engineeringIsOk;
	}

	public String getEngineeringIsActive() {
		return engineeringIsActive;
	}

	public void setEngineeringIsActive(String engineeringIsActive) {
		this.engineeringIsActive = engineeringIsActive;
	}

	public String getEngineeringIsSingleton() {
		return engineeringIsSingleton;
	}

	public void setEngineeringIsSingleton(String engineeringIsSingleton) {
		this.engineeringIsSingleton = engineeringIsSingleton;
	}

	public EngineeringManual getEngineeringManual() {
		return engineeringManual;
	}

	public void setEngineeringManual(EngineeringManual engineeringManual) {
		this.engineeringManual = engineeringManual;
	}

	public User getEngineeringMaster() {
		return engineeringMaster;
	}

	public void setEngineeringMaster(User engineeringMaster) {
		this.engineeringMaster = engineeringMaster;
	}

	public Engineering getEngineeringParent() {
		return engineeringParent;
	}

	public void setEngineeringParent(Engineering engineeringParent) {
		this.engineeringParent = engineeringParent;
	}

	public List<Engineering> getEngineeringChildren() {
		return engineeringChildren;
	}

	public void setEngineeringChildren(List<Engineering> engineeringChildren) {
		this.engineeringChildren = engineeringChildren;
	}

	/**
	 * 加入子级Engineering
	 * 
	 * @param engineering
	 */
	public void addEngineeringChild(Engineering engineering) {
		if (engineering == null)
			throw new IllegalArgumentException("Null child engineering!");
		if (engineering.getEngineeringParent() != null)
			engineering.getEngineeringParent().getEngineeringChildren()
					.remove(engineering);
		engineering.setEngineeringParent(this.engineeringParent);
		this.engineeringChildren.add(engineering);
	}

	/**
	 * 移出子级Engineering
	 * 
	 * @param engineering
	 */
	public void removeEngineeringChild(Engineering engineering) {
		if (engineering == null)
			throw new IllegalArgumentException("Null child engineering!");
		engineering.setEngineeringParent(null);
		this.engineeringChildren.remove(engineering);
	}

	public List<Project> getEngineeringProject() {
		return engineeringProject;
	}

	public void setEngineeringProject(List<Project> engineeringProject) {
		this.engineeringProject = engineeringProject;
	}

	public List<Cost> getEngineeringCost() {
		return engineeringCost;
	}

	public void setEngineeringCost(List<Cost> engineeringCost) {
		this.engineeringCost = engineeringCost;
	}

	public String getEnableSave() {
		return enableSave;
	}

	public void setEnableSave(String enableSave) {
		this.enableSave = enableSave;
	}

	public String getEnableDelete() {
		return enableDelete;
	}

	public void setEnableDelete(String enableDelete) {
		this.enableDelete = enableDelete;
	}

}
