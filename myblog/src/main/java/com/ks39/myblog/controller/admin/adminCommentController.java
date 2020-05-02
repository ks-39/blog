package com.ks39.myblog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.dto.ParamDto;
import com.ks39.myblog.model.entity.comment;
import com.ks39.myblog.service.commentService;
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
public class adminCommentController {

    @Autowired
    private commentService commentService;

    @RequestMapping("/comment")
    public String comment(HttpServletRequest request, Model model, ParamDto paramDto){

        System.out.println("'admin:':X-Pjax "+request.getHeader("X-Pjax"));
        PageInfo<comment> comments = commentService.getComments(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("comments",comments);

        if(request.getHeader("X-Pjax") != null){
            return "admin/page-fragment/comment" ;
        }
        return "admin/comment";
    }

    @RequestMapping("/comment/edit")
    public String editComment(comment comment,Model model,ParamDto paramDto){
        //1. 修改comment
        commentService.editComment(comment);
        //2. 局部刷新
        PageInfo<comment> comments = commentService.getComments(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("comments",comments);
        return "admin/page-fragment/comment";
    }


    @RequestMapping("/comment/search")
    public String searchComment(ParamDto paramDto,Model model,HttpServletRequest request){

        //1. 日期格式转换
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = formatter.format(paramDto.getStart_time());
        String endTime = formatter.format(paramDto.getEnd_time());

        //2. 搜素评论
        PageInfo<comment> comments = commentService.searchComment(startTime, endTime, paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("comments",comments);
        //3. pjax请求
        System.out.println("'admin:':X-Pjax "+request.getHeader("X-Pjax"));

        return "admin/page-fragment/comment" ;
    }
}
