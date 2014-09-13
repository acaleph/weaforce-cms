package com.weaforce.cms.entity.forum;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.cms.entity.Template;

/**
 * 论坛类别管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_forum_category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class ForumCategory implements Serializable {
	private static final long serialVersionUID = 6155217686743469614L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "126", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "CATEGORY_ID", length = 20)
	private Long categoryId;
	// Parse文件名
	@Column(name = "CATEGORY_PARSE_NAME", length = 20)
	private String categoryParseName;
	// Parse文件扩展名
	@Column(name = "CATEGORY_PARSE_NAME_EXT", length = 5)
	private String categoryParseNameExt;
	// 名称
	@Column(name = "CATEGORY_NAME", length = 45)
	private String categoryName;
	// 地址:实施时,对WEB服务器进行规划时设置
	@Column(name = "CATEGORY_URL", length = 45)
	private String categoryUrl;
	// parse 文件路径
	@Column(name = "CATEGORY_PARSE_PATH", length = 90)
	private String categoryParsePath;
	// 排序
	@Column(name = "CATEGORY_ORDER", length = 3)
	private Byte categoryOrder;
	// 描述
	@Column(name = "CATEGORY_DESCRIPTION", length = 255)
	private String categoryDescription;
	// 所属频道
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_CHANNEL_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_FORUM_CATEGORY_CHANNEL_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private ForumChannel categoryChannel;
	// 栏目模板：应用于主题列表
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_TEMPLATE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_FORUM_CATEGORY_TEMPLATE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Template categoryTemplate;
	// 栏目
	@OneToMany(mappedBy = "forumCategory", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.LAZY)
	@org.hibernate.annotations.OrderBy(clause = "FORUM_ORDER asc")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Forum> categoryForum = new ArrayList<Forum>();

	public ForumCategory() {

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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryUrl() {
		return categoryUrl;
	}

	public void setCategoryUrl(String categoryUrl) {
		this.categoryUrl = categoryUrl;
	}

	public String getCategoryParsePath() {
		return categoryParsePath;
	}

	public void setCategoryParsePath(String categoryParsePath) {
		this.categoryParsePath = categoryParsePath;
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

	public ForumChannel getCategoryChannel() {
		return categoryChannel;
	}

	public void setCategoryChannel(ForumChannel categoryChannel) {
		this.categoryChannel = categoryChannel;
	}

	public Template getCategoryTemplate() {
		return categoryTemplate;
	}

	public void setCategoryTemplate(Template categoryTemplate) {
		this.categoryTemplate = categoryTemplate;
	}

	public List<Forum> getCategoryForum() {
		return categoryForum;
	}

	public void setCategoryForum(List<Forum> categoryForum) {
		this.categoryForum = categoryForum;
	}

}
