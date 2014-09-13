package com.weaforce.system.entity.trafic;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.entity.admin.User;

@Entity
@Table(name = "geo_mobile")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Mobile implements Serializable {
	private static final long serialVersionUID = -3173646502555269702L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "189", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "MOBILE_ID", length = 20)
	private Long mobileId;
	// Mobile
	@Column(name = "MOBILE_MOBILE", length = 20)
	private String mobileMobile;
	// Longitude
	@Column(name = "MOBILE_LONGITUDE", length = 20)
	private String mobileLongitude;
	// Latitude
	@Column(name = "MOBILE_LATITUDE", length = 20)
	private String mobileLatitude;
	// Mobile user(owner)
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinColumn(name = "MOBILE_USER_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_MOBILE_USER_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private User mobileUser;

	@Transient
	private String userLogin;
	
	@Transient
	private String userName;

	public Mobile() {

	}

	public Long getMobileId() {
		return mobileId;
	}

	public void setMobileId(Long mobileId) {
		this.mobileId = mobileId;
	}

	public String getMobileMobile() {
		return mobileMobile;
	}

	public void setMobileMobile(String mobileMobile) {
		this.mobileMobile = mobileMobile;
	}

	public String getMobileLongitude() {
		return mobileLongitude;
	}

	public void setMobileLongitude(String mobileLongitude) {
		this.mobileLongitude = mobileLongitude;
	}

	public String getMobileLatitude() {
		return mobileLatitude;
	}

	public void setMobileLatitude(String mobileLatitude) {
		this.mobileLatitude = mobileLatitude;
	}

	public User getMobileUser() {
		return mobileUser;
	}

	public void setMobileUser(User mobileUser) {
		this.mobileUser = mobileUser;
	}

	public String getUserLogin() {
		if (this.mobileUser != null)
			userLogin = this.mobileUser.getUserLogin();
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
