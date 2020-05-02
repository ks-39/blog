package com.ks39.myblog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.dto.ParamDto;
import com.ks39.myblog.model.entity.category;
import com.ks39.myblog.service.categoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 17:35 2020/4/21
 */
@RequestMapping("/admin")
@Controller
@Slf4j
public class adminCategoryController {

    @Autowired
    private categoryService categoryService;

    @RequestMapping("/category")
    public String category(HttpServletRequest request, Model model, ParamDto paramDto){

        System.out.println("'admin:':X-Pjax "+request.getHeader("X-Pjax"));

        PageInfo<category> categories = categoryService.getAllCategories(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("categories",categories);

        if(request.getHeader("X-Pjax") != null){
            return "admin/page-fragment/category" ;
        }
        return "admin/category";
    }

    @RequestMapping("/category/add")
    public String addCategory(category category,Model model,ParamDto paramDto){
        //1. 添加category
        categoryService.addCategory(category);
        //2. 局部刷新
        PageInfo<category> categories = categoryService.getAllCategories(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("categories",categories);
        return "admin/page-fragment/category";
    }

    @RequestMapping("/category/delete")
    public String deleteCategory(category category,Model model,ParamDto paramDto){
        //1. 删除category
        categoryService.deleteCategory(category.getCategory_id());
        //2. 局部刷新
        PageInfo<category> categories = categoryService.getAllCategories(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("categories",categories);
        return "admin/page-fragment/category";
    }

    @RequestMapping("/category/edit")
    public String editCategory(category category,Model model,ParamDto paramDto){
        //1. 修改category
        categoryService.editCategory(category);
        //2. 局部刷新
        PageInfo<category> categories = categoryService.getAllCategories(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("categories",categories);
        return "admin/page-fragment/category";
    }

    @RequestMapping("/category/search")
    public String searchCategory(ParamDto paramDto,Model model,HttpServletRequest request){
        //1. 搜索category
        PageInfo<category> categories = categoryService.searchCategory(paramDto.getSearch(),paramDto.getPage(),paramDto.getSize(),paramDto.getNavigatePages());
        //2. 局部刷新
        model.addAttribute("categories",categories);
        //3. pjax请求
        if(request.getHeader("X-Pjax") != null){
            return "admin/page-fragment/category" ;
        }
        return "admin/category";
    }
}
