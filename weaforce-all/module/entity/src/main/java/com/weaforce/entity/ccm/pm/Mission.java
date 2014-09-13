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

import com.weaforce.entity.PrimaryEntity;
import com.weaforce.core.util.CollectionUtil;
import com.weaforce.core.util.DateUtil;
import com.weaforce.entity.finance.Period;
import com.weaforce.entity.system.User;

/**
 * 项目任务管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "fp_mission")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Mission extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 1343378385903487265L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "44", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "MISSION_ID", length = 20)
	private Long missionId;
	// 是否被接受
	@Column(name = "MISSION_IS_ACCEPT", length = 1)
	private String missionIsAccept;
	// 是否完成
	@Column(name = "MISSION_IS_OK", length = 1)
	private String missionIsOk;
	// 是否确认
	@Column(name = "MISSION_IS_CONFIRM", length = 1)
	private String missionIsConfirm;
	// 是否挂起
	@Column(name = "MISSION_IS_SUSPEND", length = 1)
	private String missionIsSuspend;
	// 接受时间
	@Column(name = "MISSION_ACCEPT_TIME", length = 20)
	private Long missionAcceptTime;
	// 完成时间
	@Column(name = "MISSION_OK_TIME", length = 20)
	private Long missionOkTime;
	// 确认时间
	@Column(name = "MISSION_CONFIRM_TIME", length = 20)
	private Long missionConfirmTime;
	// 统计时间
	@Column(name = "MISSION_SCORE_TIME", length = 20)
	private Long missionScoreTime;
	// 挂起时间
	@Column(name = "MISSION_SUSPEND_TIME", length = 20)
	private Long missionSuspendTime;
	// 挂起原因
	@Column(name = "MISSION_SUSPEND_REASON", length = 255)
	private String missionSuspendReason;
	// 是否有效
	@Column(name = "MISSION_IS_ACTIVE", length = 1)
	private String missionIsActive;
	// 是否记分
	@Column(name = "MISSION_IS_SCORE", length = 1)
	private String missionIsScore;
	// 标题
	@Column(name = "MISSION_TITLE", length = 180)
	private String missionTitle;
	// 描述
	@Column(name = "MISSION_DESCRIPTION", length = 255)
	private String missionDescription;
	// 内容
	@Column(name = "MISSION_CONTENT")
	private String missionContent;
	// 计划开始
	@Column(name = "MISSION_PLAN_START", length = 20)
	private Long missionPlanStart;
	// 计划结束
	@Column(name = "MISSION_PLAN_END", length = 20)
	private Long missionPlanEnd;
	// 实际开始
	@Column(name = "MISSION_REAL_START", length = 20)
	private Long missionRealStart;
	// 实际结束
	@Column(name = "MISSION_REAL_END", length = 20)
	private Long missionRealEnd;
	// 占项目完成百分比
	@Column(name = "MISSION_PROJECT_PERCENT", length = 3)
	private byte missionProjectPercent;
	// 独占
	@Column(name = "MISSION_IS_SINGLETON", length = 1)
	private String missionIsSingleton;
	// 项目
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "MISSION_PROJECT_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_MISSION_PROJECT_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Project missionProject;
	// 任务主负责人
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "MISSION_MASTER_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_MISSION_MASTER_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private User missionMaster;
	// 任务副负责人
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "MISSION_SLAVE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_MISSION_SLAVE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private User missionSlave;
	// 参与人员
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "fp_mission_staff", joinColumns = { @JoinColumn(name = "MISSION_ID") }, inverseJoinColumns = { @JoinColumn(name = "STAFF_ID") })
	@org.hibernate.annotations.ForeignKey(name = "FK_MISSION_STAFF_ID", inverseName = "FK_STAFF_MISSION_ID")
	@OrderBy("staffId")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<User> missionStaff = new ArrayList<User>();
	// 任务记录,删除任务,将删除所有相关记录
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "noteMission", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<MissionNote> missionNote = new ArrayList<MissionNote>();
	// 评分：删除当前mission时，将删除所有的评分记录
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "scoreMission", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<MissionScore> missionScore = new ArrayList<MissionScore>();
	// 账期
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "MISSION_PERIOD_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_MISSION_PERIOD_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Period missionPeriod;

	// 接受时间间隔
	@Transient
	private int missionAcceptDay;
	// 完成时间间隔
	@Transient
	private int missionOkDay;
	// 确认时间间隔
	@Transient
	private int missionConfirmDay;

	@Transient
	private String missionPlanStartDate;
	@Transient
	private String missionPlanEndDate;
	@Transient
	private String missionRealStartDate;
	@Transient
	private String missionRealEndDate;
	@Transient
	private String ganttWidthPlanBlank;
	@Transient
	private String ganttWidthPlanValue;
	@Transient
	private String ganttWidthRealBlank;
	@Transient
	private String ganttWidthRealValue;
	@Transient
	private String missionMasterName;
	@Transient
	private String enableSave;
	@Transient
	private String enableDelete;

	public Mission() {
		missionIsActive = "1";
		missionIsAccept = "0";
		missionIsOk = "0";
		missionIsConfirm = "0";
		missionIsSuspend = "0";
		missionIsScore = "0";
		missionIsSingleton = "0";
	}

	public Long getMissionId() {
		return missionId;
	}

	public void setMissionId(Long missionId) {
		this.missionId = missionId;
	}

	public String getMissionIsActive() {
		return missionIsActive;
	}

	public void setMissionIsActive(String missionIsActive) {
		this.missionIsActive = missionIsActive;
	}

	public String getMissionIsScore() {
		return missionIsScore;
	}

	public void setMissionIsScore(String missionIsScore) {
		this.missionIsScore = missionIsScore;
	}

	public String getMissionIsAccept() {
		return missionIsAccept;
	}

	public void setMissionIsAccept(String missionIsAccept) {
		this.missionIsAccept = missionIsAccept;
	}

	public String getMissionIsOk() {
		return missionIsOk;
	}

	public void setMissionIsOk(String missionIsOk) {
		this.missionIsOk = missionIsOk;
	}

	public String getMissionIsConfirm() {
		return missionIsConfirm;
	}

	public void setMissionIsConfirm(String missionIsConfirm) {
		this.missionIsConfirm = missionIsConfirm;
	}

	public String getMissionIsSuspend() {
		return missionIsSuspend;
	}

	public void setMissionIsSuspend(String missionIsSuspend) {
		this.missionIsSuspend = missionIsSuspend;
	}

	public Long getMissionAcceptTime() {
		return missionAcceptTime;
	}

	public void setMissionAcceptTime(Long missionAcceptTime) {
		this.missionAcceptTime = missionAcceptTime;
	}

	public Long getMissionOkTime() {
		return missionOkTime;
	}

	public void setMissionOkTime(Long missionOkTime) {
		this.missionOkTime = missionOkTime;
	}

	public Long getMissionConfirmTime() {
		return missionConfirmTime;
	}

	public void setMissionConfirmTime(Long missionConfirmTime) {
		this.missionConfirmTime = missionConfirmTime;
	}

	public Long getMissionScoreTime() {
		return missionScoreTime;
	}

	public void setMissionScoreTime(Long missionScoreTime) {
		this.missionScoreTime = missionScoreTime;
	}

	public Long getMissionSuspendTime() {
		return missionSuspendTime;
	}

	public void setMissionSuspendTime(Long missionSuspendTime) {
		this.missionSuspendTime = missionSuspendTime;
	}

	public String getMissionSuspendReason() {
		return missionSuspendReason;
	}

	public void setMissionSuspendReason(String missionSuspendReason) {
		this.missionSuspendReason = missionSuspendReason;
	}

	public String getMissionTitle() {
		return missionTitle;
	}

	public void setMissionTitle(String missionTitle) {
		this.missionTitle = missionTitle;
	}

	public String getMissionDescription() {
		return missionDescription;
	}

	public void setMissionDescription(String missionDescription) {
		this.missionDescription = missionDescription;
	}

	public String getMissionContent() {
		return missionContent;
	}

	public void setMissionContent(String missionContent) {
		this.missionContent = missionContent;
	}

	public Long getMissionPlanStart() {
		return missionPlanStart;
	}

	public void setMissionPlanStart(Long missionPlanStart) {
		this.missionPlanStart = missionPlanStart;
	}

	public Long getMissionPlanEnd() {
		return missionPlanEnd;
	}

	public void setMissionPlanEnd(Long missionPlanEnd) {
		this.missionPlanEnd = missionPlanEnd;
	}

	public Long getMissionRealStart() {
		return missionRealStart;
	}

	public void setMissionRealStart(Long missionRealStart) {
		this.missionRealStart = missionRealStart;
	}

	public Long getMissionRealEnd() {
		return missionRealEnd;
	}

	public void setMissionRealEnd(Long missionRealEnd) {
		this.missionRealEnd = missionRealEnd;
	}

	public byte getMissionProjectPercent() {
		return missionProjectPercent;
	}

	public void setMissionProjectPercent(byte missionProjectPercent) {
		this.missionProjectPercent = missionProjectPercent;
	}

	public String getMissionIsSingleton() {
		return missionIsSingleton;
	}

	public void setMissionIsSingleton(String missionIsSingleton) {
		this.missionIsSingleton = missionIsSingleton;
	}

	public Project getMissionProject() {
		return missionProject;
	}

	public void setMissionProject(Project missionProject) {
		this.missionProject = missionProject;
	}

	public User getMissionMaster() {
		return missionMaster;
	}

	public void setMissionMaster(User missionMaster) {
		this.missionMaster = missionMaster;
	}

	public User getMissionSlave() {
		return missionSlave;
	}

	public void setMissionSlave(User missionSlave) {
		this.missionSlave = missionSlave;
	}

	public List<User> getMissionStaff() {
		return missionStaff;
	}

	public void setMissionStaff(List<User> missionStaff) {
		this.missionStaff = missionStaff;
	}

	@Transient
	public String getMissionStaffNames() throws Exception {
		return CollectionUtil.fetchPropertyToString(this.getAccount(),
				missionStaff, "staffName", ",");
	}

	@Transient
	public List<Long> getMissionStaffIds() throws Exception {
		return CollectionUtil.fetchPropertyToList(this.getAccount(),
				missionStaff, "staffId");
	}

	public Period getMissionPeriod() {
		return missionPeriod;
	}

	public void setMissionPeriod(Period missionPeriod) {
		this.missionPeriod = missionPeriod;
	}

	/**
	 * 显示接受时间间隔
	 * 
	 * @return
	 */
	public int getMissionAcceptDay() {
		if (missionAcceptTime != null) {
			Long interval = missionAcceptTime - getCreateTime();
			missionAcceptDay = (int) (interval / com.weaforce.core.util.Global.INTERVAL_DAY);
			missionAcceptDay += (interval % com.weaforce.core.util.Global.INTERVAL_DAY) > 0 ? 1
					: 0;
		}
		return missionAcceptDay;
	}

	/**
	 * 显示完成时间间隔
	 * 
	 * @return
	 */
	public int getMissionOkDay() {
		if (missionOkTime != null) {
			Long interval = missionOkTime - missionAcceptTime;
			missionOkDay = (int) (interval / com.weaforce.core.util.Global.INTERVAL_DAY);
			missionOkDay += (interval % com.weaforce.core.util.Global.INTERVAL_DAY) > 0 ? 1
					: 0;
		}
		return missionOkDay;
	}

	/**
	 * 显示确认时间间隔
	 * 
	 * @return
	 */
	public int getMissionConfirmDay() {
		if (missionConfirmTime != null) {
			Long interval = missionConfirmTime - missionOkTime;
			missionConfirmDay = (int) (interval / com.weaforce.core.util.Global.INTERVAL_DAY);
			missionConfirmDay += (interval % com.weaforce.core.util.Global.INTERVAL_DAY) > 0 ? 1
					: 0;
		}
		return missionConfirmDay;
	}

	/**
	 * 以W3C格式显示计划开始时间
	 * 
	 * @return
	 */
	public String getMissionPlanStartDate() {
		if (missionPlanStartDate == null)
			missionPlanStartDate = DateUtil.defaultFormat(new Date(
					missionPlanStart));
		return missionPlanStartDate;
	}

	/**
	 * 以UTC格式保存计划开始时间
	 * 
	 * @param missionPlanStartDate
	 */
	public void setMissionPlanStartDate(String missionPlanStartDate) {
		if (StringUtils.isNotEmpty(missionPlanStartDate))
			missionPlanStart = DateUtil.getUTCDate(missionPlanStartDate);
		this.missionPlanStartDate = missionPlanStartDate;
	}

	/**
	 * 以W3C格式显示计划结束时间
	 * 
	 * @return
	 */
	public String getMissionPlanEndDate() {
		if (missionPlanEndDate == null)
			missionPlanEndDate = DateUtil
					.defaultFormat(new Date(missionPlanEnd));
		return missionPlanEndDate;
	}

	/**
	 * 以UTC格式保存计划结束时间
	 * 
	 * @param missionPlanEndDate
	 */
	public void setMissionPlanEndDate(String missionPlanEndDate) {
		if (StringUtils.isNotEmpty(missionPlanEndDate))
			missionPlanEnd = DateUtil.getUTCDate(missionPlanEndDate);
		this.missionPlanEndDate = missionPlanEndDate;
	}

	/**
	 * 以W3C格式显示实际开始时间
	 * 
	 * @return
	 */
	public String getMissionRealStartDate() {
		if (missionRealStartDate == null)
			missionRealStartDate = DateUtil.defaultFormat(new Date(
					missionRealStart));
		return missionRealStartDate;
	}

	/**
	 * 以UTC格式保存实际开始时间
	 * 
	 * @param missionRealStartDate
	 */
	public void setMissionRealStartDate(String missionRealStartDate) {
		if (StringUtils.isNotEmpty(missionRealStartDate))
			missionRealStart = DateUtil.getUTCDate(missionRealStartDate);
		this.missionRealStartDate = missionRealStartDate;
	}

	/**
	 * 以W3C格式显示实际结束时间
	 * 
	 * @return
	 */
	public String getMissionRealEndDate() {
		if (missionRealEndDate == null)
			missionRealEndDate = DateUtil
					.defaultFormat(new Date(missionRealEnd));
		return missionRealEndDate;
	}

	/**
	 * 以UTC格式保存实际结束时间
	 * 
	 * @param missionRealEndDate
	 */
	public void setMissionRealEndDate(String missionRealEndDate) {
		if (StringUtils.isNotEmpty(missionRealEndDate))
			missionRealEnd = DateUtil.getUTCDate(missionRealEndDate);
		this.missionRealEndDate = missionRealEndDate;
	}

	public String getGanttWidthPlanBlank() {
		return ganttWidthPlanBlank;
	}

	public void setGanttWidthPlanBlank(String ganttWidthPlanBlank) {
		this.ganttWidthPlanBlank = ganttWidthPlanBlank;
	}

	public String getGanttWidthPlanValue() {
		return ganttWidthPlanValue;
	}

	public void setGanttWidthPlanValue(String ganttWidthPlanValue) {
		this.ganttWidthPlanValue = ganttWidthPlanValue;
	}

	public String getGanttWidthRealBlank() {
		return ganttWidthRealBlank;
	}

	public void setGanttWidthRealBlank(String ganttWidthRealBlank) {
		this.ganttWidthRealBlank = ganttWidthRealBlank;
	}

	public String getGanttWidthRealValue() {
		return ganttWidthRealValue;
	}

	public void setGanttWidthRealValue(String ganttWidthRealValue) {
		this.ganttWidthRealValue = ganttWidthRealValue;
	}

	public String getMissionMasterName() {
		return missionMasterName;
	}

	public void setMissionMasterName(String missionMasterName) {
		this.missionMasterName = missionMasterName;
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

	public List<MissionNote> getMissionNote() {
		return missionNote;
	}

	public void setMissionNote(List<MissionNote> missionNote) {
		this.missionNote = missionNote;
	}

	public List<MissionScore> getMissionScore() {
		return missionScore;
	}

	public void setMissionScore(List<MissionScore> missionScore) {
		this.missionScore = missionScore;
	}

}
