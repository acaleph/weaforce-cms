package com.weaforce.cms.dao;

import java.util.List;

import com.weaforce.cms.entity.AlbumPhoto;
import com.weaforce.core.dao.IGenericDao;

public interface IPhotoDao extends IGenericDao<AlbumPhoto, Long> {
	/**
	 * 根据像册,取得照片 list
	 * 
	 * @param album
	 *            像册主键集
	 * @return
	 */
	public List<AlbumPhoto> getPhotoListByAlbum(String album);
}
