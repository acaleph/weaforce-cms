package com.weaforce.cms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.IAlbumDao;
import com.weaforce.cms.entity.Album;
import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.core.util.DateUtil;
import com.weaforce.core.util.StringUtil;

@Repository("albumDao")
public class AlbumDao extends GenericDao<Album, Long> implements IAlbumDao {

	private static final String QUERY_ALBUM = " From Album a Where a.account=? ";

	/**
	 * 取得像册list
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	public List<Album> getAlbumList(String account, boolean flag) {
		List<Album> albumList = listQuery(
				" From Album a Where a.account=? And a.albumParent=null Order by a.albumName ",
				account);
		if (albumList.size() > 0)
			albumChildren(albumList, 1);
		if (flag) {
			Album o = new Album();
			o.setAlbumName(" --- all --- ");
			albumList.add(0, o);
		}
		return albumList;
	}

	/**
	 * 取得孩子list:该方法没有出现在Interface中
	 * 
	 * @param albumList
	 * @param lastIndex
	 * @return
	 */
	public List<Album> albumChildren(List<Album> albumList, Integer lastIndex) {
		Album currentAlbum = albumList.get(lastIndex - 1);
		if (currentAlbum != null) {
			List<Album> albumChildren = currentAlbum.getAlbumChildren();
			if (albumChildren.size() > 0)
				albumList.addAll(lastIndex, albumChildren);
			if (lastIndex < albumList.size()) {
				lastIndex++;
				return albumChildren(albumList, lastIndex);
			}
		}
		return albumList;
	}

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
			Long dateFrom, Long dateTo) {
		String queryAlbum = QUERY_ALBUM;
		if (StringUtil.isNotEmpty(albumName))
			queryAlbum = queryAlbum + " And a.albumName like '%" + albumName
					+ "%'";
		if (dateFrom != null && dateTo != null)
			queryAlbum = queryAlbum + " And a.createTime >='" + dateFrom
					+ "' And a.createTime <='" + dateTo + "'";
		return listQuery(queryAlbum, account);
	}

	/**
	 * 根据文章，取得像集 list
	 * 
	 * @param album
	 *            文章像集队列
	 * @return
	 */
	public List<Album> getAlbumListByAlbum(String album) {
		if (StringUtil.isNotEmpty(album)) {
			return listQuery(" From Album a Where a.albumId in (" + album
					+ ") ");
		} else {
			return new ArrayList<Album>();
		}
	}
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
			String albumName, String dateFrom, String dateTo) {
		StringBuffer sb = new StringBuffer();
		List<Album> albumList = getAlbumList(account, albumName, DateUtil
				.getUTCDate(dateFrom), DateUtil.getUTCDate(dateTo));
		if (StringUtils.isEmpty(albumIds))
			albumIds = "";
		else {
			List<Album> albumIdsList = getAlbumListByAlbum(albumIds);
			for (Album a : albumIdsList)
				if (albumList.contains(a))
					albumList.remove(a);
			albumList.addAll(0, albumIdsList);
		}
		String checked = "0";
		for (Album b : albumList) {
			if (albumIds.contains(b.getAlbumId().toString()))
				checked = "1";
			else
				checked = "0";
			if (sb.length() == 0)
				sb.append("[");
			else
				sb.append(",");
			sb.append("{\"albumId\":\"" + b.getAlbumId()
					+ "\",\"albumName\":\"" + b.getAlbumName()
					+ "\",\"checked\":\"" + checked + "\",\"createTime\":\""
					+ b.getDate() + "\"}");
		}
		if (sb.length() > 0)
			sb.append("]");
		else
			sb.append("[{}]");
		return sb.toString();
	}
}
