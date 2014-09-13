package com.weaforce.system.dao.finance;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.finance.ExchangeRate;

public interface IExchangeRateDao extends IGenericDao<ExchangeRate, Long> {
	/**
	 * 取得当前有效的汇率
	 * 
	 * @param account
	 *            帐套
	 * @param originId
	 *            原货币
	 * @param targetId
	 *            目标货货
	 * @param isActive
	 *            有效
	 * @return
	 */
	public ExchangeRate getExchangeRateByOriginTargetActive(String account,
			Long originId, Long targetId, String isActive);

	public List<ExchangeRate> getExchangeRateList(String account,
			String rateIsActive);

	public List<ExchangeRate> getExchangeRateList(String account,
			Long originId, Long targetId);
}
