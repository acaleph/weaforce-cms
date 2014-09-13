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

import com.weaforce.system.component.fckeditor.handlers.ConnectorHandler;

/**
 * An interface which provides a way to build a user-dependent
 * <code>UserFilesPath</code>.
 * 
 * @version $Id: UserPathBuilder.java 2151 2008-07-02 22:03:15Z mosipov $
 */
public interface UserPathBuilder {

	/**
	 * Getter for the user-dependent <code>UserFilesPath</code>.<br />
	 * <strong>Important:</strong>
	 * <ul>
	 * <li> If the implementation returns <code>null</code>,
	 * {@link ConnectorHandler} will used the default one! That's useful, if the
	 * implementation doesn't bother you.</li>
	 * <li>The returned directory string has to start with '/', but has to end
	 * without '/'.</li>
	 * <li>The path has to be within the context.</li>
	 * </ul>
	 * 
	 * @param request
	 * @return <code>null</code> or the <code>UserFilesPath</code> for the
	 *         current user.
	 */
	public String getUserFilesPath(final HttpServletRequest request);
}
