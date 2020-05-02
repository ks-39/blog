package com.ks39.myblog.controller;

import com.ks39.myblog.customizeException.CustomizeException;
import com.ks39.myblog.model.entity.exception;
import com.ks39.myblog.service.exceptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice   //ControllerAdvice用于处理异常的controller
@Slf4j
public class CustomizeExceptionHandler {

    @Autowired
    private exceptionService exceptionService;

    @ExceptionHandler(Exception.class)
    String handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {

        //如果e是CustommizeException的对象，，即代表出现自定义的异常，
        if (e instanceof CustomizeException) {

            //sys_exception存储e.getCode()、e.getMessage()
            exception exception = new exception();
            exception.setException_code(((CustomizeException) e).getCode());
            exception.setException_message(e.getMessage());
            exceptionService.addException(exception);

            model.addAttribute("ErrorMsg", e.getMessage()); //添加ErrorMsg为e.getMessage()。（读取枚举的错误信息）
        } else {
            //否则抛出非自定义异常
            model.addAttribute("ErrorMsg", "服务器迷失在太空了。。。");
        }

        return "blog/error";
    }
}
