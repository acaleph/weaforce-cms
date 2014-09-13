package com.weaforce.cms.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Album;
import com.weaforce.cms.entity.AlbumPhoto;
import com.weaforce.cms.entity.Article;
import com.weaforce.cms.entity.Category;
import com.weaforce.cms.entity.PageLink;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;

/**
 * 页面元素管理Action
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@ParentPackage("default")
@Namespace("/cms")
public class PageLinkAction extends AbstractCrudAction<PageLink> {
	private static final long serialVersionUID = 941766277905575853L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	private PageLink link;
	private Long linkId;
	private Long parentId;
	private Long childId; //Saving:parentId = childId

	private Long articleId;
	private Long photoId;

	private Long channelId;
	private Long categoryId;
	private Long albumId;

	private List<PageLink> linkParentList = new ArrayList<PageLink>();
	private List<PageLink> articleLinkList = new ArrayList<PageLink>();

	private String queryName;
	private String queryTitle;

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	
	protected void prepareModel() throws Exception {
		link = cmsService.preparePageLink(link, linkId, parentId);
	}

	
	public String input() throws Exception {
		// 如果link来自于文章,将文章属性存入到link
		Article o = link.getLinkArticle();
		if (o != null) {
			link.setLinkName(o.getArticleTitle());
			link.setLinkUrl(o.getArticleUrl());
			link.setLinkTitle(o.getArticleTitle());
			link.setLinkImageUrl(o.getArticleImageUrl());
		}
		return INPUT;
	}

	/**
	 * 页面取得文章
	 * 
	 * @return
	 */
	public Long getArticleId() {
		return articleId;
	}

	/**
	 * 父节点
	 * 
	 * @param parent
	 */

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 频道
	 * 
	 * @param channelId
	 */
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	/**
	 * 栏目
	 * 
	 * @param categoryId
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 像集
	 * 
	 * @param albumId
	 */
	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	/**
	 * 二级联动子节点，保存时，传递给parentId
	 * 
	 * @param childId
	 */
	public void setChildId(Long childId) {
		this.childId = childId;
	}

	/**
	 * 保存页页元素
	 */
	
	public String save() throws Exception {
		if (childId != null)
			parentId = childId;
		link = cmsService.savePageLink(getAdmin().getAccount(),link, parentId, channelId, categoryId,
				albumId, articleId);
		return list();
	}

	/**
	 * 只允许删除没有子元素且没有加锁的link
	 */
	
	public String delete() throws Exception {
		cmsService.deletePageLink(linkId);
		return list();
	}

	/**
	 * 只取得最高级页面元素:首页,频道,栏目
	 */
	
	public String list() throws Exception {
		pageInfo = cmsService.getLinkPage(pageInfo, getAdmin().getAccount(),
				null, queryName, queryTitle);
		return SUCCESS;
	}


	/**
	 * 页面元素树
	 * 
	 * @return
	 * @throws Exception
	 */
	public String tree() throws Exception {
		return StrutsUtil.renderJSON(cmsService.getLinkTree(parentId));
	}

	/**
	 * 文章,用于文章发布至页面
	 * 
	 * @param articleId
	 */
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	/**
	 * 取得链接页面根 list
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PageLink> getLinkParentList() throws Exception {
		return linkParentList;
	}

	/**
	 * 实现prepare接口,产生一个新的link对象
	 * 
	 * @throws Exception
	 */
	public void prepareArticle() throws Exception {
		if (articleId != null) {
			Article article = cmsService.getArticle(articleId);
			link = article.getArticleLink();
			if (link == null) {
				link = new PageLink();
				link.setLinkArticle(article);
			}
			// 默认直接以文章ID做为标签代码
			if (link.getLinkLabelCode() == null
					|| "".equals(link.getLinkLabelCode()))
				link.setLinkLabelCode(articleId.toString());
		}
	}

	/**
	 * 发布文章至页面元素
	 * 
	 * @return
	 * @throws Exception
	 */
	public String article() throws Exception {
		// 根页面元素 list
		linkParentList = cmsService.getLinkRootList(getAdmin().getAccount());
		if (parentId == null)
			if (linkParentList.size() > 0)
				parentId = linkParentList.get(0).getLinkId();
		if (parentId != null)
			articleLinkList = cmsService.getPageLinkListByParent(parentId);

		return "article";
	}

	/**
	 * 根据顶级页面元素,取得页面元素 JSON,无文章列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getLinkDDL() throws Exception {
		return StrutsUtil.renderJSON(cmsService.getPageLinkDDL(parentId));
	}

	/**
	 * 照片,用于文章发布至页面
	 * 
	 * @param photoId
	 */
	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	/**
	 * 实现prepare接口,产生一个新的link对象
	 * 
	 * @throws Exception
	 */
	public void preparePhoto() throws Exception {
		if (photoId != null) {
			AlbumPhoto photo = cmsService.getPhoto(photoId);
			if (photo.getPhotoLink() == null) {
				link = new PageLink();
				link.setLinkPhoto(photo);
			} else
				link = photo.getPhotoLink();
		}
	}

	/**
	 * 发布照片至页面元素
	 * 
	 * @return
	 * @throws Exception
	 */
	public String photo() throws Exception {
		return "photo";
	}

	/**
	 * parse 自定义页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String parse() throws Exception {
		if (linkId != null) {
			cmsService.parsePageLink(linkId, getAdmin().getUserCity()
					.getCityNameCn());
		}
		return list();
	}

	/**
	 * 根据文章,取得页面元素list,用于文章发布至页面
	 * 
	 * @return
	 */
	public List<PageLink> getArticleLinkList() {
		return articleLinkList;
	}

	public List<PageLink> getPhotoLinkList() {
		return cmsService.getPageLinkListByPhoto(photoId);
	}

	/**
	 * 频道list
	 * 
	 * @return
	 */

	/**
	 * 栏目list
	 * 
	 * @return
	 */
	public List<Category> getCategoryList() {
		List<Category> categoryList = cmsService.getCategoryListWithChannel(
				getAdmin().getAccount(), "1");
		Category o = new Category();
		o.setCategoryName("--- none ---");
		categoryList.add(0, o);
		return categoryList;
	}

	public List<Album> getAlbumList() {
		return cmsService.getAlbumList(getAdmin().getAccount(), true);
	}

	public PageLink getModel() {
		return link;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQueryTitle() {
		return queryTitle;
	}

	public void setQueryTitle(String queryTitle) {
		this.queryTitle = queryTitle;
	}

}
