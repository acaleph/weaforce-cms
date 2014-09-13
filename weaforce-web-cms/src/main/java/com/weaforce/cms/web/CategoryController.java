package com.weaforce.cms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weaforce.cms.entity.Category;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.core.util.Global;
import com.weaforce.core.web.page.Page;
import com.weaforce.entity.admin.User;
import com.weaforce.system.component.spring.BasicController;

@Controller
@RequestMapping(value = "/cms/category")
public class CategoryController extends BasicController<Category> {
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	@ModelAttribute()
	public void getCategory(
			@RequestParam(value = "categoryId", required = false) Long categoryId,
			Model model) {
		if (categoryId != null) {
			model.addAttribute("category", cmsService.getCategory(categoryId));
		}
	}

	@RequestMapping(value = "")
	public String execute(
			@RequestParam(value = "page", defaultValue = "1") int currentPage,
			@RequestParam(value = "categoryId", required = false) Long categoryId,
			@RequestParam(value = "channelId", required = false) Long channelId,
			@RequestParam(value = "templateId", required = false) Long templateId,
			@RequestParam(value = "articleTemplateId", required = false) Long articleTemplateId,
			@RequestParam(value = "queryChannelId", required = false) Long queryChannelId,
			@RequestParam(value = "queryName", required = false) String queryName,
			@RequestParam(value = "crud", defaultValue = "l") String crud,
			@ModelAttribute("category") Category category, Model model,
			User user) {
		if (crud.equals("c")) {
			category = new Category();
			if (queryChannelId != null)
				category.setCategoryChannel(cmsService
						.getChannel(queryChannelId));
			model.addAttribute("category", category);
			model.addAttribute("isActive", "1");
		} else if (crud.equals("r"))
			model.addAttribute("isActive", "1");
		else if (crud.equals("u")) {
			cmsService.saveCategory(user, category, channelId, templateId,
					articleTemplateId);
			model.addAttribute("message", "保存成功");
			model.addAttribute("isActive", "1");
		} else if (crud.equals("d")) {
			cmsService.deleteCategory(categoryId);
			model.addAttribute("isActive", "0");
			model.addAttribute("message", "删除成功");
		} else if (crud.equals("p")){
			model.addAttribute("isActive", "0");
		}else
			model.addAttribute("isActive", "0");
		if (StringUtils.isEmpty(queryName))
			page = new Page<Category>(currentPage, Global.PAGE_SIZE);
		else
			page = new Page<Category>(currentPage, Global.PAGE_SIZE,
					"queryName=" + queryName + "&queryChannelId="
							+ queryChannelId);
		cmsService.getCategoryPage(page, user.getAccount(), queryChannelId,
				queryName);
		// Channels
		model.addAttribute("channels",
				cmsService.getChannelList(user.getAccount(), "1", category));
		// Templates
		model.addAttribute("templates", cmsService.getTemplateList(
				user.getAccount(), "1", true, category));
		// Page
		model.addAttribute("page", page);
		return "cms/category";
	}
}
