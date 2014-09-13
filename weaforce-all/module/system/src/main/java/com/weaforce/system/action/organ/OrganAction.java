package com.weaforce.system.action.organ;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.system.entity.organ.Organ;
import com.weaforce.system.service.ISystemService;

@ParentPackage("default")
@Namespace("/system/organ")
public class OrganAction extends AbstractCrudAction<Organ> {
	private static final long serialVersionUID = 7008761402144177270L;
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	private Organ organ;
	private Long organId;
	private Long organFid;
	private Long fid;

	private List<Organ> organList;

	private String queryOrganName;

	public Organ getModel() {
		return this.organ;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	
	protected void prepareModel() throws Exception {
		if (this.organId == null)
			this.organ = new Organ();
		else
			this.organ = systemService.getOrgan(organId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		if (organFid != null)
			organ.setOrganParent(systemService.getOrgan(organFid));
		systemService.saveOrgan(organ);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = systemService.getOrganPage(pageInfo, getAdmin().getAccount(),
				queryOrganName);
		return SUCCESS;
	}


	
	public String delete() throws Exception {
		systemService.deleteOrgan(organ);
		return list();
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public String doOrganTree() throws Exception {
		StringBuffer organTree = new StringBuffer();
		System.out.println("organFid value: " + fid);
		List<Organ> list = systemService.getOrganListByParent(getAdmin()
				.getAccount(), fid);
		organTree
				.append("<ul class=\"jqueryTree\" style=\"display: none;\"> \n");
		for (Organ o : list) {
			//System.out.println("son: " + o.getOrganChild().size());
			if (o.getOrganChild().size() <= 0)
				organTree.append("<li class=\"isHref\" >");
			else
				organTree.append("<li class='noHref collapsed'>");
			organTree.append("<a href=\"#\" rel=\"" + o.getOrganId() + "\"> ");
			organTree.append(o.getOrganName() + "</a></li> \n");
		}
		organTree.append("</ul>");
		System.out.println("organTree: " + organTree.toString());
		return StrutsUtil.renderHTML(organTree.toString());
	}

	public void setOrganFid(Long organFid) {
		this.organFid = organFid;
	}

	public List<Organ> getOrganList() {
		return organList;
	}

	public String getQueryOrganName() {
		return queryOrganName;
	}

	public void setQueryOrganName(String queryOrganName) {
		this.queryOrganName = queryOrganName;
	}

}
