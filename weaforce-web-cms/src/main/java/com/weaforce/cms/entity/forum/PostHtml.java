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
 * 贴子HTML管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_forum_post_html")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class PostHtml implements Serializable {
	private static final long serialVersionUID = -5412769574400011712L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "129", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "HTML_ID", length = 20)
	private Long htmlId;
	//伪主题ID
	@Column(name = "HTML_TOPIC_ID", length = 20)
	private Long htmlTopicId;
	// 内容
	@Column(name = "HTML_CONTENT")
	private String htmlContent;
	// 贴子
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinColumn(name = "HTML_POST_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_HTML_POST_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Post htmlPost;

	public PostHtml() {
	}

	public Long getHtmlId() {
		return htmlId;
	}

	public void setHtmlId(Long htmlId) {
		this.htmlId = htmlId;
	}

	public Long getHtmlTopicId() {
		return htmlTopicId;
	}

	public void setHtmlTopicId(Long htmlTopicId) {
		this.htmlTopicId = htmlTopicId;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public Post getHtmlPost() {
		return htmlPost;
	}

	public void setHtmlPost(Post htmlPost) {
		this.htmlPost = htmlPost;
	}

}
