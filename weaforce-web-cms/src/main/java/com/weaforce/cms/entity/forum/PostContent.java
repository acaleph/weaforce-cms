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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

/**
 * 贴子内容管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_forum_post_content")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class PostContent implements Serializable {
	private static final long serialVersionUID = 1527827332288767639L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "130", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "CONTENT_ID", length = 20)
	private Long contentId;
	// 用户IP
	@Column(name = "CONTENT_IP", length = 20)
	private String contentIp;
	// 内容
	@Column(name = "CONTENT_CONTENT")
	private String contentContent;
	// 贴子
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinColumn(name = "CONTENT_POST_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_CONTENT_POST_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Post contentPost;

	public PostContent() {

	}


	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getContentIp() {
		return contentIp;
	}

	public void setContentIp(String contentIp) {
		this.contentIp = contentIp;
	}

	public String getContentContent() {
		return contentContent;
	}

	public void setContentContent(String contentContent) {
		this.contentContent = contentContent;
	}

	public Post getContentPost() {
		return contentPost;
	}

	public void setContentPost(Post contentPost) {
		this.contentPost = contentPost;
	}

}
