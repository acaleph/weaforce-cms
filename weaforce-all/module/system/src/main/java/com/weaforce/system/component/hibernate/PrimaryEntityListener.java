package com.weaforce.system.component.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.SaveOrUpdateEventListener;

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.system.component.spring.Security;
import com.weaforce.entity.admin.User;

public class PrimaryEntityListener implements SaveOrUpdateEventListener {

	private static final long serialVersionUID = 5418750004677212128L;

	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent event)
			throws HibernateException {
		// 判断是否有用户登录,没有登录,不允许拦截
		if (Security.getCurrentUserName() != null) {
			Object o = event.getObject();
			if (o instanceof PrimaryEntity) {
				PrimaryEntity entity = (PrimaryEntity) o;
				User user = Security.getUser();
				if (entity.getAccount() == null || entity.getCreator() == null
						|| "".equals(entity.getAccount())
						|| "".equals(entity.getCreator())) {
					entity.setAccount(user.getAccount());
					entity.setCreator(user.getUserLogin());
				} else {
					// 如果有登录用户,记录用户登录ID
					if (user != null)
						entity.setLastUpdate(user.getUserLogin());
					entity.setLastUpdateTime(System.currentTimeMillis());
				}
			}
		}
	}

}
