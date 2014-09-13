package com.weaforce.system.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.core.util.DateUtil;

/**
 * 工厂日历类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "base_calendar")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class Calendar extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 6099174203158504979L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "67", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "CALENDAR_ID", length = 20)
	private Long calendarId;
	// 日期
	@Column(name = "CALENDAR_DATE", length = 20)
	private Long calendarDate;
	// 标题
	@Column(name = "CALENDAR_TITLE", length = 90)
	private String calendarTitle;
	// 是否法定假日
	@Column(name = "CALENDAR_IS_HOLIDAY", length = 1)
	private String calendarIsHoliday;

	@Transient
	private String calendarDateDate;

	public Calendar() {
		calendarIsHoliday = "1";
	}

	public Long getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(Long calendarId) {
		this.calendarId = calendarId;
	}

	public Long getCalendarDate() {
		return calendarDate;
	}

	public void setCalendarDate(Long calendarDate) {
		this.calendarDate = calendarDate;
	}

	public String getCalendarTitle() {
		return calendarTitle;
	}

	public void setCalendarTitle(String calendarTitle) {
		this.calendarTitle = calendarTitle;
	}

	public String getCalendarIsHoliday() {
		return calendarIsHoliday;
	}

	public void setCalendarIsHoliday(String calendarIsHoliday) {
		this.calendarIsHoliday = calendarIsHoliday;
	}

	/**
	 * 以W3C格式显示公告日期
	 * 
	 * @return
	 */
	public String getCalendarDateDate() {
		if (calendarDate != null)
			calendarDateDate = DateUtil.defaultFormat(new Date(calendarDate));
		else
			calendarDateDate = DateUtil.defaultFormat(new Date(System
					.currentTimeMillis()));
		return calendarDateDate;
	}

	/**
	 * 以UTC格式保存公告日期
	 * 
	 * @param calendarDateDate
	 */
	public void setCalendarDateDate(String calendarDateDate) {
		if (StringUtils.isNotEmpty(calendarDateDate))
			calendarDate = DateUtil.getUTCDate(calendarDateDate);
		this.calendarDateDate = calendarDateDate;
	}
}
