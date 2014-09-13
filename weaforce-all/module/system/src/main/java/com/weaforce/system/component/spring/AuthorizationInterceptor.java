package com.weaforce.system.component.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.weaforce.entity.admin.User;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean handlerOk = super.preHandle(request, response, handler);
		if (handlerOk) {
			String url = request.getRequestURL().toString();
			if (url.endsWith("login"))
				return true;

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if (user == null) {
				response.sendRedirect(request.getContextPath() + "/login");
			}
			return true;
		}
		return false;
	}
}
