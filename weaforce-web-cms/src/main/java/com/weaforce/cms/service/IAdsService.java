package com.weaforce.cms.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.weaforce.cms.entity.ads.Ads;
import com.weaforce.cms.entity.ads.AdsDiscount;
import com.weaforce.cms.entity.ads.AdsPageLink;
import com.weaforce.cms.entity.ads.AdsPay;
import com.weaforce.cms.entity.ads.AdsStyle;
import com.weaforce.cms.entity.ads.BizCategory;
import com.weaforce.cms.entity.ads.BizCategoryService;
import com.weaforce.cms.entity.ads.BizChannel;
import com.weaforce.cms.entity.ads.CategorySite;
import com.weaforce.cms.entity.ads.CategoryTip;
import com.weaforce.cms.entity.ads.Deal;
import com.weaforce.cms.entity.ads.Message;
import com.weaforce.cms.entity.ads.Order;
import com.weaforce.cms.entity.ads.Product;
import com.weaforce.cms.entity.ads.Tag;
import com.weaforce.core.util.PageInfo;
import com.weaforce.system.service.ISystemService;

public interface IAdsService extends ISystemService{
	/**
	 * 为了防止业务逻辑偶合，对目标模板中的频道栏目菜单进行统一预处理
	 * 
	 * @param templateId
	 * @param cityId
	 */
	public void parseTemplateMenu(Long templateId, Long cityId);

	/**
	 * parse 热门商家{$ADS$}
	 * 
	 * @param templateId
	 *            模板主键
	 */
	public void parseAdsCell(Long templateId);

	/**
	 * parse 最新打折
	 * 
	 * @param templateId
	 *            模板主键
	 */

	public void parseDiscountCell(Long templateId);

	/**
	 * 预处理频道
	 * 
	 * @param o
	 *            频道
	 * @param channelId
	 *            频道主键
	 * @param cityId
	 *            城市主键
	 * @return
	 */
	public BizChannel prepareChannel(BizChannel o, Long channelId, Long cityId);

	/**
	 * 取得频道
	 * 
	 * @param channelId
	 * @return
	 */
	public BizChannel getChannel(Long channelId);

	/**
	 * 保存频道
	 * 
	 * @param o
	 *            频道
	 * @param cityId
	 *            城市主键
	 * @return
	 */
	public BizChannel saveChannel(BizChannel o, Long cityId);

	/**
	 * 删除频道
	 * 
	 * @param channelId
	 */
	public void deleteChannel(Long channelId);

	/**
	 * 取得频道 list
	 * 
	 * @param cityId
	 * @return
	 */
	public List<BizChannel> getChannelListByCity(Long cityId);

	/**
	 * 根据频道，取得城市 JSON
	 * 
	 * @param channelId
	 * @return
	 */
	public String getCategoryDDL(Long channelId);

	/**
	 * 取得频道 page
	 * 
	 * @param pageInfo
	 * @param cityId
	 *            城市
	 * @param channelName
	 *            名称
	 * @return
	 */
	public PageInfo<BizChannel> getChannelPage(PageInfo<BizChannel> pageInfo,
			Long cityId, String channelName);

	/**
	 * parse 频道下的所有栏目
	 * 
	 * @param account
	 * @param channelId
	 * @throws Exception
	 */
	public void parseChannel(String account, Long channelId) throws Exception;

	/**
	 * 预处理栏目
	 * 
	 * @param o
	 * @param categoryId
	 * @param channelId
	 * @return
	 */
	public BizCategory prepareCategory(BizCategory o, Long categoryId,
			Long channelId);


	/**
	 * 保存广告栏目
	 * 
	 * @param o
	 *            栏目
	 * @param channelId
	 *            频道
	 * @param templateId
	 *            栏目模板
	 * @param templateAdsId
	 *            广告单元模板
	 * @param templateAdsStarId
	 *            广告明星
	 * @param templateSiteId
	 *            商家网站
	 * @param templateTipArticleId
	 *            小贴士模板
	 * @param templateTipListId
	 *            小贴士模板
	 * @param templateDiscountArticleId
	 *            打折模板
	 * @param templateDiscountListId
	 *            打折模板
	 * @return
	 */
	public BizCategory saveCategory(BizCategory o, Long channelId,
			Long templateId, Long templateAdsId, Long templateAdsStarId,
			Long templateSiteId, Long templateTipArticleId,
			Long templateTipListId, Long templateDiscountArticleId,
			Long templateDiscountListId);

