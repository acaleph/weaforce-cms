package com.weaforce.system.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.core.util.StringUtil;
import com.weaforce.system.dao.IMenuDao;
import com.weaforce.entity.app.Menu;

@Repository("menuDao")
public class MenuDao extends GenericDao<Menu, Long> implements IMenuDao {
	private static final String QUERY_MENU_BY_PARENT = "From Menu a Where a.menuParent.menuId = ? ";
	

	/**
	 * Get menu list by parent primary key
	 * 
	 * @param menuFid
	 *            Parent primary key
	 * @return
	 */
	public List<Menu> getMenuListByParent(Long menuFid) {
		return listQuery(QUERY_MENU_BY_PARENT
				+ " Order by a.menuParent.menuId,a.menuGroupOrder ", menuFid);
	}

	/**
	 * Get menu list by primary key rank
	 * 
	 * @param roleMenuRank
	 *            Primary key rank
	 * @param menuFid
	 *            Primary key
	 * @return
	 */
	public List<Menu> getMenuListByRankFid(String roleMenuRank, Long menuFid) {
		return listQuery(QUERY_MENU_BY_PARENT + " And a.menuId In ("
				+ roleMenuRank + ")"
				+ " Order by a.menuParent.menuId,a.menuGroupOrder ", menuFid);
	}

	/**
	 * Get menu list by rank string
	 * 
	 * @param roleMenuRank
	 *            Rank string
	 * @return
	 */
	public List<Menu> getMenuListByRank(String roleMenuRank) {
		return listQuery(" From Menu a Where a.menuId In (" + roleMenuRank
				+ ")");
	}

	/**
	 * Get menu JSON data by parent
	 * 
	 * @param parentId
	 *            Primary key
	 * @param lastIndex
	 *            Last index
	 * @param menuJSON
	 *            Menu JSON
	 * @param menuList
	 *            Menu list
	 * @return
	 */
	public StringBuffer getMenuChildrenJSONByParent(Long parentId,
			Integer lastIndex, StringBuffer sb, List<Menu> menuList) {
		Menu parentMenu = loadEntity(parentId);
		// Root node
		if (lastIndex == 0) {
			sb.append("[");
			menuList.add(parentMenu);
		}
		List<Menu> menuChildren = parentMenu.getMenuChild();
		if (menuChildren.size() > 0)
			menuList.addAll(lastIndex, menuChildren);
		// Root node should be excepted
		if (lastIndex < menuList.size() && lastIndex > 0) {
			Menu o = menuList.get(lastIndex);
			sb.append("{\"menuId\":\"" + o.getMenuId() + "\",\"menuName\":\""
					+ o.getMenuNameCn() + "\"},");

			lastIndex++;
			return getMenuChildrenJSONByParent(parentId, lastIndex, sb,
					menuList);
		} else {
			StringUtil.cutLastChar(sb.toString(), ",");
			sb.append("]");
			return sb;
		}
	}
}
