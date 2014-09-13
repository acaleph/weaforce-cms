package com.weaforce.system.component.spring;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.weaforce.entity.admin.User;

/**
 * Spring Security的工具类.
 * 
 * @author acaleph8@yahoo.com.cn
 */
public class Security {

	/**
	 * 取得当前用户的登录名,如果当前用户未登录则返回null.
	 */
	public static String getCurrentUserName() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		if (auth == null)
			return null;

		return auth.getName();
	}

	/**
	 * 取得当前用户
	 * 
	 * @return
	 */
	public static User getUser() {
		if (getCurrentUserName() == null
				|| "roleAnonymous".equals(getCurrentUserName()))
			return null;
		return (User) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
	}
}
