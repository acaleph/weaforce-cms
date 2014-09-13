package com.weaforce.system.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.entity.admin.Authority;
import com.weaforce.entity.admin.Resource;
import com.weaforce.entity.app.Business;
import com.weaforce.entity.app.Module;
import com.weaforce.system.service.IBaseService;
import com.weaforce.system.service.ISystemService;

@ParentPackage("default")
@Namespace("/system")
public class ResourceAction extends AbstractCrudAction<Resource> {

	private static final long serialVersionUID = -7105516560504882889L;

	@Autowired
	@Qualifier("baseService")
	private IBaseService baseService;
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	private Resource resource;
	private Long resourceId;

	private List<Business> businessList;

	private List<Resource> resourceList;
	private Long businessId;
	private Long moduleId;
	private Long authorityId;

	
	protected void prepareModel() throws Exception {
		resource = systemService.prepareResource(resource, resourceId);

	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		resource = systemService.saveResource(resource, moduleId);
		return list();
	}

	
	public String delete() throws Exception {
		return list();
	}

	
	public String list() throws Exception {
		businessList = baseService.getBusinessListByResource("1");
		if (businessId == null)
			if (businessList.size() > 0)
				businessId = businessList.get(0).getBusinessId();
		if (moduleId != null)
			resourceList = systemService.getResourceListByModule(moduleId,
					authorityId);
		return SUCCESS;
	}


	/**
	 * 绑定授权资源
	 * 
	 * @throws Exception
	 */
	public void checked() throws Exception {
		systemService.checkedResource(resourceId, authorityId);
	}

	/**
	 * 取消绑定授权资源
	 * 
	 * @throws Exception
	 */
	public void uncheck() throws Exception {
		systemService.uncheckResource(resourceId, authorityId);
	}

	public Resource getModel() {
		return resource;
	}

	/**
	 * 取得资源系统list
	 * 
	 * @return
	 */
	public List<Business> getBusinessList() {
		return businessList;
	}

	/**
	 * 根据系统,取得资源模块list
	 * 
	 * @return
	 */
	public List<Module> getModuleList() {
		return baseService.getModuleListByBusinessResource(businessId, "1");
	}

	/**
	 * 根据系统,取得授权模块list
	 * 
	 * @return
	 */
	public List<Authority> getAuthorityList() {
		return systemService.getAuthorityListByBusiness(businessId);
	}

	public List<Resource> getResourceList() {
		return resourceList;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

}
