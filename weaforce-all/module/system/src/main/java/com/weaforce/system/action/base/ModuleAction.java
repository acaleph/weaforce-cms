package com.weaforce.system.action.base;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.entity.app.Business;
import com.weaforce.entity.app.Module;
import com.weaforce.system.service.IBaseService;

@ParentPackage("default")
@Namespace("/system/base")
public class ModuleAction extends AbstractCrudAction<Module> {

	private static final long serialVersionUID = 2431051003514675024L;
	@Autowired
	@Qualifier("baseService")
	private IBaseService baseService;

	private Long moduleId;
	private Module module;

	private String moduleQueryNameEn;
	private String queryDateFrom;
	private String queryDateTo;
	private Long businessId;

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	
	protected void prepareModel() throws Exception {
		module = baseService.prepareModule(module, moduleId, businessId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		module = baseService.saveModule(module, businessId);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = baseService.getModulePage(pageInfo, businessId,
				moduleQueryNameEn);
		return SUCCESS;
	}


	
	public String delete() throws Exception {
		baseService.deleteModule(module.getModuleId());
		return list();
	}

	/**
	 * 取得JSON格式的Module
	 * 
	 * @return
	 */
	public String getModuleDDL() {
		return StrutsUtil.renderJSON(baseService.getModuleDDL(businessId));
	}

	/**
	 * 取得资源系统list
	 * 
	 * @return
	 */
	public List<Business> getBusinessList() {
		return baseService.getBusinessList();
	}

	public String getModuleQueryNameEn() {
		return moduleQueryNameEn;
	}

	public void setModuleQueryNameEn(String moduleQueryNameEn) {
		this.moduleQueryNameEn = moduleQueryNameEn;
	}

	public String getQueryDateFrom() {
		return queryDateFrom;
	}

	public void setQueryDateFrom(String queryDateFrom) {
		this.queryDateFrom = queryDateFrom;
	}

	public String getQueryDateTo() {
		return queryDateTo;
	}

	public void setQueryDateTo(String queryDateTo) {
		this.queryDateTo = queryDateTo;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public Module getModel() {
		return this.module;
	}

}
