package com.weaforce.system.dao.impl.finance;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.finance.ICurrencyDao;
import com.weaforce.system.entity.finance.Currency;

@Repository("currencyDao")
public class CurrencyDao extends GenericDao<Currency, Long> implements
		ICurrencyDao {
	private static final String QUERY_CURRENCY_BY_ACTIVE = " From Currency a Where a.currencyIsActive=? ";

	/**
	 * 取得活动的货币
	 * 
	 * @param account
	 *            帐套
	 * @param isActive
	 *            活动
	 * @return
	 */
	public List<Currency> getCurrencyListByActive(String isActive) {
		return listQuery(QUERY_CURRENCY_BY_ACTIVE, isActive);
	}

}
