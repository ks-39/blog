package com.ks39.myblog.aspect;

import com.ks39.myblog.model.entity.log;
import com.ks39.myblog.service.logService;
import com.ks39.myblog.service.viewService;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;


@Aspect     //aop切面
@Component      //spring组件
public class controllerAop {

    @Autowired
    private logService logService;

    @Autowired
    private viewService viewService;

    private final Logger LOGGER = LoggerFactory.getLogger(controllerAop.class) ;

    //指定aspect()方法为所有Controller的切面
    @Pointcut("execution(public * com.ks39.myblog.controller..*(..))")
    public void aspect(){}

    //在aspect切面之前执行
    @Before("aspect()")
    public void before(JoinPoint joinPoint){

        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        //获取浏览器信息
        String ua = request.getHeader("User-Agent");
        //转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        //获取浏览器信息
        Browser browser = userAgent.getBrowser();
        //获取系统信息
        OperatingSystem os = userAgent.getOperatingSystem();

        //系统名称
        String system = os.getName();
        //浏览器名称
        String browserName = browser.getName();

        //1. sys_view增加
        viewService.addView(request.getRemoteAddr(),system,browserName);

        //3. 打印日志记录
        System.out.println('\n'+"开始记录:");
        LOGGER.info("URL : " + request.getRequestURI().toString());
        LOGGER.info("HTTP_METHOD : " + request.getMethod());
        LOGGER.info("IP : " + request.getRemoteAddr());
        LOGGER.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        LOGGER.info(":" + joinPoint.getSignature() );
        LOGGER.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    //在aspect切面后执行
    @AfterReturning(returning = "result", pointcut = "aspect()")
    public void afterReturning(JoinPoint joinPoint,Object result){

        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //1. 填充log属性
        log log = new log();
        log.setIp(request.getRemoteAddr());
        log.setHTTP_URL(request.getRequestURL().toString());
        log.setHTTP_TYPE(request.getMethod());
        log.setCLASS_METHOD(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.setCLASS_METHOD_ARGS(Arrays.toString(joinPoint.getArgs()));
        log.setMETHOD_RESPONSE(result.toString());

        //2. 添加日志记录
        logService.addLog(log);

        LOGGER.info("RESPONSE : " + result);
    }

    //在aspect切面后执行
    @AfterThrowing(throwing = "exception", pointcut = "aspect()")
    public void afterThrowing(JoinPoint joinPoint,Throwable exception){

        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //1. 填充log属性
        log log = new log();
        log.setIp(request.getRemoteAddr());
        log.setHTTP_URL(request.getRequestURL().toString());
        log.setHTTP_TYPE(request.getMethod());
        log.setCLASS_METHOD(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.setCLASS_METHOD_ARGS(Arrays.toString(joinPoint.getArgs()));
        log.setMETHOD_RESPONSE(exception.toString());

        //2. 添加日志记录
        logService.addLog(log);

        LOGGER.info("EXCEPTION : " + exception);
    }
}
