package com.weaforce.system.action.area;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.entity.area.Country;
import com.weaforce.entity.area.Province;
import com.weaforce.system.service.ISystemService;

@ParentPackage("default")
@Namespace("/system/area")
public class ProvinceAction extends AbstractCrudAction<Province> {
	private static final long serialVersionUID = 6949203422974964498L;
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;
	
	private Long provinceId;
	private Province province;
	private Long countryId;
	private List<Country> countryList;

	private String queryProvinceNameEn;
	private String queryProvinceNameCn;

	
	public String delete() throws Exception {
		return list();
	}

	
	public String input() throws Exception {
		countryList = systemService.getCountryList();
		return INPUT;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	
	public String list() throws Exception {
		pageInfo = systemService.getProvincePage(pageInfo, countryId,
				queryProvinceNameEn, queryProvinceNameCn);
		return SUCCESS;
	}


	
	public String save() throws Exception {
		province = systemService.saveProvince(province);
		return list();
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public String getQueryProvinceNameEn() {
		return queryProvinceNameEn;
	}

	public void setQueryProvinceNameEn(String queryProvinceNameEn) {
		this.queryProvinceNameEn = queryProvinceNameEn;
	}

	public String getQueryProvinceNameCn() {
		return queryProvinceNameCn;
	}

	public void setQueryProvinceNameCn(String queryProvinceNameCn) {
		this.queryProvinceNameCn = queryProvinceNameCn;
	}

	
	protected void prepareModel() throws Exception {
		if (this.provinceId == null)
			this.province = new Province();
		else
			this.province = systemService.getProvince(provinceId);
	}

	public Province getModel() {
		return this.province;
	}

}
