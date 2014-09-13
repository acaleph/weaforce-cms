package com.weaforce.cms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;

/**
 * 页面元素定义管理类:其内容节结包括文章、像集、视频等，频道、栏目节结点将被删除
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_page_link", uniqueConstraints = @UniqueConstraint(columnNames = {
		"LINK_NAME", "LINK_FID" }))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = true)
public class PageLink extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 2581654822729440788L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "94", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "LINK_ID", length = 20)
	private Long linkId;
	// 排序
	@Column(name = "LINK_ORDER", length = 10)
	private Integer linkOrder;
	// 短名
	@Column(name = "LINK_NAME", length = 45)
	private String linkName;
	// 标题
	@Column(name = "LINK_TITLE", length = 90)
	private String linkTitle;
	// 标签码,由用户自己编码
	@Column(name = "LINK_LABEL_CODE", length = 3)
	private String linkLabelCode;
	// 链接
	@Column(name = "LINK_URL", length = 90)
	private String linkUrl;
	// 图片
	@Column(name = "LINK_IMAGE_URL", length = 90)
	private String linkImageUrl;
	// 父link
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LINK_FID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_PAGE_LINK_FID")
	private PageLink linkParent;
	// 儿子links
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "linkParent", fetch = FetchType.LAZY)
	@org.hibernate.annotations.OrderBy(clause = "LINK_ORDER asc")
	private List<PageLink> linkChildren = new ArrayList<PageLink>();
	// 频道文章页:允许为null,Parse过程,自动将其写为index.html文件,存放在channel指定的目录下
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinColumn(name = "LINK_CHANNEL_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_PAGE_LINK_CHANNEL_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Channel linkChannel;
	// 类频道栏目:允许为null,Parse过程,自动将其写为栏目categoryParseName.html文件,存放在channel指定的目录下
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinColumn(name = "LINK_CATEGORY_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_PAGE_LINK_CATEGORY_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Category linkCategory;
	// 类频道像集
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinColumn(name = "LINK_ALBUM_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_PAGE_LINK_ALBUM_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Album linkAlbum;
	// 文章:可以为null
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "LINK_ARTICLE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_PAGE_LINK_ARTICLE_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Article linkArticle;
	// 照片:可以为null
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "LINK_PHOTO_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_PAGE_LINK_PHOTO_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private AlbumPhoto linkPhoto;

	public PageLink() {
		linkLabelCode = "000";
	}

	public Long getLinkId() {
		return linkId;
	}

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	public Integer getLinkOrder() {
		return linkOrder;
	}

	public void setLinkOrder(Integer linkOrder) {
		this.linkOrder = linkOrder;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
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

	public String getLinkImageUrl() {
		return linkImageUrl;
	}

	public void setLinkImageUrl(String linkImageUrl) {
		this.linkImageUrl = linkImageUrl;
	}

	public PageLink getLinkParent() {
		return linkParent;
	}

	public void setLinkParent(PageLink linkParent) {
		this.linkParent = linkParent;
	}

	public List<PageLink> getLinkChildren() {
		return linkChildren;
	}

	public void setLinkChildren(List<PageLink> linkChildren) {
		this.linkChildren = linkChildren;
	}

	public Channel getLinkChannel() {
		return linkChannel;
	}

	public void setLinkChannel(Channel linkChannel) {
		this.linkChannel = linkChannel;
	}

	public Category getLinkCategory() {
		return linkCategory;
	}

	public void setLinkCategory(Category linkCategory) {
		this.linkCategory = linkCategory;
	}

	public Album getLinkAlbum() {
		return linkAlbum;
	}

	public void setLinkAlbum(Album linkAlbum) {
		this.linkAlbum = linkAlbum;
	}

	public Article getLinkArticle() {
		return linkArticle;
	}

	public void setLinkArticle(Article linkArticle) {
		this.linkArticle = linkArticle;
	}

	public AlbumPhoto getLinkPhoto() {
		return linkPhoto;
	}

	public void setLinkPhoto(AlbumPhoto linkPhoto) {
		this.linkPhoto = linkPhoto;
	}

}
