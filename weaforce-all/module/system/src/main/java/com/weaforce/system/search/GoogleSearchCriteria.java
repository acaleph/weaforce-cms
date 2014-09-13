package com.weaforce.system.search;

import java.util.Locale;

/**
 * Web search criteria
 * 
 * @author Manfred
 * 
 */
public class GoogleSearchCriteria {	
	private FileType fileType;
	private Locale resultLocale;
	private String searchExpression;
	private int resultNumber = 14;
	private int startNumber = 0;

	public GoogleSearchCriteria(FileType fileType, Locale resultLocale,
			String searchExpression, int resultNumber, int startNumber) {
		this.fileType = fileType;
		this.resultLocale = resultLocale;
		this.searchExpression = searchExpression;
		this.resultNumber = resultNumber;
		this.startNumber = startNumber;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public Locale getResultLocale() {
		return resultLocale;
	}

	public void setResultLocale(Locale resultLocale) {
		this.resultLocale = resultLocale;
	}

	public String getSearchExpression() {
		return searchExpression;
	}

	public void setSearchExpression(String searchExpression) {
		this.searchExpression = searchExpression;
	}

	public int getResultNumber() {
		return resultNumber;
	}

	public void setResultNumber(int resultNumber) {
		this.resultNumber = resultNumber;
	}

	public int getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}

}
