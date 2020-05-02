package com.ks39.myblog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.dto.ParamDto;
import com.ks39.myblog.model.entity.tag;
import com.ks39.myblog.service.tagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
public class adminTagController {

    @Autowired
    private tagService tagService;

    @RequestMapping("/tag")
    public String tag(HttpServletRequest request, Model model, ParamDto paramDto){

        System.out.println("'admin:':X-Pjax "+request.getHeader("X-Pjax"));

        PageInfo<tag> tags = tagService.getAllTags(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("tags",tags);

        if(request.getHeader("X-Pjax") != null){
            return "admin/page-fragment/tag" ;
        }
        return "admin/tag";
    }

    @RequestMapping("/tag/add")
    public String addTag(tag tag,Model model,ParamDto paramDto){
        //1. 添加tag
        tagService.addTag(tag);
        //2. 局部刷新
        PageInfo<tag> tags = tagService.getAllTags(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("tags",tags);
        return "admin/page-fragment/tag";
    }

    @RequestMapping("/tag/delete")
    public String deleteTag(tag tag,Model model,ParamDto paramDto){
        //1. 删除tag
        tagService.deleteTag(tag.getTag_id());
        //2. 局部刷新
        PageInfo<tag> tags = tagService.getAllTags(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("tags",tags);
        return "admin/page-fragment/tag";
    }

    @RequestMapping("/tag/edit")
    public String editTag(tag tag,Model model,ParamDto paramDto){
        //1. 修改tag
        tagService.editTag(tag);
        //2. 局部刷新
        PageInfo<tag> tags = tagService.getAllTags(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());

        model.addAttribute("tags",tags);
        return "admin/page-fragment/tag";
    }

    @RequestMapping("/tag/search")
    public String searchTag(ParamDto paramDto,Model model,HttpServletRequest request){
        //1. 搜索category
        PageInfo<tag> tags = tagService.searchTag(paramDto.getSearch(), paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        //2. 局部刷新
        model.addAttribute("tags",tags);
        //3. pjax请求
        if(request.getHeader("X-Pjax") != null){
            return "admin/page-fragment/tag" ;
        }
        return "admin/tag";
    }
}
