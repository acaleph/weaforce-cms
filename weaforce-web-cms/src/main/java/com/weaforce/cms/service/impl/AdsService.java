package com.weaforce.cms.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weaforce.cms.dao.IAlbumDao;
import com.weaforce.cms.dao.ICopyFromDao;
import com.weaforce.cms.dao.IMetaDao;
import com.weaforce.cms.dao.IPhotoDao;
import com.weaforce.cms.dao.ITemplateDao;
import com.weaforce.cms.dao.ads.IAdsDao;
import com.weaforce.cms.dao.ads.IAdsPayDao;
import com.weaforce.cms.dao.ads.IAdsServiceDao;
import com.weaforce.cms.dao.ads.IAdsStyleDao;
import com.weaforce.cms.dao.ads.ICategoryDao;
import com.weaforce.cms.dao.ads.ICategoryServiceDao;
import com.weaforce.cms.dao.ads.IChannelDao;
import com.weaforce.cms.dao.ads.IDealDao;
import com.weaforce.cms.dao.ads.IDiscountDao;
import com.weaforce.cms.dao.ads.IMessageDao;
import com.weaforce.cms.dao.ads.IOrderDao;
import com.weaforce.cms.dao.ads.IPageLinkDao;
import com.weaforce.cms.dao.ads.IProductDao;
import com.weaforce.cms.dao.ads.ISiteDao;
import com.weaforce.cms.dao.ads.ITagDao;
import com.weaforce.cms.dao.ads.ITipDao;
import com.weaforce.cms.entity.AlbumPhoto;
import com.weaforce.cms.entity.Meta;
import com.weaforce.cms.entity.Template;
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
import com.weaforce.cms.service.IAdsService;
import com.weaforce.cms.util.AdsParser;
import com.weaforce.core.util.CollectionUtil;
import com.weaforce.core.util.DateUtil;
import com.weaforce.core.util.Global;
import com.weaforce.core.util.PageInfo;
import com.weaforce.core.util.StringUtil;
import com.weaforce.entity.area.Zone;
import com.weaforce.system.service.impl.SystemService;

