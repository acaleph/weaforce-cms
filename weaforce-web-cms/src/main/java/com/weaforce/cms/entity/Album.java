package com.weaforce.cms.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;

/**
 * 像片集管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_album")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = true)
public class Album extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 5916114466891342154L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "90", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "ALBUM_ID", length = 20)
	private Long albumId;
	// 名称
	@Column(name = "ALBUM_NAME", length = 45)
	private String albumName;
	// 文件名称
	@Column(name = "ALBUM_FILE_NAME", length = 45)
	private String albumFileName;
	// 标签代码:支持HTML多像册
	@Column(name = "ALBUM_LABEL_CODE", length = 10)
	private String albumLabelCode;
	// 路径
	@Column(name = "ALBUM_PATH", length = 45)
	private String albumPath;
	// parse类型
	@Column(name = "ALBUM_PARSE_TYPE", length = 1)
	private String albumParseType;
	// 描述
	@Column(name = "ALBUM_DESCRIPTION", length = 255)
	private String albumDescription;
	// 排序
	@Column(name = "ALBUM_ORDER", length = 3)
	private Byte albumOrder;
	// 小照片宽
	@Column(name = "ALBUM_SMALL_WIDTH", length = 10)
	private Integer albumSmallWidth;
	// 小照片高
	@Column(name = "ALBUM_SMALL_HEIGHT", length = 10)
	private Integer albumSmallHeight;
	// 父亲
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ALBUM_FID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ALBUM_FID")
	// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Album albumParent;
	// 孩子
	@OneToMany(mappedBy = "albumParent", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.LAZY)
	@org.hibernate.annotations.OrderBy(clause = "ALBUM_ORDER asc")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Album> albumChildren = new ArrayList<Album>();
	// 像集模板
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ALBUM_TEMPLATE_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_ALBUM_TEMPLATE_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Template albumTemplate;
	// 类频道像集:照片定义页
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "linkAlbum", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "LINK_ID")
	private PageLink albumLink;
	// 所属照片
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "photoAlbum", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<AlbumPhoto> albumPhoto = new ArrayList<AlbumPhoto>();

	public Album() {
		//默认模板中的标签为ALBUM
		albumLabelCode="ALBUM";
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumFileName() {
		return albumFileName;
	}

	public void setAlbumFileName(String albumFileName) {
		this.albumFileName = albumFileName;
	}

	public String getAlbumLabelCode() {
		return albumLabelCode;
	}

	public void setAlbumLabelCode(String albumLabelCode) {
		this.albumLabelCode = albumLabelCode;
	}

	public String getAlbumPath() {
		return albumPath;
	}

	public void setAlbumPath(String albumPath) {
		this.albumPath = albumPath;
	}

	public String getAlbumParseType() {
		return albumParseType;
	}

	public void setAlbumParseType(String albumParseType) {
		this.albumParseType = albumParseType;
	}

	public String getAlbumDescription() {
		return albumDescription;
	}

	public void setAlbumDescription(String albumDescription) {
		this.albumDescription = albumDescription;
	}

	public Byte getAlbumOrder() {
		return albumOrder;
	}

	public void setAlbumOrder(Byte albumOrder) {
		this.albumOrder = albumOrder;
	}

	public Integer getAlbumSmallWidth() {
		return albumSmallWidth;
	}

	public void setAlbumSmallWidth(Integer albumSmallWidth) {
		this.albumSmallWidth = albumSmallWidth;
	}

	public Integer getAlbumSmallHeight() {
		return albumSmallHeight;
	}

	public void setAlbumSmallHeight(Integer albumSmallHeight) {
		this.albumSmallHeight = albumSmallHeight;
	}

	public Album getAlbumParent() {
		return albumParent;
	}

	public void setAlbumParent(Album albumParent) {
		this.albumParent = albumParent;
	}

	public List<Album> getAlbumChildren() {
		return albumChildren;
	}

	public void setAlbumChildren(List<Album> albumChildren) {
		this.albumChildren = albumChildren;
	}

	public Template getAlbumTemplate() {
		return albumTemplate;
	}

	public void setAlbumTemplate(Template albumTemplate) {
		this.albumTemplate = albumTemplate;
	}

	public PageLink getAlbumLink() {
		return albumLink;
	}

	public void setAlbumLink(PageLink albumLink) {
		this.albumLink = albumLink;
	}

	public List<AlbumPhoto> getAlbumPhoto() {
		return albumPhoto;
	}

	public void setAlbumPhoto(List<AlbumPhoto> albumPhoto) {
		this.albumPhoto = albumPhoto;
	}

}
