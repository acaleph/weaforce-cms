package com.weaforce.core.web.page;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Pagination navigation bar
 * 
 * 1. Get the data bean
 * 
 * 2. Get the URL
 * 
 * @author yanjiacheng
 * 
 */
public class Pagination extends TagSupport {

	private static final long serialVersionUID = -3362040492296651035L;
	// Saving the page items bean in the request
	private String bean = "page";
	private String url;
	private Integer pageNumber;

	public int doStartTag() {
		JspWriter writer = pageContext.getOut();
		Page<?> onePage = (Page<?>) pageContext.getRequest().getAttribute(bean);
		if (onePage == null)
			return SKIP_BODY;

		return SKIP_BODY;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	

}
