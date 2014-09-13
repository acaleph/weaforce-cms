package com.weaforce.entity.ccm.pm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.entity.system.User;
import com.weaforce.entity.PrimaryEntity;
import com.weaforce.core.util.CollectionUtil;
import com.weaforce.core.util.DateUtil;

@Entity
@Table(name = "fp_project")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Project extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 6493733595747968099L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "39", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "PROJECT_ID", length = 20)
	private Long projectId;
	// 代码
	@Column(name = "PROJECT_CODE", length = 10)
	private String projectCode;
	// 标题
	@Column(name = "PROJECT_TITLE", length = 180)
	private String projectTitle;
	// 描述
	@Column(name = "PROJECT_DESCRIPTION", length = 255)
	private String projectDescription;
	// 内容
	@Column(name = "PROJECT_CONTENT")
	private String projectContent;
	// 计划开始
	@Column(name = "PROJECT_PLAN_START", length = 20)
	private Long projectPlanStart;
	// 计划结束
	@Column(name = "PROJECT_PLAN_END", length = 20)
	private Long projectPlanEnd;
	// 实际开始
	@Column(name = "PROJECT_REAL_START", length = 20)
	private Long projectRealStart;
	// 实际结束
	@Column(name = "PROJECT_Real_END", length = 20)
	private Long projectRealEnd;
	// 计划费用
	@Column(name = "PROJECT_COST_ESTIMATE", length = 16)
	private Double projectCostEstimate;
	// 实际费用
	@Column(name = "PROJECT_COST_REAL", length = 16)
	private Double projectCostReal;
	// 活动
	@Column(name = "PROJECT_IS_ACTIVE", length = 1)
	private String projectIsActive;
	// 完成
	@Column(name = "PROJECT_OK", length = 1)
	private String projectOk;
	// 独占
	@Column(name = "PROJECT_IS_SINGLETON", length = 1)
	private String projectIsSingleton;
	// 占工程比重
	@Column(name = "PROJECT_ENGINEERING_PERCENT", length = 3)
	private byte projectEngineeringPercent;
	// 负责人
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_MASTER_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_PROJECT_MASTER_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private User projectMaster;
	// 项目经理
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_MANAGER_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@org.hibernate.annotations.ForeignKey(name = "FK_PROJECT_MANAGER_ID")
	private User projectManager;
	// 工程
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ENGINEERING_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_PROJECT_ENGINEERING_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Engineering projectEngineering;
	// 参与人员
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "fp_project_staff", joinColumns = { @JoinColumn(name = "PROJECT_ID") }, inverseJoinColumns = { @JoinColumn(name = "STAFF_ID") })
	@org.hibernate.annotations.ForeignKey(name = "FK_PROJECT_STAFF_ID", inverseName = "FK_STAFF_PROJECT_ID")
	@OrderBy("staffId")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<User> projectStaff = new ArrayList<User>();
	// 任务列表
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "missionProject")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Mission> projectMission = new ArrayList<Mission>();

	@Transient
	private String projectPlanStartDate;
	@Transient
	private String projectPlanEndDate;
	@Transient
	private String projectRealStartDate;
	@Transient
	private String projectRealEndDate;
	@Transient
	private String enableSave;
	@Transient
	private String enableDelete;

	public Project() {
		projectIsActive = "1";
		projectEngineeringPercent = 1;
		projectIsSingleton = "0";
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getProjectContent() {
		return projectContent;
	}

	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}

	public Long getProjectPlanStart() {
		return projectPlanStart;
	}

	public void setProjectPlanStart(Long projectPlanStart) {
		// Convert Date to UTC
		this.projectPlanStart = projectPlanStart;
	}

	public Long getProjectPlanEnd() {
		return projectPlanEnd;
	}

	public void setProjectPlanEnd(Long projectPlanEnd) {
		this.projectPlanEnd = projectPlanEnd;
	}

	public Long getProjectRealStart() {
		return projectRealStart;
	}

	public void setProjectRealStart(Long projectRealStart) {
		this.projectRealStart = projectRealStart;
	}

	public Long getProjectRealEnd() {
		return projectRealEnd;
	}

	public void setProjectRealEnd(Long projectRealEnd) {
		this.projectRealEnd = projectRealEnd;
	}

	public Double getProjectCostEstimate() {
		return projectCostEstimate;
	}

	public void setProjectCostEstimate(Double projectCostEstimate) {
		this.projectCostEstimate = projectCostEstimate;
	}

	public Double getProjectCostReal() {
		return projectCostReal;
	}

	public void setProjectCostReal(Double projectCostReal) {
		this.projectCostReal = projectCostReal;
	}

	public String getProjectIsActive() {
		return projectIsActive;
	}

	public void setProjectIsActive(String projectIsActive) {
		this.projectIsActive = projectIsActive;
	}

	public String getProjectOk() {
		return projectOk;
	}

	public void setProjectOk(String projectOk) {
		this.projectOk = projectOk;
	}

	public String getProjectIsSingleton() {
		return projectIsSingleton;
	}

	public void setProjectIsSingleton(String projectIsSingleton) {
		this.projectIsSingleton = projectIsSingleton;
	}

	public byte getProjectEngineeringPercent() {
		return projectEngineeringPercent;
	}

	public void setProjectEngineeringPercent(byte projectEngineeringPercent) {
		this.projectEngineeringPercent = projectEngineeringPercent;
	}

	public User getProjectMaster() {
		return projectMaster;
	}

	public void setProjectMaster(User projectMaster) {
		this.projectMaster = projectMaster;
	}

	public User getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(User projectManager) {
		this.projectManager = projectManager;
	}

	public Engineering getProjectEngineering() {
		return projectEngineering;
	}

	public void setProjectEngineering(Engineering projectEngineering) {
		this.projectEngineering = projectEngineering;
	}

	@Transient
	public String getProjectStaffNames() throws Exception {
		return CollectionUtil.fetchPropertyToString(this.getAccount(),
				projectStaff, "userNameCn", ",");
	}

	@Transient
	public List<Long> getProjectStaffIds() throws Exception {
		return CollectionUtil.fetchPropertyToList(this.getAccount(),
				projectStaff, "userId");
	}

	public List<User> getProjectStaff() {
		return projectStaff;
	}

	public void setProjectStaff(List<User> projectStaff) {
		this.projectStaff = projectStaff;
	}

	public String getProjectPlanStartDate() {
		if (projectPlanStart != null)
			projectPlanStartDate = DateUtil.defaultFormat(new Date(
					projectPlanStart));
		return projectPlanStartDate;
	}

	public void setProjectPlanStartDate(String projectPlanStartDate) {
		if (StringUtils.isNotEmpty(projectPlanStartDate))
			this.projectPlanStart = DateUtil.getUTCDate(projectPlanStartDate);
		this.projectPlanStartDate = projectPlanStartDate;
	}

	public String getProjectPlanEndDate() {
		if (projectPlanEnd != null)
			projectPlanEndDate = DateUtil
					.defaultFormat(new Date(projectPlanEnd));
		return projectPlanEndDate;
	}

	public void setProjectPlanEndDate(String projectPlanEndDate) {
		if (StringUtils.isNotEmpty(projectPlanEndDate))
			this.projectPlanEnd = DateUtil.getUTCDate(projectPlanEndDate);
		this.projectPlanEndDate = projectPlanEndDate;
	}

	public String getProjectRealStartDate() {
		if (projectRealStart != null)
			projectRealStartDate = DateUtil.defaultFormat(new Date(
					projectRealStart));
		return projectRealStartDate;
	}

	public void setProjectRealStartDate(String projectRealStartDate) {
		if (StringUtils.isNotEmpty(projectRealStartDate))
			this.projectRealStart = DateUtil.getUTCDate(projectRealStartDate);
		this.projectRealStartDate = projectRealStartDate;
	}

	public String getProjectRealEndDate() {
		if (projectRealEnd != null)
			projectRealEndDate = DateUtil
					.defaultFormat(new Date(projectRealEnd));
		return projectRealEndDate;
	}

	public void setProjectRealEndDate(String projectRealEndDate) {
		if (StringUtils.isNotEmpty(projectRealEndDate))
			this.projectRealEnd = DateUtil.getUTCDate(projectRealEndDate);
		this.projectRealEndDate = projectRealEndDate;
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

	public List<Mission> getProjectMission() {
		return projectMission;
	}

	public void setProjectMission(List<Mission> projectMission) {
		this.projectMission = projectMission;
	}

}
