package com.weaforce.system.entity.help;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;

@Entity
@Table(name = "base_help_tip")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class HelpTip extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -5924732745161133755L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "37", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "TIP_ID", length = 20)
	private Long tipId;
	@Column(name = "TIP_MODULE_ID", length = 20)
	private Long tipModuleId;
	@Column(name = "TIP_ITEM_ID", length = 20)
	private Long tipItemId;
	@Column(name = "TIP_TITLE", length = 180)
	private String tipTitle;
	@Column(name = "TIP_INFO", length = 255)
	private String tipInfo;
	@Column(name = "TIP_NEXT_ID", length = 20)
	private Long tipNextId;

	public HelpTip() {

	}

	public Long getTipId() {
		return tipId;
	}

	public void setTipId(Long tipId) {
		this.tipId = tipId;
	}

	public Long getTipModuleId() {
		return tipModuleId;
	}

	public void setTipModuleId(Long tipModuleId) {
		this.tipModuleId = tipModuleId;
	}

	public Long getTipItemId() {
		return tipItemId;
	}

	public void setTipItemId(Long tipItemId) {
		this.tipItemId = tipItemId;
	}

	public String getTipTitle() {
		return tipTitle;
	}

	public void setTipTitle(String tipTitle) {
		this.tipTitle = tipTitle;
	}

	public String getTipInfo() {
		return tipInfo;
	}

	public void setTipInfo(String tipInfo) {
		this.tipInfo = tipInfo;
	}

	public Long getTipNextId() {
		return tipNextId;
	}

	public void setTipNextId(Long tipNextId) {
		this.tipNextId = tipNextId;
	}

}
