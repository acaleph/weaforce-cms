package com.weaforce.system.action.area;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.entity.area.City;
import com.weaforce.entity.area.Province;
import com.weaforce.system.service.ISystemService;

@ParentPackage("default")
@Namespace("/system/area")
public class CityAction extends AbstractCrudAction<City> {
	private static final long serialVersionUID = 9162603958065061032L;
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	private Long cityId;
	private City city;
	private Long provinceId;

	private List<Province> provinceList;

	private String cityQueryNameEn;
	private String cityQueryNameCn;

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	
	protected void prepareModel() throws Exception {
		city = systemService.prepareCity(city, cityId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		city = systemService.saveCity(city);
		return list();
	}

	
	public String delete() throws Exception {
		systemService.deleteCity(cityId);
		return list();
	}

	
	public String list() throws Exception {
		provinceList = systemService.getProvinceList(Long.valueOf("1"));
		if (provinceId == null)
			if (provinceList.size() > 0)
				provinceId = provinceList.get(0).getProvinceId();
		pageInfo = systemService.getCityPage(pageInfo, provinceId,
				cityQueryNameEn, cityQueryNameCn);
		return SUCCESS;
	}

	/**
	 * 取得城市下拉列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getCityDDL() throws Exception {
		provinceList = systemService.getProvinceList(Long.valueOf("1"));
		if (provinceId == null)
			if (provinceList.size() > 0)
				provinceId = provinceList.get(0).getProvinceId();
		return StrutsUtil.renderJSON(systemService.getCityDDL(provinceId));
	}


	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public List<Province> getProvinceList() {
		return provinceList;
	}

	public String getCityQueryNameEn() {
		return cityQueryNameEn;
	}

	public void setCityQueryNameEn(String cityQueryNameEn) {
		this.cityQueryNameEn = cityQueryNameEn;
	}

	public String getCityQueryNameCn() {
		return cityQueryNameCn;
	}

	public void setCityQueryNameCn(String cityQueryNameCn) {
		this.cityQueryNameCn = cityQueryNameCn;
	}

	public City getModel() {
		return this.city;
	}

}
