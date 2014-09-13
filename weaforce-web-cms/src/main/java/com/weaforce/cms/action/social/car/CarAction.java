package com.weaforce.cms.action.social.car;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.social.Car;
import com.weaforce.cms.entity.social.CarBrand;
import com.weaforce.cms.entity.social.CarSeries;
import com.weaforce.cms.service.ISocialService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

@ParentPackage("default")
@Namespace("/cms/social/car")
public class CarAction extends AbstractCrudAction<Car> {

	private static final long serialVersionUID = -6421967382974353238L;

	@Autowired
	@Qualifier("socialService")
	private ISocialService socialService;

	private Car car;
	private Long carId;
	private Long brandId;
	private Long seriesId;
	private List<CarBrand> brandList;

	public Car getModel() {
		return car;
	}

	public String input() throws Exception {
		return INPUT;
	}

	public String list() throws Exception {
		brandList = socialService.getCarBrandList();
		// Initialize brand primary key
		if (brandId == null)
			if (brandList.size() > 0)
				brandId = brandList.get(0).getBrandId();
		pageInfo = socialService.getCarPage(pageInfo, brandId, seriesId);
		return SUCCESS;
	}

	public String save() throws Exception {
		car = socialService.saveCar(car, seriesId);
		return list();
	}

	public String delete() throws Exception {
		socialService.deleteCar(carId);
		return list();
	}

	/**
	 * @param carId
	 *            Car primary key
	 */
	public void setCarId(Long carId) {
		this.carId = carId;
	}

	protected void prepareModel() throws Exception {
		car = socialService.prepareCar(car, carId, seriesId);
	}

	/**
	 * Get car brand list
	 * 
	 * @return
	 * @throws InterruptedException 
	 */
	public List<CarBrand> getBrandList() throws InterruptedException {
		return socialService.getCarBrandList();
	}

	public List<Car> getCarList() {
		return socialService.getCarListByOwner(getAdmin().getUserLogin());
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(Long seriesId) {
		this.seriesId = seriesId;
	}

	public List<CarSeries> getSeriesList() {
		return socialService.getSeriesByBrand(brandId);
	}

}
