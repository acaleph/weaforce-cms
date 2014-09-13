package com.weaforce.system.dao.finance;

import java.util.List;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.entity.finance.Period;

public interface IPeriodDao extends IGenericDao<Period, Long> {
	/**
	 * 取得年度账期
	 * 
	 * @param account
	 *            帐套
	 * @param periodYear
	 *            年度
	 * @return
	 */
	public List<Period> getPeriodListByYear(String account, Integer periodYear);

	/**
	 * 根据起始和结束时间确定账期
	 * 
	 * @param account
	 *            帐套
	 * @param periodStart
	 *            起始时间
	 * @param periodEnd
	 *            结束时间
	 * @return
	 */
	public Period getPeriodByStartEnd(String account, Long periodStart,
			Long periodEnd);

	/**
	 * 取得活动的账期
	 * 
	 * @param account
	 * @param isActive
	 * @return
	 */
	public List<Period> getPeriodListByActive(String account, String isActive);

}
