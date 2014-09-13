package com.weaforce.system.dao;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.entity.app.Menu;

public interface IMenuDao extends IGenericDao<Menu, Long> {
	/**
	 * Get menu list by parent primary key
	 * 
	 * @param menuFid
	 *            Parent primary key
	 * @return
	 */
	public List<Menu> getMenuListByParent(Long menuFid);

	/**
	 * Get menu list by primary key rank
	 * 
	 * @param roleMenuRank
	 *            Primary key rank
	 * @param menuFid
	 *            Primary key
	 * @return
	 */
	public List<Menu> getMenuListByRankFid(String roleMenuRank, Long menuFid);
	
	/**
	 * Get menu list by rank string
	 * 
	 * @param roleMenuRank
	 *            Rank string
	 * @return
	 */
	public List<Menu> getMenuListByRank(String roleMenuRank);

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
			Integer lastIndex, StringBuffer sb, List<Menu> menuList);

}
