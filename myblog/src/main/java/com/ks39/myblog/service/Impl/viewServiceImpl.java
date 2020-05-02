package com.ks39.myblog.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ks39.myblog.mapper.viewMapper;
import com.ks39.myblog.model.entity.view;
import com.ks39.myblog.service.viewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 11:36 2020/4/21
 */
@Service
public class viewServiceImpl implements viewService {

    @Autowired
    private viewMapper viewMapper;

    //1. 添加浏览记录
    @Override
    public void addView(String ip,String browser,String system) {
        viewMapper.addView(ip,browser,system);
    }

    //2. 统计浏览量
    @Override
    public view getViewsCount() {
        return viewMapper.getViewsCount();
    }

    //3. 浏览列表(分页)
    @Override
    public PageInfo<view> getViews(Integer page, Integer size, Integer navigatePage) {
        PageHelper.startPage(page,size);
        List<view> views = viewMapper.getViews();
        return new PageInfo<>(views,navigatePage);
    }

    @Override
    public PageInfo<view> searchView(String startTime, String endTime, Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        List<view> views = viewMapper.searchView(startTime, endTime);
        return new PageInfo<>(views,navigatePages);
    }
}
