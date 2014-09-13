package com.weaforce.system.dao.impl.organ;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.core.util.StringUtil;
import com.weaforce.system.dao.organ.IStaffDao;
import com.weaforce.system.entity.organ.Staff;

@Repository("staffDao")
public class StaffDao extends GenericDao<Staff, Long> implements IStaffDao {
	private static final String QUERY_STAFF_BY_ACTIVE = " From Staff a Where a.staffIsActive=? ";
	private static final String QUERY_STAFF_BY_DEPARTMENT = " From Staff a Where a.staffDepartment.departmentId=? And a.staffIsActive=? ";
	private static final String QUERY_STAFF_BY_LOGIN = " From Staff a Where a.account=? And a.staffLogin=? ";

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
			String isActive) {
		return listQuery(QUERY_STAFF_BY_DEPARTMENT, departmentId, isActive);
	}

	/**
	 * 取得关联职员 list
	 * 
	 * @param configStaffRank
	 * @param isActive
	 * @return
	 */
	public List<Staff> getStaffListByRankActive(String configStaffRank,
			String isActive) {
		if (configStaffRank != null && !"".equals(configStaffRank))
			return listQuery(QUERY_STAFF_BY_ACTIVE + " And a.staffId in ("
					+ configStaffRank + ") Order by staffLogin ", isActive);
		else
			return new ArrayList<Staff>();
	}

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
			String isActive) {
		Staff o = loadEntity("staffLogin", userLogin);
		if (o != null)
			return getStaffListByRankActive(o.getConfigStaffRank(), isActive);
		else
			return new ArrayList<Staff>();
	}

	/**
	 * 根据登录ID取得staff
	 * 
	 * @param account
	 *            帐套
	 * @param staffLogin
	 *            登录ID
	 * @return
	 */
	public Staff getStaffByLogin(String account, String staffLogin) {
		return loadEntity(QUERY_STAFF_BY_LOGIN, account, staffLogin);
	}

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
			List<Long> checkedStaffIds, Long primaryKey, String rankField) {
		if (checkedStaffIds.size() > 0) {
			String configRank = "";
			for (Long l : checkedStaffIds) {
				Staff config = getEntity(l);
				if (config != null) {
					configRank = getConfigRank(config, rankField, configRank);
					// 为验证会议队列中,number,是否存在,而将configInfoRank中的内容重构
					configRank = StringUtil
							.addClazzRank(configRank, primaryKey);
					config = setConfigRank(config, rankField, configRank);
					configList.add(config);
				}
			}
		}
		return configList;
	}

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
			String rankField, boolean checkout) {
		if (checkout)
			if (checkedStaffIds.size() > 0)
				for (Long l : checkedStaffIds)
					staffIds = StringUtil.removeClazzRank(staffIds, l);
		if (!"".equals(staffIds))
			staffIds = "," + staffIds + ",";
		while (staffIds.length() > 2) {
			staffIds = staffIds.substring(1, staffIds.length());
			String staffId = staffIds.substring(0, staffIds.indexOf(","));
			Staff config = getEntity(Long.valueOf(staffId));
			String configRank = "";
			configRank = getConfigRank(config, rankField, configRank);
			if (StringUtil.isNotEmpty(configRank))
				configRank = StringUtil.removeClazzRank(configRank, primaryKey);
			config = setConfigRank(config, rankField, configRank);
			// checkedStaffIds队列中一定不会有当前staffId,无需判断
			configList.add(config);
			staffIds = staffIds.substring(staffId.length(), staffIds.length());
		}
		return configList;
	}

	/**
	 *取得RANK，没有在Interface中出现，取代：ReflectionUtil.getFieldValue(config, rankFiled)
	 * 
	 * @param config
	 * @param rankField
	 * @param configRank
	 * @return
	 */
	public String getConfigRank(Staff config, String rankField,
			String configRank) {
		if ("configJobRank".equals(rankField))
			configRank = config.getConfigJobRank();
		else if ("configEngineeringRank".equals(rankField))
			configRank = config.getConfigEngineeringRank();
		else if ("configProjectRank".equals(rankField))
			configRank = config.getConfigProjectRank();
		else if ("configMissionRank".equals(rankField))
			configRank = config.getConfigMissionRank();
		else if ("configRoutineRank".equals(rankField))
			configRank = config.getConfigRoutineRank();
		else if ("configInfoRank".equals(rankField))
			configRank = config.getConfigInfoRank();
		return configRank;
	}

	/**
	 * 设置RANK，没有在Interface中出现，取代：
	 * <p>
	 * ReflectionUtil.setFieldValue(config, rankFiled, configRank);
	 * </p>
	 * 
	 * @param config
	 * @param rankField
	 * @param configRank
	 * @return
	 */
	public Staff setConfigRank(Staff config, String rankField, String configRank) {
		if ("configJobRank".equals(rankField))
			config.setConfigJobRank(configRank);
		else if ("configEngineeringRank".equals(rankField))
			config.setConfigEngineeringRank(configRank);
		else if ("configProjectRank".equals(rankField))
			config.setConfigProjectRank(configRank);
		else if ("configMissionRank".equals(rankField))
			config.setConfigMissionRank(configRank);
		else if ("configRoutineRank".equals(rankField))
			config.setConfigRoutineRank(configRank);
		else if ("configInfoRank".equals(rankField))
			config.setConfigInfoRank(configRank);
		return config;
	}

}
