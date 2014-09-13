package com.weaforce.system.action.help;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.entity.help.HelpTip;
import com.weaforce.system.service.IHelpService;

public class HelpTipAction extends AbstractCrudAction<HelpTip> {
	private static final long serialVersionUID = -949425954143437241L;
	@Autowired
	@Qualifier("helpService")
	private IHelpService helpService;

	private Long tipId;
	private HelpTip helpTip;

	private String helpTipQueryInfo;

	public void setTipId(Long tipId) {
		this.tipId = tipId;
	}

	
	protected void prepareModel() throws Exception {
		if (tipId == null)
			helpTip = new HelpTip();
		else
			helpTip = helpService.getHelpTip(tipId);
	}

	
	public String input() throws Exception {
		helpTip = helpService.getHelpTip(tipId);
		return INPUT;
	}

	
	public String list() throws Exception {
		return SUCCESS;
	}


	
	public String delete() throws Exception {
		return list();
	}

	
	public String save() throws Exception {
		return null;
	}

	public String getHelpTipQueryInfo() {
		return helpTipQueryInfo;
	}

	public void setHelpTipQueryInfo(String helpTipQueryInfo) {
		this.helpTipQueryInfo = helpTipQueryInfo;
	}

	public HelpTip getModel() {
		return helpTip;
	}

}
