package com.weaforce.cms.util;

import java.util.Date;
import java.util.List;

import com.weaforce.cms.entity.AlbumPhoto;
import com.weaforce.cms.entity.Meta;
import com.weaforce.cms.entity.ads.Ads;
import com.weaforce.cms.entity.ads.AdsDiscount;
import com.weaforce.cms.entity.ads.AdsPageLink;
import com.weaforce.cms.entity.ads.AdsService;
import com.weaforce.cms.entity.ads.AdsStyle;
import com.weaforce.cms.entity.ads.BizCategory;
import com.weaforce.cms.entity.ads.BizCategoryService;
import com.weaforce.cms.entity.ads.BizChannel;
import com.weaforce.cms.entity.ads.CategoryTip;
import com.weaforce.cms.entity.ads.Tag;
import com.weaforce.core.util.DateUtil;
import com.weaforce.core.util.Global;
import com.weaforce.core.util.StringUtil;
import com.weaforce.entity.area.Zone;

/**
 * 广告工具类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public class AdsParser extends AbstractParser {
	/**
	 * parse 广告商家，保存商家HTML单元于数据库中
	 * <p>
	 * <ul>
	 * <li>标题</li>
	 * <li>图片</li>
	 * <li>内容</li>
	 * <li>广告栏目服务</li>
	 * </ul>
	 * </p>
	 * 
	 * @param o
	 *            广告商家
	 * @param serviceList
	 *            商家服务
	 * @throws Exception
	 */
	public static Ads parseAds(Ads o, List<AdsService> serviceList,
			BizCategory category) throws Exception {
		// 取得单元模板
		String template = o.getAdsCategory().getCategoryAdsTemplate()
				.getTemplateContent();
		template = parseAds(o, template);
		// 广告风格
		StringBuffer sb = new StringBuffer();
		for (AdsStyle s : o.getAdsStyle())
			sb.append(parseAdsStyle(s));
		template = StringUtil.replaceAll(template, "{$ADSSTYLE$}",
				sb.toString());
		o.setAdsUrl(category.getCategoryParsePath() + "/" + o.getCreateTime()
				+ ".html");
		// 广告服务(菜单)
		sb = parseAdsService(o, serviceList);
		if (sb.length() > 0)
			template = StringUtil.replaceAll(template, "{$ADSSERVICE$}",
					sb.toString());
		else
			template = StringUtil.replaceAll(template, "{$ADSSERVICE$}", "");
		// 保存内容至数据库
		o.setAdsContentHtml(template);
		return o;
	}

	/**
	 * parse 广告商家的基本信息
	 * 
	 * @param o
	 *            广告商家
	 * @param template
	 *            模板
	 * @return
	 */
	public static String parseAds(Ads o, String template) {
		// 替代模板内容
		template = StringUtil.replaceAll(template, "{$ADSID$}", o.getAdsId()
				.toString());
		// 名称
		template = StringUtil.replaceAll(template, "{$ADSNAME$}",
				o.getAdsName());
		// 主页
		template = StringUtil.replaceAll(template, "{$ADSWEB$}", o.getAdsWeb());
		// 图片URL
		template = StringUtil.replaceAll(template, "{$ADSPICTUREURL$}",
				o.getAdsPictureUrl());
		// 电话
		template = StringUtil.replaceAll(template, "{$ADSPHONE$}",
				o.getAdsPhone());
		// 电话工作时间
		template = StringUtil.replaceAll(template, "{$ADSPHONETIME$}",
				o.getAdsPhoneTime());
		// 电话工作日期
		template = StringUtil.replaceAll(template, "{$ADSPHONEDAY$}",
				o.getAdsPhoneDay());
		// 地址
		template = StringUtil.replaceAll(template, "{$ADSADDRESS$}",
				o.getAdsAddress());
		// 上联
		template = StringUtil.replaceAll(template, "{$ADSINFOLEFT$}",
				o.getAdsInfoLeft());
		// 下联
		template = StringUtil.replaceAll(template, "{$ADSINFORIGHT$}",
				o.getAdsInfoRight());
		return template;
	}

	/**
	 * parse 明星广告商家
	 * 
	 * @param o
	 *            广告商家
	 * @return
	 */
	public static String parseAdsStar(Ads o) {
		// 取得单元模板
		String template = o.getAdsCategory().getCategoryAdsStarTemplate()
				.getTemplateContent();
		template = parseAds(o, template);
		return template;
	}

	/**
	 * parse 广告风格内容
	 * 
	 * @param o
	 *            广告风格
	 * @return
	 */
	public static String parseAdsStyle(AdsStyle o) {
		String templateCell = "<p class=\"adstyle_l\">{$STYLEITEM$}</p>";
		templateCell = StringUtil.replaceAll(templateCell, "{$STYLEITEM$}",
				o.getStyleItem());
		return templateCell;
	}

	/**
	 * parse 广告服务链接
	 * 
	 * @param category
	 *            广告栏目
	 * @param ads
	 *            广告
	 * @param o
	 *            服务
	 * @return
	 */
	public static StringBuffer parseAdsService(Ads ads,
			List<AdsService> serviceList) {
		StringBuffer sb = new StringBuffer();
		for (com.weaforce.cms.entity.ads.AdsService o : serviceList) {
			String templateCell = "<li class=\"show_hide\"><a onClick=\"javascript:loadService('{$SERVICEFILENAME$}','{$SERVICEURL$}',{$ADSID$},'{$MAPX$}','{$MAPY$}');\" href=\"javascript:;\">{$SERVICENAME$}</a></li>";
			// 广告ID,用于定位加载广告服务的div="service_content_{$ADSID$}"
			templateCell = StringUtil.replaceAll(templateCell, "{$ADSID$}", ads
					.getAdsId().toString());
			// X坐标
			templateCell = StringUtil.replaceAll(templateCell, "{$MAPX$}",
					ads.getAdsMapX());
			// Y坐标
			templateCell = StringUtil.replaceAll(templateCell, "{$MAPY$}",
					ads.getAdsMapY());
			// 服务名称
			templateCell = StringUtil.replaceAll(templateCell,
					"{$SERVICENAME$}", o.getServiceService().getServiceName());
			// 服务URL
			templateCell = StringUtil.replaceAll(templateCell,
					"{$SERVICEURL$}", o.getAdsServiceUrl());
			// 服务文件名
			templateCell = StringUtil.replaceAll(templateCell,
					"{$SERVICEFILENAME$}", o.getServiceService()
							.getServiceFileName().toLowerCase());
			sb.append(templateCell);
		}
		return sb;
	}

	/**
	 * parse 广告商家服务为HTML,
	 * <p>
	 * <ul>
	 * <li>文件名称:createTime + "-" + serviceFileName + ".html"</li>
	 * <li>物理路径:a.getAdsCategory().getCategoryParsePath()</li>
	 * </ul>
	 * </p>
	 * 
	 * @param o
	 *            广告服务
	 * @param template
	 *            服务模板
	 * @param service
	 *            商家服务定义
	 * @param ads
	 *            广告商家
	 * @param category
	 *            栏目
	 * @param photoList
	 *            照片 list
	 * @throws Exception
	 */
	public static String parseService(com.weaforce.cms.entity.ads.AdsService o,
			String template, BizCategoryService service, Ads ads,
			BizCategory category, String cityName, List<AlbumPhoto> photoList)
			throws Exception {
		// 替换关联照片
		if (photoList.size() > 0)
			template = StringUtil.replaceAll(template, "{$PHOTO$}",
					HtmlParser.parsePhotoCell(photoList));
		else
			template = StringUtil.replaceAll(template, "{$PHOTO$}", "");
		// 城市
		template = StringUtil.replaceAll(template, "{$CITYNAME$}", cityName);
		// 栏目名称
		template = StringUtil.replaceAll(template, "{$CATEGORYNAME$}",
				category.getCategoryName());
		// 广告商家信息
		template = StringUtil.replaceAll(template, "{$ADSNAME$}",
				ads.getAdsName());
		template = StringUtil.replaceAll(template, "{$ADSID$}", ads.getAdsId()
				.toString());
		template = StringUtil
				.replaceAll(template, "{$MAPX$}", ads.getAdsMapX());
		template = StringUtil
				.replaceAll(template, "{$MAPY$}", ads.getAdsMapY());
		// 服务信息
		template = StringUtil.replaceAll(template, "{$SERVICETITLE$}",
				o.getAdsServiceTitle());
		template = StringUtil.replaceAll(template, "{$SERVICEFILENAME$}", o
				.getServiceService().getServiceFileName());
		if (o.getAdsServiceContent() == null
				|| "".equals(o.getAdsServiceContent()))
			template = StringUtil.replaceAll(template, "{$CONTENT$}", "");
		else
			template = StringUtil.replaceAll(template, "{$CONTENT$}",
					o.getAdsServiceContent());
		// HTML文件
		o.setAdsServiceUrl(ads.getCreateTime() + "-"
				+ service.getServiceFileName() + ".html");
		HtmlParser.saveHtml(template, category.getCategoryParsePath(),
				ads.getCreateTime() + "-" + service.getServiceFileName()
						+ ".html");
		return template;
	}

	/**
	 * parse 广告商家网站
	 * 
	 * @param o
	 *            广告商家
	 * @param friendList
	 *            友好商家
	 * @param serviceList
	 *            商家服务
	 * @param category
	 *            栏目
	 * @param cityName
	 *            城市
	 * @return
	 */
	public static String parseAdsSite(Ads o, List<Ads> friendList,
			List<AdsService> serviceList, BizCategory category,
			String cityName, String menu) {
		// 取得网站模板
		String template = category.getCategorySiteTemplate()
				.getTemplateContent();
		// 城市名称
		template = StringUtil.replaceAll(template, "{$CITYNAME$}", cityName);
		template = parseCategory(category, template, cityName, menu);
		// 因为有{$ADSWEB$}标签，所以要把parseAds(o,template)方法放在后面执行
		StringBuffer sbMenu = new StringBuffer(
				"<li class=\"hover\"><a href=\"{$ADSWEB$}\" id=\"index\">商家主页</a></li>");
		StringBuffer sbService = new StringBuffer();
		// 与还原的内容不同:基本信息菜单项
		String templateMenuCell = "<li><a onclick=\"loadService('{$SERVICEFILENAME$}','{$SERVICEURL$}','{$ADSID$}','{$MAPX$}','{$MAPY$}')\" href=\"javascript:void(0);\" id=\"ji_ben_xin_xi\">商家主页</a></li>";
		String templateServiceCell = "<div class=\"column\"><div class=\"column_title\"><h2>{$SERVICENAME$}</h2></div><div class=\"column_content\">{$SERVICECONTENT$}</div></div>";
		for (AdsService s : serviceList) {
			BizCategoryService service = s.getServiceService();
			String serviceName = service.getServiceName();
			String serviceUrl = s.getAdsServiceUrl();
			String serviceFileName = service.getServiceFileName();
			String serviceContent = s.getAdsServiceContent();
			templateMenuCell = StringUtil.replaceAll(templateMenuCell,
					"{$SERVICEURL$}", serviceUrl);
			templateMenuCell = StringUtil.replaceAll(templateMenuCell,
					"{$ADSID$}", o.getAdsId().toString());
			// X坐标(javascript)
			templateMenuCell = StringUtil.replaceAll(templateMenuCell,
					"{$MAPX$}", o.getAdsMapX());
			// Y坐标(javascript)
			templateMenuCell = StringUtil.replaceAll(templateMenuCell,
					"{$MAPY$}", o.getAdsMapY());
			sbMenu.append(StringUtil.replaceAll(templateMenuCell,
					"{$SERVICENAME$}", serviceName));
			// 还原左菜单单元
			templateMenuCell = "<li><a onclick=\"loadService('{$SERVICEFILENAME$}','{$SERVICEURL$}','{$ADSID$}','{$MAPX$}','{$MAPY$}')\" href=\"javascript:void(0);\" id=\"ji_ben_xin_xi\">{$SERVICENAME$}</a></li>";
			// 地图/照片不放在商家信息首页里
			if (!"map".equals(serviceFileName)
					|| "photo".equals(serviceFileName)) {
				// 服务名称
				templateServiceCell = StringUtil.replaceAll(
						templateServiceCell, "{$SERVICENAME$}", serviceName);
				// URL地址
				templateServiceCell = StringUtil.replaceAll(
						templateServiceCell, "{$SERVICEURL$}", serviceUrl);
				templateServiceCell = StringUtil.replaceAll(
						templateServiceCell, "{$SERVICEFILENAME$}",
						serviceFileName);
				// 内容
				templateServiceCell = StringUtil.replaceAll(
						templateServiceCell, "{$SERVICECONTENT$}",
						serviceContent);
				sbService.append(templateServiceCell);
				// 还原内容
				templateServiceCell = "<div class=\"column\"><div class=\"column_title\"><h2>{$SERVICENAME$}</h2></div><div class=\"column_content\">{$SERVICECONTENT$}</div></div>";
			}
		}
		template = StringUtil.replaceAll(template, "{$LEFTMENU$}",
				sbMenu.toString());
		template = StringUtil.replaceAll(template, "{$SERVICE$}",
				sbService.toString());
		// 友好商家
		StringBuffer sb = new StringBuffer();
		for (Ads a : friendList)
			sb.append(parseAdsFriendCell(a));
		template = StringUtil.replaceAll(template, "{$ADSFRIEND$}",
				sb.toString());
		// X坐标(html template)
		template = StringUtil.replaceAll(template, "{$MAPX$}", o.getAdsMapX());
		// Y坐标(html template)
		template = StringUtil.replaceAll(template, "{$MAPY$}", o.getAdsMapY());
		template = parseAds(o, template);
		return template;
	}

	/**
	 * parse 友好商家单元,链接在商家网站主页下方
	 * 
	 * @param o
	 *            广告商家
	 * @return
	 */
	public static String parseAdsFriendCell(Ads o) {
		String templateCell = "<li><div class=\"merchant_img\"><a target=\"_blank\" href=\"{$ADSWEB$}\"><img alt=\"{$ADSNAME$}\" src=\"{$ADSPICTUREURL$}\"></a></div><h3><a target=\"_blank\" href=\"{$ADSWEB$}\">{$ADSNAME$}</a></h3><p>{$ADSSTYLE$}</p></li>";
		templateCell = parseAds(o, templateCell);
		return templateCell;
	}

	/**
	 * parse 广告栏目:默认为index.html
	 * 
	 * @param o
	 *            广告栏目
	 * @param categoryList
	 *            相关广告栏目 list
	 * @param categoryCount
	 *            同一频道下栏目数量
	 * @param tagSet
	 *            标签
	 * @param tipList
	 *            小贴士 list
	 * @param discountList
	 *            打折 list
	 * @param metaList
	 *            META list
	 * @throws Exception
	 */
	public static void parseCategory(BizCategory o, String cityName,
			List<BizCategory> categoryList, List<Tag> tagList,
			List<Zone> zoneList, List<CategoryTip> tipList,
			List<AdsDiscount> discountList, List<Meta> metaList, String menu)
			throws Exception {
		String template = o.getCategoryTemplate().getTemplateContent();
		// 替换模板中的贴士模块
		template = StringUtil.replaceAll(template, "{$TIP$}",
				parseTipCell(o, tipList));
		// 替换模板中的打折模块
		template = StringUtil.replaceAll(template, "{$DISCOUNT$}",
				parseDiscountCell(o, discountList));
		// 替换模板中用户自定义meta信息
		template = HtmlParser.replaceMeta(template, metaList);
		// 相关栏目
		template = StringUtil.replaceAll(template, "{$RELATION$}",
				parseCategoryRelation(categoryList));

		// 替换模板中的标签模块
		template = StringUtil.replaceAll(template, "{$TAG$}",
				parseTagCell(tagList, Long.parseLong("0")));
		// 替换模板中的区域模块
		template = StringUtil.replaceAll(template, "{$ZONE$}",
				parseZoneCell(zoneList, Long.parseLong("0")));
		// 栏目基本信息
		template = parseCategory(o, template, cityName, menu);
		// 广告数量/翻页组件
		int maxRowCount = o.getCategoryAds().size();
		StringBuffer sb = new StringBuffer(
				"<li><a class=\"now\" ref=\"1\">1</a></li>");
		int rowsPerPage = 10;
		int maxPage = 1;
		if (maxRowCount % rowsPerPage == 0)
			maxPage = maxRowCount / rowsPerPage;
		else
			maxPage = maxRowCount / rowsPerPage + 1;
		if (maxPage > 1) {
			for (int i = 2; i < maxPage; i++)
				sb.append("<li><a class=\"now\" ref=\"" + i + "\">" + i
						+ "</a></li>");
		}
		template = StringUtil.replaceAll(template, "{$PAGE$}", sb.toString());
		HtmlParser.saveHtml(template, o.getCategoryParsePath(), "index.html");
	}

	/**
	 * parse 栏目基本信息
	 * 
	 * @param o
	 *            栏目
	 * @param template
	 *            模板
	 * @param cityName
	 *            城市名称
	 * @param menu
	 *            下拉菜单
	 * @return
	 */
	public static String parseCategory(BizCategory o, String template,
			String cityName, String menu) {
		// 城市名称
		template = StringUtil.replaceAll(template, "{$CITYNAME$}", cityName);
		// 服务菜单
		template = StringUtil.replaceAll(template, "{$MENU$}", menu);
		// 栏目ID
		template = StringUtil.replaceAll(template, "{$CATEGORYID$}", o
				.getCategoryId().toString());
		// 栏目名称
		template = StringUtil.replaceAll(template, "{$CATEGORYNAME$}",
				o.getCategoryName());
		// 特殊CSS
		template = StringUtil.replaceAll(template, "{$CATEGORYCSS$}",
				o.getCategoryCss());
		// 栏目URL:网站规划阶段在WEB服务器上设定的访问地址
		template = StringUtil.replaceAll(template, "{$CATEGORYURL$}",
				o.getCategoryUrl());
		return template;
	}

	/**
	 * parse 广告栏目中的链拉信息
	 * 
	 * @param o
	 *            栏目
	 * @param template
	 *            模板
	 * @param categoryCount
	 *            栏目数量
	 * @param categoryList
	 *            相关栏目
	 * @param tagSet
	 *            当前栏下的标签
	 * @param zoneList
	 *            当前城市下的区
	 * @param tipList
	 *            当前栏目下的贴士
	 * @param discountList
	 *            当前栏目下的打折
	 * @param metaList
	 *            meta信息
	 * @throws Exception
	 */
	public static String parseCategory(BizCategory o, String template,
			List<BizCategory> categoryList, List<CategoryTip> tipList,
			List<AdsDiscount> discountList, List<Meta> metaList)
			throws Exception {
		// 相关栏目
		template = StringUtil.replaceAll(template, "{$RELATION$}",
				parseCategoryRelation(categoryList));
		// 替换模板中的贴士模块
		template = StringUtil.replaceAll(template, "{$TIP$}",
				parseTipCell(o, tipList));
		// 替换模板中的打折模块
		template = StringUtil.replaceAll(template, "{$DISCOUNT$}",
				parseDiscountCell(o, discountList));
		// 替换模板中用户自定义meta信息
		template = HtmlParser.replaceMeta(template, metaList);
		return template;
	}

	/**
	 * Parse当前条件下的Tag标签
	 * 
	 * @param tagList
	 * @param tagId
	 * @return
	 */
	public static String parseTagCell(List<Tag> tagList, Long tagId) {
		StringBuffer sb = new StringBuffer();
		String templateCell = "<li><a href=\"javascript:;\"{$CLASS$}onClick=\"javascript:tagClick(this,{$TAGID$})\">{$TAGTAG$}</a></li>";
		if (tagId == 0)
			sb.append("<li><a href=\"javascript:;\" class=\"now\" onClick=\"javascript:tagClick(this,0)\">不限</a></li>");
		else
			sb.append("<li><a href=\"javascript:;\" onClick=\"javascript:tagClick(this,0)\">不限</a></li>");
		for (Tag o : tagList) {
			templateCell = StringUtil.replaceAll(templateCell, "{$TAGID$}", o
					.getTagId().toString());
			templateCell = StringUtil.replaceAll(templateCell, "{$TAGTAG$}", o
					.getTagTag().toString());
			if (tagId == o.getTagId())
				sb.append(StringUtil.replaceAll(templateCell, "{$CLASS$}",
						" class=\"now\" "));
			else
				sb.append(StringUtil.replaceAll(templateCell, "{$CLASS$}", " "));
		}

		return sb.toString();
	}

	/**
	 * Parse当前条件下的Zone标签
	 * 
	 * @param zoneList
	 * @param zoneId
	 * @return
	 */
	public static String parseZoneCell(List<Zone> zoneList, Long zoneId) {
		StringBuffer sb = new StringBuffer();
		String templateCell = "<li><a href=\"javascript:;\"{$CLASS$}onClick=\"javascript:zoneClick(this,{$ZONEID$})\">{$ZONENAME$}</a></li>";
		if (zoneId == 0)
			sb.append("<li><a href=\"javascript:;\" class=\"now\" onClick=\"javascript:zoneClick(this,0)\">不限</a></li>");
		else
			sb.append("<li><a href=\"javascript:;\" onClick=\"javascript:zoneClick(this,0)\">不限</a></li>");
		for (Zone o : zoneList) {
			templateCell = StringUtil.replaceAll(templateCell, "{$ZONEID$}", o
					.getZoneId().toString());
			templateCell = StringUtil.replaceAll(templateCell, "{$ZONENAME$}",
					o.getZoneName().toString());
			if (o.getZoneId() == zoneId)
				sb.append(StringUtil.replaceAll(templateCell, "{$CLASS$}",
						" class=\"now\" "));
			else
				sb.append(StringUtil.replaceAll(templateCell, "{$CLASS$}", " "));
		}
		return sb.toString();

	}

	/**
	 * parse 相关（同级）广告栏目
	 * 
	 * @param categoryList
	 *            相关栏目 list
	 * @param categoryCount
	 *            同一频道下栏目数量
	 * @return
	 */
	public static String parseCategoryRelation(List<BizCategory> categoryList) {
		StringBuffer sb = new StringBuffer();
		for (BizCategory o : categoryList) {
			String templateCell = "<li><a href=\"{$URL$}\" target=\"_blank\">{$NAME$}</a>({$COUNT$})</li>";
			// 栏目名称
			templateCell = StringUtil.replaceAll(templateCell, "{$NAME$}",
					o.getCategoryName());
			// URL地址
			templateCell = StringUtil.replaceAll(templateCell, "{$URL$}",
					o.getCategoryUrl());
			// 广告数量
			templateCell = StringUtil.replaceAll(templateCell, "{$COUNT$}",
					String.valueOf(o.getCategoryAds().size()));
			sb.append(templateCell);
		}
		return sb.toString();
	}

	/**
	 * parse 广告打折单元:用于首页及栏目
	 * 
	 * @param o
	 *            广告打折
	 * @param template
	 *            模板
	 */
	public static String parseDiscountCell(BizCategory category,
			List<AdsDiscount> discountList) {
		StringBuffer sb = new StringBuffer();
		for (AdsDiscount o : discountList) {
			System.out.println(o.getDiscountUrl());
			String templateCell = "<li><h4><a target=\"_blank\" href=\"{$URL$}\">{$TITLE$}</a></h4><p>{$INTRO$}</p><p><strong>发布商家：</strong><a href=\"{$ADSWEB$}\" target=\"_blank\">{$ADSADDRESS$}</a></p></li>";
			templateCell = StringUtil.replaceAll(templateCell, "{$URL$}",
					o.getDiscountUrl());
			templateCell = StringUtil.replaceAll(templateCell, "{$TITLE$}",
					o.getDiscountTitle());
			templateCell = StringUtil.replaceAll(templateCell, "{$INTRO$}",
					o.getDiscountIntro());
			// 广告主页
			templateCell = StringUtil.replaceAll(templateCell, "{$ADSWEB$}", o
					.getDiscountAds().getAdsWeb());
			// 广告地址
			templateCell = StringUtil.replaceAll(templateCell,
					"{$ADSADDRESS$}", o.getDiscountAds().getAdsAddress());
			sb.append(templateCell);
		}
		if (category != null)
			sb.append("<p class=\"more\"><a href=\""
					+ category.getCategoryUrl()
					+ "/discount\" target=\"_blank\">更多"
					+ category.getCategoryName() + "打折优惠</a></p>");
		return sb.toString();
	}

	/**
	 * parse 广告栏目小贴士单元
	 * 
	 * @param o
	 *            小贴士
	 * @param template
	 *            模板
	 */
	public static String parseTipCell(BizCategory category,
			List<CategoryTip> tipList) {
		StringBuffer sb = new StringBuffer();
		for (CategoryTip o : tipList) {
			String templateCell = "<li><a target=\"_blank\" title=\"{$TITLE$}\" href=\"{$URL$}\">{$TITLE$}</a></li>";
			templateCell = StringUtil.replaceAll(templateCell, "{$TITLE$}",
					o.getTipTitle());
			templateCell = StringUtil.replaceAll(templateCell, "{$URL$}",
					o.getTipUrl());
			sb.append(templateCell);
		}
		if (category != null)
			sb.append("<li class=\"more\"><a target=\"_blank\" href=\""
					+ category.getCategoryUrl() + "/tip\">更多</a></li>");
		return sb.toString();
	}

	/**
	 * parse 小贴士为HTML,保存在栏目下子目录"tip"下
	 * 
	 * @param o
	 *            小贴士
	 * @param cityName
	 *            城市名称
	 * @param tipList
	 *            相关小贴士 list
	 * @param adsList
	 *            广告 list
	 * @throws Exception
	 */

	public static void parseTip(CategoryTip o, String cityName,
			BizCategory category, List<CategoryTip> tipList, List<Ads> adsList,
			List<Meta> metaList, String menu) throws Exception {
		String template = category.getCategoryTipArticleTemplate()
				.getTemplateContent();
		// 城市名称
		template = StringUtil.replaceAll(template, "{$CITYNAME$}", cityName);
		// 栏目ID
		template = StringUtil.replaceAll(template, "{$CATEGORYID$}", category
				.getCategoryId().toString());
		// 栏目名称
		template = StringUtil.replaceAll(template, "{$CATEGORYNAME$}",
				category.getCategoryName());
		// 栏目URL
		template = StringUtil.replaceAll(template, "{$CATEGORYURL$}",
				category.getCategoryUrl());
		// 小贴士URL
		template = StringUtil.replaceAll(template, "{$TIPURL$}",
				category.getCategoryUrl() + "/tip");
		// 小贴士标题
		template = StringUtil.replaceAll(template, "{$TIPTITLE$}",
				o.getTipTitle());
		// 小贴士来源名称
		template = StringUtil.replaceAll(template, "{$COPYFROM$}", o
				.getTipFrom().getFromName());
		// 小贴士来源URL
		template = StringUtil.replaceAll(template, "{$COPYFROMWEB$}", o
				.getTipFrom().getFromWeb());
		// 小贴士发布
		template = StringUtil.replaceAll(template, "{$CREATETIME$}",
				DateUtil.completeFormat(new Date(System.currentTimeMillis())));
		// 小贴士内容
		template = StringUtil.replaceAll(template, "{$TIPCONTENT$}",
				o.getTipContent());
		// 广告栏目名称
		template = StringUtil.replaceAll(template, "{$CATEGORYNAME$}",
				category.getCategoryName());
		// 广告栏目URL:网站规划阶段在WEB服务器上设定的访问地址
		template = StringUtil.replaceAll(template, "{$CATEGORYURL$}",
				category.getCategoryUrl());
		// 替换模板中的相关联的小贴士模块
		template = StringUtil.replaceAll(template, "{$TIPRELATION$}",
				parseTipCell(category, tipList));
		// 替换模板中的热门商家(前五名)广告模块
		template = StringUtil.replaceAll(template, "{$ADS$}",
				parseAdsCell(category, adsList));
		// 保存parse后的URL及物理地址
		o.setTipUrl(category.getCategoryUrl() + "/tip/" + o.getCreateTime()
				+ ".html");
		o.setTipLocation(category.getCategoryParsePath() + "/tip/"
				+ o.getCreateTime() + ".html");
		o.setTipIsParse("1");
		// 服务菜单
		template = StringUtil.replaceAll(template, "{$MENU$}", menu);
		// 替换模板中用户自定义meta信息
		template = HtmlParser.replaceMeta(template, metaList);
		HtmlParser.saveHtml(template, category.getCategoryParsePath() + "/tip",
				o.getCreateTime() + ".html");
	}

	/**
	 * parse 热门商家:链接在其它网页如小贴士中的广告单元
	 * 
	 * @param category
	 *            商家栏目
	 * @param adsList
	 *            商家列表
	 * @return
	 */
	public static String parseAdsCell(BizCategory category, List<Ads> adsList) {
		StringBuffer sb = new StringBuffer();
		for (Ads o : adsList) {
			String templateCell = "<li><div class=\"star_business_text\"><h3><a href=\"{$ADSWEB$}\" target=\"_blank\">{$ADSNAME$}</a><p>{$ADSPHONE$}</p></h3></div><div class=\"star_business_img\"><a href=\"{$ADSWEB$}\" target=\"_blank\"><img src=\"{$ADSPICTUREURL$}\" alt=\"{$ADSNAME$}\"></a></div></li>";
			templateCell = StringUtil.replaceAll(templateCell, "{$ADSNAME$}",
					o.getAdsName());
			templateCell = StringUtil.replaceAll(templateCell, "{$ADSWEB$}",
					o.getAdsWeb());
			templateCell = StringUtil.replaceAll(templateCell, "{$ADSPHONE$}",
					o.getAdsPhone());
			templateCell = StringUtil.replaceAll(templateCell,
					"{$ADSPICTUREURL$}", o.getAdsPictureUrl());
			sb.append(templateCell);
		}
		if (category != null)
			sb.append("<li class=\"more\"><a href=\""
					+ category.getCategoryUrl() + "\">查看更多</a></li>");
		return sb.toString();
		// return templateCell;
	}

	/**
	 * parse 首页最新商家
	 * 
	 * @param o
	 *            广告
	 * @return
	 */
	public static String parseAdsLink(Ads o) {
		String templateCell = "<li><a href=\"{$ADSWEB$}\" target=\"_blank\"><img src=\"{$ADSPICTUREURL$}\" alt=\"{$ADSNAME$}\"></a><p>更新时间：{$SYSTEMTIME$}</p></li>";
		templateCell = StringUtil.replaceAll(templateCell, "{$ADSNAME$}",
				o.getAdsName());
		templateCell = StringUtil.replaceAll(templateCell, "{$ADSWEB$}",
				o.getAdsWeb());
		templateCell = StringUtil.replaceAll(templateCell, "{$ADSPICTUREURL$}",
				o.getAdsPictureUrl());
		// 更新时间
		templateCell = StringUtil.replaceAll(templateCell, "{$SYSTEMTIME$}",
				(DateUtil.completeFormat(new Date(System.currentTimeMillis())))
						.toString());
		return templateCell;
	}

	/**
	 * 根据单元模板,parse 频道单元
	 * 
	 * @param o
	 *            频道
	 * @param sbCategory
	 *            栏目单元
	 * @return
	 */
	public static String parseChannelCell(BizChannel o, StringBuffer sbCategory) {
		String templateCell = ("<li class=\"channel\"><strong>{$CHANNELNAME$}</strong><div style=\"display: none;\"><ul>{$CATEGORY$}</ul><span class=\"filled_box\"></span></div></li>");
		templateCell = StringUtil.replaceAll(templateCell, "{$CHANNELNAME$}",
				o.getChannelName());
		templateCell = StringUtil.replaceAll(templateCell, "{$CATEGORY$}",
				sbCategory.toString());
		return templateCell;
	}

	/**
	 * 根据单元模板,parse 栏目单元
	 * 
	 * @param o
	 *            栏目
	 * @return
	 */
	public static String parseCategoryCell(BizCategory o) {
		String templateCell = "<li><a href=\"{$CATEGORYURL$}\">{$CATEGORYNAME$}</a>（{$COUNT$}）</li>";
		templateCell = StringUtil.replaceAll(templateCell, "{$CATEGORYURL$}",
				o.getCategoryUrl());
		templateCell = StringUtil.replaceAll(templateCell, "{$CATEGORYNAME$}",
				o.getCategoryName());
		// 广告数量
		templateCell = StringUtil.replaceAll(templateCell, "{$COUNT$}",
				String.valueOf(o.getCategoryAds().size()));
		return templateCell;
	}

	/**
	 * parse 打折信息
	 * 
	 * @param o
	 *            打折
	 * @param cityName
	 *            城市名称
	 * @param discountList
	 *            相关打折
	 * @param adsList
	 *            热门商家
	 * @param metaList
	 *            META数据
	 * @throws Exception
	 */

	public static void parseDiscount(AdsDiscount o, String cityName,
			BizCategory category, List<Ads> adsList,
			List<AdsDiscount> discountList, List<Meta> metaList, String menu,
			Integer discountCount) throws Exception {
		String template = category.getCategoryDiscountArticleTemplate()
				.getTemplateContent();
		// 城市
		template = StringUtil.replaceAll(template, "{$CITYNAME$}", cityName);
		// 栏目ID
		template = StringUtil.replaceAll(template, "{$CATEGORYID$}", category
				.getCategoryId().toString());
		// 栏目名称
		template = StringUtil.replaceAll(template, "{$CATEGORYNAME$}",
				category.getCategoryName());
		// 栏目URL
		template = StringUtil.replaceAll(template, "{$CATEGORYURL$}",
				category.getCategoryUrl());
		// 商家图片
		template = StringUtil.replaceAll(template, "{$ADSPICTUREURL$}", o
				.getDiscountAds().getAdsPictureUrl());
		// 栏目打折数量
		template = StringUtil.replaceAll(template, "{$DISCOUNTCOUNT$}",
				discountCount.toString());
		// 打折URL
		template = StringUtil.replaceAll(template, "{$DISCOUNTURL$}",
				category.getCategoryUrl() + "/discount");
		// 打折标题
		template = StringUtil.replaceAll(template, "{$DISCOUNTTITLE$}",
				o.getDiscountTitle());
		// 打折URL
		template = StringUtil.replaceAll(template, "{$DISCOUNTURL$}",
				category.getCategoryUrl() + "/discount");
		// 打折截止时间
		template = StringUtil.replaceAll(template, "{$DISCOUNTDATETO$}", o
				.getDiscountDateTo().toString());
		// 打折开始
		template = StringUtil.replaceAll(template, "{$DISCOUNTDATETODATE$}",
				o.getDiscountDateToDate());
		// 打折结束
		template = StringUtil.replaceAll(template, "{$DISCOUNTDATEFROMDATE$}",
				o.getDiscountDateToDate());
		// 打折内容
		template = StringUtil.replaceAll(template, "{$DISCOUNTCONTENT$}",
				o.getDiscountContent());
		// 栏目名称
		template = StringUtil.replaceAll(template, "{$DISCOUNTCONTENT$}",
				o.getDiscountContent());
		// 广告商家信息
		template = parseDiscountAds(o.getDiscountAds(), template);
		// 替换模板中的打折模块
		StringBuffer sb = new StringBuffer();
		if (discountList.size() > 0) {
			for (AdsDiscount d : discountList)
				sb.append(parseDiscountRelation(d));
			sb.append("<li class=\"more\"><a href=\""
					+ category.getCategoryUrl() + "/discount\">查看更多</a></li>");
			template = StringUtil.replaceAll(template, "{$DISCOUNT$}",
					sb.toString());
		}
		// 替换模板中的热点(前五名)广告模块
		sb = new StringBuffer();
		if (adsList.size() > 0) {
			template = StringUtil.replaceAll(template, "{$ADS$}",
					parseAdsCell(category, adsList));
		}
		o.setDiscountUrl(category.getCategoryUrl() + "/discount/"
				+ o.getCreateTime() + ".html");
		o.setDiscountLocation(category.getCategoryParsePath() + "/discount/"
				+ o.getCreateTime() + ".html");
		o.setDiscountIsParse("1");
		// 服务菜单
		template = StringUtil.replaceAll(template, "{$MENU$}", menu);
		// 替换模板中用户自定义meta信息
		template = HtmlParser.replaceMeta(template, metaList);
		HtmlParser.saveHtml(template, category.getCategoryParsePath()
				+ "/discount", o.getCreateTime() + ".html");
	}

	/**
	 * parse 打折广告单元
	 * 
	 * @param o
	 *            广告
	 * @param template
	 *            模板内容
	 * @return
	 */
	public static String parseDiscountAds(Ads o, String template) {
		template = StringUtil.replaceAll(template, "{$ADSNAME$}",
				o.getAdsName());
		template = StringUtil.replaceAll(template, "{$ADSWEB$}", o.getAdsWeb());
		template = StringUtil.replaceAll(template, "{$ADSPICTUREURL$}",
				o.getAdsPictureUrl());
		template = StringUtil.replaceAll(template, "{$ADSINTRO$}",
				o.getAdsIntro());
		template = StringUtil.replaceAll(template, "{$ADSPHONE$}",
				o.getAdsPhone());
		// 广告风格
		StringBuffer sb = new StringBuffer();
		for (AdsStyle s : o.getAdsStyle())
			sb.append(parseAdsStyle(s));
		template = StringUtil.replaceAll(template, "{$ADSSTYLE$}",
				sb.toString());
		return template;
	}

	/**
	 * parse 相关打折单元
	 * 
	 * @param o
	 *            打折
	 * @return
	 */
	public static String parseDiscountRelation(AdsDiscount o) {
		String templateCell = "<li><a href=\"{$U000U$}\" target=\"_blank\">{$T000T$}</a></li>";
		templateCell = StringUtil.replaceAll(templateCell, "{$U000U$}",
				o.getDiscountUrl());
		templateCell = StringUtil.replaceAll(templateCell, "{$T000T$}",
				o.getDiscountTitle());
		return templateCell;
	}

	/**
	 * parse 栏目打折 list
	 * 
	 * @param category
	 *            栏目
	 * @param metaList
	 *            META信息
	 * @param menu
	 *            菜单
	 * @throws Exception
	 */
	public static void parseDiscountPage(BizCategory category,
			List<Meta> metaList, String cityName, String menu,
			Integer discountCount) throws Exception {
		String template = category.getCategoryDiscountListTemplate()
				.getTemplateContent();
		if (StringUtil.isNotEmpty(template)) {
			// 栏目ID
			template = StringUtil.replaceAll(template, "{$CATEGORYID$}",
					category.getCategoryId().toString());
			// 栏目名称
			template = StringUtil.replaceAll(template, "{$CATEGORYNAME$}",
					category.getCategoryName());
			// 城市
			template = StringUtil
					.replaceAll(template, "{$CITYNAME$}", cityName);
			// 栏目URL
			template = StringUtil.replaceAll(template, "{$CATEGORYURL$}",
					category.getCategoryUrl());
			// 栏目打折数量：
			template = StringUtil.replaceAll(template, "{$DISCOUNTCOUNT$}",
					discountCount.toString());
			// 服务菜单
			template = StringUtil.replaceAll(template, "{$MENU$}", menu);
			// 替换模板中用户自定义meta信息
			template = HtmlParser.replaceMeta(template, metaList);
			HtmlParser.saveHtml(template, category.getCategoryParsePath()
					+ "/discount", "index.html");
		}
	}

	/**
	 * parse 栏目小贴士 list
	 * 
	 * @param category
	 *            栏目
	 * @param metaList
	 *            META信息
	 * @param menu
	 *            菜单
	 * @throws Exception
	 */
	public static void parseTipPage(BizCategory category, List<Meta> metaList,
			String cityName, String menu, Integer tipCount) throws Exception {
		String template = category.getCategoryTipListTemplate()
				.getTemplateContent();
		if (StringUtil.isNotEmpty(template)) {
			// 栏目ID
			template = StringUtil.replaceAll(template, "{$CATEGORYID$}",
					category.getCategoryId().toString());
			// 城市
			template = StringUtil
					.replaceAll(template, "{$CITYNAME$}", cityName);
			// 栏目名称
			template = StringUtil.replaceAll(template, "{$CATEGORYNAME$}",
					category.getCategoryName());
			// 栏目URL
			template = StringUtil.replaceAll(template, "{$CATEGORYURL$}",
					category.getCategoryUrl());
			// 栏目小贴士数量
			template = StringUtil.replaceAll(template, "{$TIPCOUNT$}",
					tipCount.toString());
			// 服务菜单
			template = StringUtil.replaceAll(template, "{$MENU$}", menu);
			// 替换模板中用户自定义meta信息
			template = HtmlParser.replaceMeta(template, metaList);
			HtmlParser.saveHtml(template, category.getCategoryParsePath()
					+ "/tip", "index.html");
		}
	}

	/**
	 * parse 链接频道/栏目单元
	 * 
	 * @param o
	 *            链接
	 * @param sbCategory
	 *            栏止单元
	 * @return
	 */
	public static String parseLinkChannel(AdsPageLink o, StringBuffer sbCategory) {
		String templateCell = "<div class=\"business_list_content\"><h3><span>{$LINKTITLE$}</span></h3><ul>{$CATEGORY$}</ul></div>";
		templateCell = StringUtil.replaceAll(templateCell, "{$LINKTITLE$}",
				o.getLinkTitle());
		templateCell = StringUtil.replaceAll(templateCell, "{$CATEGORY$}",
				sbCategory.toString());
		return templateCell;
	}

	/**
	 * parse 链接栏目单元
	 * 
	 * @param o
	 *            链接
	 * @return
	 */
	public static String parseLinkCategory(AdsPageLink o) {
		String templateCell = "<li><a href=\"{$LINKURL$}\">{$LINKTITLE$}</a></li>";
		templateCell = StringUtil.replaceAll(templateCell, "{$LINKURL$}",
				o.getLinkUrl());
		templateCell = StringUtil.replaceAll(templateCell, "{$LINKTITLE$}",
				o.getLinkTitle());
		return templateCell;
	}

	/**
	 * 取得页导航链接文件名
	 * 
	 * 1 栏目首页：index.html
	 * 
	 * 2 其它页面：categoryId+'-'+zoneId+'-'+tipId+'-'+pageNumber+'.html'
	 * 
	 * @param pageCount
	 *            页数
	 * @param zoneId
	 *            城市区主键
	 * @param categoryId
	 *            栏目主键
	 * @param tagId
	 *            栏目标签主键
	 * @return
	 */
	public static String[] getCategoryPageFileName(int pageCount, Long zoneId,
			Long categoryId, long tagId, String[] pageFile) {
		for (int i = 0; i < pageCount; i++) {
			if (i == 0 & zoneId == 0 & tagId == 0)
				pageFile[i] = "index.html";
			else
				pageFile[i] = categoryId.toString() + '-' + zoneId + '-'
						+ tagId + '-' + i + ".html";
		}
		return pageFile;
	}

	/**
	 * 取得页面内需要替换的广告商家内容
	 * 
	 * @param adsList
	 * @param pageCount
	 * @param rowStart
	 * @return
	 */
	public static String getPageContent(List<Ads> adsList, int pageCount,
			int rowStart) {
		StringBuffer adsContent = new StringBuffer();
		int adsCount = adsList.size();
		for (int j = 0; j < Global.PAGE_SIZE; j++) {
			// 防止数组越界，越界后进行空循环
			if (adsCount > j + rowStart * Global.PAGE_SIZE)
				if (StringUtil.isNotEmpty(adsList.get(
						j + rowStart * Global.PAGE_SIZE)
						.getAdsContentHtml()))
					adsContent.append(adsList.get(j + rowStart)
							.getAdsContentHtml());
		}
		return adsContent.toString();
	}

	/**
	 * Parse当前栏目下的所有广告商家，生成Html文件
	 * 
	 * @param o
	 *            当前栏目
	 * @param zoneId
	 *            当前城区主键
	 * @param tagId
	 *            当前标签主键
	 * @param template
	 *            模板
	 * @param tagCell
	 *            标签区
	 * @param zoneCell
	 *            城市区域
	 * @param pagerCell
	 *            翻页组件
	 * @param contentCell
	 *            广告商家内容区
	 * @param fileName
	 *            文件名称
	 * @throws Exception
	 */
	public static void parseCategory(BizCategory o, Long zoneId, Long tagId,
			String template, String tagCell, String zoneCell, String pagerCell,
			String contentCell, String fileName) throws Exception {
		template = StringUtil.replaceAll(template, "{$ZONEID$}",
				zoneId.toString());
		template = StringUtil.replaceAll(template, "{$TAGID$}",
				tagId.toString());
		template = StringUtil.replaceAll(template, "{$TAG$}", tagCell);
		template = StringUtil.replaceAll(template, "{$ZONE$}", zoneCell);
		template = StringUtil.replaceAll(template, "{$PAGER$}", pagerCell);
		template = StringUtil.replaceAll(template, "{$CONTENT$}", contentCell);
		HtmlParser.saveHtml(template, o.getCategoryParsePath() + "/tip",
				fileName);

	}

	public static void main(String[] args) {
		String pageFileName[] = new String[5];
		pageFileName = getCategoryPageFileName(5, Long.valueOf("0"),
				Long.valueOf("1"), Long.valueOf("0"), pageFileName);
		for (int i = 0; i < pageFileName.length; i++)
			System.out.println(pageFileName[i]);
		// {$PAGE$}
		System.out.println(pager(5, pageFileName, 3, "_self"));
		pageFileName = getCategoryPageFileName(5, Long.valueOf("1"),
				Long.valueOf("1"), Long.valueOf("0"), pageFileName);
		for (int i = 0; i < pageFileName.length; i++)
			System.out.println(pageFileName[i]);
		// {$PAGE$}
		System.out.println(pager(5, pageFileName, 3, "_self"));
		pageFileName = getCategoryPageFileName(5, Long.valueOf("0"),
				Long.valueOf("1"), Long.valueOf("1"), pageFileName);
		for (int i = 0; i < pageFileName.length; i++)
			System.out.println(pageFileName[i]);
		// {$PAGE$}
		System.out.println(pager(5, pageFileName, 3, "_self"));
	}

}
