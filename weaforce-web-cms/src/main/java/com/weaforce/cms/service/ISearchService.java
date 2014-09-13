package com.weaforce.cms.service;

import java.io.IOException;
import java.util.List;

import javax.xml.transform.TransformerConfigurationException;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import com.weaforce.cms.entity.Media;
import com.weaforce.cms.entity.MetaData;
import com.weaforce.core.common.bean.Node;
import com.weaforce.system.search.FileType;
import com.weaforce.system.search.GoogleSearchCriteria;
import com.weaforce.system.search.GoogleSearchResult;
import com.weaforce.system.service.ISystemService;

public interface ISearchService extends ISystemService {

	/**
	 * Save meta data instance
	 * 
	 * @param o
	 *            Meta data instance
	 * @return
	 */
	public MetaData saveMetaData(MetaData o);

	/**
	 * Get file type list
	 * 
	 * @return
	 */
	public List<Node> getFileTypeList();

	/**
	 * Get file type by google id
	 * 
	 * @param googleId
	 * @return
	 */
	public FileType getFileType(String googleId);

	/**
	 * Get supported locale language list
	 * 
	 * @return
	 */
	public List<Node> getSupportedLanguages();

	/**
	 * Get search result
	 * 
	 * @param criteria
	 *            Search criteria
	 * @return
	 * @throws IOException
	 */
	public List<GoogleSearchResult> getSearchResult(
			GoogleSearchCriteria criteria) throws IOException;

	/**
	 * Get media instance by URL
	 * 
	 * @param url
	 *            URL
	 * @return
	 */
	public Media getMediaByUrl(String url);

	/**
	 * Save media instance
	 * 
	 * @param o
	 *            Media instance
	 * @return
	 */
	public Media saveMedia(Media o);

	/**
	 * Get media from search result
	 * 
	 * @param searchResult
	 *            Search result
	 * @return
	 * @throws TransformerConfigurationException
	 * @throws SAXException
	 * @throws TikaException
	 */
	public Media getMediaFromSearchResult(GoogleSearchResult searchResult)
			throws TransformerConfigurationException, SAXException,
			TikaException;

	/**
	 * Query media list by query string
	 * 
	 * @param query
	 *            Query parameter
	 * @return
	 */
	public List<Media> getMediaList(String query);
}
