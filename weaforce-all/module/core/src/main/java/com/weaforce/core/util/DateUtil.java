package com.weaforce.core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormatSymbols;

/**
 * <p>
 * Title: DateUtil
 * </p>
 * <p>
 * Description: 日期处理工具集
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author
 * @version 1.0
 */
public class DateUtil {
	public static final int CanNotGetDays = -1;

	/**
	 * W3C日期格式:2008-08-08
	 * 
	 * @param d
	 *            Date
	 * @return String
	 */
	public static String defaultFormat(Date d) {
		return format(d, "yyyy-MM-dd");
	}
	

	/**
	 * 根据用户定义的pattern,取得日期格式
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getTime(String pattern) {
		if (pattern == null || pattern.equals("")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date cday = new Date();
		String timestr = sdf.format(cday);
		return timestr;
	}

	/**
	 * 完整的W3C日期格式:"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param d
	 * @return
	 */
	public static String completeFormat(Date d) {
		return format(d, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获得UTC格式时间
	 * 
	 * @param s
	 *            String format
	 * @throws WebException
	 * @return Long
	 */
	public static Long getUTCDate(String s, String format) {
		return parse(s, format).getTime();
	}

	/**
	 * 根据标准W3C日期格式,取得UTC格式时间
	 * 
	 * @param s
	 *            日期格式: 2009-06-30
	 * @return
	 */
	public static Long getUTCDate(String s) {
		return defaultParse(s).getTime();
	}

	public static String format(Date d, String format) {
		if (d == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(d);
	}

	public static Date parse(String s, String format) {
		if (s == null || "".equals(s) || "null".equals(s)) {
			return null;
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.parse(s);
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 缺省日期转换
	 * 
	 * @param s
	 *            String
	 * @throws WebException
	 * @return Date
	 */
	public static Date defaultParse(String s) {
		return parse(s, "yyyy-MM-dd");
	}

	/**
	 * 得到日期与现在相距天数
	 * 
	 * @param df
	 *            Date
	 * @return int
	 */
	public static int getDays(Date df) {
		Date d = new Date();
		return (int) ((df.getTime() - d.getTime()) / (24 * 60 * 60 * 1000));

	}

	/**
	 * 得到两个日期间相隔天数
	 * 
	 * @param df1
	 *            Date
	 * @param df2
	 *            Date
	 * @return int
	 */
	public static int getDisDays(Date df1, Date df2) {
		if (df1 == null || df2 == null)
			return CanNotGetDays;
		else
			return (int) ((df1.getTime() - df2.getTime()) / (24 * 60 * 60 * 1000));
	}

	/**
	 * @param df1
	 *            Date
	 * @param df2
	 *            Date
	 * @return int
	 */
	public static String getStrDisDays(Date df1, Date df2) {
		if (df1 == null || df2 == null)
			return "";
		else
			return ""
					+ (int) ((df1.getTime() - df2.getTime()) / (24 * 60 * 60 * 1000));
	}

	/**
	 * 日期小于当前日期，返回true
	 * 
	 * @param df
	 *            Date
	 * @return boolean
	 */
	public static boolean isBeforeCurTime(Date df) {
		Date d = new Date();
		if (d.compareTo(df) > 0)
			return true;
		else
			return false;
	}

	/**
	 * 取得当前日期向前一个月日期
	 * 
	 * @return 2010-04-06
	 */
	@SuppressWarnings("static-access")
	public static String getMonthBeforeCur() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, -1);
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		return df.format(cal.getTime());
	}

	/**
	 * 得到当前日期的年份
	 * 
	 * @param df
	 *            Date
	 * @return int
	 */
	public static int getYear(Date df) {
		Calendar c = Calendar.getInstance();
		c.setTime(df);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 得到当前日期的月份
	 * 
	 * @param df
	 *            Date
	 * @return int
	 */
	public static int getMonth(Date df) {
		Calendar c = Calendar.getInstance();
		c.setTime(df);
		return c.get(Calendar.MONTH);
	}

	/**
	 * 取得"当前"月份第一天
	 * 
	 * @return 2009-05-01
	 */
	@SuppressWarnings("static-access")
	public static String getMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, -1);
		cal.set(cal.DATE, 1);
		cal.add(cal.DATE, -1);
		cal.add(cal.DATE, getMonthDateCount(cal));
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		return df.format(cal.getTime());
	}

	/**
	 * 取得"当前"月份最后一天
	 * 
	 * 
	 * @return 2009-05-31
	 */
	@SuppressWarnings("static-access")
	public static String getMonthLastDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, 1);
		cal.set(cal.DATE, 1);
		cal.add(cal.DATE, -1);
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		return df.format(cal.getTime());
	}

