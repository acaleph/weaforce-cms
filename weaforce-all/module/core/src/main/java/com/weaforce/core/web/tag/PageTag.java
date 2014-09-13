package com.weaforce.core.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class PageTag<T> extends ComponentTagSupport {

	private static final long serialVersionUID = -8717355206173514661L;

	
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		return new Page<T>(arg0);
	}

	protected String styleClass = "";
	protected String formName = "";
	protected String beanName = "forms(0)";
	protected String javaScript = "";
	private String value;

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getJavaScript() {
		return javaScript;
	}

	public void setJavaScript(String javaScript) {
		this.javaScript = javaScript;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	protected void populateParams() {
		super.populateParams();
		Page<T> tag = (Page<T>) component;
		tag.setStyleClass(styleClass);
		tag.setFormName(formName);
		tag.setBeanName(beanName);
		tag.setJavaScript(javaScript);
		tag.setValue(value);
	}
}
