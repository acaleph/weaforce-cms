package com.weaforce.entity.system;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.util.CollectionUtil;
import com.weaforce.entity.app.Module;

/**
 * 资源权限管理类
 * 
 * @author acaleph8＠yahoo.com.cn
 * 
 */

@Entity
@Table(name = "admin_resource")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Resource implements Serializable {
	private static final long serialVersionUID = 1866650175299449404L;
	public static final String TYPE_URL = "url";
	public static final String TYPE_MENU = "menu";
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "93", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "RESOURCE_ID", length = 20)
	private Long resourceId;
	// 类型，默认为url
	@Column(name = "RESOURCE_TYPE", length = 10)
	private String resourceType;
	// 名称
	@Column(name = "RESOURCE_NAME", length = 45)
	private String resourceName;
	// 值
	@Column(name = "RESOURCE_VALUE", length = 90)
	private String resourceValue;
	// 排序
	@Column(name = "RESOURCE_GROUP_ORDER", length = 3)
	private Byte resourceGroupOrder;
	// 模块
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "RESOURCE_MODULE_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_RESOURCE_MODULE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Module resourceModule;
	// 对应授权
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "admin_resource_authority", joinColumns = { @JoinColumn(name = "RESOURCE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	@Fetch(FetchMode.JOIN)
	@org.hibernate.annotations.ForeignKey(name = "FK_RESOURCE_AUTHORITY_ID", inverseName = "FK_AUTHORITY_RESOURCE_ID")
	@OrderBy("authorityId")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<Authority> resourceAuthority = new LinkedHashSet<Authority>();
	
	@Transient
	private String checked;

	public Resource() {
		resourceType = TYPE_URL;
		checked = "0";
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceValue() {
		return resourceValue;
	}

	public void setResourceValue(String resourceValue) {
		this.resourceValue = resourceValue;
	}

	public Byte getResourceGroupOrder() {
		return resourceGroupOrder;
	}

	public void setResourceGroupOrder(Byte resourceGroupOrder) {
		this.resourceGroupOrder = resourceGroupOrder;
	}

	public Module getResourceModule() {
		return resourceModule;
	}

	public void setResourceModule(Module resourceModule) {
		this.resourceModule = resourceModule;
	}

	public Set<Authority> getResourceAuthority() {
		return resourceAuthority;
	}

	public void setResourceAuthority(Set<Authority> resourceAuthority) {
		this.resourceAuthority = resourceAuthority;
	}

	@Transient
	public String getResourceAuthorityNames() throws Exception {
		return CollectionUtil.fetchPropertyToString("", resourceAuthority,
				"authorityName", ",");

	}

	@Transient
	public String getResourceAuthorityCodes() throws Exception {
		return CollectionUtil.fetchPropertyToString("", resourceAuthority,
				"authorityCode", ",");
	}

	@Transient
	public List<Long> getResourceAuthorityIds() throws Exception {
		return CollectionUtil.fetchPropertyToList("", resourceAuthority,
				"authorityId");
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

}
