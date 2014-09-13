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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.weaforce.system.component.fckeditor.FCKeditor;

/**
 * FCKeditor Tag class to access the
 * {@linkplain com.weaforce.sys.component.fckeditor.FCKeditor container}.
 * 
 * @version $Id: EditorTag.java 2151 2008-07-02 22:03:15Z mosipov $
 */
public class EditorTag extends TagSupport {

	private static final long serialVersionUID = -173091731589866140L;

	private String instanceName;
	private String width;
	private String height;
	private String toolbarSet;
	private String value;
	private String basePath;

	/** The underlying FCKeditor object */
	private FCKeditor fckEditor;

	/**
	 * Sets the name for the given editor instance
	 * 
	 * @param instanceName
	 *            some name without whitespaces
	 */
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	/**
	 * Sets the width of the textarea
	 * 
	 * @param width
	 *            width of the editor instance
	 * 
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * Sets the height of the textarea
	 * 
	 * @param height
	 *            height of the editor instance
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * Sets the name of the toolbar to display
	 * 
	 * @param toolbarSet
	 *            toolbar set of the editor instance
	 */
	public void setToolbarSet(String toolbarSet) {
		this.toolbarSet = toolbarSet;
	}

	/**
	 * Sets the editor document content
	 * 
	 * @param value
	 *            any HTML string
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Sets the dir where the FCKeditor files reside on the server
	 * 
	 * @param basePath
	 *            basePath of the editor instance (e.g. /fckeditor)
	 */
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	void setConfigParamAll(Map<String, String> map) {
		fckEditor.getConfig().putAll(map);
	}

	/**
	 * Initializes the FCKeditor container and sets attributes
	 * 
	 * @return EVAL_BODY_INCLUDE
	 */
	public int doStartTag() throws JspException {
		fckEditor = new FCKeditor(
				(HttpServletRequest) pageContext.getRequest(), instanceName,
				width, height, toolbarSet, value, basePath);

		return EVAL_BODY_INCLUDE;
	}

	
	public int doEndTag() throws JspException {

		JspWriter out = pageContext.getOut();

		try {
			out.println(fckEditor);
		} catch (IOException e) {
			throw new JspException("Tag response could not be written to the user!",e);
		}

		return EVAL_PAGE;
	}

}
