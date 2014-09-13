package com.weaforce.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.weaforce.core.util.DateUtil;

/**
 * <ul>
 * <h3>实体的基本类,将被继承</h3>
 * <li>相关列由PrimaryEntityInterceptor维护</li>
 * </ul>
 */
@MappedSuperclass
public abstract class PrimaryEntity {
	// 帐套
	@Column(name = "ACCOUNT", updatable = false, nullable = false, length = 3)
	private String account;
	// 创建时间
	@Column(name = "CREATE_TIME", updatable = false, nullable = false, length = 20)
	private Long createTime;
	// 创建人
	@Column(name = "CREATOR", updatable = false, nullable = false, length = 45)
	private String creator;
	// 锁
	@Column(name = "LOGIC_LOCK", updatable = true, nullable = false, length = 1)
	private String logicLock;
	// update有效 insert无效
	@Column(name = "LAST_UPDATE_TIME", insertable = false, updatable = true, nullable = true, length = 20)
	private Long lastUpdateTime;
	// update有效 insert无效
	@Column(name = "LAST_UPDATE", insertable = false, updatable = true, nullable = true, length = 45)
	private String lastUpdate;

	@Transient
	private String date;

	public PrimaryEntity() {
		logicLock = "0";
		createTime = System.currentTimeMillis();
		lastUpdateTime = createTime;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getLogicLock() {
		return logicLock;
	}

	public void setLogicLock(String logicLock) {
		this.logicLock = logicLock;
	}

	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * 返回W3C格式日期,如2008-08-08
	 * 
	 * @return
	 */
	public String getDate() {
		if (createTime == null || createTime == 0) {
			date = DateUtil.defaultFormat(new Date(System.currentTimeMillis()));
		} else {
			date = DateUtil.defaultFormat(new Date(createTime));
		}
		return date;
	}


}
