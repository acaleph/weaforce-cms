package com.weaforce.cms.action.ads;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.CopyFrom;
import com.weaforce.cms.entity.ads.BizCategory;
import com.weaforce.cms.entity.ads.BizChannel;
import com.weaforce.cms.entity.ads.CategoryTip;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

/**
 * 小贴士管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
public class TipAction extends AbstractCrudAction<CategoryTip> {

	private static final long serialVersionUID = 3039627323996804814L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private CategoryTip tip;
	private Long tipId;
	private Long channelId;
	private Long categoryId;
	private Long fromId;

	private List<BizChannel> channelList;

	private String queryTitle;

	public void setTipId(Long tipId) {
		this.tipId = tipId;
	}

	
	protected void prepareModel() throws Exception {
		tip = adsService.prepareTip(tip, tipId, categoryId);
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
	 * 来源
	 * 
	 * @param fromId
	 */
	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}

	
	public String save() throws Exception {
		tip = adsService.saveTip(tip, categoryId, fromId);
		return list();
	}

	
	public String delete() throws Exception {
		adsService.deleteTip(tipId);
		return list();
	}

	
	public String list() throws Exception {
		// 所属城市的广告频道
		channelList = adsService.getChannelListByCity(getAdmin().getUserCity()
				.getCityId());
		if (channelId == null)
			if (channelList.size() > 0)
				channelId = channelList.get(0).getChannelId();
		if (categoryId != null)
			pageInfo = adsService.getTipPage(pageInfo, categoryId, queryTitle);
		return SUCCESS;
	}

	


	/**
	 * parse 小贴士
	 * 
	 * @return
	 * @throws Exception
	 */
	public String parse() throws Exception {
		adsService.parseTip(getAdmin().getAccount(), tipId);
		return list();
	}

	/**
	 * pares 小贴士 page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String parsePage() throws Exception {
		adsService.parseTipPage(getAdmin().getAccount(), categoryId);
		return list();
	}

	public CategoryTip getModel() {
		return tip;
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
	 * 取得广告栏目 list
	 * 
	 * @return
	 */
	public List<BizCategory> getCategoryList() {
		return adsService.getCategoryListByChannel(channelId, false);
	}

	/**
	 * 取得来源list
	 * 
	 * @return
	 */
	public List<CopyFrom> getFromList() {
		return cmsService.getCopyFromList(false);

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

	public String getQueryTitle() {
		return queryTitle;
	}

	public void setQueryTitle(String queryTitle) {
		this.queryTitle = queryTitle;
	}

}
