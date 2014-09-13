package com.weaforce.cms.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.transform.TransformerConfigurationException;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tika.exception.TikaException;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import com.weaforce.cms.dao.IMediaDao;
import com.weaforce.cms.dao.IMetaDataDao;
import com.weaforce.cms.entity.Media;
import com.weaforce.cms.entity.MetaData;
import com.weaforce.cms.service.ISearchService;
import com.weaforce.cms.util.MediaParser;
import com.weaforce.core.common.bean.Node;
import com.weaforce.core.util.WebUtil;
import com.weaforce.system.search.FileType;
import com.weaforce.system.search.GoogleSearchCriteria;
import com.weaforce.system.search.GoogleSearchResult;
import com.weaforce.system.service.impl.SystemService;

@Service("searchService")
@Transactional(rollbackFor = Exception.class)
public class SearchService extends SystemService implements ISearchService {
	private String httpReferrer = "http://www.metagear.de";// "http://www.example.com";

	// Log
	private static final Log log = LogFactory.getLog(SearchService.class);

	// Search engine holder
	private final String googleURL = "http://ajax.googleapis.com/ajax/services/search/web?&lr=lang_%s&start=0&rsz=large&v=1.0&q=%s&num=%d&start=%d";

	@Autowired
	@Qualifier("metaDataDao")
	private IMetaDataDao metaDataDao;
	@Autowired
	@Qualifier("mediaDao")
	private IMediaDao mediaDao;

	/**
	 * Save meta data instance
	 * 
	 * @param o
	 *            Meta data instance
	 * @return
	 */
	public MetaData saveMetaData(MetaData o) {
		return metaDataDao.save(o);
	}

	/**
	 * Get file type list
	 * 
	 * @return
	 */
	public List<Node> getFileTypeList() {
		List<Node> nodeList = new ArrayList<Node>();
		for (FileType o : FileType.values()) {
			Node node = new Node();
			node.setName(o.name());
			node.setValue(o.getGoogleID());
			nodeList.add(node);
		}
		return nodeList;
	}

	/**
	 * Get file type by google id
	 * 
	 * @param googleId
	 * @return
	 */
	public FileType getFileType(String googleID) {
		for (FileType o : FileType.values()) {
			if (o.getGoogleID().equals(googleID))
				return o;
		}
		return null;
	}

	/**
	 * Get supported locale language list
	 * 
	 * @return
	 */
	public List<Node> getSupportedLanguages() {
		List<Node> nodeList = new ArrayList<Node>();
		List<Locale> localeList = getLocales();
		for (Locale o : localeList) {
			Node node = new Node();
			node.setName(o.getDisplayLanguage());
			node.setValue(o.getLanguage());
			nodeList.add(node);
		}
		return nodeList;
	}

	/**
	 * Get search result
	 * 
	 * @param criteria
	 *            Search criteria
	 * @return
	 * @throws IOException
	 */
	public List<GoogleSearchResult> getSearchResult(
			GoogleSearchCriteria criteria) throws IOException {
		List<GoogleSearchResult> resultList = new ArrayList<GoogleSearchResult>();
		// Combine the search expression with the file type
		if (!criteria.getFileType().equals(FileType.ANY)) {
			criteria.setSearchExpression(criteria.getSearchExpression()
					+ " filetype:" + criteria.getFileType().getGoogleID());
		}
		// Encode the search expression
		String searchExpression = URLEncoder.encode(
				criteria.getSearchExpression(), "UTF-8");
		log.debug("Search expression: " + searchExpression);
		// Setup the language parameter
		String language = "";

		if (criteria.getResultLocale() != null) {
			language = criteria.getResultLocale().getLanguage();
			log.debug("Search language:"
					+ criteria.getResultLocale().getDisplayLanguage());
		}

		URL url = new URL(String.format(googleURL, language, searchExpression,
				criteria.getResultNumber(), criteria.getStartNumber()));
		log.debug("Search URL: " + url);
		URLConnection connection = url.openConnection();
		connection.addRequestProperty("Referer", httpReferrer);
		// Get the JSON response data
		String line;
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}

		String response = builder.toString();
		log.debug("Response: " + response);
		// Response data to JSON format data
		ObjectMapper mapper = new ObjectMapper();
		JsonFactory jf = mapper.getJsonFactory();
		JsonParser jp = jf.createJsonParser(response);
		JsonNode jn = mapper.readTree(jp).get("responseData").get("results");
		System.out.println("jn3:" + jn);

