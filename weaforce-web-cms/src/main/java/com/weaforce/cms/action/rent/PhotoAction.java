package com.weaforce.cms.action.rent;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.rent.HousePhoto;
import com.weaforce.cms.service.IRentService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

/**
 * 房间照片管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/rent")
public class PhotoAction extends AbstractCrudAction<HousePhoto> {
	private static final long serialVersionUID = -8149245381346478933L;
	@Autowired
	@Qualifier("rentService")
	private IRentService rentService;

	private HousePhoto photo;
	private Long photoId;
	private Long houseId;

	private String queryName;

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	
	protected void prepareModel() throws Exception {
		photo = rentService.preparePhoto(photo, photoId, houseId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		photo = rentService.savePhoto(photo, houseId);
		return list();
	}

	
	public String delete() throws Exception {
		rentService.deletePhoto(photoId);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = rentService.getPhotoPage(pageInfo, houseId, queryName);
		return SUCCESS;
	}


	public HousePhoto getModel() {
		return photo;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
