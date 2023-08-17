package com.itblee.controller.admin;

import com.itblee.dto.*;
import com.itblee.security.utils.SecurityUtils;
import com.itblee.service.CustomerService;
import com.itblee.service.UserService;
import com.itblee.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.itblee.constant.SystemConstant.*;

@Controller(value = "customersControllerOfAdmin")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin/customer-list")
    public ModelAndView getCustomerList(
            @ModelAttribute(MODEL) CustomerSearchDTO searchModel,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/list");

        PagedListHolder<CustomerListDTO> pages = new PagedListHolder<>();
        pages.setPage(DisplayTagUtils.pageOf(LIST_TABLE_ID, request));
        customerService.fillSource(pages, searchModel);

        mav.addObject("pages", pages);
        if (SecurityUtils.hasRole(MANAGER))
            mav.addObject("staffs", userService.getStaffs());
        mav.addObject(MODEL, searchModel);
        return mav;
    }

    @GetMapping(value = "/admin/customer-edit")
    public ModelAndView addCustomer() {
        return new ModelAndView("admin/customer/edit");
    }

    @GetMapping(value = "/admin/customer-edit-{id}")
    public ModelAndView getCustomer(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject(MODEL, customerService.findById(id).get());
        return mav;
    }

}
