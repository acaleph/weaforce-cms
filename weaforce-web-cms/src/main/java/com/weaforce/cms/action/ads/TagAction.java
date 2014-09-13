package com.weaforce.cms.action.ads;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.ads.BizCategory;
import com.weaforce.cms.entity.ads.BizChannel;
import com.weaforce.cms.entity.ads.Tag;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.entity.area.City;

/**
 * 广告标签管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
public class TagAction extends AbstractCrudAction<Tag> {
	private static final long serialVersionUID = 513045945577502142L;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private Tag tag;
	private Long tagId;
	private Long channelId;
	private Long categoryId;
	private Long cityId;

	private List<BizChannel> channelList;
	private List<City> cityList;

	private String queryName;

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	
	protected void prepareModel() throws Exception {
		 tag = adsService.prepareTag(tag,tagId,categoryId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		tag = adsService.saveTag(tag, categoryId);
		return list();
	}

	
	public String delete() throws Exception {
		adsService.deleteTag(tagId);
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
			pageInfo = adsService.getTagPage(pageInfo, categoryId, queryName);
		return SUCCESS;
	}

	

	public Tag getModel() {
		return tag;
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
