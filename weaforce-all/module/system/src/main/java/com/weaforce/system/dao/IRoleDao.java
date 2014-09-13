package com.weaforce.system.dao;

import java.util.List;

import com.weaforce.core.common.bean.Node;
import com.weaforce.core.dao.IGenericDao;
import com.weaforce.entity.admin.Role;
import com.weaforce.entity.admin.User;

public interface IRoleDao extends IGenericDao<Role, Long> {
	public List<Node> getRoleMenuNode(Long roleId, User user);

	public List<Role> getOrganRoleList(Long organId);

	/**
	 * 取得有效的role list
	 * 
	 * @param account
	 *            帐套
	 * @param isActive
	 *            是否有效
	 * @return
	 */
	public List<Role> getRoleListByActive(String account, String isActive);
}
