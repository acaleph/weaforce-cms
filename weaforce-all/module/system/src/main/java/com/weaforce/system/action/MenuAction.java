package com.weaforce.system.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.entity.app.Menu;
import com.weaforce.system.service.ISystemService;

@ParentPackage("default")
@Namespace("/system")
public class MenuAction extends AbstractCrudAction<Menu> {
	private static final long serialVersionUID = 8664206720641684333L;
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	private Menu menu;
	private Long menuId;

	private Long menuFid;
	private Long fid;

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	protected void prepareModel() throws Exception {
	}

	public String input() throws Exception {
		return INPUT;
	}

	public String save() throws Exception {
		menu = systemService.saveMenu(menu, menuFid);
		return list();
	}

	public String list() throws Exception {
		return SUCCESS;
	}

	public String delete() throws Exception {
		systemService.deleteMenu(menuId);
		return list();
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	/**
	 * 没有checkbox的菜单树,新增同级,下一级,编辑菜单功能,在menu.jsp中调用
	 * 
	 * @return
	 * @throws Exception
	 */
	public String tree() throws Exception {
		return StrutsUtil.renderHTML(systemService.getMenuHtml(fid).toString());
	}

	/**
	 * 有checkbox的菜单树,实现功能菜单的权限分发,在role-input.jsp中调用
	 * 
	 * @return
	 * @throws Exception
	 */

	public String roleMenuHtml() throws Exception {
		return StrutsUtil.renderHTML(systemService.getRoleMenuHtml(menuFid,
				getAdmin().getDefaultUserRole().getRoleMenuRank()).toString());
	}

	/**
	 * Called by jquery ajax function
	 * 
	 * @param menuFid
	 */
	public void setMenuFid(Long menuFid) {
		this.menuFid = menuFid;
	}

	public Menu getModel() {
		return menu;
	}
}
