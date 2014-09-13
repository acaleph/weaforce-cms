package com.weaforce.system.dao.organ;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.organ.Staff;

public interface IStaffDao extends IGenericDao<Staff, Long> {
	/**
	 * 取得部门成员列表
	 * 
	 * @param departmentId
	 *            部门ID
	 * @param isActive
	 *            是否有效
	 * @return
	 */
	public List<Staff> getStaffListByDepartmentActive(Long departmentId,
			String isActive);

	/**
	 * 取得关联职员 list
	 * 
	 * @param configStaffRank
	 * @param isActive
	 * @return
	 */
	public List<Staff> getStaffListByRankActive(String configStaffRank,
			String isActive);

	/**
	 * 取得职员 list
	 * 
	 * @param userLogin
	 *            当前用户登录ID
	 * @param isActive
	 *            活动
	 * @return
	 */
	public List<Staff> getStaffListByLoginActive(String userLogin,
			String isActive);

	/**
	 * 根据登录ID取得staff
	 * 
	 * @param account
	 *            帐套
	 * @param staffLogin
	 *            登录ID
	 * @return
	 */
	public Staff getStaffByLogin(String account, String staffLogin);

	/**
	 * 增加当前entity Id到参与人员config.config*Rank队列中
	 * 
	 * @param account
	 *            帐套
	 * @param configList
	 *            配置列表
	 * @param checkedStaffIds
	 *            新增staff list ids
	 * @param primaryKey
	 *            对象主键
	 * @param rankField
	 *            属性名称
	 * @return
	 */
	public List<Staff> addRank(String account, List<Staff> configList,
			List<Long> checkedStaffIds, Long primaryKey, String rankField);

	/**
	 * 从参与人员config.config*Rank队列中去除当前entity Id
	 * 
	 * @param account
	 *            帐套
	 * @param configList
	 *            参与人员
	 * @param checkedStaffIds
	 *            参与人员 ids
	 * @param primaryKey
	 *            对象主刍
	 * @param rankFiled
	 *            队列属性
	 * @param checkout
	 *            是否全移出
	 * @return
	 */
	public List<Staff> removeRank(String account, List<Staff> configList,
			List<Long> checkedStaffIds, String staffIds, Long primaryKey,
			String rankField, boolean checkout);

}
