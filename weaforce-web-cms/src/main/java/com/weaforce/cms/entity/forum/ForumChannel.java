package com.weaforce.cms.entity.forum;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.cms.entity.Template;
import com.weaforce.cms.entity.ads.Ads;
import com.weaforce.entity.area.City;

/**
 * 论坛频道管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_forum_channel")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class ForumChannel implements Serializable {
	private static final long serialVersionUID = -1263644292507605623L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "125", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "CHANNEL_ID", length = 20)
	private Long channelId;
	// 名称
	@Column(name = "CHANNEL_NAME", length = 45)
	private String channelName;
	// parse 文件路径
	@Column(name = "CHANNEL_PARSE_PATH", length = 90)
	private String channelParsePath;
	// 排序
	@Column(name = "CHANNEL_ORDER", length = 3)
	private Byte channelOrder;
	// 描述
	@Column(name = "CHANNEL_DESCRIPTION", length = 255)
	private String channelDescription;
	// 明星商家
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CHANNEL_ADS_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_FORUM_CHANNEL_ADS_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Ads channelAds;
	// 栏目
	@OneToMany(mappedBy = "categoryChannel", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.LAZY)
	@org.hibernate.annotations.OrderBy(clause = "CATEGORY_ORDER asc")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<ForumCategory> channelCategory = new ArrayList<ForumCategory>();
	// 城市
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CHANNEL_CITY_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_FORUM_CHANNEL_CITY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private City channelCity;
	// 频道模板:栏目及栏目下的所有论坛，以tab页面显示
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CHANNEL_TEMPLATE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_FORUM_CHANNEL_TEMPLATE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Template channelTemplate;

	public ForumChannel() {

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

	public String getChannelParsePath() {
		return channelParsePath;
	}

	public void setChannelParsePath(String channelParsePath) {
		this.channelParsePath = channelParsePath;
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

	public Ads getChannelAds() {
		return channelAds;
	}

	public void setChannelAds(Ads channelAds) {
		this.channelAds = channelAds;
	}

	public List<ForumCategory> getChannelCategory() {
		return channelCategory;
	}

	public void setChannelCategory(List<ForumCategory> channelCategory) {
		this.channelCategory = channelCategory;
	}

	public City getChannelCity() {
		return channelCity;
	}

	public void setChannelCity(City channelCity) {
		this.channelCity = channelCity;
	}

	public Template getChannelTemplate() {
		return channelTemplate;
	}

	public void setChannelTemplate(Template channelTemplate) {
		this.channelTemplate = channelTemplate;
	}

}
