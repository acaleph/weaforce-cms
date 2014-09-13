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
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;

/**
 * 评分策略内容类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "co_strategy_detail")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class StrategyDetail extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 2447017522613530592L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "50", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "DETAIL_ID", length = 20)
	private Long detailId;
	// 所占比例
	@Column(name = "DETAIL_PERCENT", length = 3)
	private byte detailPercent;
	// 排序
	@Column(name = "DETAIL_ORDER", length = 3)
	private byte detailOrder;
	// 描述
	@Column(name = "DETAIL_DESCRIPTION", length = 255)
	private String detailDescription;
	// 工作数量加权值
	@Column(name = "DETAIL_JOB_SCORE_COUNT", length = 3)
	private Byte detailJobScoreCount;
	// 工作得分加权值
	@Column(name = "DETAIL_JOB_SCORE_VALUE", length = 3)
	private Byte detailJobScoreValue;
	// 任务数量加权值
	@Column(name = "DETAIL_MISSION_SCORE_COUNT", length = 3)
	private Byte detailMissionScoreCount;
	// 任务得分加权值
	@Column(name = "DETAIL_MISSION_SCORE_VALUE", length = 3)
	private Byte detailMissionScoreValue;
	// 评分策略
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "DETAIL_STRATEGY_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_DETAIL_STRATEGY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Strategy detailStrategy;
	// 评分项目
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "DETAIL_ITEM_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_DETAIL_ITEM_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private StrategyItem detailItem;

	@Transient
	private Byte scorePlanValue; // Default is 1
	@Transient
	private Byte scoreRealValue; // Default is 1
	@Transient
	private Integer scoreRealValueJobTotal; // 总得分
	@Transient
	private Integer scoreRealValueJobAverage; // 平均分
	@Transient
	private Integer scoreRealValueMissionTotal; // 总得分
	@Transient
	private Integer scoreRealValueMissionAverage; // 平均分

	public StrategyDetail() {
		scorePlanValue = 1;
		scoreRealValue = 1;
		detailJobScoreCount = 0;
		detailJobScoreValue = 0;
		detailMissionScoreCount = 0;
		detailMissionScoreValue = 0;
	}

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public byte getDetailPercent() {
		return detailPercent;
	}

	public void setDetailPercent(byte detailPercent) {
		this.detailPercent = detailPercent;
	}

	public byte getDetailOrder() {
		return detailOrder;
	}

	public void setDetailOrder(byte detailOrder) {
		this.detailOrder = detailOrder;
	}

	public Byte getDetailJobScoreCount() {
		return detailJobScoreCount;
	}

	public void setDetailJobScoreCount(Byte detailJobScoreCount) {
		this.detailJobScoreCount = detailJobScoreCount;
	}

	public Byte getDetailJobScoreValue() {
		return detailJobScoreValue;
	}

	public void setDetailJobScoreValue(Byte detailJobScoreValue) {
		this.detailJobScoreValue = detailJobScoreValue;
	}

	public Byte getDetailMissionScoreCount() {
		return detailMissionScoreCount;
	}

	public void setDetailMissionScoreCount(Byte detailMissionScoreCount) {
		this.detailMissionScoreCount = detailMissionScoreCount;
	}

	public Byte getDetailMissionScoreValue() {
		return detailMissionScoreValue;
	}

	public void setDetailMissionScoreValue(Byte detailMissionScoreValue) {
		this.detailMissionScoreValue = detailMissionScoreValue;
	}

	public String getDetailDescription() {
		return detailDescription;
	}

	public void setDetailDescription(String detailDescription) {
		this.detailDescription = detailDescription;
	}

	public Strategy getDetailStrategy() {
		return detailStrategy;
	}

	public void setDetailStrategy(Strategy detailStrategy) {
		this.detailStrategy = detailStrategy;
	}

	public Byte getScorePlanValue() {
		return scorePlanValue;
	}

	public void setScorePlanValue(Byte scorePlanValue) {
		this.scorePlanValue = scorePlanValue;
	}

	public Byte getScoreRealValue() {
		return scoreRealValue;
	}

	public void setScoreRealValue(Byte scoreRealValue) {
		this.scoreRealValue = scoreRealValue;
	}

	public Integer getScoreRealValueJobTotal() {
		return scoreRealValueJobTotal;
	}

	/**
	 * 直接累加新的值(Job)
	 * 
	 * @param scoreRealValueJobTotal
	 */
	public void setScoreRealValueJobTotal(Integer scoreRealValueJobTotal) {
		if (this.scoreRealValueJobTotal == null)
			this.scoreRealValueJobTotal = 0;
		this.scoreRealValueJobTotal += scoreRealValueJobTotal;
	}

	public Integer getScoreRealValueJobAverage() {
		return scoreRealValueJobAverage;
	}

	public void setScoreRealValueJobAverage(Integer scoreRealValueJobAverage) {
		this.scoreRealValueJobAverage = scoreRealValueJobAverage;
	}

	public Integer getScoreRealValueMissionTotal() {
		return scoreRealValueMissionTotal;
	}

	/**
	 * 直接累加新的值(Mission)
	 * 
	 * @param scoreRealValueMissionTotal
	 */
	public void setScoreRealValueMissionTotal(Integer scoreRealValueMissionTotal) {
		if (this.scoreRealValueMissionTotal == null)
			this.scoreRealValueMissionTotal = 0;
		this.scoreRealValueMissionTotal += scoreRealValueMissionTotal;
	}

	public Integer getScoreRealValueMissionAverage() {
		return scoreRealValueMissionAverage;
	}

	public void setScoreRealValueMissionAverage(
			Integer scoreRealValueMissionAverage) {
		this.scoreRealValueMissionAverage = scoreRealValueMissionAverage;
	}

	public StrategyItem getDetailItem() {
		return detailItem;
	}

	public void setDetailItem(StrategyItem detailItem) {
		this.detailItem = detailItem;
	}

}
