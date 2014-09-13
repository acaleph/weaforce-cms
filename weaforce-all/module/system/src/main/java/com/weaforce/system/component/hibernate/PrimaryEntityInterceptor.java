package com.weaforce.system.component.hibernate;

import java.io.Serializable;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.system.component.spring.Security;
import com.weaforce.entity.admin.User;

/**
 * 实体基类拦截器
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public class PrimaryEntityInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = 1705767050548039228L;

	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) throws CallbackException {
		if (entity instanceof PrimaryEntity) {
			User user = Security.getUser();
			boolean modified = false;
			for (int i = 0; i < propertyNames.length; i++) {
				if ("lastUpdateTime".equals(propertyNames[i])) {
					currentState[i] = System.currentTimeMillis();
					modified = true;
				} else if ("lastUpdate".equals(propertyNames[i])) {
					currentState[i] = user.getUsername();
					modified = true;
				}
			}
			return modified;
		}
		return false;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) throws CallbackException {
		if (entity instanceof PrimaryEntity) {
			User user = Security.getUser();
			boolean insert = false;
			for (int i = 0; i < propertyNames.length; i++) {
				if ("account".equals(propertyNames[i])) {
					state[i] = user.getAccount();
					insert = true;
				} else if ("creator".equals(propertyNames[i])) {
					state[i] = user.getUsername();
					insert = true;
				} else if ("logicLock".equals(propertyNames[i])) {
					state[i] = "0";
					insert = true;
				}
			}
			return insert;
		}
		return false;
	}

}
