package com.ks39.myblog.mapper;

import com.ks39.myblog.model.entity.config;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 13:19 2020/4/13
 */
public interface configMapper {

    //1. getConfigs(公共资源)
    List<config> getConfigs();

    int updateConfig(config config);
}
