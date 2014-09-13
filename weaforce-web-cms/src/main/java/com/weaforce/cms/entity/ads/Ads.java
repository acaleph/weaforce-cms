package com.weaforce.cms.entity.ads;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.util.CollectionUtil;
import com.weaforce.core.util.DateUtil;
import com.weaforce.entity.area.Zone;

/**
 * 广告管理类
 * 
 * <p>
 * <ul>
 * <li>Parse path:/province/city/zone/catalog/createTime.html</li>
 * <li>Parse setup:category.parsePath</li>
 * </ul>
 * </p>
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_ads")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class Ads implements Serializable {
	private static final long serialVersionUID = 1026160401908306793L;
	// 主键
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "114", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "ADS_ID", length = 20)
	private Long adsId;
	// 名称
	@Column(name = "ADS_NAME", length = 180)
	private String adsName;
	// 简介
	@Column(name = "ADS_INTRO", length = 255)
	private String adsIntro;
	// 主页(注意与ADS_URL的区别)
	@Column(name = "ADS_WEB", length = 180)
	private String adsWeb;
	// 图片地址
	@Column(name = "ADS_PICTURE_URL", length = 90)
	private String adsPictureUrl;
	// 欢迎:短信平台
	@Column(name = "ADS_WELCOME", length = 180)
	private String adsWelcome;
	// 上联
	@Column(name = "ADS_INFO_LEFT", length = 45)
	private String adsInfoLeft;
	// 下联
	@Column(name = "ADS_INFO_RIGHT", length = 45)
	private String adsInfoRight;
	// 联系手机:短信平台
	@Column(name = "ADS_WELCOME_MOBILE", length = 20)
	private String adsWelcomeMobile;
	// 电话
	@Column(name = "ADS_PHONE", length = 20)
	private String adsPhone;
	// 接听时间
	@Column(name = "ADS_PHONE_TIME", length = 45)
	private String adsPhoneTime;
	// 接听日
	@Column(name = "ADS_PHONE_DAY", length = 45)
	private String adsPhoneDay;
	// 地址
	@Column(name = "ADS_ADDRESS", length = 20)
	private String adsAddress;
	// 描述
	@Column(name = "ADS_DESCRIPTION", length = 255)
	private String adsDescription;
	// 内容
	@Column(name = "ADS_CONTENT")
	private String adsContent;
	// 内容(HTML)
	@Column(name = "ADS_CONTENT_HTML")
	private String adsContentHtml;
	// 是否发布
	@Column(name = "ADS_IS_PARSE", length = 1)
	private String adsIsParse;
	// Parse后的WEB服务器URL(注意与ADS_WEB的区别)
	@Column(name = "ADS_URL", length = 90)
	private String adsUrl;
	// Parse后的物理地址
	@Column(name = "ADS_LOCATION", length = 90)
	private String adsLocation;
	// 地图坐标X
	@Column(name = "ADS_MAP_X", length = 20)
	private String adsMapX;
	// 地图坐标Y
	@Column(name = "ADS_MAP_Y", length = 20)
	private String adsMapY;
	// 热点级别
	@Column(name = "ADS_HOT_LEVEL", length = 3)
	private Byte adsHotLevel;
	// 点击次数
	@Column(name = "ADS_HIT", length = 10)
	private Integer adsHit;
	// 创建时间
	@Column(name = "CREATE_TIME", length = 20)
	private Long createTime;
	// 所属帐套
	@Column(name = "ADS_ACCOUNT_ID", length = 20)
	private Long adsAccountId;
	// 当前费用金额
	@Column(name = "ADS_PAY_TOTAL", precision = 14, scale = 4)
	private BigDecimal adsPayTotal;
	// 广告栏目
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ADS_CATEGORY_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_CATEGORY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private BizCategory adsCategory;
	// 广告标签
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ADS_TAG_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_TAG_ADS_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Tag adsTag;
	// 所在区（镇）
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ADS_ZONE_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_ZONE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Zone adsZone;
	// 广告栏目服务
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "cms_ads_service", joinColumns = { @JoinColumn(name = "ADS_ID") }, inverseJoinColumns = { @JoinColumn(name = "SERVICE_ID") })
	@Fetch(FetchMode.JOIN)
	@org.hibernate.annotations.ForeignKey(name = "FK_ADS_SERVICE_ID", inverseName = "FK_SERVICE_ADS_ID")
	@OrderBy("serviceOrder")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<BizCategoryService> adsService = new ArrayList<BizCategoryService>();
	// 广告服务:MTM扩展映射
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceAds")
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	// @OrderBy("serviceService.serviceOrder")
	private Set<AdsService> adsAdsService = new HashSet<AdsService>();
	// 广告短信
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "messageAds")
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<Message> adsAdsMessage = new ArrayList<Message>();
	// 广告风格
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "styleAds")
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@OrderBy("styleId")
	private Set<AdsStyle> adsStyle = new HashSet<AdsStyle>();

	@Transient
	private String date;

	public Ads() {
		adsIsParse = "0";
		adsHotLevel = 1;
		adsHit = 0;
		createTime = System.currentTimeMillis();
		adsPayTotal = BigDecimal.ZERO;
	}

	public Long getAdsId() {
		return adsId;
	}

	public void setAdsId(Long adsId) {
		this.adsId = adsId;
	}

	public String getAdsName() {
		return adsName;
	}

	public void setAdsName(String adsName) {
		this.adsName = adsName;
	}

	public String getAdsIntro() {
		return adsIntro;
	}

	public void setAdsIntro(String adsIntro) {
		this.adsIntro = adsIntro;
	}

	public String getAdsWeb() {
		return adsWeb;
	}

	public void setAdsWeb(String adsWeb) {
		this.adsWeb = adsWeb;
	}

	public String getAdsPictureUrl() {
		return adsPictureUrl;
	}

	public void setAdsPictureUrl(String adsPictureUrl) {
		this.adsPictureUrl = adsPictureUrl;
	}

	public String getAdsWelcome() {
		return adsWelcome;
	}

	public void setAdsWelcome(String adsWelcome) {
		this.adsWelcome = adsWelcome;
	}

	public String getAdsInfoLeft() {
		return adsInfoLeft;
	}

	public void setAdsInfoLeft(String adsInfoLeft) {
		this.adsInfoLeft = adsInfoLeft;
	}

	public String getAdsInfoRight() {
		return adsInfoRight;
	}

	public void setAdsInfoRight(String adsInfoRight) {
		this.adsInfoRight = adsInfoRight;
	}

	public String getAdsWelcomeMobile() {
		return adsWelcomeMobile;
	}

	public void setAdsWelcomeMobile(String adsWelcomeMobile) {
		this.adsWelcomeMobile = adsWelcomeMobile;
	}

	public String getAdsPhone() {
		return adsPhone;
	}

	public void setAdsPhone(String adsPhone) {
		this.adsPhone = adsPhone;
	}

	public String getAdsPhoneTime() {
		return adsPhoneTime;
	}

	public void setAdsPhoneTime(String adsPhoneTime) {
		this.adsPhoneTime = adsPhoneTime;
	}

	public String getAdsPhoneDay() {
		return adsPhoneDay;
	}

	public void setAdsPhoneDay(String adsPhoneDay) {
		this.adsPhoneDay = adsPhoneDay;
	}

	public String getAdsAddress() {
		return adsAddress;
	}

	public void setAdsAddress(String adsAddress) {
		this.adsAddress = adsAddress;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAdsDescription() {
		return adsDescription;
	}

	public void setAdsDescription(String adsDescription) {
		this.adsDescription = adsDescription;
	}

	public String getAdsContent() {
		return adsContent;
	}

	public void setAdsContent(String adsContent) {
		this.adsContent = adsContent;
	}

	public String getAdsContentHtml() {
		return adsContentHtml;
	}

	public void setAdsContentHtml(String adsContentHtml) {
		this.adsContentHtml = adsContentHtml;
	}

	public String getAdsIsParse() {
		return adsIsParse;
	}

	public void setAdsIsParse(String adsIsParse) {
		this.adsIsParse = adsIsParse;
	}

	public String getAdsUrl() {
		return adsUrl;
	}

	public void setAdsUrl(String adsUrl) {
		this.adsUrl = adsUrl;
	}

	public String getAdsLocation() {
		return adsLocation;
	}

	public void setAdsLocation(String adsLocation) {
		this.adsLocation = adsLocation;
	}

	public String getAdsMapX() {
		return adsMapX;
	}

	public void setAdsMapX(String adsMapX) {
		this.adsMapX = adsMapX;
	}

	public String getAdsMapY() {
		return adsMapY;
	}

	public void setAdsMapY(String adsMapY) {
		this.adsMapY = adsMapY;
	}

	public Byte getAdsHotLevel() {
		return adsHotLevel;
	}

	public void setAdsHotLevel(Byte adsHotLevel) {
		this.adsHotLevel = adsHotLevel;
	}

	public Integer getAdsHit() {
		return adsHit;
	}

	public void setAdsHit(Integer adsHit) {
		this.adsHit = adsHit;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getAdsAccountId() {
		return adsAccountId;
	}

	public void setAdsAccountId(Long adsAccountId) {
		this.adsAccountId = adsAccountId;
	}

	public BigDecimal getAdsPayTotal() {
		return adsPayTotal;
	}

	public void setAdsPayTotal(BigDecimal adsPayTotal) {
		this.adsPayTotal = adsPayTotal;
	}

	public BizCategory getAdsCategory() {
		return adsCategory;
	}

	public void setAdsCategory(BizCategory adsCategory) {
		this.adsCategory = adsCategory;
	}

	public Zone getAdsZone() {
		return adsZone;
	}

	public void setAdsZone(Zone adsZone) {
		this.adsZone = adsZone;
	}

	public Tag getAdsTag() {
		return adsTag;
	}

	public void setAdsTag(Tag adsTag) {
		this.adsTag = adsTag;
	}

	public List<BizCategoryService> getAdsService() {
		return adsService;
	}

	public void setAdsService(List<BizCategoryService> adsService) {
		this.adsService = adsService;
	}

	// Many-to-many with additional columns on join table, intermediate entity
	// class
	// To create a link, instantiate a AdsCategory with the right constructor
	// To remove a link, use getAdsAdsCategory().remove()
	public Set<AdsService> getAdsAdsService() {
		return adsAdsService;
	}

	public void setAdsAdsService(Set<AdsService> adsAdsService) {
		this.adsAdsService = adsAdsService;
	}

	public List<Message> getAdsAdsMessage() {
		return adsAdsMessage;
	}

	public void setAdsAdsMessage(List<Message> adsAdsMessage) {
		this.adsAdsMessage = adsAdsMessage;
	}

	public Set<AdsStyle> getAdsStyle() {
		return adsStyle;
	}

	public void setAdsStyle(Set<AdsStyle> adsStyle) {
		this.adsStyle = adsStyle;
	}

	/**
	 * 广告栏目服务名称
	 * 
	 * @return
	 * @throws Exception
	 */
	@Transient
	public String getAdsServiceNames() throws Exception {
		return CollectionUtil.fetchPropertyToString("", adsService,
				"serviceName", ",");
	}

	/**
	 * 广告栏目服务 IDS
	 * 
	 * @return
	 * @throws Exception
	 */
	@Transient
	public List<Long> getAdsServiceIds() throws Exception {
		return CollectionUtil.fetchPropertyToList("", adsService, "serviceId");
	}

	/**
	 * 返回W3C格式日期,如2008-08-08
	 * 
	 * @return
	 */
	public String getDate() {
		if (createTime == null || createTime == 0) {
			date = DateUtil.defaultFormat(new Date(System.currentTimeMillis()));
		} else {
			date = DateUtil.defaultFormat(new Date(createTime));
		}
		return date;
	}
}
