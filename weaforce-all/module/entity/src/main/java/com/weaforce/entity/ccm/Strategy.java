package com.weaforce.entity.ccm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.entity.PrimaryEntity;

/**
 * 管理策略定义类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "co_strategy")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Strategy extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 9023215245116630135L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "53", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "STRATEGY_ID", length = 20)
	private Long strategyId;
	// 标题
	@Column(name = "STRATEGY_TITLE", length = 90)
	private String strategyTitle;
	// 评分
	@Column(name = "STRATEGY_SCORE", length = 3)
	private Byte strategyScore;
	// 排序
	@Column(name = "STRATEGY_ORDER", length = 3)
	private String strategyOrder;
	// 描述
	@Column(name = "STRATEGY_DESCRIPTION", length = 255)
	private String strategyDescription;
	// 是否默认
	@Column(name = "STRATEGY_IS_DEFAULT", length = 1)
	private String strategyIsDefault;
	// 是否默认
	@Column(name = "STRATEGY_IS_ACTIVE", length = 1)
	private String strategyIsActive;
	// 策略内容
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "detailStrategy")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<StrategyDetail> strategyDetail = new ArrayList<StrategyDetail>();

	public Strategy() {
		// 默认为百分制
		strategyScore = 100;
		strategyIsDefault = "0";
		strategyIsActive = "1";
	}

	public Long getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(Long strategyId) {
		this.strategyId = strategyId;
	}

	public String getStrategyTitle() {
		return strategyTitle;
	}

	public void setStrategyTitle(String strategyTitle) {
		this.strategyTitle = strategyTitle;
	}

	public Byte getStrategyScore() {
		return strategyScore;
	}

	public void setStrategyScore(Byte strategyScore) {
		this.strategyScore = strategyScore;
	}

	public String getStrategyOrder() {
		return strategyOrder;
	}

	public void setStrategyOrder(String strategyOrder) {
		this.strategyOrder = strategyOrder;
	}

	public String getStrategyDescription() {
		return strategyDescription;
	}

	public void setStrategyDescription(String strategyDescription) {
		this.strategyDescription = strategyDescription;
	}

	public String getStrategyIsDefault() {
		return strategyIsDefault;
	}

	public void setStrategyIsDefault(String strategyIsDefault) {
		this.strategyIsDefault = strategyIsDefault;
	}

	public String getStrategyIsActive() {
		return strategyIsActive;
	}

	public void setStrategyIsActive(String strategyIsActive) {
		this.strategyIsActive = strategyIsActive;
	}

	public List<StrategyDetail> getStrategyDetail() {
		return strategyDetail;
	}

	public void setStrategyDetail(List<StrategyDetail> strategyDetail) {
		this.strategyDetail = strategyDetail;
	}

}
