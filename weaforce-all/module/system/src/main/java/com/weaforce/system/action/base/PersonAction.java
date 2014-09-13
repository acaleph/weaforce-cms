package com.weaforce.system.action.base;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.entity.area.City;
import com.weaforce.entity.area.Country;
import com.weaforce.entity.area.Province;
import com.weaforce.system.entity.base.Person;
import com.weaforce.system.service.ISystemService;

@ParentPackage("default")
@Namespace("/system/base")
public class PersonAction extends AbstractCrudAction<Person> {
	private static final long serialVersionUID = 1L;
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	private Long personId;
	private Person person;

	private List<Country> personAddressCountry;
	private List<Province> personAddressProvince;
	private List<City> personAddressCity;

	private String personQueryName;
	private Long personQueryAddressCountryId;
	private Long personQueryAddressProvinceId;
	private Long personQueryAddressCityId;
	private String personQueryAddressLocation;

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	
	protected void prepareModel() throws Exception {
		if (this.personId == null)
			this.person = new Person();
	}

	
	public String input() throws Exception {
		personAddressCountry = systemService.getCountryList();
		personAddressProvince = systemService.getProvinceList(Long.valueOf("1"));
		personAddressCity = systemService.getCityList();
		return INPUT;
	}

	
	public String save() throws Exception {
		return list();
	}

	
	public String list() throws Exception {
		return SUCCESS;
	}

	public String delete() throws Exception {
		return list();
	}

	public List<Country> getPersonAddressCountry() {
		return personAddressCountry;
	}

	public void setPersonAddressCountry(List<Country> personAddressCountry) {
		this.personAddressCountry = personAddressCountry;
	}

	public List<Province> getPersonAddressProvince() {
		return personAddressProvince;
	}

	public void setPersonAddressProvince(List<Province> personAddressProvince) {
		this.personAddressProvince = personAddressProvince;
	}

	public List<City> getPersonAddressCity() {
		return personAddressCity;
	}

	public void setPersonAddressCity(List<City> personAddressCity) {
		this.personAddressCity = personAddressCity;
	}


	public String getPersonQueryName() {
		return personQueryName;
	}

	public void setPersonQueryName(String personQueryName) {
		this.personQueryName = personQueryName;
	}

	public Long getPersonQueryAddressCountryId() {
		return personQueryAddressCountryId;
	}

	public void setPersonQueryAddressCountryId(Long personQueryAddressCountryId) {
		this.personQueryAddressCountryId = personQueryAddressCountryId;
	}

	public Long getPersonQueryAddressProvinceId() {
		return personQueryAddressProvinceId;
	}

	public void setPersonQueryAddressProvinceId(
			Long personQueryAddressProvinceId) {
		this.personQueryAddressProvinceId = personQueryAddressProvinceId;
	}

	public Long getPersonQueryAddressCityId() {
		return personQueryAddressCityId;
	}

	public void setPersonQueryAddressCityId(Long personQueryAddressCityId) {
		this.personQueryAddressCityId = personQueryAddressCityId;
	}

	public String getPersonQueryAddressLocation() {
		return personQueryAddressLocation;
	}

	public void setPersonQueryAddressLocation(String personQueryAddressLocation) {
		this.personQueryAddressLocation = personQueryAddressLocation;
	}

	public Person getModel() {
		return this.person;
	}

}
