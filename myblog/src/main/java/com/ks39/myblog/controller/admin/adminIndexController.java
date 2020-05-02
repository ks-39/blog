package com.ks39.myblog.controller.admin;

import com.ks39.myblog.model.entity.*;
import com.ks39.myblog.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 14:59 2020/4/19
 */
@Controller
@RequestMapping("/admin")
@Slf4j
public class adminIndexController {

    @Autowired
    private blogService blogService;

    @Autowired
    private commentService commentService;

    @Autowired
    private categoryService categoryService;

    @Autowired
    private tagService tagService;

    @Autowired
    private viewService viewService;

    @Autowired
    private exceptionService exceptionService;

    @RequestMapping("/index")
    public String Index(HttpServletRequest request, Model model){

        System.out.println("'admin:':X-Pjax "+request.getHeader("X-Pjax"));

        //1. 统计博客数量
        blog blogsNumber = blogService.getBlogsNumber();
        model.addAttribute("blogsNumber",blogsNumber);

        //2. 统计评论数量
        comment commentsNumber = commentService.getCommentsNumber();
        model.addAttribute("commentsNumber",commentsNumber);

        //3. 统计分类数量
        category categoriesNumber = categoryService.getCategoriesNumber();
        model.addAttribute("categoriesNumber",categoriesNumber);

        //4. 统计标签数量
        tag tagsNumber = tagService.getTagsNumber();
        model.addAttribute("tagsNumber",tagsNumber);

        //5. 统计浏览量
        view views = viewService.getViewsCount();
        model.addAttribute("views",views);

        //6. 统计异常日志数
        exception exceptions = exceptionService.getExceptionsCount();
        model.addAttribute("exceptions",exceptions);

        if(request.getHeader("X-Pjax") != null){
            return "admin/page-fragment/index" ;
        }

        return "admin/index";
    }
}
