package com.weaforce.cms.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Article;
import com.weaforce.cms.entity.Author;
import com.weaforce.cms.entity.Category;
import com.weaforce.cms.entity.Channel;
import com.weaforce.cms.entity.CopyFrom;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;

@ParentPackage("default")
@Namespace("/cms")
public class ArticleAction extends AbstractCrudAction<Article> {

	private static final long serialVersionUID = 239945882878475461L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	private Long articleId;
	private Long channelId;
	private Long categoryId;
	private Article article;
	private List<Channel> channelList;
	private Integer pageNumber;

	private Long slaveId;
	private Long albumId;

	private String queryTitle;
	private String queryDateFrom;
	private String queryDateTo;

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	protected void prepareModel() throws Exception {
	}

	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 从页面取得文章内容
	 * 
	 * @param contentContent
	 */

	public String save() throws Exception {
		return list();
	}

	public String delete() throws Exception {
		cmsService.deleteArticle(articleId);
		return list();
	}

	public String list() throws Exception {
		channelList = cmsService.getChannelList(getAdmin().getAccount(), "1");
		// 初始化频道/栏目
		if (channelId == null)
			if (channelList.size() > 0)
				channelId = channelList.get(0).getChannelId();
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
	 * 以JSON格式,取得栏目文章 page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String page() throws Exception {
		return StrutsUtil.renderJSON(cmsService.getArticleJSONByCategory(
				categoryId, pageNumber));
	}

	/**
	 * parse 文章 HTML 文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String parse() throws Exception {
		if (articleId != null)
			cmsService.parseArticle(null, articleId, getAdmin().getUserCity()
					.getCityNameCn());
		return list();
	}

	/**
	 * unparse 文章,删除HTML 文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String unparse() throws Exception {
		if (articleId != null)
			cmsService.unparseArticle(articleId);
		return list();
	}

	/**
	 * 将栏目下的所有文章parse为HTML文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String parseAll() throws Exception {
		if (categoryId != null) {
			cmsService.parseArticleAllByCategory(categoryId);
		}
		return list();
	}

	/**
	 * 预处理文章
	 */
	public void prepareRelation() throws Exception {
	}

	/**
	 * 跳转到选择关联文章页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String relation() throws Exception {
		return "relation";
	}

	/**
	 * 取得JSON格式文章 list
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getArticleListJSON() throws Exception {
		return StrutsUtil.renderJSON(cmsService.getArticleListJSON(articleId,
				queryTitle, queryDateFrom, queryDateTo));
	}

	/**
	 * 关联文章主键
	 * 
	 * @param slaveId
	 */
	public void setSlaveId(Long slaveId) {
		this.slaveId = slaveId;
	}

	/**
	 * 保存关联文章
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveRelation() throws Exception {
		return StrutsUtil.renderJSON(cmsService.saveArticleRelation(articleId,
				slaveId));
	}

	/**
	 * 预处理文章
	 */
	public void prepareAlbum() throws Exception {
	}

	/**
	 * 跳转到选择像册页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String album() throws Exception {
		return "album";
	}

	/**
	 * 像册主键
	 * 
	 * @param albumId
	 */
	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	/**
	 * 保存与文章关联的像册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveAlbum() throws Exception {
		return StrutsUtil.renderJSON(cmsService.saveArticleAlbum(articleId,
				albumId));
	}

	public Article getModel() {
		return article;
	}

	/**
	 * 频道
	 * 
	 * @return
	 */
	public List<Channel> getChannelList() {
		return channelList;
	}

	/**
	 * 取得栏目list
	 * 
	 * @return
	 */
	public List<Category> getCategoryList() {
		return cmsService.getCategoryListByUserChannel(getAdmin().getUserId(),
				channelId);
	}

	/**
	 * 取得作者list
	 * 
	 * @return
	 */
	public List<Author> getAuthorList() {
		return cmsService.getAuthorList(getAdmin().getAccount(), false);
	}

	/**
	 * 取得来源list
	 * 
	 * @return
	 */
	public List<CopyFrom> getFromList() {
		return cmsService.getCopyFromList(false);

	}

	public String getQueryTitle() {
		return queryTitle;
	}

	public void setQueryTitle(String queryTitle) {
		this.queryTitle = queryTitle;
	}

	public String getQueryDateFrom() {
		return queryDateFrom;
	}

	public void setQueryDateFrom(String queryDateFrom) {
		this.queryDateFrom = queryDateFrom;
	}

	public String getQueryDateTo() {
		return queryDateTo;
	}

	public void setQueryDateTo(String queryDateTo) {
		this.queryDateTo = queryDateTo;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
