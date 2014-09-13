package com.weaforce.cms.service;

import java.io.IOException;
import java.util.List;

import com.weaforce.cms.entity.Album;
import com.weaforce.cms.entity.AlbumPhoto;
import com.weaforce.cms.entity.Article;
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
import com.weaforce.cms.entity.PageLink;
import com.weaforce.cms.entity.Registry;
import com.weaforce.cms.entity.Template;
import com.weaforce.cms.entity.TemplateMobile;
import com.weaforce.core.util.PageInfo;
import com.weaforce.core.web.page.Page;
import com.weaforce.entity.admin.User;
import com.weaforce.system.service.ISystemService;

public interface ICmsService extends ISystemService {

	public Template getTemplate(long templateId);

	public List<Template> getTemplateList(String account);

	public List<Template> getTemplateList(String account,String isActive, boolean flag,
			Channel channel);

	public List<Template> getTemplateList(String account, String isActive,
			boolean flag, Category category);

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
			boolean flag);

	/**
	 * Get template list
	 * 
	 * @param channel
	 *            Channel
	 * @param account
	 *            Account
	 * @param isActive
	 *            If is active
	 * @param flag
	 * @return
	 */
	public List<Template> getTemplateList(Channel channel, String account,
			String isActive, boolean flag);

	/**
	 * Get template page
	 * 
	 * @param page
	 *            Page component
	 * @param account
	 *            Account
	 * @param queryName
	 *            Query template by name
	 * @return
	 */
	public void getTemplatePage(Page<Template> page, String account,
			String queryName);

	/**
	 * 删除模板
	 * 
	 * @param templateId
	 *            模板主键
	 */
	public void deleteTemplate(Long templateId);

	/**
	 * 保存模板
	 * 
	 * @param o
	 *            模板
	 * @param parentId
	 * @return
	 */

	public Template saveTemplate(Template o, String type);

	public Channel getChannel(Long channelId);

	public Channel getChannel(String name, String value);

	/**
	 * 删除频道
	 * 
	 * @param channelId
	 *            频道主键
	 */
	public void deleteChannel(Long channelId);

	/**
	 * 取得活动的频道list
	 * 
	 * @param account
	 *            　帐套
	 * @param isActive
	 *            　活动
	 * @return
	 */

	public List<Channel> getChannelList(String account, String isActive);

	public List<Channel> getChannelList(String account, String isActive,
			Category category);

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
			String queryChannelName);

	/**
	 * 保存频道
	 * 
	 * @param o
	 *            频道
	 * @param templateId
	 *            模板
	 * @return
	 */
	public Channel saveChannel(User user, Channel o, Long templateId);

	public Category getCategory(Long categoryId);

	public void deleteCategory(Long categoryId);

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
			String isActive);

	/**
	 * 取得栏目列表
	 * 
	 * @param queryChannelId
	 *            频道
	 * @return
	 */
	public List<Category> getCategoryList(Long channelId);

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
			String isActive);

	/**
	 * 取得栏目下拉列表JSON格式数据
	 * 
	 * @param channelId
	 *            频道
	 * @param isActive
	 *            　活动
	 * @return
	 */
	public String getCategoryDDL(Long userId, Long channelId);

	/**
	 * 根据频道取得栏目JSON格式数据
	 * 
	 * @param channelId
	 * @return
	 */

	public String getCategoryJSON(Long channelId);

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
			Long templateId, Long articleTemplateId);

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
			Long queryChannelId, String queryName);

	/**
	 * 预处理META
	 * 
	 * @param o
	 * @param metaId
	 * @return
	 */
	public Meta prepareMeta(Meta o, Long metaId);

	public Meta getMeta(Long metaId);

	public void deleteMeta(Long metaId);

	public Meta saveMeta(Meta o);

	public List<Meta> getMetaList(String account);

	public void getMetaPage(Page<Meta> page, String account,
			String queryMetaKey, String queryMetaValue);

	/**
	 * 预处理友情链接
	 * 
	 * @param o
	 * @param linkId
	 * @return
	 */
	public Link prepareLink(Link o, Long linkId);

	/**
	 * 保存友情链接
	 * 
	 * @param o
	 * @return
	 */
	public Link saveLink(Link o);

	/**
	 * 删除友情链接
	 * 
	 * @param linkId
	 */
	public void deleteLink(Long linkId);

	/**
	 * 取得友情链接 page
	 * 
	 * @param pageInfo
	 * @param account
	 * @return
	 */
	public PageInfo<Link> getLinkPage(PageInfo<Link> pageInfo, String account);

	/**
	 * 预处理页面元素
	 * 
	 * @param o
	 * @param linkId
	 * @param parentId
	 * @return
	 */
	public PageLink preparePageLink(PageLink o, Long linkId, Long parentId);

	/**
	 * 根据文章,取得页面元素
	 * 
	 * @param articleId
	 *            文章
	 * @return
	 */

	public PageLink getLinkByArticle(Long articleId);

	/**
	 * 保存页面元素
	 * 
	 * @param o
	 * @return
	 */

	public PageLink saveLink(PageLink o);

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
			Long channelId, Long categoryId, Long albumId, Long articleId);

	/**
	 * 删除页面元素
	 * 
	 * @param o
	 */

	public void deletePageLink(Long linkId);

	/**
	 * 以JSON格式取得页面结构树
	 * 
	 * @param fid
	 *            根节点
	 * @return
	 */

	public String getLinkTree(Long fid);

	/**
	 * 根据父ID取得list
	 * 
	 * @param fid
	 *            父ID
	 * @return
	 */
	public List<PageLink> getLinkListByParent(String account, Long id);

	/**
	 * 取得链接页面根 list
	 * 
	 * @param account
	 *            帐套
	 * @return
	 */
	public List<PageLink> getLinkRootList(String account);

	/**
	 * 根据顶级页面元素,取得页面元素 list,无文章列表
	 * 
	 * @param parentId
	 *            顶级页面元素
	 * @return
	 */
	public List<PageLink> getPageLinkListByParent(Long parentId);

	/**
	 * parse 自定义页面
	 * 
	 * @param linkId
	 *            页面元素主键
	 * @param account
	 * @throws Exception
	 */
	public void parsePageLink(Long linkId, String cityName) throws Exception;

	/**
	 * 根据顶级页面元素,取得页面元素 JSON,无文章列表
	 * 
	 * @param parentId
	 *            顶级页页元素主键
	 * @return
	 */

	public String getPageLinkDDL(Long parentId);

	/**
	 * 根据照片,取得页面元素list
	 * 
	 * @param photoId
	 *            照片
	 * @return
	 */
	public List<PageLink> getPageLinkListByPhoto(Long photoId);

	/**
	 * 根据文章,取得页面元素list
	 * 
	 * @param channel
	 *            频道
	 * @return
	 */

	public List<PageLink> getPageLinkListByChannel(Channel channel);

	/**
	 * 根据栏目，取得页面元素list
	 * 
	 * @param category
	 *            栏目
	 * @param flag
	 *            标识:是否文章节点
	 * @return
	 */
	public List<PageLink> getLinkListByCategory(Category category);

	/**
	 * 根据像集，取得页面元素list
	 * 
	 * @param album
	 *            像集
	 * @return
	 */
	public List<PageLink> getLinkListByAlbum(Album album);

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
			String account, Long linkFid, String queryName, String queryTitle);

	public String getChannelCategoryTree(User user);

	/**
	 * 取得文章
	 * 
	 * @param articleId
	 * @return
	 */
	public Article getArticle(Long articleId);

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
			Long categoryId, Long fromId);

	/**
	 * 保存文章点击次数
	 * 
	 * @param articleId
	 * @return
	 */
	public void saveArticleHit(Long articleId);

	/**
	 * 保存关联文章
	 * 
	 * @param primaryId
	 *            目标文章主键
	 * @param slaveId
	 *            关联文章主键
	 * @return
	 */
	public String saveArticleRelation(Long primaryId, Long slaveId);

	/**
	 * 保存文章像册
	 * 
	 * @param articleId
	 *            文章主键
	 * @param albumId
	 *            　像册主键
	 * @return
	 */
	public String saveArticleAlbum(Long articleId, Long albumId);

	/**
	 * 删除文章
	 * 
	 * @param articleId
	 */

	public void deleteArticle(Long articleId);

	/**
	 * 根据栏目取得JSON格式文章分页记录
	 * 
	 * @param categoryId
	 *            栏目
	 * @param pageNumber
	 *            页码
	 * @return
	 */
	public String getArticleJSONByCategory(Long categoryId, Integer pageNumber);

	/**
	 * 取得JSON格式文章 list
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
			String dateFrom, String dateTo);

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
	 * @param queryDateTo
	 *            　创建结束日期
	 * @return
	 */
	public void getArticlePage(Page<Article> page, String account,
			Long channelId, Long categoryId, String queryTitle,
			String queryDateFrom, String queryDateTo);

	/**
	 * 保存点击文章点记录
	 * 
	 * @param articleId
	 *            文章主键
	 * @param ip
	 *            客户 IP
	 * @return
	 */
	public void saveHit(Long articleId, String hitIp);

	/**
	 * 预处理作者
	 * 
	 * @param o
	 * @param authorId
	 * @return
	 */
	public Author prepareAuthor(Author o, Long authorId);

	/**
	 * 取得作者
	 * 
	 * @param authorId
	 *            作者
	 * @return
	 */
	public Author getAuthor(Long authorId);

	/**
	 * 保存作者
	 * 
	 * @param o
	 *            作者
	 * @return
	 */
	public Author saveAuthor(Author o);

	/**
	 * 删除作者
	 * 
	 * @param authorId
	 *            作者主键
	 */
	public void deleteAuthor(Long authorId);

	/**
	 * 取得作者list
	 * 
	 * @param account
	 * @param flag
	 * @return
	 */
	public List<Author> getAuthorList(String account, boolean flag);

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
			String account, String queryName);

	/**
	 * 预处理来源
	 * 
	 * @param from
	 * @param fromId
	 * @return
	 */
	public CopyFrom prepareFrom(CopyFrom from, Long fromId);

	/**
	 * 取得来源
	 * 
	 * @param fromId
	 *            来源
	 * @return
	 */
	public CopyFrom getCopyFrom(Long fromId);

	/**
	 * 保存来源
	 * 
	 * @param o
	 *            来源
	 * @return
	 */
	public CopyFrom saveCopyFrom(CopyFrom o);

	/**
	 * 删除来源
	 * 
	 * @param fromId
	 *            来源
	 */

	public void deleteCopyFrom(Long fromId);

	/**
	 * 取得来源list
	 * 
	 * @param flag
	 *            是否包括 --- all --- 选项
	 * @return
	 */

	public List<CopyFrom> getCopyFromList(boolean flag);

	/**
	 * 取得来源页
	 * 
	 * @param pageInfo
	 * @param queryName
	 *            来源名称
	 * @return
	 */

	public PageInfo<CopyFrom> getCopyFromPage(PageInfo<CopyFrom> pageInfo,
			String queryName);

	/**
	 * 把文章parse为HTML文件
	 * 
	 * @param articleId
	 *            文章主键
	 * @param cityName
	 *            城市名称
	 * @return
	 * @throws Exception
	 */
	public Article parseArticle(Article o, Long articleId, String cityName)
			throws Exception;

	/**
	 * 将栏目下的所有文章parse为HTML文件
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @throws Exception
	 */
	public void parseArticleAllByCategory(Long categoryId) throws Exception;

	/**
	 * unparse 文章,删除HTML 文件
	 * 
	 * @param article
	 *            文章
	 * @throws Exception
	 */

	public void unparseArticle(Long articleId) throws Exception;

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
			throws Exception;

	/**
	 * parse频道，首页是特殊频道
	 * 
	 * @param channelId
	 *            频道
	 * @param cityName
	 * @throws Exception
	 */

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
			throws Exception;

	/**
	 * parse像册子
	 * 
	 * @param albumId
	 *            像册
	 * @throws Exception
	 */

	public void parseAlbum(Long albumId) throws Exception;

	/**
	 * 取得像册
	 * 
	 * @param albumId
	 *            像册
	 * @return
	 */
	public Album getAlbum(Long albumId);

	/**
	 * 预处理像册
	 * 
	 * @param o
	 * @param albumId
	 * @return
	 */
	public Album prepareAlbum(Album o, Long albumId);

	/**
	 * 保存像册
	 * 
	 * @param o
	 *            像册
	 * @return
	 */
	public Album saveAlbum(Album o);

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

	public Album saveAlbum(Album o, Long parentId, Long templateId);

	/**
	 * 删除像册
	 * 
	 * @param o
	 *            像册
	 */
	public void deleteAlbum(Album o);

	/**
	 * 删除像册
	 * 
	 * @param o
	 *            像册
	 */

	public void deleteAlbum(Long albumId);

	/**
	 * 取得像册list
	 * 
	 * @param account
	 *            帐套
	 * @param flag
	 *            是否允许为null选项
	 * @return
	 */

	public List<Album> getAlbumList(String account, boolean flag);

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
			String albumName, String dateFrom, String dateTo);

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
			String account, String queryName);

	/**
	 * 预处理像片
	 * 
	 * @param o
	 * @param photoId
	 * @param albumId
	 * @return
	 */
	public AlbumPhoto preparePhoto(AlbumPhoto o, Long photoId, Long albumId);

	/**
	 * 取得照片
	 * 
	 * @param photoId
	 *            照片
	 * @return
	 */
	public AlbumPhoto getPhoto(Long photoId);

	/**
	 * 保存照片
	 * 
	 * @param o
	 *            照片
	 * @return
	 */
	public AlbumPhoto savePhoto(AlbumPhoto o);

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
			throws IOException;

	/**
	 * 保存照片
	 * 
	 * @param o
	 *            照片
	 * @param albumId
	 *            像册
	 * @return
	 */
	public AlbumPhoto saveAlbum(AlbumPhoto o, Long albumId);

	/**
	 * 删除照片
	 * 
	 * @param photoId
	 *            照片
	 */
	public void deletePhoto(Long photoId);

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
			String account, Long albumId, String photoName);

	/**
	 * 预处理注册
	 * 
	 * @param o
	 * @param registryId
	 * @return
	 */
	public Registry prepareRegistry(Registry o, Long registryId);

	/**
	 * 取得注册
	 * 
	 * @param registryId
	 * @return
	 */
	public Registry getRegistry(Long registryId);

	/**
	 * 根据登录，取得注册
	 * 
	 * @param registryLogin
	 * @return
	 */
	public Registry getRegistry(String registryLogin);

	/**
	 * 保存注册
	 * 
	 * @param o
	 * @return
	 */
	public Registry saveRegistry(Registry o);

	/**
	 * 删除注册
	 * 
	 * @param registryId
	 */
	public void deleteRegistry(Long registryId);

	/**
	 * 注册用户登录
	 * 
	 * @param userLogin
	 *            用户
	 * @param userPassword
	 *            密码
	 * @param remoteIp
	 *            最后登录IP
	 * @return
	 */

	public Registry registryLogin(String userLogin, String userPassword,
			String remoteIp);

	/**
	 * 取得注册 page
	 * 
	 * @param pageInfo
	 * @param registryLogin
	 * @return
	 */
	public PageInfo<Registry> getRegistryPage(PageInfo<Registry> pageInfo,
			String registryLogin);

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
			Long articleId, Long discountId, Long tipId);

	/**
	 * 保存用户栏目
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @param userId
	 *            用户主键
	 * @return
	 */
	public CategoryUser saveCategoryUser(Long categoryId, Long userId);

	/**
	 * 保存用户栏目
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @param userLogin
	 *            用户登录ID
	 * @return
	 */
	public CategoryUser saveCategoryUser(Long categoryId, String userLogin);

	/**
	 * 删除栏目用户
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @param userLogin
	 *            用户登录ID
	 */
	public void deleteCategoryUser(Long categoryId, String userLogin);

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
			Integer flag);

	/**
	 * 根据用户主键取得用户栏目
	 * 
	 * @param userId
	 *            用戶主键
	 * @return
	 */
	public String getCategoryJSONByUserLogin(String userLogin);

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
			Long channelId);

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
			Long templateId);

	/**
	 * Save template mobile
	 * 
	 * @param o
	 *            Template mobile
	 * @return
	 */
	public TemplateMobile saveTemplateMobile(TemplateMobile o);

	/**
	 * Delete template mobile
	 * 
	 * @param templateId
	 *            Template mobile primary key
	 */
	public void deleteTemplateMobile(Long templateId);

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
			String queryTemplateName);

	/**
	 * Prepare channel mobile
	 * 
	 * @param o
	 *            Channel mobile instance
	 * @param channelId
	 *            Channel mobile primary key
	 * @return
	 */
	public ChannelMobile prepareChannelMobile(ChannelMobile o, Long channelId);

	/**
	 * Save channel mobile
	 * 
	 * @param o
	 *            Channel mobile instance
	 * @return
	 */
	public ChannelMobile saveChannelMobile(ChannelMobile o);

	/**
	 * Delete channel mobile
	 * 
	 * @param channleId
	 *            Channel mobile primary key
	 */
	public void deleteChannelMobile(Long channleId);

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
			PageInfo<ChannelMobile> pageInfo, String account, String queryName);

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
			Long categoryId, Long channelId);

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
			Long templateId, Long articleTemplateId);

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
			Long queryChannelId, String queryName);

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
			Long categoryId);
}
