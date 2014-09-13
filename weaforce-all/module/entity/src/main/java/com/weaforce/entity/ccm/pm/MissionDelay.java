package com.weaforce.entity.ccm.pm;

import java.io.Serializable;

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
 * 任务超时管理对象类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "fp_mission_delay")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class MissionDelay extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -5289288242334840648L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "52", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "DELAY_ID", length = 20)
	private Long delayId;
	@Column(name = "DELAY_TITLE", length = 90)
	private String delayTitle;
	@Column(name = "DELAY_PLAN_START", length = 20)
	private Long delayPlanStart;
	@Column(name = "DELAY_PLAN_END", length = 20)
	private Long delayPlanEnd;
	@Column(name = "DELAY_REAL_START", length = 20)
	private Long delayRealStart;
	@Column(name = "DELAY_REAL_END", length = 20)
	private Long delayRealEnd;
	@Column(name = "DELAY_DESCRIPTION", length = 255)
	private String delayDescription;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "DELAY_MISSION_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_DELAY_MISSION_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Mission delayMission;

	public MissionDelay() {

	}

	public Long getDelayId() {
		return delayId;
	}

	public void setDelayId(Long delayId) {
		this.delayId = delayId;
	}

	public String getDelayTitle() {
		return delayTitle;
	}

	public void setDelayTitle(String delayTitle) {
		this.delayTitle = delayTitle;
	}

	public Long getDelayPlanStart() {
		return delayPlanStart;
	}

	public void setDelayPlanStart(Long delayPlanStart) {
		this.delayPlanStart = delayPlanStart;
	}

	public Long getDelayPlanEnd() {
		return delayPlanEnd;
	}

	public void setDelayPlanEnd(Long delayPlanEnd) {
		this.delayPlanEnd = delayPlanEnd;
	}

	public Long getDelayRealStart() {
		return delayRealStart;
	}

	public void setDelayRealStart(Long delayRealStart) {
		this.delayRealStart = delayRealStart;
	}

	public Long getDelayRealEnd() {
		return delayRealEnd;
	}

	public void setDelayRealEnd(Long delayRealEnd) {
		this.delayRealEnd = delayRealEnd;
	}

	public String getDelayDescription() {
		return delayDescription;
	}

	public void setDelayDescription(String delayDescription) {
		this.delayDescription = delayDescription;
	}

	public Mission getDelayMission() {
		return delayMission;
	}

	public void setDelayMission(Mission delayMission) {
		this.delayMission = delayMission;
	}

}
