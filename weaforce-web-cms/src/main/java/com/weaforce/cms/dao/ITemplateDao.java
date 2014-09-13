package com.weaforce.cms.dao;

import java.util.List;

import com.weaforce.cms.entity.Template;
import com.weaforce.core.dao.IGenericDao;

public interface ITemplateDao extends IGenericDao<Template, Long> {
	/**
	 * 取得所有属于当前帐套的模板 list
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	public List<Template> getTemplateList(String account);

	/**
	 * 取得所有属于当前帐套活动的模板 list
	 * 
	 * @param account
	 *            帐套
	 * @param isActive
	 * @param flag
	 * @return
	 */
	public List<Template> getTemplateList(String account, String isActive,
			boolean flag);

	/**
	 * Get template list
	 * 
	 * @param account
	 *            Account
	 * @param isActive
	 *            If is active
	 * @param flag
	 *            If has the null option
	 * @param typeId
	 *            Type ID
	 * @return
	 */
	public List<Template> getTemplateList(String account, String isActive,
			boolean flag, String type);
}
