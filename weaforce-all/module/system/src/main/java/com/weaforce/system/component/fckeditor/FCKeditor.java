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

package com.weaforce.system.component.fckeditor;

import javax.servlet.http.HttpServletRequest;

import com.weaforce.core.util.PropertiesLoader;
import com.weaforce.system.component.fckeditor.tool.Compatibility;
import com.weaforce.system.component.fckeditor.tool.Utils;
import com.weaforce.system.component.fckeditor.tool.XHtmlTagTool;

/**
 * Java represantation of the FCKeditor. This class creates the html code for
 * the FCKeditor based on the following things:
 * <ul>
 * <li>browser capabilities</li>
 * <li>different properties settings managed by the {@link PropertiesLoader}</li>
 * <li>settings from the FCKeditor tag, template engines and other systems</li>
 * </ul>
 * 
 * @version $Id: FCKeditor.java 2151 2008-07-02 22:03:15Z mosipov $
 */
public class FCKeditor {

	private FCKeditorConfig config;
	private String instanceName;
	private String value;
	private String basePath;
	private HttpServletRequest request;
	private static PropertiesLoader loader = new PropertiesLoader();

	// defaults
	private String toolbarSet = loader.getProperty("fckeditor.toolbarSet");
	private String width = loader.getProperty("fckeditor.width");
	private String height = loader.getProperty("fckeditor.height");
	private String defaultBasePath = loader.getProperty("fckeditor.basePath");

	/**
	 * Main constructor.<br>
	 * All important settings are done here and will be preset by the defaults
	 * taken from {@link PropertiesLoader}.
	 * 
	 * @param request
	 *            request object
	 * @param instanceName
	 *            unique name
	 * @param width
	 *            width
	 * @param height
	 *            height
	 * @param toolbarSet
	 *            toolbarSet name
	 */
	public FCKeditor(final HttpServletRequest request,
			final String instanceName, final String width, final String height,
			final String toolbarSet, final String value, final String basePath) {
		this.request = request;
		this.instanceName = instanceName;
		if (Utils.isNotEmpty(width))
			this.width = width;
		if (Utils.isNotEmpty(height))
			this.height = height;
		if (Utils.isNotEmpty(toolbarSet))
			this.toolbarSet = toolbarSet;
		if (Utils.isNotEmpty(value))
			this.value = value;
		if (Utils.isNotEmpty(basePath))
			this.basePath = request.getContextPath().concat(basePath);
		else
			this.basePath = request.getContextPath().concat(defaultBasePath);

		config = new FCKeditorConfig();
	}

	/**
	 * Just a wrapper to {@link FCKeditor}.
	 * 
	 * @param request
	 *            request object
	 * @param instanceName
	 *            unique name
	 */

	public FCKeditor(final HttpServletRequest request, final String instanceName) {
		this(request, instanceName, null, null, null, null, null);
	}

	/**
	 * Set the unique name of the editor
	 * 
	 * @param instanceName
	 *            name
	 */
	public void setInstanceName(final String instanceName) {
		this.instanceName = instanceName;
	}

	/**
	 * Set the initial value to be edited as HTML markup.
	 * 
	 * @param value
	 *            value
	 */
	public void setValue(final String value) {
		this.value = value;
	}

	/**
	 * Sets the directory where the FCKeditor resides on the server.<br />
	 * <strong>Remarks</strong>: Avoid using relative paths. Use an absolute
	 * path from the context (e.g. /fckeditor).
	 * 
	 * @param basePath
	 *            path
	 */
	public void setBasePath(final String basePath) {
		this.basePath = basePath;
	}

	/**
	 * Set the name of the toolbar to display
	 * 
	 * @param toolbarSet
	 *            toolbar name
	 */
	public void setToolbarSet(final String toolbarSet) {
		this.toolbarSet = toolbarSet;
	}

	/**
	 * Set the width of the textarea
	 * 
	 * @param width
	 *            width
	 */
	public void setWidth(final String width) {
		this.width = width;
	}

