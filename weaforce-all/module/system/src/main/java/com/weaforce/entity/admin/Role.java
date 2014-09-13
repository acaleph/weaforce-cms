package com.weaforce.entity.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.core.util.CollectionUtil;
import com.weaforce.entity.app.Menu;

@Entity
@Table(name = "admin_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = true)
public class Role extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 6175202183332610630L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "2", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "ROLE_ID", length = 20)
	private Long roleId;
	// 名称
	@Column(name = "ROLE_NAME", nullable = false, length = 45)
	private String roleName;
	// 菜单rank
	@Column(name = "ROLE_MENU_RANK", nullable = false, length = 255)
	private String roleMenuRank;
	// Bootstrap menu content on the left
	@Column(name = "ROLE_MENU_CONTENT", length = 1000)
	private String roleMenuContent;
	// CMS categories
	@Column(name = "ROLE_CATEGORY", length = 1000)
	private String roleCategory;
	// 类型
	@Column(name = "ROLE_TYPE", nullable = false, length = 1)
	private String roleType;
	// 活动
	@Column(name = "ROLE_IS_ACTIVE", length = 1)
	private String roleIsActive;
	// 系统角色
	@Column(name = "ROLE_IS_SYSTEM", length = 1)
	private String roleIsSystem;
	// 描述
	@Column(name = "ROLE_DESCRIPTION", length = 255)
	private String roleDescription;
	// 当前角色下的用户 list
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "defaultUserRole")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<User> roleUser = new ArrayList<User>();
	// 授权
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "admin_role_authority", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	@Fetch(FetchMode.JOIN)
	@org.hibernate.annotations.ForeignKey(name = "FK_ROLE_AUTHORITY_ID", inverseName = "FK_AUTHORITY_ROLE_ID")
	@OrderBy("authorityId")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Authority> roleAuthority = new LinkedHashSet<Authority>();
	// Menus
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "admin_role_menu", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "MENU_ID") })
	@Fetch(FetchMode.JOIN)
	@org.hibernate.annotations.ForeignKey(name = "FK_ROLE_MENU_ID", inverseName = "FK_MENU_ROLE_ID")
	@OrderBy("menuParent,menuGroupOrder")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Menu> roleMenu = new LinkedHashSet<Menu>();

	@Transient
	private boolean selected;
	@Transient
	private boolean checked;

	public Role() {
		this.roleType = "1";
		this.roleIsActive = "1";
		this.roleIsSystem = "0";
		this.roleMenuRank = "";
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleMenuRank() {
		return roleMenuRank;
	}

	public void setRoleMenuRank(String roleMenuRank) {
		this.roleMenuRank = roleMenuRank;
	}

	public String getRoleType() {
		return roleType;
	}

	public String getRoleMenuContent() {
		return roleMenuContent;
	}

	public void setRoleMenuContent(String roleMenuContent) {
		this.roleMenuContent = roleMenuContent;
	}

	public String getRoleCategory() {
		return roleCategory;
	}

	public void setRoleCategory(String roleCategory) {
		this.roleCategory = roleCategory;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleIsActive() {
		return roleIsActive;
	}

	public void setRoleIsActive(String roleIsActive) {
		this.roleIsActive = roleIsActive;
	}

	public String getRoleIsSystem() {
		return roleIsSystem;
	}

	public void setRoleIsSystem(String roleIsSystem) {
		this.roleIsSystem = roleIsSystem;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public List<User> getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(List<User> roleUser) {
		this.roleUser = roleUser;
	}

	public Set<Authority> getRoleAuthority() {
		return roleAuthority;
	}

	public void setRoleAuthority(Set<Authority> roleAuthority) {
		this.roleAuthority = roleAuthority;
	}

	/**
	 * 取得已授权 name list
	 * 
	 * @return
	 * @throws Exception
	 */
	@Transient
	public String getRoleAuthorityNames() throws Exception {
		return CollectionUtil.fetchPropertyToString(this.getAccount(),
				roleAuthority, "authorityName", ",");

	}

	/**
	 * 取得已授权 id list
	 * 
	 * @return
	 * @throws Exception
	 */
	@Transient
	public List<Long> getRoleAuthorityIds() throws Exception {
		return CollectionUtil.fetchPropertyToList(this.getAccount(),
				roleAuthority, "authorityId");
	}

	public Set<Menu> getRoleMenu() {
		return roleMenu;
	}

	public void setRoleMenu(Set<Menu> roleMenu) {
		this.roleMenu = roleMenu;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
