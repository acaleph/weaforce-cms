package com.weaforce.cms.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Meta;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

@ParentPackage("default")
@Namespace("/cms")
public class MetaAction extends AbstractCrudAction<Meta> {

	private static final long serialVersionUID = -6867793209455954190L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	private Meta meta;
	private Long metaId;

	private String queryMetaKey;
	private String queryMetaValue;

	public void setMetaId(Long metaId) {
		this.metaId = metaId;
	}

	
	protected void prepareModel() throws Exception {
		meta = cmsService.prepareMeta(meta, metaId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String list() throws Exception {
		return SUCCESS;
	}

	
	public String delete() throws Exception {
		cmsService.deleteMeta(metaId);
		return list();
	}

	public String lock() throws Exception {
		if (metaId != null) {
			meta = cmsService.getMeta(metaId);
		}
		return list();
	}

	
	public String save() throws Exception {
		meta = cmsService.saveMeta(meta);
		return list();
	}

	public String getQueryMetaKey() {
		return queryMetaKey;
	}

	public void setQueryMetaKey(String queryMetaKey) {
		this.queryMetaKey = queryMetaKey;
	}

	public String getQueryMetaValue() {
		return queryMetaValue;
	}

	public void setQueryMetaValue(String queryMetaValue) {
		this.queryMetaValue = queryMetaValue;
	}

	public Meta getModel() {
		return meta;
	}

}
