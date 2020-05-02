package com.ks39.myblog.controller.admin;

import com.ks39.myblog.model.entity.user;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 16:20 2020/4/26
 */
@RequestMapping("/admin")
@Controller
@Slf4j
public class adminLoginController {


    @GetMapping("/login")
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String checkLogin(user user, Model model){

        //1. 获取Subject
        Subject subject = SecurityUtils.getSubject();

        //4. 封装前端数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        //5. 执行login方法
        try{
            subject.login(token);
        }catch (UnknownAccountException e){
            model.addAttribute("LoginMsg","用户名错误");
        }catch (IncorrectCredentialsException e){
            model.addAttribute("LoginMsg","密码错误");
        }

        //6. 判断login结果
        if(subject.isAuthenticated()){
            return "forward:/admin/index";
        }else {
            token.clear();
            return "admin/login";
        }
    }

}
