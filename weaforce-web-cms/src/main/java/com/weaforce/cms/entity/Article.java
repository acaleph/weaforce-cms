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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.weaforce.core.entity.PrimaryEntity;

/**
 * 文章管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_article")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = true)
@Indexed
public class Article extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 1467473461226853219L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "52", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "ARTICLE_ID", length = 20)
	private Long articleId;
	// 标题
	@Field(index = Index.NO, store = Store.NO)
	@Column(name = "ARTICLE_TITLE", length = 180)
	private String articleTitle;
	// 副标题
	@Column(name = "ARTICLE_TITLE_SUB", length = 180)
	private String articleTitleSub;
	// 简介
	@Column(name = "ARTICLE_INTRO", length = 255)
	private String articleIntro;
	// 图片地址
	@Column(name = "ARTICLE_IMAGE_URL", length = 90)
	private String articleImageUrl;
	// 图片地址(变更后)
	@Column(name = "ARTICLE_IMAGE_URL_RESIZE", length = 90)
	private String articleImageUrlResize;
	// 图片存放物理路径
	@Column(name = "ARTICLE_IMAGE_FILE", length = 90)
	private String articleImageFile;
	// 图片存放物理路径(变更后)
	@Column(name = "ARTICLE_IMAGE_FILE_RESIZE", length = 90)
	private String articleImageFileResize;
	// 图片宽度
	@Column(name = "ARTICLE_IMAGE_WIDTH", length = 10)
	private Integer articleImageWidth;
	// 图片高度
	@Column(name = "ARTICLE_IMAGE_HEIGHT", length = 10)
	private Integer articleImageHeight;
	// 文章引用的URL
	@Column(name = "ARTICLE_LINK_URL", length = 90)
	private String articleLinkUrl;
	// 是否已经Parse成HTML
	@Column(name = "ARTICLE_IS_PARSE", length = 1)
	private String articleIsParse;
	// Parse后的WEB服务器URL
	@Column(name = "ARTICLE_URL", length = 90)
	private String articleUrl;
	// Parse后的物理地址
	@Column(name = "ARTICLE_LOCATION", length = 90)
	private String articleLocation;
	// 文章内容
	// @Column(name = "ARTICLE_CONTENT")
	// private String articleContents;
	@Column(name = "ARTICLE_MAX_CHAR_PER_PAGE", length = 10)
	private Integer articleMaxCharPerPage;
	@Column(name = "ARTICLE_PAGINATION_TYPE", length = 3)
	private byte articlePaginationType;
	// 点击次数
	@Column(name = "ARTICLE_HIT", length = 10)
	private Integer articleHit;
	// 相关文章
	@Column(name = "ARTICLE_RELATION", length = 90, nullable = true)
	private String articleRelation;
	// 像集
	@Column(name = "ARTICLE_ALBUM", length = 90, nullable = true)
	private String articleAlbum;
	// 栏目
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ARTICLE_CATEGORY_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ARTICLE_CATEGORY_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Category articleCategory;
	// 作者
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ARTICLE_AUTHOR_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ARTICLE_AUTHOR_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Author articleAuthor;
	// 来源(WEB)
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ARTICLE_FROM_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ARTICLE_COPY_FROM_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private CopyFrom articleFrom;
	// 自定义页面
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "linkArticle", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "ARTICLE_ID")
	private PageLink articleLink;
	// 内容
	@IndexedEmbedded
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "contentArticle", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "ARTICLE_ID")
	private ArticleContent articleContent;

	public Article() {
		articleIsParse = "0";
		articlePaginationType = 0;
		articleHit = 0;
	}

	public Article(Category o) {
		this.articleCategory = o;
		articleIsParse = "0";
		articlePaginationType = 0;
		articleHit = 0;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleTitleSub() {
		return articleTitleSub;
	}

	public void setArticleTitleSub(String articleTitleSub) {
		this.articleTitleSub = articleTitleSub;
	}

	public String getArticleIntro() {
		return articleIntro;
	}

	public void setArticleIntro(String articleIntro) {
		this.articleIntro = articleIntro;
	}

	public String getArticleImageUrl() {
		return articleImageUrl;
	}

	public void setArticleImageUrl(String articleImageUrl) {
		this.articleImageUrl = articleImageUrl;
	}

	public String getArticleImageUrlResize() {
		return articleImageUrlResize;
	}

	public void setArticleImageUrlResize(String articleImageUrlResize) {
		this.articleImageUrlResize = articleImageUrlResize;
	}

	public String getArticleImageFile() {
		return articleImageFile;
	}

	public void setArticleImageFile(String articleImageFile) {
		this.articleImageFile = articleImageFile;
	}

	public String getArticleImageFileResize() {
		return articleImageFileResize;
	}

	public void setArticleImageFileResize(String articleImageFileResize) {
		this.articleImageFileResize = articleImageFileResize;
	}

	public Integer getArticleImageWidth() {
		return articleImageWidth;
	}

	public void setArticleImageWidth(Integer articleImageWidth) {
		this.articleImageWidth = articleImageWidth;
	}

	public Integer getArticleImageHeight() {
		return articleImageHeight;
	}

	public void setArticleImageHeight(Integer articleImageHeight) {
		this.articleImageHeight = articleImageHeight;
	}

	public String getArticleLinkUrl() {
		return articleLinkUrl;
	}

	public void setArticleLinkUrl(String articleLinkUrl) {
		this.articleLinkUrl = articleLinkUrl;
	}

	public void setArticleIsParse(String articleIsParse) {
		this.articleIsParse = articleIsParse;
	}

	public Category getArticleCategory() {
		return articleCategory;
	}

	public String getArticleUrl() {
		return articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}

	public String getArticleLocation() {
		return articleLocation;
	}

	public void setArticleLocation(String articleLocation) {
		this.articleLocation = articleLocation;
	}

	public Integer getArticleMaxCharPerPage() {
		return articleMaxCharPerPage;
	}

	public void setArticleMaxCharPerPage(Integer articleMaxCharPerPage) {
		this.articleMaxCharPerPage = articleMaxCharPerPage;
	}

	public byte getArticlePaginationType() {
		return articlePaginationType;
	}

	public void setArticlePaginationType(byte articlePaginationType) {
		this.articlePaginationType = articlePaginationType;
	}

	public Integer getArticleHit() {
		return articleHit;
	}

	public void setArticleHit(Integer articleHit) {
		this.articleHit = articleHit;
	}

	public String getArticleRelation() {
		return articleRelation;
	}

	public void setArticleRelation(String articleRelation) {
		this.articleRelation = articleRelation;
	}

	public String getArticleAlbum() {
		return articleAlbum;
	}

	public void setArticleAlbum(String articleAlbum) {
		this.articleAlbum = articleAlbum;
	}

	public String getArticleIsParse() {
		return articleIsParse;
	}

	public void setArticleCategory(Category articleCategory) {
		this.articleCategory = articleCategory;
	}

	public Author getArticleAuthor() {
		return articleAuthor;
	}

	public void setArticleAuthor(Author articleAuthor) {
		this.articleAuthor = articleAuthor;
	}

	public CopyFrom getArticleFrom() {
		return articleFrom;
	}

	public void setArticleFrom(CopyFrom articleFrom) {
		this.articleFrom = articleFrom;
	}

	public PageLink getArticleLink() {
		return articleLink;
	}

	public void setArticleLink(PageLink articleLink) {
		this.articleLink = articleLink;
	}

	public ArticleContent getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(ArticleContent articleContent) {
		this.articleContent = articleContent;
	}

}
