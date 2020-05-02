package com.ks39.myblog.mapper;

import com.ks39.myblog.model.entity.user;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 16:52 2020/4/26
 */
public interface userMapper {

    user findByUsername(String username);
}
