package com.weaforce.system.dao;

import com.weaforce.core.dao.IGenericDao;
import com.weaforce.system.entity.base.CalendarEvent;

public interface ICalendarEventDao extends IGenericDao<CalendarEvent, Long> {
	/**
	 * 取得当前用户某个日期的事件
	 * 
	 * @param uesrLogin
	 *            当前用户
	 * @param time
	 *            日期
	 * @return
	 */
	public CalendarEvent getEventByLoginTime(String userLogin, Long time);
}
