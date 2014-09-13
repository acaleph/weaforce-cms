package com.weaforce.cms.action.ads;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.ads.Order;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

/**
 * 团购订单管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
public class OrderAction extends AbstractCrudAction<Order> {

	private static final long serialVersionUID = -6257935048810927221L;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private Order order;
	private Long orderId;
	private Long dealId;
	private Long categoryId;

	private String queryAddress;
	private String dateFrom;
	private String dateTo;

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}

	
	protected void prepareModel() throws Exception {
		order = adsService.prepareOrder(order, orderId, dealId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		order = adsService.saveOrder(order, dealId);
		return list();
	}

	
	public String delete() throws Exception {
		adsService.deleteOrder(orderId);
		return list();
	}

	/**
	 * 栏目主键
	 * 
	 * @param categoryId
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	
	public String list() throws Exception {
		pageInfo = adsService.getOrderPage(pageInfo, categoryId, queryAddress,
				dateFrom, dateTo);
		return SUCCESS;
	}

	

	
	public Order getModel() {
		return order;
	}

	public String getQueryAddress() {
		return queryAddress;
	}

	public void setQueryAddress(String queryAddress) {
		this.queryAddress = queryAddress;
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
