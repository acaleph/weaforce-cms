package com.weaforce.cms.service;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.xml.transform.TransformerConfigurationException;

import org.apache.tika.exception.TikaException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.xml.sax.SAXException;

import com.weaforce.cms.entity.Media;
import com.weaforce.cms.entity.MetaData;
import com.weaforce.core.common.bean.Node;
import com.weaforce.system.search.FileType;
import com.weaforce.system.search.GoogleSearchCriteria;
import com.weaforce.system.search.GoogleSearchResult;

public class SearchServiceTest extends AbstractTransactionalSpringTest {
	@Autowired
	@Qualifier("searchService")
	private ISearchService searchService;

	/**
	 * Supported file types
	 */
	@Test
	public void getFileTypeList() {
		List<Node> nodeList = searchService.getFileTypeList();
		assertTrue(nodeList.size() > 0);
	}

	/**
	 * File type
	 */
	@Test
	public void getFileType() {
		FileType fileType = searchService.getFileType("pdf");
		assertTrue(fileType.getGoogleID().equals("pdf"));
	}

	/**
	 * Supported languages
	 */
	@Test
	public void getSupportedLanguages() {
		List<Node> nodeList = searchService.getSupportedLanguages();
		assertTrue(nodeList.size() > 0);
	}

	@Test
	@Rollback(true)
	public void saveMetaData() {
		MetaData o = new MetaData();
		o.setDataName("title");
		o.setDataContent("Current U.S. Nuclear Forces");
		searchService.saveMetaData(o);
	}

	//@Test
	//@Rollback(false)
	public void search() throws IOException, TransformerConfigurationException,
			SAXException, TikaException {
		GoogleSearchCriteria criteria = new GoogleSearchCriteria(FileType.ANY,
				new Locale("zh"), "ipad", 14, 0);
		List<GoogleSearchResult> resultList = searchService
				.getSearchResult(criteria);
		Media media = searchService.getMediaFromSearchResult(resultList.get(0));
		System.out.println(media.getMediaUrl());
		if (searchService.getMediaByUrl(media.getMediaUrl()) == null)
			searchService.saveMedia(searchService
					.getMediaFromSearchResult(resultList.get(0)));
	}
	@Test
	public void getMediaListTest() {
		List<Media> mediaList = searchService.getMediaList("ipad");
		assertTrue(mediaList.size() == 0);
	}
	
}
