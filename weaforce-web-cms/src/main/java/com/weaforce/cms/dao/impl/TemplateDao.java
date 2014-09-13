package com.weaforce.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.cms.dao.ITemplateDao;
import com.weaforce.cms.entity.Template;
import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.core.util.Global;

@Repository("templateDao")
public class TemplateDao extends GenericDao<Template, Long> implements
		ITemplateDao {
	private static final String QUERY_TEMPLATE_BY_ACTIVE = "From Template a WHERE a.account=? And a.templateIsActive=?  Order by templateName ";
	private static final String QUERY_TEMPLATE_BY_ACTIVE_TYPE = "From Template a WHERE a.account=? And a.templateIsActive=? And a.templateType=?  Order by templateName ";
	private static final String QUERY_TEMPLATE = " From Template a Where a.account=? ";

	/**
	 * 取得所有属于当前帐套的模板 list
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	public List<Template> getTemplateList(String account) {
		return listQuery(QUERY_TEMPLATE + " Order by templateName ", account);
	}

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
			boolean flag) {
		List<Template> templateList = listQuery(QUERY_TEMPLATE_BY_ACTIVE,
				account, isActive);
		if (flag) {
			Template o = new Template();
			o.setTemplateName(" ---none--- ");
			templateList.add(0, o);
		}
		return templateList;
	}

	public List<Template> getTemplateList(String account, String isActive,
			boolean flag, String type) {
		List<Template> templateList = listQuery(QUERY_TEMPLATE_BY_ACTIVE_TYPE,
				account, isActive, type);
		if (flag) {
			Template o = new Template();
			o.setTemplateName(Global.NONE_OPTION);
			templateList.add(0, o);
		}
		return templateList;
	}
}
