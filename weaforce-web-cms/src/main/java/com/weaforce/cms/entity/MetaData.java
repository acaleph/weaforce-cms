package com.weaforce.cms.entity;

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

@Entity
@Table(name = "cms_meta_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = true)
public class MetaData implements Serializable {
	private static final long serialVersionUID = -2944637201826170975L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "193", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "DATA_ID", length = 20)
	private Long dataId;
	@Column(name = "DATA_NAME", length = 255)
	private String dataName;
	@Column(name = "DATA_CONTENT", length = 255)
	private String dataContent;
	// Media
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "DATA_MEDIA_ID")
	@org.hibernate.annotations.ForeignKey(name = "FKDATA_MEDIA_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Media dataMedia;

	public MetaData() {

	}

	public MetaData(String name, String content) {
		this.dataName = name;
		this.dataContent = content;
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDataContent() {
		return dataContent;
	}

	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}

	public Media getDataMedia() {
		return dataMedia;
	}

	public void setDataMedia(Media dataMedia) {
		this.dataMedia = dataMedia;
	}

}
