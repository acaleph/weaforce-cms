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
 * 日历事件管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "base_calendar_event")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class CalendarEvent extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 5351327564261829124L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "41", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "EVENT_ID", length = 20)
	private Long eventId;
	// 时间
	@Column(name = "EVENT_TIME", length = 20)
	private Long eventTime;
	// 标题
	@Column(name = "EVENT_TITLE", length = 90)
	private String eventTitle;
	// 内容
	@Column(name = "EVENT_CONTENT", length = 255)
	private String eventContent;
	@Transient
	private String eventDate;

	public CalendarEvent() {

	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getEventTime() {
		return eventTime;
	}

	public void setEventTime(Long eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}

	/**
	 * 以W3C格式显示事件日期
	 * 
	 * @return
	 */
	public String getEventDate() {
		if (eventTime != null)
			eventDate = DateUtil.defaultFormat(new Date(eventTime));
		return eventDate;
	}

	/**
	 * 以UTC格式保存事件日期
	 * 
	 * @param eventDate
	 */
	public void setEventDate(String eventDate) {
		if (StringUtils.isNotEmpty(eventDate))
			eventTime = DateUtil.getUTCDate(eventDate);
		this.eventDate = eventDate;
	}

}
