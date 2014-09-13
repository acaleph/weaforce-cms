package com.weaforce.cms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.IPhotoDao;
import com.weaforce.cms.entity.AlbumPhoto;
import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.core.util.StringUtil;

@Repository("albumPhotoDao")
public class PhotoDao extends GenericDao<AlbumPhoto, Long> implements IPhotoDao {
	private static final String QUERY_PHOTO = "From AlbumPhoto a ";

	/**
	 * 根据像册,取得照片 list
	 * 
	 * @param album
	 *            像册主键集
	 * @return
	 */
	public List<AlbumPhoto> getPhotoListByAlbum(String album) {
		if (StringUtil.isNotEmpty(album))
			return listQuery(QUERY_PHOTO + " Where a.photoAlbum.albumId in ( "
					+ album + ") ");
		else
			return new ArrayList<AlbumPhoto>();
	}
}
