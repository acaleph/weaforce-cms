package com.weaforce.entity.ccm;

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

import com.weaforce.entity.admin.User;
import com.weaforce.entity.finance.Period;

/**
 * 账期评分统计管理类
 * <ul>
 * <li>scoreCountJob/scoreCountJobValue区别:1不计分</li>
 * <li>scoreCountMission/scoreCountMissionValue区别:1不计分</li>
 * </ul>
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "co_strategy_score")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class StrategyScore implements Serializable {
	private static final long serialVersionUID = -2832461832221951747L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "51", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "SCORE_ID", length = 20)
	private Long scoreId;
	// 本期工作(评分项目)得分
	@Column(name = "SCORE_VALUE_TOTAL_JOB", length = 10)
	private Integer scoreValueTotalJob;
	// 本期工作数量
	@Column(name = "SCORE_COUNT_JOB", length = 10)
	private Integer scoreCountJob;
	// 本期计分工作数量
	@Column(name = "SCORE_COUNT_JOB_VALUE", length = 10)
	private Integer scoreCountJobValue;
	// 本期任务(评分项目)得分
	@Column(name = "SCORE_VALUE_TOTAL_MISSION", length = 10)
	private Integer scoreValueTotalMission;
	// 本期任务数量
	@Column(name = "SCORE_COUNT_MISSION", length = 10)
	private Integer scoreCountMission;
	// 本期计分任务数量
	@Column(name = "SCORE_COUNT_MISSION_VALUE", length = 10)
	private Integer scoreCountMissionValue;
	// 上期仪表总分
	@Column(name = "SCORE_DIAL_VALUE_LAST_PERIOD", length = 10)
	private Integer scoreDialValueLastPeriod;
	// 本期仪表总分（上期总分 + 本期得分）
	@Column(name = "SCORE_DIAL_VALUE_CURRENT_PERIOD", length = 10)
	private Integer scoreDialValueCurrentPeriod;

	// 账期
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "SCORE_PERIOD_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_SCORE_PERIOD_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Period scorePeriod;
	// 评分项目
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "SCORE_STRATEGY_ITEM_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_SCORE_STRATEGY_ITEM_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private StrategyItem scoreItem;
	// 所有人
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "SCORE_MASTER_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_SCORE_MASTER_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private User scoreMaster;

	public StrategyScore() {
		scoreValueTotalJob = 0;
		scoreCountJob = 0;
		scoreCountJobValue = 0;
		scoreValueTotalMission = 0;
		scoreCountMission = 0;
		scoreCountMissionValue = 0;
		scoreDialValueLastPeriod = 0;
		scoreDialValueCurrentPeriod = 0;
	}

	public Long getScoreId() {
		return scoreId;
	}

	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
	}

	public Integer getScoreValueTotalJob() {
		return scoreValueTotalJob;
	}

	/**
	 * 直接累加当前账期job得分
	 * 
	 * @param scoreValueTotalJob
	 */
	public void setScoreValueTotalJob(Integer scoreValueTotalJob) {
		this.scoreValueTotalJob += scoreValueTotalJob;
	}

	/**
	 * 当前账期确认完成job数量
	 * 
	 * @return
	 */
	public Integer getScoreCountJob() {
		return scoreCountJob;
	}

	/**
	 * 直接累加job数量
	 * 
	 * @param scoreCountJob
	 */
	public void setScoreCountJob(Integer scoreCountJob) {
		this.scoreCountJob += scoreCountJob;
	}

	/**
	 * 当前账期确认完成计分job数量
	 * 
	 * @return
	 */
	public Integer getScoreCountJobValue() {
		return scoreCountJobValue;
	}

	/**
	 * 直接累加计分job数量
	 * 
	 * @param scoreCountJobValue
	 */
	public void setScoreCountJobValue(Integer scoreCountJobValue) {
		this.scoreCountJobValue += scoreCountJobValue;
	}

	public Integer getScoreValueTotalMission() {
		return scoreValueTotalMission;
	}

	/**
	 * 直接累加当前账期mission得分
	 * 
	 * @param scoreValueTotalMission
	 */
	public void setScoreValueTotalMission(Integer scoreValueTotalMission) {
		this.scoreValueTotalMission += scoreValueTotalMission;
	}

	/**
	 * 当前账期确认完成mission数量
	 * 
	 * @return
	 */
	public Integer getScoreCountMission() {
		return scoreCountMission;
	}

	/**
	 * 直接累加mission数量
	 * 
	 * @param scoreCountMission
	 */
	public void setScoreCountMission(Integer scoreCountMission) {
		this.scoreCountMission += scoreCountMission;
	}

	/**
	 * 当前账期确认完成计分mission数量
	 * 
	 * @return
	 */
	public Integer getScoreCountMissionValue() {
		return scoreCountMissionValue;
	}

	/**
	 * 直接累加计分mission数量
	 * 
	 * @param scoreCountMissionValue
	 */
	public void setScoreCountMissionValue(Integer scoreCountMissionValue) {
		this.scoreCountMissionValue += scoreCountMissionValue;
	}

	public Integer getScoreDialValueLastPeriod() {
		return scoreDialValueLastPeriod;
	}

	public void setScoreDialValueLastPeriod(Integer scoreDialValueLastPeriod) {
		this.scoreDialValueLastPeriod = scoreDialValueLastPeriod;
	}

	public Integer getScoreDialValueCurrentPeriod() {
		return scoreDialValueCurrentPeriod;
	}

	public void setScoreDialValueCurrentPeriod(
			Integer scoreDialValueCurrentPeriod) {
		this.scoreDialValueCurrentPeriod = scoreDialValueCurrentPeriod;
	}

	public Period getScorePeriod() {
		return scorePeriod;
	}

	public void setScorePeriod(Period scorePeriod) {
		this.scorePeriod = scorePeriod;
	}

	public StrategyItem getScoreItem() {
		return scoreItem;
	}

	public void setScoreItem(StrategyItem scoreItem) {
		this.scoreItem = scoreItem;
	}

	public User getScoreMaster() {
		return scoreMaster;
	}

	public void setScoreMaster(User scoreMaster) {
		this.scoreMaster = scoreMaster;
	}

}
