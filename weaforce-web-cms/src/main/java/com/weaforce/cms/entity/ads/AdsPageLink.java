package com.weaforce.cms.entity.ads;

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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.cms.entity.Template;

/**
 * 广告自定义页面管理类:不同的城市有不同的首页
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_ads_page_link", uniqueConstraints = @UniqueConstraint(columnNames = {
		"LINK_TITLE", "LINK_FID" }))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@Proxy(lazy = true)
public class AdsPageLink implements Serializable {
	private static final long serialVersionUID = 1112548481121221845L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "122", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "LINK_ID", length = 20)
	private Long linkId;
	// 标题
	@Column(name = "LINK_TITLE", length = 90)
	private String linkTitle;
	// 类型
	@Column(name = "LINK_TYPE", length = 1)
	private String linkType;
	// 标签码,由用户自己编码
	@Column(name = "LINK_LABEL_CODE", length = 3)
	private String linkLabelCode;
	// 链接
	@Column(name = "LINK_URL", length = 90)
	private String linkUrl;
	// 图片
	@Column(name = "LINK_PICTURE_URL", length = 90)
	private String linkPictureUrl;
	// 排序
	@Column(name = "LINK_ORDER", length = 3)
	private Byte linkOrder;
	// 级别
	@Column(name = "LINK_LEVEL", length = 3)
	private Byte linkLevel;
	// 物理路径
	@Column(name = "LINK_PARSE_PATH", length = 90)
	private String linkParsePath;
	// 文件名称
	@Column(name = "LINK_PARSE_FILE", length = 20)
	private String linkParseFile;
	// 父link
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LINK_FID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_PAGE_LINK_FID")
	private AdsPageLink linkParent;
	// 子工程
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "linkParent")
	@org.hibernate.annotations.OrderBy(clause = "LINK_ORDER asc")
	private List<AdsPageLink> linkChildren = new ArrayList<AdsPageLink>();
	// 模板
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "LINK_TEMPLATE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_PAGE_LINK_TEMPLATE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Template linkTemplate;
	//频道
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "LINK_CHANNEL_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_PAGE_LINK_CHANNEL_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private BizChannel linkChannel;
	//栏目
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "LINK_CATEGORY_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_PAGE_LINK_CATEGORY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private BizCategory linkCategory;
	//广告
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "LINK_ADS_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_PAGE_LINK_ADS_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Ads linkAds;
	

	public AdsPageLink() {
		linkLevel = 0;
	}

	public Long getLinkId() {
		return linkId;
	}

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public String getLinkLabelCode() {
		return linkLabelCode;
	}

	public void setLinkLabelCode(String linkLabelCode) {
		this.linkLabelCode = linkLabelCode;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLinkPictureUrl() {
		return linkPictureUrl;
	}

	public void setLinkPictureUrl(String linkPictureUrl) {
		this.linkPictureUrl = linkPictureUrl;
	}

	public Byte getLinkOrder() {
		return linkOrder;
	}

	public void setLinkOrder(Byte linkOrder) {
		this.linkOrder = linkOrder;
	}

	public Byte getLinkLevel() {
		return linkLevel;
	}

	public void setLinkLevel(Byte linkLevel) {
		this.linkLevel = linkLevel;
	}

	public String getLinkParsePath() {
		return linkParsePath;
	}

	public void setLinkParsePath(String linkParsePath) {
		this.linkParsePath = linkParsePath;
	}

	public String getLinkParseFile() {
		return linkParseFile;
	}

	public void setLinkParseFile(String linkParseFile) {
		this.linkParseFile = linkParseFile;
	}

	public AdsPageLink getLinkParent() {
		return linkParent;
	}

	public void setLinkParent(AdsPageLink linkParent) {
		this.linkParent = linkParent;
	}

	public List<AdsPageLink> getLinkChildren() {
		return linkChildren;
	}

	public void setLinkChildren(List<AdsPageLink> linkChildren) {
		this.linkChildren = linkChildren;
	}

	public Template getLinkTemplate() {
		return linkTemplate;
	}

	public void setLinkTemplate(Template linkTemplate) {
		this.linkTemplate = linkTemplate;
	}

	public BizChannel getLinkChannel() {
		return linkChannel;
	}

	public void setLinkChannel(BizChannel linkChannel) {
		this.linkChannel = linkChannel;
	}

	public BizCategory getLinkCategory() {
		return linkCategory;
	}

	public void setLinkCategory(BizCategory linkCategory) {
		this.linkCategory = linkCategory;
	}

	public Ads getLinkAds() {
		return linkAds;
	}

	public void setLinkAds(Ads linkAds) {
		this.linkAds = linkAds;
	}

}
