package com.weaforce.cms.action.ads;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.ads.AdsService;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.core.util.DateUtil;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;

/**
 * 联合广告目录类别管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
@Results( { @Result(name = "ads", location = "ads.action", params = { "adsId",
		"adsId" }, type = "redirect") })
public class AdsServiceAction extends AbstractCrudAction<AdsService> {
	private static final long serialVersionUID = 7099024191206870431L;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private AdsService service;
	private Long adsId;
	private Long serviceId;

	private Long albumId;

	private List<AdsService> serviceList;

	// 检索像册
	private String queryName;
	private String queryDateFrom;
	private String queryDateTo;

	/**
	 * 广告
	 * 
	 * @param adsId
	 */
	public void setAdsId(Long adsId) {
		this.adsId = adsId;
	}

	/**
	 * 广告目录类别
	 * 
	 * @param categoryId
	 */
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	
	protected void prepareModel() throws Exception {
		if (adsId != null && serviceId != null)
			service = adsService.getAdsService(adsId, serviceId);

	}

	
	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 保存后，返加广告界面
	 */
	
	public String save() throws Exception {
		service = adsService.saveAdsService(service, adsId, serviceId);
		return list();
	}

	
	public String delete() throws Exception {
		adsService.deleteAdsService(adsId, serviceId);
		return list();
	}

	
	public String list() throws Exception {
		serviceList = adsService.getAdsServiceListByAds(adsId);
		return SUCCESS;
	}

	public String parse() throws Exception {
		adsService.parseAdsServce(adsId, serviceId);
		return list();
	}


	/**
	 * 预处理广告商家服务
	 * 
	 * @throws Exception
	 */
	public void prepareAlbum() throws Exception {
		if (adsId != null && serviceId != null)
			service = adsService.getAdsService(adsId, serviceId);
	}

	/**
	 * 服务像册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String album() throws Exception {
		return "album";
	}

	/**
	 * 根据文章以JSON格式,取得像册 list
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAlbumListJSON() throws Exception {
		if (queryDateFrom == null || "".equals(queryDateFrom))
			queryDateFrom = DateUtil.getMonthBeforeCur();
		if (queryDateTo == null || "".equals(queryDateTo))
			queryDateTo = DateUtil.defaultFormat(new Date());
		return StrutsUtil.renderJSON(adsService.getAlbumListJSONByAdsService(getAdmin().getAccount(),
				adsId, serviceId, queryName, queryDateFrom, queryDateTo));
	}

	/**
	 * 像册主键
	 * 
	 * @param albumId
	 */

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	/**
	 * 保存像册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveAlbum() throws Exception {
		return StrutsUtil.renderJSON(adsService.saveAdsServiceAlbum(adsId, serviceId,
				albumId));
	}

	public AdsService getModel() {
		return service;
	}

	/**
	 * 取得广告目录类别 list
	 * 
	 * @return
	 */
	public List<AdsService> getServiceList() {
		return serviceList;
	}

	public Long getAdsId() {
		return adsId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
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
