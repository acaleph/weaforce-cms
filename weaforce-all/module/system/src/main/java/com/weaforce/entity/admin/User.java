package com.weaforce.entity.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.core.util.CollectionUtil;
import com.weaforce.core.util.DateUtil;
import com.weaforce.entity.area.City;
import com.weaforce.entity.area.Zone;
import com.weaforce.entity.admin.Role;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

/**
 * User flag presents the user's type is system or register user
 * 
 * @author Manfred
 * 
 */
@Entity
@Table(name = "admin_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
// Hibernate search
@Indexed
public class User extends PrimaryEntity implements UserDetails, Serializable {
	private static final long serialVersionUID = -2200481491823262268L;

	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "3", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "USER_ID", length = 20)
	private Long userId;
	// 中文名称
	@Column(name = "USER_NAME_CN", length = 45)
	private String userNameCn;
	// 英文名称
	@Column(name = "USER_NAME_EN", length = 45)
	private String userNameEn;
	// 首名
	@Column(name = "USER_FIRSTNAME", length = 45)
	private String userFirstname;
	// 尾名
	@Column(name = "USER_LASTNAME", length = 20)
	private String userLastname;
	// 登录
	@Column(name = "USER_LOGIN", length = 45, unique = true)
	@Field(name = "userLogin", index = Index.TOKENIZED, store = Store.YES)
	private String userLogin;
	// 密码
	@Column(name = "USER_PASSWORD", length = 16)
	private String userPassword;
	// MD5
	@Column(name = "USER_PWD", length = 45)
	private String userPwd;
	// 活动
	@Column(name = "USER_IS_ACTIVE", length = 1)
	private String userIsActive;
	// 用户类型：0 Case One系统，1其它
	@Column(name = "USER_TYPE", length = 1)
	private String userType;
	// 体验
	@Column(name = "USER_FLAG", length = 1)
	private String userFlag;
	// 默认语言
	@Column(name = "USER_DEFAULT_LANGUAGE", length = 3)
	private String userDefaultLanguage;
	// 上一次登录时间
	@Column(name = "USER_LAST_LOGIN_TIME", length = 20)
	private Long userLastLoginTime;
	// 当前登录时间
	@Column(name = "USER_CURRENT_LOGIN_TIME", length = 20)
	private Long userCurrentLoginTime;
	// 逻辑删除
	@Column(name = "LOGIC_DELETE", length = 1)
	private String logicDelete;
	// 默认角色(菜单rank)
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ROLE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_DEFAULT_USER_ROLE_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Role defaultUserRole;
	// Roles
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "admin_user_role", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	@org.hibernate.annotations.ForeignKey(name = "FK_USER_ROLE_ID", inverseName = "FK_ROLE_USER_ID")
	@OrderBy("roleId")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Role> userRole = new LinkedHashSet<Role>();
	// 城市
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_CITY_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_USER_CITY_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private City userCity;
	// 区域
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ZONE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_USER_ZONE_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Zone userZone;

	@Transient
	private String userLastLoginDate;
	@Transient
	private String userCurrentLoginDate;

	public User() {
		this.logicDelete = "0";
		this.userIsActive = "1";
		this.userType = "0";
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserNameCn() {
		return userNameCn;
	}

	public void setUserNameCn(String userNameCn) {
		this.userNameCn = userNameCn;
	}

	public String getUserNameEn() {
		return userNameEn;
	}

	public void setUserNameEn(String userNameEn) {
		this.userNameEn = userNameEn;
	}

	public String getUserFirstname() {
		return userFirstname;
	}

	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}

	public String getUserLastname() {
		return userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserIsActive() {
		return userIsActive;
	}

	public void setUserIsActive(String userIsActive) {
		this.userIsActive = userIsActive;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}

	public String getUserDefaultLanguage() {
		return userDefaultLanguage;
	}

	public void setUserDefaultLanguage(String userDefaultLanguage) {
		this.userDefaultLanguage = userDefaultLanguage;
	}

	public Long getUserLastLoginTime() {
		return userLastLoginTime;
	}

	public void setUserLastLoginTime(Long userLastLoginTime) {
		this.userLastLoginTime = userLastLoginTime;
	}

	public Long getUserCurrentLoginTime() {
		return userCurrentLoginTime;
	}

	public void setUserCurrentLoginTime(Long userCurrentLoginTime) {
		this.userCurrentLoginTime = userCurrentLoginTime;
	}

	public String getLogicDelete() {
		return logicDelete;
	}

	public void setLogicDelete(String logicDelete) {
		this.logicDelete = logicDelete;
	}

	public Role getDefaultUserRole() {
		return defaultUserRole;
	}

	public void setDefaultUserRole(Role defaultUserRole) {
		this.defaultUserRole = defaultUserRole;
	}

	public Set<Role> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<Role> userRole) {
		this.userRole = userRole;
	}

	public City getUserCity() {
		return userCity;
	}

	public void setUserCity(City userCity) {
		this.userCity = userCity;
	}

	public Zone getUserZone() {
		return userZone;
	}

	public void setUserZone(Zone userZone) {
		this.userZone = userZone;
	}

	/**
	 * 以完整的W3C格式显示最后一次登录日期
	 * 
	 * @return
	 */
	public String getUserLastLoginDate() {
		if (userLastLoginTime != null)
			userLastLoginDate = DateUtil.completeFormat(new Date(
					userLastLoginTime));
		return userLastLoginDate;
	}

	/**
	 * 以完成的W3C格式显示当前登录日期
	 * 
	 * @return
	 */
	public String getUserCurrentLoginDate() {
		if (userCurrentLoginTime != null)
			userCurrentLoginDate = DateUtil.completeFormat(new Date(
					userCurrentLoginTime));
		return userCurrentLoginDate;
	}

	@Transient
	public String getUserRoleNames() throws Exception {
		return CollectionUtil.fetchPropertyToString(this.getAccount(),
				userRole, "roleName", ",");

	}

	@Transient
	public List<Long> getUserRoleIds() throws Exception {
		return CollectionUtil.fetchPropertyToList(this.getAccount(), userRole,
				"roleId");
	}

	// =================================================================================
	@Transient
	private String password;
	@Transient
	private String username;
	@Transient
	private Set<GrantedAuthority> authorities;
	@Transient
	private boolean accountNonExpired;
	@Transient
	private boolean accountNonLocked;
	@Transient
	private boolean credentialsNonExpired;
	@Transient
	private boolean enabled;

	// =================================================================================
	public User(String username, String password, boolean enabled,
			Set<GrantedAuthority> authorities) throws IllegalArgumentException {
		this(username, password, enabled, true, true, authorities);
	}

	public User(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			Set<GrantedAuthority> authorities) throws IllegalArgumentException {
		this(username, password, enabled, accountNonExpired,
				credentialsNonExpired, true, authorities);
	}

	public User(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Set<GrantedAuthority> authorities)
			throws IllegalArgumentException {
		if (((username == null) || "".equals(username)) || (password == null)) {
			throw new IllegalArgumentException(
					"Cannot pass null or empty values to constructor");
		}

		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		setAuthorities(authorities);
	}

	/**
	 * 重构User
	 * 
	 * @param account
	 *            帐套
	 * @param userLogin
	 *            登录
	 * @param username
	 *            等同userLogin
	 * @param password
	 *            密码
	 * @param enabled
	 *            有效
	 * @param accountNonExpired
	 *            过期
	 * @param credentialsNonExpired
	 *            授权过期
	 * @param accountNonLocked
	 *            是否加锁
	 * @param authorities
	 *            授权
	 * @throws IllegalArgumentException
	 */
	public User(Long userId, String account, String userLogin, String username,
			String password, City userCity, Zone userZone, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Set<GrantedAuthority> authorities)
			throws IllegalArgumentException {
		if (((username == null) || "".equals(username)) || (password == null)) {
			throw new IllegalArgumentException(
					"Cannot pass null or empty values to constructor");
		}
		this.setUserId(userId);
		this.setAccount(account);
		this.setUserLogin(userLogin);
		this.username = username;
		this.password = password;
		this.userCity = userCity;
		this.userZone = userZone;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		setAuthorities(authorities);
	}

	protected void setAuthorities(Set<GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority array");
		// Ensure array iteration order is predictable (as per
		// UserDetails.getAuthorities() contract and SEC-xxx)
		Set<GrantedAuthority> sorter = new HashSet<GrantedAuthority>();
		for (GrantedAuthority o : authorities) {
			Assert.notNull(
					o,
					"Granted authority element "
							+ " is null - GrantedAuthority[] cannot contain any null elements");
			sorter.add(o);
		}

		this.authorities = sorter;
	}

	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getPassword() {
		// this.password = this.userPassword;
		return password;
	}

	public String getUsername() {
		// this.username=this.userLogin;
		return username;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString()).append(": ");
		sb.append("Username: ").append(this.username).append("; ");
		sb.append("Password: [PROTECTED]; ");
		sb.append("Enabled: ").append(this.enabled).append("; ");
		sb.append("AccountNonExpired: ").append(this.accountNonExpired)
				.append("; ");
		sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired)
				.append("; ");
		sb.append("AccountNonLocked: ").append(this.accountNonLocked)
				.append("; ");

		if (this.getAuthorities() != null) {
			sb.append("Granted Authorities: ");

			for (int i = 0; i < this.getAuthorities().size(); i++) {
				if (i > 0) {
					sb.append(", ");
				}

				sb.append(this.getAuthorities().toString());
			}
		} else {
			sb.append("Not granted any authorities");
		}

		return sb.toString();
	}

}
