package com.weaforce.system.component.struts2.interceptor;

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.system.component.spring.Security;
import com.weaforce.entity.admin.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 维护实体类的公共属性
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public class PrimaryEntityInterceptor extends MethodFilterInterceptor {
	private static final long serialVersionUID = 7224237874245817556L;

	private User user;

	
	protected String doIntercept(ActionInvocation ai) throws Exception {
		if (Security.getCurrentUserName() != null) {
			user = Security.getUser();
			if (user != null) {
				if (ai.getAction() instanceof ModelDriven) {
					ModelDriven<?> action = (ModelDriven<?>) ai.getAction();
					PrimaryEntity T = (PrimaryEntity) action.getModel();
					if (T instanceof PrimaryEntity) {
						if (T.getAccount() == null || "".equals(T.getAccount())) {
							T.setAccount(user.getAccount());
							T.setCreator(user.getUserLogin());
							//T.setCreateTime(System.currentTimeMillis());
						} else {
							T.setLastUpdate(user.getUserLogin());
							T.setLastUpdateTime(System.currentTimeMillis());
						}
					}
				}
			}
			return ai.invoke();
		} else {
			return Action.LOGIN;
		}
	}
}
