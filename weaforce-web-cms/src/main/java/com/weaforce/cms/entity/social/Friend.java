package com.weaforce.cms.entity.social;

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

@Entity
@Table(name = "cms_social_friend")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class Friend implements Serializable {
	private static final long serialVersionUID = -8157353489597677008L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "47", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "FRIEND_ID", length = 20)
	private Long friendId;
	// My login
	@Column(name = "FRIEND_LOGIN_ME", length = 45)
	private String friendLoginMe;
	// Friend login
	@Column(name = "FRIEND_LOGIN", length = 45)
	private String friendLogin;
	// Create time
	@Column(name = "FRIEND_DATE", length = 20)
	private Long friendDate;

	public Friend() {
		friendDate = System.currentTimeMillis();
	}

	public Long getFriendId() {
		return friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}

	public String getFriendLoginMe() {
		return friendLoginMe;
	}

	public void setFriendLoginMe(String friendLoginMe) {
		this.friendLoginMe = friendLoginMe;
	}

	public String getFriendLogin() {
		return friendLogin;
	}

	public void setFriendLogin(String friendLogin) {
		this.friendLogin = friendLogin;
	}

	public Long getFriendDate() {
		return friendDate;
	}

	public void setFriendDate(Long friendDate) {
		this.friendDate = friendDate;
	}

}
