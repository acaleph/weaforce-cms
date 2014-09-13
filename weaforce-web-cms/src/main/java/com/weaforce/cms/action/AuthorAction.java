package com.weaforce.cms.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Author;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;

/**
 * 文章作者管理
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms")
public class AuthorAction extends AbstractCrudAction<Author> {

	private static final long serialVersionUID = -3072426287300554450L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	private Author author;
	private Long authorId;

	private String queryName;

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	
	protected void prepareModel() throws Exception {
		author = cmsService.prepareAuthor(author, authorId);
	}

	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		author = cmsService.saveAuthor(author);
		return list();
	}

	
	public String delete() throws Exception {
		cmsService.deleteAuthor(authorId);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = cmsService.getAuthorPage(pageInfo, getAdmin().getAccount(),
				queryName);
		return SUCCESS;
	}


	public Author getModel() {
		return author;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

}
