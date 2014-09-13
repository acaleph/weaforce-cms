package com.weaforce.cms.action.ads;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.ads.BizCategory;
import com.weaforce.cms.entity.ads.BizChannel;
import com.weaforce.cms.entity.ads.CategorySite;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.entity.area.City;

/**
 * 相关网站管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
public class SiteAction extends AbstractCrudAction<CategorySite> {
	private static final long serialVersionUID = -8760026016375313466L;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private CategorySite site;
	private Long siteId;

	private Long channelId;
	private Long categoryId;
	private Long cityId;

	private List<BizChannel> channelList;
	private List<City> cityList;

	private String queryName;

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	
	protected void prepareModel() throws Exception {
		site = adsService.prepareSite(site, siteId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		site = adsService.saveSite(site, categoryId);
		return list();
	}

	
	public String delete() throws Exception {
		adsService.deleteSite(siteId);
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
			pageInfo = adsService.getSitePage(pageInfo, categoryId, queryName);
		return SUCCESS;
	}

	

	public CategorySite getModel() {
		return site;
	}

	/**
	 * 取得频道 list
	 * 
	 * @return
	 */
	public List<BizChannel> getChannelList() {
		return channelList;
	}

	/**
	 * 取得栏目 list
	 * 
	 * @return
	 */
	public List<BizCategory> getCategoryList() {
		return adsService.getCategoryListByChannel(channelId, false);
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

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
