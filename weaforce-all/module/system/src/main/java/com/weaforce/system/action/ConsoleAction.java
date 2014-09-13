package com.weaforce.system.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 如果没有该类，拦截器无法对console.action进行过滤
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/")
public class ConsoleAction extends ActionSupport {

	private static final long serialVersionUID = -2377044005535352392L;

	public String execute() throws Exception {
		return SUCCESS;
	}
}
