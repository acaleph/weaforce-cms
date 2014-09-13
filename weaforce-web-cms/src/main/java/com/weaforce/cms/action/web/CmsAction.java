package com.weaforce.cms.action.web;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Registry;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * CMS Web请求管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms")
public class CmsAction extends ActionSupport {
	private static final long serialVersionUID = 5126923571070940334L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	private Long channelId;
	private Long articleId;

	private String userLogin;
	private String userPassword;

	private Long categoryId;
	private Integer pageNumber;

	public String execute() throws Exception {
		return SUCCESS;
	}

	// ------------------------------------------------------------------------
	/**
	 * 从response中获取频道主键
	 * 
	 * @param channelId
	 */
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	/**
	 * 根据频道取得栏目JSON格式数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/cms/category-json")
	public String getCategoryJSON() throws Exception {
		return StrutsUtil.renderJSON(cmsService.getCategoryJSON(channelId));
	}

	// ------------------------------------------------------------------------
	// ------------------------------------------------------------------------

	/**
	 * 文章主键
	 * 
	 * @param articleId
	 */
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	/**
	 * 保存文章点击次数
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/cms/hit-article")
	public void hitArticle() throws Exception {
		// cmsService.saveArticleHit(articleId);
		cmsService.saveHit(articleId, StrutsUtil.getRequest().getRemoteAddr());
	}

	// ------------------------------------------------------------------------
	// ------------------------------------------------------------------------
	/**
	 * 注册登录
	 * 
	 * @param userLogin
	 */
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	/**
	 * 注册密码
	 * 
	 * @param userPassword
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * 注册登录
	 * 
	 * @throws Exception
	 */
	@Action(value = "/cms/registry-login")
	public void registryLogin() throws Exception {
		Registry user = cmsService.registryLogin(userLogin, userPassword,
				StrutsUtil.getRequest().getRemoteAddr());
		if (user != null) {
			StrutsUtil.getRequest().getSession().setAttribute(
					com.weaforce.core.util.Global.SESSION_ID_IN_COOKIE, user);
		}

	}

	// ------------------------------------------------------------------------
	// ------------------------------------------------------------------------
	/**
	 * 当前页码
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * 栏目主键
	 * 
	 * @param categoryId
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 栏目文章页组件
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/cms/article-page")
	public String pageArticle() throws Exception {
		return StrutsUtil.renderJSON(cmsService.getArticleJSONByCategory(
				categoryId, pageNumber));
	}

	// ------------------------------------------------------------------------

}
