package com.weaforce.cms.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weaforce.cms.entity.Meta;
import com.weaforce.core.util.StringUtil;
import com.weaforce.system.util.FileUtils;

public abstract class AbstractParser {
	private static final Logger logger = LoggerFactory
			.getLogger(AbstractParser.class);

	/**
	 * 以HTML文件格式，保存模板内容到指定目录中
	 * 
	 * @param template
	 *            模板
	 * @param htmlFilePath
	 *            指定路径
	 * @param fileName
	 *            文件名称
	 * @return
	 * @throws Exception
	 */

	public static String saveHtml(String template, String htmlFilePath,
			String fileName) throws Exception {
		try {
			makeDir(htmlFilePath);// 看看有没有指定的目录，如果没有，则建一个！
			String targetFile = htmlFilePath + "/" + fileName;
			OutputStreamWriter bufferedWriter = new OutputStreamWriter(
					new FileOutputStream(targetFile),
					FileUtils.getArticleHtmlCharset());
			bufferedWriter.write(template);
			bufferedWriter.close();
		} catch (IOException e) {
			logger.info("Writing file error: {}.", e.getMessage());
			throw new Exception(e.getMessage());
		}
		logger.info("article.html.charset,{}", FileUtils.getArticleHtmlCharset());
		logger.info("Saving html file completed.path: {}, fileName: {}",
				htmlFilePath, fileName);
		return htmlFilePath + fileName;
	}

	/**
	 * 删除HTML文件
	 * 
	 * @param fileName
	 */
	public static void deleteHtml(String fileName) {
		File file = new File(fileName);
		if (file.exists())
			file.delete();
	}

	/**
	 * 创建目录
	 * 
	 * @param htmlFilePath
	 *            目录:/data/www/weaforce
	 */
	private static void makeDir(String htmlFilePath) {
		try {
			File file = new File(htmlFilePath); // 建立文件路径
			if (!file.exists()) { // 如果没有指定目录
				file.mkdirs();// 建一个
			}
		} catch (Exception e) {
			logger.info("mkdirs error! {}", e.getMessage());
		}
	}

	/**
	 * 替换模板中用户自定义的meta data如电话，地址，传真等
	 * 
	 * @param template
	 * @param metaList
	 * @return
	 */

	public static String replaceMeta(String template, List<Meta> metaList) {
		for (Meta m : metaList)
			template = StringUtil.replaceAll(template, m.getMetaKey(),
					m.getMetaValue());
		return template;
	}

	/**
	 * 取得页数
	 * 
	 * @param listSize
	 *            记录总数：用于确定页数
	 * @param pageRow
	 *            每页记录数
	 * @return
	 */
	public static int getPageCount(int listSize, int pageRow) {
		int pageCount = 0;
		if (listSize > 0 & listSize < pageRow)
			pageCount = 1;
		else if (listSize % pageRow > 0)
			pageCount = listSize % pageRow + 1;
		else if (listSize % pageRow == 0)
			pageCount = listSize % pageRow;
		else
			return 0;
		return pageCount;
	}

	/**
	 * 根据页数和当前页，生成翻页导航条，替换{$PAGES$}元素
	 * 
	 * @param pageCount
	 *            总页数
	 * @param pageRow
	 *            每页记录数
	 * @param pageFileName
	 *            页链接文件
	 * @param currentPage
	 *            当前页
	 * @param target
	 *            超链接元素打开方式
	 * @return
	 */
	public static String pager(int pageCount, String[] pageFileName,
			int currentPage, String target) {
		StringBuffer pager = new StringBuffer("<ul class=\"pages\">");
		for (int i = 0; i < pageCount; i++) {
			int pageNumber = i + 1;
			// 首页＆上一页
			if (i == 0) {
				if (pageCount > 1)
					pager.append("<li class=\"pgEmpty\"><a href=\""
							+ pageFileName[0] + "\" target=\"" + target + "\">"
							+ "首页" + "</a></li>");
				else if (pageCount == 1)
					pager.append("<li class=\"pgEmpty\"><a>" + "首页"
							+ "</a></li>");
				if (pageCount > 1 & currentPage > 1)
					pager.append("<li class=\"pgEmpty\"><a href=\""
							+ pageFileName[currentPage - 2] + "\" target=\""
							+ target + "\">" + "上一页" + "</a></li>");
				else
					pager.append("<li class=\"pgEmpty\"><a>" + "上一页"
							+ "</a></li>");
			}
			// 当前页＆其它页
			if (pageNumber == currentPage) {
				pager.append("<li><a class=\"now\">" + pageNumber + "</a></li>");
			} else
				pager.append("<li class=\"pgEmpty\"><a href=\""
						+ pageFileName[i] + "\" target=\"" + target + "\">"
						+ pageNumber + "</a></li>");
			// 上一页＆尾页
			if (i == pageCount - 1) {
				if (pageCount > 1 & currentPage < pageCount)
					pager.append("<li class=\"pgEmpty\"><a href=\""
							+ pageFileName[currentPage] + "\" target=\""
							+ target + "\">" + "下一页" + "</a></li>");
				else
					pager.append("<li class=\"pgEmpty\"><a>" + "下一页"
							+ "</a></li>");
				if (pageCount > 1)
					pager.append("<li class=\"pgEmpty\"><a href=\""
							+ pageFileName[i] + "\" target=\"" + target + "\">"
							+ "尾页" + "</a></li>");
				else if (pageCount == 1)
					pager.append("<li class=\"pgEmpty\"><a>" + "尾页"
							+ "</a></li>");
			}
		}
		pager.append("</ul>");
		return pager.toString();
	}

	public static void main(String[] args) {
		// 五页测试
		System.out.println("<p>五页测试</p>");
		String[] pageFile1 = { "1.html", "2.html", "3.html", "4.html", "5.html" };
		System.out.println(pager(5, pageFile1, 3, "_self"));
		// 一页测试
		System.out.println("<p>一页测试</p>");
		String[] pageFile2 = { "1.html" };
		System.out.println(pager(1, pageFile2, 1, "_self"));
		// 二页测试
		System.out.println("<p>二页测试</p>");
		String[] pageFile3 = { "1.html", "2.html" };
		System.out.println(pager(2, pageFile3, 1, "_self"));
		System.out.println(pager(2, pageFile3, 2, "_self"));
		// 三页测试
		System.out.println("<p>三页测试</p>");
		String[] pageFile4 = { "1.html", "2.html", "3.html" };
		System.out.println(pager(3, pageFile4, 1, "_self"));
		System.out.println(pager(3, pageFile4, 2, "_self"));
		System.out.println(pager(3, pageFile4, 3, "_self"));
		// System.out.println(pager(3,pageFile4,3,"_self"));
	}

}
