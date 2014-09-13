package com.weaforce.cms.action.ads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.service.IAdsService;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 根据广告目录/标签/城区,检索广告
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public class QueryAdsAction extends ActionSupport {
	private static final long serialVersionUID = 3303344941374797092L;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private Long catalogId;
	private Long tagId;
	private Long zoneId;
	private Integer pageNumber;
	
	private String queryName;

	/**
	 * 
	 * @param catalogId
	 *            广告目录
	 */
	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	/**
	 * 
	 * @param tagId
	 *            标签
	 */
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	/**
	 * 
	 * @param zoneId
	 *            城区
	 */
	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	/**
	 * 当前页码
	 * 
	 * @param pageNumber
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String execute() throws Exception {
		return StrutsUtil.renderJSON(adsService.getCategoryAdsJSON(catalogId,
				tagId, zoneId,queryName, pageNumber));
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	
}
