package com.weaforce.system.dao;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.entity.admin.Resource;

public interface IResourceDao extends IGenericDao<Resource, Long> {
	public List<Resource> getResourceListByType(String resourceType);
	public List<Resource> getResourceListByModule(Long moduleId);
}
