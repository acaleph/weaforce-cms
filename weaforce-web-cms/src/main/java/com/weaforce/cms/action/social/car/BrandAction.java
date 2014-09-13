package com.weaforce.cms.action.social.car;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.social.CarBrand;
import com.weaforce.cms.service.ISocialService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

/**
 * Brand action
 * 
 * @author Manfred
 * 
 */
@ParentPackage("default")
@Namespace("/cms/social/car")
public class BrandAction extends AbstractCrudAction<CarBrand> {
	private static final long serialVersionUID = -1688749682352517402L;

	@Autowired
	@Qualifier("socialService")
	private ISocialService socialService;

	private CarBrand brand;
	private Long brandId;

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	protected void prepareModel() throws Exception {
		brand = socialService.prepareCarBrand(brand, brandId);
	}

	public String input() throws Exception {
		return INPUT;
	}

	public String list() throws Exception {
		pageInfo = socialService.getCarBrandPage(pageInfo);
		return SUCCESS;
	}

	public String save() throws Exception {
		brand = socialService.saveCarBrand(brand);
		return INPUT;
	}

	public String delete() throws Exception {
		socialService.deleteCarBrand(brandId);
		return list();
	}

	public CarBrand getModel() {
		return brand;
	}

}
