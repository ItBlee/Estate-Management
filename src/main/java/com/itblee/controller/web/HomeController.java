package com.itblee.controller.web;

import com.itblee.security.utils.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@GetMapping(value = "/trang-chu")
	public ModelAndView homePage() {
		return new ModelAndView("web/home");
	}

	@GetMapping(value = "/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@GetMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityUtils.getAuthentication();
		if (auth != null)
			new SecurityContextLogoutHandler().logout(request, response, auth);
		return new ModelAndView("redirect:/trang-chu");
	}
}
