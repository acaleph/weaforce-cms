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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * This tag sets configuration options for the surrounding editor tag. Provide
 * any configuraion options you want as described <a
 * href="http://docs.fckeditor.net/FCKeditor_2.x/Developers_Guide/Configuration/Configuration_Options">here</a>.
 * This tag utilizes the dynamic attribute features provided by the
 * {@link DynamicAttributes} interface.<br />
 * <strong>Attention</strong>: This tag can only be nested with an editor tag.
 * 
 * @version $Id: ConfigTag.java 2151 2008-07-02 22:03:15Z mosipov $
 */
public class ConfigTag extends TagSupport implements DynamicAttributes {

	private Map<String, String> params = new HashMap<String, String>();

	private static final long serialVersionUID = -5282810094404700422L;

	
	public int doStartTag() throws JspException {

		Tag ancestor = findAncestorWithClass(this, EditorTag.class);
		if (ancestor == null)
			throw new JspException(
					"the config tag can only be nested within an editor tag");
		EditorTag editorTag = (EditorTag) ancestor;
		editorTag.setConfigParamAll(params);

		return SKIP_BODY;
	}

	/**
	 * Sets the desired FCKeditor configuration option.
	 */
	public void setDynamicAttribute(String arg0, String name, Object value)
			throws JspException {
		if (value != null)
			params.put(name, value.toString());
	}

}
