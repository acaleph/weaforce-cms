package com.weaforce.system.action.help;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.core.common.bean.Node;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.entity.help.Help;
import com.weaforce.system.service.IHelpService;

@ParentPackage("default")
@Namespace("/system/help")
public class HelpAction extends AbstractCrudAction<Help> {
	private static final long serialVersionUID = 5597681248788198440L;
	@Autowired
	@Qualifier("helpService")
	private IHelpService helpService;

	private Long helpId;
	private Help help;
	public List<Node> helpModule;

	private String helpQueryTitle;

	public void setHelpId(Long helpId) {
		this.helpId = helpId;
	}

	
	protected void prepareModel() throws Exception {
		if (helpId == null)
			help = new Help();
		else
			help = helpService.getHelp(helpId);
	}

	
	public String input() throws Exception {
		help = helpService.getHelp(helpId);
		return INPUT;
	}

	
	public String list() throws Exception {
		pageInfo = helpService.getHelpPage(pageInfo, getAdmin().getAccount());
		return SUCCESS;
	}

	public String delete() throws Exception {
		return list();
	}

	
	public String save() throws Exception {
		helpService.saveHelp(help);
		return list();
	}

	public Help getHelp() {
		return help;
	}

	public void setHelp(Help help) {
		this.help = help;
	}

	public List<Node> getHelpModule() {
		return helpModule;
	}

	public void setHelpModule(List<Node> helpModule) {
		this.helpModule = helpModule;
	}


	public String getHelpQueryTitle() {
		return helpQueryTitle;
	}

	public void setHelpQueryTitle(String helpQueryTitle) {
		this.helpQueryTitle = helpQueryTitle;
	}

	public Help getModel() {
		return null;
	}

}
