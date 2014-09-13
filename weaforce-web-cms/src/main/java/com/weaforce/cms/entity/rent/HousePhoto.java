package com.weaforce.cms.entity.rent;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.util.DateUtil;

/**
 * 房间照片管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_rent_house_photo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class HousePhoto implements Serializable {
	private static final long serialVersionUID = -9007889228619993159L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "135", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "PHOTO_ID", length = 20)
	private Long photoId;
	// 名称
	@Column(name = "PHOTO_NAME", length = 45)
	private String photoName;
	// 小图URL
	@Column(name = "PHOTO_URL_SMALL", length = 45)
	private String photoUrlSmall;
	// 原图URL
	@Column(name = "PHOTO_URL", length = 45)
	private String photoUrl;
	// 发布时间
	@Column(name = "CREATE_TIME", length = 20)
	private Long createTime;
	// 所属房间
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PHOTO_HOUSE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_PHOTO_HOUSE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private House photoHouse;
	
	// 发布日期
	@Transient
	private String date;

	public HousePhoto() {
		createTime = System.currentTimeMillis();
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

	public String getPhotoUrlSmall() {
		return photoUrlSmall;
	}

	public void setPhotoUrlSmall(String photoUrlSmall) {
		this.photoUrlSmall = photoUrlSmall;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public House getPhotoHouse() {
		return photoHouse;
	}

	public void setPhotoHouse(House photoHouse) {
		this.photoHouse = photoHouse;
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
}
