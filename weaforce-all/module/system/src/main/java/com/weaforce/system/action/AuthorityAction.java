package com.weaforce.system.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.entity.admin.Authority;
import com.weaforce.entity.admin.Role;
import com.weaforce.entity.app.Business;
import com.weaforce.system.service.IBaseService;
import com.weaforce.system.service.ISystemService;

@ParentPackage("default")
@Namespace("/system")
public class AuthorityAction extends AbstractCrudAction<Authority> {
	private static final long serialVersionUID = 5016570700114953241L;
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;
	@Autowired
	@Qualifier("baseService")
	private IBaseService baseService;

	private Authority authority;
	private Long authorityId;

	private String queryAuthorityCode;
	private Long businessId;
	private Long roleId;

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

	
	protected void prepareModel() throws Exception {
		authority = systemService.prepareAuthority(authority, authorityId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		if (businessId != null)
			authority.setAuthorityBusiness(baseService.getBusiness(businessId));
		authority = systemService.saveAuthority(authority);
		return list();
	}

	
	public String list() throws Exception {
		if (roleId != null)
			pageInfo = systemService.getAuthorityPage(pageInfo,
					queryAuthorityCode, businessId, roleId);
		return SUCCESS;
	}

	
	public String delete() throws Exception {
		return list();
	}

	

	/**
	 * 分配授权给角色
	 * 
	 * @throws Exception
	 */
	public void checked() throws Exception {
		systemService.checkedAuthority(authorityId, roleId);
	}

	/**
	 * 解除角色授权
	 * 
	 * @throws Exception
	 */
	public void uncheck() throws Exception {
		systemService.uncheckAuthority(authorityId, roleId);
	}

	/**
	 * 取得JSON格式的Authority
	 * 
	 * @return
	 */
	public String getAuthorityDDL() {
		return StrutsUtil.renderJSON(systemService.getAuthorityDDL(businessId));
	}

	/**
	 * 取得模块
	 * 
	 * @return
	 */
	public List<Business> getBusinessList() {
		return baseService.getBusinessListByResource("1");
	}

	/**
	 * 角色list
	 * 
	 * @return
	 */
	public List<Role> getRoleList() {
		return systemService.getRoleList(getAdmin().getAccount(), "1");
	}

	public String getQueryAuthorityCode() {
		return queryAuthorityCode;
	}

	public void setQueryAuthorityCode(String queryAuthorityCode) {
		this.queryAuthorityCode = queryAuthorityCode;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Authority getModel() {
		return authority;
	}

}
