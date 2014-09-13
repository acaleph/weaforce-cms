package com.weaforce.system.action.finance;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.entity.finance.Currency;
import com.weaforce.system.service.IFinanceService;

@ParentPackage("default")
@Namespace("/system/finance")
public class CurrencyAction extends AbstractCrudAction<Currency> {
	private static final long serialVersionUID = -5393300092061130828L;
	@Autowired
	@Qualifier("financeService")
	private IFinanceService financeService;

	private Currency currency;
	private Long currencyId;

	private String queryCurrencyCode;
	private String queryCurrencyName;

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	
	protected void prepareModel() throws Exception {
		if (this.currencyId == null)
			this.currency = new Currency();
		else
			this.currency = financeService.getCurrency(currencyId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		currency = financeService.saveCurrency(currency);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = financeService.getCurrencyPage(pageInfo, getAdmin()
				.getAccount(), queryCurrencyCode, queryCurrencyName);
		return SUCCESS;
	}


	/**
	 * 将货币设置为非活动
	 */
	
	public String delete() throws Exception {
		if (currencyId != null) {
			currency = financeService.getCurrency(currencyId);
			if ("1".equals(currency.getCurrencyIsActive())) {
				currency.setCurrencyIsActive("0");
				currency = financeService.saveCurrency(currency);
			}
		}
		return list();
	}

	public Currency getModel() {
		return currency;
	}

	public String getQueryCurrencyCode() {
		return queryCurrencyCode;
	}

	public void setQueryCurrencyCode(String queryCurrencyCode) {
		this.queryCurrencyCode = queryCurrencyCode;
	}

	public String getQueryCurrencyName() {
		return queryCurrencyName;
	}

	public void setQueryCurrencyName(String queryCurrencyName) {
		this.queryCurrencyName = queryCurrencyName;
	}

}
