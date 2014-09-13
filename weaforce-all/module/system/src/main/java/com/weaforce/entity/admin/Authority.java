package com.weaforce.entity.admin;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.entity.app.Business;

/**
 * 访问授权管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "admin_authority")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Authority implements Serializable {
	private static final long serialVersionUID = -4766471409776134160L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "55", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "AUTHORITY_ID", length = 20)
	private Long authorityId;
	// 名称
	@Column(name = "AUTHORITY_NAME", length = 45)
	private String authorityName;
	// 代码
	@Column(name = "AUTHORITY_CODE", length = 20)
	private String authorityCode;
	// 描述
	@Column(name = "AUTHORITY_DESCRIPTION", length = 255)
	private String authorityDescription;
	// 系统
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHORITY_BUSINESS_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_AUTHORITY_BUSINESS_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Business authorityBusiness;
	// 角色
	@ManyToMany(mappedBy = "roleAuthority", targetEntity = Role.class, fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Role> authorityRole;
	// 资源
	@ManyToMany(mappedBy = "resourceAuthority", targetEntity = Resource.class, fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Resource> authorityResource;

	@Transient
	private Boolean checked;

	public Authority() {
	}

	public Long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getAuthorityDescription() {
		return authorityDescription;
	}

	public void setAuthorityDescription(String authorityDescription) {
		this.authorityDescription = authorityDescription;
	}

	public Business getAuthorityBusiness() {
		return authorityBusiness;
	}

	public void setAuthorityBusiness(Business authorityBusiness) {
		this.authorityBusiness = authorityBusiness;
	}

	public Set<Role> getAuthorityRole() {
		return authorityRole;
	}

	public void setAuthorityRole(Set<Role> authorityRole) {
		this.authorityRole = authorityRole;
	}

	public Set<Resource> getAuthorityResource() {
		return authorityResource;
	}

	public void setAuthorityResource(Set<Resource> authorityResource) {
		this.authorityResource = authorityResource;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}
