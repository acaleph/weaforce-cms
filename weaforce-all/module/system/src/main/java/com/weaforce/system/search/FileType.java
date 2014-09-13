package com.weaforce.system.search;

/**
 * Supported file type
 * 
 * @author Manfred
 * 
 */
public enum FileType {
	PDF("pdf"), PostScript("ps"), AutoDeskDWF("dwf"), GoogleEarthKML("kml"), GoogleEarthKMZ(
			"kmz"), Excel("xls"), PowerPoint("ppt"), WordDocument("doc"), RTF(
			"rtf"), Flash("swf"), ANY("");

	private final String googleID;

	private FileType(String googleID) {
		this.googleID = googleID;
	}

	public String getGoogleID() {
		return this.googleID;
	}
}