	/**
	 * Set the height of the textarea
	 * 
	 * @param height
	 *            height
	 */
	public void setHeight(final String height) {
		this.height = height;
	}

	/**
	 * Get the advanced configuration set.<br />
	 * By adding elements to this collection you can override the settings
	 * specified in the config.js file.
	 * 
	 * @return configuration collection
	 */
	public FCKeditorConfig getConfig() {
		return config;
	}

	/**
	 * Set the advanced configuation set.
	 * 
	 * @param config
	 *            configuration collection
	 */
	public void setConfig(FCKeditorConfig config) {
		this.config = config;
	}

	/**
	 * Escape base XML entities as specified <a
	 * href="http://en.wikipedia.org/wiki/Xml#Entity_references">here</a>.
	 * 
	 * @param str
	 *            Text to escape.
	 * @return Escaped text.
	 */
	private String escapeXml(String str) {
		if (Utils.isEmpty(str))
			return str;

		/*
		 * TODO Strings are inefficent. It should be done like in Commons Lang
		 * 2.4 StringUtils#replaceEach(String, String[], String[])
		 */
		str = str.replaceAll("&", "&#38;");
		str = str.replaceAll("<", "&#60;");
		str = str.replaceAll(">", "&#62;");
		str = str.replaceAll("\"", "&#34;");
		str = str.replaceAll("'", "&#39;");
		return str;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see #createHtml()
	 */
	/**
	 * This method simply wraps to {@link #createHtml()}.
	 * 
	 * @see #createHtml()
	 */
	
	public String toString() {
		return createHtml();
	}

	/**
	 * Minimum implementation, see ticket #27 for detailed information. Generate
	 * the HTML Code for the editor.<br />
	 * Evaluate the browser capabilities and generate the editor if compatible,
	 * or a simple textarea otherwise.
	 * 
	 * @return FCKeditor html code
	 */
	public String createHtml() {
		StringBuffer strEditor = new StringBuffer();

		strEditor.append("<div>");
		String encodedValue = escapeXml(value.replaceAll("((\r?\n)+|\t*)", ""));

		if (Compatibility.check(request.getHeader("user-agent"))) {
			strEditor.append(createInputForVariable(instanceName, instanceName,
					encodedValue));

			// create config html
			String configStr = config.getUrlParams();
			if (Utils.isNotEmpty(configStr))
				strEditor.append(createInputForVariable(null,
						instanceName.concat("___Config"), configStr));

			// create IFrame
			String sLink = basePath.concat(
					"/editor/fckeditor.html?InstanceName=")
					.concat(instanceName);
			if (Utils.isNotEmpty(toolbarSet))
				sLink += "&Toolbar=".concat(toolbarSet);
			XHtmlTagTool iframeTag = new XHtmlTagTool("iframe",
					XHtmlTagTool.SPACE);
			iframeTag.addAttribute("id", instanceName.concat("___Frame"));
			iframeTag.addAttribute("src", sLink);
			iframeTag.addAttribute("width", width);
			iframeTag.addAttribute("height", height);
			iframeTag.addAttribute("frameborder", "no");
			iframeTag.addAttribute("scrolling", "no");
			strEditor.append(iframeTag);

		} else {
			XHtmlTagTool textareaTag = new XHtmlTagTool("textarea",
					encodedValue);
			textareaTag.addAttribute("name", instanceName);
			textareaTag.addAttribute("rows", "4");
			textareaTag.addAttribute("cols", "40");
			textareaTag.addAttribute("wrap", "virtual");
			textareaTag
					.addAttribute("style",
							"width: ".concat(width).concat("; height: ")
									.concat(height));
		}
		strEditor.append("</div>");
		return strEditor.toString();
	}

	private String createInputForVariable(final String name, final String id,
			final String value) {
		XHtmlTagTool tag = new XHtmlTagTool("input");
		if (Utils.isNotEmpty(id))
			tag.addAttribute("id", id);
		if (Utils.isNotEmpty(name))
			tag.addAttribute("name", name);
		tag.addAttribute("value", value);
		tag.addAttribute("type", "hidden");
		return tag.toString();
	}
}
