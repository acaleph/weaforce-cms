package com.weaforce.cms.action.social.car;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.social.CarShareRequest;
import com.weaforce.cms.service.ISocialService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

@ParentPackage("default")
@Namespace("/cms/social/car") 
public class RequestAction extends AbstractCrudAction<CarShareRequest> {

	private static final long serialVersionUID = 3200459791251778204L;
	@Autowired
	@Qualifier("socialService")
	private ISocialService socialService;

	private CarShareRequest request;
	private Long requestId;
	private Long shareId;

	
	public CarShareRequest getModel() {
		return request;
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String list() throws Exception {
		return SUCCESS;
	}

	
	public String save() throws Exception {
		request = socialService.saveCarShareRequest(request, shareId);
		return list();
	}

	
	public String delete() throws Exception {
		socialService.deleteCarShareRequest(requestId);
		return list();
	}


	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}

	
	protected void prepareModel() throws Exception {
		request = socialService.prepareCarShareRequest(request, requestId,
				shareId);
	}

}
