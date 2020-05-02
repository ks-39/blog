package com.ks39.myblog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.dto.ParamDto;
import com.ks39.myblog.model.entity.exception;
import com.ks39.myblog.service.exceptionService;
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
public class adminExceptionController {

    @Autowired
    private exceptionService exceptionService;

    @RequestMapping("/exception")
    public String exception(HttpServletRequest request, Model model, ParamDto paramDto){

        System.out.println("'admin:':X-Pjax "+request.getHeader("X-Pjax"));

        PageInfo<exception> exceptions = exceptionService.getExceptions(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("exceptions",exceptions);

        if(request.getHeader("X-Pjax") != null){
            return "admin/page-fragment/exception" ;
        }
        return "admin/exception";
    }

    @RequestMapping("/exception/search")
    public String searchException(ParamDto paramDto,Model model,HttpServletRequest request){

        //1. 日期格式转换
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = formatter.format(paramDto.getStart_time());
        String endTime = formatter.format(paramDto.getEnd_time());

        //2. 搜素评论
        PageInfo<exception> exceptions = exceptionService.searchException(startTime, endTime, paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("exceptions",exceptions);
        //3. pjax请求
        System.out.println("'admin:':X-Pjax "+request.getHeader("X-Pjax"));

        return "admin/page-fragment/exception" ;
    }
}
