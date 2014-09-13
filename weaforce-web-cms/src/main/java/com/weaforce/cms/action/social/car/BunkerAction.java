package com.weaforce.cms.action.social.car;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.social.CarBunker;
import com.weaforce.cms.service.ISocialService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

@ParentPackage("default")
@Namespace("/cms/social/car")
public class BunkerAction extends AbstractCrudAction<CarBunker> {

	private static final long serialVersionUID = -7962693838647623311L;

	@Autowired
	@Qualifier("socialService")
	private ISocialService socialService;

	private CarBunker bunker;
	private Long bunkerId;
	private Long carId;

	
	public CarBunker getModel() {
		return bunker;
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String list() throws Exception {
		pageInfo = socialService.getCarBunkerPage(pageInfo, carId);
		return SUCCESS;
	}

	
	public String save() throws Exception {
		bunker = socialService.saveCarBunker(bunker, carId);
		return list();
	}

	
	public String delete() throws Exception {
		socialService.deleteCarBunker(bunkerId);
		return list();
	}


	public void setBunkerId(Long bunkerId) {
		this.bunkerId = bunkerId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	
	protected void prepareModel() throws Exception {
		bunker = socialService.prepareCarBunker(bunker, bunkerId, carId);
	}

}
