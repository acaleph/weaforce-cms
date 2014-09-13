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
package com.weaforce.system.component.fckeditor.handlers;

import javax.servlet.http.HttpServletRequest;

import com.weaforce.core.util.PropertiesLoader;
import com.weaforce.system.component.fckeditor.requestcycle.UserPathBuilder;

/**
 * Handler for Connector-related properties.<br />
 * Wraps to the {@link PropertiesLoader}.
 * 
 * @version $Id: ConnectorHandler.java 2101 2008-06-22 22:00:48Z mosipov $
 */
public class ConnectorHandler {
	static PropertiesLoader loader = new PropertiesLoader();
	/**
	 * Getter for the <code>UserFilesPath</code>.
	 * 
	 * @return {@link UserPathBuilder#getUserFilesPath(HttpServletRequest)} or
	 *         the <code>DefaultUserFilePath</code> if {@link UserPathBuilder}
	 *         isn't set.
	 */
	public static String getUserFilesPath(final HttpServletRequest request) {
    	String userFilePath = RequestCycleHandler.getUserFilePath(request);
    	return (userFilePath != null) ? userFilePath : getDefaultUserFilesPath();
    }

	/**
	 * Getter for the default handling of files with multiple extensions.
	 * 
	 * @return <code>true</code> if single extension only should be enforced
	 *         else <code>false</code>.
	 */
	public static boolean isForceSingleExtension() {
		return Boolean.valueOf(loader.getProperty("connector.forceSingleExtension"));
	}

	/**
	 * Getter for the value to instruct the connector to return the full URL of
	 * a file/folder in the XML response rather than the absolute URL.
	 * 
	 * @return <code>true</code> if the property <code>connector.fullUrl</code> is
	 *         set else <code>false</code>.
	 */
	public static boolean isFullUrl() {
		return Boolean.valueOf(loader.getProperty("connector.fullUrl"));
	}

	/**
	 * Getter for the default <code>UserFilesPath</code>.
	 * 
	 * @return <code>DefaultUserFilesPath</code> (/userfiles)
	 */
	public static String getDefaultUserFilesPath() {
		return loader.getProperty("connector.userFilesPath");
	}
	
	/**
	 * Getter for the value to instruct the Connector to check if the uploaded
	 * image is really an image.
	 * 
	 * @return Boolean value of the property
	 *         <code>connector.secureImageUploads</code>.
	 */
	public static boolean isSecureImageUploads() {
		return Boolean.valueOf(loader.getProperty("connector.secureImageUploads"));
	}
}
