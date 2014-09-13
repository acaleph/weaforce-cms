package com.weaforce.system.action.finance;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.entity.finance.Currency;
import com.weaforce.system.entity.finance.ExchangeRate;
import com.weaforce.system.service.IFinanceService;

@ParentPackage("default")
@Namespace("/system/finance")
public class ExchangeRateAction extends AbstractCrudAction<ExchangeRate> {
	private static final long serialVersionUID = -4385998448925021198L;
	@Autowired
	@Qualifier("financeService")
	private IFinanceService financeService;

	private Long rateId;
	private ExchangeRate exchangeRate;

	private List<Currency> currencyList;

	private Long rateOriginId;
	private Long rateTargetId;

	public void setRateId(Long rateId) {
		this.rateId = rateId;
	}

	
	protected void prepareModel() throws Exception {
		if (this.rateId == null)
			this.exchangeRate = new ExchangeRate();
		else
			this.exchangeRate = financeService.getExchangeRate(rateId);
	}

	
	public String input() throws Exception {
		currencyList = financeService.getCurrencyListByActive("1");
		return INPUT;
	}

	
	public String save() throws Exception {
		if (rateOriginId != null)
			exchangeRate.setRateOriginCurrency(financeService
					.getCurrency(rateOriginId));
		if (rateTargetId != null)
			exchangeRate.setRateTargetCurrency(financeService
					.getCurrency(rateTargetId));
		if ("1".equals(exchangeRate.getRateIsActive())) {
			List<ExchangeRate> rateList = financeService.getExchangeRateList(
					getAdmin().getAccount(), rateOriginId, rateTargetId);
			if (!rateList.contains(exchangeRate))
				rateList.add(exchangeRate);
			for (ExchangeRate o : rateList)
				if (!o.equals(exchangeRate) && "1".equals(o.getRateIsActive()))
					o.setRateIsActive("0");

			financeService.saveExchangeRate(rateList);
		} else {
			exchangeRate = financeService.saveExchangeRate(exchangeRate);
		}
		return list();
	}

	
	public String list() throws Exception {
		currencyList = financeService.getCurrencyListByActive("1");
		if (currencyList.size() > 0) {
			if (rateOriginId == null)
				rateOriginId = currencyList.get(0).getCurrencyId();
			if (rateTargetId == null)
				rateTargetId = currencyList.get(0).getCurrencyId();
		}
		pageInfo = financeService.getExchangeRatePage(pageInfo, getAdmin()
				.getAccount(), rateOriginId, rateTargetId);
		return SUCCESS;
	}

	
	public String lock() throws Exception {
		financeService.saveExchangeRate(exchangeRate);
		return list();
	}

	/**
	 * 将汇率设置为非活动
	 */
	
	public String delete() throws Exception {
		if (rateId != null) {
			exchangeRate = financeService.getExchangeRate(rateId);
			if ("1".equals(exchangeRate.getRateIsActive())) {
				exchangeRate.setRateIsActive("0");
				financeService.saveExchangeRate(exchangeRate);
			}
		}
		return list();
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<Currency> currencyList) {
		this.currencyList = currencyList;
	}

	public Long getRateOriginId() {
		return rateOriginId;
	}

	public void setRateOriginId(Long rateOriginId) {
		this.rateOriginId = rateOriginId;
	}

	public Long getRateTargetId() {
		return rateTargetId;
	}

	public void setRateTargetId(Long rateTargetId) {
		this.rateTargetId = rateTargetId;
	}

	public ExchangeRate getModel() {
		return this.exchangeRate;
	}

}
