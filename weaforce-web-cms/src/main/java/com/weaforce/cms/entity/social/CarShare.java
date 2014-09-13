package com.weaforce.cms.entity.social;

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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.entity.area.City;
import com.weaforce.entity.area.Zone;

@Entity
@Table(name = "cms_social_car_share")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class CarShare implements Serializable {
	private static final long serialVersionUID = 5019417820203246418L;

	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "181", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "SHARE_ID", length = 20)
	private Long shareId;
	// Title
	@Column(name = "SHARE_TITLE", length = 45)
	private String shareTitle;
	// Seat
	@Column(name = "SHARE_SEAT", length = 3)
	private Byte shareSeat;
	// Contact phone
	@Column(name = "SHARE_PHONE", length = 20)
	private String sharePhone;
	// Contact
	@Column(name = "SHARE_CONTACT", length = 20)
	private String shareContact;
	// Start date
	@Column(name = "SHARE_START_DATE", length = 45)
	private Long shareStartDate;
	// End date
	@Column(name = "SHARE_END_DATE", length = 45)
	private Long shareEndDate;
	// Description
	@Column(name = "SHARE_DESCRIPTION", length = 255)
	private String shareDescription;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "SHARE_CAR_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_SHARE_CAR_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Car shareCar;
	// Share Type
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "SHARE_TYPE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_CAR_SHARE_TYPE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private CarShareType shareType;
	// City from
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "SHARE_CITY_FROM_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_SHARE_FROM_CITY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private City shareCityFrom;
	// City to
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "SHARE_CITY_TO_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_SHARE_TO_CITY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private City shareCityTo;
	// Zone from
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "SHARE_ZONE_FROM_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_SHARE_FROM_ZONE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Zone shareZoneFrom;
	// Zone to
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "SHARE_ZONE_TO_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_SHARE_TO_ZONE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Zone shareZoneTo;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "requestShare")
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@OrderBy("requestUserLogin")
	private List<CarShareRequest> sharRequest = new ArrayList<CarShareRequest>();

	public CarShare() {

	}

	public Long getShareId() {
		return shareId;
	}

	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public Byte getShareSeat() {
		return shareSeat;
	}

	public void setShareSeat(Byte shareSeat) {
		this.shareSeat = shareSeat;
	}

	public String getSharePhone() {
		return sharePhone;
	}

	public void setSharePhone(String sharePhone) {
		this.sharePhone = sharePhone;
	}

	public String getShareContact() {
		return shareContact;
	}

	public void setShareContact(String shareContact) {
		this.shareContact = shareContact;
	}

	public Long getShareStartDate() {
		return shareStartDate;
	}

	public void setShareStartDate(Long shareStartDate) {
		this.shareStartDate = shareStartDate;
	}

	public Long getShareEndDate() {
		return shareEndDate;
	}

	public void setShareEndDate(Long shareEndDate) {
		this.shareEndDate = shareEndDate;
	}

	public String getShareDescription() {
		return shareDescription;
	}

	public void setShareDescription(String shareDescription) {
		this.shareDescription = shareDescription;
	}

	public Car getShareCar() {
		return shareCar;
	}

	public void setShareCar(Car shareCar) {
		this.shareCar = shareCar;
	}

	public CarShareType getShareType() {
		return shareType;
	}

	public void setShareType(CarShareType shareType) {
		this.shareType = shareType;
	}

	public City getShareCityFrom() {
		return shareCityFrom;
	}

	public void setShareCityFrom(City shareCityFrom) {
		this.shareCityFrom = shareCityFrom;
	}

	public City getShareCityTo() {
		return shareCityTo;
	}

	public void setShareCityTo(City shareCityTo) {
		this.shareCityTo = shareCityTo;
	}

	public Zone getShareZoneFrom() {
		return shareZoneFrom;
	}

	public void setShareZoneFrom(Zone shareZoneFrom) {
		this.shareZoneFrom = shareZoneFrom;
	}

	public Zone getShareZoneTo() {
		return shareZoneTo;
	}

	public void setShareZoneTo(Zone shareZoneTo) {
		this.shareZoneTo = shareZoneTo;
	}

	public List<CarShareRequest> getSharRequest() {
		return sharRequest;
	}

	public void setSharRequest(List<CarShareRequest> sharRequest) {
		this.sharRequest = sharRequest;
	}

}
