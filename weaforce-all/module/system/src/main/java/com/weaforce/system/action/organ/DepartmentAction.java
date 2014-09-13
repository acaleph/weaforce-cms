package com.weaforce.system.action.organ;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.entity.organ.Department;
import com.weaforce.system.service.ISystemService;

@ParentPackage("default")
@Namespace("/system/organ")
public class DepartmentAction extends AbstractCrudAction<Department> {

	private static final long serialVersionUID = 2721074134640977132L;
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	private Long departmentId;
	private Department department;
	private Long accountId;

	private String departmentQueryName;

	public Department getModel() {
		return this.department;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * 当从帐套管理中新增部门时，将帐套ID转为字符，写入到当前新增用户信息中
	 * 
	 * @param accountId
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	
	protected void prepareModel() throws Exception {
		department = systemService.prepareDepartment(department, departmentId,
				accountId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		department = systemService.saveDepartment(department);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = systemService.getDepartmentPage(pageInfo, getAdmin()
				.getAccount(), departmentQueryName);
		return SUCCESS;
	}

	

	/**
	 * 设置部门为非活动
	 */
	
	public String delete() throws Exception {
		if (departmentId != null) {
			department = systemService.getDepartment(departmentId);
			department.setDepartmentIsActive("0");
			department = systemService.saveDepartment(department);
		}
		return list();
	}

	public String getDepartmentQueryName() {
		return departmentQueryName;
	}

	public void setDepartmentQueryName(String departmentQueryName) {
		this.departmentQueryName = departmentQueryName;
	}

}
