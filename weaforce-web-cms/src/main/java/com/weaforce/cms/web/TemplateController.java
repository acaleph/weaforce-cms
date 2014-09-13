package com.weaforce.cms.web;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weaforce.cms.entity.Template;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.cms.util.Cms;
import com.weaforce.core.util.Global;
import com.weaforce.core.web.page.Page;
import com.weaforce.entity.admin.User;
import com.weaforce.system.component.spring.BasicController;

@Controller
@RequestMapping(value = "/cms/template")
public class TemplateController extends BasicController<Template> {
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	@ModelAttribute()
	public void getTemplate(
			@RequestParam(value = "templateId", required = false) Long templateId,
			Model model) {
		if (templateId != null) {
			model.addAttribute("template", cmsService.getTemplate(templateId));
		}
	}

	@RequestMapping(value = "")
	public String execute(
			@RequestParam(value = "page", defaultValue = "1") int currentPage,
			@RequestParam(value = "templateId", required = false) Long templateId,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "queryName", required = false) String queryName,
			@RequestParam(value = "crud", defaultValue = "l") String crud,
			@ModelAttribute("template") Template template, Model model,
			User user) {
		if (crud.equals("c")) {
			model.addAttribute("template", new Template());
			model.addAttribute("isActive", "1");
		} else if (crud.equals("r"))
			model.addAttribute("isActive", "1");
		else if (crud.equals("u")) {
			cmsService.saveTemplate(template, type);
			model.addAttribute("message", "保存成功");
			model.addAttribute("isActive", "1");
		} else if (crud.equals("d")) {
			cmsService.deleteTemplate(templateId);
			model.addAttribute("isActive", "0");
			model.addAttribute("message", "删除成功");
		} else
			model.addAttribute("isActive", "0");
		if (StringUtils.isEmpty(queryName))
			page = new Page<Template>(currentPage, Global.PAGE_SIZE);
		else
			page = new Page<Template>(currentPage, Global.PAGE_SIZE,
					"queryName=" + queryName);
		cmsService.getTemplatePage(page, user.getAccount(), queryName);
		model.addAttribute("page", page);
		// Template types
		model.addAttribute("types", Cms.templateType);
		return "cms/template";
	}

}
