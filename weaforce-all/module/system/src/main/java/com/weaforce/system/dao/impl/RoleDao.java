package com.weaforce.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.weaforce.core.common.bean.Node;
import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.IRoleDao;
import com.weaforce.entity.admin.Role;
import com.weaforce.entity.admin.User;

@Repository("roleDao")
public class RoleDao extends GenericDao<Role, Long> implements IRoleDao {
	private static final String QUERY_ROLE_BY_ORGAN = "From Role a Where a.roleOrgan.organId = ?";
	private static final String QUERY_ROLE_BY_ACTIVE = " From Role a Where a.account=? And a.roleIsActive =? ";

	/**
	 * 取得有效的role list
	 * 
	 * @param account
	 *            帐套
	 * @param isActive
	 *            是否有效
	 * @return
	 */
	public List<Role> getRoleListByActive(String account, String isActive) {
		return listQuery(QUERY_ROLE_BY_ACTIVE, account, isActive);
	}

	public List<Node> getRoleMenuNode(Long organId, User user) {
		List<Node> nodes = new ArrayList<Node>();
		String roleMenuRank = loadEntity(organId).getRoleMenuRank();
		Map<String, String> mp = new HashMap<String, String>();
		String[] ids;
		if (roleMenuRank != null) {
			ids = roleMenuRank.split(",");
			for (int i = 0; i < ids.length; i++) {
				mp.put(ids[i], ids[i]);
			}
		}
		/*
		 * 根据当前修改的角色roleType取得菜单树 roleType = 0 系统树 roleType = 1 应用树 提取父角色权限树数据项
		 */

		return nodes;
	}

	public List<Role> getOrganRoleList(Long organId) {
		return listQuery(QUERY_ROLE_BY_ORGAN, organId);
	}
}
