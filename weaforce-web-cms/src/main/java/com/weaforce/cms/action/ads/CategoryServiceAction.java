package com.weaforce.cms.action.ads;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Template;
import com.weaforce.cms.entity.ads.BizCategory;
import com.weaforce.cms.entity.ads.BizCategoryService;
import com.weaforce.cms.entity.ads.BizChannel;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.entity.area.City;

/**
 * 广告栏目服务管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
public class CategoryServiceAction extends
		AbstractCrudAction<BizCategoryService> {
	private static final long serialVersionUID = -4394425219893762237L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private BizCategoryService service;
	private Long serviceId;
	private Long channelId;
	private Long categoryId;
	private Long templateId;
	private Long cityId;

	private List<BizChannel> channelList;
	private List<City> cityList;

	private String queryName;

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	protected void prepareModel() throws Exception {
		service = adsService.prepareService(service, serviceId, categoryId);
	}

	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 广告栏目
	 * 
	 * @param categoryId
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 栏目模板
	 * 
	 * @param templateId
	 */
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String save() throws Exception {
		service = adsService.saveService(service, categoryId, templateId);
		return list();
	}

	public String delete() throws Exception {
		adsService.deleteService(categoryId);
		return list();
	}

	public String list() throws Exception {
		// 城市
		cityList = adsService.getCityListByProvince(Long.valueOf("1"));
		if (cityId == null)
			if (cityList.size() > 0)
				cityId = cityList.get(0).getCityId();
		channelList = adsService.getChannelListByCity(cityId);
		if (channelId == null)
			if (channelList.size() > 0)
				channelId = channelList.get(0).getChannelId();
		if (categoryId != null)
			pageInfo = adsService.getServicePage(pageInfo, categoryId,
					queryName);
		return SUCCESS;
	}

	public BizCategoryService getModel() {
		return service;
	}

	/**
	 * 广告频道 list
	 * 
	 * @return
	 */
	public List<BizChannel> getChannelList() {
		return channelList;
	}

	/**
	 * 广告栏目 list
	 * 
	 * @return
	 */
	public List<BizCategory> getCategoryList() {
		return adsService.getCategoryListByChannel(channelId, false);
	}

	/**
	 * 取得模板 list
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Template> getTemplateList() throws Exception {
		return cmsService.getTemplateList(getAdmin().getAccount(), "1", true);
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
