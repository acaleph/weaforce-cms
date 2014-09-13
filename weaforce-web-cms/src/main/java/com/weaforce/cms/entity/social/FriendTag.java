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
@Table(name = "cms_social_friend_tag")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class FriendTag implements Serializable {
	private static final long serialVersionUID = 3444664678468683207L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "185", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "TAG_ID", length = 20)
	private Long tagId;
	// Name
	@Column(name = "TAG_NAME", length = 45)
	private String tagName;
	// Name
	@Column(name = "TAG_OWNER", length = 45)
	private String tagOwner;

	public FriendTag() {

	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagOwner() {
		return tagOwner;
	}

	public void setTagOwner(String tagOwner) {
		this.tagOwner = tagOwner;
	}

}
