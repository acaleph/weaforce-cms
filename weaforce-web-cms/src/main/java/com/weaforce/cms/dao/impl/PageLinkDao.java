package com.weaforce.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.IPageLinkDao;
import com.weaforce.cms.entity.Channel;
import com.weaforce.cms.entity.PageLink;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("pageLinkDao")
public class PageLinkDao extends GenericDao<PageLink, Long> implements
		IPageLinkDao {
	private static final String QUERY_LINK = " From PageLink a Where a.account=? ";
	private static final String QUERY_LINK_BY_CHANNEL = " From PageLink a Where a.linkChannel=? ";
	private static final String QUERY_LINK_BY_ARTICLE = " From PageLink a Where a.linkArticle.articleId=? ";

	/**
	 * 根据父ID取得list
	 * 
	 * @param id
	 *            父ID
	 * @return
	 */
	public List<PageLink> getLinkListByParent(String account, Long id) {
		return listQuery(QUERY_LINK + " And a.linkParent.linkId=" + id, account);
	}


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
			Long articleId) {
		return listQuery(QUERY_LINK_BY_CHANNEL + " And a.linkArticle="
				+ articleId, channel);
	}

	/**
	 * 根据文章,取得页面元素
	 * 
	 * @param articleId
	 *            文章主键
	 * @return
	 */

	public PageLink getLinkByArticle(Long articleId) {
		return loadEntity(QUERY_LINK_BY_ARTICLE, articleId);
	}

	/**
	 * 根据频道,取得页面元素
	 * 
	 * @param channelId
	 *            频道主键
	 * @return
	 */
	public PageLink getLinkByChannel(Long channelId) {
		return loadEntity(QUERY_LINK_BY_CHANNEL, channelId);
	}

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
			List<PageLink> linkList) {
		List<PageLink> linkChildren = loadEntity(linkId).getLinkChildren();
		if (linkChildren.size() > 0)
			linkList.addAll(lastIndex, linkChildren);
		if (lastIndex < linkList.size()) {
			// 指针前移
			lastIndex++;
			return getLinkListByRoot(linkList.get(lastIndex - 1).getLinkId(),
					lastIndex, linkList);
		}
		return linkList;
	}

	/**
	 * 取得链接页面根 list
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	public List<PageLink> getLinkRootList(String account) {
		return listQuery(QUERY_LINK + " And a.linkParent=null ", account);
	}
}