		resultList = mapper.readValue(jn,
				new TypeReference<List<GoogleSearchResult>>() {
				});
		return resultList;
	}

	/**
	 * Get media instance by URL
	 * 
	 * @param url
	 *            URL
	 * @return
	 */
	public Media getMediaByUrl(String url) {
		return mediaDao.getMediaByUrl(url);
	}

	/**
	 * Save media instance
	 * 
	 * @param o
	 *            Media instance
	 * @return
	 */
	public Media saveMedia(Media o) {
		Media media = mediaDao.getMediaByUrl(o.getMediaUrl());
		o.setCreateTime(System.currentTimeMillis());
		o.setCreator("YANJIACHENG@WEAFORCE.COM");
		o.setLastUpdateTime(System.currentTimeMillis());
		o.setLastUpdate("YANJIACHENG@WEAFORCE.COM");
		if (media == null)
			o = mediaDao.save(o);
		else {
			BeanUtils.copyProperties(o, media);
			o = mediaDao.save(media);
		}
		return mediaDao.save(o);
	}

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
			TikaException {
		Media media = new Media();
		// Get media from search result
		try {
			byte[] bytes = WebUtil.getResponeseBody(new URL(searchResult
					.getUrl()));
			InputStream inputStream = new ByteArrayInputStream(bytes);
			StringWriter htmlWriter = new StringWriter();
			StringWriter xhtmlWriter = new StringWriter();
			StringWriter textWriter = new StringWriter();
			Map<String, String> metaData = new HashMap<String, String>();

			new MediaParser().parse(inputStream, htmlWriter, xhtmlWriter,
					textWriter, metaData);
			// Get MIME type
			String mimeType = metaData.containsKey("Content-Type") ? metaData
					.get("Content-Type") : "UNKNOWN";
			// Saving the search result into media instance
			media = new Media(searchResult.getTitleNoFormatting().trim(),
					bytes, htmlWriter.toString(), xhtmlWriter.toString(),
					textWriter.toString(), searchResult.getUrl().trim(),
					mimeType, searchResult.getLanguageCode());
			// Get meta data from meta map
			for (String key : metaData.keySet())
				media.getMediaMeta().add(new MetaData(key, metaData.get(key)));
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return media;
	}

	/**
	 * Query media list by query string
	 * 
	 * @param query
	 *            Query parameter
	 * @return
	 */
	public List<Media> getMediaList(String query) {
		return mediaDao.getMediaList(query);
	}

	/**
	 * Get locale list
	 * 
	 * @return
	 */
	public List<Locale> getLocales() {
		List<Locale> localeList = new ArrayList<Locale>();
		localeList.add(new Locale("af"));
		localeList.add(new Locale("ar"));
		localeList.add(new Locale("hy"));
		localeList.add(new Locale("bg"));
		localeList.add(new Locale("da"));
		localeList.add(new Locale("de"));
		localeList.add(new Locale("en"));
		localeList.add(new Locale("eo"));
		localeList.add(new Locale("et"));
		localeList.add(new Locale("fi"));
		localeList.add(new Locale("fr"));
		localeList.add(new Locale("el"));
		localeList.add(new Locale("iw"));
		localeList.add(new Locale("id"));
		localeList.add(new Locale("is"));
		localeList.add(new Locale("it"));
		localeList.add(new Locale("ja"));
		localeList.add(new Locale("ca"));
		localeList.add(new Locale("ko"));
		localeList.add(new Locale("hr"));
		localeList.add(new Locale("lv"));
		localeList.add(new Locale("lt"));
		localeList.add(new Locale("nl"));
		localeList.add(new Locale("no"));
		localeList.add(new Locale("fa"));
		localeList.add(new Locale("tl"));
		localeList.add(new Locale("pl"));
		localeList.add(new Locale("pt"));
		localeList.add(new Locale("ro"));
		localeList.add(new Locale("ru"));
		localeList.add(new Locale("sv"));
		localeList.add(new Locale("sr"));
		localeList.add(new Locale("sk"));
		localeList.add(new Locale("sl"));
		localeList.add(new Locale("es"));
		localeList.add(new Locale("sw"));
		localeList.add(new Locale("th"));
		localeList.add(new Locale("cs"));
		localeList.add(new Locale("tr"));
		localeList.add(new Locale("uk"));
		localeList.add(new Locale("hu"));
		localeList.add(new Locale("vi"));
		localeList.add(new Locale("be"));
		localeList.add(new Locale("zh"));
		return localeList;
	}
}
