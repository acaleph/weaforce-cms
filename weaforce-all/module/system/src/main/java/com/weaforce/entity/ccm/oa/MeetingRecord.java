package com.weaforce.entity.ccm.oa;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;

@Entity
@Table(name = "fo_meeting_record")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class MeetingRecord extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 8517826914502418270L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "43", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "RECORD_ID", length = 20)
	private Long recordId;
	// 标题
	@Column(name = "RECORD_TITLE", length = 180)
	private String recordTitle;
	// 描述
	@Column(name = "RECORD_DESCRIPTION", length = 255)
	private String recordDescription;
	// 内容
	@Column(name = "RECORD_CONTENT")
	private String recordContent;
	// 会议
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "RECORD_INFO_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_MEETING_RECORD_INFO_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private MeetingInfo recordInfo;

	@Transient
	private String recordAuthor;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getRecordTitle() {
		return recordTitle;
	}

	public void setRecordTitle(String recordTitle) {
		this.recordTitle = recordTitle;
	}

	public String getRecordDescription() {
		return recordDescription;
	}

	public void setRecordDescription(String recordDescription) {
		this.recordDescription = recordDescription;
	}

	public String getRecordContent() {
		return recordContent;
	}

	public void setRecordContent(String recordContent) {
		this.recordContent = recordContent;
	}

	public MeetingInfo getRecordInfo() {
		return recordInfo;
	}

	public void setRecordInfo(MeetingInfo recordInfo) {
		this.recordInfo = recordInfo;
	}

	public String getRecordAuthor() {
		return recordAuthor;
	}

	public void setRecordAuthor(String recordAuthor) {
		this.recordAuthor = recordAuthor;
	}

}
