package com.weaforce.cms.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Album;
import com.weaforce.cms.entity.AlbumPhoto;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

@ParentPackage("default")
@Namespace("/cms")
public class PhotoAction extends AbstractCrudAction<AlbumPhoto> {
	private static final long serialVersionUID = -2153623343932875360L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	private AlbumPhoto photo;
	private Long photoId;
	private Long albumId;

	private List<java.io.File> uploads = new ArrayList<java.io.File>();
	private List<String> uploadFileName = new ArrayList<String>();
	private List<String> photoNames = new ArrayList<String>();
	private List<Integer> photoWidths = new ArrayList<Integer>();
	private List<Integer> photoHeights = new ArrayList<Integer>();

	private String queryName;

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	
	protected void prepareModel() throws Exception {
		photo = cmsService.preparePhoto(photo, photoId, albumId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		photo = cmsService.saveAlbum(photo, albumId);
		return list();
	}

	
	public String delete() throws Exception {
		cmsService.deletePhoto(photoId);
		return list();
	}

	
	public String list() throws Exception {
		if (albumId != null)
			pageInfo = cmsService.getPhotoPage(pageInfo, getAdmin()
					.getAccount(), albumId, queryName);
		return SUCCESS;
	}

	/**
	 * 照片上传页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String photo() throws Exception {
		return "photo";
	}

	/**
	 * 完成照片上传
	 * 
	 * @return
	 * @throws Exception
	 */
	public String upload() throws Exception {
		cmsService.uploadPhoto(albumId, uploadFileName, uploads, photoNames,
				photoWidths, photoHeights);
		return list();
	}

	public AlbumPhoto getModel() {
		return photo;
	}

	/**
	 * 取得像册list
	 * 
	 * @return
	 */

	public List<Album> getAlbumList() {
		return cmsService.getAlbumList(getAdmin().getAccount(), true);
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	/**
	 * 页面上传照片数组 getUpload() not getUploads()
	 * 
	 * @param uploads
	 */
	public void setUpload(List<java.io.File> uploads) {
		this.uploads = uploads;
	}

	/**
	 * 页面上传照片名称数组
	 * 
	 * @param uploadFileName
	 */
	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * 页面上传照片名称
	 * 
	 * @param photoNames
	 */
	public void setPhotoNames(List<String> photoNames) {
		this.photoNames = photoNames;
	}

	/**
	 * 页面上传照片宽度
	 * 
	 * @param photoWidths
	 */
	public void setPhotoWidths(List<Integer> photoWidths) {
		this.photoWidths = photoWidths;
	}

	/**
	 * 页面上传照片高度
	 * 
	 * @param photoHeights
	 */
	public void setPhotoHeights(List<Integer> photoHeights) {
		this.photoHeights = photoHeights;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
