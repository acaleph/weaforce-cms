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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "cms_image")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Image implements Serializable {
	private static final long serialVersionUID = -5120385182631751042L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "194", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "IMAGE_ID", length = 20)
	private Long imageId;
	// File name on disk
	@Column(name = "IMAGE_NAME_PHYSICAL", length = 45, nullable = true)
	private String imageNamePhysical;
	// FIle name from user input
	@Column(name = "IMAGE_NAME", length = 45, nullable = true)
	private String imageName;
	// Year
	@Column(name = "IMAGE_YEAR")
	private String imageYear;
	// Month
	@Column(name = "IMAGE_MONTH")
	private String imageMonth;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IMAGE_FID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_CMS_IMAGE_FID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Image imageParent;
	// Image parent
	@OneToMany(mappedBy = "imageParent", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	List<Image> imageChildren = new ArrayList<Image>();

	public Image() {

	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getImageNamePhysical() {
		return imageNamePhysical;
	}

	public void setImageNamePhysical(String imageNamePhysical) {
		this.imageNamePhysical = imageNamePhysical;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Image getImageParent() {
		return imageParent;
	}

	public void setImageParent(Image imageParent) {
		this.imageParent = imageParent;
	}

	public List<Image> getImageChildren() {
		return imageChildren;
	}

	public void setImageChildren(List<Image> imageChildren) {
		this.imageChildren = imageChildren;
	}

	public String getImageYear() {
		return imageYear;
	}

	public void setImageYear(String imageYear) {
		this.imageYear = imageYear;
	}

	public String getImageMonth() {
		return imageMonth;
	}

	public void setImageMonth(String imageMonth) {
		this.imageMonth = imageMonth;
	}

}