	/**
	 * 删除广告栏目
	 * 
	 * @param categoryId
	 */

	public void deleteCategory(Long categoryId);

	/**
	 * 根据频道,取得栏目 list
	 * 
	 * @param channelId
	 *            频道
	 * @param flag
	 *            是否包括 " --- all --- "选项
	 * @return
	 */

	public List<BizCategory> getCategoryListByChannel(Long channelId,
			boolean flag);

	/**
	 * 取得栏目 page
	 * 
	 * @param pageInfo
	 * @param channelId
	 *            频道
	 * @param catalogName
	 * @return
	 */
	public PageInfo<BizCategory> getCategoryPage(
			PageInfo<BizCategory> pageInfo, Long channelId, String categoryName);

	/**
	 * 以JSON格式，取得广告信息
	 * 
	 * @param categoryId
	 *            广告栏目
	 * @param tagId
	 *            标签
	 * @param zoneId
	 *            城区
	 * @param pageNumber
	 *            页码
	 * @return
	 */
	public String getCategoryAdsJSON(Long categoryId, Long tagId, Long zoneId,
			String adsName, Integer pageNumber);

	/**
	 * 以HTML格式，批量取得广告/风格/服务
	 * 
	 * @param pageNumber
	 *            页码
	 * @param categoryId
	 *            广告栏目
	 * @param tagId
	 *            标签
	 * @param zoneId
	 *            城区
	 * @param pageNumber
	 *            页码
	 * @return
	 */
	public String getCategoryAdsHTML(Long categoryId, Long tagId, Long zoneId,
			String adsName, Integer pageNumber);

	/**
	 * parse 广告目录
	 * <p>
	 * 终点
	 * <ul>
	 * <li>网页标题</li>
	 * <li>广告目录导航</li>
	 * <li>标签</li>
	 * <li>区域</li>
	 * </ul>
	 * </p>
	 * 
	 * @param 帐套
	 * @param cityId
	 * @param categoryId
	 *            栏目
	 * 
	 * @throws Exception
	 */
	public void parseCategory(String account, Long cityId, Long categoryId)
			throws Exception;

	/**
	 * 将栏目下的所有广告parse成html文件：基本信息／标签／页码／商家内容
	 * 
	 * @param account
	 *            　帐套
	 * @param cityId
	 *            　城市主键
	 * @param categoryId
	 *            　栏目主键
	 * @throws Exception
	 */
	public void parseCategoryAdsHtml(String account, Long cityId,
			Long categoryId) throws Exception;

	/**
	 * parse 服务下拦菜单:频道+栏目
	 * 
	 * @param cityId
	 *            城市
	 * @return
	 */
	public String getChannelCategoryDDM(Long cityId);

	/**
	 * 预处理广告栏目服务
	 * 
	 * @param service
	 * @param serviceId
	 * @param categoryId
	 * @return
	 */
	public BizCategoryService prepareService(BizCategoryService o,
			Long serviceId, Long categoryId);

	/**
	 * 取得广告栏目服务
	 * 
	 * @param serviceId
	 *            主键
	 * @return
	 */
	public BizCategoryService getService(Long serviceId);

	/**
	 * 保存广告目录类别
	 * 
	 * @param o
	 * @param categoryId
	 *            广告目录
	 * @param templateId
	 *            模板
	 * @return
	 */
	public BizCategoryService saveService(BizCategoryService o,
			Long categoryId, Long templateId);

	/**
	 * 删除广告目录类别
	 * 
	 * @param categoryId
	 */
	public void deleteService(Long serviceId);

	/**
	 * 取得广告目录 list
	 * 
	 * @param categoryId
	 *            广告目录
	 * @return
	 */
	public List<BizCategoryService> getServiceListByCategory(Long categoryId);

