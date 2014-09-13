package com.weaforce.cms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;

@Entity
@Table(name = "cms_template")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = true)
public class Template extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -2769285611980608983L;

	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "31", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "TEMPLATE_ID", length = 20)
	private Long templateId;
	// 内容
	@Column(name = "TEMPLATE_CONTENT")
	private String templateContent;
	// 名称
	@Column(name = "TEMPLATE_NAME", length = 45)
	private String templateName;
	// 子模板关键字：替换父模板中的内容
	@Column(name = "TEMPLATE_KEY", length = 45)
	private String templateKey;
	// 描述
	@Column(name = "TEMPLATE_DESCRIPTION", length = 255)
	private String templateDescription;
	// 活动
	@Column(name = "TEMPLATE_IS_ACTIVE", length = 1)
	private String templateIsActive;
	// Keyworkds
	@Column(name = "TEMPLATE_KEYWORDS", length = 1)
	private String templateKeywords;
	// Title
	@Column(name = "TEMPLATE_TITLE", length = 1)
	private String templateTitle;
	// CSS or javascript file
	@Column(name = "TEMPLATE_FILE", length = 1)
	private String templateFile;
	// Javacript
	@Column(name = "TEMPLATE_SCRIPT", length = 1)
	private String templateScript;
	// Style
	@Column(name = "TEMPLATE_STYLE", length = 1)
	private String templateStyle;
	// Type
	@Enumerated
	@Column(name = "TEMPLATE_TYPE")
	private String templateType;
	// 频道
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "channelTemplate", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Channel> templateChannel = new ArrayList<Channel>();
	// 栏目
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "categoryTemplate", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Category> templateCategory = new ArrayList<Category>();

	@Transient
	private boolean selected;
	@Transient
	private String typeId = "0";

	public Template() {
		templateIsActive = "1";
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateKey() {
		return templateKey;
	}

	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}

	public String getTemplateDescription() {
		return templateDescription;
	}

	public void setTemplateDescription(String templateDescription) {
		this.templateDescription = templateDescription;
	}

	public String getTemplateIsActive() {
		return templateIsActive;
	}

	public void setTemplateIsActive(String templateIsActive) {
		this.templateIsActive = templateIsActive;
	}

	public List<Channel> getTemplateChannel() {
		return templateChannel;
	}

	public void setTemplateChannel(List<Channel> templateChannel) {
		this.templateChannel = templateChannel;
	}

	public String getTemplateKeywords() {
		return templateKeywords;
	}

	public void setTemplateKeywords(String templateKeywords) {
		this.templateKeywords = templateKeywords;
	}

	public String getTemplateTitle() {
		return templateTitle;
	}

	public void setTemplateTitle(String templateTitle) {
		this.templateTitle = templateTitle;
	}

	public String getTemplateFile() {
		return templateFile;
	}

	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}

	public String getTemplateScript() {
		return templateScript;
	}

	public void setTemplateScript(String templateScript) {
		this.templateScript = templateScript;
	}

	public String getTemplateStyle() {
		return templateStyle;
	}

	public void setTemplateStyle(String templateStyle) {
		this.templateStyle = templateStyle;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public List<Category> getTemplateCategory() {
		return templateCategory;
	}

	public void setTemplateCategory(List<Category> templateCategory) {
		this.templateCategory = templateCategory;
	}

	public String toString() {
		return new ToStringBuilder(this).append("templateId", getTemplateId())
				.toString();
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}