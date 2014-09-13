package com.weaforce.system.dao.impl.organ;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.organ.IDepartmentDao;
import com.weaforce.system.entity.organ.Department;
@Repository("departmentDao")
public class DepartmentDao extends GenericDao<Department, Long> implements
		IDepartmentDao {
	private static final String QUERY_DEPARTMENT_BY_ACTIVE=" From Department a Where a.account=? And a.departmentIsActive = ? ";
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
			String isActive, boolean flag) {
		List<Department> departmentList = listQuery(QUERY_DEPARTMENT_BY_ACTIVE,account,isActive);
		if (flag){
			Department o = new Department();
			o.setDepartmentName("--- ALL ---");
			departmentList.add(0, o);
		}
		return departmentList;
	}

}
