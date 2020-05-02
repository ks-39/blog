package com.ks39.myblog.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/*
该工具类用于生成数据库password
 */
public class PasswordGenerateTest {
    public static void main(String[] args) {
        /*
        1. algorithmName:指定为MD5
        2. source:明文密码
        3. ByteSource.Util.bytes:设置盐,这里指定为username，在CustomRealm中AuthenticationInfo中的simpleAuthentication要对应
            如：salt设置为username，则simpleAuthenticationInfo的ByteSource.Util.bytes()的参数要为user.getUsername()才能通过认证
                如果设置了salt，则将参数改为user.getSalt()

        4. hashlterations:加密次数为4次，与ShiroConfig的比较器要相对于
        将输出结果作为密码存入数据库password
         */
        String Md5Password = new Md5Hash("password", "username", 4).toHex();
        System.out.println(Md5Password);
    }
}