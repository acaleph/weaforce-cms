package com.weaforce.cms.dao;

import java.util.List;

import com.weaforce.cms.entity.Album;
import com.weaforce.core.dao.IGenericDao;

public interface IAlbumDao extends IGenericDao<Album, Long> {
	/**
	 * 取得像册list
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	public List<Album> getAlbumList(String account, boolean flag);

	/**
	 * 取得像册 list
	 * 
	 * @param account
	 *            帐套
	 * @param albumName
	 *            像册名称
	 * @param dateFrom
	 *            开始时间
	 * @param dateTo
	 *            结束时间
	 * @return
	 */
	public List<Album> getAlbumList(String account, String albumName,
			Long dateFrom, Long dateTo);

	/**
	 * 根据文章，取得像集 list
	 * 
	 * @param albumIds
	 *            文章像集队列
	 * @return
	 */
	public List<Album> getAlbumListByAlbum(String albumIds);

	/**
	 * 取得像册JSON格式 list
	 * 
	 * @param account
	 *            帐套
	 * @param albumIds
	 *            像册主键集
	 * @param albumName
	 *            像册名称
	 * @param dateFrom
	 *            起始日期
	 * @param dateTo
	 *            终止日期
	 * @return
	 */
	public String getAlbumListJSON(String account, String albumIds,
			String albumName, String dateFrom, String dateTo);
}
