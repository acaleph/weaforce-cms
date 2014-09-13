package com.weaforce.cms.action.ads;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.ads.Ads;
import com.weaforce.cms.entity.ads.BizCategory;
import com.weaforce.cms.entity.ads.BizChannel;
import com.weaforce.cms.entity.ads.Message;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.core.util.StringUtil;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.entity.area.City;

/**
 * 短信管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
public class MessageAction extends AbstractCrudAction<Message> {
	private static final long serialVersionUID = 4832420872004715215L;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private Message message;
	private Long messageId;
	private Long channelId;
	private Long categoryId;
	private Long adsId;
	private Long cityId;

	private List<BizChannel> channelList;
	private List<BizCategory> categoryList;
	private List<City> cityList;

	private String queryTitle;
	private String queryMobile;
	private String isOk;
	private String queryDateFrom;
	private String queryDateTo;

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	
	protected void prepareModel() throws Exception {
		if (messageId == null)
			message = new Message();
		else
			message = adsService.getMessage(messageId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 保存消息:当电话和手机不同时为空时,执行保存
	 */
	
	public String save() throws Exception {
		if (StringUtil.isNotEmpty(message.getMessagePhone())
				|| StringUtil.isNotEmpty(message.getMessageMobile()))
			message = adsService.saveMessage(message, adsId);
		return list();
	}

	

	
	public String delete() throws Exception {
		adsService.deleteMessage(messageId);
		return list();
	}

	
	public String list() throws Exception {
		// 城市
		cityList = adsService.getCityListByProvince(Long.valueOf("1"));
		if (cityId == null)
			if (cityList.size() > 0)
				cityId = cityList.get(0).getCityId();
		// Ready for category
		channelList = adsService.getChannelListByCity(cityId);
		if (channelId == null)
			if (channelList.size() > 0)
				channelId = channelList.get(0).getChannelId();
		// Ready for ads
		if (channelId != null) {
			categoryList = adsService
					.getCategoryListByChannel(channelId, false);
			if (categoryId == null)
				if (categoryList.size() > 0)
					categoryId = categoryList.get(0).getCategoryId();
		}
		// 直接从帐套中取得广告商家
		adsId = adsService.getAccount(Long.valueOf(getAdmin().getAccount()))
				.getAccountAdsId();
		if (adsId != null)
			pageInfo = adsService.getMessagePage(pageInfo, adsId, queryTitle,
					queryMobile, isOk, queryDateFrom, queryDateTo);
		return SUCCESS;
	}

	/**
	 * 在lock()前执行二次绑定.
	 */
	public void prepareLock() throws Exception {
		prepareModel();
	}

	/**
	 * 确认消息处理完成，方便操作
	 */
	
	public String lock() throws Exception {
		adsService.lockMessage(messageId);
		return list();
	}

	public Message getModel() {
		return message;
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
		return categoryList;
	}

	/**
	 * 取得广告 list
	 * 
	 * @return
	 */
	public List<Ads> getAdsList() {
		return adsService.getAdsListByCategory(categoryId);
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

	public Long getAdsId() {
		return adsId;
	}

	public void setAdsId(Long adsId) {
		this.adsId = adsId;
	}

	public String getQueryTitle() {
		return queryTitle;
	}

	public void setQueryTitle(String queryTitle) {
		this.queryTitle = queryTitle;
	}

	public String getIsOk() {
		return isOk;
	}

	public void setIsOk(String isOk) {
		if (StringUtil.isEmpty(isOk))
			isOk = "0";
		this.isOk = isOk;
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

	public String getQueryMobile() {
		return queryMobile;
	}

	public void setQueryMobile(String queryMobile) {
		this.queryMobile = queryMobile;
	}

}
