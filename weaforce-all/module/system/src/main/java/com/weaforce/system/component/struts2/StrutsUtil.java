package com.weaforce.system.component.struts2;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Struts 工具类
 * 
 * @author acaleph8@yahoo.com
 * 
 */
public class StrutsUtil {

	final static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 取得HttpServletRequest
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 取得HttpServletResponse
	 * 
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 取得HttpServletRequest Parameter
	 */
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	/**
	 * 直接输出内容的简便函数.
	 */
	public static String render(String text, String contentType) {
		// HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletResponse response = getResponse();
		try {
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * 直接输出字符串.
	 * 
	 * @param text
	 * @return String
	 */
	public static String renderText(String text) {
		return render(text, "text/plain;charset=UTF-8");
	}

	/**
	 * 直接输出HTML.
	 * 
	 * @param html
	 * @return String
	 */
	public static String renderHTML(String html) {
		return render(html, "text/html;charset=UTF-8");
	}

	/**
	 * 直接输出XML.
	 * 
	 * @param xml
	 * @return String
	 */
	public static String renderXML(String xml) {
		return render(xml, "text/xml;charset=UTF-8");
	}

	/**
	 * 直接输出JSON
	 * 
	 * @param json
	 * @return String
	 */

	public static String renderJSON(String json) {
		return render(json, "application/json;charset=UTF-8");
	}

	/**
	 * 应用jackson直接生成 JSON格式数据
	 * 
	 * @param response
	 * @param data
	 */
	public static void renderJSON(final Object data) {
		HttpServletResponse response = getResponse();
		setNoCacheHeader(response);
		try {
			mapper.writeValue((JsonGenerator) response, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取消缓存
	 * 
	 * @param response
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache");
	}

}
