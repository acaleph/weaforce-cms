package com.weaforce.cms.action.ads;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Template;
import com.weaforce.cms.entity.ads.BizCategory;
import com.weaforce.cms.entity.ads.BizChannel;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.entity.area.City;

/**
 * 广告栏目管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
public class CategoryAction extends AbstractCrudAction<BizCategory> {
	private static final long serialVersionUID = 1620624775620650683L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private BizCategory category;
	private Long categoryId;
	private Long channelId;
	private Long templateId;
	private Long templateAdsId;
	private Long templateAdsStarId;
	private Long templateSiteId;
	private Long templateTipArticleId;
	private Long templateDiscountArticleId;
	private Long templateTipListId;
	private Long templateDiscountListId;

	private String queryName;

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	
	protected void prepareModel() throws Exception {
		category = adsService.prepareCategory(category, categoryId, channelId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 广告频道
	 * 
	 * @param channelId
	 */
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	/**
	 * 栏目模板
	 * 
	 * @param templateId
	 */
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	/**
	 * 栏目广告单元模板
	 * 
	 * @param templateAdsId
	 */

	public void setTemplateAdsId(Long templateAdsId) {
		this.templateAdsId = templateAdsId;
	}

	/**
	 * 广告明星
	 * 
	 * @param templateAdsStarId
	 */
	public void setTemplateAdsStarId(Long templateAdsStarId) {
		this.templateAdsStarId = templateAdsStarId;
	}

	/**
	 * 商家网站
	 * 
	 * @param templateSiteId
	 */
	public void setTemplateSiteId(Long templateSiteId) {
		this.templateSiteId = templateSiteId;
	}

	/**
	 * 小贴士模板 article
	 * 
	 * @param templateTipArticleId
	 */
	public void setTemplateTipArticleId(Long templateTipArticleId) {
		this.templateTipArticleId = templateTipArticleId;
	}

	/**
	 * 小贴士模板 list
	 * 
	 * @param templateTipListId
	 */
	public void setTemplateTipListId(Long templateTipListId) {
		this.templateTipListId = templateTipListId;
	}

	/**
	 * 打折模板 article
	 * 
	 * @param templateDiscountArticleId
	 */
	public void setTemplateDiscountArticleId(Long templateDiscountArticleId) {
		this.templateDiscountArticleId = templateDiscountArticleId;
	}

	/**
	 * 打折模板 list
	 * 
	 * @param templateDiscountListId
	 */
	public void setTemplateDiscountListId(Long templateDiscountListId) {
		this.templateDiscountListId = templateDiscountListId;
	}

	/**
	 * 保存栏目
	 */
	
	public String save() throws Exception {
		category = adsService.saveCategory(category, channelId, templateId,
				templateAdsId, templateAdsStarId, templateSiteId,
				templateTipArticleId, templateTipListId,
				templateDiscountArticleId, templateDiscountListId);
		return list();
	}

	
	public String delete() throws Exception {
		adsService.deleteCategory(categoryId);
		return list();
	}

	
	public String list() throws Exception {
		if (channelId != null)
			pageInfo = adsService.getCategoryPage(pageInfo, channelId,
					queryName);
		return SUCCESS;
	}

	

	/**
	 * 按照categoryParsePath提供的内容parse为index.html:当前category应该为终极节点
	 * 
	 * @return
	 * @throws Exception
	 */
	public String parse() throws Exception {
		adsService.parseCategory(getAdmin().getAccount(),getAdmin().getUserCity().getCityId(), categoryId);
		return list();
	}

	/**
	 * 以JSON格式,取得栏目下所有广告
	 * 
	 * @return
	 * @throws Exception
	 */
	// public String categoryAdsJSON() throws Exception {
	// return renderJSON(adsService.getCategoryAdsJSON(categoryId, tagId,
	// zoneId, queryName, pageNumber));
	// }

	/**
	 * 根据频道，取得栏目 JSON
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getCategoryDDL() throws Exception {
		return StrutsUtil.renderJSON(adsService.getCategoryDDL(channelId));
	}

	public BizCategory getModel() {
		return category;
	}

	/**
	 * 取得模板 list
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Template> getTemplateList() throws Exception {
		return cmsService.getTemplateList(getAdmin().getAccount(), "1",
				false);
	}

	/**
	 * 取得频道 list
	 * 
	 * @return
	 */
	public List<BizChannel> getChannelList() {
		// 城市
		List<City> cityList = adsService.getCityListByProvince(Long
				.valueOf("1"));
		Long cityId = null;
		if (cityId == null)
			if (cityList.size() > 0)
				cityId = cityList.get(0).getCityId();
		return adsService.getChannelListByCity(cityId);
	}

	public Long getChannelId() {
		return channelId;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
