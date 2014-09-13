package com.weaforce.cms.web;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.weaforce.cms.entity.Channel;
import com.weaforce.cms.service.ICmsService;
import com.weaforce.core.util.Global;
import com.weaforce.core.web.page.Page;
import com.weaforce.entity.admin.User;
import com.weaforce.system.component.spring.BasicController;

@Controller
@RequestMapping(value = "/cms/channel")
public class ChannelController extends BasicController<Channel> {
	@Autowired
	@Qualifier("cmsService")
	private ICmsService cmsService;

	@ModelAttribute()
	public void getChannel(
			@RequestParam(value = "channelId", required = false) Long channelId,
			Model model) {
		if (channelId != null) {
			model.addAttribute("channel", cmsService.getChannel(channelId));
		}
	}

	@RequestMapping(value = "")
	public String execute(
			@RequestParam(value = "page", defaultValue = "1") int currentPage,
			@RequestParam(value = "channelId", required = false) Long channelId,
			@RequestParam(value = "templateId", required = false) Long templateId,
			@RequestParam(value = "queryName", required = false) String queryName,
			@RequestParam(value = "crud", defaultValue = "l") String crud,
			@ModelAttribute("channel") Channel channel, Model model, User user) {
		if (crud.equals("c")) {
			model.addAttribute("channel", new Channel());
			model.addAttribute("isActive", "1");
		} else if (crud.equals("r"))
			model.addAttribute("isActive", "1");
		else if (crud.equals("u")) {
			cmsService.saveChannel(user, channel, templateId);
			model.addAttribute("message", "保存成功");
			model.addAttribute("isActive", "1");
		} else if (crud.equals("d")) {
			cmsService.deleteChannel(channelId);
			model.addAttribute("isActive", "0");
			model.addAttribute("message", "删除成功");
		} else
			model.addAttribute("isActive", "0");
		if (StringUtils.isEmpty(queryName))
			page = new Page<Channel>(currentPage, Global.PAGE_SIZE);
		else
			page = new Page<Channel>(currentPage, Global.PAGE_SIZE,
					"channelName=" + queryName);
		cmsService.getChannelPage(page, user.getAccount(), queryName);
		// Templates
		model.addAttribute("templates",
				cmsService.getTemplateList(user.getAccount(),"1", true, channel));
		model.addAttribute("page", page);
		return "cms/channel";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model, User user) {
		model.addAttribute("templates",
				cmsService.getTemplateList(user.getAccount()));
		model.addAttribute("channel", new Channel());
		return "cms/channel-input";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long channelId, Model model,
			User user) {
		Channel channel = cmsService.getChannel(channelId);
		model.addAttribute("templates", cmsService.getTemplateList(channel,
				user.getAccount(), "1", false));
		model.addAttribute("isActive", "1");
		model.addAttribute("channel", channel);
		return "cms/channel";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(
			@RequestParam(value = "templateId", required = false) Long templateId,
			@RequestParam(value = "queryNameInEdit", required = false) String queryInEditName,
			@ModelAttribute("channel") Channel channel,
			RedirectAttributes redirectAttributes) {
		// cmsService.saveChannel(channel, templateId);
		redirectAttributes.addFlashAttribute("message", "更新成功");
		return "redirect:/cms/channel";
	}

}
