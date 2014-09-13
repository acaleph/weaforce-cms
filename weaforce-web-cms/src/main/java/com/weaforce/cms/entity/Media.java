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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Table(name = "cms_media")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = true)
@Indexed
public class Media implements Serializable {
	private static final long serialVersionUID = -6885660885507459502L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "192", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "MEDIA_ID", length = 20)
	private Long mediaId;
	// Language code
	@Column(name = "MEDIA_LANGUAGE_CODE", length = 2)
	private String mediaLanguageCode;
	// Type
	@Column(name = "MEDIA_MIME_TYPE", length = 45)
	private String mediaMimeType;
	// Title
	@Column(name = "MEDIA_TITLE", length = 255)
	private String mediaTitle;
	// URL
	@Column(name = "MEDIA_URL", nullable = false, length = 255)
	@org.hibernate.annotations.Index(name = "IN_MEDIA_URL")
	@Field(index = Index.UN_TOKENIZED, store = Store.YES)
	private String mediaUrl;
	// Binary content
	@Lob
	@Column(name = "MEDIA_BANARY_DATA")
	private byte[] mediaBinaryData;
	// HTML content
	@Lob
	@Column(name = "MEDIA_HTML")
	private String mediaHtml;
	// HTML content
	@Lob
	@Column(name = "MEDIA_XHTML")
	private String mediaXhtml;
	// Plain text content
	@Lob
	@Column(name = "MEDIA_PLAIN_TEXT")
	private String mediaPlainText;
	// Meta data
	@OneToMany(mappedBy = "dataMedia", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<MetaData> mediaMeta = new ArrayList<MetaData>();
	// Create time
	@Column(name = "CREATE_TIME", updatable = false, nullable = false, length = 20)
	private Long createTime;
	// Creator
	@Column(name = "CREATOR", updatable = false, nullable = false, length = 45)
	private String creator;
	// Last update time
	@Column(name = "LAST_UPDATE_TIME", insertable = false, updatable = true, nullable = true, length = 20)
	private Long lastUpdateTime;
	// Last update
	@Column(name = "LAST_UPDATE", insertable = false, updatable = true, nullable = true, length = 45)
	private String lastUpdate;

	public Media() {

	}

	public Media(String title, byte[] binaryData, String html, String xhtml,
			String plainText, String url, String mimeType, String languageCode) {
		this.mediaTitle = title;
		this.mediaBinaryData = binaryData;
		this.mediaHtml = html;
		this.mediaXhtml = xhtml;
		this.mediaUrl = url;
		this.mediaMimeType = mimeType;
		this.mediaLanguageCode = languageCode;

	}

	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public String getMediaLanguageCode() {
		return mediaLanguageCode;
	}

	public void setMediaLanguageCode(String mediaLanguageCode) {
		this.mediaLanguageCode = mediaLanguageCode;
	}

	public String getMediaMimeType() {
		return mediaMimeType;
	}

	public void setMediaMimeType(String mediaMimeType) {
		this.mediaMimeType = mediaMimeType;
	}

	public String getMediaTitle() {
		return mediaTitle;
	}

	public void setMediaTitle(String mediaTitle) {
		this.mediaTitle = mediaTitle;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public byte[] getMediaBinaryData() {
		return mediaBinaryData;
	}

	public void setMediaBinaryData(byte[] mediaBinaryData) {
		this.mediaBinaryData = mediaBinaryData;
	}

	public String getMediaHtml() {
		return mediaHtml;
	}

	public void setMediaHtml(String mediaHtml) {
		this.mediaHtml = mediaHtml;
	}

	public String getMediaXhtml() {
		return mediaXhtml;
	}

	public void setMediaXhtml(String mediaXhtml) {
		this.mediaXhtml = mediaXhtml;
	}

	public String getMediaPlainText() {
		return mediaPlainText;
	}

	public void setMediaPlainText(String mediaPlainText) {
		this.mediaPlainText = mediaPlainText;
	}

	public List<MetaData> getMediaMeta() {
		return mediaMeta;
	}

	public void setMediaMeta(List<MetaData> mediaMeta) {
		this.mediaMeta = mediaMeta;
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

}
