package com.weaforce.system.component.spring;

import java.util.Map;

import com.google.common.collect.Maps;
import com.weaforce.core.web.page.Page;
import com.weaforce.entity.admin.User;
import com.weaforce.entity.app.Menu;

public abstract class BasicController<T> {
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	public Page<T> page = null;

	protected User getUser() {
		return Security.getUser();
	}
}
