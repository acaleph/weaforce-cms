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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.cms.entity.Album;
import com.weaforce.cms.entity.Template;

/**
 * 广告商家产品管理实体类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_ads_product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class Product implements Serializable {
	private static final long serialVersionUID = 4050946705533654718L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "160", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "PRODUCT_ID", length = 20)
	private Long productId;
	// 名称
	@Column(name = "PRODUCT_NAME", length = 45)
	private String productName;
	// 说明
	@Column(name = "PRODUCT_DESCRIPTION", length = 255)
	private String productDescription;
	// 所属商家
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ADS_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_CMS_PRODUCT_ADS_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Ads productAds;
	// 像集
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ALBUM_ID",nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_CMS_PRODUCT_ALBUM_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Album productAlbum;
	//模板
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_TEMPLATE_ID",nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_CMS_PRODUCT_TEMPLATE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Template productTemplate;
	//相关交易
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "dealProduct", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Deal> productDeal = new ArrayList<Deal>();
	
	public Product() {

	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Ads getProductAds() {
		return productAds;
	}

	public void setProductAds(Ads productAds) {
		this.productAds = productAds;
	}

	public Album getProductAlbum() {
		return productAlbum;
	}

	public void setProductAlbum(Album productAlbum) {
		this.productAlbum = productAlbum;
	}

	public Template getProductTemplate() {
		return productTemplate;
	}

	public void setProductTemplate(Template productTemplate) {
		this.productTemplate = productTemplate;
	}

	public List<Deal> getProductDeal() {
		return productDeal;
	}

	public void setProductDeal(List<Deal> productDeal) {
		this.productDeal = productDeal;
	}

}
