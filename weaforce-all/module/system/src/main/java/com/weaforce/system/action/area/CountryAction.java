package com.weaforce.system.action.area;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.entity.area.Country;
import com.weaforce.system.service.ISystemService;

@ParentPackage("default")
@Namespace("/system/area")
public class CountryAction extends AbstractCrudAction<Country> {
	private static final long serialVersionUID = 655999329430145756L;
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	private Long countryId;
	private Country country;

	private String countryQueryNameEn;
	private String countryQueryNameCn;

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	
	protected void prepareModel() throws Exception {
		if (this.countryId == null)
			this.country = new Country();
		else
			this.country = systemService.getCountry(countryId);

	}

	
	public String input() throws Exception {
		country = systemService.getCountry(countryId);
		return INPUT;
	}

	
	public String list() throws Exception {
		pageInfo = systemService.getCountryPage(pageInfo, countryQueryNameEn,
				countryQueryNameCn);
		return SUCCESS;
	}

	
	public String save() throws Exception {
		systemService.saveCountry(country);
		return list();
	}

	
	public String lock() throws Exception {
		systemService.saveCountry(country);
		return list();
	}

	
	public String delete() throws Exception {
		systemService.deleteCountry(countryId);
		return list();
	}

	public String getCountryQueryNameEn() {
		return countryQueryNameEn;
	}

	public void setCountryQueryNameEn(String countryQueryNameEn) {
		this.countryQueryNameEn = countryQueryNameEn;
	}

	public String getCountryQueryNameCn() {
		return countryQueryNameCn;
	}

	public void setCountryQueryNameCn(String countryQueryNameCn) {
		this.countryQueryNameCn = countryQueryNameCn;
	}

	public Country getModel() {
		return this.country;
	}

}
