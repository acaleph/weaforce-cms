package com.weaforce.system.dao.finance;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.finance.Currency;

public interface ICurrencyDao extends IGenericDao<Currency, Long> {
	/**
	 * 取得活动的货币
	 * 
	 * @param account
	 *            帐套
	 * @param isActive
	 *            活动
	 * @return
	 */

	public List<Currency> getCurrencyListByActive( String isActive);

}
