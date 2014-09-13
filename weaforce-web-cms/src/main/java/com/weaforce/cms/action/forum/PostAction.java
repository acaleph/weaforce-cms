package com.weaforce.cms.action.forum;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.forum.Post;
import com.weaforce.cms.service.IForumService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;

/**
 * 贴子管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms/forum")
public class PostAction extends AbstractCrudAction<Post> {
	private static final long serialVersionUID = 8919181174238729753L;
	@Autowired
	@Qualifier("forumService")
	private IForumService forumService;

	private Post post;
	private Long postId;
	private Long topicId;
	private Long forumId;
	private String contentContent;

	private String queryTitle;
	private Integer pageNumber;

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	
	protected void prepareModel() throws Exception {
		post = forumService.preparePost(post, postId, topicId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String delete() throws Exception {
		forumService.deletePost(postId);
		return list();
	}

	/**
	 * 贴子内容
	 * 
	 * @param contentContent
	 */
	public void setContentContent(String contentContent) {
		this.contentContent = contentContent;
	}

	
	public String save() throws Exception {
		post = forumService.savePost(post, forumId, contentContent,
				StrutsUtil.getRequest().getRemoteAddr());
		return list();
	}

	
	public String list() throws Exception {
		if (topicId != null)
			pageInfo = forumService.getPostPage(pageInfo, topicId, queryTitle);
		return SUCCESS;
	}

	/**
	 * 当前页数
	 * 
	 * @param pageNumber
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * 以JSON格式,取得贴子列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String page() throws Exception {
		return StrutsUtil.renderJSON(forumService.getPostPageJSON(topicId, queryTitle,
				pageNumber));
	}

	/**
	 * 以HTML格式,取得贴子列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String html() throws Exception {
		return StrutsUtil.renderHTML(forumService.getPostPageHTML(topicId, queryTitle,
				pageNumber));
	}


	public Post getModel() {
		return post;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	public String getQueryTitle() {
		return queryTitle;
	}

	public void setQueryTitle(String queryTitle) {
		this.queryTitle = queryTitle;
	}

}
