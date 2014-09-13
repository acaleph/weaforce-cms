package com.weaforce.system.dao.impl.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.entity.app.Module;
import com.weaforce.system.dao.base.IModuleDao;

@Repository("moduleDao")
public class ModuleDao extends GenericDao<Module, Long> implements IModuleDao {
	private static final String QUERY_MODULE_BY_DIC_ATT = "From Module a Where a.moduleDictionary=? and a.moduleAttachment=? Order By a.moduleNameCn";
	private static final String QUERY_MODULE_BY_BUSINESS_RESOURCE = "From Module a Where a.moduleBusiness.businessId=? And a.moduleIsResource=? Order By a.moduleNameCn ";
	private static final String QUERY_MODULE_BY_BUSINESS = "From Module a Where a.moduleBusiness.businessId=? Order By a.moduleNameCn ";

	/**
	 * 取得数据字典与附件组合列表
	 * 
	 * @param entityDic
	 *            数据字典
	 * @param entityAtt
	 *            附件
	 * @return
	 */
	public List<Module> getModuleList(String entityDic, String entityAtt) {
		return listQuery(QUERY_MODULE_BY_DIC_ATT, entityDic, entityAtt);
	}

	/**
	 * 根据资源标识取得Module list
	 * 
	 * @param isResource
	 * @return
	 */
	public List<Module> getModuleListByBusinessResource(Long businessId,
			String isResource) {
		return listQuery(QUERY_MODULE_BY_BUSINESS_RESOURCE, businessId,
				isResource);
	}

	/**
	 * 根据系统,取得模块list
	 * 
	 * @param businessId
	 *            系统
	 * @return
	 */
	public List<Module> getModuleListByBusiness(Long businessId) {
		return listQuery(QUERY_MODULE_BY_BUSINESS, businessId);
	}
}
