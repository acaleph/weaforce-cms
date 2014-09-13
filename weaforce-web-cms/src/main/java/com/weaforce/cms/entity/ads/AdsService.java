package com.weaforce.cms.entity.ads;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

/**
 * 广告服务管理类:parse后文件名称:Ads + BizCategoryService.serviceFileName. 如：基本信息，简介，地图等
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_ads_service")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class AdsService implements Serializable {
	private static final long serialVersionUID = 3913980185266864606L;

	public AdsService() {
	}

	public AdsService(Ads serviceAds, BizCategoryService serviceService) {
		this.serviceAds = serviceAds;
		this.serviceService = serviceService;
		// Set primary key
		this.id = new Id(serviceAds.getAdsId(), serviceService.getServiceId());
		// Guarantee referential integrity
		serviceAds.getAdsAdsService().add(this);
		serviceService.getAdsAdsService().add(this);

	}

	/**
	 * Full constructor, the Ads And CatalogCategory instances have to have an
	 * identifier value, they have to be in detached or persistent state. This
	 * constructor takes care of the bidirectional relationship by adding the
	 * new instance to the collections on either side of the many-to-many
	 * association (added to the collections).
	 */
	public AdsService(Ads serviceAds, BizCategoryService serviceService,
			String adsServiceTitle, String adsServiceContent,
			String adsServiceUrl, String adsServiceAlbum) {
		// Set private fields
		this.serviceAds = serviceAds;
		this.serviceService = serviceService;
		this.adsServiceTitle = adsServiceTitle;
		this.adsServiceContent = adsServiceContent;
		this.adsServiceUrl = adsServiceUrl;
		this.adsServiceAlbum = adsServiceAlbum;
		// Set primary key
		this.id = new Id(serviceAds.getAdsId(), serviceService.getServiceId());
		// Guarantee referential integrity
		serviceAds.getAdsAdsService().add(this);
		serviceService.getAdsAdsService().add(this);

	}

	/**
	 * Emedded composite identifier class that represents the primary key
	 * columns of the many-to-many join table.
	 */
	@Embeddable
	public static class Id implements Serializable {
		private static final long serialVersionUID = 987835064055115191L;
		@Column(name = "ADS_ID", length = 20)
		private Long adsId;
		@Column(name = "SERVICE_ID")
		private Long serviceId;

		public Id() {
		}

		public Id(Long adsId, Long serviceId) {
			this.serviceId = serviceId;
			this.adsId = adsId;
		}

		public boolean equals(Object o) {
			if (o instanceof Id) {
				Id that = (Id) o;
				return this.serviceId.equals(that.serviceId)
						&& this.adsId.equals(that.adsId);
			} else {
				return false;
			}
		}

		public int hashCode() {
			return adsId.hashCode() + serviceId.hashCode();
		}

		public Long getAdsId() {
			return adsId;
		}

		public void setAdsId(Long adsId) {
			this.adsId = adsId;
		}

		public Long getServiceId() {
			return serviceId;
		}

		public void setServiceId(Long serviceId) {
			this.serviceId = serviceId;
		}

	}

	@EmbeddedId
	private Id id;
	// 广告
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADS_ID", insertable = false, updatable = false)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_ADS_SERVICE_ID")
	private Ads serviceAds;
	// 广服务
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_ID", insertable = false, updatable = false)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_CATEGORY_SERVICE_ID")
	private BizCategoryService serviceService;
	// 标题
	@Column(name = "ADS_SERVICE_TITLE", length = 180)
	private String adsServiceTitle;
	// 内容
	@Column(name = "ADS_SERVICE_CONTENT")
	private String adsServiceContent;
	// parse后的访问URL
	@Column(name = "ADS_SERVICE_URL", length = 90)
	private String adsServiceUrl;
	// 像册集
	@Column(name = "ADS_SERVICE_ALBUM", length = 90)
	private String adsServiceAlbum;

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public Ads getServiceAds() {
		return serviceAds;
	}

	public void setServiceAds(Ads serviceAds) {
		this.serviceAds = serviceAds;
	}

	public BizCategoryService getServiceService() {
		return serviceService;
	}

	public void setServiceService(BizCategoryService serviceService) {
		this.serviceService = serviceService;
	}

	public String getAdsServiceTitle() {
		return adsServiceTitle;
	}

	public void setAdsServiceTitle(String adsServiceTitle) {
		this.adsServiceTitle = adsServiceTitle;
	}

	public String getAdsServiceContent() {
		return adsServiceContent;
	}

	public void setAdsServiceContent(String adsServiceContent) {
		this.adsServiceContent = adsServiceContent;
	}

	public String getAdsServiceUrl() {
		return adsServiceUrl;
	}

	public void setAdsServiceUrl(String adsServiceUrl) {
		this.adsServiceUrl = adsServiceUrl;
	}

	public int hashCode() {
		return id.hashCode();
	}

	public String getAdsServiceAlbum() {
		return adsServiceAlbum;
	}

	public void setAdsServiceAlbum(String adsServiceAlbum) {
		this.adsServiceAlbum = adsServiceAlbum;
	}

}
