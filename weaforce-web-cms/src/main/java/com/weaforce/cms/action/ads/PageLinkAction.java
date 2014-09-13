package com.weaforce.cms.action.ads;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Template;
import com.weaforce.cms.entity.ads.AdsPageLink;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;

/**
 * 广告自定义页面管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
public class PageLinkAction extends AbstractCrudAction<AdsPageLink> {
	private static final long serialVersionUID = -1873473532078690711L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private AdsPageLink link;
	private Long linkId;
	private Long parentId;
	private Long templateId;

	private Long channelId;
	private Long categoryId;
	private Long adsId;

	private List<AdsPageLink> linkList;

	private String queryTitle;

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	protected void prepareModel() throws Exception {
		if (linkId == null)
			link = new AdsPageLink();
		else
			link = adsService.getLink(linkId);
	}

	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 模板
	 * 
	 * @param templateId
	 *            主键
	 */
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String save() throws Exception {
		link = adsService.saveLink(link, parentId, channelId, categoryId,
				adsId, templateId);
		return list();
	}

	public String delete() throws Exception {
		adsService.deleteLink(linkId);
		return list();
	}

	public String list() throws Exception {
		pageInfo = adsService.getLinkPage(pageInfo, queryTitle);
		return SUCCESS;
	}

	/**
	 * 频道
	 * 
	 * @param channelId
	 */
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	/**
	 * 频道链接
	 * 
	 * @return
	 * @throws Exception
	 */
	public String channel() throws Exception {
		linkList = adsService.getLinkListByLevel(Byte.valueOf("0"), null, null);
		return "channel";
	}

	/**
	 * 栏目
	 * 
	 * @param categoryId
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 栏目链接
	 * 
	 * @return
	 * @throws Exception
	 */
	public String category() throws Exception {
		linkList = adsService.getLinkListByLevel(Byte.valueOf("1"), categoryId,
				null);
		return "category";
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
	 * 广告链接
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ads() throws Exception {
		linkList = adsService
				.getLinkListByLevel(Byte.valueOf("2"), null, adsId);
		return "ads";
	}

	/**
	 * 根结点
	 * 
	 * @param parentId
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 页面树
	 * 
	 * @return
	 * @throws Exception
	 */
	public String tree() throws Exception {
		return StrutsUtil.renderJSON(adsService.getLinkTree(parentId));
	}

	/**
	 * pares 首页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String parse() throws Exception {
		adsService.parseLink(getAdmin().getAccount(), getAdmin().getUserCity()
				.getCityNameCn(), linkId);
		return list();
	}

	public AdsPageLink getModel() {
		return link;
	}

	public List<AdsPageLink> getLinkList() {
		return linkList;
	}

	/**
	 * 模板
	 * 
	 * @return
	 */
	public List<Template> getTemplateList() {
		return cmsService.getTemplateList(getAdmin().getAccount(), "1", false);
	}

	public String getQueryTitle() {
		return queryTitle;
	}

	public void setQueryTitle(String queryTitle) {
		this.queryTitle = queryTitle;
	}

	public Long getChannelId() {
		return channelId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public Long getAdsId() {
		return adsId;
	}

}
