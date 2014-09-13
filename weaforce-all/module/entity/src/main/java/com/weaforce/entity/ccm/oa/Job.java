package com.weaforce.entity.ccm.oa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
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

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.core.util.CollectionUtil;
import com.weaforce.core.util.DateUtil;
import com.weaforce.entity.finance.Period;
import com.weaforce.entity.system.User;

@Entity
@Table(name = "fo_job")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Job extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -2255878035338566451L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "56", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "JOB_ID", length = 20)
	private Long jobId;
	// 标题
	@Column(name = "JOB_TITLE", length = 180)
	private String jobTitle;
	// 描述
	@Column(name = "JOB_DESCRIPTION", length = 255)
	private String jobDescription;
	// 内容
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "JOB_CONTENT")
	private String jobContent;
	// 标识（没有应用），考虑将来做例行工作标识
	@Column(name = "JOB_FLAG", length = 1)
	private String jobFlag;
	// 计划开始日期
	@Column(name = "JOB_PLAN_START", length = 20)
	private Long jobPlanStart;
	// 计划结束日期
	@Column(name = "JOB_PLAN_END", length = 20)
	private Long jobPlanEnd;
	// 实际开始日期
	@Column(name = "JOB_REAL_START", length = 20)
	private Long jobRealStart;
	// 实际结束日期
	@Column(name = "JOB_REAL_END", length = 20)
	private Long jobRealEnd;
	// 工作已经被接受:可以修改计划时间
	@Column(name = "JOB_IS_ACCEPT", length = 1)
	private String jobIsAccept;
	// 工作已经被确认:确认完成日期，确认之后，将不允许修改计划日期
	@Column(name = "JOB_IS_CONFIRM", length = 1)
	private String jobIsConfirm;
	// 确认完成:确认完成之后，将不允许修改任何内容
	@Column(name = "JOB_IS_OK", length = 1)
	private String jobIsOk;
	// 是否挂起
	@Column(name = "JOB_IS_SUSPEND", length = 1)
	private String jobIsSuspend;
	// 是否记分
	@Column(name = "JOB_IS_SCORE", length = 1)
	private String jobIsScore;
	// 挂起原因
	@Column(name = "JOB_SUSPEND_REASON", length = 255)
	private String jobSuspendReason;
	// 接受时间
	@Column(name = "JOB_ACCEPT_TIME", length = 20)
	private Long jobAcceptTime;
	// 完成时间
	@Column(name = "JOB_OK_TIME", length = 20)
	private Long jobOkTime;
	// 确认时间
	@Column(name = "JOB_CONFIRM_TIME", length = 20)
	private Long jobConfirmTime;
	// 统计时间
	@Column(name = "JOB_SCORE_TIME", length = 20)
	private Long jobScoreTime;
	// 挂起原因
	@Column(name = "JOB_SUSPEND_TIME", length = 20)
	private Long jobSuspendTime;
	// 活动
	@Column(name = "JOB_IS_ACTIVE", length = 1)
	private String jobIsActive;
	// 独占
	@Column(name = "JOB_IS_SINGLETON", length = 1)
	private String jobIsSingleton;
	// 负责人
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "JOB_MASTER_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_JOB_MASTER_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private User jobMaster;
	// 参与人:删除当前工作时,同时删除参与人,不能设置CascadeType.ALL
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "fo_job_staff", joinColumns = { @JoinColumn(name = "JOB_ID") }, inverseJoinColumns = { @JoinColumn(name = "STAFF_ID") })
	@org.hibernate.annotations.ForeignKey(name = "FK_JOB_STAFF_ID", inverseName = "FK_STAFF_JOB_ID")
	@OrderBy("staffId")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<User> jobStaff = new ArrayList<User>();
	// 评分：删除当前job时，将删除所有的评分记录
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "scoreJob", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<JobScore> jobScore = new ArrayList<JobScore>();
	// 工作记录：删除当前job时，将删除工作记录
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "noteJob", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<JobNote> jobNote = new ArrayList<JobNote>();
	// 账期
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "JOB_PERIOD_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_JOB_PERIOD_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Period jobPeriod;

	// 接受时间间隔
	@Transient
	private int jobAcceptDay;
	// 完成时间间隔
	@Transient
	private int jobOkDay;
	// 确认时间间隔
	@Transient
	private int jobConfirmDay;

	@Transient
	private String jobPlanStartDate;
	@Transient
	private String jobPlanEndDate;
	@Transient
	private String jobRealStartDate;
	@Transient
	private String jobRealEndDate;

	@Transient
	private String enableSave;
	@Transient
	private String enableDelete;

	public Job() {
		this.jobFlag = "0";
		this.jobIsAccept = "0";
		this.jobIsConfirm = "0";
		this.jobIsOk = "0";
		this.jobIsScore = "0";
		this.jobIsActive = "1";
		this.jobIsSingleton = "0";
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobContent() {
		return jobContent;
	}

	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}

	public String getJobFlag() {
		return jobFlag;
	}

	public void setJobFlag(String jobFlag) {
		this.jobFlag = jobFlag;
	}

	public Long getJobPlanStart() {
		return jobPlanStart;
	}

	public void setJobPlanStart(Long jobPlanStart) {
		this.jobPlanStart = jobPlanStart;
	}

	public Long getJobPlanEnd() {
		return jobPlanEnd;
	}

	public void setJobPlanEnd(Long jobPlanEnd) {
		this.jobPlanEnd = jobPlanEnd;
	}

	public Long getJobRealStart() {
		return jobRealStart;
	}

	public void setJobRealStart(Long jobRealStart) {
		this.jobRealStart = jobRealStart;
	}

	public Long getJobRealEnd() {
		return jobRealEnd;
	}

	public void setJobRealEnd(Long jobRealEnd) {
		this.jobRealEnd = jobRealEnd;
	}

	public String getJobIsAccept() {
		return jobIsAccept;
	}

	public void setJobIsAccept(String jobIsAccept) {
		this.jobIsAccept = jobIsAccept;
	}

	public String getJobIsConfirm() {
		return jobIsConfirm;
	}

	public void setJobIsConfirm(String jobIsConfirm) {
		this.jobIsConfirm = jobIsConfirm;
	}

	public String getJobIsOk() {
		return jobIsOk;
	}

	public void setJobIsOk(String jobIsOk) {
		this.jobIsOk = jobIsOk;
	}

	public String getJobIsSuspend() {
		return jobIsSuspend;
	}

	public void setJobIsSuspend(String jobIsSuspend) {
		this.jobIsSuspend = jobIsSuspend;
	}

	public String getJobIsScore() {
		return jobIsScore;
	}

	public void setJobIsScore(String jobIsScore) {
		this.jobIsScore = jobIsScore;
	}

	public String getJobSuspendReason() {
		return jobSuspendReason;
	}

	public void setJobSuspendReason(String jobSuspendReason) {
		this.jobSuspendReason = jobSuspendReason;
	}

	public Long getJobAcceptTime() {
		return jobAcceptTime;
	}

	public void setJobAcceptTime(Long jobAcceptTime) {
		this.jobAcceptTime = jobAcceptTime;
	}

	public Long getJobOkTime() {
		return jobOkTime;
	}

	public void setJobOkTime(Long jobOkTime) {
		this.jobOkTime = jobOkTime;
	}

	public Long getJobConfirmTime() {
		return jobConfirmTime;
	}

	public void setJobConfirmTime(Long jobConfirmTime) {
		this.jobConfirmTime = jobConfirmTime;
	}

	public Long getJobScoreTime() {
		return jobScoreTime;
	}

	public void setJobScoreTime(Long jobScoreTime) {
		this.jobScoreTime = jobScoreTime;
	}

	public Long getJobSuspendTime() {
		return jobSuspendTime;
	}

	public void setJobSuspendTime(Long jobSuspendTime) {
		this.jobSuspendTime = jobSuspendTime;
	}

	public String getJobIsActive() {
		return jobIsActive;
	}

	public void setJobIsActive(String jobIsActive) {
		this.jobIsActive = jobIsActive;
	}

	public String getJobIsSingleton() {
		return jobIsSingleton;
	}

	public void setJobIsSingleton(String jobIsSingleton) {
		this.jobIsSingleton = jobIsSingleton;
	}

	public User getJobMaster() {
		return jobMaster;
	}

	public void setJobMaster(User jobMaster) {
		this.jobMaster = jobMaster;
	}

	/**
	 * 显示接受时间间隔
	 * 
	 * @return
	 */
	public int getJobAcceptDay() {
		if (jobAcceptTime != null) {
			Long interval = jobAcceptTime - getCreateTime();
			jobAcceptDay = (int) (interval / com.weaforce.core.util.Global.INTERVAL_DAY);
			jobAcceptDay += (interval % com.weaforce.core.util.Global.INTERVAL_DAY) > 0 ? 1
					: 0;
		}
		return jobAcceptDay;
	}

	/**
	 * 显示完成时间间隔
	 * 
	 * @return
	 */
	public int getJobOkDay() {
		if (jobOkTime != null) {
			Long interval = jobOkTime - jobAcceptTime;
			jobOkDay = (int) (interval / com.weaforce.core.util.Global.INTERVAL_DAY);
			jobOkDay += (interval % com.weaforce.core.util.Global.INTERVAL_DAY) > 0 ? 1
					: 0;
		}
		return jobOkDay;
	}

	/**
	 * 显示确认时间间隔
	 * 
	 * @return
	 */
	public int getJobConfirmDay() {
		if (jobConfirmTime != null) {
			Long interval = jobConfirmTime - jobOkTime;
			jobConfirmDay = (int) (interval / com.weaforce.core.util.Global.INTERVAL_DAY);
			jobConfirmDay += (interval % com.weaforce.core.util.Global.INTERVAL_DAY) > 0 ? 1
					: 0;
		}
		return jobConfirmDay;
	}

	public Period getJobPeriod() {
		return jobPeriod;
	}

	public void setJobPeriod(Period jobPeriod) {
		this.jobPeriod = jobPeriod;
	}

	/**
	 * 以W3C格式显示计划开始日期
	 * 
	 * @return
	 */
	public String getJobPlanStartDate() {
		if (jobPlanStart != null)
			this.jobPlanStartDate = DateUtil.defaultFormat(new Date(
					jobPlanStart));
		return jobPlanStartDate;
	}

	/**
	 * 以UTC格式保存计划开始日期
	 * 
	 * @param jobPlanStartDate
	 */
	public void setJobPlanStartDate(String jobPlanStartDate) {
		if (StringUtils.isNotEmpty(jobPlanStartDate))
			this.jobPlanStart = DateUtil.getUTCDate(jobPlanStartDate);
		this.jobPlanStartDate = jobPlanStartDate;
	}

	/**
	 * 以W3C格式显示计划结束日期
	 * 
	 * @return
	 */
	public String getJobPlanEndDate() {
		if (jobPlanEnd != null)
			this.jobPlanEndDate = DateUtil.defaultFormat(new Date(jobPlanEnd));
		return jobPlanEndDate;
	}

	/**
	 * 以UTC格式保存计划结束日期
	 * 
	 * @param jobPlanEndDate
	 */
	public void setJobPlanEndDate(String jobPlanEndDate) {
		if (StringUtils.isNotEmpty(jobPlanEndDate))
			this.jobPlanEnd = DateUtil.getUTCDate(jobPlanEndDate);
		this.jobPlanEndDate = jobPlanEndDate;
	}

	/**
	 * 以W3C格式显示实际开始日期
	 * 
	 * @return
	 */
	public String getJobRealStartDate() {
		if (jobRealStart != null)
			jobRealStartDate = DateUtil.defaultFormat(new Date(jobRealStart));
		return jobRealStartDate;
	}

	/**
	 * 以UTC格式保存实际开始日期
	 * 
	 * @param jobRealStartDate
	 */
	public void setJobRealStartDate(String jobRealStartDate) {
		if (StringUtils.isNotEmpty(jobRealStartDate))
			this.jobRealStart = DateUtil.getUTCDate(jobRealStartDate);
		this.jobRealStartDate = jobRealStartDate;
	}

	/**
	 * 以W3C格式显示实际结束日期
	 * 
	 * @return
	 */
	public String getJobRealEndDate() {
		if (jobRealEnd != null)
			jobRealEndDate = DateUtil.defaultFormat(new Date(jobRealEnd));
		return jobRealEndDate;
	}

	/**
	 * 以UTC格式保存实际结束日期
	 * 
	 * @param jobRealEndDate
	 */
	public void setJobRealEndDate(String jobRealEndDate) {
		if (StringUtils.isNotEmpty(jobRealEndDate))
			this.jobRealEnd = DateUtil.getUTCDate(jobRealEndDate);
		this.jobRealEndDate = jobRealEndDate;
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

	public List<User> getJobStaff() {
		return jobStaff;
	}

	public void setJobStaff(List<User> jobStaff) {
		this.jobStaff = jobStaff;
	}

	public List<JobScore> getJobScore() {
		return jobScore;
	}

	public void setJobScore(List<JobScore> jobScore) {
		this.jobScore = jobScore;
	}

	@Transient
	public String getJobStaffNames() throws Exception {
		return CollectionUtil.fetchPropertyToString(this.getAccount(),
				jobStaff, "staffName", ",");
	}

	/**
	 * 取得参与人IDS
	 * 
	 * @return
	 * @throws Exception
	 */

	@Transient
	public List<Long> getJobStaffIds() throws Exception {
		return CollectionUtil.fetchPropertyToList(this.getAccount(), jobStaff,
				"staffId");
	}

	/**
	 * 取得参与人IDS,为了减少call db次数,增加传入参数
	 * 
	 * @param jobStaff
	 * @return
	 * @throws Exception
	 */
	@Transient
	public List<Long> getJobStaffIds(List<User> jobStaff) throws Exception {
		return CollectionUtil.fetchPropertyToList(this.getAccount(), jobStaff,
				"userId");
	}

	public List<JobNote> getJobNote() {
		return jobNote;
	}

	public void setJobNote(List<JobNote> jobNote) {
		this.jobNote = jobNote;
	}

}
