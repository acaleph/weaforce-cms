package com.weaforce.entity.app;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "admin_module")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Module implements Serializable {
	private static final long serialVersionUID = -5748882026584818886L;
	@Id
	@GeneratedValue
	@Column(name = "MODULE_ID", length = 20)
	private Long moduleId;
	// 英文名称
	@Column(name = "MODULE_NAME_EN", length = 45)
	private String moduleNameEn;
	// 步长
	@Column(name = "MODULE_STEP")
	private Long moduleStep;
	// 下一值
	@Column(name = "MODULE_NEXT")
	private Long moduleNext;
	// 描述
	@Column(name = "MODULE_DESCRIPTION", length = 255)
	private String moduleDescription;
	// 中文名称
	@Column(name = "MODULE_NAME_CN")
	private String moduleNameCn;
	// 字典
	@Column(name = "MODULE_DICTIONARY", length = 1)
	private String moduleDictionary;
	// 附件
	@Column(name = "MODULE_ATTACHMENT", length = 1)
	private String moduleAttachment;
	// 资源
	@Column(name = "MODULE_IS_RESOURCE", length = 1)
	private String moduleIsResource;
	// 模块
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "MODULE_BUSINESS_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_MODULE_BUSINESS_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Business moduleBusiness;

	public Module() {
		moduleStep = Long.valueOf("1");
		moduleNext = Long.valueOf("1");
		moduleDictionary = "1";
		moduleAttachment = "0";
		moduleIsResource = "1";
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleNameEn() {
		return moduleNameEn;
	}

	public void setModuleNameEn(String moduleNameEn) {
		this.moduleNameEn = moduleNameEn;
	}

	public Long getModuleStep() {
		return moduleStep;
	}

	public void setModuleStep(Long moduleStep) {
		this.moduleStep = moduleStep;
	}

	public Long getModuleNext() {
		return moduleNext;
	}

	public void setModuleNext(Long moduleNext) {
		this.moduleNext = moduleNext;
	}

	public String getModuleDescription() {
		return moduleDescription;
	}

	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}

	public String getModuleNameCn() {
		return moduleNameCn;
	}

	public void setModuleNameCn(String moduleNameCn) {
		this.moduleNameCn = moduleNameCn;
	}

	public String getModuleDictionary() {
		return moduleDictionary;
	}

	public void setModuleDictionary(String moduleDictionary) {
		this.moduleDictionary = moduleDictionary;
	}

	public String getModuleAttachment() {
		return moduleAttachment;
	}

	public void setModuleAttachment(String moduleAttachment) {
		this.moduleAttachment = moduleAttachment;
	}

	public String getModuleIsResource() {
		return moduleIsResource;
	}

	public void setModuleIsResource(String moduleIsResource) {
		this.moduleIsResource = moduleIsResource;
	}

	public Business getModuleBusiness() {
		return moduleBusiness;
	}

	public void setModuleBusiness(Business moduleBusiness) {
		this.moduleBusiness = moduleBusiness;
	}

}
