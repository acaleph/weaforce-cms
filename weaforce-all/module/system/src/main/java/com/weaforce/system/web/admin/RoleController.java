package com.weaforce.system.web.admin;

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
import com.weaforce.entity.admin.Role;
import com.weaforce.entity.admin.User;
import com.weaforce.system.component.spring.BasicController;
import com.weaforce.system.service.ISystemService;

@Controller
@RequestMapping(value = "/admin/role")
public class RoleController extends BasicController<Role> {
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	/**
	 * Auto band attribute to the entity instance
	 * 
	 * @param roleId
	 *            Role primary key
	 * @param model
	 *            Model view
	 */
	@ModelAttribute()
	public void getRole(
			@RequestParam(value = "roleId", required = false) Long roleId,
			Model model) {
		if (roleId != null) {
			model.addAttribute("role", systemService.getRole(roleId));
		}
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", defaultValue = "1") int currentPage,
			@RequestParam(value = "roleName", required = false) String roleName,
			Model model, User user) {
		if (StringUtils.isEmpty(roleName))
			page = new Page<Role>(currentPage, Global.PAGE_SIZE);
		else
			page = new Page<Role>(currentPage, Global.PAGE_SIZE, "roleName="
					+ roleName);
		systemService.getRolePage(page, user.getAccount(), roleName, "1");
		model.addAttribute("page", page);
		return "admin/role";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("authorities", systemService.getAuthorityList());
		model.addAttribute("role", new Role());
		return "admin/role-input";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long roleId, Model model) {
		Role role = systemService.getRole(roleId);
		model.addAttribute("authorities",
				systemService.getAuthorityList(role.getRoleAuthority()));
		model.addAttribute("role", role);
		return "admin/role-input";
	}

	/**
	 * Save the role and it's authorities
	 * 
	 * @param authorityIds
	 *            Authority IDs
	 * @param role
	 *            Role instance
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(
			@RequestParam(value = "authorityIds", required = false) Long[] authorityIds,
			@ModelAttribute("role") Role role,
			RedirectAttributes redirectAttributes) {
		systemService.saveRole(role, authorityIds);
		redirectAttributes.addFlashAttribute("message", "更新任务成功");
		return "redirect:/admin/role";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long roleId,
			RedirectAttributes redirectAttributes) {
		systemService.deleteRole(roleId);
		return "redirect:/admin/role";
	}
}
