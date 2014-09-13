package com.weaforce.cms.action.ads;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.ads.AdsDiscount;
import com.weaforce.cms.entity.ads.BizCategory;
import com.weaforce.cms.entity.ads.BizChannel;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

/**
 * 广告打折管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
public class DiscountAction extends AbstractCrudAction<AdsDiscount> {
	private static final long serialVersionUID = 5993110613166313088L;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private AdsDiscount discount;
	private Long discountId;
	private Long channelId;
	private Long adsId;
	private Long categoryId;

	private List<BizChannel> channelList;

	private String queryTitle;
	private String queryName;

	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	
	protected void prepareModel() throws Exception {
		discount = adsService.prepareDiscount(discount, discountId, adsId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 广告
	 * 
	 * @param adsId
	 */
	public void setAdsId(Long adsId) {
		this.adsId = adsId;
	}

	/**
	 * 保存广告信息 adsId 广告 categoryId 广告信息类别
	 * 
	 */
	
	public String save() throws Exception {
		discount = adsService.saveDiscount(discount, adsId);
		return list();
	}

	
	public String delete() throws Exception {
		adsService.deleteDiscount(discountId);
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
			pageInfo = adsService.getDiscountPage(pageInfo, categoryId,
					queryTitle, queryName);
		return SUCCESS;
	}



	/**
	 * parse 打折信息
	 * 
	 * @throws Exception
	 */
	public String parse() throws Exception {
		adsService.parseDiscount(getAdmin().getAccount(), discountId);
		return list();
	}

	/**
	 * parse 打折信息 list
	 * 
	 * @return
	 * @throws Exception
	 */
	public String parsePage() throws Exception {
		adsService.parseDiscountPage(getAdmin().getAccount(), categoryId);
		return list();
	}

	public AdsDiscount getModel() {
		return discount;
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
	
	public Long getAdsId() {
		return adsId;
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

	public String getQueryTitle() {
		return queryTitle;
	}

	public void setQueryTitle(String queryTitle) {
		this.queryTitle = queryTitle;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
