package com.ks39.myblog.service.Impl;

import com.ks39.myblog.mapper.configMapper;
import com.ks39.myblog.model.entity.config;
import com.ks39.myblog.service.configService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 13:18 2020/4/13
 */
@Service
public class configServiceImpl implements configService {

    @Autowired
    private configMapper configMapper;

    //1. getConfigs(公共资源)
    @Override
    public List<config> getConfigs() {
        return configMapper.getConfigs();
    }

    //2. updateConfi
    @Override
    public int updateConfig(config config) {
       return configMapper.updateConfig(config);
    }
}
