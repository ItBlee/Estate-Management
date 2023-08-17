package com.itblee.controller.admin;

import com.itblee.dto.BuildingDTO;
import com.itblee.dto.BuildingListDTO;
import com.itblee.dto.BuildingSearchDTO;
import com.itblee.dto.PagedListHolder;
import com.itblee.security.utils.SecurityUtils;
import com.itblee.service.BuildingService;
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

@Controller(value = "buildingsControllerOfAdmin")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin/building-list")
    public ModelAndView getBuildingList(
            @ModelAttribute(MODEL) BuildingSearchDTO searchModel,
            HttpServletRequest request) {
        ModelAndView mav = init("admin/building/list");

        PagedListHolder<BuildingListDTO> pages = new PagedListHolder<>();
        pages.setPage(DisplayTagUtils.pageOf(LIST_TABLE_ID, request));
        buildingService.fillSource(pages, searchModel);

        mav.addObject("pages", pages);
        if (SecurityUtils.hasRole(MANAGER))
            mav.addObject("staffs", userService.getStaffs());
        mav.addObject(MODEL, searchModel);
        return mav;
    }

    @GetMapping(value = "/admin/building-edit")
    public ModelAndView addBuilding() {
        return init("admin/building/edit");
    }

    @GetMapping(value = "/admin/building-edit-{id}")
    public ModelAndView getBuilding(@PathVariable("id") Long id) {
        ModelAndView mav = init("admin/building/edit");
        BuildingDTO building = buildingService.findById(id).get();
        mav.addObject(MODEL, building);
        return mav;
    }

    private ModelAndView init(String viewName) {
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("districts", buildingService.getDistricts());
        mav.addObject("rentTypes", buildingService.getRentTypes());
        return mav;
    }

}
