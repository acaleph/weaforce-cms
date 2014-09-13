package com.weaforce.system.component.struts2.interceptor;

import java.lang.reflect.Method;

import com.weaforce.core.annotation.Token;
import com.opensymphony.xwork2.ActionInvocation;

public class TokenInterceptor extends
		org.apache.struts2.interceptor.TokenInterceptor {
	private static final long serialVersionUID = 802981110432899971L;

	
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		if (action != null) {
			Method method = getActionMethod(action.getClass(), invocation
					.getProxy().getMethod());
			Token token = method.getAnnotation(Token.class);
			if (token != null) {
				return super.doIntercept(invocation);
			}
		}
		return invocation.invoke();
	}

	public static final Method getActionMethod(
			Class<? extends Object> actionClass, String methodName)
			throws NoSuchMethodException {
		Method method;
		try {
			method = actionClass.getMethod(methodName, new Class[0]);
		} catch (NoSuchMethodException e) {
			String altMethodName = "do"
					+ methodName.substring(0, 1).toUpperCase()
					+ methodName.substring(1);
			method = actionClass.getMethod(altMethodName, new Class[0]);
		}
		return method;
	}
}
