package com.ks39.myblog.service.Impl;

import com.ks39.myblog.mapper.userMapper;
import com.ks39.myblog.model.entity.user;
import com.ks39.myblog.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 16:51 2020/4/26
 */
@Service
public class userServiceImpl implements userService {

    @Autowired
    private userMapper userMapper;

    @Override
    public user findByUserName(String username) {
        return userMapper.findByUsername(username);
    }
}
