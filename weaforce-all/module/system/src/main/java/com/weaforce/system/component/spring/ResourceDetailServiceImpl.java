package com.weaforce.system.component.spring;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weaforce.system.dao.IResourceDao;
import com.weaforce.entity.admin.Resource;

/**
 * 从数据库查询URL--授权定义的RequestMapService实现类.
 * 
 * @author acaleph8@yahoo.com.cn
 */
@Service("resourceDetailService")
@Transactional(readOnly = true)
public class ResourceDetailServiceImpl implements ResourceDetailService {
	@Autowired
	@Qualifier("resourceDao")
	private IResourceDao resourceDao;

	/**
	 * @see ResourceDetailService#getRequestMap()
	 */
	public LinkedHashMap<String, String> getRequestMap() throws Exception {
		List<Resource> resourceList = resourceDao
				.getResourceListByType(Resource.TYPE_URL);
		LinkedHashMap<String, String> requestMap = new LinkedHashMap<String, String>(
				resourceList.size());
		for (Resource resource : resourceList) {
			requestMap.put(resource.getResourceValue(), resource
					.getResourceAuthorityCodes());
		}
		return requestMap;
	}

}
