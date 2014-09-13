package com.weaforce.cms.action.ads;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.ads.BizChannel;
import com.weaforce.cms.service.IAdsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.entity.area.City;

/**
 * 广告频道管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/ads")
public class ChannelAction extends AbstractCrudAction<BizChannel> {
	private static final long serialVersionUID = 4968890118736920264L;
	@Autowired
	@Qualifier("adsService")
	private IAdsService adsService;

	private BizChannel channel;
	private Long channelId;
	private Long cityId;

	private String queryName;

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	
	protected void prepareModel() throws Exception {
		channel = adsService.prepareChannel(channel, channelId, cityId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		channel = adsService.saveChannel(channel, cityId);
		return list();
	}

	
	public String delete() throws Exception {
		adsService.deleteChannel(channelId);
		return list();
	}

	
	public String list() throws Exception {
		if (cityId != null)
			pageInfo = adsService.getChannelPage(pageInfo, cityId, queryName);
		return SUCCESS;
	}

	

	/**
	 * parse 频道下的所有栏目
	 * 
	 * @return
	 * @throws Exception
	 */
	public String parse() throws Exception {
		if (channelId != null)
			adsService.parseChannel(getAdmin().getAccount(), channelId);
		return list();
	}

	public BizChannel getModel() {
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

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