	/**
	 * 取得广告目录类别 page
	 * 
	 * @param pageInfo
	 * @param categoryId
	 *            广告目录
	 * @param categoryName
	 * @return
	 */
	public PageInfo<BizCategoryService> getServicePage(
			PageInfo<BizCategoryService> pageInfo, Long categoryId,
			String serviceName);

	/**
	 * 取得联合广告服务
	 * 
	 * @param adsId
	 *            广告
	 * @param serviceId
	 *            广告服务
	 * @return
	 */
	public com.weaforce.cms.entity.ads.AdsService getAdsService(Long adsId,
			Long serviceId);

	/**
	 * 保存联合广告目录类别
	 * 
	 * @param o
	 * @param adsId
	 *            广告
	 * @param categoryId
	 *            广告目录类别
	 * @return
	 */
	public com.weaforce.cms.entity.ads.AdsService saveAdsService(
			com.weaforce.cms.entity.ads.AdsService o, Long adsId, Long serviceId);

	/**
	 * 根据商家服务,以JSON格式取得像册 list
	 * 
	 * @param account
	 *            帐套
	 * @param adsId
	 *            广告商家
	 * @param serviceId
	 *            商家服务
	 * @param albumName
	 *            　像册名称
	 * @param dateFrom
	 *            　起始时间
	 * @param dateTo
	 *            　终止时间
	 * @return
	 */
	public String getAlbumListJSONByAdsService(String account, Long adsId,
			Long serviceId, String albumName, String dateFrom, String dateTo);

	/**
	 * 保存广告商家服务像册
	 * 
	 * @param adsId
	 *            广告商家
	 * @param serviceId
	 *            商家服务
	 * @param albumId
	 *            像册
	 * @return
	 */
	public String saveAdsServiceAlbum(Long adsId, Long serviceId, Long albumId);

	/**
	 * 删除联合广告目录类别
	 * 
	 * @param adsId
	 *            广告
	 * @param categoryId
	 *            广告目录类别
	 */
	public void deleteAdsService(Long adsId, Long serviceId);

	/**
	 * parse 联合广告目录类别,需要保存URL
	 * 
	 * @param adsId
	 *            广告
	 * @param serviceId
	 *            广告目录类别
	 * @throws Exception
	 */
	public void parseAdsServce(Long adsId, Long serviceId) throws Exception;

	/**
	 * 根据广告，取得广告目录类别 list
	 * 
	 * @param adsId
	 *            广告
	 * @return
	 */
	public List<com.weaforce.cms.entity.ads.AdsService> getAdsServiceListByAds(
			Long adsId);

	/**
	 * 预处理标签
	 * 
	 * @param o
	 * @param tagId
	 * @param categoryId
	 * @return
	 */

	public Tag prepareTag(Tag o, Long tagId, Long categoryId);

	/**
	 * 取得标签
	 * 
	 * @param tagId
	 * @return
	 */
	public Tag getTag(Long tagId);

	/**
	 * 保存标签
	 * 
	 * @param o
	 * @param categoryId
	 *            广告目录
	 * @return
	 */
	public Tag saveTag(Tag o, Long categoryId);

	/**
	 * 删除标签
	 * 
	 * @param tagId
	 */
	public void deleteTag(Long tagId);

	/**
	 * 根据广告目录,取得标签 list
	 * 
	 * @param categoryId
	 *            广告目录
	 * @return
	 */
	public List<Tag> getTagListByCategory(Long categoryId);

	/**
	 * 取得标签 page
	 * 
	 * @param pageInfo
	 * @param categoryId
	 *            广告目录
	 * @param tagName
	 * @return
	 */
	public PageInfo<Tag> getTagPage(PageInfo<Tag> pageInfo, Long categoryId,
			String tagName);

	/**
	 * 预处理广告
	 * 
	 * @param o
	 *            广告
	 * @param adsId
	 *            广告主键
	 * @param zoneId
	 *            区域
	 * @param categoryId
	 *            广告栏目
	 * @return
	 */
	public Ads prepareAds(Ads o, Long adsId, Long zoneId, Long categoryId);

	/**
	 * 取得广告
	 * 
	 * @param adsId
	 * @return
	 */
	public Ads getAds(Long adsId);

