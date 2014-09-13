package com.weaforce.system.web.admin;

import java.security.Principal;
import java.util.List;

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

import com.weaforce.core.util.Global;
import com.weaforce.core.web.page.Page;
import com.weaforce.entity.admin.User;
import com.weaforce.system.component.spring.BasicController;
import com.weaforce.system.service.ISystemService;

@Controller
@RequestMapping(value = "/admin/user")
public class UserController extends BasicController<User> {

	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	@ModelAttribute()
	public void getUser(
			@RequestParam(value = "userId", required = false) Long userId,
			Model model) {
		if (userId != null) {
			model.addAttribute("user", systemService.getUser(userId));
		}
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", defaultValue = "1") int currentPage,
			@RequestParam(value = "userId", required = false) Long userId,
			@RequestParam(value = "userName", required = false) String userName,
			Model model, User user) {
		if (userId != null) {
			systemService.deleteUser(userId);
			model.addAttribute("message", "删除成功");
		}
		if (StringUtils.isEmpty(userName))
			page = new Page<User>(currentPage, Global.PAGE_SIZE);
		else
			page = new Page<User>(currentPage, Global.PAGE_SIZE, "userName="
					+ userName);
		systemService.getUserPage(page, user.getAccount(), "0", "", userName,
				null, null);
		model.addAttribute("page", page);
		return "admin/user";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model, User user) {
		model.addAttribute("roles",
				systemService.getRoleList(user.getAccount(), "1"));
		model.addAttribute("user", new User());
		return "admin/user-input";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long userId, Model model) {
		User user = systemService.getUser(userId);
		model.addAttribute("user", user);
		model.addAttribute("roles",
				systemService.getRoleList(user, user.getAccount(), "1"));
		return "admin/user-input";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(
			@RequestParam(value = "userId", required = false) Long userId,
			@RequestParam(value = "defaultUserRoleId", required = false) Long defaultUserRoleId,
			@RequestParam(value = "userNameCn", required = false) String userNameCn,
			@RequestParam(value = "userNameEn", required = false) String userNameEn,
			@RequestParam(value = "userLogin", required = false) String userLogin,
			@RequestParam(value = "roleIds", required = false) List<Long> roleIds,
			Principal principal, RedirectAttributes redirectAttributes)
			throws Exception {
		User o = new User();
		if (userId != null)
			o = systemService.getUser(userId);
		// Bundle the properties
		o.setUserNameCn(userNameCn);
		o.setUserNameEn(userNameEn);
		o.setUserLogin(userLogin);
		// Current login user
		User loginUser = systemService.getUserByLogin(principal.getName());
		// Save the new user
		systemService.saveUser(loginUser, o, defaultUserRoleId, roleIds);
		redirectAttributes.addFlashAttribute("message", "更新成功");
		return "redirect:/admin/user";
	}

}
