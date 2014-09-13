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
package com.weaforce.system.component.fckeditor.tags;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.weaforce.core.util.PropertiesLoader;
import com.weaforce.system.component.fckeditor.handlers.RequestCycleHandler;
import com.weaforce.system.component.fckeditor.tool.Compatibility;

/**
 * This tag displays information about browser and user capabilities. There are
 * tree available commands (CompatibleBrowser, FileBrowsing, FileUpload) which
 * respond an English message.<br />
 * <strong>Hint</strong>: The message string cannot be localized at the moment.
 * 
 * @version $Id: CheckTag.java 2151 2008-07-02 22:03:15Z mosipov $
 */
public class CheckTag extends TagSupport {

	private static final long serialVersionUID = -6834095891675681686L;

	private static final String FILE_UPLOAD = "FileUpload";
	private static final String FILE_BROWSING = "FileBrowsing";
	private static final String COMPATIBLE_BROWSER = "CompatibleBrowser";
	private static final String PROPERTY_MESSAGE_FILE_BROWSING_DISABLED = "message.enabled_tag.connector.file_browsing.disabled";
	private static final String PROPERTY_MESSAGE_FILE_BROWSING_ENABLED = "message.enabled_tag.connector.file_browsing.enabled";
	private static final String PROPERTY_MESSAGE_FILE_UPLOAD_DISABLED = "message.enabled_tag.connector.file_upload.disalbed";
	private static final String PROPERTY_MESSAGE_FILE_UPLOAD_ENABLED = "message.enabled_tag.connector.file_upload.enabled";
	private static final String PROPERTY_MESSAGE_NOT_COMPATIBLE_BROWSER = "message.enabled_tag.compatible_browser.no";
	private static final String PROPERTY_MESSAGE_COMPATIBLE_BROWSER = "message.enabled_tag.compatible_browser.yes";
	private static Set<String> commands = new HashSet<String>(3);
	private static PropertiesLoader loader = new PropertiesLoader();

	static {
		commands.add(FILE_UPLOAD);
		commands.add(FILE_BROWSING);
		commands.add(COMPATIBLE_BROWSER);
	}

	private String command;

	/**
	 * Sets the desired command.
	 * 
	 * @param command
	 * @throws JspTagException
	 */
	public void setCommand(String command) throws JspTagException {
		if (!commands.contains(command))
			throw new JspTagException(
					"You have to provide one of the following commands: "
							+ commands);
		this.command = command;
	}

	
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();

		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		String response = null;

		if (command.equals(FILE_UPLOAD)) {
			if (RequestCycleHandler.isEnabledForFileUpload(request))
				response = loader
						.getProperty(CheckTag.PROPERTY_MESSAGE_FILE_UPLOAD_ENABLED);
			else
				response = loader
						.getProperty(CheckTag.PROPERTY_MESSAGE_FILE_UPLOAD_DISABLED);
		}

		if (command.equals(FILE_BROWSING)) {
			if (RequestCycleHandler.isEnabledForFileBrowsing(request))
				response = loader
						.getProperty(CheckTag.PROPERTY_MESSAGE_FILE_BROWSING_ENABLED);
			else
				response = loader
						.getProperty(CheckTag.PROPERTY_MESSAGE_FILE_BROWSING_DISABLED);
		}
		if (command.equals(COMPATIBLE_BROWSER)) {
			if (Compatibility.isCompatibleBrowser(request))
				response = loader
						.getProperty(CheckTag.PROPERTY_MESSAGE_COMPATIBLE_BROWSER);
			else
				response = loader
						.getProperty(CheckTag.PROPERTY_MESSAGE_NOT_COMPATIBLE_BROWSER);
		}

		try {
			out.print(response);
		} catch (IOException e) {
			throw new JspException(
					"Tag response could not be written to the user!", e);
		}

		return SKIP_BODY;
	}

}
