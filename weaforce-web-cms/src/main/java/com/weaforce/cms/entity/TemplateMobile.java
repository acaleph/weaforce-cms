package com.weaforce.cms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;

@Entity
@Table(name = "cms_template_mobile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Proxy(lazy = true)
public class TemplateMobile extends PrimaryEntity implements Serializable{
	private static final long serialVersionUID = 4113791704927229634L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "185", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "TEMPLATE_ID", length = 20)
	private Long templateId;
	// 名称
	@Column(name = "TEMPLATE_NAME", length = 45)
	private String templateName;
	// 内容
	@Column(name = "TEMPLATE_CONTENT")
	private String templateContent;
	
	// Category
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "categoryTemplate", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<CategoryMobile> templateCategory = new ArrayList<CategoryMobile>();

	public TemplateMobile() {

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

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public List<CategoryMobile> getTemplateCategory() {
		return templateCategory;
	}

	public void setTemplateCategory(List<CategoryMobile> templateCategory) {
		this.templateCategory = templateCategory;
	}
	
	
	
}