	/**
	 * 以JSON格式,取得广告名称,同时保存点击率
	 * 
	 * @param adsId
	 *            广告主键
	 * @return
	 */
	public String getAdsName(Long adsId);

	/**
	 * 保存广告
	 * 
	 * @param o
	 * @param categoryId
	 *            广告目录
	 * @param zoneId
	 *            地区
	 * @param tagId
	 *            广告标签
	 * @param styleId
	 *            风格主键
	 * @param styleItem
	 *            风格项目
	 * @return
	 * @throws Exception
	 */
	public Ads saveAds(Ads o, Long categoryId, Long zoneId, Long tagId,
			List<Long> checkedCategoryIds, Long[] styleId, String[] styleItem)
			throws Exception;

	/**
	 * 删除广告
	 * 
	 * @param adsId
	 */
	public void deleteAds(Long adsId);

	/**
	 * 根据广告目录,取得广告 list
	 * 
	 * @param categoryId
	 *            广告目录
	 * @return
	 */
	public List<Ads> getAdsListByCategory(Long categoryId);

	/**
	 * 根据区域主键,取得广告商家 list
	 * 
	 * @param zoneId
	 *            区域主键
	 * @return
	 */
	public List<Ads> getAdsListByZone(Long zoneId);

	/**
	 * 根据区域主键/广告栏目,取得广告商家 list
	 * 
	 * @param zoneId
	 * @param categoryId
	 * @param flag
	 * @return
	 */
	public List<Ads> getAdsListByZoneCategory(Long zoneId, Long categoryId,
			boolean flag);

	/**
	 * 取得广告JSON格式数据
	 * 
	 * @param categoryId
	 *            广告目录:一定不能为空
	 * @return
	 */
	public String getAdsJSONByCategory(Long categoryId);

	/**
	 * 根据栏目，取得广告 JSON
	 * 
	 * @param categoryId
	 *            栏目
	 * @return
	 */
	public String getAdsDDL(Long categoryId);

	/**
	 * 根据区域主键/广告栏目,取得广告商家 list
	 * 
	 * @param zoneId
	 *            城区
	 * @param categoryId
	 *            栏目
	 * @param flag
	 *            是否有 all 选取项
	 * @return
	 */
	public String getAdsDDL(Long zoneId, Long categoryId, boolean flag);

	/**
	 * 取得广告 page
	 * 
	 * @param pageInfo
	 * @param categoryId
	 *            广告目录
	 * @param zoneId
	 *            地区
	 * @param adsName
	 * @return
	 */
	public PageInfo<Ads> getAdsPage(PageInfo<Ads> pageInfo, Long categoryId,
			Long zoneId, String adsName);

	/**
	 * 以JSON格式，取得栏目广告分页信息
	 * 
	 * @param categoryId
	 *            广告栏目
	 * @param tagId
	 *            标签
	 * @param zoneId
	 *            城区
	 * @param pageNumber
	 *            页码
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getAdsPageJSON(Long categoryId, Long tagId, Long zoneId,
			String adsName, Integer pageNumber);

	/**
	 * 取得广告风格
	 * 
	 * @param styleId
	 *            主键
	 * @return
	 */
	public AdsStyle getStyle(Long styleId);

	/**
	 * parse 广告商家，主要完成如下工作：
	 * <p>
	 * <ul>
	 * <li>parse 广告商家为HTML：网站</li>
	 * <li>parse 广告商家服务为HTML：服务项目</li>
	 * </ul>
	 * </p>
	 * 
	 * @param adsId
	 *            商家主键
	 * @throws Exception
	 */
	public void parseAds(Long adsId) throws Exception;

	/**
	 * parse 栏目下所有的广告商家
	 * 
	 * @param categoryId
	 *            商家栏目主键
	 * @throws Exception
	 */
	public void parseAdsAll(Long categoryId) throws Exception;

	/**
	 * 预处理打折
	 * 
	 * @param o
	 *            打折
	 * @param discountId
	 *            打折主键
	 * @param adsId
	 *            广告商家主键
	 * @return
	 */
	public AdsDiscount prepareDiscount(AdsDiscount o, Long discountId,
			Long adsId);

