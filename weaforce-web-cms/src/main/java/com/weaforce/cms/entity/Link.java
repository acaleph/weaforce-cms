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

/**
 * 友情链接
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_link")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = true)
public class Link extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -5427317611340918132L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "168", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "LINK_ID", length = 20)
	private Long linkId;
	// 名称
	@Column(name = "LINK_NAME", length = 45)
	private String linkName;
	// 名称
	@Column(name = "LINK_URL", length = 45)
	private String linkUrl;
	// 名称
	@Column(name = "LINK_LOGO", length = 45)
	private String linkLogo;

	public Link() {

	}

	public Long getLinkId() {
		return linkId;
	}

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLinkLogo() {
		return linkLogo;
	}

	public void setLinkLogo(String linkLogo) {
		this.linkLogo = linkLogo;
	}

}
