package com.weaforce.system.component.fckeditor.requestcycle.impl;

import javax.servlet.http.HttpServletRequest;

import com.weaforce.system.component.fckeditor.requestcycle.UserPathBuilder;

public class UserPathBuilderImpl implements UserPathBuilder {

	public String getUserFilesPath(HttpServletRequest request) {
		return "/Uploads/";
	}

}
