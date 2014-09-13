package com.weaforce.system.dao.impl.finance;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.finance.IExchangeRateDao;
import com.weaforce.system.entity.finance.ExchangeRate;

@Repository("exchangeRateDao")
public class ExchangeDao extends GenericDao<ExchangeRate, Long> implements
		IExchangeRateDao {
	private static final String QUERY_CURRENCYRATE_BY_ACTIVE = " From ExchangeRate a Where a.account = ? And a.rateIsActive=? ";
	private static final String QUERY_CURRENCYRATE_BY_ORIGIN_TARGET = " From ExchangeRate a Where a.account = ? And a.rateOriginCurrency.currencyId = ? And a.rateTargetCurrency.currencyId =? ";
	private static final String QUERY_CURRENCYRATE_BY_ORIGIN_TARGET_ACTIVE = " From ExchangeRate a Where a.account = ? And a.rateOriginCurrency.currencyId = ? And a.rateTargetCurrency.currencyId =? And a.rateIsActive=? ";

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
			Long originId, Long targetId, String isActive) {
		return loadEntity(QUERY_CURRENCYRATE_BY_ORIGIN_TARGET_ACTIVE, account,
				originId, targetId, isActive);
	}

	public List<ExchangeRate> getExchangeRateList(String account,
			String rateIsActive) {
		return listQuery(QUERY_CURRENCYRATE_BY_ACTIVE, account, rateIsActive);

	}

	public List<ExchangeRate> getExchangeRateList(String account,
			Long originId, Long targetId) {
		return listQuery(QUERY_CURRENCYRATE_BY_ORIGIN_TARGET, account,
				originId, targetId);
	}
}
