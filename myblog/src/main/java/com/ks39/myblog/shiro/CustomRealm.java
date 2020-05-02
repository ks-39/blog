package com.ks39.myblog.shiro;

import com.ks39.myblog.model.entity.user;
import com.ks39.myblog.service.userService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class CustomRealm extends AuthorizingRealm {

    //0. 注入业务对象
    @Autowired
    private userService userService;

    //1. 授权(该项目不需要做)
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //2. 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1. 将authenticationToken对象转换为UsernamePasswordToken
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //2. 查找数据库
        user user = userService.findByUserName(token.getUsername());
        System.out.println(user);
        //3. 验证
        if(user==null){
            return null;
        }
        //4. 返回认证
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes(user.getUsername()),getName());
        return simpleAuthenticationInfo;
    }


}
