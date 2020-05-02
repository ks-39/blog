package com.ks39.myblog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.dto.ParamDto;
import com.ks39.myblog.model.entity.link;
import com.ks39.myblog.service.linkService;
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
public class adminLinkController {

    @Autowired
    private linkService linkService;

    @RequestMapping("/link")
    public String link(HttpServletRequest request, Model model, ParamDto paramDto){

        System.out.println("'admin:':X-Pjax "+request.getHeader("X-Pjax"));

        PageInfo<link> links = linkService.getLinks(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("links",links);

        if(request.getHeader("X-Pjax") != null){
            return "admin/page-fragment/link" ;
        }
        return "admin/link";
    }

    @RequestMapping("/link/add")
    public String addLink(link link,Model model,ParamDto paramDto){
        //1. 添加link
        linkService.addLink(link);
        //2. 局部刷新
        PageInfo<link> links = linkService.getLinks(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("links",links);
        return "admin/page-fragment/link";
    }

    @RequestMapping("/link/delete")
    public String deleteLink(link link,Model model,ParamDto paramDto){
        //1. 添加link
        linkService.deleteLink(link.getLink_id());
        //2. 局部刷新
        PageInfo<link> links = linkService.getLinks(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("links",links);
        return "admin/page-fragment/link";
    }

    @RequestMapping("/link/edit")
    public String editLink(link link,Model model,ParamDto paramDto){
        //1. 修改link
        linkService.editLink(link);
        //2. 局部刷新
        PageInfo<link> links = linkService.getLinks(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("links",links);
        return "admin/page-fragment/link";
    }

    @RequestMapping("/link/search")
    public String searchLink(ParamDto paramDto,Model model,HttpServletRequest request){
        //1. 搜索link
        PageInfo<link> links = linkService.searchLink(paramDto.getSearch(),paramDto.getPage(),paramDto.getSize(),paramDto.getNavigatePages());
        //2. 局部刷新
        model.addAttribute("links",links);
        //3. pjax请求
        if(request.getHeader("X-Pjax") != null){
            return "admin/page-fragment/link" ;
        }
        return "admin/link";

    }
}
