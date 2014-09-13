package com.weaforce.cms.action.web;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.service.IAdsService;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * www.adniu.com Web请求管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
public class AdniuAction extends ActionSupport {
	private static final long serialVersionUID = -2486307377295995623L;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private Long adsId;

	private String phone;
	private String mobile;
	private String title;

	private Long categoryId;
	private Long zoneId;
	private Long tagId;
	private Integer pageNumber;
	private String queryName;
	private String queryTitle;

	public String execute() throws Exception {
		return SUCCESS;
	}

	// ------------------------------------------------------------------------
	/**
	 * 广告商家主键
	 * 
	 * @param adsId
	 */
	public void setAdsId(Long adsId) {
		this.adsId = adsId;
	}

	/**
	 * 以JSON格式,取得广告名称,同时保存点击率
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/cms/ads/ads-name")
	public String name() throws Exception {
		return StrutsUtil.renderJSON(adsService.getAdsName(adsId));
	}

	// ------------------------------------------------------------------------

	// ------------------------------------------------------------------------
	/**
	 * 电话
	 * 
	 * @param phone
	 * 
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 手机
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 标题
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 保存客户留言/电话/手机
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/cms/ads/message-contact")
	public void contact() throws Exception {
		adsService.saveMessage(adsId, phone, mobile, title);
	}

	// ------------------------------------------------------------------------
	// ------------------------------------------------------------------------
	/**
	 * 商家类别
	 * 
	 * @param categoryId
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 城区
	 * 
	 * @param zoneId
	 */
	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	/**
	 * 标签
	 * 
	 * @param tagId
	 */
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	/**
	 * 当前页码
	 * 
	 * @param pageNumber
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * 以HTML格式,取得栏目下所有广告
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/cms/ads/category-html")
	public String html() throws Exception {
		return StrutsUtil.renderHTML(adsService.getCategoryAdsHTML(categoryId,
				zoneId, tagId, queryName, pageNumber));
	}

	/**
	 * 以JSON格式,取得栏目下广告页信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/cms/ads/category-page")
	public String pageCategory() throws Exception {
		return StrutsUtil.renderJSON(adsService.getAdsPageJSON(categoryId,
				zoneId, tagId, queryName, pageNumber));
	}

	// ------------------------------------------------------------------------
	// ------------------------------------------------------------------------

	/**
	 * 以JSON格式,取得栏目打折 page
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/cms/ads/discount-page")
	public String pageDiscount() throws Exception {
		return StrutsUtil.renderJSON(adsService.getDiscountJSON(categoryId,
				queryTitle, pageNumber));
	}

	// ------------------------------------------------------------------------
	// ------------------------------------------------------------------------
	/**
	 * 以JSON格式,取得栏目小贴士 list
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/cms/ads/tip-page")
	public String pageTip() throws Exception {
		return StrutsUtil.renderJSON(adsService.getTipJSON(categoryId,
				queryTitle, pageNumber));
	}

	// ------------------------------------------------------------------------
	/**
	 * 标题
	 */
	public void setQueryTitle(String queryTitle) {
		this.queryTitle = queryTitle;
	}

}
