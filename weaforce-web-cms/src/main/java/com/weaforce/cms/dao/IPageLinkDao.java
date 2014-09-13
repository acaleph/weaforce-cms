package com.weaforce.cms.dao;

import java.util.List;

import com.weaforce.cms.entity.Channel;
import com.weaforce.cms.entity.PageLink;
import com.weaforce.core.dao.IGenericDao;

public interface IPageLinkDao extends IGenericDao<PageLink, Long> {
	/**
	 * 根据父ID取得 list
	 * 
	 * @param id
	 *            父ID
	 * @return
	 */
	public List<PageLink> getLinkListByParent(String account, Long id);


	/**
	 * 根据频道，取得页面元素list
	 * 
	 * @param articleId
	 *            文章
	 * @param channel
	 *            频道
	 * @return
	 */
	public List<PageLink> getLinkListByChannelArticle(Channel channel,
			Long articleId);

	/**
	 * 根据文章,取得页面元素
	 * 
	 * @param articleId
	 *            文章主键
	 * @return
	 */

	public PageLink getLinkByArticle(Long articleId);

	/**
	 * 根据频道,取得页面元素
	 * 
	 * @param channelId
	 *            频道主键
	 * @return
	 */
	public PageLink getLinkByChannel(Long channelId);

	/**
	 * 根据自定义页面树根取得页面树 list
	 * 
	 * @param linkId
	 *            当前页面
	 * @param lastIndex
	 *            当前自定义页面 list索引
	 * @param linkList
	 *            自定义页面 list
	 * @return
	 */
	public List<PageLink> getLinkListByRoot(Long linkId, int lastIndex,
			List<PageLink> linkList);

	/**
	 * 取得链接页面根 list
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	public List<PageLink> getLinkRootList(String account);

}
