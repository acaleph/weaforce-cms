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

@Entity
@Table(name = "cms_social_car_photo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class CarPhoto implements Serializable {
	private static final long serialVersionUID = 1693684418335473452L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "184", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "PHOTO_ID", length = 20)
	private Long photoId;
	// 名称
	@Column(name = "PHOTO_NAME", length = 45)
	private String photoName;
	// 上传前本地文件名称
	@Column(name = "PHOTO_FILE_NAME", length = 20)
	private String photoFileName;
	// 上传后文件物理地址(含文件名称)
	@Column(name = "PHOTO_LOCATION", length = 90)
	private String photoLocation;
	// 调整后的文件物理地址(含文件名称)
	@Column(name = "PHOTO_LOCATION_RESIZE", length = 90)
	private String photoLocationResize;
	// URL地址
	@Column(name = "PHOTO_URL", length = 90)
	private String photoUrl;
	// 调整后图片URL地址
	@Column(name = "PHOTO_URL_RESIZE", length = 90)
	private String photoUrlResize;
	// Car
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PHOTO_CAR_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_PHOTO_CAR_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Car photoCar;

	public CarPhoto() {

	}

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getPhotoLocation() {
		return photoLocation;
	}

	public void setPhotoLocation(String photoLocation) {
		this.photoLocation = photoLocation;
	}

	public String getPhotoLocationResize() {
		return photoLocationResize;
	}

	public void setPhotoLocationResize(String photoLocationResize) {
		this.photoLocationResize = photoLocationResize;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getPhotoUrlResize() {
		return photoUrlResize;
	}

	public void setPhotoUrlResize(String photoUrlResize) {
		this.photoUrlResize = photoUrlResize;
	}

	public Car getPhotoCar() {
		return photoCar;
	}

	public void setPhotoCar(Car photoCar) {
		this.photoCar = photoCar;
	}

}
