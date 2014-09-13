package com.weaforce.system.web;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weaforce.entity.admin.Role;
import com.weaforce.entity.admin.User;
import com.weaforce.system.service.ISystemService;

@Controller
public class LoginController {
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Principal principal) {
		return "login";
	}

	/**
	 * After login in successfully, redirect to the first page
	 * 
	 * @return
	 */
	@RequestMapping(value = "success", method = RequestMethod.GET)
	public String success(Principal principal, HttpSession session) {
		if (principal == null)
			return "redirect:login";
		User user = systemService.getUserByLogin(principal.getName());
		session.setAttribute("loginUser", user);
		Role r = user.getDefaultUserRole();
		// Left menu of the user
		session.setAttribute("leftMenu", r.getRoleMenuContent());
		// Categories
		session.setAttribute("category", r.getRoleCategory());
		// Bundle the current login user to the session
		return "redirect:app/menu";
	}
}
