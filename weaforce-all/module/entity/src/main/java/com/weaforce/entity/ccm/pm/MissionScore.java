package com.weaforce.entity.ccm.pm;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.weaforce.entity.ccm.StrategyDetail;

/**
 * 任务评分管理对象
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "fp_mission_score")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class MissionScore implements Serializable {

	private static final long serialVersionUID = 7661554652726759995L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "66", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "SCORE_ID", length = 20)
	private Long scoreId;
	// 计划得分
	@Column(name = "SCORE_PLAN", length = 3)
	private Byte scorePlan;
	// 实际得分
	@Column(name = "SCORE_REAL", length = 3)
	private Byte scoreReal;
	// 任务
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "SCORE_MISSION_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_SCORE_MISSION_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Mission scoreMission;
	// 评分策略内容
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "SCORE_DETAIL_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_MISSION_SCORE_DETAIL_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private StrategyDetail scoreDetail;

	public MissionScore() {
		scorePlan = 1;
		scoreReal = 1;
	}

	public Long getScoreId() {
		return scoreId;
	}

	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
	}

	public Byte getScorePlan() {
		return scorePlan;
	}

	public void setScorePlan(Byte scorePlan) {
		this.scorePlan = scorePlan;
	}

	public Byte getScoreReal() {
		return scoreReal;
	}

	public void setScoreReal(Byte scoreReal) {
		this.scoreReal = scoreReal;
	}

	public Mission getScoreMission() {
		return scoreMission;
	}

	public void setScoreMission(Mission scoreMission) {
		this.scoreMission = scoreMission;
	}

	public StrategyDetail getScoreDetail() {
		return scoreDetail;
	}

	public void setScoreDetail(StrategyDetail scoreDetail) {
		this.scoreDetail = scoreDetail;
	}

}
