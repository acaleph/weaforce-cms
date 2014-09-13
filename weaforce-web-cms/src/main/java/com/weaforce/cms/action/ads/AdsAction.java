package com.weaforce.cms.action.ads;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.ads.Ads;
import com.weaforce.cms.entity.ads.BizCategory;
import com.weaforce.cms.entity.ads.BizCategoryService;
import com.weaforce.cms.entity.ads.BizChannel;
import com.weaforce.cms.entity.ads.Tag;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.entity.area.City;
import com.weaforce.entity.area.Zone;

/**
 * 广告商家管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
public class AdsAction extends AbstractCrudAction<Ads> {
	private static final long serialVersionUID = -7429558724860358669L;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private Ads ads;
	private Long adsId;
	private Long channelId;
	private Long categoryId;
	private Long cityId;
	private Long zoneId;
	private Long tagId;

	private Long[] styleId;
	private String[] styleItem;

	private List<BizChannel> channelList;
	private List<BizCategory> categoryList;
	private List<Zone> zoneList;

	private List<Long> checkedServiceIds = new ArrayList<Long>();

	private String queryName;

	/**
	 * 广告主键
	 * 
	 * @param adsId
	 */
	public void setAdsId(Long adsId) {
		this.adsId = adsId;
	}

	
	protected void prepareModel() throws Exception {
		ads = adsService.prepareAds(ads, adsId, zoneId, categoryId);
	}

	
	public String input() throws Exception {
		// 栏目
		categoryList = adsService.getCategoryListByChannel(channelId, false);
		// 城区/镇
		zoneList = adsService.getZoneListByCity(cityId);
		// 服务
		checkedServiceIds = ads.getAdsServiceIds();
		return INPUT;
	}

	/**
	 * 广告风格
	 * 
	 * @param styleId
	 *            主键
	 */
	public void setStyleId(Long[] styleId) {
		this.styleId = styleId;
	}

	/**
	 * 广告风格
	 * 
	 * @param styleItem
	 *            项目
	 */

	public void setStyleItem(String[] styleItem) {
		this.styleItem = styleItem;
	}

	
	public String save() throws Exception {
		// adsService.getCategoryAdsJSON(categoryId, tagId, zoneId);
		ads = adsService.saveAds(ads, categoryId, zoneId, tagId,
				checkedServiceIds, styleId, styleItem);
		return list();
	}

	
	public String delete() throws Exception {
		adsService.deleteAds(adsId);
		return list();
	}

	
	public String list() throws Exception {
		// 城市
		if (cityId == null)
			cityId = getAdmin().getUserCity().getCityId();
		// 城区/镇
		zoneList = adsService.getZoneListByCity(cityId);
		if (zoneId == null)
			if (zoneList.size() > 0)
				zoneId = zoneList.get(0).getZoneId();
		// 频道
		channelList = adsService.getChannelListByCity(cityId);
		if (channelId == null)
			if (channelList.size() > 0)
				channelId = channelList.get(0).getChannelId();
		// 栏目
		categoryList = adsService.getCategoryListByChannel(channelId, false);
		if (categoryId == null)
			if (categoryList.size() > 0)
				categoryId = categoryList.get(0).getCategoryId();
		if (categoryId != null && zoneId != null)
			pageInfo = adsService.getAdsPage(pageInfo, categoryId, zoneId,
					queryName);
		return SUCCESS;
	}


	/**
	 * parse 广告
	 * 
	 * @return
	 * @throws Exception
	 */
	public String parse() throws Exception {
		if (adsId != null)
			adsService.parseAds(adsId);
		return list();
	}

	/**
	 * parse 栏目下所有的广告商家及服务为HTML
	 * 
	 * @return
	 * @throws Exception
	 */
	public String parseAll() throws Exception {
		if (categoryId != null) {
			adsService.parseAdsAll(categoryId);
		}
		return list();
	}

	public Ads getModel() {
		return ads;
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
		return categoryList;
	}

	/**
	 * 根据栏目，取得广告 JSON
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAdsDDL() throws Exception {
		return StrutsUtil.renderJSON(adsService.getAdsDDL(categoryId));
	}

	/**
	 * 取得城市 list
	 * 
	 * @return
	 */
	public List<City> getCityList() {
		return adsService.getCityListByProvince(Long.valueOf("1"));
	}

	/**
	 * 取得区 list
	 * 
	 * @return
	 */
	public List<Zone> getZoneList() {
		return zoneList;
	}

	/**
	 * 取得标签 list
	 * 
	 * @return
	 */
	public List<Tag> getTagList() {
		return adsService.getTagListByCategory(categoryId);
	}

	/**
	 * 取得广告栏目服务 list
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BizCategoryService> getServiceList() throws Exception {
		return ads.getAdsCategory().getCategoryService();
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

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getZoneId() {
		return zoneId;
	}

	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public List<Long> getCheckedServiceIds() {
		return checkedServiceIds;
	}

	public void setCheckedServiceIds(List<Long> checkedServiceIds) {
		this.checkedServiceIds = checkedServiceIds;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
