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
package com.weaforce.system.component.fckeditor.requestcycle;

import javax.servlet.http.HttpServletRequest;


/**
 * An interface which provides the authorization of server-side commands.<br />
 * The commands are:
 * <ul>
 * <li>{@link #isEnabledForFileBrowsing(HttpServletRequest)}: Enables the user
 * to browse/select files.</li>
 * <li>{@link #isEnabledForFileUpload(HttpServletRequest)}: Enables the user
 * to upload files.</li>
 * </ul>
 * 
 * @version $Id: UserAction.java 2151 2008-07-02 22:03:15Z mosipov $
 */
public interface UserAction {

	/**
	 * Authenticates/enables the current user for uploading files.<br />
	 * If the implementation doesn't bother you, just return <code>true</code>.
	 * 
	 * @param request
	 *            Servlet request from user
	 * @return <code>true</code> if user can upload to the server, or
	 *         <code>false</code>
	 */
	public boolean isEnabledForFileUpload(final HttpServletRequest request);

	/**
	 * Authenticates/enables the current user for browsing files.<br />
	 * If the implementation doesn't bother you, just return <code>true</code>.
	 * 
	 * @param request
	 *            Servlet request from user
	 * @return <code>true</code> if user can browse the server, or
	 *         <code>false</code>
	 */
	public boolean isEnabledForFileBrowsing(final HttpServletRequest request);

}
