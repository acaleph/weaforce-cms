package com.weaforce.cms.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.ChannelMobile;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

public class ChannelMobileAction extends AbstractCrudAction<ChannelMobile> {

	private static final long serialVersionUID = -1247988147015880171L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	private ChannelMobile o;
	private Long channelId;

	private String queryName;

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	@Override
	protected void prepareModel() throws Exception {
		o = cmsService.prepareChannelMobile(o, channelId);
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		pageInfo = cmsService.getChannelMobilePage(pageInfo, getAdmin().getAccount(), queryName);
		return SUCCESS;
	}

	@Override
	public String save() throws Exception {
		o = cmsService.saveChannelMobile(o);
		return list();
	}

	@Override
	public String delete() throws Exception {
		return list();
	}

	@Override
	public ChannelMobile getModel() {
		return o;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
