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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.core.util.CollectionUtil;
import com.weaforce.entity.admin.User;

/**
 * 文章频道管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_channel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = true)
public class Channel extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -4868876314156124834L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "61", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "CHANNEL_ID", length = 20)
	private Long channelId;
	// 名称
	@Column(name = "CHANNEL_NAME", length = 45)
	private String channelName;
	// 路径:Parse文件指定目录
	@Column(name = "CHANNEL_PATH", length = 100)
	private String channelPath;
	// 网址:Parse过程中应用.如http://www.weaforce.com/news
	@Column(name = "CHANNEL_URL", length = 100)
	private String channelUrl;
	// 排序
	@Column(name = "CHANNEL_ORDER", length = 3)
	private Byte channelOrder;
	// 描述
	@Column(name = "CHANNEL_DESCRIPTION", length = 255)
	private String channelDescription;
	// 活动
	@Column(name = "CHANNEL_IS_ACTIVE", length = 1)
	private String channelIsActive;
	// Template
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CHANNEL_TEMPLATE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_CHANNEL_TEMPLATE_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Template channelTemplate;
	// Categories
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "categoryChannel", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Category> channelCategory = new ArrayList<Category>();
	// 负责人
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "cms_channel_user", joinColumns = { @JoinColumn(name = "CHANNEL_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	@org.hibernate.annotations.ForeignKey(name = "FK_CHANNEL_USER_ID", inverseName = "FK_USER_CHANNEL_ID")
	@OrderBy("userId")
	private List<User> channelUser = new ArrayList<User>();
	// 频道页面
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "linkChannel", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "LINK_ID")
	private PageLink channelLink;

	@Transient
	private boolean selected;

	public Channel() {
		channelIsActive = "1";
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelPath() {
		return channelPath;
	}

	public void setChannelPath(String channelPath) {
		this.channelPath = channelPath;
	}

	public String getChannelUrl() {
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

	public Byte getChannelOrder() {
		return channelOrder;
	}

	public void setChannelOrder(Byte channelOrder) {
		this.channelOrder = channelOrder;
	}

	public String getChannelDescription() {
		return channelDescription;
	}

	public void setChannelDescription(String channelDescription) {
		this.channelDescription = channelDescription;
	}

	public String getChannelIsActive() {
		return channelIsActive;
	}

	public void setChannelIsActive(String channelIsActive) {
		this.channelIsActive = channelIsActive;
	}

	public Template getChannelTemplate() {
		return channelTemplate;
	}

	public void setChannelTemplate(Template channelTemplate) {
		this.channelTemplate = channelTemplate;
	}

	public List<Category> getChannelCategory() {
		return channelCategory;
	}

	public void setChannelCategory(List<Category> channelCategory) {
		this.channelCategory = channelCategory;
	}

	public List<User> getChannelUser() {
		return channelUser;
	}

	public void setChannelUser(List<User> channelUser) {
		this.channelUser = channelUser;
	}

	public String getChannelUserNames() throws Exception {
		return CollectionUtil.fetchPropertyToString(this.getAccount(),
				channelUser, "userNameCn", ",");
	}

	public List<Long> getChannelUserIds() throws Exception {
		return CollectionUtil.fetchPropertyToList(this.getAccount(),
				channelUser, "userId");
	}

	public PageLink getChannelLink() {
		return channelLink;
	}

	public void setChannelLink(PageLink channelLink) {
		this.channelLink = channelLink;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
