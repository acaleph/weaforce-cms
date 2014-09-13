package com.weaforce.entity.ccm.pm;

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

/**
 * 任务记录管理类:任务相关人员都可以进行任务记录,因此是ManyToOne关系
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "fp_mission_note")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class MissionNote extends PrimaryEntity implements Serializable {

	private static final long serialVersionUID = -3701381899393468194L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "42", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "NOTE_ID", length = 20)
	private Long noteId;
	// 标题
	@Column(name = "NOTE_TITLE", length = 180)
	private String noteTitle;
	// 描述
	@Column(name = "NOTE_DESCRIPTION", length = 255)
	private String noteDescription;
	// 内容
	@Column(name = "NOTE_CONTENT")
	private String noteContent;
	// 任务
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "NOTE_MISSION_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_NOTE_MISSION_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Mission noteMission;

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

	public Mission getNoteMission() {
		return noteMission;
	}

	public void setNoteMission(Mission noteMission) {
		this.noteMission = noteMission;
	}

	public String getNoteAuthor() {
		return noteAuthor;
	}

	public void setNoteAuthor(String noteAuthor) {
		this.noteAuthor = noteAuthor;
	}

}
