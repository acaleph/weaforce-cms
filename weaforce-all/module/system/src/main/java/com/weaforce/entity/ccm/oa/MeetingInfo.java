package com.weaforce.entity.ccm.oa;

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

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.entity.admin.User;
import com.weaforce.core.util.CollectionUtil;
import com.weaforce.core.util.DateUtil;

@Entity
@Table(name = "fo_meeting_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class MeetingInfo extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -6085622300442930867L;

	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "25", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "INFO_ID", unique = false, nullable = false, updatable = false, length = 10)
	private Long infoId;
	// 标题
	@Column(name = "INFO_TITLE", length = 90)
	private String infoTitle;
	// 描述
	@Column(name = "INFO_DESCRIPTION", length = 255)
	private String infoDescription;
	// 计划开始日期
	@Column(name = "INFO_PLAN_DATE", length = 20)
	private Long infoPlanDate;
	// 实际开始日期
	@Column(name = "INFO_REAL_DATE", length = 20)
	private Long infoRealDate;
	// 计划开始时间
	@Column(name = "INFO_PLAN_START", length = 20)
	private Long infoPlanStart;
	// 计划结束时间
	@Column(name = "INFO_PLAN_END", length = 20)
	private Long infoPlanEnd;
	// 实际开始时间
	@Column(name = "INFO_REAL_START", length = 20)
	private Long infoRealStart;
	// 实际结束时间
	@Column(name = "INFO_REAL_END", length = 20)
	private Long infoRealEnd;
	// 是否公开
	@Column(name = "INFO_IS_PUBLIC", length = 1)
	private String infoIsPublic;
	// 是否活动
	@Column(name = "INFO_IS_ACTIVE", length = 1)
	private String infoIsActive;
	// 独占
	@Column(name = "INFO_IS_SINGLETON", length = 1)
	private String infoIsSingleton;
	// 内容
	@Column(name = "INFO_CONTENT")
	private String infoContent;
	// 主持人
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "INFO_HOST_STAFF_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_INFO_HOST_STAFF_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private User infoHost;
	// 会议室
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "INFO_ROOM_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_INFO_ROOM_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Room infoRoom;
	// 参与人员
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "fo_meeting_info_staff", joinColumns = { @JoinColumn(name = "INFO_ID") }, inverseJoinColumns = { @JoinColumn(name = "STAFF_ID") })
	@org.hibernate.annotations.ForeignKey(name = "FK_INFO_STAFF_ID", inverseName = "FK_STAFF_INFO_ID")
	@OrderBy("staffId")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<User> infoUser = new ArrayList<User>();
	// 会议记录
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "recordInfo")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<MeetingRecord> infoRecord = new ArrayList<MeetingRecord>();

	@Transient
	private Long startHour;
	@Transient
	private Long startMinute;
	@Transient
	private Long endHour;
	@Transient
	private Long endMinute;
	@Transient
	private String infoPlanDateDate;
	@Transient
	private String infoRealDateDate;
	@Transient
	private String infoRealStartDate;

	@Transient
	private String enableSave;
	@Transient
	private String enableDelete;

	public MeetingInfo() {
		this.infoIsPublic = "0";
		this.infoIsActive = "1";
		this.infoIsSingleton = "0";
	}

	public Long getInfoId() {
		return infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	public User getInfoHost() {
		return infoHost;
	}

	public void setInfoHost(User infoHost) {
		this.infoHost = infoHost;
	}

	public Room getInfoRoom() {
		return infoRoom;
	}

	public void setInfoRoom(Room infoRoom) {
		this.infoRoom = infoRoom;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}

	public String getInfoDescription() {
		return infoDescription;
	}

	public void setInfoDescription(String infoDescription) {
		this.infoDescription = infoDescription;
	}

	public String getInfoIsPublic() {
		return infoIsPublic;
	}

	public void setInfoIsPublic(String infoIsPublic) {
		this.infoIsPublic = infoIsPublic;
	}

	public String getInfoIsActive() {
		return infoIsActive;
	}

	public void setInfoIsActive(String infoIsActive) {
		this.infoIsActive = infoIsActive;
	}

	public String getInfoIsSingleton() {
		return infoIsSingleton;
	}

	public void setInfoIsSingleton(String infoIsSingleton) {
		this.infoIsSingleton = infoIsSingleton;
	}

	public String getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}

	

	public List<User> getInfoUser() {
		return infoUser;
	}

	public void setInfoUser(List<User> infoUser) {
		this.infoUser = infoUser;
	}

	@Transient
	public String getInfoStaffNames() throws Exception {
		return CollectionUtil.fetchPropertyToString(this.getAccount(),
				infoUser, "userNameCn", ",");
	}

	@Transient
	public List<Long> getInfoStaffIds() throws Exception {
		return CollectionUtil.fetchPropertyToList(this.getAccount(), infoUser,
				"userId");
	}

	/**
	 * 直接从持久层取得UTC日期
	 * 
	 * @return
	 */
	public Long getInfoPlanDate() {
		return infoPlanDate;
	}

	/**
	 * Action 从页面获取infoPlanDateDate W3C格式日期,转换为UTC日期,完成该属性持久化工作
	 * 
	 * @param infoPlanDate
	 */

	public void setInfoPlanDate(Long infoPlanDate) {
		this.infoPlanDate = infoPlanDate;
	}

	/**
	 * 直接从持久层取得UTC日期
	 * 
	 * @return
	 */
	public Long getInfoPlanStart() {
		return infoPlanStart;
	}

	/**
	 * Action 从页面获取infoPlanDateDate W3C格式日期 + Hour + Minute 转换为UTC日期,完成该属性持久化工作
	 * 
	 * @param infoPlanStart
	 */

	public void setInfoPlanStart(Long infoPlanStart) {
		this.infoPlanStart = infoPlanStart;
	}

	/**
	 * 将已经持久化的UTC日期,转换为W3C格式日期,供页面显示
	 * 
	 * @return
	 */

	public String getInfoPlanDateDate() {
		if (infoPlanDateDate == null)
			infoPlanDateDate = DateUtil.defaultFormat(new Date(infoPlanDate));
		return infoPlanDateDate;
	}

	/**
	 * 直接从页面取得W3C格式日期
	 * 
	 * @param infoPlanDateDate
	 *            计划开会日期
	 */

	public void setInfoPlanDateDate(String infoPlanDateDate) {
		if (StringUtils.isNotEmpty(infoPlanDateDate))
			infoPlanDate = DateUtil.getUTCDate(infoPlanDateDate);
		this.infoPlanDateDate = infoPlanDateDate;
	}

	/**
	 * 把infoPlanStart和infoPlanDate转换成小时,页面显示
	 * 
	 * @return
	 */
	public Long getStartHour() {
		// 变更前
		if (startHour == null)
			startHour = (infoPlanStart - infoPlanDate) / 60000 / 60;
		return startHour;
	}

	public void setStartHour(Long startHour) {
		this.startHour = startHour;
	}

	public Long getStartMinute() {
		// 变更前
		if (startMinute == null)
			startMinute = (infoPlanStart - infoPlanDate) / 60000 % 60;
		return startMinute;
	}

	public void setStartMinute(Long startMinute) {
		this.startMinute = startMinute;
	}

	public Long getEndHour() {
		// 变更前
		if (endHour == null)
			endHour = (infoPlanEnd - infoPlanDate) / 60000 / 60;
		return endHour;
	}

	public void setEndHour(Long endHour) {
		this.endHour = endHour;
	}

	public Long getEndMinute() {
		// 变更前
		if (endMinute == null)
			endMinute = (infoPlanEnd - infoPlanDate) / 60000 % 60;
		return endMinute;
	}

	public Long getInfoRealDate() {
		return infoRealDate;
	}

	public void setInfoRealDate(Long infoRealDate) {
		this.infoRealDate = infoRealDate;
	}

	public Long getInfoPlanEnd() {
		return infoPlanEnd;
	}

	public void setInfoPlanEnd(Long infoPlanEnd) {
		this.infoPlanEnd = infoPlanEnd;
	}

	public Long getInfoRealStart() {
		return infoRealStart;
	}

	public void setInfoRealStart(Long infoRealStart) {
		this.infoRealStart = infoRealStart;
	}

	public Long getInfoRealEnd() {
		return infoRealEnd;
	}

	public void setInfoRealEnd(Long infoRealEnd) {
		this.infoRealEnd = infoRealEnd;
	}

	public void setEndMinute(Long endMinute) {
		this.endMinute = endMinute;
	}

	public String getInfoRealDateDate() {
		if (infoRealDate != null && infoRealDate > 0)
			infoRealDateDate = DateUtil.defaultFormat(new Date(infoRealDate));
		return infoRealDateDate;
	}

	public void setInfoRealDateDate(String infoRealDateDate) {
		if (StringUtils.isNotEmpty(infoRealDateDate))
			this.infoRealDateDate = infoRealDateDate;
	}

	public String getInfoRealStartDate() {
		return infoRealStartDate;
	}

	public void setInfoRealStartDate(String infoRealStartDate) {
		if (StringUtils.isNotEmpty(infoRealStartDate))
			this.infoRealStartDate = infoRealStartDate;
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

	public List<MeetingRecord> getInfoRecord() {
		return infoRecord;
	}

	public void setInfoRecord(List<MeetingRecord> infoRecord) {
		this.infoRecord = infoRecord;
	}

}
