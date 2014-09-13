package com.weaforce.cms.web;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weaforce.cms.entity.Meta;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.core.util.Global;
import com.weaforce.core.web.page.Page;
import com.weaforce.entity.admin.User;
import com.weaforce.system.component.spring.BasicController;

@Controller
@RequestMapping(value = "/cms/meta")
public class MetaController extends BasicController<Meta> {
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	@ModelAttribute()
	public void getMeta(
			@RequestParam(value = "metaId", required = false) Long metaId,
			Model model) {
		if (metaId != null) {
			model.addAttribute("meta", cmsService.getMeta(metaId));
		}
	}

	@RequestMapping(value = "")
	public String execute(
			@RequestParam(value = "page", defaultValue = "1") int currentPage,
			@RequestParam(value = "metaId", required = false) Long metaId,
			@RequestParam(value = "queryKey", required = false) String queryKey,
			@RequestParam(value = "queryValue", required = false) String queryValue,
			@RequestParam(value = "crud", defaultValue = "l") String crud,
			@ModelAttribute("meta") Meta meta, Model model, User user) {
		if (crud.equals("c")) {
			model.addAttribute("meta", new Meta());
			model.addAttribute("isActive", "1");
		} else if (crud.equals("r"))
			model.addAttribute("isActive", "1");
		else if (crud.equals("u")) {
			cmsService.saveMeta(meta);
			model.addAttribute("message", "保存成功");
			model.addAttribute("isActive", "1");
		} else if (crud.equals("d")) {
			cmsService.deleteTemplate(metaId);
			model.addAttribute("isActive", "0");
			model.addAttribute("message", "删除成功");
		} else
			model.addAttribute("isActive", "0");
		if (StringUtils.isEmpty(queryKey))
			page = new Page<Meta>(currentPage, Global.PAGE_SIZE);
		else
			page = new Page<Meta>(currentPage, Global.PAGE_SIZE, "queryName="
					+ queryKey);
		cmsService.getMetaPage(page, user.getAccount(), queryKey, queryValue);
		model.addAttribute("page", page);
		return "cms/meta";
	}

}
