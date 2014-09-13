package com.weaforce.system.web.app;

import java.security.Principal;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weaforce.core.common.bean.Node;
import com.weaforce.core.util.Global;
import com.weaforce.core.web.page.Page;
import com.weaforce.entity.admin.User;
import com.weaforce.entity.app.Menu;
import com.weaforce.system.component.spring.BasicController;
import com.weaforce.system.component.spring.Security;
import com.weaforce.system.service.ISystemService;

@Controller
@RequestMapping(value = "/app/menu")
public class MenuController extends BasicController<Menu> {
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	@ModelAttribute()
	public void getMenu(
			@RequestParam(value = "menuId", required = false) Long menuId,
			Model model) {
		if (menuId != null) {
			model.addAttribute("menu", systemService.getMenu(menuId));
		}
	}

	/**
	 * List menus and show a menu instance
	 * 
	 * @param currentPage
	 *            Current page number
	 * @param menuId
	 *            Menu primary key
	 * @param name
	 *            Menu name
	 * @param userLogin
	 *            User login ID
	 * @param model
	 *            View model
	 * @param principal
	 *            Security
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String execute(
			@RequestParam(value = "page", defaultValue = "1") int currentPage,
			@RequestParam(value = "parentId", defaultValue = "1") Long parentId,
			@RequestParam(value = "menuId", required = false) Long menuId,
			@RequestParam(value = "menuName", required = false) String menuName,
			@RequestParam(value = "userLogin", required = false) String userLogin,
			Model model, Principal principal) {

		if (StringUtils.isEmpty(menuName))
			page = new Page<Menu>(currentPage, Global.PAGE_SIZE);
		else
			page = new Page<Menu>(currentPage, Global.PAGE_SIZE, "menuName="
					+ menuName);
		// List form
		systemService.getMenuPage(page, menuName);
		model.addAttribute("page", page);
		model.addAttribute("userLoginJSON",
				systemService.getUserLoginJSON(Security.getUser().getAccount()));
		User user = new User();
		// Default is the current login in user
		if (StringUtils.isEmpty(userLogin)) {
			model.addAttribute("userLogin", principal.getName());
			user = systemService.getUserByLogin(principal.getName());
		} else {
			model.addAttribute("userLogin", userLogin);
			user = systemService.getUserByLogin(userLogin);
		}
		// Menu tree JSON data
		model.addAttribute("menuTree",
				systemService.getMenuTree(parentId, user));
	
		// Ready for list form and edit form
		if (menuId == null) {
			model.addAttribute("isActive", 0);
			model.addAttribute("menu", new Menu());
		} else {
			model.addAttribute("isActive", 1);
			model.addAttribute("menu", systemService.getMenu(menuId));
		}
		// Return to the root
		return "app/menu";
	}

	/**
	 * Saving menu
	 * 
	 * @param menu
	 *            Menu instance
	 * @param parentId
	 *            Parent menu primary key
	 * @param model
	 *            View model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Node save(@ModelAttribute("menu") Menu menu,
			@ModelAttribute("parentId") Long parentId, Model model) {
		menu = systemService.saveMenu(menu, parentId);
		Node node = new Node();
		node.setId(menu.getMenuId());
		node.setMessage("更新成功");
		return node;
	}

	@ResponseBody
	@RequestMapping(value = "toRole", method = RequestMethod.GET)
	public Node setMenuToRole(
			@RequestParam(value = "menuId", required = true) Long menuId,
			@RequestParam(value = "userLogin", required = true) String userLogin) {
		systemService.setMenuToRole(menuId, userLogin);
		Node node = new Node();
		node.setMessage("更新成功");
		return node;
	}

}
