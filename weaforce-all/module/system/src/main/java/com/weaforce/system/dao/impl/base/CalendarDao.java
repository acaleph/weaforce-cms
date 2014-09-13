package com.weaforce.system.dao.impl.base;

import java.util.Calendar;

import org.springframework.stereotype.Repository;

import com.weaforce.core.dao.impl.GenericDao;
import com.weaforce.system.dao.base.ICalendarDao;

@Repository("calendarDao")
public class CalendarDao extends
		GenericDao<com.weaforce.system.entity.base.Calendar, Long> implements
		ICalendarDao {
	/**
	 * 根据年份月份获取时间日期
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            　月
	 * @return
	 */
	public String getCalendar(int year, byte month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int firstDateInWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		int dateOfMonth = getMonthDateCount(cal);
		int base = dateOfMonth + firstDateInWeek;
		int row = base / 7;
		row += ((base % 7) > 0) ? 1 : 0;
		int[][] cals = new int[row][7];
		int iCol = firstDateInWeek, iRow = 0;
		String calendarJson = "";
		for (int i = 0; i < iCol; i++)
			if ("".equals(calendarJson))
				calendarJson = "{'row':'" + iRow + "','col':'" + i
						+ "','value':''" + ",'isHoliday':'" + "'}";
			else
				calendarJson += ",{'row':'" + iRow + "'," + "'col':'" + i
						+ "'}";
		System.out.println(calendarJson);
		for (int i = 1; i <= dateOfMonth; i++) {
			cals[iRow][iCol] = i;
			if (iCol == 6) {
				iCol = 0;
				iRow++;
			} else {
				iCol++;
			}
		}
		return "";
	}

	public int getMonthDateCount(Calendar cal) {
		Calendar cal2 = (Calendar) cal.clone();
		cal2.add(Calendar.MONTH, 1);
		cal2.set(Calendar.DAY_OF_MONTH, 1);
		cal2.add(Calendar.DAY_OF_MONTH, -1);
		return cal2.get(Calendar.DAY_OF_MONTH);
	}
}
