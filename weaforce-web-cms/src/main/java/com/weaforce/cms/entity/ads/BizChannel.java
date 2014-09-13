package com.weaforce.cms.entity.ads;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.util.CollectionUtil;
import com.weaforce.entity.area.City;

/**
 * 广告频道管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_ads_channel")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class BizChannel implements Serializable {
	private static final long serialVersionUID = 4529794669873573503L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "120", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "CHANNEL_ID", length = 20)
	private Long channelId;
	// 名称
	@Column(name = "CHANNEL_NAME", length = 45)
	private String channelName;
	// 排序
	@Column(name = "CHANNEL_ORDER", length = 3)
	private Byte channelOrder;
	// 描述
	@Column(name = "CHANNEL_DESCRIPTION", length = 255)
	private String channelDescription;
	// 栏目
	@OneToMany(mappedBy = "categoryChannel", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.LAZY)
	@org.hibernate.annotations.OrderBy(clause = "CATEGORY_ORDER asc")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<BizCategory> channelCategory = new ArrayList<BizCategory>();
	// 城市
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CHANNEL_CITY_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_CHANNEL_CITY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private City channelCity;

	public BizChannel() {

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

	public List<BizCategory> getChannelCategory() {
		return channelCategory;
	}

	public void setChannelCategory(List<BizCategory> channelCategory) {
		this.channelCategory = channelCategory;
	}

	public City getChannelCity() {
		return channelCity;
	}

	public void setChannelCity(City channelCity) {
		this.channelCity = channelCity;
	}

	/**
	 * 取得栏目名称
	 * 
	 * @return
	 * @throws Exception
	 */
	@Transient
	public String getChannelCategoryNames() throws Exception {
		return CollectionUtil.fetchPropertyToString("", channelCategory,
				"categoryName", ",");
	}

}
