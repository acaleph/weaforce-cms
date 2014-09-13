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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * Compatibility check.
 * 
 * @version $Id: Compatibility.java 2134 2008-06-25 08:36:32Z th-schwarz $
 */
public class Compatibility {

	/**
	 * Checks if a browser's user agent string is compatible for the FCKeditor.
	 * 
	 * @param userAgentString
	 * @return <code>true</code> if compatible otherwise <code>false</code>
	 */
	public static boolean check(final String userAgentString) {
		if (Utils.isEmpty(userAgentString))
			return false;
		
		String userAgentStr = userAgentString.toLowerCase();

		// IE 5.5+, check special keys like 'Opera' and 'mac', because there are some
		// other browsers, containing 'MSIE' in there agent string!
		if (userAgentStr.indexOf("opera") < 0 && userAgentStr.indexOf("mac") < 0) {
			if (getBrowserVersion(userAgentStr, ".*msie ([\\d]+.[\\d]+).*") >= 5.5f)
				return true;
		}
		
		// for all gecko based browsers
		if (getBrowserVersion(userAgentStr, ".*rv:([\\d]+.[\\d]+).*") > 1.7f)
			return true;
		
		// Opera 9.5+
		if (getBrowserVersion(userAgentStr, "opera/([\\d]+.[\\d]+).*") >= 9.5f
				|| getBrowserVersion(userAgentStr, ".*opera ([\\d]+.[\\d]+)") >= 9.5f)
			return true;

		// Safari 3+
		if (getBrowserVersion(userAgentStr, ".*applewebkit/([\\d]+).*") >= 522f)
			return true;

		return false;
	}

	/**
	 * Just a wrapper to {@link #check(String)}.
	 * 
	 * @param request
	 */
	public static boolean isCompatibleBrowser(final HttpServletRequest request) {
		return (request == null) ? false : check(request.getHeader("user-agent"));
	}

	/**
	 * Helper method to get the browser version from 'userAgent' with regex. The
	 * first matching group has to be the version number!
	 * 
	 * @param userAgent
	 * @param regex
	 * @return The browser version, or -1f if version can't be determined.
	 */
	private static float getBrowserVersion(final String userAgent, final String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userAgent);
		if (matcher.matches()) {
			try {
				return Float.parseFloat(matcher.group(1));
			} catch (NumberFormatException e) {
				return -1f;
			}
		}
		return -1f;
	}

}
