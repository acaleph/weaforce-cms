package com.weaforce.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.ICalendarEventDao;
import com.weaforce.system.entity.base.CalendarEvent;

@Repository("calendarEventDao")
public class CalendarEventDao extends GenericDao<CalendarEvent, Long> implements
		ICalendarEventDao {
	private static final String QUERY_EVENT_BY_LOGIN_TIME = " From CalendarEvent a Where a.creator=? And a.eventTime=? ";
	/**
	 * 取得当前用户某个日期的事件
	 * 
	 * @param uesrLogin
	 *            当前用户
	 * @param time
	 *            日期
	 * @return
	 */
	public CalendarEvent getEventByLoginTime(String userLogin, Long time){
		return loadEntity(QUERY_EVENT_BY_LOGIN_TIME,userLogin,time);
	}
}
