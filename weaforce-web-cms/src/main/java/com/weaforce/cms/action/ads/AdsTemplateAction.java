package com.weaforce.cms.action.ads;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Template;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.spring.Security;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 对模板中的有关ADS标签进行替换
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
public class AdsTemplateAction extends ActionSupport {

	private static final long serialVersionUID = 2420427779692645386L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private Long templateId;

	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * 模板
	 * 
	 * @param templateId
	 */
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	/**
	 * parse 模板中ADS相关的标签
	 * 
	 * @return
	 * @throws Exception
	 */
	public String parse() throws Exception {
		adsService.parseTemplateMenu(templateId, Long.valueOf("2"));
		adsService.parseAdsCell(templateId);
		adsService.parseDiscountCell(templateId);
		return SUCCESS;
	}

	/**
	 * 板模list
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Template> getTemplateList() throws Exception {
		if (Security.getUser() != null)
			return cmsService.getTemplateList(Security
					.getUser().getAccount(), "1", false);
		else
			return new ArrayList<Template>();
	}

}