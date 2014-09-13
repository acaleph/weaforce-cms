package com.weaforce.cms.util;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.weaforce.cms.entity.Article;
import com.weaforce.core.util.DateUtil;
import com.weaforce.core.util.StringUtil;
import com.weaforce.cms.entity.AlbumPhoto;
import com.weaforce.cms.entity.Category;
import com.weaforce.cms.entity.Channel;
import com.weaforce.cms.entity.Link;
import com.weaforce.cms.entity.Meta;
import com.weaforce.cms.entity.PageLink;
import com.weaforce.cms.entity.Template;

/**
 * <h3>文章/栏目管理工具类</h3>
 * <ul>
 * <li>文章parse为HTML文档</li>
 * <li>频道下的栏目文章Parse为HTML文章列表</li>
 * </ul>
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public class HtmlParser extends AbstractParser {
	private static final Logger logger = LoggerFactory
			.getLogger(HtmlParser.class);
	// 没有内容，替换为空串
	private static final String STRING_EMPTY = "";
	private static final String HTML5 = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><meta name=\"keywords\" content=\"${KEYWORDS}\"><title>${TITLE}</title></head><body>${BODY}</body></html>";

	/**
	 * <h3>把文章parse为HTML文件到所在栏目指定目录下</h3>
	 * <ul>
	 * <li>首页格式：<a href="{$MAINPAGE$}" >首页</a></li>
	 * <li>频道格式：<a href="{$channelUrl$}">{$channelName$}</a></li>
	 * <li>栏目格式：<a href="{$categoryUrl$}">{$categoryName$}</a></li>
	 * <li>页面导航格式{$location$}: 首页 >> 资讯(频道) >> 科技(栏目) >> 正文 {$titleIntact$} 文章标题
	 * <li>文件组织格式:每个频道下有index.html,展示频道内容或者跳转到指定的栏目文章</li>
	 * </li>
	 * </ul>
	 * 
	 * @param oa
	 *            文章对象
	 * @param website
	 *            主页:weaforce.com
	 * @param articleHtmlPath
	 *            html文件保存根目录
	 * @param articleUrl
	 *            文章URL:http://www.weaforce.com
	 * @param metaList
	 *            meta参数
	 * @param relationList
	 *            相关文章
	 * @param photoList
	 *            像片集
	 * @throws Exception
	 *             异常
	 */
	public static String parseArticle(Article oa, String template,
			String website, String articleHtmlPath, String articleUrl,
			List<Meta> metaList, List<Article> relationList,
			List<AlbumPhoto> photoList) throws Exception {
		// 替换模板中用户自定义meta信息
		template = replaceMeta(template, metaList);
		// 替换关联文章
		if (relationList.size() > 0)
			template = StringUtil.replaceAll(template, "${RELATION}",
					parseRelationCell(relationList));
		else
			template = StringUtil.replaceAll(template, "${RELATION}", "");
		// 替换照片集
		if (photoList.size() > 0)
			template = StringUtil.replaceAll(template, "${PHOTO}",
					parsePhotoCell(photoList));
		else
			template = StringUtil.replaceAll(template, "${PHOTO}", "");
		// 替换模板中导航信息
		template = parseBasicChannelCategory(oa.getArticleCategory()
				.getCategoryChannel(), oa.getArticleCategory(), template,
				website, null);
		// 文章属性及内容
		template = replaceArticle(oa, template);
		// 初始HTML文件保存参数
		String htmlFilePath = "";
		String fileName = "";
		// 根据文章的创建时间得到年/月目录
		String date = DateUtil.defaultFormat(new Date(oa.getCreateTime()));
		htmlFilePath = articleHtmlPath + "/" + date.subSequence(0, 4) + "/"
				+ date.subSequence(5, 7);
		logger.info("htmlFilePath value: {}", htmlFilePath);
		// 以文章的创建日期为HTML文件名
		fileName = oa.getCreateTime() + ".html";
		articleUrl = articleUrl + "/" + date.subSequence(0, 4) + "/"
				+ date.subSequence(5, 7);
		articleUrl = articleUrl + "/" + fileName;
		oa.setArticleLocation(htmlFilePath);
		oa.setArticleUrl(articleUrl);
		// 将内容写入到html文件中
		saveHtml(template, htmlFilePath, fileName);
		return template;
	}

	/**
	 * parse 友情链接
	 * 
	 * @param linkList
	 *            友情链接 list
	 * @return
	 */
	public static String parseLinkCell(List<Link> linkList) {
		StringBuffer sb = new StringBuffer();
		for (Link o : linkList) {
			String templateCell = "<li><a href=\"${LINKURL}\" target=\"_blank\">${LINKNAME}</a></li>";
			templateCell = StringUtil.replaceAll(templateCell, "${LINKURL}",
					o.getLinkUrl());
			templateCell = StringUtil.replaceAll(templateCell, "${LINKNAME$}",
					o.getLinkName());
			sb.append(templateCell);
		}
		logger.info("parseLinkCell(Link<List>) end.");
		return sb.toString();
	}

	/**
	 * 替换模板中子模板关键字,子模板内容需要手工生成
	 * 
	 * @param template
	 *            模板内容
	 * @param subList
	 *            子模板 list
	 * @return
	 */
	public static String replaceSubTemplate(String template,
			List<Template> subList) {
		for (Template o : subList)
			template = StringUtil.replaceAll(template, o.getTemplateKey(),
					o.getTemplateContent());
		return template;
	}

	/**
	 * 生成页面导航,导航结构有要求
	 * 
	 * @param channel
	 *            频道
	 * @param category
	 *            栏目
	 * @param template
	 *            模板内容
	 * @param website
	 *            主页,例如:weaforce.com
	 * @return
	 */
	public static String parseBasicChannelCategory(Channel channel,
			Category category, String template, String website, String cityName) {
		// 城市
		if (StringUtil.isNotEmpty(cityName))
			template = StringUtil.replaceAll(template, "${CITYNAME}", cityName);
		// 主页
		if (StringUtil.isNotEmpty(website))
			template = StringUtil.replaceAll(template, "${WEBSITE}", website);
		// 频道
		template = StringUtil.replaceAll(template, "${CHANNELURL}",
				channel.getChannelUrl());
		template = StringUtil.replaceAll(template, "${CHANNELNAME}",
				channel.getChannelName());
		// 栏目
		template = StringUtil.replaceAll(template, "${CATEGORYURL}",
				category.getCategoryUrl());
		template = StringUtil.replaceAll(template, "${CATEGORYNAME}",
				category.getCategoryName());
		logger.info("replaceNavigator(Channel,Category,template,mainpage) end.");
		return template;
	}

	/**
	 * 替换文章属性及内容
	 * 
	 * @param oa
	 *            文章
	 * @param template
	 *            模板
	 * @return
	 */
	public static String replaceArticle(Article oa, String template) {
		if (oa.getArticleTitle() != null)
			template = StringUtil.replaceAll(template, "${TITLE}",
					oa.getArticleTitle());
		if (oa.getArticleTitleSub() != null)
			template = StringUtil.replaceAll(template, "${TITLESUB}",
					oa.getArticleTitleSub());
		else
			template = StringUtil.replaceAll(template, "${TITLESUB}",
					STRING_EMPTY);
		if (oa.getArticleFrom() != null)
			template = StringUtil.replaceAll(template, "${COPYFROM}", oa
					.getArticleFrom().getFromName());
		else
			template = StringUtil.replaceAll(template, "${COPYFROM}",
					STRING_EMPTY);
		if (oa.getArticleAuthor() != null)
			template = StringUtil.replaceAll(template, "${AUTHOR}", oa
					.getArticleAuthor().getAuthorName());
		else
			template = StringUtil.replaceAll(template, "${AUTHOR}",
					STRING_EMPTY);
		if (oa.getArticleFrom() != null)
			template = StringUtil.replaceAll(template, "${COPYFROM}", oa
					.getArticleFrom().getFromName());
		else
			template = StringUtil.replaceAll(template, "${COPYFROM}",
					STRING_EMPTY);
		if (oa.getArticleContent() != null)
			template = StringUtil.replaceAll(template, "${CONTENT}", oa
					.getArticleContent().getContentContent());
		else
			template = StringUtil.replaceAll(template, "${CONTENT}",
					STRING_EMPTY);
		// 发布日期:已经在PrimaryEntity中处理
		template = StringUtil.replaceAll(template, "${UPDATETIME}",
				DateUtil.completeFormat(new Date(oa.getLastUpdateTime())));
		logger.info("replaceArticle(Article,template) end.");
		return template;
	}

	/**
	 * <p>
	 * 生成分页导航条 格式如下： [1]<a href='test1.html'>[2]</a><a
	 * href='test2.html'>[3]</a><a href='test1.html'>[下一页]</a> <a
	 * href='test.html'>[1]</a>[2]<a href='test2.html'>[3]</a><a
	 * href='test2.html'>[下一页]</a> <a href='test.html'>[1]</a><a
	 * href='test1.html'>[2]</a>[3]<a href='test.html'>[下一页]</a>
	 * </p>
	 * 
	 * @param size
	 *            页码
	 * @param name
	 *            文件名
	 * @return
	 */
	public static String[] getPage(int size, String name) {
		String[] re;
		if (size == 0) {
			re = new String[size + 1];
			re[0] = "  ";
		} else {
			re = new String[size];
		}
		if (size == 1) {
			re[0] = "[1]";
		} else {
			for (int i = 0; i < size; i++) {
				String k = getPageFirst(i, name) + "[" + (i + 1) + "]"
						+ getPageEnd(size, i + 1, name);
				if (i == 0) {
					re[i] = "[1]" + getPageFirst(i, name)
							+ getPageEnd(size, i + 1, name) + "<a href='"
							+ name + (i + 1) + ".html'>[下一页]</a>";
				} else if (i != (size - 1) && i != 0) {
					re[i] = k + "<a href='" + name + (i + 1)
							+ ".html'>[下一页]</a>";
				} else {
					re[i] = k + "<a href='" + name + ".html'>[下一页]</a>";
				}
			}
		}
		return re;
	}

	/* 生成分布导航的头 */
	public static String getPageFirst(int size, String name) {
		StringBuffer re = new StringBuffer();
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				re.append("<a href='" + name + ".html'>[" + (i + 1) + "]</a>");
			} else {
				re.append("<a href='" + name + (i) + ".html'>[" + (i + 1)
						+ "]</a>");
			}
		}
		return re.toString();
	}

	/* 生成分布导航的尾 */
	public static String getPageEnd(int size, int no, String name) {
		String re = "";
		for (int i = no; i < size; i++) {
			if (i == 0) {
				re += "<a href='" + name + ".html'>[" + (i + 1) + "]</a>";
			} else {
				re += "<a href='" + name + (i) + ".html'>[" + (i + 1) + "]</a>";
			}
		}
		return re;
	}

	/**
	 * 栏目基本信息
	 * 
	 * @param o
	 *            栏目
	 * @param cityName
	 *            城市
	 * @param linkList
	 *            友情链接
	 * @param template
	 *            模板
	 * @return
	 */
	public static String parseCategory(Category o, String cityName,
			List<Link> linkList, String template) {
		// 城市
		template = StringUtil.replaceAll(template, "${CITYNAME}", cityName);
		// 频道名称
		template = StringUtil.replaceAll(template, "${CHANNELNAME}", o
				.getCategoryChannel().getChannelName());
		// 频道主键
		template = StringUtil.replaceAll(template, "${CATEGORYID}", o
				.getCategoryId().toString());
		// 栏目名称
		template = StringUtil.replaceAll(template, "${CATEGORYNAME}",
				o.getCategoryName());
		// 栏目URL
		template = StringUtil.replaceAll(template, "${CATEGORYURL}",
				o.getCategoryUrl());
		// 友情链接
		template = StringUtil.replaceAll(template, "${LINK}",
				HtmlParser.parseLinkCell(linkList));
		return template;
	}

	/**
	 * 取得栏目下文章文件名称数组
	 * 
	 * @param pageFile
	 *            文件名称数组:初始化方法:new String[pageCount]
	 * @param categoryParseName
	 *            栏目第一个文件名称
	 * @param articleListSize
	 *            当前栏目下的文章总数
	 * @param categoryId
	 *            栏目主键
	 * @param categoryMaxPerPage
	 *            每页最大文章数
	 * @return
	 */
	public static String[] getCategoryPageFileName(String[] pageFile,
			String categoryParseName, int articleListSize, Long categoryId,
			int categoryMaxPerPage) {
		int pageCount = getPageCount(articleListSize, categoryMaxPerPage);
		for (int i = 0; i < pageCount; i++) {
			if (i == 0)
				pageFile[i] = categoryParseName + ".html";
			else
				pageFile[i] = categoryId + "-" + i + ".html";
			logger.info("Category NO. {}, file name:{}", i, pageFile[i]);
		}

		return pageFile;
	}

	/**
	 * 生成文本文章列表单元
	 * 
	 * @param title
	 *            标题
	 * @param url
	 *            链接
	 * @param updateTime
	 *            更新日期
	 * @param intro
	 *            简介
	 * @return
	 */
	public static String parseCategoryCell(String title, String url,
			String intro) {
		String cellTemplate = "<div class=\"textbox\"><div class=\"textbox-title\"><h4><a href=\"{$URL$}\">{$TITLE$}</a></h4><div class=\"textbox-label\">{$UPDATETIME$}</div></div><div class=\"textbox-content\">{$INTRO$}<br/><div style=\"margin-top: 9px;\"><a href=\"{$URL$}\">阅读全文</a></div></div></div>";
		cellTemplate = StringUtil.replaceAll(cellTemplate, "${TITLE}", title);
		cellTemplate = StringUtil.replaceAll(cellTemplate, "${URL}", url);
		cellTemplate = StringUtil.replaceAll(cellTemplate, "${UPDATETIME}",
				DateUtil.completeFormat(new Date(System.currentTimeMillis())));

		cellTemplate = StringUtil.replaceAll(cellTemplate, "${INTRO}", intro);
		return cellTemplate;
	}

	/**
	 * 生成图片文章列表单元
	 * 
	 * @param title
	 *            标题
	 * @param url
	 *            链接
	 * @param img
	 *            图片
	 * @param updateTime
	 *            更新日期
	 * @param intro
	 *            简介
	 * @return
	 */
	public static String parseCategoryCell(String title, String url,
			String intro, String img) {
		String cellTemplate = "<div class=\"list_r_list\"><span class=\"list_r_list_book\"><a name=\"pub_link_img\" target=\"_blank\" href=\"{$URL$}\"><img src=\"{$IMG$}\" ></a></span><h2><a name=\"pub_link_name\" target=\"_blank\" href=\"{$URL$}\" >{$TITLE$}</a></h2><h3>{$UPDATETIME$}<br>{$INTRO$}</h3><h3><a href=\"{$URL$}\">阅读全文</a></h3></div><div class=\"clear\"></div>";
		cellTemplate = StringUtil.replaceAll(cellTemplate, "{$TITLE$}", title);
		cellTemplate = StringUtil.replaceAll(cellTemplate, "{$URL$}", url);
		cellTemplate = StringUtil.replaceAll(cellTemplate, "{$IMG$}", img);
		cellTemplate = StringUtil.replaceAll(cellTemplate, "{$UPDATETIME$}",
				DateUtil.completeFormat(new Date(System.currentTimeMillis())));
		cellTemplate = StringUtil.replaceAll(cellTemplate, "{$INTRO$}", intro);
		return cellTemplate;
	}

	/**
	 * parse栏目及文章（图片文章列表&文本文章列表）
	 * 
	 * @param o
	 *            栏目
	 * @param template
	 *            模板
	 * @param articleList
	 *            当前栏目下的所有parse了的文章
	 * @param pageFile
	 *            栏目文件
	 * @param pageCount
	 *            页数
	 * @param htmlFilePath
	 *            保存路径
	 * @return
	 * @throws Exception
	 */
	public static void parseCategoryArticle(Category o, String template,
			List<Article> articleList, String[] pageFile, int pageCount,
			String htmlFilePath) throws Exception {
		String templateOriginal = template;
		for (int i = 0; i < pageCount; i++) {
			template = templateOriginal;
			StringBuffer content = new StringBuffer();
			for (Article a : articleList) {
				StringBuffer cell = new StringBuffer();
				String artielFileName = a.getArticleUrl();
				// "3"：文章列表
				if ("3".equals(o.getCategoryParseType())) {
					cell = new StringBuffer(HtmlParser.parseCategoryCell(
							a.getArticleTitle(), artielFileName,
							a.getArticleIntro()));
					// "4": 图片文章列表
				} else if ("4".equals(o.getCategoryParseType())) {
					cell = new StringBuffer(HtmlParser.parseCategoryCell(
							a.getArticleTitle(), artielFileName,
							a.getArticleIntro(), a.getArticleImageUrl()));
				}
				content.append(cell.toString());
			}
			// 栏目文章内容区
			template = StringUtil.replaceAll(template, "{$CONTENT$}",
					content.toString());
			template = StringUtil
					.replaceAll(
							template,
							"{$PAGER$}",
							pager(pageCount, pageFile, i,
									o.getCategoryArticleTarget()));
			// 保存栏目页html
			saveHtml(template, htmlFilePath, pageFile[i]);
		}
	}

	/**
	 * parse关联文章单元
	 * 
	 * @param articleList
	 * @return
	 */
	public static String parseRelationCell(List<Article> relationList) {
		StringBuffer sb = new StringBuffer();
		String cellTemplate = "<li><a href=\"{$URL$}\" target=\"_blank\">{$TITLE$}</a></li>";
		for (Article o : relationList) {
			cellTemplate = StringUtil.replaceAll(cellTemplate, "{$URL$}",
					o.getArticleUrl());
			cellTemplate = StringUtil.replaceAll(cellTemplate, "{$TITLE$}",
					o.getArticleTitle());
			sb.append(cellTemplate);
			cellTemplate = "<li><a href=\"{$URL$}\" target=\"_blank\">{$TITLE$}</a></li>";
		}
		return sb.toString();
	}

	/**
	 * parse关联照片单元:应用fancybox模板,具有特殊性
	 * 
	 * @param photoList
	 *            照片 list
	 * @return
	 */
	public static String parsePhotoCell(List<AlbumPhoto> photoList) {
		StringBuffer sb = new StringBuffer();
		String cellTemplate = "<a rel=\"photo_gallery\" href=\"{$URL$}\" title=\"{$NAME$}\"><img alt=\"\" src=\"{$URLRESIZE$}\" /></a>";
		for (AlbumPhoto o : photoList) {
			cellTemplate = StringUtil.replaceAll(cellTemplate, "{$URL$}",
					o.getPhotoUrl());
			cellTemplate = StringUtil.replaceAll(cellTemplate, "{$NAME$}",
					o.getPhotoName());
			cellTemplate = StringUtil.replaceAll(cellTemplate, "{$URLRESIZE$}",
					o.getPhotoUrlResize());
		}
		return sb.toString();
	}

	/**
	 * 根据“频道”或者“栏目”对应的自定义页面，替换模板中关于文章的标签信息
	 * 
	 * @param template
	 *            模板
	 * @param a
	 *            文章
	 * @param l
	 *            页面元素
	 * @return
	 */
	public static String replaceArticleFromLink(String template,
			List<PageLink> linkList) {
		for (PageLink l : linkList) {
			Article a = l.getLinkArticle();
			if (l.getLinkLabelCode() != null && a != null) {
				// 栏目名称
				template = StringUtil.replaceAll(template,
						"{$T" + l.getLinkLabelCode() + "G$}", a
								.getArticleCategory().getCategoryName());
				// 栏目链接
				template = StringUtil.replaceAll(template,
						"{$T" + l.getLinkLabelCode() + "Y$}", a
								.getArticleCategory().getCategoryUrl());
				// 主键ID
				template = StringUtil.replaceAll(template,
						"{$P" + l.getLinkLabelCode() + "P$}", a.getArticleId()
								.toString());
				// 标题
				template = StringUtil.replaceAll(template,
						"{$T" + l.getLinkLabelCode() + "T$}",
						a.getArticleTitle());
				// 文章链接
				template = StringUtil
						.replaceAll(template, "{$T" + l.getLinkLabelCode()
								+ "U$}", a.getArticleUrl());
				// 原始图片
				template = StringUtil.replaceAll(template,
						"{$T" + l.getLinkLabelCode() + "P$}",
						a.getArticleImageUrl());
				// 变更尺寸后的图片
				template = StringUtil.replaceAll(template,
						"{$T" + l.getLinkLabelCode() + "R$}",
						a.getArticleImageUrlResize());
				// 简介
				template = StringUtil.replaceAll(template,
						"{$T" + l.getLinkLabelCode() + "I$}",
						a.getArticleIntro());
				// 内容
				template = StringUtil.replaceAll(template,
						"{$T" + l.getLinkLabelCode() + "C$}", a
								.getArticleContent().getContentContent());
				// 来源
				template = StringUtil.replaceAll(template,
						"{$T" + l.getLinkLabelCode() + "F$}", a
								.getArticleFrom().getFromName());
				// 日期
				template = StringUtil.replaceAll(template,
						"{$T" + l.getLinkLabelCode() + "D$}",
						DateUtil.completeFormat(new Date(a.getCreateTime())));
			}
		}
		return template;
	}

	/**
	 * Get the html content
	 * 
	 * @param keywords
	 *            Keywords
	 * @param title
	 *            Title
	 * @param file
	 *            CSS or javascript file
	 * @param script
	 *            Script
	 * @param style
	 *            Style
	 * @param body
	 *            HTML body
	 * @return
	 */
	public String getHtml(String keywords, String title, String file,
			String script, String style, String body) {
		String html5 = HTML5;
		if (StringUtils.isEmpty(keywords))
			html5 = StringUtils.replace(html5, "${KEYWORDS}", "");
		else
			html5 = StringUtils.replace(html5, "${KEYWORDS}", keywords);
		if (StringUtils.isEmpty(title))
			html5 = StringUtils.replace(html5, "${TITLE}", "");
		else
			html5 = StringUtils.replace(html5, "${TITLE}", title);
		if (StringUtils.isEmpty(file))
			html5 = StringUtils.replace(html5, "${FILE}", "");
		else
			html5 = StringUtils.replace(html5, "${FILE}", file);
		if (StringUtils.isEmpty(script))
			html5 = StringUtils.replace(html5, "${SCRIPT}", "");
		else
			html5 = StringUtils.replace(html5, "${SCRIPT}", script);
		if (StringUtils.isEmpty(style))
			html5 = StringUtils.replace(html5, "${STYLE}", "");
		else
			html5 = StringUtils.replace(html5, "${STYLE}", style);
		if (StringUtils.isEmpty(body))
			html5 = StringUtils.replace(html5, "${BODY}", "");
		else
			html5 = StringUtils.replace(html5, "${BODY}", body);
		return html5;

	}
}
