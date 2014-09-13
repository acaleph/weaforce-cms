package com.weaforce.system.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.core.util.CollectionUtil;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.entity.admin.Authority;
import com.weaforce.entity.admin.Role;
import com.weaforce.entity.admin.User;
import com.weaforce.entity.app.Menu;
import com.weaforce.system.service.ISystemService;

@ParentPackage("default")
@Namespace("/system")
public class RoleAction extends AbstractCrudAction<Role> {
	private static final long serialVersionUID = 653297398071397350L;
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	private Long roleId;
	private Role role;

	private Long fid;
	private String roleMenuRank;
	private String menuRank;
	private String menuValue;

	private List<Long> checkedAuthorityIds;

	private String queryName;
	private String queryActive = "1";

	public Role getModel() {
		return this.role;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	
	protected void prepareModel() throws Exception {
		if (this.roleId == null)
			this.role = new Role();
		else {
			this.role = systemService.getRole(roleId);
		}
	}

	
	public String input() throws Exception {
		checkedAuthorityIds = role.getRoleAuthorityIds();
		// 设置为当前用户所属角色的roleMenuRank
		if (role.getRoleMenuRank() == null || "".equals(role.getRoleMenuRank()))
			role.setRoleMenuRank(systemService.getUserByLogin(
					getAdmin().getUserLogin()).getDefaultUserRole()
					.getRoleMenuRank());
		return INPUT;
	}

	/**
	 * 授权list
	 * 
	 * @return
	 */
	public Set<Authority> getAuthoritieList() {
		return role.getRoleAuthority();
	}

	public List<Long> getCheckedAuthorityIds() {
		return checkedAuthorityIds;
	}

	public void setCheckedAuthorityIds(List<Long> checkedAuthorityIds) {
		this.checkedAuthorityIds = checkedAuthorityIds;
	}

	
	public String list() throws Exception {
		return SUCCESS;
	}

	
	public String lock() throws Exception {
		systemService.saveRole(role);
		return list();
	}

	public void setMenuRank(String menuRank) {
		this.menuRank = menuRank;
	}

	
	public String save() throws Exception {
		CollectionUtil.mergeByCheckedIds(role.getRoleAuthority(),
				checkedAuthorityIds, Authority.class, "authorityId");
		roleMenuRank = "1";
		if (",".equals(roleMenuRank))
			role.setRoleMenuRank("");
		else if (!"".equals(menuRank) && menuRank.length() > 1) {
			menuRank = menuRank.substring(1, menuRank.length() - 1);
			String[] menu = menuRank.split(",");
			for (String s : menu) {
				Menu m = systemService.getMenu(Long.parseLong(s))
						.getMenuParent();
				if (m != null
						&& (menuRank.contains("," + m.getMenuId().toString()
								+ ",") || m.getMenuId() == 1))
					roleMenuRank = roleMenuRank + "," + s;
			}
			role.setRoleMenuRank(roleMenuRank);
		}
		role = systemService.saveRole(role);
		return list();
	}

	/**
	 * 只有非系统角色才可以被设置为非活动,将不能被user引用
	 */
	
	public String delete() throws Exception {
		if (roleId != null) {
			role = systemService.getRole(roleId);
			if ("0".equals(role.getRoleIsSystem())) {
				role.setRoleIsActive("0");
				role = systemService.saveRole(role);
			}
		}
		return list();
	}

	/**
	 * 如果当前role没有设置menu rank,取当前用户的menu rank
	 * 
	 * @return HTML string
	 * @throws Exception
	 */
	public String roleMenuHtml() throws Exception {
		StringBuffer menuHtml = new StringBuffer();
		if (roleId != null) {
			role = systemService.getRole(roleId);
			if (role.getRoleMenuRank() != null
					&& !"".equals(role.getRoleMenuRank()))
				roleMenuRank = role.getRoleMenuRank();
			else {
				User user = systemService.getUserByLogin(getAdmin()
						.getUserLogin());
				roleMenuRank = user.getDefaultUserRole().getRoleMenuRank();
			}
		}
		menuHtml
				.append("<UL class=\"jqueryTree\" style=\"display: none;\"> \n");
		List<Menu> menuList = systemService.getUserMenuListByRankFid(
				roleMenuRank, fid);
		roleMenuRank = "," + roleMenuRank + ",";
		String href = new String("javascript:;");
		for (Menu o : menuList) {
			if (o.getMenuUrl() != null && !"".equals(o.getMenuUrl())) {
				menuHtml.append("<LI class='isHref'>");
			} else {
				href = "#";
				menuHtml.append("<LI class='noHref collapsed'>");
			}
			menuHtml.append("<A href=\"" + href + "\" rel=\"" + o.getMenuId()
					+ "\"> ");
			if (roleMenuRank.contains("," + o.getMenuId() + ","))
				menuHtml
						.append("<input type=\"checkbox\" id=\""
								+ o.getMenuId()
								+ "\" name=\"authority\" value=\""
								+ o.getMenuId()
								+ ":1\" onClick=\"javascript:(this.checked)? this.value=this.id + ':1':this.value=this.id + ':0';resetTree(this); \" checked=\"true\" > ");
			else
				menuHtml
						.append("<input type=\"checkbox\" id=\""
								+ o.getMenuId()
								+ "\" name=\"authority\" value=\""
								+ o.getMenuId()
								+ ":0\" onClick=\"javascript:(this.checked)? this.value=this.id + ':1':this.value=this.id + ':0';resetTree(this); \" > ");
			menuHtml.append(o.getMenuNameCn() + " </A></LI> \n");
		}
		menuHtml.append("</UL>");
		return StrutsUtil.renderHTML(menuHtml.toString());
	}

	/**
	 * <ul>
	 * <p>
	 * 处理当前节点的父子结点
	 * </p>
	 * <li>如果当前节点：checkbox.checked = true,选中所有的父结点</li>
	 * <br>
	 * <li>如果当前节点：checkbox.checked = false,取消所有的子结点</li>
	 * </ul>
	 * 
	 */
	public String doChecked() throws Exception {
		StringBuffer menuJson = new StringBuffer();
		if (menuValue != null && !"".equals(menuJson)) {
			Long id = Long.valueOf(menuValue.substring(0,
					menuValue.length() - 2));
			if ("1".equals(menuValue.substring(menuValue.length() - 1,
					menuValue.length()))) {
				menuJson.append("[" + checkedParent(id, "") + "]");
			}
			if ("0".equals(menuValue.substring(menuValue.length() - 1,
					menuValue.length()))) {
				List<Menu> menuList = new ArrayList<Menu>();
				menuJson.append("[" + uncheckChildren(id, 0, "", menuList)
						+ "]");
			}
		}
		// System.out.println("doCheck: " + menuJson.toString());
		return StrutsUtil.renderJSON(menuJson.toString());
	}

	/**
	 * <p>
	 * 从当前结点的父结点开始递归处理,得到所有的父,爷节点
	 * <p>
	 * 
	 * @param id
	 * @param menuIdString
	 * @return
	 */

	public String checkedParent(Long id, String menuIdString) {
		Menu curMenu = systemService.getMenu(id).getMenuParent();
		if (curMenu != null)
			if ("".equals(menuIdString))
				menuIdString = "{\"menuId\":" + "\""
						+ curMenu.getMenuId().toString() + "\"}";
			else
				menuIdString = menuIdString + "," + "{\"menuId\":" + "\""
						+ curMenu.getMenuId().toString() + "\"}";
		if (curMenu.getMenuParent() != null) {
			return checkedParent(curMenu.getMenuId(), menuIdString);
		} else {
			return menuIdString;
		}
	}

	/**
	 * <p>
	 * 从当前结点的子结点开始递归处理,得到所有子结点
	 * <p>
	 * 
	 * @param id
	 * @param lastIndex
	 * @param menuIdString
	 * @param menuList
	 * @return
	 */

	public String uncheckChildren(Long id, Integer lastIndex,
			String menuIdString, List<Menu> menuList) {
		List<Menu> menuChildren = systemService.getMenu(id).getMenuChild();
		if (menuChildren.size() > 0)
			menuList.addAll(lastIndex, menuChildren);
		if (lastIndex < menuList.size()) {
			if ("".equals(menuIdString))
				menuIdString = "{\"menuId\":" + "\""
						+ menuList.get(lastIndex).getMenuId().toString()
						+ "\"}";
			else
				menuIdString = menuIdString + "," + "{\"menuId\":" + "\""
						+ menuList.get(lastIndex).getMenuId().toString()
						+ "\"}";
			lastIndex++;
			return uncheckChildren(menuList.get(lastIndex - 1).getMenuId(),
					lastIndex, menuIdString, menuList);
		} else {
			// After searching the whole child node of the tree.
			return menuIdString;
		}
	}

	public void setMenuValue(String menuValue) {
		this.menuValue = menuValue;
	}

	/**
	 * <p>
	 * 返回MENU JSON数据
	 * </p>
	 * 
	 * @return menuJSON.toString();
	 */

	public String doRoleMenuJSON() {
		StringBuffer menuJSON = new StringBuffer();
		User user = getAdmin();
		if (user != null)
			System.out.println(user.getUserLogin() + " menuFid " + fid);
		else
			System.out.println("user is null." + " menuFid " + fid);
		List<Menu> list = systemService.getUserMenuListByRankFid(getAdmin()
				.getDefaultUserRole().getRoleMenuRank(), fid);
		menuJSON.append("[\n ");
		for (Menu o : list) {
			menuJSON.append("   { \n ");
			menuJSON.append("      \"menuId\":\"" + o.getMenuId() + "\",\n");
			menuJSON.append("      \"menuName:\"\"" + o.getMenuNameCn()
					+ "\",\n");
			if (o.getMenuChild().size() > 0)
				menuJSON.append("      \"menuChild:\"\"true\" \n");
			else
				menuJSON.append("      \"menuChild:\"\"false\" \n");
			if (list.indexOf(o) + 1 == list.size())
				menuJSON.append("   } \n");
			else
				menuJSON.append("   }, \n");
		}
		menuJSON.append("] \n ");
		System.out.println(menuJSON.toString());
		return StrutsUtil.renderJSON(menuJSON.toString());
	}

	/**
	 * 重置roleMenuRank为空串,然后可以当前用户权限进行设置
	 * 
	 * @return
	 * @throws Exception
	 */
	public String reset() throws Exception {
		if (roleId != null) {
			role = systemService.getRole(roleId);
			if ("0".equals(role.getRoleIsSystem())) {
				role.setRoleMenuRank("");
				role = systemService.saveRole(role);
			}
		}
		return list();
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQueryActive() {
		return queryActive;
	}

	public void setQueryActive(String queryActive) {
		this.queryActive = queryActive;
	}

}
