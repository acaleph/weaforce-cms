package com.weaforce.cms.service.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.weaforce.cms.dao.IAlbumDao;
import com.weaforce.cms.dao.IArticleMobileDao;
import com.weaforce.cms.dao.ICategoryMobileDao;
import com.weaforce.cms.dao.ICategoryUserDao;
import com.weaforce.cms.dao.IChannelMobileDao;
import com.weaforce.cms.dao.IHitDao;
import com.weaforce.cms.dao.ILinkDao;
import com.weaforce.cms.dao.INoteDao;
import com.weaforce.cms.dao.IPhotoDao;
import com.weaforce.cms.dao.IArticleDao;
import com.weaforce.cms.dao.IAuthorDao;
import com.weaforce.cms.dao.ICategoryDao;
import com.weaforce.cms.dao.IChannelDao;
import com.weaforce.cms.dao.ICopyFromDao;
import com.weaforce.cms.dao.IMetaDao;
import com.weaforce.cms.dao.IPageLinkDao;
import com.weaforce.cms.dao.IRegistryDao;
import com.weaforce.cms.dao.ITemplateDao;
import com.weaforce.cms.dao.ITemplateMobileDao;
import com.weaforce.cms.entity.Album;
import com.weaforce.cms.entity.AlbumPhoto;
import com.weaforce.cms.entity.Article;
import com.weaforce.cms.entity.ArticleContent;
import com.weaforce.cms.entity.ArticleHit;
import com.weaforce.cms.entity.ArticleMobile;
import com.weaforce.cms.entity.Author;
import com.weaforce.cms.entity.Category;
import com.weaforce.cms.entity.CategoryMobile;
import com.weaforce.cms.entity.CategoryUser;
import com.weaforce.cms.entity.Channel;
import com.weaforce.cms.entity.ChannelMobile;
import com.weaforce.cms.entity.CopyFrom;
import com.weaforce.cms.entity.Link;
import com.weaforce.cms.entity.Meta;
import com.weaforce.cms.entity.Note;
import com.weaforce.cms.entity.PageLink;
import com.weaforce.cms.entity.Registry;
import com.weaforce.cms.entity.Template;
import com.weaforce.cms.entity.TemplateMobile;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.cms.util.HtmlParser;
import com.weaforce.core.common.bean.TreeNode;
import com.weaforce.core.util.DateUtil;
import com.weaforce.core.util.ImageUtil;
import com.weaforce.core.util.JsonBuilder;
import com.weaforce.core.util.MD5;
import com.weaforce.core.util.PageInfo;
import com.weaforce.core.util.StringUtil;
import com.weaforce.core.web.page.Page;
import com.weaforce.system.component.spring.Security;
import com.weaforce.entity.admin.User;
import com.weaforce.system.service.impl.SystemService;
import com.weaforce.system.util.FileUtils;

