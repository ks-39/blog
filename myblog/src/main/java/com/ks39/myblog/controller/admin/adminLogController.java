package com.ks39.myblog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.dto.ParamDto;
import com.ks39.myblog.model.entity.log;
import com.ks39.myblog.service.logService;
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
public class adminLogController {

    @Autowired
    private logService logService;

    @RequestMapping("/log")
    public String log(HttpServletRequest request, Model model, ParamDto paramDto){

        System.out.println("'admin:':X-Pjax "+request.getHeader("X-Pjax"));

        PageInfo<log> logs = logService.getLogs(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("logs",logs);

        if(request.getHeader("X-Pjax") != null){
            return "admin/page-fragment/log" ;
        }
        return "admin/log";
    }

    @RequestMapping("/log/search")
    public String searchLog(ParamDto paramDto,Model model,HttpServletRequest request){

        //1. 日期格式转换
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = formatter.format(paramDto.getStart_time());
        String endTime = formatter.format(paramDto.getEnd_time());

        //2. 搜素评论
        PageInfo<log> logs = logService.searchLog(startTime, endTime, paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("logs",logs);
        //3. pjax请求
        System.out.println("'admin:':X-Pjax "+request.getHeader("X-Pjax"));

        return "admin/page-fragment/log" ;
    }
}
