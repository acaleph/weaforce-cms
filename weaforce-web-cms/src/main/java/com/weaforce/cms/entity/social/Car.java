package com.weaforce.cms.entity.social;

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
//import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name = "cms_social_car")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Proxy(lazy = true)
//@Indexed
public class Car implements Serializable {
	private static final long serialVersionUID = 8441524184658278360L;

	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "179", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "CAR_ID", length = 20)
	private Long carId;
	// 车牌号
	@Column(name = "CAR_NO", length = 20)
	private String carNo;
	// 车牌号
	@Column(name = "CAR_OWNER", length = 45)
	private String carOwner;
	//Phone
	@Column(name = "CAR_PHONE", length = 20)
	private String carPhone;
	@Column(name = "CAR_PHOTO_URL", length = 90)
	private String carPhotoUrl;
	@Column(name = "CAR_DEFAULT", length = 1)
	private String carDeault;
	// Brand
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CAR_SERIES_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_CAR_SERIES_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private CarSeries carSeries;

	public Car() {
		carDeault = "1";
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}

	public String getCarPhone() {
		return carPhone;
	}

	public void setCarPhone(String carPhone) {
		this.carPhone = carPhone;
	}

	public String getCarPhotoUrl() {
		return carPhotoUrl;
	}

	public void setCarPhotoUrl(String carPhotoUrl) {
		this.carPhotoUrl = carPhotoUrl;
	}

	public String getCarDeault() {
		return carDeault;
	}

	public void setCarDeault(String carDeault) {
		this.carDeault = carDeault;
	}

	public CarSeries getCarSeries() {
		return carSeries;
	}

	public void setCarSeries(CarSeries carSeries) {
		this.carSeries = carSeries;
	}

}
