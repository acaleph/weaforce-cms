package com.weaforce.cms.entity.social;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

/**
 * The talk about car between friends
 * 
 * @author Manfred
 * 
 */
@Entity
@Table(name = "cms_social_car_talk")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class TalkCar implements Serializable {
	private static final long serialVersionUID = -2604123824292310752L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "90", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "TALK_ID", length = 20)
	private Long talkId;
	// Writer
	@Column(name = "TALK_WRITER", length = 45)
	private String talkWriter;
	// Content
	@Column(name = "TALK_CONTENT", length = 280)
	private String talkContent;
	// Parent
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TALK_FID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_CAR_TALK_FID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private TalkCar talkParent;
	// Car share
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "TALK_SHARE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_CAR_TALK_SHARE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private CarShare talkShare;

	public TalkCar() {

	}

	public Long getTalkId() {
		return talkId;
	}

	public void setTalkId(Long talkId) {
		this.talkId = talkId;
	}

	public String getTalkWriter() {
		return talkWriter;
	}

	public void setTalkWriter(String talkWriter) {
		this.talkWriter = talkWriter;
	}

	public String getTalkContent() {
		return talkContent;
	}

	public void setTalkContent(String talkContent) {
		this.talkContent = talkContent;
	}

	public TalkCar getTalkParent() {
		return talkParent;
	}

	public void setTalkParent(TalkCar talkParent) {
		this.talkParent = talkParent;
	}

	public CarShare getTalkShare() {
		return talkShare;
	}

	public void setTalkShare(CarShare talkShare) {
		this.talkShare = talkShare;
	}

}
