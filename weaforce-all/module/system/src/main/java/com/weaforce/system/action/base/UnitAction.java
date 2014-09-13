package com.weaforce.system.action.base;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.entity.base.Unit;
import com.weaforce.system.service.IBaseService;

/**
 * <h3>维护单位字典域</h3>
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/system/base")
public class UnitAction extends AbstractCrudAction<Unit> {
	private static final long serialVersionUID = -5513588235233658706L;
	@Autowired
	@Qualifier("baseService")
	private IBaseService baseService;

	private Unit unit;
	private Long unitId;

	private String queryUnitCode;
	private String queryName;

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	
	protected void prepareModel() throws Exception {
		unit = baseService.prepareUnit(unit, unitId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		unit = baseService.saveUnit(unit);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = baseService.getUnitPage(pageInfo, queryUnitCode, queryName);
		return SUCCESS;
	}


	
	public String delete() throws Exception {
		return list();
	}

	public String getQueryUnitCode() {
		return queryUnitCode;
	}

	public void setQueryUnitCode(String queryUnitCode) {
		this.queryUnitCode = queryUnitCode;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public Unit getModel() {
		return unit;
	}

}
