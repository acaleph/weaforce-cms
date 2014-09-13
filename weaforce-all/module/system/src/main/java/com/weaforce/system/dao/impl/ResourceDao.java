package com.weaforce.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.IResourceDao;
import com.weaforce.entity.admin.Resource;

@Repository("resourceDao")
public class ResourceDao extends GenericDao<Resource, Long> implements
		IResourceDao {
	private static final String QUERY_RESOURCE_BY_AUTHORITY_TYPE = " From Resource a left join fetch a.resourceAuthority Where a.resourceType=? ";
	private static final String QUERY_RESOURCE_BY_MODULE = " From Resource a Where a.resourceModule.moduleId=? Order by a.resourceGroupOrder ";

	public List<Resource> getResourceListByType(String resourceType) {
		return listQueryDistinct(QUERY_RESOURCE_BY_AUTHORITY_TYPE, resourceType);
	}

	public List<Resource> getResourceListByModule(Long moduleId) {
		return listQuery(QUERY_RESOURCE_BY_MODULE, moduleId);
	}

}
