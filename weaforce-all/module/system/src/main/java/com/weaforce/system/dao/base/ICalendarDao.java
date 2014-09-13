package com.weaforce.system.dao.base;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.base.Calendar;

public interface ICalendarDao extends IGenericDao<Calendar, Long> {
	/**
	 * 根据年份月份获取时间日期
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            　月
	 * @return
	 */
	public String getCalendar(int year, byte month);
}
