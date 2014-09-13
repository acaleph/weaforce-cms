package com.weaforce.system.action.logistics;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.entity.logistics.Producer;
import com.weaforce.system.service.ILogisticsService;

/**
 * 生产商管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/system/logistics")
public class ProducerAction extends AbstractCrudAction<Producer> {

	private static final long serialVersionUID = -4050877381664466636L;

	@Autowired
	@Qualifier("logisticsService")
	private ILogisticsService logisticsService;

	private Producer producer;
	private Long producerId;
	private Long accountId;

	private String queryCode;
	private String queryName;

	public void setProducerId(Long producerId) {
		this.producerId = producerId;
	}

	
	protected void prepareModel() throws Exception {
		producer = logisticsService.prepareProducer(getAdmin().getAccount(),
				producer, producerId, accountId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		producer = logisticsService.saveProducer(producer, accountId);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = logisticsService.getProducerPage(pageInfo, getAdmin()
				.getAccount(), queryCode, queryName);
		return SUCCESS;
	}

	
	public String delete() throws Exception {
		return list();
	}


	
	public Producer getModel() {
		return producer;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getQueryCode() {
		return queryCode;
	}

	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
