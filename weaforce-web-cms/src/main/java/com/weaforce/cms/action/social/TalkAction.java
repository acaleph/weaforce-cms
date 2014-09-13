package com.weaforce.cms.action.social;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.social.TalkCar;
import com.weaforce.cms.service.ISocialService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;

@ParentPackage("default")
@Namespace("/cms/social")
public class TalkAction extends AbstractCrudAction<TalkCar> {

	private static final long serialVersionUID = 1655907097606321770L;

	@Autowired
	@Qualifier("socialService")
	private ISocialService socialService;

	private TalkCar o;
	private Long talkId;
	private Long parentId;

	public TalkCar getModel() {
		return o;
	}

	public String input() throws Exception {
		return INPUT;
	}

	public String list() throws Exception {
		return SUCCESS;
	}

	public String save() throws Exception {
		o = socialService.saveTalk(o, parentId);
		return INPUT;
	}

	public String delete() throws Exception {
		return StrutsUtil.renderJSON(socialService.deleteTalk(talkId));
	}

	public void setTalkId(Long talkId) {
		this.talkId = talkId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	protected void prepareModel() throws Exception {
		o = socialService.prepareTalk(o, talkId, parentId);
	}

}
