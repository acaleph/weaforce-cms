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

import com.weaforce.entity.PrimaryEntity;

@Entity
@Table(name = "fo_job_note")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class JobNote extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 2249361661614834018L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "43", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "NOTE_ID", length = 20)
	private Long noteId;
	//标题
	@Column(name = "NOTE_TITLE", length = 180)
	private String noteTitle;
	//描述
	@Column(name = "NOTE_DESCRIPTION", length = 255)
	private String noteDescription;
	//内容
	@Column(name = "NOTE_CONTENT")
	private String noteContent;
	//所属工作
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "NOTE_JOB_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_NOTE_JOB_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Job noteJob;

	@Transient
	private String noteAuthor;

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteDescription() {
		return noteDescription;
	}

	public void setNoteDescription(String noteDescription) {
		this.noteDescription = noteDescription;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public Job getNoteJob() {
		return noteJob;
	}

	public void setNoteJob(Job noteJob) {
		this.noteJob = noteJob;
	}

	public String getNoteAuthor() {
		return noteAuthor;
	}

	public void setNoteAuthor(String noteAuthor) {
		this.noteAuthor = noteAuthor;
	}

}
