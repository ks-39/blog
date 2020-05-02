package com.ks39.myblog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.dto.ParamDto;
import com.ks39.myblog.model.entity.comment;
import com.ks39.myblog.model.entity.config;
import com.ks39.myblog.service.configService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 17:35 2020/4/21
 */
@RequestMapping("/admin")
@Controller
@Slf4j
public class adminConfigController {

    @Autowired
    private configService configService;

    @RequestMapping("/config")
    public String config(HttpServletRequest request, Model model){
        System.out.println("'admin:':X-Pjax "+request.getHeader("X-Pjax"));
        List<config> configs = configService.getConfigs();
        model.addAttribute("configs",configs);
        if(request.getHeader("X-Pjax") != null){
            return "admin/page-fragment/config" ;
        }
        return "admin/config";
    }

    //onclick()进行局部刷新
    @RequestMapping("/config/post")
    public String updateConfig(config config,Model model){
        //更新config
        configService.updateConfig(config);
        List<config> configs = configService.getConfigs();
        model.addAttribute("configs",configs);
        return "admin/page-fragment/config";
    }

}
