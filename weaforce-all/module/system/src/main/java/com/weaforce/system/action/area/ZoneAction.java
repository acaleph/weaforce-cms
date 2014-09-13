package com.weaforce.system.action.area;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.entity.area.City;
import com.weaforce.entity.area.Zone;
import com.weaforce.system.service.ISystemService;

/**
 * 区管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/system/area")
public class ZoneAction extends AbstractCrudAction<Zone> {

	private static final long serialVersionUID = -3379425069063244801L;
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	private Zone zone;
	private Long zoneId;
	private Long cityId;

	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	protected void prepareModel() throws Exception {
		zone = systemService.prepareZone(zone, zoneId, cityId);
	}

	public String input() throws Exception {
		return INPUT;
	}

	public String save() throws Exception {
		zone = systemService.saveZone(zone, cityId);
		return list();
	}

	public String delete() throws Exception {
		systemService.deleteZone(zoneId);
		return list();
	}

	public String list() throws Exception {
		pageInfo = systemService.getZonePage(pageInfo, cityId);
		return SUCCESS;
	}

	public Zone getModel() {
		return zone;
	}

	/**
	 * 取得城市 list
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<City> getCityList() throws Exception {
		return systemService.getCityListByProvince(Long.valueOf("1"));
	}

	/**
	 * 取得城区下拉列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getZoneDDL() throws Exception {
		return StrutsUtil.renderJSON(systemService.getZoneDDL(cityId));
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

}
