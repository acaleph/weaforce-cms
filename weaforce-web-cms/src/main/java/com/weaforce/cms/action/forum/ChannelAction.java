package com.weaforce.cms.action.forum;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Template;
import com.weaforce.cms.entity.forum.ForumChannel;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.cms.service.IForumService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.entity.area.City;

/**
 * 论坛频道管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/forum")
public class ChannelAction extends AbstractCrudAction<ForumChannel> {
	private static final long serialVersionUID = -2736675460755390065L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;
	@Autowired
	@Qualifier("forumService")
	private IForumService forumService;

	private ForumChannel channel;
	private Long channelId;
	private Long cityId;
	private Long templateId;

	private String queryName;

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	
	protected void prepareModel() throws Exception {
		channel = forumService.prepareChannel(channel, channelId, cityId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 所属城市
	 * 
	 * @param cityId
	 */
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	/**
	 * 模板
	 * 
	 * @param templateId
	 */
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	
	public String save() throws Exception {
		channel = forumService.saveChannel(channel, cityId, templateId);
		return list();
	}

	
	public String delete() throws Exception {
		forumService.deleteChannel(channelId);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = forumService.getChannelPage(pageInfo, cityId, queryName);
		return SUCCESS;
	}

	/**
	 * parse 频道
	 * 
	 * @return
	 * @throws Exception
	 */
	public String parse() throws Exception {
		String menu = adsService.getChannelCategoryDDM(Long.valueOf("2"));
		forumService.parseChannel(getAdmin().getAccount(), channelId, menu);
		return list();
	}



	public ForumChannel getModel() {
		return channel;
	}

	/**
	 * 取得城市 list
	 * 
	 * @return
	 */
	public List<City> getCityList() {
		return adsService.getCityListByProvince(Long.valueOf("1"));
	}

	/**
	 * 取得模板list
	 * 
	 * @return
	 */
	public List<Template> getTemplateList() {
		return cmsService.getTemplateList(getAdmin().getAccount(), "1",
				false);
	}

	public Long getCityId() {
		return cityId;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
