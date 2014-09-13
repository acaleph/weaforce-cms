package com.weaforce.cms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.IMediaDao;
import com.weaforce.cms.entity.Media;
import com.weaforce.core.dao.impl.GenericDao;

@Repository("mediaDao")
public class MediaDao extends GenericDao<Media, Long> implements IMediaDao {

	/**
	 * Get media instance by URL
	 * 
	 * @param url
	 *            URL
	 * @return
	 */
	public Media getMediaByUrl(String url) {
		return loadEntity("mediaUrl", url);
	}

	/**
	 * Query media list by query string
	 * 
	 * @param query
	 *            Query parameter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Media> getMediaList(String query) {
		List<Media> mediaList = new ArrayList<Media>();
		FullTextSession fullTextSession = Search
				.getFullTextSession(getSession());
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Media.class).get();

		org.apache.lucene.search.Query luceneQuery = qb.keyword()
				.onFields("mediaUrl").matching(query).createQuery();

		org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(
				luceneQuery, Media.class);
		hibQuery.setFirstResult(20).setMaxResults(20);
		mediaList = hibQuery.list();
		return mediaList;
	}
}
