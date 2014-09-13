package com.weaforce.cms.action.ads;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.ads.Deal;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

/**
 * 团购交易管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
public class DealAction extends AbstractCrudAction<Deal> {

	private static final long serialVersionUID = 5206901715319666327L;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private Deal deal;
	private Long dealId;

	private Long productId;

	private String dateFrom;
	private String dateTo;

	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	
	protected void prepareModel() throws Exception {
		deal = adsService.prepareDeal(deal, dealId, productId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		deal = adsService.saveDeal(deal, productId);
		return list();
	}

	
	public String delete() throws Exception {
		adsService.deleteDeal(dealId);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = adsService
				.getDealPage(pageInfo, productId, dateFrom, dateTo);
		return SUCCESS;
	}

	

	
	public Deal getModel() {
		return deal;
	}

	public Long getProductId() {
		return productId;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

}
