package com.ks39.myblog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.dto.ParamDto;
import com.ks39.myblog.model.entity.view;
import com.ks39.myblog.service.viewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 17:35 2020/4/21
 */
@RequestMapping("/admin")
@Controller
@Slf4j
public class adminViewController {

    @Autowired
    private viewService viewService;

    @RequestMapping("/view")
    public String view(HttpServletRequest request, Model model, ParamDto paramDto){

        System.out.println("'admin:':X-Pjax "+request.getHeader("X-Pjax"));

        PageInfo<view> views = viewService.getViews(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("views",views);

        if(request.getHeader("X-Pjax") != null){
            return "admin/page-fragment/view" ;
        }
        return "admin/view";
    }

    @RequestMapping("/view/search")
    public String searchView(ParamDto paramDto,Model model,HttpServletRequest request){

        //1. 日期格式转换
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = formatter.format(paramDto.getStart_time());
        String endTime = formatter.format(paramDto.getEnd_time());

        //2. 搜素评论
        PageInfo<view> views = viewService.searchView(startTime, endTime, paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("views",views);
        //3. pjax请求
        System.out.println("'admin:':X-Pjax "+request.getHeader("X-Pjax"));

        return "admin/page-fragment/view" ;
    }
}
