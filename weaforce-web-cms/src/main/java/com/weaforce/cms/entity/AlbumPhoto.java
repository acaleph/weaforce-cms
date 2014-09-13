package com.weaforce.cms.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;

/**
 * 像片管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_album_photo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@Proxy(lazy = true)
public class AlbumPhoto extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = -2676569861612672064L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "91", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "PHOTO_ID", length = 20)
	private Long photoId;
	// 名称
	@Column(name = "PHOTO_NAME", length = 45)
	private String photoName;
	// 上传前本地文件名称
	@Column(name = "PHOTO_FILE_NAME", length = 20)
	private String photoFileName;
	// 描述
	@Column(name = "PHOTO_DESCRIPTION", length = 255)
	private String photoDescription;
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
	// 调整后宽度
	@Column(name = "PHOTO_WIDTH", length = 10)
	private Integer photoWidth;
	// 调整后高度
	@Column(name = "PHOTO_HEIGHT", length = 10)
	private Integer photoHeight;
	// 所属像册
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PHOTO_ALBUM_ID")
	@org.hibernate.annotations.ForeignKey(name = "FK_PHOTO_ALBUM_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Album photoAlbum;
	// 自定义页面
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "linkPhoto", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "LINK_ID")
	private PageLink photoLink;

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

	public String getPhotoDescription() {
		return photoDescription;
	}

	public void setPhotoDescription(String photoDescription) {
		this.photoDescription = photoDescription;
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

	public Integer getPhotoWidth() {
		return photoWidth;
	}

	public void setPhotoWidth(Integer photoWidth) {
		this.photoWidth = photoWidth;
	}

	public Integer getPhotoHeight() {
		return photoHeight;
	}

	public void setPhotoHeight(Integer photoHeight) {
		this.photoHeight = photoHeight;
	}

	public Album getPhotoAlbum() {
		return photoAlbum;
	}

	public void setPhotoAlbum(Album photoAlbum) {
		this.photoAlbum = photoAlbum;
	}

	public PageLink getPhotoLink() {
		return photoLink;
	}

	public void setPhotoLink(PageLink photoLink) {
		this.photoLink = photoLink;
	}

}
