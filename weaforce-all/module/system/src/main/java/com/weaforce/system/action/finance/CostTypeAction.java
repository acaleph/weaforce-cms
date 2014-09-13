package com.weaforce.system.action.finance;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.entity.finance.CostType;
import com.weaforce.system.service.IFinanceService;

/**
 * 资金类型管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/system/finance")
public class CostTypeAction extends AbstractCrudAction<CostType> {
	private static final long serialVersionUID = 7654760732491007455L;
	@Autowired
	@Qualifier("financeService")
	private IFinanceService financeService;

	private CostType type;
	private Long typeId;

	private String queryTypeName;

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	protected void prepareModel() throws Exception {
		if (typeId == null)
			type = new CostType();
		else
			type = financeService.getType(typeId);
	}

	public String input() throws Exception {
		return INPUT;
	}

	public String save() throws Exception {
		type = financeService.saveType(type);
		return list();
	}

	public String list() throws Exception {
		pageInfo = financeService.getTypePage(pageInfo,
				getAdmin().getAccount(), queryTypeName);
		return SUCCESS;
	}

	public String lock() throws Exception {
		if (typeId != null) {
			type = financeService.getType(typeId);
			type = financeService.saveType(type);
		}
		return list();
	}

	public String delete() throws Exception {
		if (typeId != null) {
			type = financeService.getType(typeId);
			type.setTypeIsActive("0");
			type = financeService.saveType(type);
		}
		return list();
	}

	public CostType getModel() {
		return type;
	}

	public String getQueryTypeName() {
		return queryTypeName;
	}

	public void setQueryTypeName(String queryTypeName) {
		this.queryTypeName = queryTypeName;
	}

}
