package com.weaforce.cms.entity.ads;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * 广告栏目管理类:没有parse后的文件名称,默认为index.html
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_ads_category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class BizCategory implements Serializable {
	private static final long serialVersionUID = 7547371875948989233L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "32", allocationSize = 1)
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
	// 收费
	@Column(name = "CATEGORY_PRICE", precision = 10, scale = 4)
	private BigDecimal categoryPrice;
	// 制作费用
	@Column(name = "CATEGORY_TOTAL_PAY", precision = 10, scale = 4)
	private BigDecimal categoryTotalPay;
	// 压金费用
	@Column(name = "CATEGORY_TOTAL_ADS", precision = 10, scale = 4)
	private BigDecimal categoryTotalAds;
	// 网站费用
	@Column(name = "CATEGORY_TOTAL_WEB", precision = 10, scale = 4)
	private BigDecimal categoryTotalWeb;
	// parse 文件路径
	@Column(name = "CATEGORY_PARSE_PATH", length = 90)
	private String categoryParsePath;
	// css 文件路径
	@Column(name = "CATEGORY_CSS", length = 90)
	private String categoryCss;
	// 排序
	@Column(name = "CATEGORY_ORDER", length = 3)
	private Byte categoryOrder;
	// 描述
	@Column(name = "CATEGORY_DESCRIPTION", length = 255)
	private String categoryDescription;
	// 所属频道
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_CHANNEL_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_CATEGORY_CHANNEL_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private BizChannel categoryChannel;
	// 栏目模板
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_TEMPLATE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_CATEGORY_TEMPLATE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Template categoryTemplate;
	// 广告单元模板
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_TEMPLATE_ADS_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_CATEGORY_TEMPLATE_ADS_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Template categoryAdsTemplate;
	// 广告明星模板
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_TEMPLATE_ADS_STAR_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_CATEGORY_TEMPLATE_ADS_STAR_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Template categoryAdsStarTemplate;
	//网站模板
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_TEMPLATE_SITE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_CATEGORY_TEMPLATE_SITE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Template categorySiteTemplate;
	// 小贴士模板
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_TEMPLATE_TIP_ARTICLE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_CATEGORY_TEMPLATE_TIP_ARTICLE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Template categoryTipArticleTemplate;
	// 小贴士模板
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_TEMPLATE_TIP_LIST_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_CATEGORY_TEMPLATE_TIP_LIST_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Template categoryTipListTemplate;
	// 打折模板
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_TEMPLATE_DISCOUNT_ARTICLE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_CATEGORY_TEMPLATE_DISCOUNT_ARTICLE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Template categoryDiscountArticleTemplate;
	// 打折模板
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_TEMPLATE_DISCOUNT_LIST_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_CATEGORY_TEMPLATE_DISCOUNT_LIST_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Template categoryDiscountListTemplate;
	// 标签
	@OneToMany(mappedBy = "tagCategory", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.LAZY)
	@org.hibernate.annotations.OrderBy(clause = "TAG_ORDER asc")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Tag> categoryTag = new ArrayList<Tag>();
	// 广告栏目服务
	@OneToMany(mappedBy = "serviceCategory", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.LAZY)
	@org.hibernate.annotations.OrderBy(clause = "SERVICE_NAME asc")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<BizCategoryService> categoryService = new ArrayList<BizCategoryService>();
	// 广告
	@OneToMany(mappedBy = "adsCategory", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.LAZY)
	@org.hibernate.annotations.OrderBy(clause = "ADS_NAME asc")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Ads> categoryAds = new ArrayList<Ads>();

	public BizCategory() {

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

	public BigDecimal getCategoryPrice() {
		return categoryPrice;
	}

	public void setCategoryPrice(BigDecimal categoryPrice) {
		this.categoryPrice = categoryPrice;
	}

	public BigDecimal getCategoryTotalPay() {
		return categoryTotalPay;
	}

	public void setCategoryTotalPay(BigDecimal categoryTotalPay) {
		this.categoryTotalPay = categoryTotalPay;
	}

	public BigDecimal getCategoryTotalAds() {
		return categoryTotalAds;
	}

	public void setCategoryTotalAds(BigDecimal categoryTotalAds) {
		this.categoryTotalAds = categoryTotalAds;
	}

	public BigDecimal getCategoryTotalWeb() {
		return categoryTotalWeb;
	}

	public void setCategoryTotalWeb(BigDecimal categoryTotalWeb) {
		this.categoryTotalWeb = categoryTotalWeb;
	}

	public String getCategoryParsePath() {
		return categoryParsePath;
	}

	public void setCategoryParsePath(String categoryParsePath) {
		this.categoryParsePath = categoryParsePath;
	}

	public String getCategoryCss() {
		return categoryCss;
	}

	public void setCategoryCss(String categoryCss) {
		this.categoryCss = categoryCss;
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

	public BizChannel getCategoryChannel() {
		return categoryChannel;
	}

	public void setCategoryChannel(BizChannel categoryChannel) {
		this.categoryChannel = categoryChannel;
	}

	public Template getCategoryTemplate() {
		return categoryTemplate;
	}

	public void setCategoryTemplate(Template categoryTemplate) {
		this.categoryTemplate = categoryTemplate;
	}

	public Template getCategoryAdsTemplate() {
		return categoryAdsTemplate;
	}

	public void setCategoryAdsTemplate(Template categoryAdsTemplate) {
		this.categoryAdsTemplate = categoryAdsTemplate;
	}

	public Template getCategoryAdsStarTemplate() {
		return categoryAdsStarTemplate;
	}

	public void setCategoryAdsStarTemplate(Template categoryAdsStarTemplate) {
		this.categoryAdsStarTemplate = categoryAdsStarTemplate;
	}

	public Template getCategorySiteTemplate() {
		return categorySiteTemplate;
	}

	public void setCategorySiteTemplate(Template categorySiteTemplate) {
		this.categorySiteTemplate = categorySiteTemplate;
	}

	public Template getCategoryTipArticleTemplate() {
		return categoryTipArticleTemplate;
	}

	public void setCategoryTipArticleTemplate(
			Template categoryTipArticleTemplate) {
		this.categoryTipArticleTemplate = categoryTipArticleTemplate;
	}

	public Template getCategoryTipListTemplate() {
		return categoryTipListTemplate;
	}

	public void setCategoryTipListTemplate(Template categoryTipListTemplate) {
		this.categoryTipListTemplate = categoryTipListTemplate;
	}

	public Template getCategoryDiscountArticleTemplate() {
		return categoryDiscountArticleTemplate;
	}

	public void setCategoryDiscountArticleTemplate(
			Template categoryDiscountArticleTemplate) {
		this.categoryDiscountArticleTemplate = categoryDiscountArticleTemplate;
	}

	public Template getCategoryDiscountListTemplate() {
		return categoryDiscountListTemplate;
	}

	public void setCategoryDiscountListTemplate(
			Template categoryDiscountListTemplate) {
		this.categoryDiscountListTemplate = categoryDiscountListTemplate;
	}


	public List<Tag> getCategoryTag() {
		return categoryTag;
	}

	public void setCategoryTag(List<Tag> categoryTag) {
		this.categoryTag = categoryTag;
	}

	public List<BizCategoryService> getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(List<BizCategoryService> categoryService) {
		this.categoryService = categoryService;
	}

	public List<Ads> getCategoryAds() {
		return categoryAds;
	}

	public void setCategoryAds(List<Ads> categoryAds) {
		this.categoryAds = categoryAds;
	}

}
