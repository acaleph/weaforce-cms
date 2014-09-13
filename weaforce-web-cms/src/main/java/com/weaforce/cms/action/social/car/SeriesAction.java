package com.weaforce.cms.action.social.car;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.social.CarBrand;
import com.weaforce.cms.entity.social.CarSeries;
import com.weaforce.cms.service.ISocialService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;

@ParentPackage("default")
@Namespace("/cms/social/car")
public class SeriesAction extends AbstractCrudAction<CarSeries> {

	private static final long serialVersionUID = 3088611783443306684L;
	@Autowired
	@Qualifier("socialService")
	private ISocialService socialService;

	private CarSeries series;
	private Long seriesId;
	private Long brandId;

	public void setSeriesId(Long seriesId) {
		this.seriesId = seriesId;
	}

	protected void prepareModel() throws Exception {
		series = socialService.prepareCarSeries(series, seriesId, brandId);
	}

	public String input() throws Exception {
		series = socialService.saveCarSeries(series, brandId);
		return INPUT;
	}

	public String list() throws Exception {
		pageInfo = socialService.getCarSeriesPage(pageInfo, brandId);
		return SUCCESS;
	}

	public String save() throws Exception {
		series = socialService.saveCarSeries(series, brandId);
		return list();
	}

	public String delete() throws Exception {
		socialService.deleteCarSeries(seriesId);
		return list();
	}

	public CarSeries getModel() {
		return series;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
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

	/**
	 * Get series JOSN
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getSeriesDDL() throws Exception {
		return StrutsUtil.renderJSON(socialService.getSeriesJSON(brandId)
				.toString());
	}
}
