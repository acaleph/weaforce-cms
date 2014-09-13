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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weaforce.core.util.PropertiesLoader;
import com.weaforce.system.component.fckeditor.requestcycle.UserAction;
import com.weaforce.system.component.fckeditor.requestcycle.UserPathBuilder;

/**
 * Handler for classes which implement the interfaces from package
 * {@link com.weaforce.sys.component.fckeditor.requestcycle}.
 * 
 * @version $Id: RequestCycleHandler.java 2151 2008-07-02 22:03:15Z mosipov $
 */
public class RequestCycleHandler {
	private static Logger logger = LoggerFactory
			.getLogger(RequestCycleHandler.class);
	private static UserAction userAction = null;
	private static UserPathBuilder userPathBuilder = null;
	private static PropertiesLoader loader = new PropertiesLoader();

	static {
		// If there are more objects to instantiate in future, we could solve
		// the following by reflection!

		// 1. try to instantiate the UserAction object
		String fqcn = loader.getProperty("connector.userActionImpl");
		if (fqcn == null)
			logger
					.warn("No property found for UserAction implementation, any user action will be disabled!");
		else {
			try {
				Class<?> clazz = Class.forName(fqcn);
				userAction = (UserAction) clazz.newInstance();
				logger
						.info("UserAction implementation successfully instantiated!");
			} catch (Exception e) {
				logger.error("Couldn't instantiate class [".concat(fqcn)
						.concat("], any user action will disabled!"), e);
			}
		}

		// 2. try to instantiate the UserPathBuilder object
		fqcn = loader.getProperty("connector.userPathBuilderImpl");
		if (fqcn == null)
			logger
					.warn("No property found for UserPathBuilder implementation! "
							.concat("The 'DefaultUserFilesPath' will be used in the ConnectorServlet!"));
		else {
			try {
				Class<?> clazz = Class.forName(fqcn);
				userPathBuilder = (UserPathBuilder) clazz.newInstance();
				logger
						.info("UserPathBuilder object successfully instantiated!");
			} catch (Exception e) {
				logger
						.error(
								"Couldn't instantiate class ["
										.concat(fqcn)
										.concat(
												"], The 'DefaultUserFilesPath' will be used in the ConnectorServlet!"),
								e);
			}
		}
	}

	/**
	 * Just a wrapper to
	 * {@link UserAction#isEnabledForFileBrowsing(HttpServletRequest)}.
	 * 
	 * @param request
	 * @return {@link UserAction#isEnabledForFileBrowsing(HttpServletRequest)}
	 *         or false if <code>userAction</code> isn't set.
	 */
	public static boolean isEnabledForFileBrowsing(
			final HttpServletRequest request) {
		return (userAction != null && userAction
				.isEnabledForFileBrowsing(request));
	}

	/**
	 * Just a wrapper to
	 * {@link UserAction#isEnabledForFileUpload(HttpServletRequest)}.
	 * 
	 * @param request
	 * @return {@link UserAction#isEnabledForFileUpload(HttpServletRequest)} or
	 *         false if <code>userAction</code> isn't set.
	 */
	public static boolean isEnabledForFileUpload(
			final HttpServletRequest request) {
		// return (userAction != null &&
		// userAction.isEnabledForFileUpload(request));
		return true;
	}

	/**
	 * Getter for the <code>UserFilesPath</code>.<br />
	 * Method is used by other handlers only!
	 * 
	 * @param request
	 * @return {@link UserPathBuilder#getUserFilesPath(HttpServletRequest)} or
	 *         <code>null</code> if <code>userPathBuilder</code> is
	 *         <code>null</code>.
	 */
	protected static String getUserFilePath(final HttpServletRequest request) {
		return (userPathBuilder != null) ? userPathBuilder
				.getUserFilesPath(request) : null;
	}
}
