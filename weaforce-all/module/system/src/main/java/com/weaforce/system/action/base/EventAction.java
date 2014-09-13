package com.weaforce.system.action.base;

import java.util.Calendar;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.system.entity.base.CalendarEvent;
import com.weaforce.system.service.ISystemService;

@ParentPackage("default")
@Namespace("/system/base")
public class EventAction extends AbstractCrudAction<CalendarEvent> {
	private static final long serialVersionUID = 936048919753573343L;

	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	private CalendarEvent event;
	private Long eventTime;

	private int year;
	private int month;


	public void setEventTime(Long eventTime) {
		this.eventTime = eventTime;
	}

	
	protected void prepareModel() throws Exception {
			event = systemService.getEventByLoginTime(getAdmin().getUserLogin(),eventTime);
		if (event == null){
			event = new CalendarEvent();
			event.setEventTime(eventTime);
		}
			
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		event = systemService.saveEvent(event);
		return StrutsUtil.renderText("{成功保存备忘!}");
	}

	/**
	 * 页面显示年
	 * 
	 * @return
	 */
	public int getYear() {
		return year;
	}

	/**
	 * 目标年
	 * 
	 * @param year
	 */

	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * 页面显示月
	 * 
	 * @return
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * 目标月
	 * 
	 * @param month
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * 初始化年、月至页面
	 */
	
	public String list() throws Exception {
		if (year == 0 && month == 0) {
			Calendar cal = Calendar.getInstance();
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
		}
		return SUCCESS;
	}

	/**
	 * JSON格式日历
	 */
	public String calendar() throws Exception {
		return StrutsUtil.renderJSON(systemService.getCalendarEventJSON(getAdmin()
				.getUserLogin(), year, month).toString());
	}

	
	public String delete() throws Exception {
		return StrutsUtil.renderText("{成功删除备忘!}");
	}


	public CalendarEvent getModel() {
		return event;
	}

}
