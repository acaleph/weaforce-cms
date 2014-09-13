package com.weaforce.core.util;

import java.util.HashMap;
import java.util.Map;

public class Global {
	public final static String DEFAULT_CONTENT_TYPE = "text/html;charset=UTF-8";

	public final static String LOCAL_PATH_PREFIX = "file://";

	public final static String ENC_UTF_8 = "UTF-8";
	public final static String ENC_8859_1 = "8859_1";

	public final static String RANDOM_LOGIN_KEY = "RANDOM_LOGIN_KEY";

	public final static String PARAM_SID = "sid";

	public final static String MAIL_QUEUE = "MAIL_QUEUE";

	public final static String USER_AGENT = "user-agent";

	public final static String SESSION_ID_IN_COOKIE = "SESSION_ID";

	public final static String ALBUM_VERIFY_KEY = "ALBUM_";

	public static final int KUKE_USB_KEY_ID = 0x3b84a2ad;
	public static final String KUKE_USB_KEY_FORM = "USBKEY";
	public static final String IMAGE_SERVER_PATH = "/data/file";
	public static final String IMAGE_SERVER_PATH_RESIZE = "/data/file/resize";
	public static final String IMAGE_SERVER_URL = "http://img.xizihui.com";
	public static final String NONE_OPTION = " ---none--- ";

	public static final long INTERVAL_SECOND = 1000;
	public static final long INTERVAL_MINUTE = 60000;
	public static final long INTERVAL_HOUR = 3600000;
	public static final long INTERVAL_DAY = 86400000;
	public static final long INTERVAL_WEEK = 604800000;
	public static final long INTERVAL_MONTH = 2629744;
	public static final long INTERVAL_YEAR = 31556926;
	public static final long INTERVAL_DECADE = 315569260;

	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;
	// 广告
	public static final int PAGE_SIZE = 10;
	// 20
	public static final String FILE_TEMPLATE = "00000000000000000000";

	// IconMap
	public static Map<String, String> iconMap = new HashMap<String, String>();

	static {
		iconMap.put("png", "/common/image/file/film.png");
		iconMap.put("avi", "/common/image/file/film.png");
		iconMap.put("bmp", "/common/image/file/picture.png");
		iconMap.put("com", "/common/image/file/application.png");
		iconMap.put("css", "/common/image/file/css.png");
		iconMap.put("doc", "/common/image/file/doc.png");
		iconMap.put("exe", "/common/image/file/application.png");
		iconMap.put("jar", "/common/image/file/java.png");
		iconMap.put("jpg", "/common/image/file/picture.png");
		iconMap.put("gif", "/common/image/file/picture.png");
		iconMap.put("jpeg", "/common/image/file/picture.png");
		iconMap.put("log", "/common/image/file/txt.png");
		iconMap.put("m4p", "/common/image/file/music.png");
		iconMap.put("mov", "/common/image/file/film.png");
		iconMap.put("mp3", "/common/image/file/music.png");
		iconMap.put("mp4", "/common/image/file/film.png");
		iconMap.put("mpg", "/common/image/file/film.png");
		iconMap.put("flv", "/common/image/file/film.png");
		iconMap.put("pcx", "/common/image/file/picture.png");
		iconMap.put("pdf", "/common/image/file/pdf.png");
		iconMap.put("ppt", "/common/image/file/ppt.png");
		iconMap.put("psd", "/common/image/file/psd.png");
		iconMap.put("rpm", "/common/image/file/linux.png");
		iconMap.put("swf", "/common/image/file/flash.png");
		iconMap.put("tif", "/common/image/file/picture.png");
		iconMap.put("wav", "/common/image/file/music.png");
		iconMap.put("wmv", "/common/image/file/film.png");
		iconMap.put("xls", "/common/image/file/xls.png");
		iconMap.put("xml", "/common/image/file/code.png");
		iconMap.put("zip", "/common/image/file/zip.png");
		iconMap.put("rar", "/common/image/file/zip.png");
		iconMap.put("tar", "/common/image/file/zip.png");
		iconMap.put("gz", "/common/image/file/zip.png");
		iconMap.put("tiff", "/common/image/file/picture.png");
	}

	public static String getIconMap(final String key) {
		return iconMap.get(key);
	}
}