	/**
	 * 取得广告信息
	 * 
	 * @param discountId
	 * @return
	 */
	public AdsDiscount getDiscount(Long discountId);

	/**
	 * 保存广告信息
	 * 
	 * @param o
	 * @param adsId
	 *            广告
	 * @return
	 */
	public AdsDiscount saveDiscount(AdsDiscount o, Long adsId);

	/**
	 * 删除广告信息
	 * 
	 * @param discountId
	 */
	public void deleteDiscount(Long discountId);

	/**
	 * 取得JSON格式打折记录
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @param tipTitle
	 *            标题
	 * @param pageNumber
	 *            当前页数
	 * @return
	 */
	public String getDiscountJSON(Long categoryId, String discountTitle,
			Integer pageNumber);

	/**
	 * 取得广告信息 page
	 * 
	 * @param pageInfo
	 * @param adsId
	 *            广告
	 * @return
	 */
	public PageInfo<AdsDiscount> getDiscountPage(
			PageInfo<AdsDiscount> pageInfo, Long categoryId,
			String discountTitle, String adsName);

	/**
	 * parse 打折信息
	 * 
	 * @param discountId
	 * @throws Exception
	 */
	public void parseDiscount(String account, Long discountId) throws Exception;

	/**
	 * parse 栏目打折 list
	 * 
	 * @param account
	 *            帐套
	 * @param categoryId
	 *            　栏目主键
	 * @throws Exception
	 */
	public void parseDiscountPage(String account, Long categoryId)
			throws Exception;

	/**
	 * 预处理小贴士
	 * 
	 * @param o
	 *            小贴士
	 * @param tipId
	 *            小贴士主键
	 * @param categoryId
	 *            栏目主键
	 * @return
	 */
	public CategoryTip prepareTip(CategoryTip o, Long tipId, Long categoryId);

	/**
	 * 取得小贴士
	 * 
	 * @param tipId
	 * @return
	 */
	public CategoryTip getTip(Long tipId);

	/**
	 * 保存小贴士
	 * 
	 * @param o
	 * @param categoryId
	 *            广告目录
	 * @param fromId
	 *            来源
	 * @return
	 */
	public CategoryTip saveTip(CategoryTip o, Long categoryId, Long fromId);

	/**
	 * 删除小贴士
	 * 
	 * @param tipId
	 */
	public void deleteTip(Long tipId);

	/**
	 * 取得JSON格式小贴士记录
	 * 
	 * @param categoryId
	 *            栏目主键
	 * 
	 * @param tipTitle
	 *            标题
	 * @param pageNumber
	 *            当前页数
	 * @return
	 */
	public String getTipJSON(Long categoryId, String tipTitle,
			Integer pageNumber);

	/**
	 * 取得小贴士 page
	 * 
	 * @param pageInfo
	 * @param categoryId
	 *            广告目录
	 * @param tipTitle
	 *            标题
	 * @return
	 */
	public PageInfo<CategoryTip> getTipPage(PageInfo<CategoryTip> pageInfo,
			Long categoryId, String tipTitle);

	/**
	 * parse 小贴士
	 * 
	 * @param account
	 *            帐套
	 * @param tipId
	 *            小贴士
	 * @throws Exception
	 */
	public void parseTip(String account, Long tipId) throws Exception;

	/**
	 * pares 小贴士 page
	 * 
	 * @param account
	 *            帐套
	 * @param categoryId
	 *            栏目主键
	 * @throws Exception
	 */
	public void parseTipPage(String account, Long categoryId) throws Exception;

	/**
	 * 取得短信
	 * 
	 * @param messageId
	 * @return
	 */
	public Message getMessage(Long messageId);

	/**
	 * 保存短信
	 * 
	 * @param o
	 * @param adsId
	 *            广告
	 * @return
	 */
	public Message saveMessage(Message o, Long adsId);

	/**
	 * 保存客户留言/短信/电话
	 * 
	 * @param adsId
	 *            广告商家
	 * @param phone
	 *            电话
	 * @param mobile
	 *            手机
	 * @param title
	 *            标题
	 */
	public void saveMessage(Long adsId, String phone, String mobile,
			String title);

