package com.weaforce.cms.util;

import java.util.List;

import com.weaforce.cms.entity.Registry;
import com.weaforce.cms.entity.forum.Forum;
import com.weaforce.cms.entity.forum.ForumCategory;
import com.weaforce.cms.entity.forum.ForumChannel;
import com.weaforce.cms.entity.forum.Post;
import com.weaforce.cms.entity.forum.PostContent;
import com.weaforce.cms.exception.ForumException;
import com.weaforce.core.util.StringUtil;

/**
 * 论坛工具类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public class ForumParser {
	// 没有内容，替换为空串
	private static final String STRING_EMPTY = "";

	/**
	 * parse 贴子,并将结果保存至PostHtml中
	 * 
	 * @param o
	 *            帖子
	 * @return
	 * @throws ForumException
	 */
	public static String parsePost(Post o) throws ForumException {
		StringBuffer sb = new StringBuffer();
		Registry r = o.getPostRegistry();
		sb.append(parsePostTitle(o));
		if (r != null)
			sb.append(parsePostInfo(o.getPostContent(), r, o.getPostTitle()));
		return sb.toString();
	}

	/**
	 * parse 贴子标题条
	 * 
	 * @param o
	 * @return
	 * @throws ForumException
	 */
	public static String parsePostTitle(Post o) throws ForumException {
		String templateCell = "<tr><td colspan=\"2\"><div class=\"postinfo\"><div class=\"date\"><a href=\"{$CATEGORYURL$}#{$NUMBER$}\"><img alt=\"{$POSTTITLE$}\" src=\"images/icon_minipost_new.gif\"/></a>{$DATE$}</div><div class=\"subject\"><b>文章主题:</b><a name=\"{$NUMBER$}\">{$POSTTITLE$}</a></div><div class=\"action\"><a class=\"icon_quote\" rel=\"nofollow\" href=\"javascript:;\" onClick=\"javascript:quotePost({$POSTID$});\"><img alt=\"引用\" src=\"images/transp.gif\"/></a><a class=\"icon_edit\" rel=\"nofollow\" href=\"javascript:;\" onClick=\"javascript:editPost({$POSTID$});\"><img alt=\"编辑\" src=\"images/transp.gif\"/></a><a id=\"delete\" href=\"javascript:;\" onClick=\"javascript:deletePost({$POSTID$});\"><img alt=\"删除\" src=\"images/icon_delete.gif\"/></a><a class=\"nav\" href=\"#top\"><img alt=\"[置顶]\" src=\"images/icon_up.gif\"/></a></div></div></td></tr>";
		templateCell = StringUtil.replaceAll(templateCell, "{$CATEGORYURL$}", o
				.getPostForum().getForumCategory().getCategoryUrl());
		templateCell = StringUtil.replaceAll(templateCell, "{$POSTID$}", o
				.getPostId().toString());
		templateCell = StringUtil.replaceAll(templateCell, "{$POSTTITLE$}", o
				.getPostTitle());
		templateCell = StringUtil.replaceAll(templateCell, "{$DATE$}", o
				.getDate());
		return templateCell;
	}

	/**
	 * parse 贴子信息
	 * 
	 * @param o
	 * @param r
	 *            注册用户
	 * @param postTitle
	 *            贴子标题
	 * @return
	 * @throws ForumException
	 */
	public static String parsePostInfo(PostContent o, Registry r,
			String postTitle) throws ForumException {
		String templateCell = "<tr><td class=\"row2\" valign=\"top\" align=\"left\" rowspan=\"2\"><span class=\"genmed\"><b>{$REGISTRYNICKNAME$}</b></span><br/>{$STAR$}<br/><span class=\"gensmall\"><br/><br/>注册时间: {$REGISTRYDATE$}<br/>文章: {$REGISTRYPOSTCOUNT$}<br/>来源IP: {$CONTENTIP$}<br/><span class=\"{$REGISTRYSTATUSCLASS$}\">{$REGISTRYSTATUS$}</span></span><br/></td><td id=\"post_text_{$NUMBER$}\" class=\"row2\" valign=\"top\"><span class=\"postbody\"><div id=\"{$NUMBER$}\" class=\"edit_area\" title=\"{$POSTTITLE$}\">{$CONTENT$}</div></span></td></tr>";
		templateCell = StringUtil.replaceAll(templateCell,
				"{$REGISTRYNICKNAME$}", r.getRegistryNickname());
		templateCell = StringUtil.replaceAll(templateCell, "{$STAR$}",
				parsePostStar(r.getStarCount()));
		templateCell = StringUtil.replaceAll(templateCell, "{$REGISTRYDATE$}",
				r.getDate());
		templateCell = StringUtil.replaceAll(templateCell,
				"{$REGISTRYPOSTCOUNT$}", r.getRegistryTopicCount().toString());
		templateCell = StringUtil.replaceAll(templateCell,
				"{$REGISTRYPOSTCOUNT$}", r.getRegistryPostCount().toString());
		templateCell = StringUtil.replaceAll(templateCell, "{$CONTENTIP$}", o
				.getContentIp());
		templateCell = StringUtil.replaceAll(templateCell, "{$POSTTITLE$}",
				postTitle);
		templateCell = StringUtil.replaceAll(templateCell, "{$CONTENT$}", o
				.getContentContent());
		return templateCell;
	}

	public static String parsePostStar(int i) {
		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < i; j++)
			sb.append("<img alt=\"星级\" name=\"star_" + j
					+ "\" src=\"images/star_on.gif\"/>");
		for (int j = i; j < 5; j++)
			sb.append("<img alt=\"星级\" name=\"star_" + j
					+ "\" src=\"images/star_off.gif\"/>");
		return sb.toString();
	}

	/**
	 * parse 贴子内容
	 * 
	 * @param o
	 * @return
	 * @throws ForumException
	 */
	public static String parsePostContent(PostContent o, String quote)
			throws ForumException {
		String templateCell = "<span class=\"postbody\"><div id=\"{$CONTENTID$}\" class=\"edit_area\" title=\"双击鼠标就可编辑\">{$QUOTE$}<br>{$POSTCONTENT$}</div></span>";
		templateCell = StringUtil.replaceAll(templateCell, "{$POSTCONTENT$}", o
				.getContentContent());
		if (StringUtil.isNotEmpty(quote))
			templateCell = StringUtil.replaceAll(templateCell, "{$QUOTE$}<br>",
					quote);
		else
			templateCell = StringUtil.replaceAll(templateCell, "{$QUOTE$}<br>",
					STRING_EMPTY);
		return templateCell;
	}

	/**
	 * 引用贴子
	 * 
	 * @param o
	 * @param registryNickName
	 * @param quoteParent
	 * @return
	 * @throws ForumException
	 */
	public static String parsePostContentQuote(PostContent o,
			String registryNickName, String quoteParent) throws ForumException {
		String templateCell = "<blockquote><div><cite>{$REGISTRYNICKNAME$}</cite>{$QUOTEPARENT$}<br>{$POSTCONTENT$}</div></blockquote>";
		templateCell = StringUtil.replaceAll(templateCell,
				"{$REGISTRYNICKNAME$}", registryNickName);
		templateCell = StringUtil.replaceAll(templateCell, "{$POSTCONTENT$}", o
				.getContentContent());
		if (StringUtil.isNotEmpty(quoteParent))
			templateCell = StringUtil.replaceAll(templateCell,
					"{$QUOTEPARENT$}<br>", quoteParent);
		else
			templateCell = StringUtil.replaceAll(templateCell,
					"{$QUOTEPARENT$}<br>", STRING_EMPTY);
		return templateCell;
	}

	/**
	 * parse 频道
	 * 
	 * @param o
	 *            频道
	 * @param template
	 *            模板
	 * @return
	 */
	public static String parseChannel(ForumChannel o, String template) {
		template = StringUtil.replaceAll(template, "{$CHANNELNAME$}", o
				.getChannelName());
		List<ForumCategory> categoryList = o.getChannelCategory();
		// *******************栏目及论坛*********************
		StringBuffer sb = new StringBuffer();
		for (ForumCategory category : categoryList) {
			StringBuffer sbForum = new StringBuffer();
			for (Forum forum : category.getCategoryForum())
				sbForum
						.append(parseForumCell(forum, category.getCategoryUrl()));
			sb.append(parseCategoryCell(category, sbForum));

		}
		// *******************栏目及论坛*********************
		template = StringUtil.replaceAll(template, "{$CATEGORY$}", sb
				.toString());
		return template;
	}

	/**
	 * parse 栏目单元
	 * 
	 * @param o
	 * @return
	 */
	public static String parseCategoryCell(ForumCategory o,
			StringBuffer forumForum) {
		String templateCell = "<h3>{$CATEGORYNAME$}</h3><ul>{$FORUM$}</ul>";
		templateCell = StringUtil.replaceAll(templateCell, "{$CATEGORYNAME$}",
				o.getCategoryName());
		// 论坛单元
		templateCell = StringUtil.replaceAll(templateCell, "{$FORUM$}",
				forumForum.toString());
		return templateCell;
	}

	/**
	 * parse 论坛单元
	 * 
	 * @return
	 */
	public static String parseForumCell(Forum o, String categoryUrl) {
		String templateCell = "<li><a href=\"{$FORUMURL$}\">{$FORUMNAME$}</a></li>";
		String url = o.getForumUrl();
		if (StringUtil.isNotEmpty(url))
			templateCell = StringUtil.replaceAll(templateCell, "{$FORUMURL$}",
					url);
		else
			templateCell = StringUtil.replaceAll(templateCell, "{$FORUMURL$}",
					"javascript:;");
		templateCell = StringUtil.replaceAll(templateCell, "{$FORUMNAME$}", o
				.getForumName());
		return templateCell;
	}

	/**
	 * parse 论坛
	 * <ul>
	 * <li>论坛导航</li>
	 * <li>论坛信息</li>
	 * </ul>
	 * 
	 * @param o
	 *            论坛
	 * @param category
	 *            栏目
	 * @param template
	 *            模板
	 * @return
	 */
	public static String parseForum(Forum o, ForumCategory category,
			String template) {
		template = StringUtil.replaceAll(template, "{$CATEGORYNAME$}", category
				.getCategoryName());
		template = StringUtil.replaceAll(template, "{$FORUMID$}", o
				.getForumId().toString());
		template = StringUtil.replaceAll(template, "{$FORUMNAME$}", o
				.getForumName());
		template = StringUtil.replaceAll(template, "{$FORUMURL$}", o
				.getForumUrl());
		return template;
	}
}