	/**
	 * 得到"当前"日期的日
	 * 
	 * @param df
	 *            Date
	 * @return int
	 */
	public static int getDay(Date df) {
		Calendar c = Calendar.getInstance();
		c.setTime(df);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到2004/04/01之类的字符串
	 * 
	 * @return String
	 */
	public static String getCurYearMonthFirstDay() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1) + "/01";
	}

	/**
	 * 构造诸出40401之类的序列号
	 * 
	 * @return int
	 */
	public static int getCurYearMonthSeq() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int seq;
		if ((seq = c.get(Calendar.YEAR) - 2000) < 0)
			seq = c.get(Calendar.YEAR) - 1000;
		return (seq * 100 + c.get(Calendar.MONTH) + 1) * 100;
	}

	public static Timestamp getTimestamp(Object obj) {
		Timestamp t = null;
		if (obj instanceof Date)
			t = new Timestamp(((Date) obj).getTime());
		else if (obj instanceof Timestamp)
			t = (Timestamp) obj;

		return t;
	}

	/**
	 * Get months
	 * 
	 * @author acaleph8@yahoo.com.cn
	 * @return String array.
	 */
	public static String[] getMonths() {
		return new DateFormatSymbols().getMonths();
	}

	/**
	 * Get the date string
	 * 
	 * @param date
	 * @return 2008-12-11 to 20081211
	 */
	public static String getDatePlainStr() {
		return format(new Date(), "yyyyMMdd");
	}

	/**
	 * 得到日历数组
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return JSON format
	 */
	public static int[][] getCalendarArray(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		// 月份第一天是星期几
		int firstDateInWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		int dateOfMonth = getMonthDateCount(cal);
		// 日历网格个数
		int base = dateOfMonth + firstDateInWeek;
		// 日期行数
		int row = base / 7;
		row += ((base % 7) > 0) ? 1 : 0;
		// 日历
		int[][] cals = new int[row][7];
		int iCol = firstDateInWeek, iRow = 0;
		for (int i = 1; i <= dateOfMonth; i++) {
			cals[iRow][iCol] = i;
			if (iCol == 6) {
				iCol = 0;
				iRow++;
			} else
				iCol++;
		}
		return cals;
	}

	/**
	 * 得到日历JSON
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return JSON format
	 */
	public static StringBuffer getCalendarJSON(int year, int month) {
		StringBuffer sb = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		// 月份第一天是星期几
		int firstDateInWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		int dateOfMonth = getMonthDateCount(cal);
		// 日历网格个数
		int base = dateOfMonth + firstDateInWeek;
		// 日期行数
		int row = base / 7;
		row += ((base % 7) > 0) ? 1 : 0;
		// 日历
		int[][] cals = new int[row][7];
		int iCol = firstDateInWeek, iRow = 0;
		for (int i = 1; i <= dateOfMonth; i++) {
			cals[iRow][iCol] = i;
			if (iCol == 6) {
				iCol = 0;
				iRow++;
			} else
				iCol++;
		}
		// 把数据转成JSON格式
		// System.out.println(cals.length);
		for (int i = 0; i < row; i++) {
			if (sb.length() == 0)
				sb.append("[{");
			else
				sb.append(",{");
			for (int j = 0; j < 7; j++) {
				switch (j) {
				case 0:
					if (cals[i][j] == 0)
						sb.append("\"sun\":\"*\"");
					else
						sb.append("\"sun\":\"" + cals[i][j] + "\"");
					break;
				case 1:
					if (cals[i][j] == 0)
						sb.append(",\"mon\":\"*\"");
					else
						sb.append(",\"mon\":\"" + cals[i][j] + "\"");
					break;
				case 2:
					if (cals[i][j] == 0)
						sb.append(",\"tue\":\"*\"");
					else
						sb.append(",\"tue\":\"" + cals[i][j] + "\"");
					break;
				case 3:
					if (cals[i][j] == 0)
						sb.append(",\"wed\":\"*\"");
					else
						sb.append(",\"wed\":\"" + cals[i][j] + "\"");
					break;
				case 4:
					if (cals[i][j] == 0)
						sb.append(",\"thu\":\"*\"");
					else
						sb.append(",\"thu\":\"" + cals[i][j] + "\"");
					break;
				case 5:
					if (cals[i][j] == 0)
						sb.append(",\"fri\":\"*\"");
					else
						sb.append(",\"fri\":\"" + cals[i][j] + "\"");
					break;
				case 6:
					if (cals[i][j] == 0)
						sb.append(",\"sat\":\"*\"");
					else
						sb.append(",\"sat\":\"" + cals[i][j] + "\"");
					break;
				}
			}
			sb.append("}");
		}
		sb.append("]");
		return sb;
	}

	/**
	 * 得到指定月份的天数
	 * 
	 * @param cal
	 *            日历
	 * @return
	 */
	private static int getMonthDateCount(Calendar cal) {
		Calendar cal2 = (Calendar) cal.clone();
		cal2.add(Calendar.MONTH, 1);
		cal2.set(Calendar.DAY_OF_MONTH, 1);
		cal2.add(Calendar.DAY_OF_MONTH, -1);
		return cal2.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 取得当前年字串
	 * @return
	 */
	public static String getCurrentYearStr(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return String.valueOf(year);
	}
	/**
	 * 取得当前年月字串
	 * @return
	 */
	public static String getCurrentYearMonthStr(){
		StringBuffer sb = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONDAY);
		sb.append(year);
		if (month > 9)
			sb.append(month);
		else{
			sb.append("0");
			sb.append(month);
		}
		return sb.toString();
	}
	/**
	 * 取得当前年斜线月字串
	 * @return
	 */
	public static String getCurrentYearObliqueMonthStr(){
		StringBuffer sb = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONDAY);
		sb.append(year);
		sb.append("/");
		if (month > 9)
			sb.append(month);
		else{
			sb.append("0");
			sb.append(month);
		}
		return sb.toString();
		
	}

	public static void main(String[] args) {
		getCalendarJSON(2009, 5);
		System.out.println(getMonthFirstDay());
		System.out.println(getCurrentYearStr());
		System.out.println(getCurrentYearMonthStr());
		System.out.println(getCurrentYearObliqueMonthStr());
	}
}
