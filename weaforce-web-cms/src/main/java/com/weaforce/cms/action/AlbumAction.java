package com.weaforce.cms.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.cms.entity.Album;
import com.weaforce.cms.entity.Template;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.core.util.DateUtil;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;

@ParentPackage("default")
@Namespace("/cms")
public class AlbumAction extends AbstractCrudAction<Album> {
	private static final long serialVersionUID = -4778666450199357885L;
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	private Album album;
	private Long albumId;
	private Long parentId;
	private Long templateId;

	private List<Template> templateList;
	private Long articleId;

	private String queryName;
	private String queryDateFrom;
	private String queryDateTo;

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	protected void prepareModel() throws Exception {
		album = cmsService.prepareAlbum(album, albumId);
	}

	public String input() throws Exception {
		templateList = cmsService.getTemplateList(getAdmin().getAccount(), "1",
				false);
		Template template = album.getAlbumTemplate();
		if (template != null)
			if (templateList.indexOf(template) == -1)
				templateList.add(template);
		return INPUT;
	}

	/**
	 * 父亲
	 * 
	 * @param parentId
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 模板
	 * 
	 * @param templateId
	 */
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String save() throws Exception {
		album = cmsService.saveAlbum(album, parentId, templateId);
		return list();
	}

	/**
	 * 删除像集
	 */

	public String delete() throws Exception {
		cmsService.deleteAlbum(albumId);
		return list();
	}

	public String list() throws Exception {
		pageInfo = cmsService.getAlbumPage(pageInfo, getAdmin().getAccount(),
				queryName);
		return SUCCESS;
	}

	/**
	 * 发布像片栏目到HTML
	 * 
	 * @return
	 * @throws Exception
	 */
	public String parse() throws Exception {
		cmsService.parseAlbum(albumId);
		return list();
	}

	/**
	 * 文章主键
	 * 
	 * @param articleId
	 */
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	/**
	 * 根据文章以JSON格式,取得像册 list
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAlbumListJSON() throws Exception {
		if (queryDateFrom == null || "".equals(queryDateFrom))
			queryDateFrom = DateUtil.getMonthBeforeCur();
		if (queryDateTo == null || "".equals(queryDateTo))
			queryDateTo = DateUtil.defaultFormat(new Date());
		return StrutsUtil.renderJSON(cmsService.getAlbumListJSONByArticle(
				getAdmin().getAccount(), articleId, queryName, queryDateFrom,
				queryDateTo));
	}

	public Album getModel() {
		return album;
	}

	/**
	 * 取得像册list
	 * 
	 * @return
	 */

	public List<Album> getAlbumList() {
		return cmsService.getAlbumList(getAdmin().getAccount(), true);
	}

	/**
	 * 取得模板list
	 * 
	 * @return
	 */
	public List<Template> getTemplateList() {
		return templateList;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
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

}
