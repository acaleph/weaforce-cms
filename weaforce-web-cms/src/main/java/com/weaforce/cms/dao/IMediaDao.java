package com.weaforce.cms.dao;

import java.util.List;

import com.weaforce.cms.entity.Media;
import com.weaforce.core.dao.IGenericDao;

public interface IMediaDao extends IGenericDao<Media, Long> {
	/**
	 * Get media instance by URL
	 * 
	 * @param url
	 *            URL
	 * @return
	 */
	public Media getMediaByUrl(String url);
	
	/**
	 * Query media list by query string
	 * 
	 * @param query
	 *            Query parameter
	 * @return
	 */
	public List<Media> getMediaList(String query);
}
