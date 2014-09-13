package com.weaforce.cms.action.social;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.social.Friend;
import com.weaforce.cms.service.ISocialService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;

@ParentPackage("default")
@Namespace("/cms/social") 
public class FriendAction extends AbstractCrudAction<Friend> {

	private static final long serialVersionUID = 3777303156060964079L;
	@Autowired
	@Qualifier("socialService")
	private ISocialService socialService;

	private Friend friend;
	private Long friendId;
	private String loginFriend;

	
	public Friend getModel() {
		return friend;
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String list() throws Exception {
		return SUCCESS;
	}

	public void setLoginFriend(String loginFriend) {
		this.loginFriend = loginFriend;
	}

	
	public String save() throws Exception {
		return StrutsUtil.renderJSON(socialService.saveFriend(friend,
				getAdmin().getUserLogin(), loginFriend));
	}

	
	public String delete() throws Exception {
		return StrutsUtil.renderJSON(socialService.deleteFriend(friendId));

	}


	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}

	
	protected void prepareModel() throws Exception {

	}

}
