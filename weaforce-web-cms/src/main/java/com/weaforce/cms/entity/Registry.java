package com.weaforce.cms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.util.DateUtil;
import com.weaforce.entity.area.City;
import com.weaforce.entity.area.Zone;

/**
 * 注册用户管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_registry")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class Registry implements Serializable {
	private static final long serialVersionUID = -4467158343109389938L;
	// 主键
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "33", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "REGISTRY_ID", length = 20)
	private Long registryId;
	// 姓名
	@Column(name = "REGISTRY_NAME", length = 45)
	private String registryName;
	// 性别
	@Column(name = "REGISTRY_SEX", length = 6)
	@Enumerated(EnumType.STRING)
	private SexEnum registrySex;
	// 昵称
	@Column(name = "REGISTRY_NICKNAME", length = 45)
	private String registryNickname;
	// 宣传URL:微博/网店/博客
	@Column(name = "REGISTRY_SHOW", length = 20)
	private String registryShow;
	// 宣传URL:微博/网店/博客
	@Column(name = "REGISTRY_SHOW_URL", length = 45)
	private String registryShowUrl;
	// 照片
	@Column(name = "REGISTRY_PHOTO_URL", length = 45)
	private String registryPhotoUrl;
	// 登录
	@Column(name = "REGISTRY_LOGIN", length = 45)
	private String registryLogin;
	// 密码
	@Column(name = "REGISTRY_PASSWORD", length = 45)
	private String registryPassword;
	// 主题数量
	@Column(name = "REGISTRY_TOPIC_COUNT", length = 10)
	private Integer registryTopicCount;
	// 发贴数量
	@Column(name = "REGISTRY_POST_COUNT", length = 10)
	private Integer registryPostCount;
	// 有效
	@Column(name = "REGISTRY_IS_ACTIVE", length = 1)
	private String registryIsActive;
	// 注册时间
	@Column(name = "CREATE_TIME", length = 20)
	private Long createTime;
	// 最后登录时间
	@Column(name = "LAST_LOGIN_TIME", length = 20)
	private Long lastLoginTime;
	// 最后登录IP
	@Column(name = "LAST_LOGIN_IP", length = 20)
	private String lastLoginIp;
	// 地址
	@Column(name = "REGISTRY_ADDRESS", length = 255)
	private String registryAddress;
	// 地址
	@Column(name = "REGISTRY_CAR_CODE", length = 10)
	private String registryCarCode;
	// 所在区（镇）
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "REGISTRY_CITY_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_REGISTRY_CITY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private City registryCity;
	// 所在区（镇）
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "REGISTRY_ZONE_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_REGISTRY_ZONE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Zone registryZone;

	// 注册时间
	@Transient
	private String date;

	public Registry() {
		registryIsActive = "1";
		createTime = System.currentTimeMillis();
		registryTopicCount = 0;
		registryPostCount = 0;
	}

	public static enum SexEnum {
		MALE, FEMALE
	}

	public Long getRegistryId() {
		return registryId;
	}

	public void setRegistryId(Long registryId) {
		this.registryId = registryId;
	}

	public String getRegistryName() {
		return registryName;
	}

	public void setRegistryName(String registryName) {
		this.registryName = registryName;
	}

	public SexEnum getRegistrySex() {
		return registrySex;
	}

	public void setRegistrySex(SexEnum registrySex) {
		this.registrySex = registrySex;
	}

	public String getRegistryNickname() {
		return registryNickname;
	}

	public void setRegistryNickname(String registryNickname) {
		this.registryNickname = registryNickname;
	}

	public String getRegistryShow() {
		return registryShow;
	}

	public void setRegistryShow(String registryShow) {
		this.registryShow = registryShow;
	}

	public String getRegistryShowUrl() {
		return registryShowUrl;
	}

	public void setRegistryShowUrl(String registryShowUrl) {
		this.registryShowUrl = registryShowUrl;
	}

	public String getRegistryPhotoUrl() {
		return registryPhotoUrl;
	}

	public void setRegistryPhotoUrl(String registryPhotoUrl) {
		this.registryPhotoUrl = registryPhotoUrl;
	}

	public String getRegistryLogin() {
		return registryLogin;
	}

	public void setRegistryLogin(String registryLogin) {
		this.registryLogin = registryLogin;
	}

	public String getRegistryPassword() {
		return registryPassword;
	}

	public void setRegistryPassword(String registryPassword) {
		this.registryPassword = registryPassword;
	}

	public Integer getRegistryTopicCount() {
		return registryTopicCount;
	}

	public void setRegistryTopicCount(Integer registryTopicCount) {
		this.registryTopicCount = registryTopicCount;
	}

	public Integer getRegistryPostCount() {
		return registryPostCount;
	}

	public void setRegistryPostCount(Integer registryPostCount) {
		this.registryPostCount = registryPostCount;
	}

	public String getRegistryIsActive() {
		return registryIsActive;
	}

	public void setRegistryIsActive(String registryIsActive) {
		this.registryIsActive = registryIsActive;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getRegistryAddress() {
		return registryAddress;
	}

	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}

	public String getRegistryCarCode() {
		return registryCarCode;
	}

	public void setRegistryCarCode(String registryCarCode) {
		this.registryCarCode = registryCarCode;
	}

	public City getRegistryCity() {
		return registryCity;
	}

	public void setRegistryCity(City registryCity) {
		this.registryCity = registryCity;
	}

	public Zone getRegistryZone() {
		return registryZone;
	}

	public void setRegistryZone(Zone registryZone) {
		this.registryZone = registryZone;
	}

	/**
	 * 主题数量
	 */
	public void addTopicCount() {
		registryTopicCount++;
	}

	/**
	 * 帖子数量
	 */
	public void addPostCount() {
		registryPostCount++;
	}

	public Integer getStarCount() {
		return this.registryTopicCount % 10 + this.registryPostCount % 100;
	}

	/**
	 * 返回W3C格式日期,如2008-08-08
	 * 
	 * @return
	 */
	public String getDate() {
		if (createTime == null || createTime == 0) {
			date = DateUtil
					.completeFormat(new Date(System.currentTimeMillis()));
		} else {
			date = DateUtil.completeFormat(new Date(createTime));
		}
		return date;
	}

}
