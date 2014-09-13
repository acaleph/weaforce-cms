package com.weaforce.cms.action.ads;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.ads.Ads;
import com.weaforce.cms.entity.ads.AdsPay;
import com.weaforce.cms.entity.ads.BizCategory;
import com.weaforce.cms.entity.ads.BizChannel;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

/**
 * 广告商家付款管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
public class PayAction extends AbstractCrudAction<AdsPay> {
	private static final long serialVersionUID = -5284180891101402736L;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private AdsPay pay;
	private Long payId;
	private Long adsId;
	private BigDecimal payPayBefore = BigDecimal.ZERO;

	private Long channelId;
	private Long categoryId;
	private List<BizChannel> channelList;
	private List<BizCategory> categoryList;

	private String queryTitle;
	private String queryDateFrom;
	private String queryDateTo;

	public void setPayId(Long payId) {
		this.payId = payId;
	}

	
	protected void prepareModel() throws Exception {
		pay = adsService.preparePay(pay, payId, adsId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}
	/**
	 * 修改前数值
	 * @param payPayBefore
	 */
	public void setPayPayBefore(BigDecimal payPayBefore) {
		this.payPayBefore = payPayBefore;
	}

	
	public String save() throws Exception {
		pay = adsService.savePay(pay, adsId,payPayBefore);
		return list();
	}

	
	public String delete() throws Exception {
		adsService.deleteAds(adsId);
		return list();
	}

	
	public String list() throws Exception {
		channelList = adsService.getChannelListByCity(getAdmin().getUserCity()
				.getCityId());
		if (channelId == null)
			if (channelList.size() > 0)
				channelId = channelList.get(0).getChannelId();
		// 栏目
		categoryList = adsService.getCategoryListByChannel(channelId, false);
		if (categoryId == null)
			if (categoryList.size() > 0)
				categoryId = categoryList.get(0).getCategoryId();
		pageInfo = adsService.getPayPage(pageInfo, getAdmin().getAccount(),
				adsId, queryTitle, queryDateFrom, queryDateTo);
		return SUCCESS;
	}

	

	/**
	 * 频道 list
	 * 
	 * @return
	 */
	public List<BizChannel> getChannelList() {
		return channelList;
	}

	/**
	 * 栏目 list
	 * 
	 * @return
	 */
	public List<BizCategory> getCategoryList() {
		return categoryList;
	}

	/**
	 * 广告商家 list
	 * 
	 * @return
	 */
	public List<Ads> getAdsList() {
		return adsService.getAdsListByCategory(categoryId);
	}

	
	public AdsPay getModel() {
		return pay;
	}

	/**
	 * 保存修改前数值
	 * 
	 * @return
	 */
	public BigDecimal getPayPayBefore() {
		if (pay.getPayPay().compareTo(BigDecimal.ZERO) != 0)
			payPayBefore = pay.getPayPay();
		return payPayBefore;
	}

	public Long getAdsId() {
		return adsId;
	}

	public void setAdsId(Long adsId) {
		this.adsId = adsId;
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

	public String getQueryDateFrom() {
		return queryDateFrom;
	}

	public void setQueryDateFrom(String queryDateFrom) {
		this.queryDateFrom = queryDateFrom;
	}

	public String getQueryDateTo() {
		return queryDateTo;
	}

	public void setQueryDateTo(String queryDateTo) {
		this.queryDateTo = queryDateTo;
	}

}