	/**
	 * 删除短信
	 * 
	 * @param messageId
	 */
	public void deleteMessage(Long messageId);

	/**
	 * 确认信息
	 * 
	 * @param messageId
	 *            主键
	 */

	public void lockMessage(Long messageId);

	/**
	 * 取得短信 page
	 * 
	 * @param pageInfo
	 * @param adsId
	 *            广告
	 * @param messageTitle
	 *            标题
	 * @param messageMobile
	 *            手机
	 * @param isOk
	 *            处理完毕
	 * @param dateFrom
	 *            起始日期
	 * @param dateTo
	 *            终止日期
	 * @return
	 */
	public PageInfo<Message> getMessagePage(PageInfo<Message> pageInfo,
			Long adsId, String messageTitle, String messageMobile, String isOk,
			String dateFrom, String dateTo);

	/**
	 * 取得首页链接
	 * 
	 * @param linkId
	 *            主键
	 * @return
	 */
	public AdsPageLink getLink(Long linkId);

	/**
	 * 保存首页链接:linkOrder linkLabelCode在form中输入
	 * 
	 * @param o
	 *            链接
	 * @param parentId
	 *            父节点
	 * @param channelId
	 *            频道主键
	 * @param categoryId
	 *            栏目主键
	 * @param adsId
	 *            广告主键
	 * @param templateId
	 *            模板主键
	 * @return
	 */
	public AdsPageLink saveLink(AdsPageLink o, Long parentId, Long channelId,
			Long categoryId, Long adsId, Long templateId);

	/**
	 * 删除首页链接
	 * 
	 * @param linkId
	 *            主键
	 */
	public void deleteLink(Long linkId);

	/**
	 * 根据级别，取得链接 list
	 * 
	 * @param linkLevel
	 *            级别
	 * @param categoryId
	 *            栏目
	 * @param adsId
	 *            广告
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<AdsPageLink> getLinkListByLevel(Byte linkLevel,
			Long categoryId, Long adsId);

	/**
	 * 取得页面树
	 * 
	 * @param account
	 *            帐套
	 * @param parentId
	 *            父节点
	 * @return
	 */

	public String getLinkTree(Long parentId);

	/**
	 * pares 首页
	 * 
	 * @param cityName
	 *            城市名称
	 * 
	 * @param linkId
	 *            主键
	 * @throws Exception
	 */

	public void parseLink(String account, String cityName, Long linkId)
			throws Exception;

	/**
	 * 取得首页链接 page
	 * 
	 * @param pageInfo
	 * @param linkTitle
	 *            标题
	 * @return
	 */
	public PageInfo<AdsPageLink> getLinkPage(PageInfo<AdsPageLink> pageInfo,
			String linkTitle);

	/**
	 * 预处理网站
	 * 
	 * @param o
	 * @param siteId
	 * @return
	 */
	public CategorySite prepareSite(CategorySite o, Long siteId);

	/**
	 * 取得网站
	 * 
	 * @param siteId
	 *            网站主键
	 * @return
	 */
	public CategorySite getSite(Long siteId);

	/**
	 * 保存网站
	 * 
	 * @param o
	 *            网站
	 * @param categoryId
	 *            栏目主键
	 * @return
	 */
	public CategorySite saveSite(CategorySite o, Long categoryId);

	/**
	 * 删除网站
	 * 
	 * @param siteId
	 *            网站主键
	 */
	public void deleteSite(Long siteId);

	/**
	 * 取得网站 page
	 * 
	 * @param pageInfo
	 * @param categoryId
	 *            栏目主键
	 * @param siteName
	 *            名称
	 * @return
	 */
	public PageInfo<CategorySite> getSitePage(PageInfo<CategorySite> pageInfo,
			Long categoryId, String siteName);

	/**
	 * 预处理广告商家付款明细
	 * 
	 * @param o
	 *            付款明细
	 * @param payId
	 *            付款明细主键
	 * @param adsId
	 *            广告商家主键
	 * @return
	 */
	public AdsPay preparePay(AdsPay o, Long payId, Long adsId);

