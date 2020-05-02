package com.ks39.myblog.service;

import com.ks39.myblog.model.entity.config;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 13:17 2020/4/13
 */
public interface configService {

    //1. getConfigs(configs公共资源)
    List<config> getConfigs();

    //2. updateConfig()
    int updateConfig(config config);
}
