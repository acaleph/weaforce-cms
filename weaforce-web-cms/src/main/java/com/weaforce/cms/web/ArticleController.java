package com.weaforce.cms.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weaforce.cms.entity.Article;
import com.weaforce.cms.entity.Channel;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.core.util.Global;
import com.weaforce.core.web.page.Page;
import com.weaforce.entity.admin.User;
import com.weaforce.system.component.spring.BasicController;

@Controller
@RequestMapping(value = "/cms/article")
public class ArticleController extends BasicController<Article> {
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	@ModelAttribute()
	public void getArticle(
			@RequestParam(value = "articleId", required = false) Long articleId,
			Model model) {
		if (articleId != null) {
			model.addAttribute("article", cmsService.getArticle(articleId));
		}
	}

	@RequestMapping(value = "")
	public String execute(
			@RequestParam(value = "page", defaultValue = "1") int currentPage,
			@RequestParam(value = "articleId", required = false) Long articleId,
			@RequestParam(value = "articleContent", required = false) String articleContent,
			@RequestParam(value = "categoryId", required = false) Long categoryId,
			@RequestParam(value = "fromId", required = false) Long fromId,
			@RequestParam(value = "queryChannelId", required = false) Long queryChannelId,
			@RequestParam(value = "queryCategoryId", required = false) Long queryCategoryId,
			@RequestParam(value = "queryTitle", required = false) String queryTitle,
			@RequestParam(value = "queryDateFrom", required = false) String queryDateFrom,
			@RequestParam(value = "queryDateTo", required = false) String queryDateTo,
			@RequestParam(value = "crud", defaultValue = "l") String crud,
			@ModelAttribute("article") Article article, Model model, User user) {
		if (crud.equals("c")) {
			model.addAttribute("article",
					queryCategoryId == null ? new Article() : new Article(
							cmsService.getCategory(queryCategoryId)));
			model.addAttribute("isActive", "1");
		} else if (crud.equals("r"))
			model.addAttribute("isActive", "1");
		else if (crud.equals("u")) {
			cmsService.saveArticle(article, articleContent, categoryId, fromId);
			model.addAttribute("message", "保存成功");
			model.addAttribute("isActive", "1");
		} else if (crud.equals("d")) {
			cmsService.deleteArticle(articleId);
			model.addAttribute("isActive", "0");
			model.addAttribute("message", "删除成功");
		} else
			model.addAttribute("isActive", "0");
		if (StringUtils.isEmpty(queryTitle))
			page = new Page<Article>(currentPage, Global.PAGE_SIZE);
		else
			page = new Page<Article>(currentPage, Global.PAGE_SIZE,
					"queryTitle=" + queryTitle);
		cmsService.getArticlePage(page, user.getAccount(), queryChannelId,
				queryCategoryId, queryTitle, queryDateFrom, queryDateTo);
		// Channels
		List<Channel> channelList = cmsService.getChannelList(
				user.getAccount(), "1");
		model.addAttribute("channels", channelList);
		// Categories
		if (queryChannelId == null)
			if (channelList.size() > 0)
				queryChannelId = channelList.get(0).getChannelId();
		model.addAttribute("categories",
				cmsService.getCategoryList(queryChannelId));
		model.addAttribute("page", page);
		return "cms/article";
	}
}
