package com.weaforce.system.entity.organ;

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

/**
 * It's the all staffs in a organization
 * 
 * @author Manfred
 * 
 */
@Entity
@Table(name = "organ_department_staff")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Staff extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -8734548993098577706L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "65", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "STAFF_ID", length = 20)
	private Long staffId;
	// 登录
	@Column(name = "STAFF_LOGIN", length = 45)
	private String staffLogin;
	// 名称
	@Column(name = "STAFF_NAME", length = 90)
	private String staffName;
	// 有效
	@Column(name = "STAFF_IS_ACTIVE", length = 1)
	private String staffIsActive;
	// 尊称
	@Column(name = "STAFF_COURTESY_TITLE", length = 45)
	private String staffCourtesyTitle;
	// 会议间隔
	@Column(name = "CONFIG_MEETING_HOUR_INTERVAL", length = 10)
	private Integer configMeetingHourInterval;
	// 会议队列
	@Column(name = "CONFIG_INFO_RANK", length = 255)
	private String configInfoRank;
	// 工作队列
	@Column(name = "CONFIG_JOB_RANK", length = 255)
	private String configJobRank;
	// 工程队列
	@Column(name = "CONFIG_ENGINEERING_RANK", length = 255)
	private String configEngineeringRank;
	// 项目队列
	@Column(name = "CONFIG_PROJECT_RANK", length = 255)
	private String configProjectRank;
	// 任务队列
	@Column(name = "CONFIG_MISSION_RANK", length = 255)
	private String configMissionRank;
	// 相关人员队列
	@Column(name = "CONFIG_STAFF_RANK", length = 255)
	private String configStaffRank;
	// 相关人员队列
	@Column(name = "CONFIG_ROUTINE_RANK", length = 255)
	private String configRoutineRank;
	// 评分策略
	@Column(name = "CONFIG_STRATEGY_ID", length = 20)
	private Long configStrategyId;
	// 主要财务中心
	@Column(name = "CONFIG_PROFIT_CENTER_ID", length = 20)
	private Long configProfitCenterId;
	// 默认部门
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "STAFF_DEPARTMENT_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_STAFF_DEPARTMENT_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Department staffDepartment;
	// 相关部门
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "organ_staff_department", joinColumns = { @JoinColumn(name = "STAFF_ID") }, inverseJoinColumns = { @JoinColumn(name = "DEPARTMENT_ID") })
	@OrderBy("departmentId")
	@org.hibernate.annotations.ForeignKey(name = "FK_CONFIG_DEPARTMENT_ID", inverseName = "FK_DEPARTMENT_CONFIG_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Department> configDepartment = new ArrayList<Department>();

	public Staff() {
		staffIsActive = "1";
		// not permitted to be null.
		configInfoRank = "";
		configJobRank = "";
		configEngineeringRank = "";
		configProjectRank = "";
		configMissionRank = "";
		configStaffRank = "";
		configRoutineRank = "";
		configMeetingHourInterval = 10;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getStaffLogin() {
		return staffLogin;
	}

	public void setStaffLogin(String staffLogin) {
		this.staffLogin = staffLogin;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffCourtesyTitle() {
		return staffCourtesyTitle;
	}

	public void setStaffCourtesyTitle(String staffCourtesyTitle) {
		this.staffCourtesyTitle = staffCourtesyTitle;
	}

	public Integer getConfigMeetingHourInterval() {
		return configMeetingHourInterval;
	}

	public void setConfigMeetingHourInterval(Integer configMeetingHourInterval) {
		this.configMeetingHourInterval = configMeetingHourInterval;
	}

	public String getConfigInfoRank() {
		return configInfoRank;
	}

	public void setConfigInfoRank(String configInfoRank) {
		this.configInfoRank = configInfoRank;
	}

	public String getConfigJobRank() {
		return configJobRank;
	}

	public void setConfigJobRank(String configJobRank) {
		this.configJobRank = configJobRank;
	}

	public String getConfigEngineeringRank() {
		return configEngineeringRank;
	}

	public void setConfigEngineeringRank(String configEngineeringRank) {
		this.configEngineeringRank = configEngineeringRank;
	}

	public String getConfigProjectRank() {
		return configProjectRank;
	}

	public void setConfigProjectRank(String configProjectRank) {
		this.configProjectRank = configProjectRank;
	}

	public String getConfigMissionRank() {
		return configMissionRank;
	}

	public void setConfigMissionRank(String configMissionRank) {
		this.configMissionRank = configMissionRank;
	}

	public String getConfigStaffRank() {
		return configStaffRank;
	}

	public void setConfigStaffRank(String configStaffRank) {
		this.configStaffRank = configStaffRank;
	}

	public String getConfigRoutineRank() {
		return configRoutineRank;
	}

	public void setConfigRoutineRank(String configRoutineRank) {
		this.configRoutineRank = configRoutineRank;
	}

	public String getStaffIsActive() {
		return staffIsActive;
	}

	public void setStaffIsActive(String staffIsActive) {
		this.staffIsActive = staffIsActive;
	}

	public Long getConfigStrategyId() {
		return configStrategyId;
	}

	public void setConfigStrategyId(Long configStrategyId) {
		this.configStrategyId = configStrategyId;
	}

	public Long getConfigProfitCenterId() {
		return configProfitCenterId;
	}

	public void setConfigProfitCenterId(Long configProfitCenterId) {
		this.configProfitCenterId = configProfitCenterId;
	}

	public Department getStaffDepartment() {
		return staffDepartment;
	}

	public void setStaffDepartment(Department staffDepartment) {
		this.staffDepartment = staffDepartment;
	}

	public List<Department> getConfigDepartment() {
		return configDepartment;
	}

	public void setConfigDepartment(List<Department> configDepartment) {
		this.configDepartment = configDepartment;
	}

	@Transient
	public String getConfigDepartmentNames() throws Exception {
		return CollectionUtil.fetchPropertyToString(this.getAccount(),
				configDepartment, "departmentName", ",");
	}

	@Transient
	public List<Long> getConfigDepartmentIds() throws Exception {
		return CollectionUtil.fetchPropertyToList(this.getAccount(),
				configDepartment, "departmentId");
	}
}
