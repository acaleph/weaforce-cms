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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.core.util.CollectionUtil;
import com.weaforce.entity.admin.User;

/**
 * 文章栏目管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Proxy(lazy = true)
public class Category extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 25592136163497648L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "21", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "CATEGORY_ID", length = 20)
	private Long categoryId;
	// Parse文件名
	@Column(name = "CATEGORY_PARSE_NAME", length = 20)
	private String categoryParseName;
	// Parse文件扩展名
	@Column(name = "CATEGORY_PARSE_NAME_EXT", length = 5)
	private String categoryParseNameExt;
	// 如果categoryArticleList=0：每页最大文章数
	@Column(name = "CATEGORY_MAX_PER_PAGE", length = 10)
	private Integer categoryMaxPerPage;
	// Name
	@Column(name = "CATEGORY_NAME", length = 45)
	private String categoryName;
	// URL
	@Column(name = "CATEGORY_URL", length = 45)
	private String categoryUrl;
	// 单一文章/列表
	@Column(name = "CATEGORY_PARSE_TYPE", length = 1)
	private String categoryParseType;
	// Order
	@Column(name = "CATEGORY_ORDER", length = 3)
	private Byte categoryOrder;
	// 描述
	@Column(name = "CATEGORY_DESCRIPTION", length = 255)
	private String categoryDescription;
	// Cell template content
	@Column(name = "CATEGORY_CELL_TEMPLATE", length = 500)
	private String categoryCellTemplate;
	// 文章打开方式
	@Column(name = "CATEGORY_ARTICLE_TARGET", length = 10)
	private String categoryArticleTarget;
	// 活动
	@Column(name = "CATEGORY_IS_ACTIVE", length = 1)
	private String categoryIsActive;
	// Channel
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_CHANNEL_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_CATEGORY_CHANNEL_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Channel categoryChannel;
	// Template of category
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_TEMPLATE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_CATEGORY_TEMPLATE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Template categoryTemplate;
	// Template or article
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ARTICLE_TEMPLATE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_CATEGORY_ARTICLE_TEMPLATE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Template categoryArticleTemplate;
	// 负责人
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "cms_category_user", joinColumns = { @JoinColumn(name = "CATEGORY_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	@org.hibernate.annotations.ForeignKey(name = "FK_CATEGORY_USER_ID", inverseName = "FK_USER_CATEGORY_ID")
	private List<User> categoryUser = new ArrayList<User>();
	// 频道自定义页面
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "linkCategory", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "LINK_ID")
	private PageLink categoryLink;
	// 文章
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "articleCategory", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Article> categoryArticle;

	public Category() {
		categoryParseType = "0";
		categoryIsActive = "1";
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryParseName() {
		return categoryParseName;
	}

	public void setCategoryParseName(String categoryParseName) {
		this.categoryParseName = categoryParseName;
	}

	public String getCategoryParseNameExt() {
		return categoryParseNameExt;
	}

	public void setCategoryParseNameExt(String categoryParseNameExt) {
		this.categoryParseNameExt = categoryParseNameExt;
	}

	public Integer getCategoryMaxPerPage() {
		return categoryMaxPerPage;
	}

	public void setCategoryMaxPerPage(Integer categoryMaxPerPage) {
		this.categoryMaxPerPage = categoryMaxPerPage;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 取得栏目URL:http://www.weaforce.com/enviroment.html
	 * 
	 * @return
	 */
	public String getCategoryUrl() {
		return categoryUrl;
	}

	public void setCategoryUrl(String categoryUrl) {
		this.categoryUrl = categoryUrl;
	}

	public String getCategoryParseType() {
		return categoryParseType;
	}

	public void setCategoryParseType(String categoryParseType) {
		this.categoryParseType = categoryParseType;
	}

	public Byte getCategoryOrder() {
		return categoryOrder;
	}

	public void setCategoryOrder(Byte categoryOrder) {
		this.categoryOrder = categoryOrder;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getCategoryArticleTarget() {
		return categoryArticleTarget;
	}

	public void setCategoryArticleTarget(String categoryArticleTarget) {
		this.categoryArticleTarget = categoryArticleTarget;
	}

	public String getCategoryCellTemplate() {
		return categoryCellTemplate;
	}

	public void setCategoryCellTemplate(String categoryCellTemplate) {
		this.categoryCellTemplate = categoryCellTemplate;
	}

	public String getCategoryIsActive() {
		return categoryIsActive;
	}

	public void setCategoryIsActive(String categoryIsActive) {
		this.categoryIsActive = categoryIsActive;
	}


	public Channel getCategoryChannel() {
		return categoryChannel;
	}

	public void setCategoryChannel(Channel categoryChannel) {
		this.categoryChannel = categoryChannel;
	}

	public Template getCategoryTemplate() {
		return categoryTemplate;
	}

	public void setCategoryTemplate(Template categoryTemplate) {
		this.categoryTemplate = categoryTemplate;
	}

	public Template getCategoryArticleTemplate() {
		return categoryArticleTemplate;
	}

	public void setCategoryArticleTemplate(Template categoryArticleTemplate) {
		this.categoryArticleTemplate = categoryArticleTemplate;
	}

	public List<User> getCategoryUser() {
		return categoryUser;
	}

	public void setCategoryUser(List<User> categoryUser) {
		this.categoryUser = categoryUser;
	}

	public String getCategoryStaffNames() throws Exception {
		return CollectionUtil.fetchPropertyToString(this.getAccount(),
				categoryUser, "userNameCn", ",");
	}

	public List<Long> getCategoryStaffIds() throws Exception {
		return CollectionUtil.fetchPropertyToList(this.getAccount(),
				categoryUser, "userId");
	}

	public PageLink getCategoryLink() {
		return categoryLink;
	}

	public void setCategoryLink(PageLink categoryLink) {
		this.categoryLink = categoryLink;
	}

	public List<Article> getCategoryArticle() {
		return categoryArticle;
	}

	public void setCategoryArticle(List<Article> categoryArticle) {
		this.categoryArticle = categoryArticle;
	}

}
