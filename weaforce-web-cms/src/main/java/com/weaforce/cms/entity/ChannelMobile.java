package com.weaforce.cms.entity;

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

import com.weaforce.core.entity.PrimaryEntity;

@Entity
@Table(name = "cms_channel_mobile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Proxy(lazy = true)
public class ChannelMobile extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 3949837142681286549L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "186", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "CHANNEL_ID", length = 20)
	private Long channelId;
	// 名称
	@Column(name = "CHANNEL_NAME", length = 45)
	private String channelName;
	// 网址:Parse过程中应用.如http://www.weaforce.com/news
	@Column(name = "CHANNEL_URL", length = 100)
	private String channelUrl;
	// 排序
	@Column(name = "CHANNEL_ORDER", length = 3)
	private Byte channelOrder;

	public ChannelMobile() {

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

}
