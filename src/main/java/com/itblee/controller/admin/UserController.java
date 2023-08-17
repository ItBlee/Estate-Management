package com.itblee.controller.admin;

import com.itblee.dto.PagedListHolder;
import com.itblee.dto.UserDTO;
import com.itblee.dto.UserListDTO;
import com.itblee.dto.UserSearchDTO;
import com.itblee.security.utils.SecurityUtils;
import com.itblee.service.UserService;
import com.itblee.service.impl.RoleServiceImpl;
import com.itblee.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.itblee.constant.SystemConstant.LIST_TABLE_ID;
import static com.itblee.constant.SystemConstant.MODEL;

@Controller(value = "usersControllerOfAdmin")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleServiceImpl roleService;

	@GetMapping(value = "/admin/user-list")
	public ModelAndView getUsers(
			@ModelAttribute(MODEL) UserSearchDTO model,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/user/list");

		PagedListHolder<UserListDTO> pages = new PagedListHolder<>();
		pages.setPage(DisplayTagUtils.pageOf(LIST_TABLE_ID, request));
		userService.fillSource(pages, model);

		mav.addObject("pages", pages);
		mav.addObject(MODEL, model);
		return mav;
	}

	@GetMapping(value = "/admin/user-edit")
	public ModelAndView addUser(@ModelAttribute(MODEL) UserDTO model) {
		ModelAndView mav = new ModelAndView("admin/user/edit");
		mav.addObject("roles", roleService.getRoles());
		mav.addObject(MODEL, model);
		return mav;
	}

	@GetMapping(value = "/admin/user-edit-{id}")
	public ModelAndView updateUser(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("admin/user/edit");
		UserDTO model = userService.findUserById(id).get();
		mav.addObject("roles", roleService.getRoles());
		mav.addObject(MODEL, model);
		return mav;
	}

	@GetMapping(value = "/admin/profile-{username}")
	public ModelAndView updateProfile(@PathVariable("username") String username) {
		ModelAndView mav = new ModelAndView("admin/user/profile");
		UserDTO model = userService.findOneByUserName(username).get();
		mav.addObject("roles", roleService.getRoles());
		mav.addObject(MODEL, model);
		return mav;
	}

	@GetMapping(value = "/admin/profile-password")
	public ModelAndView updatePassword() {
		ModelAndView mav = new ModelAndView("admin/user/password");
		String currentUsername = SecurityUtils.getPrincipal().getUsername();
		UserDTO model = userService.findOneByUserName(currentUsername).get();
		mav.addObject(MODEL, model);
		return mav;
	}

}
