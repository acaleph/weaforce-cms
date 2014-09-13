package com.weaforce.cms.entity;

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
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

/**
 * 文章内容管理类
 * @author yanjiacheng
 *
 */
@Entity
@Table(name = "cms_article_content")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class ArticleContent {
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "174", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "CONTENT_ID", length = 20)
	private Long contentId;
	// 内容
	@Field(index=Index.NO,store=Store.NO)  
	@Column(name = "CONTENT_CONTENT")
	private String contentContent;
	// 消息
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinColumn(name = "CONTENT_ARTICLE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_CRM_CONTENT_ARTICLE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Article contentArticle;
	
	public ArticleContent(){
		
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getContentContent() {
		return contentContent;
	}

	public void setContentContent(String contentContent) {
		this.contentContent = contentContent;
	}

	public Article getContentArticle() {
		return contentArticle;
	}

	public void setContentArticle(Article contentArticle) {
		this.contentArticle = contentArticle;
	}
	
	
}