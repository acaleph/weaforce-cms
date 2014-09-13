package com.weaforce.system.dao.organ;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.organ.Department;

public interface IDepartmentDao extends IGenericDao<Department, Long> {
	/**
	 * 取得活动部门list
	 * 
	 * @param account
	 *            帐套
	 * @param isActive
	 *            活动
	 * @param flag
	 *            是否增加ALL选项
	 * @return
	 */
	public List<Department> getDepartmentListByActive(String account,
			String isActive, boolean flag);
}