/**
 * 广告服务
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Service("cmsService")
public class CmsService extends SystemService implements ICmsService {
	@Autowired
	@Qualifier("templateDao")
	private ITemplateDao templateDao;
	@Autowired
	@Qualifier("channelDao")
	private IChannelDao channelDao;
	@Autowired
	@Qualifier("categoryDao")
	private ICategoryDao categoryDao;
	@Autowired
	@Qualifier("metaDao")
	private IMetaDao metaDao;
	@Autowired
	@Qualifier("linkDao")
	private ILinkDao linkDao;
	@Autowired
	@Qualifier("pageLinkDao")
	private IPageLinkDao pageLinkDao;
	@Autowired
	@Qualifier("articleDao")
	private IArticleDao articleDao;
	@Autowired
	@Qualifier("hitDao")
	private IHitDao hitDao;
	@Autowired
	@Qualifier("authorDao")
	private IAuthorDao authorDao;
	@Autowired
	@Qualifier("copyFromDao")
	private ICopyFromDao fromDao;
	@Autowired
	@Qualifier("albumDao")
	private IAlbumDao albumDao;
	@Autowired
	@Qualifier("albumPhotoDao")
	private IPhotoDao photoDao;
	@Autowired
	@Qualifier("registryDao")
	private IRegistryDao registryDao;
	@Autowired
	@Qualifier("noteDao")
	private INoteDao noteDao;
	@Autowired
	@Qualifier("categoryUserDao")
	private ICategoryUserDao categoryUserDao;
	@Autowired
	@Qualifier("templateMobileDao")
	private ITemplateMobileDao templateMobileDao;
	@Autowired
	@Qualifier("channelMobileDao")
	private IChannelMobileDao channelMobileDao;
	@Autowired
	@Qualifier("categoryMobileDao")
	private ICategoryMobileDao categoryMobileDao;
	@Autowired
	@Qualifier("articleMobileDao")
	private IArticleMobileDao articleMobileDao;

	public Template getTemplate(long templateId) {
		return templateDao.getEntity(templateId);
	}

	/**
	 * 删除模板
	 * 
	 * @param templateId
	 *            模板主键
	 */
	public void deleteTemplate(Long templateId) {
		templateDao.delete(templateId);
	}

	/**
	 * 保存模板
	 * 
	 * @param o
	 *            模板
	 * @return
	 */
	public Template saveTemplate(Template o, String type) {
		o.setTemplateType(type);
		return templateDao.save(o);
	}

	public List<Template> getTemplateList(String account) {
		return templateDao.getTemplateList(account);
	}

	public List<Template> getTemplateList(String account, String isActive,
			boolean flag, Channel channel) {
		List<Template> list = templateDao.getTemplateList(account, isActive,
				flag, "CHANNEL");
		Template o = channel.getChannelTemplate();
		if (o != null) {
			list.remove(o);
			o.setSelected(true);
			list.add(o);
		}
		return list;
	}

	public List<Template> getTemplateList(String account, String isActive,
			boolean flag, Category category) {
		List<Template> list = templateDao.getTemplateList(account, isActive,
				flag, "CATEGORY");
		Template o = category.getCategoryTemplate();
		if (o != null) {
			list.remove(o);
			o.setSelected(true);
			list.add(o);
		}
		return list;

	}

	/**
	 * 取得模板 list
	 * 
	 * @param account
	 *            帐套
	 * @param isActive
	 *            活动
	 * @param flag
	 *            是否允许有空选取项
	 * @return
	 */
	public List<Template> getTemplateList(String account, String isActive,
			boolean flag) {
		return templateDao.getTemplateList(account, isActive, flag);
	}

	public List<Template> getTemplateList(Channel channel, String account,
			String isActive, boolean flag) {
		List<Template> templateList = templateDao.getTemplateList(account,
				isActive, flag);
		Template o = channel.getChannelTemplate();
		if (o != null) {
			o.setSelected(true);
			templateList.add(o);
		}
		return templateList;
	}

	public void getTemplatePage(Page<Template> page, String account,
			String queryName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Template a Where a.account= '" + account + "' ");
		if (StringUtil.isNotEmpty(queryName)) {
			sb.append(" And a.templateName like " + "'%" + queryName + "%'");
		}
		templateDao.getPage(page, "Select Count(*)" + sb.toString(),
				sb.toString() + " Order by a.templateName ");
	}

	public Channel getChannel(Long channelId) {
		return channelDao.loadEntity(channelId);
	}

	public Channel getChannel(String name, String value) {
		return channelDao.loadEntity(name, value);
	}

	/**
	 * 取得活动的频道list
	 * 
	 * @param account
	 *            　帐套
	 * @param isActive
	 *            　活动
	 * @return
	 */
	public List<Channel> getChannelList(String account, String isActive) {
		return channelDao.getChannelListByActive(account, isActive);
	}

	public List<Channel> getChannelList(String account, String isActive,
			Category category) {
		List<Channel> list = channelDao.getChannelListByActive(account,
				isActive);
		Channel o = category.getCategoryChannel();
		if (o != null) {
			list.remove(o);
			o.setSelected(true);
			list.add(o);
		}
		return list;
	}

	public void deleteChannel(Long channelId) {
		channelDao.delete(channelId);
	}

	/**
	 * Get Channel page
	 * 
	 * @param pageInfo
	 *            Page component
	 * @param account
	 *            Account
	 * @param queryChannelName
	 *            Query channel name
	 * @return
	 */
	public void getChannelPage(Page<Channel> page, String account,
			String queryChannelName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Channel a Where a.account= '" + account + "' ");
		if (StringUtil.isNotEmpty(queryChannelName)) {
			sb.append(" And a.channelName like '%" + queryChannelName + "%' ");
		}
		channelDao.getPage(page, "Select Count(*)" + sb.toString(),
				sb.toString() + " Order by a.channelOrder ");
	}

	/**
	 * 保存频道
	 * 
	 * @param o
	 *            频道
	 * @param templateId
	 *            模板
	 * @return
	 */
	public Channel saveChannel(User user, Channel o, Long templateId) {
		if (templateId != null)
			o.setChannelTemplate(templateDao.loadEntity(templateId));
		else
			o.setChannelTemplate(null);
		setPrimaryEntity(user, o);
		return channelDao.save(o);
	}

	public Category getCategory(Long categoryId) {
		return categoryDao.getEntity(categoryId);
	}

	/**
	 * 取得栏目列表
	 * 
	 * @param account
	 *            帐套
	 * @param isActive
	 *            活动
	 * @return
	 */
	public List<Category> getCategoryListByActive(String account,
			String isActive) {
		return categoryDao.getCategoryListByActive(account, isActive);
	}

	/**
	 * 取得栏目列表
	 * 
	 * @param queryChannelId
	 *            频道
	 * @return
	 */
	public List<Category> getCategoryList(Long channelId) {
		return categoryDao.getCategoryListByChannel(channelId);
	}

	/**
	 * 取得栏目列表:包含频道名称
	 * 
	 * @param account
	 *            帐套
	 * @param isActive
	 *            活动
	 * @return
	 */
	public List<Category> getCategoryListWithChannel(String account,
			String isActive) {
		List<Category> categoryList = categoryDao.getCategoryListByActive(
				account, isActive);
		for (Category c : categoryList)
			c.setCategoryName(c.getCategoryChannel().getChannelName() + "-"
					+ c.getCategoryName());
		return categoryList;
	}

	/**
	 * 取得栏目下拉列表JSON格式数据
	 * 
	 * @param channelId
	 *            频道
	 * @param isActive
	 *            　活动
	 * @return
	 */
	public String getCategoryDDL(Long userId, Long channelId) {
		StringBuffer sb = new StringBuffer();
		List<Category> categoryList = getCategoryListByUserChannel(userId,
				channelId);
		for (Category o : categoryList)
			if (sb.length() == 0)
				sb.append("[{\"value\":\"" + o.getCategoryId()
						+ "\",\"caption\":\"" + o.getCategoryName() + "\"}");
			else
				sb.append(",{\"value\":\"" + o.getCategoryId()
						+ "\",\"caption\":\"" + o.getCategoryName() + "\"}");
		if (sb.length() > 0)
			sb.append("]");
		else
			sb.append("[{}]");
		return sb.toString();
	}

	/**
	 * 根据频道取得栏目JSON格式数据
	 * 
	 * @param channelId
	 *            频道
	 * @return
	 */
	public String getCategoryJSON(Long channelId) {
		StringBuffer sb = new StringBuffer();
		List<Category> categoryList = categoryDao
				.getCategoryListByChannelActive(channelId, "1");
		for (Category o : categoryList) {
			if (sb.length() == 0)
				sb.append("[");
			else
				sb.append(",");
			sb.append("{\"categoryId\":\"" + o.getCategoryId()
					+ "\",\"categoryUrl\":\"" + o.getCategoryUrl()
					+ "\",\"categoryName\":\"" + o.getCategoryName() + "\"}");
		}
		if (sb.length() > 0)
			sb.append("]");
		return sb.toString();
	}

	public void deleteCategory(Long categoryId) {
		categoryDao.delete(categoryId);
	}

	/**
	 * Save category
	 * 
	 * @param o
	 *            Category instance
	 * @param channelId
	 *            Channel primary key
	 * @param templateId
	 *            Category template primary key
	 * @param articleTemplateId
	 *            Category article template primary key
	 * @return
	 */
	public Category saveCategory(User user, Category o, Long channelId,
			Long templateId, Long articleTemplateId) {
		if (channelId != null)
			o.setCategoryChannel(channelDao.loadEntity(channelId));
		else
			o.setCategoryChannel(null);
		if (templateId != null)
			o.setCategoryTemplate(templateDao.loadEntity(templateId));
		else
			o.setCategoryTemplate(null);
		if (articleTemplateId != null)
			o.setCategoryArticleTemplate(templateDao
					.loadEntity(articleTemplateId));
		else
			o.setCategoryArticleTemplate(null);
		setPrimaryEntity(user, o);
		return categoryDao.save(o);
	}

	/**
	 * Get category page
	 * 
	 * @param pageInfo
	 *            Page component
	 * @param account
	 *            Account
	 * @param queryChannelId
	 *            Channel primary key
	 * @param queryName
	 *            Category primary key
	 * @return
	 */
	public void getCategoryPage(Page<Category> page, String account,
			Long queryChannelId, String queryName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Category a Where a.account= '" + account + "' ");
		if (queryChannelId != null)
			sb.append(" And a.categoryChannel.channelId =" + queryChannelId);
		if (StringUtil.isNotEmpty(queryName))
			sb.append(" And a.categoryName like " + "'%" + queryName + "%' ");
		categoryDao.getPage(page, "Select Count(*)" + sb.toString(),
				sb.toString() + " Order by a.categoryOrder ");
	}

	/**
	 * 预处理META
	 * 
	 * @param o
	 * @param metaId
	 * @return
	 */

	public Meta prepareMeta(Meta o, Long metaId) {
		if (metaId == null)
			o = new Meta();
		else
			o = metaDao.loadEntity(metaId);
		return o;

	}

	public Meta getMeta(Long metaId) {
		return metaDao.loadEntity(metaId);
	}

	public void deleteMeta(Long metaId) {
		metaDao.delete(metaId);
	}

	public Meta saveMeta(Meta o) {
		return metaDao.save(o);
	}

	public List<Meta> getMetaList(String account) {
		return metaDao.getMetaList(account);
	}

	public void getMetaPage(Page<Meta> page, String account,
			String queryMetaKey, String queryMetaValue) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Meta a Where a.account= '" + account + "' ");
		if (StringUtil.isNotEmpty(queryMetaKey))
			sb.append(" And a.metaKey like '%" + queryMetaKey + "%'");
		if (StringUtil.isNotEmpty(queryMetaValue))
			sb.append(" And a.metaValue like '%" + queryMetaValue + "%'");
		metaDao.getPage(page, " Select Count(*) " + sb.toString(),
				sb.toString() + " Order by a.metaKey ");
	}

	/**
	 * 预处理友情链接
	 * 
	 * @param o
	 * @param linkId
	 * @return
	 */

	public Link prepareLink(Link o, Long linkId) {
		if (linkId == null)
			o = new Link();
		else
			o = linkDao.loadEntity(linkId);
		return o;
	}

	/**
	 * 保存友情链接
	 * 
	 * @param o
	 * @return
	 */
	public Link saveLink(Link o) {
		return linkDao.save(o);
	}

	/**
	 * 删除友情链接
	 * 
	 * @param linkId
	 */
	public void deleteLink(Long linkId) {
		if (linkId != null)
			linkDao.delete(linkId);
	}

	/**
	 * 取得友情链接 page
	 * 
	 * @param pageInfo
	 * @param account
	 * @return
	 */

	public PageInfo<Link> getLinkPage(PageInfo<Link> pageInfo, String account) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Link a Where a.account= '" + account + "' ");
		pageInfo = linkDao.listQuery(pageInfo,
				" Select Count(*) " + sb.toString(), sb.toString());
		return pageInfo;
	}

	/**
	 * 预处理页面元素
	 * 
	 * @param o
	 * @param linkId
	 * @param parentId
	 * @return
	 */

	public PageLink preparePageLink(PageLink o, Long linkId, Long parentId) {
		if (linkId == null) {
			o = new PageLink();
			if (parentId != null)
				o.setLinkParent(pageLinkDao.loadEntity(parentId));
		} else
			o = pageLinkDao.loadEntity(linkId);
		return o;
	}

	/**
	 * 根据文章,取得页面元素
	 * 
	 * @param articleId
	 *            文章
	 * @return
	 */

	public PageLink getLinkByArticle(Long articleId) {
		return pageLinkDao.getLinkByArticle(articleId);
	}

	/**
	 * 保存页面元素
	 * 
	 * @param o
	 * @return
	 */
	public PageLink saveLink(PageLink o) {
		return pageLinkDao.save(o);
	}

	/**
	 * 保存页面元素
	 * 
	 * @param account
	 *            帐套
	 * 
	 * @param o
	 *            页面
	 * @param channelId
	 *            频道
	 * @param categoryId
	 *            栏目
	 * @param albumId
	 *            像集
	 * @param parentId
	 *            父元素
	 * @param articleId
	 *            文章
	 * @return
	 */
	public PageLink savePageLink(String account, PageLink o, Long parentId,
			Long channelId, Long categoryId, Long albumId, Long articleId) {
		if (parentId != null)
			o.setLinkParent(pageLinkDao.loadEntity(parentId));
		else
			o.setLinkParent(null);
		if (channelId != null)
			o.setLinkChannel(channelDao.loadEntity(channelId));
		else
			o.setLinkChannel(null);
		if (categoryId != null)
			o.setLinkCategory(categoryDao.loadEntity(categoryId));
		else
			o.setLinkCategory(null);
		if (albumId != null)
			o.setLinkAlbum(albumDao.loadEntity(albumId));
		else
			o.setLinkAlbum(null);
		if (articleId != null) {
			o.setLinkArticle(articleDao.loadEntity(articleId));
			List<PageLink> linkList = new ArrayList<PageLink>();
			linkList = getPageLinkRootList(account, linkList, o);
			if (linkList.size() > 0)
				o = replacePageLink(linkList, o);
		} else {
			o.setLinkArticle(null);
		}
		return pageLinkDao.save(o);
	}

	/**
	 * 取得当前link结点所在的“自定义页面的元素”,当前方法没有出现在Interface中
	 * 
	 * @param account
	 *            帐套
	 * @param linkList
	 *            结点 list
	 * @param o
	 * @return
	 */
	public List<PageLink> getPageLinkRootList(String account,
			List<PageLink> linkList, PageLink o) {
		if (o.getLinkParent() != null) {
			List<PageLink> pList = pageLinkDao.getLinkListByParent(account, o
					.getLinkParent().getLinkId());
			if (pList.size() > 0)
				linkList.addAll(0, pList);
			return getPageLinkRootList(account, linkList, o.getLinkParent());
		} else
			return linkList;

	}

	/**
	 * 发现有相当标签的 PageLink，进行替换操作,当前方法没有出现在Interface中
	 * 
	 * @param linkList
	 *            当前自定义页面下的list
	 * @param o
	 *            当前结点
	 */
	public PageLink replacePageLink(List<PageLink> linkList, PageLink o) {
		for (PageLink p : linkList) {
			if (o.getLinkLabelCode().equals(p.getLinkLabelCode())) {
				p.setLinkArticle(o.getLinkArticle()); // 文章
				p.setLinkName(o.getLinkName()); // 标题
				p.setLinkParent(o.getLinkParent()); // 父结点
				p.setLinkUrl(o.getLinkUrl()); // 文章链接
				p.setLinkImageUrl(o.getLinkImageUrl()); // 文章图片链接
				o = p;
			}
		}
		return o;
	}

	/**
	 * 删除页面元素
	 * 
	 * @param o
	 */
	public void deletePageLink(Long linkId) {
		pageLinkDao.delete(linkId);
	}

	/**
	 * 以JSON格式取得页面结构树
	 * 
	 * @param fid
	 *            根节点
	 * @return
	 */

	public String getLinkTree(Long fid) {
		StringBuffer sb = new StringBuffer("[");
		PageLink link = pageLinkDao.getEntity(fid);
		sb.append("{\"linkId\":\"" + link.getLinkId()
				+ "\",\"linkFid\":\"\",\"linkName\":\"" + link.getLinkName()
				+ "\"}");
		// 子节点
		List<PageLink> linkList = new ArrayList<PageLink>();
		sb = linkChildren(fid, 0, sb, linkList);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * 取当前节点的所有子节点:应用递规算法取得子节点:该方法没有出现在Interface中
	 * 
	 * @param id
	 *            当前的link主键
	 * @param lastIndex
	 *            当前link位置
	 * @param sb
	 *            JSON格式的link
	 * @param linkList
	 * @return
	 */

	public StringBuffer linkChildren(Long id, Integer lastIndex,
			StringBuffer sb, List<PageLink> linkList) {
		List<PageLink> linkChildren = pageLinkDao.getEntity(id)
				.getLinkChildren();
		if (linkChildren.size() > 0)
			linkList.addAll(lastIndex, linkChildren);
		if (lastIndex < linkList.size()) {
			PageLink o = linkList.get(lastIndex);
			Article a = o.getLinkArticle();
			String linkName = "";
			// 如果是文章取文章Title
			if (a != null)
				linkName = a.getArticleTitle();
			else
				linkName = o.getLinkName();
			sb.append(",{\"linkId\":\"" + o.getLinkId() + "\",\"linkFid\":\""
					+ o.getLinkParent().getLinkId() + "\",\"linkName\":\""
					+ linkName + "\"}");
			lastIndex++;
			return linkChildren(linkList.get(lastIndex - 1).getLinkId(),
					lastIndex, sb, linkList);
		} else
			return sb;
	}

	/**
	 * 根据父ID取得list
	 * 
	 * @param fid
	 *            父ID
	 * @return
	 */

	public List<PageLink> getLinkListByParent(String account, Long id) {
		return pageLinkDao.getLinkListByParent(account, id);
	}

	/**
	 * parse 自定义页面
	 * 
	 * @param linkId
	 *            页面元素主键
	 * @param account
	 * @throws Exception
	 */
	public void parsePageLink(Long linkId, String cityName) throws Exception {
		if (linkId != null) {
			PageLink o = pageLinkDao.loadEntity(linkId);
			// 一级页面
			if (o.getLinkParent() == null) {
				// 频道
				if (o.getLinkChannel() != null)
					parseChannel(o.getLinkChannel(), null, cityName);
				// 栏目
				if (o.getLinkCategory() != null)
					parseCategory(o.getLinkCategory(), null, cityName);
				// 像集
				if (o.getLinkAlbum() != null)
					parseAlbum(o.getLinkAlbum().getAlbumId());
			}
		}
	}

	/**
	 * 取得链接页面根 list
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	public List<PageLink> getLinkRootList(String account) {
		return pageLinkDao.getLinkRootList(account);
	}

	/**
	 * 根据顶级页面元素,取得页面元素 list,无文章列表
	 * 
	 * @param parentId
	 *            顶级页面元素
	 * @return
	 */

	public List<PageLink> getPageLinkListByParent(Long parentId) {
		List<PageLink> linkList = new ArrayList<PageLink>();
		linkList = pageLinkDao.getLinkListByRoot(parentId, 0, linkList);
		List<PageLink> tempList = new ArrayList<PageLink>();
		for (PageLink o : linkList)
			if (o.getLinkArticle() == null)
				tempList.add(o);
		// System.out.println("linkList: " + linkList.size() + " tempList: "
		// + tempList.size());
		linkList = tempList;
		return linkList;
	}

	/**
	 * 根据顶级页面元素,取得页面元素 JSON,无文章列表
	 * 
	 * @param parentId
	 *            顶级页页元素主键
	 * @return
	 */

	public String getPageLinkDDL(Long parentId) {
		StringBuffer sb = new StringBuffer();
		List<PageLink> linkList = new ArrayList<PageLink>();
		linkList = pageLinkDao.getLinkListByRoot(parentId, 0, linkList);
		for (PageLink o : linkList)
			if (sb.length() == 0)
				sb.append("[{\"value\":\"" + o.getLinkId()
						+ "\",\"caption\":\"" + o.getLinkName() + "\"}");
			else
				sb.append(",{\"value\":\"" + o.getLinkId()
						+ "\",\"caption\":\"" + o.getLinkName() + "\"}");
		if (sb.length() > 0)
			sb.append("]");
		else
			sb.append("[{}]");
		return sb.toString();
	}

	/**
	 * 根据照片,取得页面元素list
	 * 
	 * @param photoId
	 *            照片
	 * @return
	 */

	public List<PageLink> getPageLinkListByPhoto(Long photoId) {
		List<PageLink> linkList = new ArrayList<PageLink>();
		AlbumPhoto photo = photoDao.loadEntity(photoId);
		PageLink linkRoot = photo.getPhotoAlbum().getAlbumLink();
		if (linkRoot != null) {
			linkChildren(linkRoot.getLinkId(), 0, linkList, true);
			linkList.add(0, linkRoot);
		}
		return linkList;

	}

	/**
	 * 根据文章,取得页面元素list
	 * 
	 * @param channel
	 *            频道
	 * @return
	 */

	public List<PageLink> getPageLinkListByChannel(Channel channel) {
		List<PageLink> linkList = new ArrayList<PageLink>();
		PageLink linkRoot = channel.getChannelLink();
		if (linkRoot != null) {
			linkChildren(linkRoot.getLinkId(), 0, linkList, true);
			linkList.add(0, linkRoot);
		}
		return linkList;
	}

	/**
	 * 根据栏目，取得页面元素list
	 * 
	 * @param category
	 *            栏目
	 * @param flag
	 *            标识:是否文章节点
	 * @return
	 */

	public List<PageLink> getLinkListByCategory(Category category) {
		List<PageLink> linkList = new ArrayList<PageLink>();
		PageLink linkRoot = category.getCategoryLink();
		if (linkRoot != null) {
			linkChildren(linkRoot.getLinkId(), 0, linkList, true);
			linkList.add(0, linkRoot);
		}
		return linkList;
	}

	/**
	 * 根据像集，取得页面元素list
	 * 
	 * @param album
	 *            像集
	 * @return
	 */
	public List<PageLink> getLinkListByAlbum(Album album) {
		List<PageLink> linkList = new ArrayList<PageLink>();
		PageLink linkRoot = album.getAlbumLink();
		if (linkRoot != null) {
			linkChildren(linkRoot.getLinkId(), 0, linkList, false);
			linkList.add(0, linkRoot);
		}
		return linkList;
	}

	/**
	 * 取当前节点的所有子节点:应用递规算法取得子节点:该方法没有出现在Interface中
	 * 
	 * @param linkId
	 *            当前的link主键
	 * @param lastIndex
	 *            当前link位置
	 * @param linkList
	 *            节点
	 * @param flag
	 *            是否加入文章结点
	 * @return linkList
	 */

	public List<PageLink> linkChildren(Long linkId, Integer lastIndex,
			List<PageLink> linkList, boolean flag) {
		PageLink linkCurrent = pageLinkDao.getEntity(linkId);
		List<PageLink> linkChildren = linkCurrent.getLinkChildren();
		if (linkChildren.size() > 0) {
			int i = lastIndex;
			for (PageLink l : linkChildren) {
				if (!flag && l.getLinkArticle() == null) {
					// 重构linkName
					l.setLinkName(linkCurrent.getLinkName() + "-"
							+ l.getLinkName());
					linkList.add(i, l);
					i++;

				} else if (flag && l.getLinkArticle() != null) {
					linkList.add(i, l);
					i++;
				}
				// i++;
			}
			// linkList.addAll(lastIndex, linkChildren);
		}
		if (lastIndex < linkList.size()) {
			lastIndex++;
			return linkChildren(linkList.get(lastIndex - 1).getLinkId(),
					lastIndex, linkList, flag);
		}
		return linkList;
	}

	/**
	 * 页面元素page
	 * 
	 * @param pageInfo
	 *            页
	 * @param account
	 *            帐套
	 * @param linkFid
	 *            父ID
	 * @param queryName
	 *            名称
	 * @return
	 */

	public PageInfo<PageLink> getLinkPage(PageInfo<PageLink> pageInfo,
			String account, Long linkFid, String queryName, String queryTitle) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From PageLink a Where a.account= '" + account
				+ "' And a.linkParent=" + linkFid);
		if (StringUtil.isNotEmpty(queryName))
			sb.append(" And a.linkName like '%" + queryName + "%'");
		if (StringUtil.isNotEmpty(queryTitle))
			sb.append(" And a.linkTitle like '%" + queryTitle + "%'");
		pageInfo = pageLinkDao.listQuery(pageInfo,
				" Select Count(*) " + sb.toString(), sb.toString()
						+ " Order by a.linkName ");
		return pageInfo;
	}

	public String getChannelCategoryTree(User user) {
		List<Channel> channelList = getChannelList(user.getAccount(), "1");
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		for (Channel o : channelList) {
			// Parent tree nodes
			TreeNode a = new TreeNode();
			a.setId(o.getChannelId());
			a.setName(o.getChannelName());
			a.setIsParent(true);
			// No check box or radio
			a.setNocheck(true);
			List<Category> categoryList = getCategoryList(a.getId());
			List<TreeNode> children = new ArrayList<TreeNode>();
			for (Category c : categoryList) {
				TreeNode b = new TreeNode();
				b.setPid(a.getId());
				b.setId(c.getCategoryId());
				b.setName(c.getCategoryName());
				b.setIsParent(false);
				b.setNocheck(false);
				children.add(b);
			}
			a.setChildren(children);
			treeNodes.add(a);
		}
		TreeNode root = new TreeNode();
		root.setName("我的栏目");
		root.setChildren(treeNodes);
		root.setIsParent(true);
		root.setNocheck(true);
		List<TreeNode> tree = new ArrayList<TreeNode>();
		tree.add(root);
		return JsonBuilder.toJson(tree);
	}

	public Article getArticle(Long articleId) {
		return articleDao.loadEntity(articleId);
	}

	/**
	 * 保存文章
	 * 
	 * @param o
	 *            文章
	 * @param articleContent
	 *            内容
	 * @param categoryId
	 *            栏目
	 * @param authorId
	 *            作者
	 * @param fromId
	 *            来源
	 * @return
	 */
	public Article saveArticle(Article o, String articleContent,
			Long categoryId, Long fromId) {
		if (categoryId != null)
			o.setArticleCategory(categoryDao.loadEntity(categoryId));
		else
			o.setArticleCategory(null);
		if (fromId != null)
			o.setArticleFrom(fromDao.loadEntity(fromId));
		else
			o.setArticleFrom(null);
		ArticleContent content = o.getArticleContent();
		content.setContentContent(articleContent);
		o.setArticleContent(content);
		return articleDao.save(o);
	}

	/**
	 * 保存文章点击次数
	 * 
	 * @param articleId
	 * @return
	 */
	public void saveArticleHit(Long articleId) {
		Article o = articleDao.loadEntity(articleId);
		o.setArticleHit(o.getArticleHit() + 1);
		o = articleDao.save(o);
	}

	/**
	 * 保存关联文章
	 * 
	 * @param primaryId
	 *            目标文章主键
	 * @param slaveId
	 *            关联文章主键
	 * @return
	 */
	public String saveArticleRelation(Long primaryId, Long slaveId) {
		Article o = articleDao.loadEntity(primaryId);
		String articleRelation = o.getArticleRelation();
		String flag = "0";
		if (articleRelation == null || "".equals(articleRelation)) {
			o.setArticleRelation(slaveId.toString());
			flag = "1";
		} else {
			articleRelation = "," + articleRelation + ",";
			if (articleRelation.indexOf("," + slaveId + ",") == -1) {
				o.setArticleRelation(articleRelation.substring(1,
						articleRelation.length()) + slaveId);
				flag = "1";
			} else {
				articleRelation = StringUtil.replaceAll(articleRelation, ","
						+ slaveId + ",", ",");
				o.setArticleRelation(articleRelation.substring(1,
						articleRelation.length() - 1));
			}
		}
		o = articleDao.save(o);
		if ("1".equals(flag))
			return "[{\"returnMsg\":\"增加成功!\"}]";
		else
			return "[{\"returnMsg\":\"删除成功!\"}]";
	}

	/**
	 * 保存文章像册
	 * 
	 * @param articleId
	 *            文章主键
	 * @param albumId
	 *            　像册主键
	 * @return
	 */
	public String saveArticleAlbum(Long articleId, Long albumId) {
		Article o = articleDao.loadEntity(articleId);
		String articleAlbum = o.getArticleAlbum();
		String flag = "0";
		if (articleAlbum == null || "".equals(articleAlbum)) {
			o.setArticleAlbum(albumId.toString());
			flag = "1";
		} else {
			articleAlbum = "," + articleAlbum + ",";
			if (articleAlbum.indexOf("," + albumId + ",") == -1) {
				o.setArticleAlbum(articleAlbum.substring(1,
						articleAlbum.length())
						+ albumId);
				flag = "1";
			} else {
				articleAlbum = StringUtil.replaceAll(articleAlbum, ","
						+ albumId + ",", ",");
				o.setArticleAlbum(articleAlbum.substring(1,
						articleAlbum.length() - 1));
			}
		}
		o = articleDao.save(o);
		if ("1".equals(flag))
			return "[{\"returnMsg\":\"增加成功!\"}]";
		else
			return "[{\"returnMsg\":\"删除成功!\"}]";
	}

	/**
	 * 删除文章:parse后的文章不允许删除
	 * 
	 * @param articleId
	 */
	public void deleteArticle(Long articleId) {
		Article o = articleDao.loadEntity(articleId);
		if (o != null)
			if ("0".equals(o.getArticleIsParse())) {
				HtmlParser.deleteHtml(o.getArticleLocation() + "/"
						+ o.getCreateTime() + ".html");
				articleDao.delete(articleId);
			}
	}

	/**
	 * 根据栏目取得JSON格式文章分页记录
	 * 
	 * @param categoryId
	 *            栏目
	 * @param pageNumber
	 *            页码
	 * @return
	 */

	public String getArticleJSONByCategory(Long categoryId, Integer pageNumber) {
		return articleDao.getArticleJSONByCategory(categoryId, pageNumber);
	}

	/**
	 * 取得文章 list
	 * 
	 * @param articleId
	 *            文章主键
	 * @param articleTitle
	 *            标题
	 * @param dateFrom
	 *            起始创建时间
	 * @param dateTo
	 *            终止创建时间
	 * @return
	 */

	public String getArticleListJSON(Long articleId, String articleTitle,
			String dateFrom, String dateTo) {
		StringBuffer sb = new StringBuffer();
		Article o = articleDao.loadEntity(articleId);
		String articleRelation = o.getArticleRelation();
		Category category = o.getArticleCategory();
		List<Article> articleList = articleDao.getArticleList(
				category.getCategoryId(), articleTitle,
				DateUtil.getUTCDate(dateFrom), DateUtil.getUTCDate(dateTo));
		if (StringUtils.isEmpty(articleRelation))
			articleRelation = "";
		else {
			List<Article> relationList = articleDao
					.getArticleListByRelation(articleRelation);
			for (Article a : relationList)
				if (articleList.contains(a))
					articleList.remove(a);
			articleList.addAll(0, relationList);
		}
		// 移除当前文章
		if (articleList.contains(o))
			articleList.remove(o);
		String checked = "0";
		for (Article b : articleList) {
			if (articleRelation.contains(b.getArticleId().toString()))
				checked = "1";
			else
				checked = "0";
			if (sb.length() == 0)
				sb.append("[");
			else
				sb.append(",");
			sb.append("{\"articleId\":\"" + b.getArticleId()
					+ "\",\"articleTitle\":\"" + b.getArticleTitle()
					+ "\",\"checked\":\"" + checked + "\",\"createTime\":\""
					+ b.getDate() + "\"}");
		}
		if (sb.length() > 0)
			sb.append("]");
		else
			sb.append("[{}]");
		return sb.toString();
	}

	/**
	 * 取得文章page
	 * 
	 * @param pageInfo
	 *            　页
	 * @param account
	 *            帐套
	 * @param categoryId
	 *            栏目
	 * @param queryTitle
	 *            　标题
	 * @param queryDateFrom
	 *            　创建开始日期
	 * @param queryDateToStringBuffer
	 *            sb = new StringBuffer();
	 *            sb.append(" From Article a Where a.account= '" + account +
	 *            "'"); 　创建结束日期
	 * @return
	 */

	public void getArticlePage(Page<Article> page, String account,
			Long channelId, Long categoryId, String queryTitle,
			String queryDateFrom, String queryDateTo) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Article a Where a.account= '" + account + "'");
		if (channelId != null)
			sb.append(" And a.articleCategory.categoryChannel.channelId="
					+ channelId);
		if (categoryId != null)
			sb.append(" And a.articleCategory.categoryId=" + categoryId);
		if (StringUtil.isNotEmpty(queryTitle))
			sb.append(" And a.articleTitle like '%" + queryTitle + "%'");
		if (StringUtil.isNotEmpty(queryDateFrom))
			sb.append(" And a.createTime >= "
					+ DateUtil.getUTCDate(queryDateFrom));
		if (StringUtil.isNotEmpty(queryDateTo))
			sb.append(" And a.createTime <= "
					+ DateUtil.getUTCDate(queryDateTo));
		articleDao.getPage(page, "Select Count(*)" + sb.toString(),
				sb.toString() + " Order by a.createTime desc ");
	}

	/**
	 * 保存点击文章点记录
	 * 
	 * @param articleId
	 *            文章主键
	 * @param ip
	 *            客户 IP
	 * @return
	 */
	public void saveHit(Long articleId, String hitIp) {
		ArticleHit o = new ArticleHit();
		o.setHitArticleId(articleId);
		o.setHitIp(hitIp);
		hitDao.save(o);
	}

	/**
	 * 预处理作者
	 * 
	 * @param o
	 * @param authorId
	 * @return
	 */

	public Author prepareAuthor(Author o, Long authorId) {
		if (authorId == null)
			o = new Author();
		else
			o = authorDao.loadEntity(authorId);
		return o;
	}

	/**
	 * 取得作者
	 * 
	 * @param authorId
	 *            作者
	 * @return
	 */

	public Author getAuthor(Long authorId) {
		return authorDao.loadEntity(authorId);
	}

	/**
	 * 保存作者
	 * 
	 * @param o
	 *            作者
	 * @return
	 */
	public Author saveAuthor(Author o) {
		return authorDao.save(o);
	}

	/**
	 * 删除作者
	 * 
	 * @param authorId
	 *            作者主键
	 */
	public void deleteAuthor(Long authorId) {
		if (authorId != null) {
			Author o = authorDao.loadEntity(authorId);
			if (o != null)
				authorDao.delete(o);
		}
	}

	/**
	 * 取得作者list
	 * 
	 * @param account
	 * @param flag
	 * @return
	 */

	public List<Author> getAuthorList(String account, boolean flag) {
		List<Author> authorList = authorDao.getAuthorList(account);
		if (flag) {
			Author o = new Author();
			o.setAuthorName(" --- none --- ");
			authorList.add(0, o);
		}
		return authorList;
	}

	/**
	 * 取得作者页
	 * 
	 * @param pageInfo
	 * @param account
	 *            帐套
	 * @param queryName
	 *            名称
	 * @return
	 */

	public PageInfo<Author> getAuthorPage(PageInfo<Author> pageInfo,
			String account, String queryName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Author a Where a.account= '" + account + "'");
		if (StringUtil.isNotEmpty(queryName))
			sb.append(" And a.authorName like '%" + queryName + "%'");
		pageInfo = authorDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.authorName ");
		return pageInfo;
	}

	/**
	 * 预处理来源
	 * 
	 * @param from
	 * @param fromId
	 * @return
	 */

	public CopyFrom prepareFrom(CopyFrom from, Long fromId) {
		if (fromId == null)
			from = new CopyFrom();
		else
			from = fromDao.loadEntity(fromId);
		return from;
	}

	/**
	 * 取得来源
	 * 
	 * @param fromId
	 *            来源
	 * @return
	 */

	public CopyFrom getCopyFrom(Long fromId) {
		return fromDao.loadEntity(fromId);
	}

	/**
	 * 保存来源
	 * 
	 * @param o
	 *            来源
	 * @return
	 */
	public CopyFrom saveCopyFrom(CopyFrom o) {
		return fromDao.save(o);
	}

	/**
	 * 删除来源
	 * 
	 * @param fromId
	 *            来源
	 */

	public void deleteCopyFrom(Long fromId) {
		fromDao.delete(fromId);
	}

	/**
	 * 取得来源list
	 * 
	 * @param account
	 *            帐套
	 * @param flag
	 *            是否包括 --- all --- 选项
	 * @return
	 */

	public List<CopyFrom> getCopyFromList(boolean flag) {
		return fromDao.getCopyFromList(flag);
	}

	/**
	 * 取得来源页
	 * 
	 * @param pageInfo
	 * @param queryName
	 *            来源名称
	 * @return
	 */

	public PageInfo<CopyFrom> getCopyFromPage(PageInfo<CopyFrom> pageInfo,
			String queryName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From CopyFrom a ");
		if (StringUtil.isNotEmpty(queryName))
			sb.append(" Where a.fromName like '%" + queryName + "%'");
		pageInfo = fromDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.fromName ");
		return pageInfo;
	}

	/**
	 * 把文章parse为HTML文件
	 * 
	 * @param o
	 *            文章
	 * @param articleId
	 *            文章主键
	 * @param cityName
	 *            城市
	 * @return
	 * @throws Exception
	 */
	public Article parseArticle(Article o, Long articleId, String cityName)
			throws Exception {
		if (o == null)
			o = articleDao.loadEntity(articleId);
		if ("0".equals(o.getArticleIsParse()))
			o.setArticleIsParse("1");
		String account = o.getAccount();
		// 用于替换导航中的信息：weaforce.com
		String website = StringUtil.getSiteFromEmail(o.getCreator());
		String articleHtmlPath = FileUtils.getArticleHtmlPath() + "/" + website;
		Category category = o.getArticleCategory();
		// 浏览品访问文章的URL
		String articleUrl = category.getCategoryChannel().getChannelUrl();
		String template = category.getCategoryArticleTemplate()
				.getTemplateContent();
		// 城市名称
		if (StringUtil.isNotEmpty(cityName))
			template = StringUtil
					.replaceAll(template, "{$CITYNAME$}", cityName);
		// 手工分配的相关Articles ID集合
		List<Article> relationList = articleDao.getArticleListByRelation(o
				.getArticleRelation());
		List<AlbumPhoto> photoList = photoDao.getPhotoListByAlbum(o
				.getArticleAlbum());
		template = HtmlParser.parseArticle(o, template, website,
				articleHtmlPath, articleUrl, metaDao.getMetaList(account),
				relationList, photoList);
		return articleDao.save(o);
	}

	/**
	 * 将栏目下的所有文章parse为HTML文件
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @throws Exception
	 */
	public void parseArticleAllByCategory(Long categoryId) throws Exception {
		Category category = categoryDao.loadEntity(categoryId);
		if (category != null) {
			String account = category.getAccount();
			String website = StringUtil.getSiteFromEmail(Security
					.getCurrentUserName());
			// 网站文件根目录 + 登录用户所属网站
			String articleHtmlPath = FileUtils.getArticleHtmlPath() + "/"
					+ website;
			// 浏览品访问文章的URL
			String articleUrl = category.getCategoryChannel().getChannelUrl();
			List<Article> articleList = articleDao
					.getArticleListByCategory(categoryId);
			String template = category.getCategoryArticleTemplate()
					.getTemplateContent();
			for (Article o : articleList) {
				List<Article> relationList = articleDao
						.getArticleListByRelation(o.getArticleRelation());
				List<AlbumPhoto> photoList = photoDao.getPhotoListByAlbum(o
						.getArticleAlbum());
				HtmlParser.parseArticle(o, template, website, articleHtmlPath,
						articleUrl, metaDao.getMetaList(account), relationList,
						photoList);
				articleDao.save(o);
			}
		}
	}

	/**
	 * unparse 文章,删除HTML 文件
	 * 
	 * @param article
	 *            文档
	 * @throws Exception
	 */

	public void unparseArticle(Long articleId) throws Exception {
		Article o = articleDao.loadEntity(articleId);
		if ("1".equals(o.getArticleIsParse()))
			o.setArticleIsParse("0");
		articleDao.save(o);
		HtmlParser.deleteHtml(o.getArticleLocation() + "/" + o.getCreateTime()
				+ ".html");
	}

	/**
	 * 把栏目下的所有文章parse为HTML文件
	 * 
	 * @param o
	 *            栏目
	 * @param categoryId
	 *            栏目主键
	 * @param cityName
	 *            城市
	 * @throws Exception
	 */
	public void parseCategory(Category o, Long categoryId, String cityName)
			throws Exception {
		if (o == null)
			o = categoryDao.loadEntity(categoryId);
		Channel channel = o.getCategoryChannel();
		String template = o.getCategoryTemplate().getTemplateContent();
		// 文档根目录+当前登录所属网站+频道目录
		String htmlFilePath = FileUtils.getArticleHtmlPath() + "/"
				+ StringUtil.getSiteFromEmail(Security.getCurrentUserName())
				+ o.getCategoryChannel().getChannelPath();
		String fileName = o.getCategoryParseName();
		String account = o.getAccount();
		String website = StringUtil.getSiteFromEmail(Security
				.getCurrentUserName());
		template = HtmlParser.parseBasicChannelCategory(channel, o, template,
				website, cityName);
		template = HtmlParser.replaceMeta(template,
				metaDao.getMetaList(account));
		// parse 基本信息
		template = HtmlParser.parseCategory(o, cityName,
				linkDao.getLinkList(0, 9), template);
		// 频道：如Weaforce首页
		if ("0".equals(o.getCategoryParseType())) {
			parseChannel(channel, null, cityName);
			// 类频道
		} else if ("1".equals(o.getCategoryParseType())) {
			// System.out.println("类频道");
			List<PageLink> linkList = getLinkListByCategory(o);
			template = HtmlParser.replaceArticleFromLink(template, linkList);
			HtmlParser.saveHtml(template, htmlFilePath, fileName + ".html");
			// 单文章栏目:内容来自于一个文章,直接保存至栏目所在的频道目录
		} else if ("2".equals(o.getCategoryParseType())) {
			List<Article> articleList = o.getCategoryArticle();
			// parse 文章
			Article article = articleList.get(0);
			String srcFile = articleList.get(0).getArticleLocation() + "/"
					+ article.getCreateTime() + ".html";
			// 检查文章是否存在
			if (!FileUtils.checkFile(srcFile))
				parseArticle(article, null, cityName);
			// 转存文章至栏目所在频道目录
			FileUtils.moveFile(srcFile, htmlFilePath, fileName);
			// 文章列表、图片文章列表
		} else if ("3".equals(o.getCategoryParseType())
				|| "4".equals(o.getCategoryParseType())) {
			List<Article> articleList = articleDao
					.getArticleListByCategoryParse(categoryId, "1");

			int articleListSize = articleList.size();
			// 每页最大文章数
			int maxPerPage = o.getCategoryMaxPerPage();
			// 页数
			int pageCount = HtmlParser
					.getPageCount(articleListSize, maxPerPage);
			// 当前栏目的parse后的文件
			String[] pageFile = HtmlParser.getCategoryPageFileName(
					new String[pageCount], o.getCategoryParseName(),
					articleListSize, o.getCategoryId(),
					o.getCategoryMaxPerPage());
			HtmlParser.parseCategoryArticle(o, template, articleList, pageFile,
					pageCount, htmlFilePath);
		}
	}

	/**
	 * 频道，首页是特殊频道,默认文件名是index.html,自动被parse到频道目录下,首页是特殊频道
	 * 
	 * @param o
	 *            频道
	 * @param channelId
	 *            频道主键
	 * @param cityName
	 *            城市
	 * @throws Exception
	 */
	public void parseChannel(Channel o, Long channelId, String cityName)
			throws Exception {
		if (o == null)
			o = channelDao.loadEntity(channelId);
		if (o.getChannelLink() != null) {
			// 频道模板
			String template = o.getChannelTemplate().getTemplateContent();
			// 城市
			template = StringUtil
					.replaceAll(template, "{$CITYNAME$}", cityName);
			// 频道名称
			template = StringUtil.replaceAll(template, "{$CHANNELNAME$}",
					o.getChannelName());
			// Channel Path已经包括web root
			String htmlFilePath = o.getChannelPath();
			String fileName = "index.html";
			// Meta信息
			template = HtmlParser.replaceMeta(template,
					metaDao.getMetaList(o.getAccount()));
			List<PageLink> linkList = getPageLinkListByChannel(o);
			template = HtmlParser.replaceArticleFromLink(template, linkList);
			HtmlParser.saveHtml(template, htmlFilePath, fileName);
		}
	}

	/**
	 * parse像册
	 * 
	 * @param albumId
	 *            像册
	 * @throws Exception
	 */

	public void parseAlbum(Long albumId) throws Exception {
		if (albumId != null) {
			Album album = albumDao.loadEntity(albumId);
			// 频道(单一页面)
			if ("0".equals(album.getAlbumParseType())) {
				if (album.getAlbumLink() != null) {
					String template = album.getAlbumTemplate()
							.getTemplateContent();
					String htmlFilePath = album.getAlbumPath();
					String fileName = album.getAlbumFileName();
					template = HtmlParser.replaceMeta(template,
							metaDao.getMetaList(album.getAccount()));
					List<PageLink> linkList = getLinkListByAlbum(album);
					for (PageLink l : linkList) {
						AlbumPhoto p = l.getLinkPhoto();
						template = replacePhoto(template, p, l);
					}
					HtmlParser.saveHtml(template, htmlFilePath, fileName);
				}
			}
		}
	}

	/**
	 * 替换模板中关于像片的信息：该方法没有出现在Interface中
	 * 
	 * @param template
	 *            模板
	 * @param p
	 *            像片
	 * @param l
	 *            页面元素
	 * @return
	 */
	public String replacePhoto(String template, AlbumPhoto p, PageLink l) {
		if (p != null) {
			// src
			template = StringUtil
					.replaceAll(template, "{$P" + l.getLinkLabelCode() + "R$}",
							p.getPhotoUrlResize());
			// <a>
			template = StringUtil.replaceAll(template,
					"{$P" + l.getLinkLabelCode() + "A$}", p.getPhotoUrl());
		}
		return template;
	}

	/**
	 * 取得像册
	 * 
	 * @param albumId
	 *            像册
	 * @return
	 */

	public Album getAlbum(Long albumId) {
		return albumDao.loadEntity(albumId);
	}

	/**
	 * 预处理像册
	 * 
	 * @param o
	 * @param albumId
	 * @return
	 */

	public Album prepareAlbum(Album o, Long albumId) {
		if (albumId == null)
			o = new Album();
		else
			o = albumDao.loadEntity(albumId);
		return o;
	}

	/**
	 * 保存像册
	 * 
	 * @param o
	 *            像册
	 * @return
	 */
	public Album saveAlbum(Album o) {
		return albumDao.save(o);
	}

	/**
	 * 保存像册
	 * 
	 * @param o
	 *            像册
	 * @param parentId
	 *            父亲
	 * @param templateId
	 *            模板
	 * @return
	 */

	public Album saveAlbum(Album o, Long parentId, Long templateId) {
		if (parentId != null)
			o.setAlbumParent(albumDao.loadEntity(parentId));
		else
			o.setAlbumParent(null);
		if (templateId != null)
			o.setAlbumTemplate(templateDao.loadEntity(templateId));
		else
			o.setAlbumTemplate(null);
		return albumDao.save(o);
	}

	/**
	 * 删除像册
	 * 
	 * @param o
	 *            像册
	 */
	public void deleteAlbum(Album o) {
		albumDao.delete(o);
	}

	/**
	 * 删除像册
	 * 
	 * @param o
	 *            像册
	 */

	public void deleteAlbum(Long albumId) {
		if (albumId != null) {
			Album o = albumDao.loadEntity(albumId);
			if (o != null && o.getAlbumChildren().size() == 0)
				albumDao.delete(o);
		}
	}

	/**
	 * 取得像册list
	 * 
	 * @param account
	 *            帐套
	 * @param flag
	 *            是否允许有null选项
	 * @return
	 */

	public List<Album> getAlbumList(String account, boolean flag) {
		return albumDao.getAlbumList(account, flag);
	}

	/**
	 * 根据文章,以JSON格式取得像册 list
	 * 
	 * @param account
	 *            帐套
	 * @param articleId
	 *            文章主键
	 * @param albumName
	 *            　像册名称
	 * @param dateFrom
	 *            　起始时间
	 * @param dateTo
	 *            　终止时间
	 * @return
	 */

	public String getAlbumListJSONByArticle(String account, Long articleId,
			String albumName, String dateFrom, String dateTo) {
		Article o = articleDao.loadEntity(articleId);
		String albumIds = o.getArticleAlbum();
		return albumDao.getAlbumListJSON(account, albumIds, albumName,
				dateFrom, dateTo);
	}

	/**
	 * 取得像册page
	 * 
	 * @param pageInfo
	 *            页
	 * @param account
	 *            帐套
	 * @param queryName
	 *            名称
	 * @return
	 */

	public PageInfo<Album> getAlbumPage(PageInfo<Album> pageInfo,
			String account, String queryName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Album a Where a.account= '" + account + "' ");
		if (StringUtil.isNotEmpty(queryName))
			sb.append(" And a.albumName like '%" + queryName + "%'");
		pageInfo = albumDao.listQuery(pageInfo,
				" Select Count(*) " + sb.toString(), sb.toString()
						+ " Order by a.albumOrder ");
		return pageInfo;
	}

	/**
	 * 预处理像片
	 * 
	 * @param o
	 * @param photoId
	 * @param albumId
	 * @return
	 */

	public AlbumPhoto preparePhoto(AlbumPhoto o, Long photoId, Long albumId) {
		if (photoId == null) {
			o = new AlbumPhoto();
			if (albumId != null)
				o.setPhotoAlbum(albumDao.loadEntity(albumId));
		} else
			o = photoDao.loadEntity(photoId);
		return o;
	}

	/**
	 * 取得照片
	 * 
	 * @param photoId
	 *            照片
	 * @return
	 */

	public AlbumPhoto getPhoto(Long photoId) {
		return photoDao.loadEntity(photoId);
	}

	/**
	 * 保存照片
	 * 
	 * @param o
	 *            照片
	 * @return
	 */
	public AlbumPhoto savePhoto(AlbumPhoto o) {
		return photoDao.save(o);
	}

	/**
	 * 上传照片
	 * 
	 * @param albumId
	 *            像册
	 * @param uploadFileName
	 *            照片文件名称
	 * @param uploads
	 *            照片文件
	 * @param photoNames
	 *            照片名称
	 * @param photoWidths
	 *            照片宽度
	 * @param photoHeights
	 *            照片高度
	 * @throws IOException
	 */
	public void uploadPhoto(Long albumId, List<String> uploadFileName,
			List<java.io.File> uploads, List<String> photoNames,
			List<Integer> photoWidths, List<Integer> photoHeights)
			throws IOException {
		for (String fileName : uploadFileName) {
			int fileIndex = uploadFileName.indexOf(fileName);
			AlbumPhoto o = new AlbumPhoto();
			o.setPhotoAlbum(albumDao.loadEntity(albumId));
			o.setPhotoFileName(fileName);
			o.setPhotoName(photoNames.get(fileIndex));
			o.setPhotoWidth(photoWidths.get(fileIndex));
			o.setPhotoHeight(photoHeights.get(fileIndex));
			o = photoDao.save(o);
			String extension = FileUtils.getExtension(fileName).toLowerCase();
			String fileServerName = com.weaforce.core.util.Global.FILE_TEMPLATE
					.substring(0, 20 - o.getPhotoId().toString().length())
					+ o.getPhotoId() + "." + extension;
			o.setPhotoLocation(FileUtils.getPhotoPath() + "/" + fileServerName);
			o.setPhotoUrl(FileUtils.getPhotoUrl() + "/" + fileServerName);
			final java.io.File src = uploads.get(fileIndex);
			final java.io.File dst = new java.io.File(o.getPhotoLocation());
			// 完全保存文件信息至DB后,再执行上传
			org.apache.commons.io.FileUtils.copyFile(src, dst);
			/* ------------------- 调整图片尺寸 ------------------- */
			int type = ImageUtil.IMAGE_UNKNOWN;
			if (extension.equals("jpg") || extension.equals("jpeg"))
				type = ImageUtil.IMAGE_JPEG;
			else if (extension.equals("gif") || extension.equals("png"))
				type = ImageUtil.IMAGE_PNG;
			if (type != ImageUtil.IMAGE_UNKNOWN) {
				String imageResizeFile = FileUtils.getPhotoPath() + "/resize/"
						+ fileServerName;
				org.apache.commons.io.FileUtils.copyFile(src, new java.io.File(
						imageResizeFile));
				BufferedImage image = ImageUtil.resizeImage(imageResizeFile,
						type, o.getPhotoWidth(), o.getPhotoHeight());
				ImageUtil.saveImage(image, imageResizeFile, type);
			}
			/* ------------------- 调整图片尺寸 ------------------- */
			o.setPhotoLocationResize(FileUtils.getPhotoPath() + "/resize/"
					+ fileServerName);
			o.setPhotoUrlResize(FileUtils.getPhotoUrl() + "/resize/"
					+ fileServerName);
			o = photoDao.save(o);
		}
	}

	/**
	 * 保存照片
	 * 
	 * @param o
	 *            照片
	 * @param albumId
	 *            像册
	 * @return
	 */
	public AlbumPhoto saveAlbum(AlbumPhoto o, Long albumId) {
		if (albumId != null)
			o.setPhotoAlbum(albumDao.loadEntity(albumId));
		else
			o.setPhotoAlbum(null);
		return photoDao.save(o);
	}

	/**
	 * 删除照片
	 * 
	 * @param photoId
	 *            照片
	 */
	public void deletePhoto(Long photoId) {
		AlbumPhoto o = photoDao.loadEntity(photoId);
		if (o != null) {
			photoDao.delete(o);
			FileUtils.deleteFile(o.getPhotoLocation());
			FileUtils.deleteFile(o.getPhotoLocationResize());
		}
	}

	/**
	 * 取得照片page
	 * 
	 * @param pageInfo
	 *            页
	 * @param account
	 *            帐套
	 * @param albumId
	 *            像集
	 * @param photoName
	 *            照片名称
	 * @return
	 */

	public PageInfo<AlbumPhoto> getPhotoPage(PageInfo<AlbumPhoto> pageInfo,
			String account, Long albumId, String photoName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From AlbumPhoto a Where a.account= '" + account + "' ");
		if (StringUtil.isNotEmpty(photoName))
			sb.append(" And a.photoName like '%" + photoName + "%'");
		if (albumId != null)
			sb.append(" And a.photoAlbum.albumId=" + albumId);
		pageInfo = photoDao.listQuery(pageInfo,
				" Select Count(*) " + sb.toString(), sb.toString()
						+ " Order by a.photoName ");
		return pageInfo;
	}

	/**
	 * 预处理注册
	 * 
	 * @param o
	 * @param registryId
	 * @return
	 */

	public Registry prepareRegistry(Registry o, Long registryId) {
		if (registryId == null)
			o = new Registry();
		else
			o = registryDao.loadEntity(registryId);
		return o;
	}

	/**
	 * 取得注册
	 * 
	 * @param registryId
	 * @return
	 */

	public Registry getRegistry(Long registryId) {
		return registryDao.loadEntity(registryId);
	}

	/**
	 * 根据登录，取得注册
	 * 
	 * @param registryLogin
	 * @return
	 */

	public Registry getRegistry(String registryLogin) {
		return registryDao.loadEntity("registryLogin", registryLogin);
	}

	/**
	 * 保存注册
	 * 
	 * @param o
	 * @return
	 */
	public Registry saveRegistry(Registry o) {
		o.setRegistryLogin(o.getRegistryLogin().toUpperCase());
		return registryDao.save(o);
	}

	/**
	 * 删除注册
	 * 
	 * @param registryId
	 */
	public void deleteRegistry(Long registryId) {
		registryDao.delete(registryId);
	}

	/**
	 * 注册用户登录
	 * 
	 * @param userLogin
	 *            用户
	 * @param userPassword
	 *            密码
	 * 
	 * @return
	 */

	public Registry registryLogin(String userLogin, String userPassword,
			String remoteIp) {
		Registry o = registryDao.getRegistryByLoginPassword(userLogin,
				MD5.toMD5(userPassword));
		if (o != null) {
			o.setLastLoginTime(System.currentTimeMillis());
			o.setLastLoginIp(remoteIp);
			registryDao.save(o);
		}
		return o;
	}

	/**
	 * 取得注册 page
	 * 
	 * @param pageInfo
	 * @param registryLogin
	 * @return
	 */

	public PageInfo<Registry> getRegistryPage(PageInfo<Registry> pageInfo,
			String registryLogin) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Registry a ");
		if (StringUtil.isNotEmpty(registryLogin))
			sb.append(" Where a.registryLogin like '%" + registryLogin + "%'");
		pageInfo = registryDao.listQuery(pageInfo,
				" Select Count(*) " + sb.toString(), sb.toString()
						+ " Order by a.registryLogin ");
		return pageInfo;
	}

	/**
	 * 保存登记用户的留言,并宣传期保存在Registry中的信息
	 * 
	 * @param noteTitle
	 *            标题
	 * @param noteContent
	 *            内容
	 * @param registryId
	 *            注册主键
	 * @param articleId
	 *            文章主键
	 * @param discountId
	 *            打主键
	 * @param tipId
	 *            小贴士主键
	 */
	public void saveNote(String noteTitle, String noteContent, Long registryId,
			Long articleId, Long discountId, Long tipId) {
		Note o = new Note();
		o.setNoteTitle(noteTitle);
		o.setNoteContent(noteContent);
		if (registryId != null)
			o.setNoteRegistryId(registryId);
		if (articleId != null)
			o.setNoteArticleId(articleId);
		if (discountId != null)
			o.setNoteDiscountId(discountId);
		if (tipId != null)
			o.setNoteTipId(tipId);
		Registry r = registryDao.loadEntity(registryId);
		o.setNoteRegistryShow(r.getRegistryShow());
		o.setNoteRegistryShowUrl(r.getRegistryShowUrl());
		noteDao.save(o);
	}

	/**
	 * 保存用户栏目
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @param userId
	 *            用户主键
	 * @return
	 */
	public CategoryUser saveCategoryUser(Long categoryId, Long userId) {
		CategoryUser o = new CategoryUser(categoryDao.loadEntity(categoryId),
				userDao.loadEntity(userId));
		return categoryUserDao.save(o);
	}

	/**
	 * 保存用户栏目
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @param userLogin
	 *            用户登录ID
	 * @return
	 */
	public CategoryUser saveCategoryUser(Long categoryId, String userLogin) {
		Category category = categoryDao.loadEntity(categoryId);
		System.out.println("category: " + category.getCategoryName());
		User user = getUserByLogin(userLogin);
		List<User> userList = category.getCategoryUser();
		userList.add(user);
		category.setCategoryUser(userList);
		categoryDao.save(category);
		CategoryUser o = new CategoryUser(category, user);
		// CategoryUser o = new CategoryUser(categoryDao.loadEntity(categoryId),
		// getUserByLogin(userLogin));
		o = categoryUserDao.save(o);
		return o;// getMenuList();
	}

	/**
	 * 删除栏目用户
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @param userLogin
	 *            用户登录ID
	 */
	public void deleteCategoryUser(Long categoryId, String userLogin) {
		CategoryUser o = categoryUserDao.getCategoryUserByCategoryUser(
				categoryId, userLogin);
		if (o != null)
			categoryUserDao.delete(o);
	}

	/**
	 * Set category to user
	 * 
	 * @param categoryId
	 *            Category primary key.
	 * @param userLogin
	 *            User login id
	 * @param flag
	 *            delete or save flag
	 */
	public void setCategoryToUser(Long categoryId, String userLogin,
			Integer flag) {
		if (flag == 0)
			deleteCategoryUser(categoryId, userLogin);
		if (flag == 1) {
			Category category = categoryDao.loadEntity(categoryId);
			User user = getUserByLogin(userLogin);
			List<User> userList = category.getCategoryUser();
			userList.add(user);
			category.setCategoryUser(userList);
			categoryDao.save(category);
		}
	}

	/**
	 * 根据用户主键取得用户栏目
	 * 
	 * @param userId
	 *            用戶主键
	 * @return
	 */

	public String getCategoryJSONByUserLogin(String userLogin) {
		StringBuffer sb = new StringBuffer("[");
		List<CategoryUser> categoryUserList = categoryUserDao
				.getCategoryUserListByUser(getUserByLogin(userLogin)
						.getUserId());
		for (CategoryUser o : categoryUserList)
			sb.append("{\"value\":\"" + o.getUserCategory().getCategoryId()
					+ "\",\"caption\":\""
					+ o.getUserCategory().getCategoryName() + "\"},");
		sb = new StringBuffer(StringUtil.cutLastChar(sb.toString(), ","));
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Get the category list by user
	 * 
	 * @param userId
	 *            User primary key
	 * @param channelId
	 *            Channel primary key
	 * @return
	 */

	public List<Category> getCategoryListByUserChannel(Long userId,
			Long channelId) {
		List<CategoryUser> categoryUserList = categoryUserDao
				.getCategoryUserListByUserChannel(userId, channelId);
		List<Category> categoryList = new ArrayList<Category>();
		for (CategoryUser o : categoryUserList)
			categoryList.add(o.getUserCategory());
		return categoryList;
	}

	/**
	 * Prepare template mobile
	 * 
	 * @param o
	 *            Template mobile
	 * @param templateId
	 *            Template mobile primary key
	 * @return
	 */

	public TemplateMobile prepareTemplateMobile(TemplateMobile o,
			Long templateId) {
		if (templateId == null)
			o = new TemplateMobile();
		else
			o = templateMobileDao.loadEntity(templateId);
		return o;
	}

	/**
	 * Save template mobile
	 * 
	 * @param o
	 *            Template mobile
	 * @return
	 */
	public TemplateMobile saveTemplateMobile(TemplateMobile o) {
		return templateMobileDao.save(o);
	}

	/**
	 * Delete template mobile
	 * 
	 * @param templateId
	 *            Template mobile primary key
	 */
	public void deleteTemplateMobile(Long templateId) {
		templateMobileDao.delete(templateId);
	}

	/**
	 * Get template page
	 * 
	 * @param pageInfo
	 *            Page component
	 * @param account
	 *            Account
	 * @param queryTemplateName
	 *            Query template by name
	 * @return
	 */

	public PageInfo<TemplateMobile> getTemplateMobilePage(
			PageInfo<TemplateMobile> pageInfo, String account,
			String queryTemplateName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From TemplateMobile a Where a.account= '" + account + "' ");
		if (StringUtil.isNotEmpty(queryTemplateName)) {
			sb.append(" And a.templateName like " + "'%" + queryTemplateName
					+ "%'");
		}
		pageInfo = templateMobileDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.templateName ");
		return pageInfo;
	}

	/**
	 * Prepare channel mobile
	 * 
	 * @param o
	 *            Channel mobile instance
	 * @param channelId
	 *            Channel mobile primary key
	 * @return
	 */

	public ChannelMobile prepareChannelMobile(ChannelMobile o, Long channelId) {
		if (channelId == null)
			o = new ChannelMobile();
		else
			o = channelMobileDao.loadEntity(channelId);
		return o;
	}

	/**
	 * Save channel mobile
	 * 
	 * @param o
	 *            Channel mobile instance
	 * @return
	 */
	public ChannelMobile saveChannelMobile(ChannelMobile o) {
		return channelMobileDao.save(o);
	}

	/**
	 * Delete channel mobile
	 * 
	 * @param channleId
	 *            Channel mobile primary key
	 */
	public void deleteChannelMobile(Long channleId) {
		channelMobileDao.delete(channleId);
	}

	/**
	 * Get channel mobile page
	 * 
	 * @param pageInfo
	 *            Page component
	 * @param account
	 *            Account
	 * @param queryName
	 *            Query channel name
	 * @return
	 */

	public PageInfo<ChannelMobile> getChannelMobilePage(
			PageInfo<ChannelMobile> pageInfo, String account, String queryName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From ChannelMobile a Where a.account= '" + account + "' ");
		if (StringUtil.isNotEmpty(queryName)) {
			sb.append(" And a.channelName like '%" + queryName + "%' ");
		}
		pageInfo = channelMobileDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.channelOrder ");
		return pageInfo;
	}

	/**
	 * Prepare category mobile
	 * 
	 * @param o
	 *            Category mobile instance
	 * @param categoryId
	 *            Category mobile primary key
	 * @param channelId
	 *            Channel mobile primary key
	 * @return
	 */

	public CategoryMobile prepareCategoryMobile(CategoryMobile o,
			Long categoryId, Long channelId) {
		if (categoryId == null)
			o = new CategoryMobile();
		else
			o = categoryMobileDao.loadEntity(categoryId);
		if (channelId == null)
			o.setCategoryChannel(null);
		else
			o.setCategoryChannel(channelMobileDao.loadEntity(channelId));
		return o;
	}

	/**
	 * Save category
	 * 
	 * @param o
	 *            Category instance
	 * @param channelId
	 *            Channel primary key
	 * @param templateId
	 *            Category template primary key
	 * @param articleTemplateId
	 *            Category article template primary key
	 * @return
	 */
	public CategoryMobile saveCategoryMobile(CategoryMobile o, Long channelId,
			Long templateId, Long articleTemplateId) {
		if (channelId != null)
			o.setCategoryChannel(channelMobileDao.loadEntity(channelId));
		else
			o.setCategoryChannel(null);
		if (templateId != null)
			o.setCategoryTemplate(templateMobileDao.loadEntity(templateId));
		else
			o.setCategoryTemplate(null);
		if (articleTemplateId != null)
			o.setCategoryArticleTemplate(templateMobileDao
					.loadEntity(articleTemplateId));
		else
			o.setCategoryArticleTemplate(null);
		return categoryMobileDao.save(o);
	}

	/**
	 * Get category page
	 * 
	 * @param pageInfo
	 *            Page component
	 * @param account
	 *            Account
	 * @param queryChannelId
	 *            Channel primary key
	 * @param queryName
	 *            Category primary key
	 * @return
	 */

	public PageInfo<CategoryMobile> getCategoryMobilePage(
			PageInfo<CategoryMobile> pageInfo, String account,
			Long queryChannelId, String queryName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From CategoryMobile a Where a.account= '" + account + "' ");
		if (queryChannelId != null)
			sb.append(" And a.categoryChannel.channelId =" + queryChannelId);
		if (StringUtil.isNotEmpty(queryName))
			sb.append(" And a.categoryName like " + "'%" + queryName + "%' ");
		pageInfo = categoryMobileDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.categoryOrder ");
		return pageInfo;
	}

	/**
	 * Prepare ArticleMobile instance
	 * 
	 * @param o
	 *            Article mobile instance
	 * @param mobileId
	 *            Primary key of article mobile instance
	 * @param categoryId
	 *            Category primary key
	 * @return
	 */

	public ArticleMobile prepareArticleMobile(ArticleMobile o, Long mobileId,
			Long categoryId) {
		if (mobileId == null)
			o = new ArticleMobile();
		else
			o = articleMobileDao.loadEntity(mobileId);
		if (categoryId != null)
			o.setMobileCategory(categoryDao.loadEntity(categoryId));

		return o;
	}

}