/**
 * 广告服务
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Service("adsService")
@Transactional(rollbackFor = Exception.class)
public class AdsService extends SystemService implements IAdsService {
	@Autowired
	@Qualifier("metaDao")
	private IMetaDao metaDao;
	@Autowired
	@Qualifier("templateDao")
	private ITemplateDao templateDao;
	@Autowired
	@Qualifier("adsBizChannelDao")
	private IChannelDao channelDao;
	@Autowired
	@Qualifier("adsBizCategoryDao")
	private ICategoryDao categoryDao;
	@Autowired
	@Qualifier("adsBizCategoryServiceDao")
	private ICategoryServiceDao categoryServiceDao;
	@Autowired
	@Qualifier("adsAdsServiceDao")
	private IAdsServiceDao adsServiceDao;
	@Autowired
	@Qualifier("adsTagDao")
	private ITagDao tagDao;
	@Autowired
	@Qualifier("adsDao")
	private IAdsDao adsDao;
	@Autowired
	@Qualifier("adsAdsStyleDao")
	private IAdsStyleDao styleDao;
	@Autowired
	@Qualifier("adsDiscountDao")
	private IDiscountDao discountDao;
	@Autowired
	@Qualifier("adsTipDao")
	private ITipDao tipDao;
	@Autowired
	@Qualifier("copyFromDao")
	private ICopyFromDao fromDao;
	@Autowired
	@Qualifier("adsMessageDao")
	private IMessageDao messageDao;
	@Autowired
	@Qualifier("adsPageLinkDao")
	private IPageLinkDao linkDao;
	@Autowired
	@Qualifier("adsSiteDao")
	private ISiteDao siteDao;
	@Autowired
	@Qualifier("albumPhotoDao")
	private IPhotoDao photoDao;
	@Autowired
	@Qualifier("albumDao")
	private IAlbumDao albumDao;
	@Autowired
	@Qualifier("adsPayDao")
	private IAdsPayDao payDao;
	@Autowired
	@Qualifier("adsProductDao")
	private IProductDao productDao;
	@Autowired
	@Qualifier("adsDealDao")
	private IDealDao dealDao;
	@Autowired
	@Qualifier("adsOrderDao")
	private IOrderDao orderDao;

	/**
	 * 为了防止业务逻辑偶合，对目标模板中的频道栏目菜单进行统一预处理
	 * 
	 * @param templateId
	 * @param cityId
	 */
	public void parseTemplateMenu(Long templateId, Long cityId) {
		Template o = templateDao.loadEntity(templateId);
		String content = o.getTemplateContent();
		String menu = getChannelCategoryDDM(cityId);
		if (menu == null)
			content = StringUtil.replaceAll(content, "{$MENU$}", "");
		else
			content = StringUtil.replaceAll(content, "{$MENU$}", menu);
		o.setTemplateContent(content);
		templateDao.save(o);
	}

	/**
	 * parse 热门商家{$ADS$}
	 * 
	 * @param templateId
	 *            模板主键
	 */
	public void parseAdsCell(Long templateId) {
		Template o = templateDao.loadEntity(templateId);
		String content = o.getTemplateContent();
		List<Ads> adsList = adsDao.getAdsList(0, 5);
		if (adsList.size() > 0)
			content = StringUtil.replaceAll(content, "{$ADS$}",
					AdsParser.parseAdsCell(null, adsList));
		else
			content = StringUtil.replaceAll(content, "{$ADS$}", "");
		o.setTemplateContent(content);
		templateDao.save(o);
	}

	/**
	 * parse 最新打折
	 * 
	 * @param templateId
	 *            模板主键
	 */
	public void parseDiscountCell(Long templateId) {
		Template o = templateDao.loadEntity(templateId);
		String content = o.getTemplateContent();
		List<AdsDiscount> discountList = discountDao.getDiscountList(0, 5);
		if (discountList.size() > 0)
			content = StringUtil.replaceAll(content, "{$DISCOUNT$}",
					AdsParser.parseDiscountCell(null, discountList));
		else
			content = StringUtil.replaceAll(content, "{$DISCOUNT$}", "");
		o.setTemplateContent(content);
		templateDao.save(o);
	}

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
	public BizChannel prepareChannel(BizChannel o, Long channelId, Long cityId) {
		if (channelId == null) {
			o = new BizChannel();
			if (cityId != null)
				o.setChannelCity(cityDao.loadEntity(cityId));
		} else
			o = channelDao.loadEntity(channelId);
		return o;
	}

	/**
	 * 取得频道
	 * 
	 * @param channelId
	 * @return
	 */
	@Transactional(readOnly = true)
	public BizChannel getChannel(Long channelId) {
		return channelDao.loadEntity(channelId);
	}

	/**
	 * 保存频道
	 * 
	 * @param o
	 *            频道
	 * @param cityId
	 *            城市主键
	 * @return
	 */
	@Transactional(readOnly = true)
	public BizChannel saveChannel(BizChannel o, Long cityId) {
		if (cityId != null)
			o.setChannelCity(cityDao.loadEntity(cityId));
		else
			o.setChannelCity(null);
		return channelDao.save(o);
	}

	/**
	 * 删除频道
	 * 
	 * @param channelId
	 */
	public void deleteChannel(Long channelId) {
		channelDao.delete(channelId);
	}

	/**
	 * 取得频道 list
	 * 
	 * @param cityId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<BizChannel> getChannelListByCity(Long cityId) {
		return channelDao.getChannelListByCity(cityId);
	}

	/**
	 * 根据频道，取得栏目 JSON
	 * 
	 * @param channelId
	 *            频道
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getCategoryDDL(Long channelId) {
		StringBuffer sb = new StringBuffer();
		List<BizCategory> categoryList = categoryDao.getCategoryListByChannel(
				channelId, false);
		for (BizCategory o : categoryList)
			if (sb.length() == 0)
				sb.append("[{\"value\":\"" + o.getCategoryId()
						+ "\",\"caption\":\"" + o.getCategoryName() + "\"}");
			else
				sb.append(",{\"value\":\"" + o.getCategoryId()
						+ "\",\"caption\":\"" + o.getCategoryName() + "\"}");
		if (sb.length() > 0)
			sb.append("]");
		return sb.toString();
	}

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
	@Transactional(readOnly = true)
	public PageInfo<BizChannel> getChannelPage(PageInfo<BizChannel> pageInfo,
			Long cityId, String channelName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From BizChannel a Where a.channelCity.cityId=" + cityId);
		if (StringUtil.isNotEmpty(channelName))
			sb.append(" And a.channelName like " + "'%" + channelName + "%'");
		pageInfo = channelDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.channelOrder ");
		return pageInfo;
	}

	/**
	 * parse 频道下的所有栏目
	 * 
	 * @param account
	 * @param channelId
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public void parseChannel(String account, Long channelId) throws Exception {
		List<BizCategory> categoryList = categoryDao.getCategoryListByChannel(
				channelId, false);
		// 直接调用parse栏目方法
		for (BizCategory o : categoryList)
			parseCategory(account, o.getCategoryChannel().getChannelCity()
					.getCityId(), o.getCategoryId());

	}

	/**
	 * 预处理栏目
	 * 
	 * @param o
	 * @param categoryId
	 * @param channelId
	 * @return
	 */
	@Transactional(readOnly = true)
	public BizCategory prepareCategory(BizCategory o, Long categoryId,
			Long channelId) {
		if (categoryId == null) {
			o = new BizCategory();
			if (channelId != null)
				o.setCategoryChannel(channelDao.loadEntity(channelId));

		} else
			o = categoryDao.loadEntity(categoryId);
		return o;
	}

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
			Long templateDiscountListId) {
		if (channelId != null)
			o.setCategoryChannel(channelDao.loadEntity(channelId));
		else
			o.setCategoryChannel(null);
		if (templateId != null)
			o.setCategoryTemplate(templateDao.loadEntity(templateId));
		else
			o.setCategoryTemplate(null);
		if (templateAdsId != null)
			o.setCategoryAdsTemplate(templateDao.loadEntity(templateAdsId));
		else
			o.setCategoryAdsTemplate(null);
		if (templateAdsStarId != null)
			o.setCategoryAdsStarTemplate(templateDao
					.loadEntity(templateAdsStarId));
		else
			o.setCategoryAdsStarTemplate(null);
		if (templateSiteId != null)
			o.setCategorySiteTemplate(templateDao.loadEntity(templateSiteId));
		else
			o.setCategorySiteTemplate(null);
		if (templateTipArticleId != null)
			o.setCategoryTipArticleTemplate(templateDao
					.loadEntity(templateTipArticleId));
		else
			o.setCategoryTipArticleTemplate(null);
		if (templateTipListId != null)
			o.setCategoryTipListTemplate(templateDao
					.loadEntity(templateTipListId));
		else
			o.setCategoryTipListTemplate(null);
		if (templateDiscountArticleId != null)
			o.setCategoryDiscountArticleTemplate(templateDao
					.loadEntity(templateDiscountArticleId));
		else
			o.setCategoryDiscountArticleTemplate(null);
		if (templateDiscountListId != null)
			o.setCategoryDiscountListTemplate(templateDao
					.loadEntity(templateDiscountListId));
		else
			o.setCategoryDiscountListTemplate(null);
		return categoryDao.save(o);
	}

	/**
	 * 删除广告栏目
	 * 
	 * @param categoryId
	 */
	public void deleteCategory(Long categoryId) {
		categoryDao.delete(categoryId);
	}

	/**
	 * 根据频道,取得栏目 list
	 * 
	 * @param channelId
	 *            频道
	 * @param flag
	 *            是否包括 " --- all --- "选项
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<BizCategory> getCategoryListByChannel(Long channelId,
			boolean flag) {
		return categoryDao.getCategoryListByChannel(channelId, flag);
	}

	/**
	 * 取得栏目 page
	 * 
	 * @param pageInfo
	 * @param channelId
	 *            频道
	 * @param categoryName
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<BizCategory> getCategoryPage(
			PageInfo<BizCategory> pageInfo, Long channelId, String categoryName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From BizCategory a Where categoryChannel.channelId="
				+ channelId);
		if (StringUtil.isNotEmpty(categoryName))
			sb.append(" Where a.categoryName like " + "'%" + categoryName
					+ "%'");
		pageInfo = categoryDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.categoryOrder ");
		return pageInfo;
	}

	/**
	 * 以JSON格式，取得广告/风格/服务
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
	public String getCategoryAdsJSON(Long categoryId, Long tagId, Long zoneId,
			String adsName, Integer pageNumber) {
		StringBuffer contentAds = new StringBuffer();
		List<Ads> adsList = adsDao.getAdsPageByCategoryTagZone(categoryId,
				tagId, zoneId, adsName, pageNumber);
		for (Ads o : adsList) {
			if (contentAds.length() == 0)
				contentAds.append("[");
			else
				contentAds.append(",");
			contentAds.append("{\"adsName\":\"" + o.getAdsName()
					+ "\",\"adsWeb\":\"" + o.getAdsWeb()
					+ "\",\"adsPictureUrl\":\"" + o.getAdsPictureUrl()
					+ "\",\"adsPhone\":\"" + o.getAdsPhone()
					+ "\",\"adsPhoneTime\":\"" + o.getAdsPhoneTime()
					+ "\",\"adsAddress\":\"" + o.getAdsAddress() + "\"");
			// 广告风格
			Set<AdsStyle> styleSet = o.getAdsStyle();
			StringBuffer contentStyle = new StringBuffer();
			for (AdsStyle s : styleSet) {
				if (contentStyle.length() == 0)
					contentStyle.append("[");
				else
					contentStyle.append(",");
				contentStyle.append("{\"serviceItem\":\"" + s.getStyleItem()
						+ "\"}");
			}
			if (contentStyle.length() > 0)
				contentAds
						.append(",\"style\":" + contentStyle.toString() + "]");
			else
				contentAds.append("}");
			// 广告栏目服务
			Set<com.weaforce.cms.entity.ads.AdsService> serviceSet = o
					.getAdsAdsService();
			StringBuffer contentService = new StringBuffer();
			for (com.weaforce.cms.entity.ads.AdsService c : serviceSet) {
				if (contentService.length() == 0)
					contentService.append("[");
				else
					contentService.append(",");
				BizCategoryService categoryService = c.getServiceService();
				contentService.append("{\"serviceName\":\""
						+ categoryService.getServiceName()
						+ "\",\"serviceMapX\":\"" + o.getAdsMapX()
						+ "\",\"serviceMapY\":\"" + o.getAdsMapY()
						+ "\",\"serviceUrl\":\"" + o.getCreateTime() + "-"
						+ categoryService.getServiceFileName() + ".html"
						+ "\"}");
			}
			if (contentService.length() > 0)
				contentAds.append(",\"service\":" + contentService.toString()
						+ "]");
			else
				contentAds.append("]");
			contentAds.append("}");
		}
		if (contentAds.length() > 0)
			contentAds.append("]");
		// System.out.println("contentAds: " + contentAds.toString());
		return contentAds.toString();
	}

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
	@Transactional(readOnly = true)
	public String getCategoryAdsHTML(Long categoryId, Long zoneId, Long tagId,
			String adsName, Integer pageNumber) {
		StringBuffer contentAds = new StringBuffer();
		List<Ads> adsList = adsDao.getAdsPageByCategoryTagZone(categoryId,
				zoneId, tagId, adsName, pageNumber);
		for (Ads o : adsList)
			if (StringUtil.isNotEmpty(o.getAdsContentHtml()))
				contentAds.append(o.getAdsContentHtml());
		return contentAds.toString();
	}

	/**
	 * parse 广告栏目
	 * <p>
	 * 首页
	 * <ul>
	 * <li>广告栏目JSON</li>
	 * <li>META数据</li>
	 * <li>热点链接</li>
	 * </ul>
	 * 终点
	 * <ul>
	 * <li>META数据</li>
	 * <li>网页标题</li>
	 * <li>广告栏目导航</li>
	 * <li>标签</li>
	 * <li>区域</li>
	 * <li>最新打折信息(4件)</li>
	 * </ul>
	 * 
	 * </p>
	 * 
	 * @param account
	 *            帐套
	 * @param categoryId
	 *            栏目主键
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public void parseCategory(String account, Long cityId, Long categoryId)
			throws Exception {
		BizCategory o = categoryDao.loadEntity(categoryId);
		if (o != null) {
			// 城市名称
			String cityName = o.getCategoryChannel().getChannelCity()
					.getCityNameCn();
			// 总导航菜单
			String menu = getChannelCategoryDDM(cityId);
			// 频道下的相关栏目
			List<BizCategory> categoryList = categoryDao
					.getCategoryListByChannel(o.getCategoryChannel()
							.getChannelId(), false);
			// 栏目下的贴士(10条)
			List<CategoryTip> tipList = tipDao.getTipListByCategory(categoryId,
					0, 9);
			// 打折信息(4条)
			List<AdsDiscount> discountList = discountDao
					.getDiscountListByCategory(categoryId, 0, 3);
			// Meta信息
			List<Meta> metaList = metaDao.getMetaList(account);
			// 当前栏目下的标签集
			List<Tag> tagList = o.getCategoryTag();
			// 城市（惠州）区列表
			List<Zone> zoneList = zoneDao.getZoneListByCity(cityId);

			AdsParser.parseCategory(o, cityName, categoryList, tagList,
					zoneList, tipList, discountList, metaList, menu);

		}
	}

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
	@Transactional(readOnly = true)
	public void parseCategoryAdsHtml(String account, Long cityId,
			Long categoryId) throws Exception {
		BizCategory o = categoryDao.loadEntity(categoryId);
		if (o != null) {
			// 当前栏目的模板
			String template = o.getCategoryTemplate().getTemplateContent();
			// -------------------parse当前栏目基本信息-------------------
			// 城市名称
			String cityName = o.getCategoryChannel().getChannelCity()
					.getCityNameCn();
			// 总导航菜单
			String menu = getChannelCategoryDDM(cityId);
			template = AdsParser.parseCategory(o, template, cityName, menu);
			// -------------------parse当前栏目基本信息-------------------

			// -------------------parse链接信息-------------------
			// 频道下的相关栏目
			List<BizCategory> categoryList = categoryDao
					.getCategoryListByChannel(o.getCategoryChannel()
							.getChannelId(), false);
			// 栏目下的贴士(10条)
			List<CategoryTip> tipList = tipDao.getTipListByCategory(categoryId,
					0, 9);
			// 打折信息(4条)
			List<AdsDiscount> discountList = discountDao
					.getDiscountListByCategory(categoryId, 0, 3);
			// Meta信息
			List<Meta> metaList = metaDao.getMetaList(account);
			template = AdsParser.parseCategory(o, template, categoryList,
					tipList, discountList, metaList);
			// -------------------parse链接信息-------------------
			// 城市（惠州）区列表
			List<Zone> zoneList = zoneDao.getZoneListByCity(cityId);
			Zone zone = new Zone();
			zone.setZoneId(Long.valueOf("0"));
			zoneList.add(0, zone);
			// 当前栏目下的标签集
			List<Tag> tagList = o.getCategoryTag();
			Tag tag = new Tag();
			tag.setTagId(Long.valueOf(Long.valueOf("0")));
			tagList.add(0, tag);
			// 对所有的当前的城市区与当前栏目下的标签进行配对生成html
			for (int k = 0; k < zoneList.size(); k++) {
				for (int l = 0; l < tagList.size(); l++) {
					// 取得当前栏目下的所有广告商家
					List<Ads> adsList = new ArrayList<Ads>();
					Long zoneId = zoneList.get(k).getZoneId();
					Long tagId = tagList.get(l).getTagId();
					if (k == 0 & l == 0)
						adsList = adsDao.getAdsListByCategoryParse(categoryId,
								"1");
					else if (k == 0 & l != 0)
						adsList = adsDao.getAdsListByCategoryTagParse(
								categoryId, tagId, "1");
					else if (k != 0 & l == 0)
						adsList = adsDao.getAdsListByZoneCategoryParse(zoneId,
								categoryId, "1");
					else if (k != 0 & l != 0)
						adsList = adsDao.getAdsListByZoneCategoryTagParse(
								zoneId, categoryId, tagId, "1");
					int adsListSize = adsList.size();
					// 标签区／页码区／商家区
					String tagCell = AdsParser.parseTagCell(tagList, tagId);
					String zoneCell = AdsParser.parseZoneCell(zoneList, zoneId);
					// 此条件下存在商家
					if (adsListSize > 0) {
						// 计算页码
						int pageCount = AdsParser.getPageCount(adsListSize,
								Global.PAGE_SIZE);
						// 可能Parse的当前参数下的文件，并作为参考，生成{$PAGER$}元素的内容
						String[] pageFileName = new String[pageCount];
						pageFileName = AdsParser.getCategoryPageFileName(
								pageCount, zoneId, categoryId, tagId,
								new String[pageCount]);
						for (int i = 0; i < pageCount; i++) {
							// i 为当前页,当前栏目下当前页面中页码{$PAGE$}和广告区内容{$CONTENT$}}
							String pagerCell = AdsParser.pager(pageCount,
									pageFileName, i, "_self");
							String contentCell = AdsParser.getPageContent(
									adsList, pageCount, i);
							AdsParser.parseCategory(o, zoneId, tagId, template,
									tagCell, zoneCell, pagerCell, contentCell,
									pageFileName[i]);

						}
						// 无商家,直接显示模板,无页码，无内容（可以默认一个内容区）
					} else {
						String pageFileName = o.getCategoryId() + "-" + zoneId
								+ "-" + tagId + "-" + "0" + ".html";
						AdsParser.parseCategory(o, zoneId, tagId, template,
								tagCell, zoneCell, "", "", pageFileName);
					}
				}
			}

		}
	}

	/**
	 * parse 服务下拦菜单:频道+栏目
	 * 
	 * @param cityId
	 *            城市
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getChannelCategoryDDM(Long cityId) {
		List<BizChannel> channelList = channelDao.getChannelListByCity(cityId);
		StringBuffer sbMenu = new StringBuffer();
		for (BizChannel channel : channelList) {
			StringBuffer sbChannel = new StringBuffer();
			List<BizCategory> categoryList = categoryDao
					.getCategoryListByChannel(channel.getChannelId(), false);
			StringBuffer sbCategory = new StringBuffer("");
			for (BizCategory category : categoryList)
				sbCategory.append(AdsParser.parseCategoryCell(category));
			sbChannel.append(AdsParser.parseChannelCell(channel, sbCategory));
			sbMenu.append(sbChannel);
		}
		return sbMenu.toString();
	}

	/**
	 * 预处理广告栏目服务
	 * 
	 * @param service
	 * @param serviceId
	 * @param categoryId
	 * @return
	 */
	@Transactional(readOnly = true)
	public BizCategoryService prepareService(BizCategoryService o,
			Long serviceId, Long categoryId) {
		if (serviceId == null) {
			o = new BizCategoryService();
			o.setServiceCategory(categoryDao.loadEntity(categoryId));
		} else
			o = categoryServiceDao.loadEntity(serviceId);
		return o;
	}

	/**
	 * 取得广告栏目服务
	 * 
	 * @param serviceId
	 *            主键
	 * @return
	 */
	@Transactional(readOnly = true)
	public BizCategoryService getService(Long serviceId) {
		return categoryServiceDao.loadEntity(serviceId);
	}

	/**
	 * 保存广告栏目服务
	 * 
	 * @param o
	 * @param categoryId
	 *            广告栏目
	 * @param templateId
	 *            模板
	 * @return
	 */
	public BizCategoryService saveService(BizCategoryService o,
			Long categoryId, Long templateId) {
		if (categoryId != null)
			o.setServiceCategory(categoryDao.loadEntity(categoryId));
		else
			o.setServiceCategory(null);
		if (templateId != null)
			o.setServiceTemplate(templateDao.loadEntity(templateId));
		else
			o.setServiceTemplate(null);
		// 强制为小写
		o.setServiceFileName(o.getServiceFileName().toLowerCase());
		return categoryServiceDao.save(o);
	}

	/**
	 * 删除广告栏目服务
	 * 
	 * @param categoryId
	 */
	public void deleteService(Long serviceId) {
		categoryServiceDao.delete(serviceId);
	}

	/**
	 * 取得广告栏目 list
	 * 
	 * @param categoryId
	 *            广告栏目
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<BizCategoryService> getServiceListByCategory(Long categoryId) {
		return categoryServiceDao.getServiceListByCategory(categoryId);
	}

	/**
	 * 取得广告栏目服务 page
	 * 
	 * @param pageInfo
	 * @param categoryId
	 *            广告栏目
	 * @param categoryName
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<BizCategoryService> getServicePage(
			PageInfo<BizCategoryService> pageInfo, Long categoryId,
			String serviceName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From BizCategoryService a ");
		if (categoryId != null) {
			sb.append("Where a.serviceCategory.categoryId=" + categoryId);
			if (StringUtil.isNotEmpty(serviceName))
				sb.append(" And a.serviceName like " + "'%" + serviceName
						+ "%'");
		} else {
			if (StringUtil.isNotEmpty(serviceName))
				sb.append(" Where a.serviceName like " + "'%" + serviceName
						+ "%'");
		}
		pageInfo = categoryServiceDao.listQuery(pageInfo, "Select Count(*)"
				+ sb.toString(), sb.toString() + " Order by a.serviceOrder ");
		return pageInfo;
	}

	/**
	 * 取得联合广告服务
	 * 
	 * @param adsId
	 *            广告
	 * @param serviceId
	 *            广告服务
	 * @return
	 */
	@Transactional(readOnly = true)
	public com.weaforce.cms.entity.ads.AdsService getAdsService(Long adsId,
			Long serviceId) {
		return adsServiceDao.getAdsService(adsId, serviceId);
	}

	/**
	 * 保存联合广告栏目服务
	 * 
	 * @param o
	 * @param adsId
	 *            广告
	 * @param categoryId
	 *            广告栏目服务
	 * 
	 * @return
	 */
	public com.weaforce.cms.entity.ads.AdsService saveAdsService(
			com.weaforce.cms.entity.ads.AdsService o, Long adsId, Long serviceId) {
		if (adsId != null)
			o.setServiceAds(adsDao.loadEntity(adsId));
		else
			o.setServiceAds(null);
		if (serviceId != null)
			o.setServiceService(categoryServiceDao.loadEntity(serviceId));
		else
			o.setServiceService(null);

		return adsServiceDao.save(o);
	}

	/**
	 * 根据商家服务,以JSON格式取得像册 list
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
	public String getAlbumListJSONByAdsService(String account, Long adsId,
			Long serviceId, String albumName, String dateFrom, String dateTo) {
		com.weaforce.cms.entity.ads.AdsService o = adsServiceDao.getAdsService(
				adsId, serviceId);
		String albumIds = o.getAdsServiceAlbum();
		return albumDao.getAlbumListJSON(account, albumIds, albumName,
				dateFrom, dateTo);
	}

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
	public String saveAdsServiceAlbum(Long adsId, Long serviceId, Long albumId) {
		com.weaforce.cms.entity.ads.AdsService o = adsServiceDao.getAdsService(
				adsId, serviceId);
		String serviceAlbum = o.getAdsServiceAlbum();
		String flag = "0";
		if (serviceAlbum == null || "".equals(serviceAlbum)) {
			o.setAdsServiceAlbum(albumId.toString());
			flag = "1";
		} else {
			serviceAlbum = "," + serviceAlbum + ",";
			if (serviceAlbum.indexOf("," + albumId + ",") == -1) {
				o.setAdsServiceAlbum(serviceAlbum.substring(1,
						serviceAlbum.length())
						+ albumId);
				flag = "1";
			} else {
				serviceAlbum = StringUtil.replaceAll(serviceAlbum, ","
						+ albumId + ",", ",");
				o.setAdsServiceAlbum(serviceAlbum.substring(1,
						serviceAlbum.length() - 1));
			}
		}
		o = adsServiceDao.save(o);
		if ("1".equals(flag))
			return "[{\"returnMsg\":\"增加成功!\"}]";
		else
			return "[{\"returnMsg\":\"删除成功!\"}]";
	}

	/**
	 * 删除联合广告栏目服务
	 * 
	 * @param adsId
	 *            广告
	 * @param categoryId
	 *            广告栏目服务
	 */
	public void deleteAdsService(Long adsId, Long serviceId) {
		com.weaforce.cms.entity.ads.AdsService o = adsServiceDao.getAdsService(
				adsId, serviceId);
		if (o != null)
			adsServiceDao.delete(o);
	}

	/**
	 * parse 联合广告栏目服务,需要保存URL
	 * 
	 * @param adsId
	 *            广告
	 * @param serviceId
	 *            广告栏目服务
	 * @throws Exception
	 */
	public void parseAdsServce(Long adsId, Long serviceId) throws Exception {
		com.weaforce.cms.entity.ads.AdsService o = adsServiceDao.getAdsService(
				adsId, serviceId);
		if (o != null) {
			BizCategory category = o.getServiceAds().getAdsCategory();
			String cityName = category.getCategoryChannel().getChannelCity()
					.getCityNameCn();
			BizCategoryService service = o.getServiceService();
			String template = service.getServiceTemplate().getTemplateContent();
			List<AlbumPhoto> photoList = photoDao.getPhotoListByAlbum(o
					.getAdsServiceAlbum());
			AdsParser.parseService(o, template, service, o.getServiceAds(),
					category, cityName, photoList);
			adsServiceDao.save(o);
		}
	}

	/**
	 * 根据广告，取得广告栏目服务 list
	 * 
	 * @param adsId
	 *            广告
	 * @return
	 */
	public List<com.weaforce.cms.entity.ads.AdsService> getAdsServiceListByAds(
			Long adsId) {
		return adsServiceDao.getAdsServiceListByAds(adsId);
	}

	/**
	 * 预处理标签
	 * 
	 * @param o
	 * @param tagId
	 * @param categoryId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Tag prepareTag(Tag o, Long tagId, Long categoryId) {
		if (tagId == null) {
			o = new Tag();
			if (categoryId != null)
				o.setTagCategory(categoryDao.loadEntity(categoryId));
		} else
			o = tagDao.loadEntity(tagId);
		return o;
	}

	/**
	 * 取得标签
	 * 
	 * @param tagId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Tag getTag(Long tagId) {
		return tagDao.loadEntity(tagId);
	}

	/**
	 * 保存标签
	 * 
	 * @param o
	 * @param categoryId
	 *            广告栏目
	 * @return
	 */
	public Tag saveTag(Tag o, Long categoryId) {
		if (categoryId != null)
			o.setTagCategory(categoryDao.loadEntity(categoryId));
		else
			o.setTagCategory(null);
		return tagDao.save(o);
	}

	/**
	 * 删除标签
	 * 
	 * @param tagId
	 */
	public void deleteTag(Long tagId) {
		tagDao.delete(tagId);
	}

	/**
	 * 根据广告栏目,取得标签 list
	 * 
	 * @param categoryId
	 *            广告栏目
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Tag> getTagListByCategory(Long categoryId) {
		return tagDao.getTagListByCategory(categoryId);
	}

	/**
	 * 取得标签 page
	 * 
	 * @param pageInfo
	 * @param categoryId
	 *            广告栏目
	 * @param tagName
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<Tag> getTagPage(PageInfo<Tag> pageInfo, Long categoryId,
			String tagName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Tag a ");
		if (categoryId != null) {
			sb.append("Where a.tagCategory.categoryId=" + categoryId);
			if (StringUtil.isNotEmpty(tagName))
				sb.append(" And a.tagName like " + "'%" + tagName + "%'");
		} else {
			if (StringUtil.isNotEmpty(tagName))
				sb.append(" Where a.tagName like " + "'%" + tagName + "%'");
		}
		pageInfo = tagDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.tagOrder ");
		return pageInfo;
	}

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
	@Transactional(readOnly = true)
	public Ads prepareAds(Ads o, Long adsId, Long zoneId, Long categoryId) {
		if (adsId == null) {
			o = new Ads();
			if (zoneId != null)
				o.setAdsZone(zoneDao.loadEntity(zoneId));
			if (categoryId != null)
				o.setAdsCategory(categoryDao.loadEntity(categoryId));
		} else
			o = adsDao.loadEntity(adsId);
		return o;
	}

	/**
	 * 取得广告
	 * 
	 * @param adsId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Ads getAds(Long adsId) {
		return adsDao.loadEntity(adsId);
	}

	/**
	 * 以JSON格式,取得广告名称,同时保存点击率
	 * 
	 * @param adsId
	 *            广告主键
	 * @return
	 */
	public String getAdsName(Long adsId) {
		Ads o = adsDao.loadEntity(adsId);
		o.setAdsHit(o.getAdsHit() + 1);
		adsDao.save(o);
		return "[{\"adsName\":\"" + o.getAdsName() + "\"}]";
	}

	/**
	 * 保存广告
	 * 
	 * @param o
	 * @param categoryId
	 *            广告栏目
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
			throws Exception {

		if (categoryId != null)
			o.setAdsCategory(categoryDao.loadEntity(categoryId));
		else
			o.setAdsCategory(null);
		if (zoneId != null)
			o.setAdsZone(zoneDao.loadEntity(zoneId));
		else
			o.setAdsZone(null);
		if (tagId != null)
			o.setAdsTag(tagDao.loadEntity(tagId));
		else
			o.setAdsTag(null);
		CollectionUtil.mergeByCheckedIds(o.getAdsService(), checkedCategoryIds,
				BizCategoryService.class, "serviceId");
		for (int i = 1; i < styleItem.length; i++) {
			if (StringUtil.isNotEmpty(styleItem[i])) {
				AdsStyle s = new AdsStyle();
				if (styleId[i] == null) {
					s.setStyleAds(o);
					s.setStyleItem(styleItem[i]);
					o.getAdsStyle().add(s);
				} else {
					s = styleDao.loadEntity(styleId[i]);
					s.setStyleItem(styleItem[i]);
				}
			} else {
				if (styleId[i] != null) {
					AdsStyle s = styleDao.loadEntity(styleId[i]);
					o.getAdsStyle().remove(s);
				}
			}

		}
		// 商家网站主页
		if (StringUtil.isEmpty(o.getAdsWeb()))
			o.setAdsWeb(o.getCreateTime() + ".html");
		return adsDao.save(o);
	}

	/**
	 * 删除广告
	 * 
	 * @param adsId
	 */
	public void deleteAds(Long adsId) {
		adsDao.delete(adsId);
	}

	/**
	 * 根据广告栏目,取得广告 list
	 * 
	 * @param categoryId
	 *            广告栏目
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Ads> getAdsListByCategory(Long categoryId) {
		return adsDao.getAdsListByCategoryParse(categoryId, "1");
	}

	/**
	 * 根据区域主键,取得广告商家 list
	 * 
	 * @param zoneId
	 *            区域主键
	 * @return
	 */
	public List<Ads> getAdsListByZone(Long zoneId) {
		return adsDao.getAdsListByZone(zoneId);
	}

	/**
	 * 根据区域主键/广告栏目,取得广告商家 list
	 * 
	 * @param zoneId
	 * @param categoryId
	 * @param flag
	 * @return
	 */
	public List<Ads> getAdsListByZoneCategory(Long zoneId, Long categoryId,
			boolean flag) {
		return adsDao.getAdsListByZoneCategory(zoneId, categoryId, "1", flag);
	}

	/**
	 * 取得广告JSON格式数据
	 * 
	 * @param categoryId
	 *            广告栏目:一定不能为空
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getAdsJSONByCategory(Long categoryId) {
		StringBuffer sb = new StringBuffer();
		List<Ads> adsList = adsDao.getAdsListByCategoryParse(categoryId, "1");
		for (Ads o : adsList) {
			if (sb.length() == 0) {
				sb.append("[{\"adsId\":\"" + o.getAdsId() + "\",\"adsName\":\""
						+ o.getAdsName() + "\",\"adsUrl\":\"" + o.getAdsUrl()
						+ "\"}");
			} else {
				sb.append(",{\"adsId\":\"" + o.getAdsId() + "\",\"adsName\":\""
						+ o.getAdsName() + "\",\"adsUrl\":\"" + o.getAdsUrl()
						+ "\"}");
			}
		}
		if (sb.length() > 0)
			sb.append("]");
		System.out.println("sb: " + sb.toString());
		return sb.toString();
	}

	/**
	 * 根据栏目，取得广告 JSON
	 * 
	 * @param categoryId
	 *            栏目
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getAdsDDL(Long categoryId) {
		StringBuffer sb = new StringBuffer();
		List<Ads> adsList = adsDao.getAdsListByCategoryParse(categoryId, "1");
		for (Ads o : adsList) {
			if (sb.length() == 0)
				sb.append("[");
			else
				sb.append(",");
			sb.append("{\"value\":\"" + o.getAdsId() + "\",\"caption\":\""
					+ o.getAdsName() + "\"}");
		}
		if (sb.length() > 0)
			sb.append("]");
		return sb.toString();
	}

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
	@Transactional(readOnly = true)
	public String getAdsDDL(Long zoneId, Long categoryId, boolean flag) {
		StringBuffer sb = new StringBuffer();
		List<Ads> adsList = adsDao.getAdsListByZoneCategory(zoneId, categoryId,
				"1", flag);
		for (Ads o : adsList) {
			if (sb.length() == 0)
				sb.append("[");
			else
				sb.append(",");
			sb.append("{\"value\":\"" + o.getAdsId() + "\",\"caption\":\""
					+ o.getAdsName() + "\"}");
		}
		if (sb.length() > 0)
			sb.append("]");
		return sb.toString();
	}

	/**
	 * 取得广告 page
	 * 
	 * @param pageInfo
	 * @param categoryId
	 *            广告栏目
	 * @param zoneId
	 *            地区
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<Ads> getAdsPage(PageInfo<Ads> pageInfo, Long categoryId,
			Long zoneId, String adsName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Ads a Where a.adsCategory.categoryId=" + categoryId
				+ " And a.adsZone.zoneId=" + zoneId);
		if (StringUtils.isNotEmpty(adsName))
			sb.append(" And a.adsName like '%" + adsName + "%' ");
		pageInfo = adsDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.adsName ");
		return pageInfo;
	}

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
	public String getAdsPageJSON(Long categoryId, Long zoneId, Long tagId,
			String adsName, Integer pageNumber) {
		return adsDao.getAdsPageJSON(categoryId, zoneId, tagId, adsName,
				pageNumber);
	}

	/**
	 * 取得广告风格
	 * 
	 * @param styleId
	 *            主键
	 * @return
	 */
	@Transactional(readOnly = true)
	public AdsStyle getStyle(Long styleId) {
		return styleDao.loadEntity(styleId);
	}

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
	public void parseAds(Long adsId) throws Exception {
		Ads o = adsDao.loadEntity(adsId);
		if (o != null) {
			o.setAdsIsParse("1");
			BizCategory category = o.getAdsCategory();
			String cityName = category.getCategoryChannel().getChannelCity()
					.getCityNameCn();
			List<com.weaforce.cms.entity.ads.AdsService> serviceList = adsServiceDao
					.getAdsServiceListByAds(adsId);
			// parse 商家服务
			for (com.weaforce.cms.entity.ads.AdsService a : serviceList) {
				BizCategoryService service = a.getServiceService();
				String template = service.getServiceTemplate()
						.getTemplateContent();
				List<AlbumPhoto> photoList = photoDao.getPhotoListByAlbum(a
						.getAdsServiceAlbum());
				AdsParser.parseService(a, template, service, o, category,
						cityName, photoList);
				adsServiceDao.save(a);
			}
			o = AdsParser.parseAds(o, serviceList, category);
			// 商家主页
			parseAdsSite(o, category, serviceList);
			adsDao.save(o);
		}
	}

	/**
	 * parse 广告商家主页,该方法没有出现在接口中
	 * 
	 * @param o
	 *            广告商家
	 * @param category
	 *            栏目
	 * @param serviceList
	 *            广告商家服务
	 * @throws Exception
	 */
	public void parseAdsSite(Ads o, BizCategory category,
			List<com.weaforce.cms.entity.ads.AdsService> serviceList)
			throws Exception {
		// 取得当前栏目所在城市
		Long cityId = category.getCategoryChannel().getChannelCity()
				.getCityId();
		String cityName = category.getCategoryChannel().getChannelCity()
				.getCityNameCn();
		String menu = getChannelCategoryDDM(cityId);
		// 生成商家网站主页
		List<Ads> friendList = adsDao.getAdsList(0, 5);
		AdsParser.saveHtml(AdsParser.parseAdsSite(o, friendList, serviceList,
				category, cityName, menu), category.getCategoryParsePath(),
				o.getCreateTime() + ".html");
	}

	/**
	 * parse 栏目下所有的广告商家及服务为HTML
	 * 
	 * @param categoryId
	 *            商家栏目主键
	 * @throws Exception
	 */
	public void parseAdsAll(Long categoryId) throws Exception {
		BizCategory category = categoryDao.loadEntity(categoryId);
		String cityName = category.getCategoryChannel().getChannelCity()
				.getCityNameCn();
		if (category != null) {
			List<Ads> adsList = adsDao.getAdsListByCategoryParse(categoryId,
					"1");
			for (Ads o : adsList) {
				o.setAdsIsParse("1");
				List<com.weaforce.cms.entity.ads.AdsService> serviceList = adsServiceDao
						.getAdsServiceListByAds(o.getAdsId());
				AdsParser.parseAds(o, serviceList, category);
				for (com.weaforce.cms.entity.ads.AdsService a : serviceList) {
					BizCategoryService service = a.getServiceService();
					String template = service.getServiceTemplate()
							.getTemplateContent();
					List<AlbumPhoto> photoList = photoDao.getPhotoListByAlbum(a
							.getAdsServiceAlbum());
					AdsParser.parseService(a, template, service, o, category,
							cityName, photoList);
				}
				parseAdsSite(o, category, serviceList);
				adsDao.save(o);
			}

		}
	}

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
	@Transactional(readOnly = true)
	public AdsDiscount prepareDiscount(AdsDiscount o, Long discountId,
			Long adsId) {
		if (discountId == null) {
			o = new AdsDiscount();
			if (adsId != null)
				o.setDiscountAds(adsDao.loadEntity(adsId));
		} else
			o = discountDao.loadEntity(discountId);
		return o;
	}

	/**
	 * 取得广告信息
	 * 
	 * @param discountId
	 * @return
	 */
	@Transactional(readOnly = true)
	public AdsDiscount getDiscount(Long discountId) {
		return discountDao.loadEntity(discountId);
	}

	/**
	 * 保存广告信息
	 * 
	 * @param o
	 * @param adsId
	 *            广告
	 * @return
	 */
	public AdsDiscount saveDiscount(AdsDiscount o, Long adsId) {
		if (adsId != null)
			o.setDiscountAds(adsDao.loadEntity(adsId));
		else
			o.setDiscountAds(null);
		return discountDao.save(o);

	}

	/**
	 * 删除广告信息
	 * 
	 * @param discountId
	 */
	public void deleteDiscount(Long discountId) {
		discountDao.delete(discountId);
	}

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
	@Transactional(readOnly = true)
	public String getDiscountJSON(Long categoryId, String discountTitle,
			Integer pageNumber) {
		return discountDao.getDiscountJSON(categoryId, discountTitle,
				pageNumber);
	}

	/**
	 * 取得广告信息 page
	 * 
	 * @param pageInfo
	 * @param adsId
	 *            广告
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<AdsDiscount> getDiscountPage(
			PageInfo<AdsDiscount> pageInfo, Long categoryId,
			String discountTitle, String adsName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From AdsDiscount a Where a.discountAds.adsCategory.categoryId="
				+ categoryId);
		if (StringUtil.isNotEmpty(discountTitle))
			sb.append(" And a.discountTitle like " + "'%" + discountTitle
					+ "%'");
		if (StringUtil.isNotEmpty(adsName))
			sb.append(" And a.discountAds.adsName like " + "'%" + adsName
					+ "%'");
		pageInfo = discountDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.discountTitle ");
		return pageInfo;
	}

	/**
	 * parse 打折信息
	 * 
	 * @param discountId
	 * @throws Exception
	 */
	public void parseDiscount(String account, Long discountId) throws Exception {
		AdsDiscount o = discountDao.loadEntity(discountId);
		if (o != null) {
			BizCategory category = o.getDiscountAds().getAdsCategory();
			Long cityId = category.getCategoryChannel().getChannelCity()
					.getCityId();
			// 取得当前栏目所在城市
			String cityName = category.getCategoryChannel().getChannelCity()
					.getCityNameCn();
			String menu = getChannelCategoryDDM(cityId);
			Long categoryId = category.getCategoryId();
			List<Ads> adsList = adsDao.getAdsListByCategoryParse(categoryId,
					"1");
			List<AdsDiscount> discountList = discountDao
					.getDiscountListByCategory(categoryId, 0, 4);
			List<Meta> metaList = metaDao.getMetaList(account);
			Integer discountCount = discountDao
					.getDiscountCountByCategory(categoryId);
			AdsParser.parseDiscount(o, cityName, category, adsList,
					discountList, metaList, menu, discountCount);
			o = discountDao.save(o);
		}
	}

	/**
	 * parse 栏目打折 list
	 * 
	 * @param account
	 *            帐套
	 * @param categoryId
	 *            　栏目主键
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public void parseDiscountPage(String account, Long categoryId)
			throws Exception {
		BizCategory category = categoryDao.getEntity(categoryId);
		if (category != null) {
			Long cityId = category.getCategoryChannel().getChannelCity()
					.getCityId();
			String menu = getChannelCategoryDDM(cityId);
			List<Meta> metaList = metaDao.getMetaList(account);
			Integer discountCount = discountDao
					.getDiscountCountByCategory(categoryId);
			String cityName = category.getCategoryChannel().getChannelCity()
					.getCityNameCn();
			// parse栏目下所有打折信息
			List<AdsDiscount> discountList = discountDao
					.getDiscountListByCategory(categoryId);
			for (AdsDiscount o : discountList) {
				parseDiscount(account, o.getDiscountId());
				o = discountDao.save(o);
			}
			AdsParser.parseDiscountPage(category, metaList, cityName, menu,
					discountCount);
		}
	}

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
	@Transactional(readOnly = true)
	public CategoryTip prepareTip(CategoryTip o, Long tipId, Long categoryId) {
		if (tipId == null) {
			o = new CategoryTip();
			if (categoryId != null)
				o.setTipCategory(categoryDao.loadEntity(categoryId));
		} else
			o = tipDao.loadEntity(tipId);
		return o;
	}

	/**
	 * 取得小贴士
	 * 
	 * @param tipId
	 * @return
	 */
	@Transactional(readOnly = true)
	public CategoryTip getTip(Long tipId) {
		return tipDao.loadEntity(tipId);
	}

	/**
	 * 保存小贴士
	 * 
	 * @param o
	 * @param categoryId
	 *            广告栏目
	 * @param fromId
	 *            来源
	 * @return
	 */
	public CategoryTip saveTip(CategoryTip o, Long categoryId, Long fromId) {
		if (categoryId != null)
			o.setTipCategory(categoryDao.loadEntity(categoryId));
		else
			o.setTipCategory(null);
		if (fromId != null)
			o.setTipFrom(fromDao.loadEntity(fromId));
		else
			o.setTipFrom(null);
		return tipDao.save(o);
	}

	/**
	 * 删除小贴士
	 * 
	 * @param tipId
	 */
	public void deleteTip(Long tipId) {
		tipDao.delete(tipId);
	}

	/**
	 * 取得JSON格式小贴士记录
	 * 
	 * @param categoryId
	 *            栏目主键
	 * @param tipTitle
	 *            标题
	 * @param pageNumber
	 *            当前页数
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getTipJSON(Long categoryId, String tipTitle,
			Integer pageNumber) {
		return tipDao.getTipJSON(categoryId, tipTitle, pageNumber);
	}

	/**
	 * 取得小贴士 page
	 * 
	 * @param pageInfo
	 * @param categoryId
	 *            广告栏目
	 * @param tipTitle
	 *            标题
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<CategoryTip> getTipPage(PageInfo<CategoryTip> pageInfo,
			Long categoryId, String tipTitle) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From CategoryTip a  Where a.tipCategory.categoryId ="
				+ categoryId);
		if (StringUtil.isNotEmpty(tipTitle))
			sb.append(" And a.tipTitle like " + "'%" + tipTitle + "%'");
		pageInfo = tipDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.tipTitle ");
		return pageInfo;
	}

	/**
	 * parse 小贴士
	 * 
	 * @param account
	 *            帐套
	 * @param tipId
	 *            小贴士
	 * @throws Exception
	 */
	public void parseTip(String account, Long tipId) throws Exception {
		CategoryTip o = tipDao.loadEntity(tipId);
		if (o != null) {
			BizCategory category = o.getTipCategory();
			Long cityId = category.getCategoryChannel().getChannelCity()
					.getCityId();
			String cityName = category.getCategoryChannel().getChannelCity()
					.getCityNameCn();
			String menu = getChannelCategoryDDM(cityId);
			List<CategoryTip> tipList = tipDao.getTipListByCategory(
					category.getCategoryId(), 0, 5);
			List<Ads> adsList = adsDao.getAdsListByCategory(
					category.getCategoryId(), 0, 5);
			List<Meta> metaList = metaDao.getMetaList(account);

			AdsParser.parseTip(o, cityName, category, tipList, adsList,
					metaList, menu);
			tipDao.save(o);
		}
	}

	/**
	 * pares 小贴士 page
	 * 
	 * @param account
	 *            帐套
	 * @param categoryId
	 *            栏目主键
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public void parseTipPage(String account, Long categoryId) throws Exception {
		BizCategory category = categoryDao.getEntity(categoryId);
		if (category != null) {
			Long cityId = category.getCategoryChannel().getChannelCity()
					.getCityId();
			String menu = getChannelCategoryDDM(cityId);
			List<Meta> metaList = metaDao.getMetaList(account);
			Integer tipCount = tipDao.getTipCountByCategory(categoryId);
			// parse 栏目下所有贴士
			List<CategoryTip> tipList = tipDao.getTipListByCategory(categoryId);
			for (CategoryTip o : tipList) {
				parseTip(account, o.getTipId());
				o = tipDao.save(o);
			}
			String cityName = category.getCategoryChannel().getChannelCity()
					.getCityNameCn();
			AdsParser
					.parseTipPage(category, metaList, cityName, menu, tipCount);
		}
	}

	/**
	 * 取得短信
	 * 
	 * @param messageId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Message getMessage(Long messageId) {
		return messageDao.loadEntity(messageId);
	}

	/**
	 * 保存短信
	 * 
	 * @param o
	 * @param adsId
	 *            广告
	 * @return
	 */
	public Message saveMessage(Message o, Long adsId) {
		if (adsId != null)
			o.setMessageAds(adsDao.loadEntity(adsId));
		else
			o.setMessageAds(null);
		return messageDao.save(o);

	}

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
			String title) {
		if (StringUtil.isNotEmpty(phone) || StringUtil.isNotEmpty(mobile)) {
			Message o = new Message();
			o.setMessageAds(adsDao.loadEntity(adsId));
			o.setMessagePhone(phone);
			o.setMessageMobile(mobile);
			o.setMessageTitle(title);
			messageDao.save(o);
		}
	}

	/**
	 * 删除短信
	 * 
	 * @param messageId
	 */
	public void deleteMessage(Long messageId) {
		messageDao.delete(messageId);
	}

	/**
	 * 确认信息
	 * 
	 * @param messageId
	 *            主键
	 */

	public void lockMessage(Long messageId) {
		if (messageId != null) {
			Message o = messageDao.loadEntity(messageId);
			o.setMessageIsOk("1");
			messageDao.save(o);
		}
	}

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
	@Transactional(readOnly = true)
	public PageInfo<Message> getMessagePage(PageInfo<Message> pageInfo,
			Long adsId, String messageTitle, String messageMobile, String isOk,
			String dateFrom, String dateTo) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Message a Where a.messageAds.adsId=" + adsId);
		if (StringUtil.isNotEmpty(messageTitle))
			sb.append(" And a.messageTitle like " + "'%" + messageTitle + "%'");
		if (StringUtil.isNotEmpty(messageMobile))
			sb.append(" And a.messageMobile like " + "'%" + messageMobile
					+ "%'");
		if (StringUtil.isNotEmpty(isOk))
			sb.append(" And a.messageIsOk=" + isOk);
		if (StringUtil.isNotEmpty(dateFrom))
			sb.append(" And a.createTime >= " + DateUtil.getUTCDate(dateFrom));
		if (StringUtil.isNotEmpty(dateTo))
			sb.append(" And a.createTime <= " + DateUtil.getUTCDate(dateTo));
		pageInfo = messageDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.messageMobile ");
		return pageInfo;
	}

	/**
	 * 取得首页链接
	 * 
	 * @param linkId
	 *            主键
	 * @return
	 */
	@Transactional(readOnly = true)
	public AdsPageLink getLink(Long linkId) {
		return linkDao.loadEntity(linkId);
	}

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
			Long categoryId, Long adsId, Long templateId) {
		if (parentId != null)
			o.setLinkParent(linkDao.loadEntity(parentId));
		if (channelId != null) {
			BizChannel channel = channelDao.loadEntity(channelId);
			o.setLinkLevel(Byte.valueOf("1"));
			o.setLinkTitle(channel.getChannelName());
			o.setLinkOrder(channel.getChannelOrder());
			o.setLinkChannel(channelDao.loadEntity(channelId));
		}
		if (categoryId != null) {
			BizCategory category = categoryDao.loadEntity(categoryId);
			o.setLinkLevel(Byte.valueOf("2"));
			o.setLinkTitle(category.getCategoryName());
			o.setLinkOrder(category.getCategoryOrder());
			o.setLinkUrl(category.getCategoryUrl());
			o.setLinkCategory(categoryDao.loadEntity(categoryId));
		}
		if (adsId != null) {
			Ads ads = adsDao.loadEntity(adsId);
			o.setLinkLevel(Byte.valueOf("3"));
			o.setLinkTitle(ads.getAdsName());
			o.setLinkUrl(ads.getAdsUrl());
			o.setLinkPictureUrl(ads.getAdsPictureUrl());
			o.setLinkAds(adsDao.loadEntity(adsId));
		}
		if (templateId != null)
			o.setLinkTemplate(templateDao.loadEntity(templateId));
		return linkDao.save(o);
	}

	/**
	 * 删除首页链接
	 * 
	 * @param linkId
	 *            主键
	 */
	public void deleteLink(Long linkId) {
		linkDao.delete(linkId);
	}

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
			Long categoryId, Long adsId) {
		List<AdsPageLink> tempList = linkDao.getLinkListByLevel(linkLevel);
		List<AdsPageLink> linkList = new ArrayList<AdsPageLink>();
		// 频道
		if (categoryId != null) {
			BizChannel channel = categoryDao.loadEntity(categoryId)
					.getCategoryChannel();
			for (AdsPageLink o : tempList)
				if (channel.getChannelName().equals(o.getLinkTitle())) {
					o.setLinkTitle(o.getLinkParent().getLinkTitle() + "-"
							+ o.getLinkTitle());
					linkList.add(o);
				}
		} else if (adsId != null) { // 栏目
			BizCategory category = adsDao.loadEntity(adsId).getAdsCategory();
			for (AdsPageLink o : tempList)
				if (category.getCategoryName().equals(o.getLinkTitle())) {
					o.setLinkTitle(o.getLinkParent().getLinkTitle() + "-"
							+ o.getLinkTitle());
					linkList.add(o);
				}
		} else
			// 定义页
			linkList = tempList;
		return linkList;
	}

	/**
	 * 取得页面树
	 * 
	 * @param parentId
	 *            父节点
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getLinkTree(Long parentId) {
		StringBuffer sb = new StringBuffer("[");
		AdsPageLink link = linkDao.loadEntity(parentId);
		sb.append("{\"linkId\":\"" + link.getLinkId()
				+ "\",\"linkFid\":\"\",\"linkName\":\"" + link.getLinkTitle()
				+ "\"}");
		List<AdsPageLink> linkList = new ArrayList<AdsPageLink>();
		sb = linkChildren(parentId, 0, sb, linkList);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * 取当前节点的所有子节点:应用递规算法取得子节点:该方法没有出现在Interface中
	 * 
	 * @param linkId
	 *            当前的link主键
	 * @param lastIndex
	 *            当前link位置
	 * @param sb
	 *            JSON格式的link
	 * @param linkList
	 * @return
	 */
	@Transactional(readOnly = true)
	public StringBuffer linkChildren(Long linkId, Integer lastIndex,
			StringBuffer sb, List<AdsPageLink> linkList) {
		List<AdsPageLink> linkChildren = linkDao.getEntity(linkId)
				.getLinkChildren();
		if (linkChildren.size() > 0)
			linkList.addAll(lastIndex, linkChildren);
		if (lastIndex < linkList.size()) {
			AdsPageLink o = linkList.get(lastIndex);
			sb.append(",{\"linkId\":\"" + o.getLinkId() + "\",\"linkFid\":\""
					+ o.getLinkParent().getLinkId() + "\",\"linkName\":\""
					+ o.getLinkTitle() + "\"}");
			lastIndex++;
			return linkChildren(linkList.get(lastIndex - 1).getLinkId(),
					lastIndex, sb, linkList);
		} else
			return sb;
	}

	/**
	 * pares 专页
	 * 
	 * @param cityName
	 * @param account
	 *            帐套
	 * @param linkId
	 *            主键
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public void parseLink(String account, String cityName, Long linkId)
			throws Exception {
		AdsPageLink root = linkDao.loadEntity(linkId);
		String template = root.getLinkTemplate().getTemplateContent();
		String parsePath = root.getLinkParsePath();
		// 替换模板中用户自定义meta信息
		template = AdsParser
				.replaceMeta(template, metaDao.getMetaList(account));
		// 0:首页;1:搜索页面
		if ("0".equals(root.getLinkType())) {
			// 这里没有城市信息
			template = parseLinkIndex(template, cityName, linkId);
		} else if ("1".equals(root.getLinkType())) {
			// 明星商家list:默认只有一个,备以后扩展
			List<AdsPageLink> adsList = linkDao.getLinkListByLevel(Byte
					.valueOf("3"));
			if (adsList.size() > 0) {
				BizCategory category = adsList.get(0).getLinkParent()
						.getLinkCategory();
				Long cityId = category.getCategoryChannel().getChannelCity()
						.getCityId();
				// 菜单
				String menu = getChannelCategoryDDM(cityId);
				template = parseLinkSearch(template, cityName, menu, adsList);
			}
		}
		// 保存首页文件
		AdsParser.saveHtml(template, parsePath, root.getLinkParseFile());
	}

	/**
	 * pares 首页:该方法没有出现在接口中
	 * <p>
	 * <ul>
	 * <li>META</li>
	 * <li>business_box</li>
	 * <li>discount_box</li>
	 * <li>side_content_box</li>
	 * </ul>
	 * </p>
	 * 
	 * @param template
	 *            模板
	 * @param linkId
	 *            首页ID
	 * @return
	 */
	@Transactional(readOnly = true)
	public String parseLinkIndex(String template, String cityName, Long linkId) {
		// 城市名称
		template = StringUtil.replaceAll(template, "{$CITYNAME$}", cityName);
		// 服务区
		List<AdsPageLink> channelList = linkDao.getLinkListByParent(linkId);
		StringBuffer sbLink = new StringBuffer();
		StringBuffer sbChannel = new StringBuffer(
				"<div class=\"business_list\">");
		for (int i = 0; i < channelList.size(); i++) {
			AdsPageLink channel = channelList.get(i);
			List<AdsPageLink> categoryList = linkDao
					.getLinkListByParent(channel.getLinkId());
			StringBuffer sbCategory = new StringBuffer();
			for (AdsPageLink category : categoryList)
				sbCategory.append(AdsParser.parseLinkCategory(category));
			sbChannel.append(AdsParser.parseLinkChannel(channel, sbCategory));
			if (i % 2 != 0) {
				sbChannel.append("</div>");
				sbLink.append(sbChannel.toString());
				// System.out.println("sbChannel: " + sbChannel.toString());
				sbChannel = new StringBuffer("<div class=\"business_list\">");
			}
		}
		template = StringUtil.replaceAll(template, "{$BUSINESS$}",
				sbLink.toString());
		// 打折区(8)
		List<AdsDiscount> discountList = discountDao.getDiscountList(0, 7);
		StringBuffer sb = new StringBuffer();
		// for (AdsDiscount discount : discountList)
		// sb.append(AdsUtil.parseDiscountCell(discount));
		template = StringUtil.replaceAll(template, "{$DISCOUNT$}",
				AdsParser.parseDiscountCell(null, discountList));
		// 小贴士区(10)
		List<CategoryTip> tipList = tipDao.getTipList(0, 9);
		template = StringUtil.replaceAll(template, "{$TIP$}",
				AdsParser.parseTipCell(null, tipList));
		// 广告区(20)
		List<Ads> adsList = adsDao.getAdsList(0, 19);
		sb = new StringBuffer();
		for (Ads ads : adsList)
			sb.append(AdsParser.parseAdsLink(ads));
		template = StringUtil.replaceAll(template, "{$ADS$}", sb.toString());
		return template;
	}

	/**
	 * pares 搜索页:该方法没有出现在接口中
	 * 
	 * @param template
	 *            模板
	 * @param linkId
	 *            搜索页ID
	 * @return
	 */
	@Transactional(readOnly = true)
	public String parseLinkSearch(String template, String cityName,
			String menu, List<AdsPageLink> adsList) {
		// 城市名称
		template = StringUtil.replaceAll(template, "{$CITYNAME$}", cityName);
		// 服务菜单
		template = StringUtil.replaceAll(template, "{$MENU$}", menu);
		// 明星商家
		template = StringUtil.replaceAll(template, "{$ADSSTAR$}",
				AdsParser.parseAdsStar(adsList.get(0).getLinkAds()));
		return template;
	}

	/**
	 * 取得首页链接 page
	 * 
	 * @param pageInfo
	 * @param linkTitle
	 *            标题
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<AdsPageLink> getLinkPage(PageInfo<AdsPageLink> pageInfo,
			String linkTitle) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From AdsPageLink a ");
		if (StringUtil.isNotEmpty(linkTitle))
			sb.append(" Where a.linkTitle like " + "'%" + linkTitle + "%'");
		pageInfo = linkDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.linkOrder ");
		return pageInfo;
	}

	/**
	 * 预处理网站
	 * 
	 * @param o
	 * @param siteId
	 * @return
	 */
	public CategorySite prepareSite(CategorySite o, Long siteId) {
		if (siteId == null)
			o = new CategorySite();
		else
			o = siteDao.loadEntity(siteId);
		return o;
	}

	/**
	 * 取得网站
	 * 
	 * @param siteId
	 *            网站主键
	 * @return
	 */
	@Transactional(readOnly = true)
	public CategorySite getSite(Long siteId) {
		return siteDao.loadEntity(siteId);
	}

	/**
	 * 保存网站
	 * 
	 * @param o
	 *            网站
	 * @param categoryId
	 *            栏目主键
	 * @return
	 */
	public CategorySite saveSite(CategorySite o, Long categoryId) {
		if (categoryId != null)
			o.setSiteCategory(categoryDao.loadEntity(categoryId));
		else
			o.setSiteCategory(null);
		return siteDao.save(o);
	}

	/**
	 * 删除网站
	 * 
	 * @param siteId
	 *            网站主键
	 */
	public void deleteSite(Long siteId) {
		siteDao.delete(siteId);
	}

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
	@Transactional(readOnly = true)
	public PageInfo<CategorySite> getSitePage(PageInfo<CategorySite> pageInfo,
			Long categoryId, String siteName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From CategorySite a  Where a.siteCategory.categoryId ="
				+ categoryId);
		if (StringUtil.isNotEmpty(siteName))
			sb.append(" And a.siteName like " + "'%" + siteName + "%'");
		pageInfo = siteDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.siteName ");
		return pageInfo;
	}

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
	@Transactional(readOnly = true)
	public AdsPay preparePay(AdsPay o, Long payId, Long adsId) {
		if (payId == null) {
			o = new AdsPay();
			if (adsId != null)
				o.setPayAds(adsDao.loadEntity(adsId));
		} else
			o = payDao.loadEntity(payId);
		return o;
	}

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
	public AdsPay savePay(AdsPay o, Long adsId, BigDecimal payPayBefore) {
		if (adsId == null)
			return null;
		Ads ads = adsDao.loadEntity(adsId);
		o.setPayAds(ads);
		// 修改付款统计
		if (o.getPayPay().compareTo(payPayBefore) != 0) {
			ads.setAdsPayTotal(ads.getAdsPayTotal().subtract(payPayBefore));
			ads.setAdsPayTotal(ads.getAdsPayTotal().add(o.getPayPay()));
			adsDao.save(ads);
		}
		return payDao.save(o);
	}

	/**
	 * 删除付款明细
	 * 
	 * @param payId
	 *            付款明细主键
	 */
	public void deletePay(Long payId) {
		payDao.delete(payId);
	}

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
	@Transactional(readOnly = true)
	public PageInfo<AdsPay> getPayPage(PageInfo<AdsPay> pageInfo,
			String account, Long adsId, String payTitle, String dateFrom,
			String dateTo) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From AdsPay a Where a.account= '" + account + "'");
		if (adsId != null)
			sb.append(" And a.payAds.adsId=" + adsId);
		if (StringUtil.isNotEmpty(payTitle))
			sb.append(" And a.payTitle like '%" + payTitle + "%'");
		if (StringUtil.isNotEmpty(dateFrom))
			sb.append(" And a.createTime >= " + DateUtil.getUTCDate(dateFrom));
		if (StringUtil.isNotEmpty(dateTo))
			sb.append(" And a.createTime <= " + DateUtil.getUTCDate(dateTo));
		pageInfo = payDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.createTime desc ");
		return pageInfo;
	}

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
	@Transactional(readOnly = true)
	public Product prepareProduct(Product o, Long productId, Long adsId) {
		if (productId == null) {
			o = new Product();
			if (adsId != null)
				o.setProductAds(adsDao.loadEntity(adsId));
		} else
			o = productDao.loadEntity(productId);

		return o;
	}

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
	public Product saveProduct(Product o, Long adsId, Long templateId) {
		if (adsId == null)
			o.setProductAds(null);
		else
			o.setProductAds(adsDao.loadEntity(adsId));
		if (templateId == null)
			o.setProductTemplate(null);
		else
			o.setProductTemplate(templateDao.loadEntity(templateId));
		return productDao.save(o);
	}

	/**
	 * 删除团购产品
	 * 
	 * @param productId
	 *            产品主键
	 */
	public void deleteProduct(Long productId) {
		if (productId != null) {
			Product o = productDao.loadEntity(productId);
			if (o != null) {
				if (o.getProductDeal().size() == 0)
					productDao.delete(o);
			}
		}
	}

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
	@Transactional(readOnly = true)
	public PageInfo<Product> getProductPage(PageInfo<Product> pageInfo,
			Long categoryId, String productName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Product a ");
		if (categoryId != null) {
			sb.append(" Where a.productAds.adsCategory.categoryId ="
					+ categoryId);
			if (StringUtil.isNotEmpty(productName))
				sb.append(" And a.productName like '%" + productName + "%'");
		} else {
			if (StringUtil.isNotEmpty(productName))
				sb.append(" Where a.productName like '%" + productName + "%'");
		}
		pageInfo = productDao.listQuery(pageInfo,
				"Select Count(*) " + sb.toString(), sb.toString());
		return pageInfo;
	}

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
	@Transactional(readOnly = true)
	public Deal prepareDeal(Deal o, Long dealId, Long productId) {
		if (dealId == null) {
			o = new Deal();
			if (productId != null)
				o.setDealProduct(productDao.loadEntity(productId));
		} else
			o = dealDao.loadEntity(dealId);
		return o;
	}

	/**
	 * 保存交易
	 * 
	 * @param o
	 *            　交易
	 * @param productId
	 *            　产品主键
	 * @return
	 */
	public Deal saveDeal(Deal o, Long productId) {
		if (productId == null)
			o.setDealProduct(null);
		else
			o.setDealProduct(productDao.loadEntity(productId));
		return dealDao.save(o);
	}

	/**
	 * 删除交易
	 * 
	 * @param dealId
	 *            交易主键
	 */
	public void deleteDeal(Long dealId) {
		if (dealId != null)
			dealDao.delete(dealId);
	}

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
	@Transactional(readOnly = true)
	public PageInfo<Deal> getDealPage(PageInfo<Deal> pageInfo, Long productId,
			String dateFrom, String dateTo) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Deal a ");
		if (productId == null) {
			if (StringUtil.isNotEmpty(dateFrom)) {
				sb.append(" Where a.createTime >= "
						+ DateUtil.getUTCDate(dateFrom));
				if (StringUtil.isNotEmpty(dateTo))
					sb.append(" And a.createTime <= "
							+ DateUtil.getUTCDate(dateTo));
			} else if (StringUtil.isNotEmpty(dateTo))
				sb.append(" Where a.createTime <= "
						+ DateUtil.getUTCDate(dateTo));
		} else {
			sb.append(" Where a.dealProduct.productId =" + productId);
			if (StringUtil.isNotEmpty(dateFrom))
				sb.append(" And a.createTime >= "
						+ DateUtil.getUTCDate(dateFrom));
			if (StringUtil.isNotEmpty(dateTo))
				sb.append(" And a.createTime <= " + DateUtil.getUTCDate(dateTo));
		}

		pageInfo = dealDao.listQuery(pageInfo,
				"Select Count(*)" + sb.toString(), sb.toString()
						+ " Order by a.createTime desc ");
		return pageInfo;
	}

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
	@Transactional(readOnly = true)
	public Order prepareOrder(Order o, Long orderId, Long dealId) {
		if (orderId == null) {
			o = new Order();
			if (dealId != null)
				o.setOrderDeal(dealDao.loadEntity(dealId));
		} else
			o = orderDao.loadEntity(orderId);
		return o;
	}

	/**
	 * 保存团购订单
	 * 
	 * @param o
	 *            　订单
	 * @param dealId
	 *            　交易主键
	 * @return
	 */
	public Order saveOrder(Order o, Long dealId) {
		if (dealId == null)
			o.setOrderDeal(null);
		else
			o.setOrderDeal(dealDao.loadEntity(dealId));
		return orderDao.save(o);
	}

	/**
	 * 删除团购订单
	 * 
	 * @param orderId
	 *            　订单主键
	 */
	public void deleteOrder(Long orderId) {
		if (orderId != null)
			orderDao.delete(orderId);
	}

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
	@Transactional(readOnly = true)
	public PageInfo<Order> getOrderPage(PageInfo<Order> pageInfo,
			Long categoryId, String orderAddress, String dateFrom, String dateTo) {
		StringBuffer sb = new StringBuffer();
		sb.append(" From Order a ");
		boolean flag = false;
		if (categoryId != null) {
			if (!flag) {
				sb.append(" Where a.orderDeal.dealProduct.productCategory.categoryId ="
						+ categoryId);
				flag = true;
			} else {
				sb.append(" And a.orderDeal.dealProduct.productCategory.categoryId ="
						+ categoryId);
			}
		}
		if (StringUtil.isNotEmpty(orderAddress)) {
			if (!flag) {
				sb.append(" Where a.orderAddress like '%" + orderAddress + "%'");
				flag = true;
			} else
				sb.append(" And a.orderAddress like '%" + orderAddress + "%'");
		}
		if (StringUtil.isNotEmpty(dateFrom)) {
			if (!flag) {
				sb.append(" Where a.createTime >= "
						+ DateUtil.getUTCDate(dateFrom));
				flag = true;
			} else
				sb.append(" And a.createTime >= "
						+ DateUtil.getUTCDate(dateFrom));
		}
		if (StringUtil.isNotEmpty(dateTo)) {
			if (!flag) {
				sb.append(" Where a.createTime <= "
						+ DateUtil.getUTCDate(dateTo));
				flag = true;
			} else
				sb.append(" And a.createTime <= " + DateUtil.getUTCDate(dateTo));
		}
		return pageInfo;
	}
}
