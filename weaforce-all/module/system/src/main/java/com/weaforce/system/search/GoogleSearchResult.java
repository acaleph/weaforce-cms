package com.weaforce.system.search;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleSearchResult {
	private String unescapedUrl;

	private String url;

	private String visibleUrl;

	private String cacheUrl;
	private String title;
	private String titleNoFormatting;
	private String content;

	private String languageCode;

	public String getUnescapedUrl() {
		return unescapedUrl;
	}

	public void setUnescapedUrl(String unescapedUrl) {
		this.unescapedUrl = unescapedUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVisibleUrl() {
		return visibleUrl;
	}

	public void setVisibleUrl(String visibleUrl) {
		this.visibleUrl = visibleUrl;
	}

	public String getCacheUrl() {
		return cacheUrl;
	}

	public void setCacheUrl(String cacheUrl) {
		this.cacheUrl = cacheUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleNoFormatting() {
		return titleNoFormatting;
	}

	public void setTitleNoFormatting(String titleNoFormatting) {
		this.titleNoFormatting = titleNoFormatting;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

}
