package com.weaforce.cms.entity.ads;

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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

/**
 * 广告风格：建筑面积/平均价格/楼盘类型等
 * 
 * @author yanjiacheng
 * 
 */
@Entity
@Table(name = "cms_ads_style")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class AdsStyle implements Serializable{
	private static final long serialVersionUID = -6166715897382090245L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "121", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "STYLE_ID", length = 20)
	private Long styleId;
	// 名称
	@Column(name = "STYLE_ITEM", length = 90)
	private String styleItem;
	// 广告
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "STYLE_ADS_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_STYLE_ADS_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Ads styleAds;
	public AdsStyle(){
		
	}
	public Long getStyleId() {
		return styleId;
	}
	public void setStyleId(Long styleId) {
		this.styleId = styleId;
	}
	public String getStyleItem() {
		return styleItem;
	}
	public void setStyleItem(String styleItem) {
		this.styleItem = styleItem;
	}
	public Ads getStyleAds() {
		return styleAds;
	}
	public void setStyleAds(Ads styleAds) {
		this.styleAds = styleAds;
	}
	
}
