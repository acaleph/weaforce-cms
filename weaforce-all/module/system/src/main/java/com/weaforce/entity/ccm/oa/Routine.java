package com.weaforce.entity.ccm.oa;

import java.io.Serializable;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;
import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.core.util.CollectionUtil;
import com.weaforce.entity.admin.User;
import com.weaforce.entity.organ.Department;

@Entity
@Table(name = "fo_routine")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Routine extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 776588812614696974L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "91", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "ROUTINE_ID", length = 20)
	private Long routineId;
	// 标题
	@Column(name = "ROUTINE_TITLE", length = 180)
	private String routineTitle;
	// 描述
	@Column(name = "ROUTINE_DESCRIPTION", length = 255)
	private String routineDescription;
	// 内容
	@Column(name = "ROUTINE_CONTENT")
	private String routineContent;
	// 周期
	@Column(name = "ROUTINE_PERIOD", length = 20)
	private Long routinePeriod;
	// 有效
	@Column(name = "ROUTINE_IS_ACTIVE", length = 1)
	private String routineIsActive;
	// 部门
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ROUTINE_DEPARTMENT_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ROUTINE_DEPARTMENT_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Department routineDepartment;
	// 参与人
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "fo_routine_staff", joinColumns = { @JoinColumn(name = "ROUTINE_ID") }, inverseJoinColumns = { @JoinColumn(name = "STAFF_ID") })
	@org.hibernate.annotations.ForeignKey(name = "FK_ROUTINE_USER_ID", inverseName = "FK_USER_ROUTINE_ID")
	@OrderBy("userId")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<User> routineUser = new ArrayList<User>();

	public Routine() {
		routineIsActive = "1";
	}

	public Long getRoutineId() {
		return routineId;
	}

	public void setRoutineId(Long routineId) {
		this.routineId = routineId;
	}

	public String getRoutineTitle() {
		return routineTitle;
	}

	public void setRoutineTitle(String routineTitle) {
		this.routineTitle = routineTitle;
	}

	public String getRoutineDescription() {
		return routineDescription;
	}

	public void setRoutineDescription(String routineDescription) {
		this.routineDescription = routineDescription;
	}

	public String getRoutineContent() {
		return routineContent;
	}

	public Long getRoutinePeriod() {
		return routinePeriod;
	}

	public void setRoutinePeriod(Long routinePeriod) {
		this.routinePeriod = routinePeriod;
	}

	public String getRoutineIsActive() {
		return routineIsActive;
	}

	public void setRoutineIsActive(String routineIsActive) {
		this.routineIsActive = routineIsActive;
	}

	public void setRoutineContent(String routineContent) {
		this.routineContent = routineContent;
	}

	public Department getRoutineDepartment() {
		return routineDepartment;
	}

	public void setRoutineDepartment(Department routineDepartment) {
		this.routineDepartment = routineDepartment;
	}

	@Transient
	public String getRoutineStaffNames() throws Exception {
		return CollectionUtil.fetchPropertyToString(this.getAccount(),
				routineUser, "userNameCn", ",");
	}

	@Transient
	public List<Long> getRoutineStaffIds() throws Exception {
		return CollectionUtil.fetchPropertyToList(this.getAccount(),
				routineUser, "userId");
	}
}
