package com.weaforce.cms.entity.forum;

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

import com.weaforce.cms.entity.Template;

/**
 * 论坛管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */

@Entity
@Table(name = "cms_forum")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class Forum implements Serializable {
	private static final long serialVersionUID = -1263644292507605623L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "124", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "FORUM_ID", length = 20)
	private Long forumId;
	// 名称
	@Column(name = "FORUM_NAME", length = 45)
	private String forumName;
	// 文件名称
	@Column(name = "FORUM_FILE_NAME", length = 10)
	private String forumFileName;
	// 排序
	@Column(name = "FORUM_ORDER", length = 3)
	private Byte forumOrder;
	// 描述
	@Column(name = "FORUM_DESCRIPTION", length = 255)
	private String forumDescription;
	// Parse后的WEB服务器URL
	@Column(name = "FORUM_URL", length = 90)
	private String forumUrl;
	// 所属栏目
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "FORUM_CATEGORY_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_FORUM_CATEGORY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private ForumCategory forumCategory;
	// 模板：应用于主题及贴子列表
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "FORUM_TEMPLATE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_FORUM_TEMPLATE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Template forumTemplate;

	public Forum() {
		forumOrder = 1;
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public String getForumFileName() {
		return forumFileName;
	}

	public void setForumFileName(String forumFileName) {
		this.forumFileName = forumFileName;
	}

	public Byte getForumOrder() {
		return forumOrder;
	}

	public void setForumOrder(Byte forumOrder) {
		this.forumOrder = forumOrder;
	}

	public String getForumDescription() {
		return forumDescription;
	}

	public void setForumDescription(String forumDescription) {
		this.forumDescription = forumDescription;
	}

	public String getForumUrl() {
		return forumUrl;
	}

	public void setForumUrl(String forumUrl) {
		this.forumUrl = forumUrl;
	}

	public ForumCategory getForumCategory() {
		return forumCategory;
	}

	public void setForumCategory(ForumCategory forumCategory) {
		this.forumCategory = forumCategory;
	}

	public Template getForumTemplate() {
		return forumTemplate;
	}

	public void setForumTemplate(Template forumTemplate) {
		this.forumTemplate = forumTemplate;
	}

}
