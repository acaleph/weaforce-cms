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

/**
 * 打折/小贴士留言管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_note")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class Note implements Serializable {
	private static final long serialVersionUID = -4620724435675765493L;
	// 主键
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "158", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "NOTE_ID", length = 20)
	private Long noteId;
	// 标题
	@Column(name = "NOTE_TITLE", length = 180)
	private String noteTitle;
	// 内容
	@Column(name = "NOTE_CONTENT", length = 280)
	private String noteContent;
	// 注册人
	@Column(name = "NOTE_REGISTRY_ID", length = 20, nullable = true)
	private Long noteRegistryId;
	// 宣传URL:微博/网店/博客
	@Column(name = "NOTE_REGISTRY_SHOW", length = 20)
	private String noteRegistryShow;
	// 宣传URL:微博/网店/博客
	@Column(name = "NOTE_REGISTRY_SHOW_URL", length = 45)
	private String noteRegistryShowUrl;
	// 文章
	@Column(name = "NOTE_ARTICLE_ID", length = 20, nullable = true)
	private Long noteArticleId;
	// 打折
	@Column(name = "NOTE_DISCOUNT_ID", length = 20, nullable = true)
	private Long noteDiscountId;
	// 小贴士
	@Column(name = "NOTE_TIP_ID", length = 20, nullable = true)
	private Long noteTipId;

	public Note() {

	}

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

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public String getNoteRegistryShow() {
		return noteRegistryShow;
	}

	public void setNoteRegistryShow(String noteRegistryShow) {
		this.noteRegistryShow = noteRegistryShow;
	}

	public String getNoteRegistryShowUrl() {
		return noteRegistryShowUrl;
	}

	public void setNoteRegistryShowUrl(String noteRegistryShowUrl) {
		this.noteRegistryShowUrl = noteRegistryShowUrl;
	}

	public Long getNoteRegistryId() {
		return noteRegistryId;
	}

	public void setNoteRegistryId(Long noteRegistryId) {
		this.noteRegistryId = noteRegistryId;
	}

	public Long getNoteArticleId() {
		return noteArticleId;
	}

	public void setNoteArticleId(Long noteArticleId) {
		this.noteArticleId = noteArticleId;
	}

	public Long getNoteDiscountId() {
		return noteDiscountId;
	}

	public void setNoteDiscountId(Long noteDiscountId) {
		this.noteDiscountId = noteDiscountId;
	}

	public Long getNoteTipId() {
		return noteTipId;
	}

	public void setNoteTipId(Long noteTipId) {
		this.noteTipId = noteTipId;
	}

}
