package com.weaforce.cms.entity.rent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.util.DateUtil;

/**
 * 房间管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_rent_house")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class House implements Serializable {
	private static final long serialVersionUID = -2556091528160074728L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "134", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "HOUSE_ID", length = 20)
	private Long houseId;
	// 标题
	@Column(name = "HOUSE_TITLE", length = 45)
	private String houseTitle;
	// 楼层
	@Column(name = "HOUSE_LAYER", length = 3)
	private Byte houseLayer;
	// 房间号
	@Column(name = "HOUSE_NUMBER", length = 10)
	private String houseNumber;
	// 名称
	@Column(name = "HOUSE_OWNER_NAME", length = 20)
	private String houseOwnerName;
	// 手机
	@Column(name = "HOUSE_OWNER_MOBILE", length = 20)
	private String houseOwnerMobile;
	// 电话
	@Column(name = "HOUSE_OWNER_PHONE", length = 20)
	private String houseOwnerPhone;
	// 联系人
	@Column(name = "HOUSE_CONTACT", length = 20)
	private String houseContact;
	// 手机
	@Column(name = "HOUSE_CONTACT_MOBILE", length = 20)
	private String houseContactMobile;
	// 电话
	@Column(name = "HOUSE_CONTACT_PHONE", length = 20)
	private String houseContactPhone;
	// 发布时间
	@Column(name = "CREATE_TIME", length = 20)
	private Long createTime;
	// 更新时间
	@Column(name = "LAST_UPDATE_TIME", length = 20)
	private Long lastUpdateTime;
	// 面积
	@Column(name = "HOUSE_SQUARE", precision = 10, scale = 2)
	private BigDecimal houseSquare;
	// 活动
	@Column(name = "HOUSE_IS_ACTIVE", length = 1)
	private String houseIsActive;
	// 户型
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "HOUSE_TYPE_ID", nullable = false)
	@org.hibernate.annotations.ForeignKey(name = "FK_HOUSE_TYPE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private HouseType houseType;
	// 月租(标签)
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "HOUSE_TAG_ID", nullable = false)
	@org.hibernate.annotations.ForeignKey(name = "FK_HOUSE_TAG_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private RentTag houseTag;
	// 所属建筑(已经包含区域)
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "HOUSE_BUILDING_ID", nullable = false)
	@org.hibernate.annotations.ForeignKey(name = "FK_HOUSE_BUILDING_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Building houseBuilding;
	// 照片：用来判断是否有照片
	@OneToMany(mappedBy = "photoHouse", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.LAZY)
	@org.hibernate.annotations.OrderBy(clause = "CREATE_TIME asc")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<HousePhoto> housePhoto = new HashSet<HousePhoto>();
	// 发布日期
	@Transient
	private String date;
	@Transient
	private String hasPhoto;

	public House() {
		createTime = System.currentTimeMillis();
		houseIsActive = "1";
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public String getHouseTitle() {
		return houseTitle;
	}

	public void setHouseTitle(String houseTitle) {
		this.houseTitle = houseTitle;
	}

	public Byte getHouseLayer() {
		return houseLayer;
	}

	public void setHouseLayer(Byte houseLayer) {
		this.houseLayer = houseLayer;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getHouseOwnerName() {
		return houseOwnerName;
	}

	public void setHouseOwnerName(String houseOwnerName) {
		this.houseOwnerName = houseOwnerName;
	}

	public String getHouseOwnerMobile() {
		return houseOwnerMobile;
	}

	public void setHouseOwnerMobile(String houseOwnerMobile) {
		this.houseOwnerMobile = houseOwnerMobile;
	}

	public String getHouseOwnerPhone() {
		return houseOwnerPhone;
	}

	public void setHouseOwnerPhone(String houseOwnerPhone) {
		this.houseOwnerPhone = houseOwnerPhone;
	}

	public String getHouseContact() {
		return houseContact;
	}

	public void setHouseContact(String houseContact) {
		this.houseContact = houseContact;
	}

	public String getHouseContactMobile() {
		return houseContactMobile;
	}

	public void setHouseContactMobile(String houseContactMobile) {
		this.houseContactMobile = houseContactMobile;
	}

	public String getHouseContactPhone() {
		return houseContactPhone;
	}

	public void setHouseContactPhone(String houseContactPhone) {
		this.houseContactPhone = houseContactPhone;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public BigDecimal getHouseSquare() {
		return houseSquare;
	}

	public void setHouseSquare(BigDecimal houseSquare) {
		this.houseSquare = houseSquare;
	}

	public String getHouseIsActive() {
		return houseIsActive;
	}

	public void setHouseIsActive(String houseIsActive) {
		this.houseIsActive = houseIsActive;
	}

	public HouseType getHouseType() {
		return houseType;
	}

	public void setHouseType(HouseType houseType) {
		this.houseType = houseType;
	}

	public RentTag getHouseTag() {
		return houseTag;
	}

	public void setHouseTag(RentTag houseTag) {
		this.houseTag = houseTag;
	}

	public Building getHouseBuilding() {
		return houseBuilding;
	}

	public void setHouseBuilding(Building houseBuilding) {
		this.houseBuilding = houseBuilding;
	}

	public Set<HousePhoto> getHousePhoto() {
		return housePhoto;
	}

	public void setHousePhoto(Set<HousePhoto> housePhoto) {
		this.housePhoto = housePhoto;
	}

	/**
	 * 返回W3C格式日期,如2008-08-08
	 * 
	 * @return
	 */
	public String getDate() {
		if (createTime == null || createTime == 0) {
			date = DateUtil
					.completeFormat(new Date(System.currentTimeMillis()));
		} else {
			date = DateUtil.completeFormat(new Date(createTime));
		}
		return date;
	}

	public String getHasPhoto() {
		if (this.housePhoto.size() > 0)
			this.hasPhoto = "1";
		return hasPhoto;
	}

}
