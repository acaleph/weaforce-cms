/*
 * FCKeditor - The text editor for Internet - http://www.fckeditor.net
 * Copyright (C) 2003-2008 Frederico Caldeira Knabben
 * 
 * == BEGIN LICENSE ==
 * 
 * Licensed under the terms of any of the following licenses at your
 * choice:
 * 
 *  - GNU General Public License Version 2 or later (the "GPL")
 *    http://www.gnu.org/licenses/gpl.html
 * 
 *  - GNU Lesser General Public License Version 2.1 or later (the "LGPL")
 *    http://www.gnu.org/licenses/lgpl.html
 * 
 *  - Mozilla Public License Version 1.1 or later (the "MPL")
 *    http://www.mozilla.org/MPL/MPL-1.1.html
 * 
 * == END LICENSE ==
 */
package com.weaforce.system.component.fckeditor.tool;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weaforce.core.util.Global;
import com.weaforce.core.util.StringUtil;
import com.weaforce.system.component.fckeditor.handlers.ResourceTypeHandler;

/**
 * Some static helper methods in conjunction with the servlet response.
 * 
 * @version $Id: UtilsResponse.java 2151 2008-07-02 22:03:15Z mosipov $
 */
public class UtilsResponse {
	private static final Logger logger = LoggerFactory
			.getLogger(UtilsResponse.class);

	/**
	 * Constructs a URL from different parameters. This method is about to
	 * change in version 2.5.
	 * 
	 * @param request
	 * @param resourceType
	 * @param urlPath
	 * @param prependContextPath
	 * @param fullUrl
	 * @return constructed url
	 */
	public static String constructResponseUrl(HttpServletRequest request,
			ResourceTypeHandler resourceType, String urlPath,
			boolean prependContextPath, boolean fullUrl) {

		StringBuffer sb = new StringBuffer();
		// "/"
		logger.info("urlPath value: {}", urlPath);
		if (fullUrl) {
			// http://192.168.8.92:8080/fckeditor_connector
			String address = request.getRequestURL().toString();
			logger.info("address value: {}", address);
			// http://192.168.8.92:8080
			sb.append(address.substring(0, address.indexOf('/', 8))
					+ request.getContextPath());
			logger.info("sb value after address: {}", sb);
		}
		// length=0
		if (prependContextPath && !fullUrl) {
			sb.append(request.getContextPath());
			logger.info("sb value after request.getContextPath: {}",
					request.getContextPath());
		}
		// "/data/file"
		// sb.append(ConnectorHandler.getUserFilesPath(request));
		// "/image"
		logger.info("resourceType.getPath() value {}", resourceType.getPath());
		sb.append(resourceType.getPath());
		// http://192.168.8.92:8080/data/file/image/
		if (StringUtil.isNotEmpty(urlPath))
			sb.append(urlPath);
		logger.info("sb value: {}", sb.toString());
		// http://192.0.1.181:8080/image/abc/1301452832680.jpg
		return sb.toString();
	}

	/**
	 * Constructs a URL from different parameters.
	 * 
	 * @param resourceType
	 *            上传文件类型:image/flash/file
	 * @param userLogin
	 *            当前用户登录ID
	 * @param urlPath
	 * @return
	 */
	public static String constructResponseUrl(ResourceTypeHandler resourceType,
			String userLogin, String urlPath) {
		StringBuffer sb = new StringBuffer();
		sb.append(Global.IMAGE_SERVER_URL);
		sb.append(resourceType.getPath() + "/");
		if (StringUtil.isNotEmpty(userLogin))
			sb.append(userLogin);
		if (StringUtil.isNotEmpty(urlPath))
			sb.append(urlPath);
		return sb.toString();
	}
}
