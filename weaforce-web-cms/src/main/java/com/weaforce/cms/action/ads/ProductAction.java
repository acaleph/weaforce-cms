package com.weaforce.cms.action.ads;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Template;
import com.weaforce.cms.entity.ads.Ads;
import com.weaforce.cms.entity.ads.BizCategory;
import com.weaforce.cms.entity.ads.BizChannel;
import com.weaforce.cms.entity.ads.Product;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

/**
 * 广告商家团购产品管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
public class ProductAction extends AbstractCrudAction<Product> {

	private static final long serialVersionUID = 3385777158908853877L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private Product product;
	private Long productId;

	private Long adsId;
	private Long templateId;

	private Long channelId;
	private Long categoryId;
	private List<BizChannel> channelList;

	private String queryName;

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * 广告商家主键
	 * 
	 * @param adsId
	 */
	public void setAdsId(Long adsId) {
		this.adsId = adsId;
	}

	protected void prepareModel() throws Exception {
		product = adsService.prepareProduct(product, productId, adsId);
	}

	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 模板主键
	 * 
	 * @param templateId
	 */
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String save() throws Exception {
		product = adsService.saveProduct(product, adsId, templateId);
		return list();
	}

	public String delete() throws Exception {
		adsService.deleteProduct(productId);
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
			pageInfo = adsService.getProductPage(pageInfo, categoryId,
					queryName);
		return SUCCESS;
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
	 * 取得广告商家 list
	 * 
	 * @return
	 */
	public List<Ads> getAdsList() {
		return adsService.getAdsListByCategory(categoryId);
	}

	/**
	 * 取得模板 list
	 * 
	 * @return
	 */
	public List<Template> getTemplateList() {
		return cmsService.getTemplateList(getAdmin().getAccount(), "1", false);
	}

	public Product getModel() {
		return product;
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
