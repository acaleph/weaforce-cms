package com.weaforce.entity.ccm;

import java.io.Serializable;
import java.util.Date;

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

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.entity.PrimaryEntity;
import com.weaforce.core.util.DateUtil;
import com.weaforce.entity.finance.Period;
import com.weaforce.entity.system.User;

/**
 * 评分调整管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "co_strategy_score_tune")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class StrategyScoreTune extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 2065101832602357358L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "92", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "TUNE_ID", length = 20)
	private Long tuneId;
	// 标识
	@Column(name = "TUNE_FLAG", length = 1)
	private String tuneFlag;
	// 标题
	@Column(name = "TUNE_TITLE", length = 90)
	private String tuneTitle;
	// 值
	@Column(name = "TUNE_VALUE", length = 3)
	private Byte tuneValue;
	// 发生日期
	@Column(name = "TUNE_DATE", length = 20)
	private Long tuneDate;
	// 原因
	@Column(name = "TUNE_REASON", length = 255)
	private String tuneReason;
	// 账期
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "TUNE_PERIOD_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_TUNE_PERIOD_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Period tunePeriod;
	// 评分项目
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "TUNE_ITEM_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_TUNE_ITEM_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private StrategyItem tuneItem;
	// 负责人
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "TUNE_MASTER_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_TUNE_MASTER_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private User tuneMaster;

	@Transient
	private String tuneDateDate;

	public Long getTuneId() {
		return tuneId;
	}

	public void setTuneId(Long tuneId) {
		this.tuneId = tuneId;
	}

	public String getTuneFlag() {
		return tuneFlag;
	}

	public void setTuneFlag(String tuneFlag) {
		this.tuneFlag = tuneFlag;
	}

	public String getTuneTitle() {
		return tuneTitle;
	}

	public void setTuneTitle(String tuneTitle) {
		this.tuneTitle = tuneTitle;
	}

	public Byte getTuneValue() {
		return tuneValue;
	}

	public void setTuneValue(Byte tuneValue) {
		this.tuneValue = tuneValue;
	}

	public Long getTuneDate() {
		return tuneDate;
	}

	public void setTuneDate(Long tuneDate) {
		this.tuneDate = tuneDate;
	}

	public String getTuneReason() {
		return tuneReason;
	}

	public void setTuneReason(String tuneReason) {
		this.tuneReason = tuneReason;
	}

	public Period getTunePeriod() {
		return tunePeriod;
	}

	public void setTunePeriod(Period tunePeriod) {
		this.tunePeriod = tunePeriod;
	}

	public StrategyItem getTuneItem() {
		return tuneItem;
	}

	public void setTuneItem(StrategyItem tuneItem) {
		this.tuneItem = tuneItem;
	}

	public User getTuneMaster() {
		return tuneMaster;
	}

	public void setTuneMaster(User tuneMaster) {
		this.tuneMaster = tuneMaster;
	}

	/**
	 * 把UTC日期转换为W3C日期显示
	 * 
	 * @return
	 */
	public String getTuneDateDate() {
		if (tuneDateDate == null)
			tuneDateDate = DateUtil.defaultFormat(new Date(tuneDate));
		return tuneDateDate;
	}

	/**
	 * 把W3C日期转换为UTC日期保存
	 * 
	 * @param tuneDateDate
	 */
	public void setTuneDateDate(String tuneDateDate) {
		if (StringUtils.isNotEmpty(tuneDateDate))
		tuneDate = DateUtil.getUTCDate(tuneDateDate);
		this.tuneDateDate = tuneDateDate;
	}

}
