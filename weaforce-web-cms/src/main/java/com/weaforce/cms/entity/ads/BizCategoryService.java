package com.weaforce.cms.entity.ads;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.cms.entity.Template;

/**
 * 服务定义
 * 
 * @author yanjiacheng
 * 
 */
/**
 * 广告服务定义管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_ads_category_service")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class BizCategoryService implements Serializable {
	private static final long serialVersionUID = 85005766543679737L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "116", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "SERVICE_ID", length = 20)
	private Long serviceId;
	// 名称
	@Column(name = "SERVICE_NAME", length = 45)
	private String serviceName;
	// 文件名称(作为Ads.createTime的扩展名,如11225555-intro.html)
	@Column(name = "SERVICE_FILE_NAME", length = 10)
	private String serviceFileName;
	// 排序（辅助标签）
	@Column(name = "SERVICE_ORDER", length = 3)
	private String serviceOrder;
	// 描述
	@Column(name = "SERVICE_DESCRIPTION", length = 255)
	private String serviceDescription;
	// 广告目录类别:预备给cms_ads_service MTM选择
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_CATEGORY_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_SERVICE_CATEGORY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private BizCategory serviceCategory;
	// 广告/服务
	@ManyToMany(mappedBy = "adsService", targetEntity = Ads.class, fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Ads> serviceAds = new ArrayList<Ads>();
	// 广告服务:MTM扩展映射
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceService")
	private Set<AdsService> adsAdsService = new HashSet<AdsService>();
	// 模板
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_TEMPLATE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_CATEGORY_SERVICE_TEMPLATE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Template serviceTemplate;

	public BizCategoryService() {

	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceFileName() {
		return serviceFileName;
	}

	public void setServiceFileName(String serviceFileName) {
		this.serviceFileName = serviceFileName;
	}

	public String getServiceOrder() {
		return serviceOrder;
	}

	public void setServiceOrder(String serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public BizCategory getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(BizCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public List<Ads> getServiceAds() {
		return serviceAds;
	}

	public void setServiceAds(List<Ads> serviceAds) {
		this.serviceAds = serviceAds;
	}

	// Read-only, to create a link, instantiate a AdsCategory with the right
	// constructor
	// To remove a link, use Category.getAdsAdsCategory().remove()
	public Set<AdsService> getAdsAdsService() {
		return adsAdsService;
	}

	public void setAdsAdsService(Set<AdsService> adsAdsService) {
		this.adsAdsService = adsAdsService;
	}

	public Template getServiceTemplate() {
		return serviceTemplate;
	}

	public void setServiceTemplate(Template serviceTemplate) {
		this.serviceTemplate = serviceTemplate;
	}

}
