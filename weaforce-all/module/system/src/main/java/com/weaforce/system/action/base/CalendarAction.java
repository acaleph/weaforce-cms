package com.weaforce.system.action.base;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.entity.base.Calendar;
import com.weaforce.system.service.IBaseService;

@ParentPackage("default")
@Namespace("/system/base")
public class CalendarAction extends AbstractCrudAction<Calendar> {

	private static final long serialVersionUID = -388042070672631708L;
	@Autowired
	@Qualifier("baseService")
	private IBaseService baseService;

	private Calendar calendar;
	private Long calendarId;

	public void setCalendarId(Long calendarId) {
		this.calendarId = calendarId;
	}

	
	protected void prepareModel() throws Exception {
		if (calendarId == null)
			calendar = new Calendar();
		else
			calendar = baseService.getCalendar(calendarId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		calendar = baseService.saveCalendar(calendar);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = baseService.getCalendarPage(pageInfo, getAdmin()
				.getAccount());
		return SUCCESS;
	}


	
	public String delete() throws Exception {
		baseService.deleteCalendar(calendarId);
		return list();
	}

	public Calendar getModel() {
		return this.calendar;
	}

}
