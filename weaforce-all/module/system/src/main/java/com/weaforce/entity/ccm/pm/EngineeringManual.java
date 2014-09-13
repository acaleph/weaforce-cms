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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;

@Entity
@Table(name = "co_engineering_manual")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class EngineeringManual extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -5640833421277243667L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "62", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "MANUAL_ID", length = 20)
	private Long manualId;
	// 标题
	@Column(name = "MANUAL_TITLE", length = 45)
	private String manualTitle;
	// 内容
	@Column(name = "MANUAL_CONTENT")
	private String manualContent;
	// 工程
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinColumn(name = "MANUAL_ENGINEERING_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_MANUAL_ENGINEERING_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Engineering manualEngineering;

	public Long getManualId() {
		return manualId;
	}

	public void setManualId(Long manualId) {
		this.manualId = manualId;
	}

	public String getManualTitle() {
		return manualTitle;
	}

	public void setManualTitle(String manualTitle) {
		this.manualTitle = manualTitle;
	}

	public String getManualContent() {
		return manualContent;
	}

	public void setManualContent(String manualContent) {
		this.manualContent = manualContent;
	}

	public Engineering getManualEngineering() {
		return manualEngineering;
	}

	public void setManualEngineering(Engineering manualEngineering) {
		this.manualEngineering = manualEngineering;
	}

}