	/**
	 * 保存付款明细
	 * 
	 * @param o
	 *            付款明细
	 * @param adsId
	 *            广告商家主键
	 * @param payPayBefore
	 *            修改前数值
	 * @return
	 */
	public AdsPay savePay(AdsPay o, Long adsId, BigDecimal payPayBefore);

	/**
	 * 删除付款明细
	 * 
	 * @param payId
	 *            付款明细主键
	 */
	public void deletePay(Long payId);

	/**
	 * 取得付款 page
	 * 
	 * @param pageInfo
	 * @param account
	 * @param adsId
	 *            广告商家主键
	 * @param payTitle
	 *            标题
	 * @param dateFrom
	 *            起始日期
	 * @param dateTo
	 *            终止日期
	 * @return
	 */
	public PageInfo<AdsPay> getPayPage(PageInfo<AdsPay> pageInfo,
			String account, Long adsId, String payTitle, String dateFrom,
			String dateTo);

	/**
	 * 预处理团购产品
	 * 
	 * @param o
	 *            产品
	 * @param productId
	 *            产品主键
	 * @param adsId
	 *            广告商家主键
	 * @return
	 */
	public Product prepareProduct(Product o, Long productId, Long adsId);

	/**
	 * 保存团购产品
	 * 
	 * @param o
	 *            　产品
	 * @param adsId
	 *            　广告商家主键
	 * @param templateId
	 *            模板主键
	 * @return
	 */
	public Product saveProduct(Product o, Long adsId, Long templateId);

	/**
	 * 删除团购产品
	 * 
	 * @param productId
	 *            产品主键
	 */
	public void deleteProduct(Long productId);

	/**
	 * 取得团购产品　page
	 * 
	 * @param pageInfo
	 * @param categoryId
	 *            栏目主键
	 * @param productName
	 *            产品名称
	 * @return
	 */
	public PageInfo<Product> getProductPage(PageInfo<Product> pageInfo,
			Long categoryId, String productName);

	/**
	 * 预处理交易
	 * 
	 * @param o
	 *            　交易
	 * @param dealId
	 *            　交易主键
	 * @param productId
	 *            　产品主键
	 * @return
	 */
	public Deal prepareDeal(Deal o, Long dealId, Long productId);

	/**
	 * 保存交易
	 * 
	 * @param o
	 *            　交易
	 * @param productId
	 *            　产品主键
	 * @return
	 */
	public Deal saveDeal(Deal o, Long productId);

	/**
	 * 删除交易
	 * 
	 * @param dealId
	 *            交易主键
	 */
	public void deleteDeal(Long dealId);

	/**
	 * 取得交易 page
	 * 
	 * @param pageInfo
	 * @param productId
	 *            产品主键
	 * @param dateFrom
	 *            起始日期
	 * @param dateTo
	 *            　终止日期
	 * @return
	 */
	public PageInfo<Deal> getDealPage(PageInfo<Deal> pageInfo, Long productId,
			String dateFrom, String dateTo);

	/**
	 * 预处理团购订单
	 * 
	 * @param o
	 *            　订单
	 * @param orderId
	 *            　订单主键
	 * @param dealId
	 *            　交易主键
	 * @return
	 */
	public Order prepareOrder(Order o, Long orderId, Long dealId);

	/**
	 * 保存团购订单
	 * 
	 * @param o
	 *            　订单
	 * @param dealId
	 *            　交易主键
	 * @return
	 */
	public Order saveOrder(Order o, Long dealId);

	/**
	 * 删除团购订单
	 * 
	 * @param orderId
	 *            　订单主键
	 */
	public void deleteOrder(Long orderId);

	/**
	 * 取得团购订单 page
	 * 
	 * @param pageInfo
	 * @param categoryId
	 *            栏目主键
	 * @param orderAddress
	 *            送货地址
	 * @param dateFrom
	 *            起始日期
	 * @param dateTo
	 *            终止日期
	 * @return
	 */
	public PageInfo<Order> getOrderPage(PageInfo<Order> pageInfo,
			Long categoryId, String orderAddress, String dateFrom, String dateTo);

}
