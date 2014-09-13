package com.weaforce.system.component.struts2.interceptor;

import com.weaforce.system.component.spring.Security;
import com.weaforce.entity.admin.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorizationInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 5814538794115199175L;

	
	public String intercept(ActionInvocation ai) throws Exception {
		String userName = Security.getCurrentUserName();
		// System.out.println("action: " + ai.getAction().toString());
		// 排除广告页检索授权
		if (ai.getAction().toString().indexOf("com.weaforce.cms.action.web") == -1) {
			if (userName != null && !"roleAnonymous".equals(userName)) {
				User user = Security.getUser();
				if (user != null)
					return ai.invoke();
			}
		} else
			return ai.invoke();
		return ActionSupport.LOGIN;
	}
}
