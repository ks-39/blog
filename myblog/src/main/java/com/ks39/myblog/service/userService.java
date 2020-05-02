package com.ks39.myblog.service;

import com.ks39.myblog.model.entity.user;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 16:50 2020/4/26
 */
public interface userService {

    user findByUserName(String username);
}
