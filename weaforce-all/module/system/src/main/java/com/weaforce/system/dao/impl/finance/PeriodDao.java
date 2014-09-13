package com.weaforce.system.dao.impl.finance;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.finance.IPeriodDao;
import com.weaforce.entity.finance.Period;

@Repository("periodDao")
public class PeriodDao extends GenericDao<Period, Long> implements IPeriodDao {
	private static final String QUERY_PERIOD_BY_YEAR = " From Period a Where a.account=? and a.periodYear=? Order by a.periodStart ";
	private static final String QUERY_PERIOD_BY_ACTIVE = " From Period a Where a.account=? and a.periodIsActive=? Order by a.periodStart ";
	private static final String QUERY_PERIOD_BY_START_END = " From Period a Where a.account=? And ? >= a.periodStart And ? <= a.periodEnd ";

	/**
	 * 取得年度账期
	 * 
	 * @param account
	 *            帐套
	 * @param periodYear
	 *            年度
	 * @return
	 */
	public List<Period> getPeriodListByYear(String account, Integer periodYear) {
		return listQuery(QUERY_PERIOD_BY_YEAR, account, periodYear);
	}

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
			Long periodEnd) {
		return loadEntity(QUERY_PERIOD_BY_START_END, account, periodStart,
				periodEnd);
	}

	/**
	 * 取得活动的账期
	 * 
	 * @param account
	 * @param isActive
	 * @return
	 */
	public List<Period> getPeriodListByActive(String account, String isActive) {
		return listQuery(QUERY_PERIOD_BY_ACTIVE, account, isActive);
	}

}
