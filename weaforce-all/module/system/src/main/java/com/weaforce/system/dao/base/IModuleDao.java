package com.weaforce.system.dao.base;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.entity.app.Module;

public interface IModuleDao extends IGenericDao<Module, Long> {
	/**
	 * 取得数据字典与附件组合列表
	 * 
	 * @param entityDic
	 *            数据字典
	 * @param entityAtt
	 *            附件
	 * @return
	 */
	public List<Module> getModuleList(String entityDic, String entityAtt);

	/**
	 * 根据资源标识取得Module list
	 * 
	 * @param isResource
	 * @return
	 */
	public List<Module> getModuleListByBusinessResource(Long businessId,String isResource);

	/**
	 * 根据系统,取得模块list
	 * 
	 * @param businessId
	 *            系统
	 * @return
	 */
	public List<Module> getModuleListByBusiness(Long businessId);
}
