package com.weaforce.core.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class SysItemTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;

	public int doEndTag() throws JspTagException {
		JspWriter out = pageContext.getOut();
		try {
			out.write("id:" + id + " name:" + name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
